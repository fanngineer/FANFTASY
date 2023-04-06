// SPDX-License-Identifier: MIT
pragma solidity ^0.8.4;

import "@openzeppelin/contracts/access/Ownable.sol";
import "contracts/NFT.sol";
import "contracts/Sale.sol";

contract SaleFactory is Ownable {
    address public admin; //관리자 계정
    address public nftContractAddress; //nft스마트 컨트랙트
    
    address[] public sales; //판매 정보 저장할 배열
    uint256 public saleIds; //판매 정보 인덱스
    MyToken private _NFTContract;
    mapping(uint256 => address) tokenLastSaleAdd; //tokenId의 마지막 Sale ContractAdd

    event NewSale(
        address indexed _saleContract,
        address indexed _owner,
        uint saleId,
        uint256 indexed itemId,
        uint256 price
    );

    constructor(address _nftContractAddress) {
        admin = msg.sender;
        nftContractAddress = _nftContractAddress;
        _NFTContract = MyToken(_nftContractAddress);
    }

    /**
     * @dev 반드시 구현해야하는 함수입니다. 
     */
    function createSale(
        uint256 _tokenId,
        uint256 _price
    ) public returns (address) {
        //생성될 판매정보의 인덱스
        //uint256 newSaleId = current();
        //판매정보 생성
        require(_NFTContract.ownerOf(_tokenId) == msg.sender, "Seller is not owner.");
        Sale newSale = new Sale(payable(msg.sender), _tokenId, _price, nftContractAddress, admin);
        //판매 정보 등록
        address saleContractAddress = address(newSale);
        // Sale 컨트랙트에 거래 대상NFT에 대한 권한
        // setApprovalForAll(factory, true);
        // _NFTContract.approve(address(saleFactory), _tokenId, { from: msg.sender });
        // Sale 컨트랙트에게 NFT 소유를 넘겨버림
        _NFTContract.transferFrom(msg.sender, saleContractAddress, _tokenId);
        //기록 남기기
        sales.push(saleContractAddress);
        tokenLastSaleAdd[_tokenId] = saleContractAddress;
        emit NewSale(saleContractAddress, msg.sender, saleIds, _tokenId, _price);
        saleIds++;
        return saleContractAddress;
    }
    // event NewDrops(uint256 indexed newItemId, address indexed saleAddress);
    // function createNFTAndSale(address to, string memory tokenURI, uint256 price) public {
    //     uint256 newItemId = NFTContract.create(to, tokenURI);
    //     Sale newSale = new Sale(payable(to), newItemId, price, nftContractAddress, admin);
    //     address saleContractAddress = address(newSale);
    //     sales.push(saleContractAddress);
    //     emit NewSale(saleContractAddress, to, saleIds, newItemId, price);
    // }
    function allSales() public view returns (address[] memory) {
        return sales;
    }

    function getSaleAddress(uint256 _tokenId) public returns(address saleAddress){
        return tokenLastSaleAdd[_tokenId];
    }
}


