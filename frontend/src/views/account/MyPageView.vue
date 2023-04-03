<template>
  <div class="mypage-entire">
    <!-- <LoginUserHeaders/> -->
    <!-- <NotLoginUserHeaders v-if="i == 1"/> -->

    <!-- 프로필 이미지 -->
    <div class="circle1">
      <div class="circle2">
        <div class="circle3">
          <img :src="require('@/assets/EthereumIcon.png')" alt="로고" class="profile-logo">
        </div>
      </div>
    </div>

    <!-- 메타마스크 주소 -->
    <div class="address-inline">
      <img :src="require('@/assets/metamask_logo.png')" alt="여우" style="width:35px; height: 25px; padding-left: 5px; padding-right: 5px;">
      <div id="metamask-address">{{address}}</div>
      <img :src="require('@/assets/copy.png')" alt="복사" style="width:28px; height: 20px; padding-left: 5px; padding-right: 5px; " @click="copyAddress">
    </div>

    <button class="charge-button">
      NFN 충전
    </button>
    <div>
      보유 NFT | 거래 내역 | 관심 아이템s
    </div>

    <hr>

    <!-- 메뉴 콤보박스 -->
    <div style="border-radius: 50%; border-color: blueviolet; margin-left: 1300px; display: flex;">
      <div style="margin-left:140px; width:200px; border-radius: 50% !important;">
        <div>
          <v-combobox :items="myNFTmenu"></v-combobox> 
  
        </div>
      </div>

    </div>

  
    
    
    <v-container>
      <v-row>
        <v-col v-for="n in 14" :key="n" :cols="2" class="mx-2 ">
          <v-card outlined tile class="holding-item" height="200px">
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
</template>

<script>
// import LoginUserHeaders from "@/components/headers/LoginUserHeaders.vue"
// import NotLoginUserHeaders from "@/components/headers/NotLoginUserHeaders.vue"
// import Web3 from "web3"

export default {
  name: 'MyPageView',
  data(){
    return {
      address: null,
      myNFTmenu : ['최신 순','거래량 많은 순','거래 횟수 많은 순', '이름 순 : A→Z','이름 순 : Z→A'],
    }
  },
  components:{
    // LoginUserHeaders,
    // NotLoginUserHeaders,
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


  },
  watch() {

  },
  mounted() {

  },
}
</script>

<style>
.mypage-entire{
  padding-top: 120px;
}

.circle1{
  width: 184px;
  height: 184px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 0px 5px 5px gray; 
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
}

#metamask-address{
  width: 440px;
  /* justify-content: right; */
  /* justify-items: right; */
  /* justify-self: right; */
  text-align: left;
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