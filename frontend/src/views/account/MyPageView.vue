<template>
  <div class="mypage-entire">
    <div>
      <!-- 프로필 이미지, 메타마스크 주소, NFT 충전 버튼 -->
      <div style="display: flex; justify-content: space-between; width: 800px;">
        <div>
          <!-- 프로필 이미지 -->
          <div class="circle1">
            <div class="circle2">
              <div class="circle3">
                <label for="uploadImg">
                  <div class="profile-update profile-img-upload-btn " @mouseover="this.isHovered = true" @mouseout="this.isHovered = false"
                  style="cursor: pointer;">
                    <div class="profile-cover"></div>
                    <img :src="this.profileImage" style="border-radius: 50%; width: 154px; height: 154px; object-fit: fill;" alt="프로필사진" />
                    <v-icon v-show="this.isHovered === true" icon="mdi-fountain-pen-tip" style="z-index: 999; position: absolute;"></v-icon>
                    <!-- <img :src="require('@/assets/EthereumIcon.png')" alt="로고" class="profile-logo"> -->
                  </div>
                </label>
                <input id="uploadImg" type="file" ref="fileInput" @change="uploadFile">
              </div>
            </div>
          </div>
          
          <div style="margin: 10px; font-weight: bold; font-size: 32px;">
            <span v-if="changeNickname === false">
              {{ nickname }} 님
            </span>
            <span v-else>
              <input v-model="this.$store.state.nickname" id="nickname" type="text" placeholder="닉네임을 적어주세요"> 
            </span>
            <v-icon v-if="changeNickname === false" icon="mdi-lead-pencil"  @click="changeNicknameBtn" class="profile-pencil-button"></v-icon>
            <button v-else class="edit-profile-confirm" @click="confirmEditProfile">수정</button>
            <!-- <router-link to="/mypageupdate" style="text-decoration: none;">
            </router-link> -->
          </div>

          <!-- 메타마스크 주소 -->
          <div class="address-inline">
            <img :src="require('@/assets/metamask_logo.png')" alt="여우" style="width:35px; height: 25px; padding-left: 5px; padding-right: 5px;">
            <div id="metamask-address">{{address}}</div>
            <img :src="require('@/assets/copy.png')" alt="복사" style="width:28px; height: 20px; padding-left: 5px; padding-right: 5px; " @click="copyAddress">
          </div>
        </div>
        
        <div v-show="changeNickname === false" style="margin-top: auto;">
          <router-link :to="{name: 'MarketAddView'}" style="text-decoration: none;">
            <button v-if="this.role === 'artist'" class="charge-button" @click="goToAdd()" >
              NFT 생성
            </button>
          </router-link>
          <button class="charge-button" @click="charge()">
          <!-- <button class="charge-button" @click="showVideoModal('VIDEO_ID');"> -->
            FTS 충전
          </button>
          <video-modal :video-id="videoId" v-if="isVideoModalOpen" @close="closeVideoModal" />
        </div>
      </div>
      
      <div class="mypage-filter-tab" >
        <button @click="clickOwnedNFT" class="mypage-filter-tab-part" tabindex="-1" @keydown.prevent="handleBtnDown" >
          보유 NFT
        </button>
        |
        <button @click="clickNFTLog" class="mypage-filter-tab-part" tabindex="-1" @keydown.prevent="handleBtnDown"> 
          거래 내역 
        </button>
        |
        <button @click="clickAttractedNFT" class="mypage-filter-tab-part" tabindex="-1" @keydown.prevent="handleBtnDown">
          관심 아이템
        </button>
        <div class="indicator" :style="{transform:'translateX('+filterTapX+'%)'}"></div>
      </div>
  
      <hr style="width:100%">
  
      <!-- 메뉴 콤보박스 -->
      <div v-if="this.seto === 1" style="display: flex; justify-content: end; margin-top: 10px;">
        <div style="margin-left:14px; width:200px; border-radius: 50% !important;">
          <div>
            <select  class="input-category input font-medium" v-model="selected" name="" id="" @change="this.onItemSelected()">
              <option value="1" selected>최신 순</option>
              <option value="2">이름 순 : A→Z</option>
              <option value="3">이름 순 : Z→A</option>
            </select>
            <!-- <v-combobox v-model="selected" :items="myNFTmenu" native="onItemSelected"></v-combobox>  -->
          </div>
        </div>
      </div>

      <div v-if="this.seto === 3" style="display: flex; justify-content: start; margin-top: 10px;">
        <div style="margin-left:14px; width:200px; border-radius: 50% !important;">
          <div>
            <button
                v-on:click="selectDrops"
                v-if="sedm === 1"
                class="filter-btn"
              >
                DROPS
              </button>
              <button v-on:click="selectDrops" v-else class="filter-btn2">
                DROPS
              </button>
              <button
                v-on:click="selectMarket"
                v-if="sedm === 2"
                class="filter-btn"
              >
                MARKET
              </button>
              <button v-on:click="selectMarket" v-else class="filter-btn2">
                MARKET
              </button>
          </div>
        </div>
      </div>
      <v-container v-if="this.seto === 1" style="width:800px">
        <v-row>
          <v-col v-for="(nft,index) in userNFTs" :key="index" cols="12" md="3">
            <div class="own-nfts-card">
              <button v-if="nft.nftSource.fileType === 'image'" @click="showModalImg(nft)" class="button-owned-nft">
                <v-img v-if="nft.nftSource.fileType === 'image'" :src="nft.nftSource.fileCID" alt="이미지" class="nft-img-owned"></v-img>
                <div style="margin-left: 5px; margin-bottom: 15px">
                  <div class="item-name" style="font-size:20px;">
                    {{ nft.nftSource.title }}
                  </div>
                  <div style="font-size:15px">
                    by {{ nft.nftSource.regArtist.nickname }}
                  </div>
                  <div style="font-size:15px">
                    <span>
                      #{{ nft.editionNum }}
                    </span>
                    <span style="font-size:10px">
                      edition
                    </span>
                  </div>
                </div>
              </button>
              <button v-if="nft.nftSource.fileType === 'video'" @click="showModalVideo(nft)" class="button-owned-nft">
                <video v-if="nft.nftSource.fileType === 'video'" :src="nft.nftSource.fileCID" alt="비디오" class="nft-img-owned" ref="videoPlayer" @mouseover="playVideo" @mouseout="stopVideo" muted></video>
                <div style="margin-left: 5px; margin-bottom: 15px">
                    <div class="item-name" style="font-size:20px;">
                      {{ nft.nftSource.title }}
                    </div>
                    <div style="font-size:15px">
                      by {{ nft.nftSource.regArtist.nickname }}
                    </div>
                    <div style="font-size:15px">
                      <span>
                        #{{ nft.editionNum }}
                      </span>
                      <span style="font-size:10px">
                        edition
                      </span>
                    </div>
                  </div>
              </button>
              <button v-if="nft.nftSource.fileType === 'audio'" @click="showModalAudio(nft)" class="button-owned-nft">
                <audio v-if="nft.nftSource.fileType === 'audio'" controls :src="nft.nftSource.fileCID" alt="오디오" class="nft-img-owned"></audio>
                <div style="margin-left: 5px; margin-bottom: 15px">
                    <div class="item-name" style="font-size:20px;">
                      {{ nft.nftSource.title }}
                    </div>
                    <div style="font-size:15px">
                      by {{ nft.nftSource.regArtist.nickname }}
                    </div>
                    <div style="font-size:15px">
                      <span>
                        #{{ nft.editionNum }}
                      </span>
                      <span style="font-size:10px">
                        edition
                      </span>
                    </div>
                  </div>
              </button>
            </div>
            <!-- <button v-if="nfr.nftSource.fileType === 'audio'" @click="showModalAudio">오디오 팔아</button> -->
            
            <!-- <div class="own-nfts-card">
              <router-link :to="{name:'UserNFTView', params:{nftId: nft.nftSource.nftSourceId}}" style="min-width:164px; text-decoration: none; color:black; display:flex; flex-direction: column; justify-content: space-between;">
                <div>
                  <v-img v-if="nft.nftSource.fileType === 'image'" :src="nft.nftSource.fileCID" alt="이미지" class="nft-img-owned"></v-img>
                  <video v-if="nft.nftSource.fileType === 'video'" :src="nft.nftSource.fileCID" alt="비디오" class="nft-img-owned" ref="videoPlayer" @mouseover="playVideo" @mouseout="stopVideo" muted></video>
                  <audio v-if="nft.nftSource.fileType === 'audio'" controls :src="nft.nftSource.fileCID" alt="오디오" class="nft-img-owned"></audio>
                </div>
                <div style="margin-left: 5px; margin-bottom: 15px">
                  <div class="item-name" style="font-size:20px;">
                    {{ nft.nftSource.title }}
                  </div>
                  <div style="font-size:15px">
                    by {{ nft.nftSource.regArtist.nickname }}
                  </div>
                  <div style="font-size:15px">
                    <span>
                      #{{ nft.editionNum }}
                    </span>
                    <span style="font-size:10px">
                      edition
                    </span>
                  </div>
                  
                </div>
              </router-link>
            </div> -->
          </v-col>
        </v-row>
      </v-container>
      
      <v-container v-if="this.seto === 3 & this.sedm === 1" style="width:800px">
        <v-row>
          <v-col v-for="(nft, index) in userLikeDropsNFTs" :key="index" cols="12" md="3">
            <div class="own-nfts-card">
              <router-link :to="{name:'MarketDetailView', params:{id: nft.nftSourceId}}" style="min-width:164px; text-decoration: none; color:black; display:flex; flex-direction: column; justify-content: space-between;">
                <div>
                  <!-- <img :src="nft.nftSource.fileCID" alt="no" class="nft-img-owned"> -->
                  <v-img v-if="nft.fileType === 'image'" :src="nft.fileCID" alt="이미지" class="nft-img-owned"></v-img>
                  <video v-if="nft.fileType === 'video'" :src="nft.fileCID" alt="비디오" class="nft-img-owned" ref="videoPlayer" @mouseover="playVideo" @mouseout="stopVideo" muted></video>
                  <audio v-if="nft.fileType === 'audio'" controls :src="nft.fileCID" alt="오디오" class="nft-img-owned"></audio>
                </div>
                <div style="margin-left: 5px; margin-bottom: 15px">
                  <div class="item-name" style="font-size:20px;">
                    {{ nft.title }}
                  </div>
                  <div style="font-size:15px">
                    by {{ nft.regArtist.nickname }}
                  </div>
                  <!-- <div style="font-size:15px">
                    {{ nft.nftSource.originPrice }} FTS
                  </div> -->
                  <div style="font-size:15px">
                    <span>
                      {{ nft.remainNum }} / {{ nft.totalNum }} 개
                    </span>
                    <!-- <span style="font-size:10px">
                      edition
                    </span> -->
                  </div>
                  
                </div>
              </router-link>
            </div>
          </v-col>
        </v-row>
      </v-container>

      <v-container v-if="this.seto === 3 & this.sedm === 2" style="width:800px">
        <v-row>
          <v-col v-for="(nft, index) in userLikeMarketNFTs" :key="index" cols="12" md="3">
            <div class="own-nfts-card">
              <router-link :to="{name:'ResellDetailView', params:{id: nft.nftSource.nftSourceId}}" style="min-width:164px; text-decoration: none; color:black; display:flex; flex-direction: column; justify-content: space-between;">
                <div>
                  <!-- <img :src="nft.nftSource.fileCID" alt="no" class="nft-img-owned"> -->
                  <v-img v-if="nft.nftSource.fileType === 'image'" :src="nft.nftSource.fileCID" alt="이미지" class="nft-img-owned"></v-img>
                  <video v-if="nft.nftSource.fileType === 'video'" :src="nft.nftSource.fileCID" alt="비디오" class="nft-img-owned" ref="videoPlayer" @mouseover="playVideo" @mouseout="stopVideo" muted></video>
                  <audio v-if="nft.nftSource.fileType === 'audio'" controls :src="nft.nftSource.fileCID" alt="오디오" class="nft-img-owned"></audio>
                </div>
                <div style="margin-left: 5px; margin-bottom: 15px">
                  <div class="item-name" style="font-size:20px;">
                    {{ nft.nftSource.title }}
                  </div>
                  <div style="font-size:15px">
                    by {{ nft.nftSource.regArtist.nickname }}
                  </div>
                  <!-- <div style="font-size:15px">
                    {{ nft.nftSource.originPrice }} FTS
                  </div> -->
                  <div style="font-size:15px">
                    <!-- <span>
                      # {{ nft.editionNum }}
                    </span> -->
                    <!-- <span style="font-size:10px">
                      edition
                    </span> -->
                  </div>
                  
                </div>
              </router-link>
            </div>
          </v-col>
        </v-row>
      </v-container>

    </div>
    

  </div>
</template>

<script>
// import LoginUserHeaders from "@/components/headers/LoginUserHeaders.vue"
// import NotLoginUserHeaders from "@/components/headers/NotLoginUserHeaders.vue"
import SaleFactoryABI from "../../../path/to/SaleFactoryABI.json";
import SaleABI from "../../../path/to/SaleABI.json";
import NFTABI from "../../../path/to/NFTABI.json";
import BankABI from "../../../path/to/BankABI.json";
import Web3 from "web3"
import VueCookies from 'vue-cookies';
// import { Buffer } from 'buffer';
import VideoModal from "@/components/mypage/modal.vue";
// import NFTCard from "@/components/market/NFTCard.vue"
import Swal from 'sweetalert2'

export default {
  name: 'MyPageView',
  components: {
    // NFTCard,
    VideoModal,
  },
  data(){
    return {
      nickname: VueCookies.get('nickname'),
      address: null,

      filterTapX: 0,
      btnOwnedNFT: true,
      btnNFTLog: false,
      btnAttractedNFT: false ,
      role: "fan",

      myNFTmenu : ['최신 순', '이름 순 : A→Z','이름 순 : Z→A'],
      profileImage: VueCookies.get('profileImage'),

      isHovered: false,
      userNFTs: [],
      userLikeDropsNFTs: [],
      userLikeMarketNFTs: [],
      userData : [],
      file: null,
      changeNickname: false,
      type: 1,
      selected: 1,
      isVideoModalOpen: false,
      videoId: "",
      seto: 1,
      sedm: 1,
      nftInfo:[],
    }
  },
  async created() {
    await this.$store.dispatch('getAccount');
    this.address = this.$store.state.address;

    this.getUserNFTs(this.type);
    this.getUserDetail();
    this.getuserLikeDropsNFTs()
    this.getuserLikeMarketNFTs()
  },
  methods:{
    getuserLikeDropsNFTs(){
      this.$store.dispatch('getuserLikeDropsNFTs')
      this.userLikeDropsNFTs = this.$store.userLikeDropsNFTs
    },

    getuserLikeMarketNFTs(){
      this.$store.dispatch('getuserLikeMarketNFTs')
      this.userLikeMarketNFTs = this.$store.userLikeMarketNFTs
    },


    
    selectDrops() {
      this.sedm = 1
    },
    selectMarket() {
      this.sedm = 2
    },
    
    showVideoModal(videoId) {
      this.videoId = videoId;
      this.isVideoModalOpen = true;
    },

    closeVideoModal() {
      this.isVideoModalOpen = false;
      this.videoId = "";
    },
    
    onItemSelected() {
      if (this.selected === "1") {
        this.type = 1
      } else if (this.selected === "2") {
        this.type = 2
      } else if (this.selected === "3") {
        this.type = 3
      }
    console.log(this.selected)
    console.log(this.type)
    this.getUserNFTs(this.type)
    },

    changeNicknameBtn (){
      this.changeNickname = true;
    },

    copyAddress: async function(){
      let a = this.address;
      // this.$store.dispatch('getAccount');
      // a = this.$store.state.address;
      console.log(a);

      try {
        console.log('a');
      } catch (error) {
        console.error(error);
      } 

      console.log('dd', a, '와:', a.textContent , '끝')
      window.navigator.clipboard.writeText(a).then(() => {
        // 복사가 완료되면 호출된다.
        alert("메타마스크 주소를 복사했습니다!");
      });
    },

    clickOwnedNFT (){
      this.btnOwnedNFT = true;
      this.btnNFTLog = false;
      this.btnAttractedNFT = false;
      this.filterTapX = 0;
      this.seto = 1
      console.log('보유한 NFT')
    },
    clickNFTLog (){
      this.btnOwnedNFT = false;
      this.btnNFTLog = true;
      this.btnAttractedNFT = false;
      this.filterTapX = 104;
      this.seto = 2
      console.log('NFT 거래 기록')
    },
    clickAttractedNFT (){
      this.getuserLikeDropsNFTs()
      this.getuserLikeMarketNFTs()
      this.btnOwnedNFT = false;
      this.btnNFTLog = false;
      this.btnAttractedNFT = true;
      this.filterTapX = 216;
      this.seto = 3
      console.log('관심 있는 NFT')
    },
    handleBtnDown(e){
      if (e.keyCode===9){
        e.stopPropagation();
      }
    },

    async getUserDetail(){
      try{
        const result = await this.$store.dispatch('userDetail')

        console.log("result : " + result)
        this.role = this.$store.state.role;
      }
      catch (err){
        console.log(err)
      }
      // finally{

      // }
    },

    async getUserNFTs(type){
      await this.$store.dispatch('userNFTs', type)
      this.userNFTs = this.$store.userNFTs
    },
    async charge() {
      console.log("1111");
      // const web3 = new Web3(new Web3.providers.HttpProvider('https://fanftasy.kro.kr/network'));
      // const web3 = new Web3('https://fanftasy.kro.kr/network');
      const account = VueCookies.get('Account');
      // const accounts = await web3.eth.getAccounts();
      // console.log(accounts);
      console.log(account);
      // console.log(await web3.eth.sign('hello', account));

      // console.log(web3.providers);
      // console.log(web3.givenProvider);

      // 메타마스크가 실행 되면서 서명 요청 
      // const method = 'personal_sign';
      // const params = ['test', account];
      // // await Web3.providers.HttpProvider('https://fanftasy.kro.kr/network').send(
      // await window.ethereum.request(
      //   {
      //     method,
      //     params,
      //     account,
      //   }
      // ).then((res) => {
      //   console.log(res);
      // })
      // .catch((err) => {
      //   console.log(err);
      // })

        console.log(window.ethereum)
        
        ////////////////
        const web3 = new Web3(window.ethereum);
        const bankContractAddress = '0xbC7eeBAbbAd2E7427C7E3cF7B3B073ed51a91390';
        const bankContract = new web3.eth.Contract(BankABI, bankContractAddress);
        const  amount = web3.utils.toWei("100", "ether"); // 1 ETH를 wei 단위로 변환
        // console.log(bankContract);


        /////////////////////////////////////////////
        const saleFactoryContractAddress = '0x0509b43FF9CcAC684ef00bc82020208b6F86d156';
        const saleFactoryContract = new web3.eth.Contract(SaleFactoryABI, saleFactoryContractAddress);
        console.log(await saleFactoryContract.methods);

        // 앞이 nft_id 이고 amount가 price -> 단위는 wei 이기에 단위 변환 만들기
        // const ans = (await saleFactoryContract.methods.createSale(5, amount).send({ from: account }));
        // (await saleFactoryContract.methods.createSale(5, amount).send({ from: account })
        // .on("transactionHash", function(hash) {
        //     console.log("Transaction hash: " + hash);
        //   })
        //   .on("receipt", function(receipt) { // .then과 비슷함
        //     console.log("Create Sale Receipt : ");
        //     console.log(receipt);
        //   })
        //   .on("error", function(error) {
        //       console.error(error);
        //   }));
        //   console.log(ans);

        // 판매 컨트랙트의 주소
        // var SaleContractAddress = null;
        
        // // 판매 컨트랙트의 주소를 가져오기 위한 판매 올린 로그 불러오기
        // saleFactoryContract.getPastEvents('NewSale', {
        //   filter: { itemId : [11] },
        //   fromBlock: 0,
        //   toBlock: 'latest',
        // },async function(err, events){
        //   console.log(err);
        //   console.log(events);
        //   this.SaleContractAddress = events[events.length - 1].returnValues._saleContract;
        //   console.log(this.SaleContractAddress);
        //   
          
          
          
          
          
        //   // ///////////////////////////////////////////////////////////////////////////////////
        //   //  판매 중인 nft 구매 하려면 필요한 컨트랙트
        //   let SaleContract = new web3.eth.Contract(SaleABI, this.SaleContractAddress);
        //   console.log(SaleContract.methods);
        //   let price = null;

        //   // 판매 가격 받아오기
        //   SaleContract.methods.getFinalPrice().call((err, res) => {
        //     if(err) {
        //       console.log(err);
        //     }
        //     else {
        //       console.log(res);
        //       price = res;
        //     }
        //   })


        //   //  구매인데 판매 중인 nft의 가격을 value에 입력이 되어야한다.
        //   const ans = SaleContract.methods.purchase().send({ from : account, value : price });
        //   console.log(await ans);




        // });
        // console.log(this.SaleContractAddress);


        // // 모든 판매 정보가 배열로 나오는데, 판매 완료 되었어도 뜬다.
        // saleFactoryContract.methods.allSales().call((err, res) => {
        //   console.log(err);
        //   console.log(res);
        // });
        
        ////////////////////////////////////////////////////////////////////////////////////////////////
        let SaleContract = new web3.eth.Contract(SaleABI, this.SaleContractAddress);
        console.log(SaleContract.methods);
        // const ans = SaleContract.methods.purchase().send({ from : account, value : 1 });
        // console.log(await ans);


        ////////////////////////////////////////////////////////////////////////////////////// 
        const NFTContractAddress = '0xDcC5cdA0Ea01c12eA756601021c0029FC39efD38';
        const NFTContract = new web3.eth.Contract(NFTABI, NFTContractAddress);
        console.log(await NFTContract.methods);

        //////// NFT 생성
        // const ans = (NFTContract.methods.create(account , "1234", saleFactoryContractAddress).send({ from: account }));
        // console.log(await ans);

        /////// 생성한 NFT의 NFT_ID(token_id)를 불러오기
        // NFTContract.methods.current().call((err, res) => {
        //   if(err){
        //     console.log(err);
        //   }
        //   else {
        //     console.log(res);
        //   }
        // })

        // 전체 거래 완료 로그 확인
        // NFTContract.getPastEvents('tradeRecord', {
        //   fromBlock: 0,
        //   toBlock: 'latest',
        // }, function(err, res) {
        //   if(err){
        //     console.log(err);
        //   }
        //   else{
        //     console.log(res);
        //   }
        // })




        // 은행으로부터 인출한 로그들 
        // bankContract.getPastEvents('Withdraw', {
        //   fromBlock: 0,
        //   toBlock: 'latest',
        // }, function(err, events){
        //   if(err){ 
        //     console.log(err);
        //   }
        //   else{
        //     console.log(events);
        //   }
        // });
        
        // 은행에 돈 집어 넣은 로그들
        // bankContract.getPastEvents('Deposit', {
        //   fromBlock: 0,
        //   toBlock: 'latest',
        // }, function(err, events){
        //   if(err){ 
        //     console.log(err);
        //   }
        //   else{
        //     console.log(events);
        //     const line = events.length;
        //     if(line <= 5) {
        //       for(let i = line; i > 0; i--){
        //         let date = new Date((events[i - 1].returnValues.time) * 1000);
        //         console.log(date.toLocaleString());
        //       }
        //     }else{
        //       for(let i = line; i > (line - 5); i--){
        //         let date = new Date((events[i - 1].returnValues.time) * 1000);
        //         console.log(date.toLocaleString());
        //       }
        //     }
        //     // console.log(line);
        //     // console.log(events[line - 1]);
        //     // console.log(events[line - 1].returnValues);
        //     // console.log(events[line - 1].returnValues.time);
        //     // const date = new Date((events[line - 1].returnValues.time) * 1000);
        //     // console.log(date.toLocaleString());
        //   }
        // });
        
        
        // amount 만큼 은행에서 가져오기 amount 가 1 이더만큼인 걸 wei 단위로 변환 시켜둔 것
        bankContract.methods.withdraw(amount).send({ from: account });
        
        // // 은행에 얼마가 남아있는지 확인하는 기능
        bankContract.methods.getBalance().call((err, result) => {
          if (err) {
            console.error(err);
          } 
          else {
            console.log('Contract balance:', result);
          }
        });
        
        
          // bank 컨트랙트에 1이더만큼 넣음 -> abi 메서드 사용
          
          // bankContract.methods.deposit().send({
          //   from: account,
          //   value: amount
          // })
          // .on("transactionHash", function(hash) {
          //   console.log("Bank Deposit Transaction hash: " + hash);
          // })
          // .on("receipt", function(receipt) { // .then과 비슷함
          //   console.log("Bank Receipt");
          //   console.log(receipt);
          //   console.log(receipt.from);
          // })
          // .on("error", function(error) {
          //     console.error(error);
          // });
          
          },
          
          uploadFile() {
      console.log('이미지 바꿉니다')
      this.$store.profileImg = this.$refs.fileInput.files[0];
      this.$store.dispatch('modiUserImg')
    },

    confirmEditProfile(){
      this.$store.dispatch('modiUserInfo')
      // this.$router.go(-1)
    },
    
    showModalImg(nft){
      const NFTId = nft.nftId
      console.log(NFTId)
      this.$store.dispatch('resellDetailNFTs', NFTId)
      this.nftInfo = this.$store.state.resellDetailNFTs

      console.log('nft: ',nft)
      const fileCid = JSON.stringify(nft.nftSource.fileCID).replace('"','').replace('"','')
      Swal.fire({
        title: '소유 NFT 정보',
        confirmButtonText: '판매',
        showCancelButton:true,
        cancelButtonText: '취소',
        // buttonsStyling: false,
        html:`
          <div style="display:flex; ">
            <img src="${fileCid}" style="height:500px; width:auto"/>
            <div style="margin-left:10px; display:flex; flex-direction:column; text-align:start;">
              <div>
                <label for="title" style="font-weight:bold;">NFT 이름</label>
                <div id="title">${nft.nftSource.title}</div>
              </div>
              <div>
                <label for="nickname" style="font-weight:bold;">아티스트</label>
                <div id="artist-nickname">by. ${nft.nftSource.regArtist.nickname}</div>
                </div>
                <div>
                <label for="edition-num" style="font-weight:bold;">에디션 번호</label>
                <div id="edition-num">#. ${nft.editionNum}</div>
              </div>
            </div>
          </div>
        `,
        customClass: {
          confirmButton: 'btn-right',
          cancelButton: 'btn-left'
        },
      }).then((result)=>{
        if (result.isConfirmed){
          console.log('confirm')
          this.$router.push({name:'UserNFTView', params:{nftId:nft.nftId}})
        } else if (result.isDenied){
          console.log('Deny')
        }
      })
    },
    showModalVideo(nft){
      const NFTId = nft.nftId
      console.log(NFTId)
      this.$store.dispatch('resellDetailNFTs', NFTId)
      this.nftInfo = this.$store.state.resellDetailNFTs
      console.log('비디오nft : ', nft)
      const fileCid = JSON.stringify(nft.nftSource.fileCID).replace('"','').replace('"','')
      Swal.fire({
        title: '소유 NFT 정보',
        confirmButtonText: '판매',
        showCancelButton:true,
        cancelButtonText: '취소',
        html:`
        <div style="display:flex; ">
          <div>
            <video src="${fileCid}" alt="비디오" autoplay muted style="height:500px; width:auto;"/>

            </div>
          <div style="margin-left:10px; display:flex; flex-direction:column; text-align:start; width:100%;">
            <div>
              <label for="title" style="font-weight:bold; ">NFT 이름</label>
              <div id="title" style="text-align:right; margin-bottom:10px;">${nft.nftSource.title}</div>
              </div>
              <hr>
            <div>
              <label for="nickname" style="font-weight:bold;">아티스트</label>
              <div id="artist-nickname" style="text-align:right; margin-bottom:10px;">by. ${nft.nftSource.regArtist.nickname}</div>
            </div>
            <hr>
            <div>
              <label for="edition-num" style="font-weight:bold;">에디션 번호</label>
              <div id="edition-num" style="text-align:right; margin-bottom:10px;">#. ${nft.editionNum}</div>
            </div>
            <hr>
          </div>
        </div>
        `,
      }).then((result)=>{
        if (result.isConfirmed){
          console.log('confirm')
          this.$router.push({name:'UserNFTView', params:{nftId:nft.nftId}})
        } else if (result.isDenied){
          console.log('Deny')
        }
      })
    },
    showModalAudio(nft){
      const NFTId = nft.nftId
      console.log(NFTId)
      this.$store.dispatch('resellDetailNFTs', NFTId)
      this.nftInfo = this.$store.state.resellDetailNFTs
      const fileCid = JSON.stringify(nft.nftSource.fileCID).replace('"','').replace('"','')
      Swal.fire({
        title: '소유 NFT 정보',
        confirmButtonText: '판매',
        showCancelButton:true,
        cancelButtonText: '취소',
        html:`
        <div style="display:flex; ">
          <iframe src="${fileCid}" alt="오디오" class="nft-img-owned" style="height:500px; width:auto;"></iframe>
            <div style="margin-left:10px; display:flex; flex-direction:column; text-align:start;">
              <div>
                <label for="title" style="font-weight:bold;">NFT 이름</label>
                <div id="title">${nft.nftSource.title}</div>
              </div>
              <div>
                <label for="nickname" style="font-weight:bold;">아티스트</label>
                <div id="artist-nickname">by. ${nft.nftSource.regArtist.nickname}</div>
              </div>
              <div>
                <label for="edition-num" style="font-weight:bold;">에디션 번호</label>
                <div id="edition-num">#. ${nft.editionNum}</div>
              </div>
            </div>
          </div>
        `,
      }).then((result)=>{
        if (result.isConfirmed){
          console.log('confirm')
          this.$router.push({name:'UserNFTView', params:{nftId:nft.nftId}})
        } else if (result.isDenied){
          console.log('Deny')
        }
      })

    },

    showModal(nft){
      console.log(nft)
      console.log('되냐?',nft.owner)
      console.log(JSON.stringify(nft))
      const c = nft.nftSource.fileType.toString()
      console.log('c:',c, c.type, c==='image', c==="image")
      const a = JSON.stringify(nft.nftSource.fileCID)
      const b = a.replace('"','').replace('"','')
      Swal.fire({
        title: '모달 타이틀',
        confirmButtonText: '판매',
        showCancelButton:true,
        cancelButtonText: '취소',
        buttonsStyling: false,
        html:`
        <div>
          div가 됨?  
        </div>
        <div v-if="${c}=='video'">
          비디오
          <video src="${b}" alt="비디오" autoplay muted style="height:400px"/>
          </div>
          <div v-else-if="${c}==='image'" >
            이미지
          <img src="${b}" style="height:400px"/>
          </div>
          <div v-else-if="${c}==='audio'">
            오디오
          <audio controls :src="${b}" alt="오디오"></audio>
          </div>  
          <div>
            ${c}
            시발 이게 왜 됨?
            </div>
            ${b}
            이거임
        ${a}
        이미지 태그
        <div>
          </div>
          
          `,
          customClass: {
          confirmButton: 'btn-right',
          cancelButton: 'btn-left'
        },
      }).then((result)=>{
        if (result.isConfirmed){
          console.log('confirm')
          this.$router.push({name:'UserNFTView', params:{nftId:17}})
        } else if (result.isDenied){
          console.log('Deny')
        }
      })
    },
  
  },
  watch() {
    
  },
  mounted() {
    if (this.profileImage === null) {
        this.profileImage = 'https://fanftasy.s3.ap-northeast-2.amazonaws.com/profileImg/08157c4f-6c97-4678-8f8a-1534d96aa21a.png'
        console.log('this.profileImage :', this.profileImage)
      }

    // this.nImg =new Image;
    // this.nImg.src=this.profileImage;
    // this.profileImgWidth = this.nImg.width;
    // this.profileImgHeight = this.nImg.height;
    // console.log('mounted 후 nImg 정보', this.nImg, this.nImg.width)
    
  },
  computed: {
    // profileLogo(){
    //   const imgWidth = this.nImg.width;
    //   const imgHeight = this.nImg.height;
    //   console.log(this.profileImgWidth, this.profileImgHeight)

    //   console.log('profileLogo() 프로필 이미지 사이즈 :', imgWidth, 'x', imgHeight)
    //   if (imgWidth <= 154 && imgHeight<=154) {
    //     return 'small-profile-logo';
    //   } else if (imgWidth>154 && imgHeight<=154) {
    //     return 'width-long-profile-logo';
    //   } else if (imgWidth<=154 && imgHeight>154){
    //     return 'height-long-profile-logo'
    //   } else {
    //     return 'default-profile-logo'
    //   }
    // }
  },
}
</script>

<style>
.mypage-entire{
  padding-top: 90px;
  display: flex;
  justify-content: center;
}

.circle1{
  width: 184px;

  height: 184px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 0px 5px 5px gray; 
  margin-left: 20px;
  margin-bottom: 20px;
}

.circle2{
  width: 169px;
  height: 169px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgb(218, 210, 233)
}

.circle3{
  width: 154px;
  height: 154px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: white;
  overflow: hidden;
}

.profile-logo{
  width: 154px;
  height: 154px;
  /* width:50%; */
  /* height: 50%; */
  object-fit: fill;
}


.profile-pencil-button{
  font-size: 30px;
  color: black;
}
.profile-pencil-button:hover{
  color: #9B7CF8;
}

.address-inline{
  display: flex;
  align-items: center;
  border-color: #9B7CF8;
  border-style: solid;
  border-radius: 15px;
  width: 500px;
  height: 40px;
  margin-bottom: 10px;
}

.charge-button{
  display: flex;
  right: 10px;
  width: 204px;
  height: 70px;
  font-size: 36px;
  font-weight: bold;
  justify-content: center;
  align-items: center;
  border-radius: 15px;
  color: white;
  background-color: RGB(106, 63, 193);
  margin-bottom: 10px;
  border: solid #cdcdcd 1px;
  box-shadow: 1px 1px 5px #cdcdcd inset;
}

#metamask-address{
  width: 440px;
  text-align: left;
}

/* 마이페이지 보유NFT, 거래내역, 관심아이템 탭 버튼 */
.mypage-filter-tab{
  display: flex;
  align-items: center;
  margin: 10px;
  font-size: 1em;
  position: relative;
}
.mypage-filter-tab-part{
  padding: 10px;
  border-radius: 15px;
}
.indicator {
  padding: 10px;
  border-radius: 15px;
  position: absolute;
  bottom: 0%;
  width: 84px;
  height: 40px;
  background-color: #9B7CF8;
  opacity: 0.3;
  transition: transform 0.3s ease-in;
  pointer-events: none;
}


.holding-item{
  border-radius: 15px;
  z-index: 0;
  border-color: #9B7CF8;
}


.artist-circle{
  position: absolute;
  margin-left: 5px;
  margin-top: 5px;
  z-index: 10;
  background-color: #9B7CF8;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 35px;
  height: 35px;
  border-radius: 50%;
}

.nft-info{
  justify-content: center;
  background-color: white;
  font-size: 10px;
  border-bottom-left-radius: 15px;
  margin-top: 118px;
}

.changeImageBtn{
  border: solid 1px black;
  border-radius: 5%;
}

.nft-img-owned{
  border-radius: 15px;
  width: 164px;
  height: 222px;
  margin-top: 10px;
}

.own-nfts-card{
  display:flex; 
  justify-content: center;
  min-height: 353px;
  border-radius: 15px;
}
.own-nfts-card:hover{
  box-shadow: 0px 10px 10px rgba(188, 188, 188, 0.6);
}

.profile-img-upload-btn{
  width: 184px;
  height: 184px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 0px 5px 5px gray; 
}

.input-category {
  width: 204px !important;
  /* float: right; */
  /* right: 0vw; */
  /* position: absolute; */
}

.input {
  width: 204px;
  height: 7vh;
  /* border-radius: 2.2vw; */
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
  /* border: 1vh solid $color-main-75; */
  margin: 0;
  padding-left: 10px;
  background: rgb(240, 240, 240);
  border-bottom: solid 1px;
}

.edit-profile-confirm{
  background-color: rgb(106, 63, 193);
  color: white;
  font-size: 28px;
  width:100px;
  height: 70px;
  border-radius: 15px;
  margin-left: 3px;
  border: solid #cdcdcd 1px;
  box-shadow: 1px 1px 5px #cdcdcd inset;
}

#nickname{
  border: 1px solid rgb(218,210,233);
  padding: 10px;
  border-radius: 15px;
}

.profile-update{
  height: 154px;
  position: relative;
}

.profile-cover{
  position: absolute;
  /* display: flex; */
  justify-content: center;
  align-items: center;
  width: 154px;
  height: 154px;
  border-radius: 50%;
  opacity: 0;
}

.profile-update:hover .profile-cover{
  opacity: 1;
  width: 154px;
  height: 154px;
  border-radius: 50%;
  background-color: rgba(0, 0, 0, 0.4);
}

.button-owned-nft{
  min-width:164px; 
  text-decoration: none; 
  color:black; 
  display:flex; 
  flex-direction: column;
  justify-content: space-between;
}

.btn-right {
  width: 100px;
  height: 50px;
  border-radius: 15px;
  background-color: #9B7CF8;
  margin-left: 5px;
  box-shadow: 0px 5px 5px rgba(188,188,188,0.8);
}
.btn-left {
  width: 100px;
  height: 50px;
  border-radius: 15px;
  margin-right: 5px;
  box-shadow: 0px 5px 5px rgba(188,188,188,0.8);
}

</style>
