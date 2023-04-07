// SPDX-License-Identifier: MIT
pragma solidity ^0.8.4;

import "@openzeppelin/contracts/token/ERC721/extensions/ERC721Enumerable.sol";
import "@openzeppelin/contracts/utils/Counters.sol";

contract MyToken is ERC721 {
    /// @custom:oz-upgrades-unsafe-allow constructor
    constructor() ERC721("FANFTASY", "NFT") {}


    uint256 private _tokenIds;
    mapping(uint256 => string) tokenURIs;

    function tokenURI(uint256 tokenId) public view virtual override returns (string memory) {return tokenURIs[tokenId];}

    function current() public view returns (uint256) {
        return _tokenIds;
    }

    function create(address to, string memory tokenURI, address factory) public returns (uint256)
    {
        _tokenIds++;
        uint256 newItemId = current();
        _safeMint(to, newItemId);
        tokenURIs[newItemId] = tokenURI;
        // setApprovalForAll(factory, true);
        _approve(factory,newItemId);
        return newItemId;
    }

    event tradeRecord(address from, address to, uint256 indexed tokenId, uint256 price, uint256 indexed tradeDate);
    
    function transferFrom(
        address from,
        address to,
        uint256 tokenId
    ) public virtual override {
        //solhint-disable-next-line max-line-length
        require(_isApprovedOrOwner(_msgSender(), tokenId), "ERC721: caller is not token owner or approved");
        _transfer(from, to, tokenId);
    }
    
    function transferAndRecord(
        address from,
        address to,
        uint256 tokenId,
        uint256 price,
        address factory
    )public{
        transferFrom(from, to, tokenId);
        uint256 endedAt = block.timestamp;
        emit tradeRecord( from,  to, tokenId, price ,endedAt);
        _approve(factory,tokenId);
    }
}
