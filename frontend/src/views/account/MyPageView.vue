<template>
  <div class="mypage-entire">
    <div>
      <!-- 프로필 이미지, 메타마스크 주소, NFT 충전 버튼 -->
      <div style="display: flex; justify-content: space-between;">
        <div>
          <!-- 프로필 이미지 -->
          <div class="circle1">
            <div class="circle2">
              <div class="circle3">
                <img :src="require('@/assets/EthereumIcon.png')" alt="로고" class="profile-logo">
              </div>
            </div>
          </div>
          
          <div style="margin: 10px; font-weight: bold; font-size: 32px;">
            {{ nickname }} 님
          </div>

          <!-- 메타마스크 주소 -->
          <div class="address-inline">
            <img :src="require('@/assets/metamask_logo.png')" alt="여우" style="width:35px; height: 25px; padding-left: 5px; padding-right: 5px;">
            <div id="metamask-address">{{address}}</div>
            <img :src="require('@/assets/copy.png')" alt="복사" style="width:28px; height: 20px; padding-left: 5px; padding-right: 5px; " @click="copyAddress">
          </div>
        </div>
        
        <div style="margin-top: auto">
          <button class="charge-button">
            FAN 충전
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
      <div style="border-radius: 50%; border-color: blueviolet; margin-left: auto;">
        <div style="margin-left:14px; width:200px; border-radius: 50% !important;">
          <div>
            <v-combobox :items="myNFTmenu"></v-combobox> 
          </div>
        </div>
      </div>
      
      
      
      
      <v-container>
        <v-row style="max-width:1000px">
          <v-col v-for="n in 14" :key="n" :cols="3">
            <v-card outlined tile class="holding-item" style="height:200px; width: 200px;">
              <div class="artist-circle">
                {{ n }}
              </div>
              <div>
                <v-img src="@/assets/minsu.jpg" alt="민수 ">
                  
                  <v-card-text class="nft-info">
                    <div class="font-weight-bold">
                      dfdfdffdsffs
                    </div>
                    <div>
                      전체거래량
                    </div>
                    <div>
                      최저거래가
                    </div>
                  </v-card-text>
                </v-img>
              </div>                                                                                                                                                                                                              
  
            </v-card>
          </v-col>
        </v-row>
      </v-container>

    </div>


    

  </div>
</template>

<script>
import VueCookies from "vue-cookies"

export default {
  name: 'MyPageView',
  data(){
    return {
      nickname: VueCookies.get('nickname'),
      address: null,

      filterTapX: 0,
      btnOwnedNFT: true,
      btnNFTLog: false,
      btnAttractedNFT: false ,

      myNFTmenu : ['최신 순','거래량 많은 순','거래 횟수 많은 순', '이름 순 : A→Z','이름 순 : Z→A'],
    }
  },
  components:{

  },
  async created() {
    await this.$store.dispatch('getAccount');
    this.address = this.$store.state.address;
  },
  methods:{
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
  },
  watch() {

  },
  mounted() {

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
}

.profile-logo{
  width: 100px;
  height: 100px;
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

</style>