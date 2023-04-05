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
        
        <div style="margin-top: auto;">
          <router-link :to="{name: 'MarketAddView'}" style="text-decoration: none;">
            <button v-if="this.role === 'artist'" class="charge-button" @click="goToAdd()" >
              NFT 생성
            </button>
          </router-link>
          <button class="charge-button" @click="charge()">
            NFN 충전
          </button>
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
      <div style="display: flex; justify-content: end;">
        <div style="margin-left:14px; width:200px; border-radius: 50% !important;">
          <div>
            <v-combobox :items="myNFTmenu"></v-combobox> 
          </div>
        </div>
      </div>
      
      
      <v-container style="width:800px">
        <v-row>
          <v-col v-for="(nft,index) in userNFTs" :key="index" cols="12" md="3">
            <div class="own-nfts-card">
              <router-link :to="{name:'MarketDetailView', params:{id: nft.nftSource.nftSourceId}}" style="min-width:164px; text-decoration: none; color:black; display:flex; flex-direction: column; justify-content: space-between;">
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
                    <span>
                      #{{ nft.editionNum }}
                    </span>
                    <span style="font-size:10px">
                      edition
                    </span>
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
import BankABI from "../../../path/to/BankABI.json";
import Web3 from "web3"
import  VueCookies  from 'vue-cookies';
import { Buffer } from 'buffer';
// import NFTCard from "@/components/market/NFTCard.vue"

export default {
  name: 'MyPageView',
  components: {
    // NFTCard,
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

      myNFTmenu : ['최신 순','거래량 많은 순','거래 횟수 많은 순', '이름 순 : A→Z','이름 순 : Z→A'],
      profileImage: VueCookies.get('profileImage'),

      isHovered: false,
      userNFTs: [],
      userData : [],
      file: null,
      changeNickname: false,
    }
  },
  async created() {
    await this.$store.dispatch('getAccount');
    this.address = this.$store.state.address;

    this.getUserNFTs();
    this.getUserDetail();
  },
  methods:{
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
      console.log('보유한 NFT')
    },
    clickNFTLog (){
      this.btnOwnedNFT = false;
      this.btnNFTLog = true;
      this.btnAttractedNFT = false;
      this.filterTapX = 104;
      console.log('NFT 거래 기록')
    },
    clickAttractedNFT (){
      this.btnOwnedNFT = false;
      this.btnNFTLog = false;
      this.btnAttractedNFT = true;
      this.filterTapX = 216;
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

    async charge() {
      console.log("1111");
      
      const web3 = new Web3('https://fanftasy.kro.kr/network');
      
      const account = VueCookies.get('Account');
      const accounts = await web3.eth.getAccounts();
      console.log(accounts);

      const tempMessage = "testSigning";
      const msg = `0x${Buffer.from(tempMessage, 'utf8').toString('hex')}`;
      console.log(msg);
      // await web3.eth.request({
      //   method: 'personal_sign',
      //   params: [msg, account, 'example sign'],
      // }).then((res) => {
      //   console.log(res);
      // });
      // web3.eth.personal.sign("msg", account, "example sign");
      
      const contractAddress = '0xc8AD4DF30fc1a99a716B9Fc9F3752E79eda47180';
      // const contractAddress = '0xcC3E0342D6E62E84bA6028220fEe64a94875b398';
      const bankContract = new web3.eth.Contract(BankABI, contractAddress);

      bankContract.getPastEvents('Withdraw', {
        fromBlock: 0,
        toBlock: 'latest',
      }, function(err, events){
        if(err){ 
          console.log(err);
        }
        else{
          console.log(events);
        }
      });

      bankContract.getPastEvents('Deposit', {
        fromBlock: 0,
        toBlock: 'latest',
      }, function(err, events){
        if(err){ 
          console.log(err);
        }
        else{
          console.log(events);
        }
      });
      
      // const amount = web3.utils.toWei("1", "ether");
      console.log(account);
      
        // amount 만큼 컨트랙트에서 가져오기
        // bankContract.methods.withdraw(amount).send({ from: account });
        
        // deposit 실행된 로그
        // const depositEvent = bankContract.events.Deposit();
        // depositEvent.on("data", event => {
          //   console.log("Deposit event:", event.returnValues);
          // }).on("error", console.error);
        
        // 컨트랙트에 얼마가 남아있는지 확인하는 기능
        bankContract.methods.getBalance().call((err, result) => {
          if (err) {
            console.error(err);
          } 
          else {
            console.log('Contract balance:', result);
          }
        });
        
        // bank 컨트랙트에 1이더만큼 넣음 -> abi 메서드 사용
        // const depositAmount = web3.utils.toWei("1", "ether"); // 1 ETH를 wei 단위로 변환
        // await window.ethereum.request('eth_requestAccounts').then((accounts) => {
        //   const accout = accounts[0];
        //   console.log(accout);
        //   bankContract.methods.deposit().send({
        //     from: accout,
        //     value: web3.utils.toWei("1", "ether")
        //   })
        //   .then((res) => {
        //     console.log(res);
        //   })
        //   .on("transactionHash", (hash) => {
        //     console.log(`Transaction hash: ${hash}`);
        //   })
        //   .on("receipt", (receipt) => {
        //     console.log(`Transaction receipt: ${JSON.stringify(receipt, null, 2)}`);
        //   })
        //   .on("error", (error) => {
        //     console.error(error);
        //   });
        // });
        

        web3.eth.accounts.wallet.add(account);

        // bankContract.methods.deposit().send({
        //   from: account,
        //   value: web3.utils.toWei("1", "ether")
        // })
        // .on("transactionHash", function(hash) {
        //   console.log("Transaction hash: " + hash);
        // })
        // .on("receipt", function(receipt) {
        //   console.log(receipt);
        // })
        // .on("error", function(error) {
        //   console.error(error);
        // });
        
        
        // bank 컨트랙트에 1이더만큼 넣음
        // console.log(account);
        // const value = web3.utils.toWei("1", "ether");
        // const txHash = await web3.eth.sendTransaction({
        //   from: account,
        //   to: '0xc8AD4DF30fc1a99a716B9Fc9F3752E79eda47180',
        //   value: value
        // });
        // console.log(txHash);
    },

    async getUserNFTs(){
      await this.$store.dispatch('userNFTs')
      this.userNFTs = this.$store.userNFTs
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
</style>
