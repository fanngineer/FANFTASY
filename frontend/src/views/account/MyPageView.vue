<template>
  <div>
    <LoginUserHeaders/>
    <NotLoginUserHeaders/>

    <!-- 프로필 이미지 -->
    <div class="circle1">
      <div class="circle2">
        <div class="circle3">
          <img :src="require('@/assets/EthereumIcon.png')" alt="로고" class="profileLogo">
        </div>
      </div>
    </div>

    <!-- 메타마스크 주소 -->
    <div class="addressInline">
      <img :src="require('@/assets/metamaskLogo.png')" alt="여우" style="width:35px; height: 25px; padding-left: 5px; padding-right: 5px;">
      <div id="metamaskAddress">{{address}}</div>
      <img :src="require('@/assets/copy.png')" alt="복사" style="width:28px; height: 20px; padding-left: 5px; padding-right: 5px; " @click="copyAddress">
    </div>

    <button class="chargeB">
      NFN 충전
    </button>
    <div>
      보유 NFT | 거래 내역 | 관심 아이템
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
          <v-card outlined tile class="holdingItem" height="200px">
            <div class="artistCircle">
              {{ n }}
            </div>
            <div>
              <v-img src="@/assets/minsu.jpg" alt="민수 ">
                
                <v-card-text class="nftInfo">
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
import LoginUserHeaders from "@/components/headers/LoginUserHeaders.vue"
import NotLoginUserHeaders from "@/components/headers/NotLoginUserHeaders.vue"
export default {
  name: 'MyPageView',
  data(){
    return {

      // address: '0x53fd1C95DCe6f1d00aA1Ad0296899b02efB20686',
      address: '와 배부르다',
      myNFTmenu : ['최신 순','거래량 많은 순','거래 횟수 많은 순', '이름 순 : A→Z','이름 순 : Z→A'],
    }
  },
  components:{
    LoginUserHeaders,
    NotLoginUserHeaders,
  },
  methods:{
    copyAddress: function(){
      const a = document.getElementById('metamaskAddress')

      console.log('dd', a, '와:', a.textContent , '끝')
      window.navigator.clipboard.writeText(a.textContent).then(() => {
        // 복사가 완료되면 호출된다.
        alert("메타마스크 주소를 복사했습니다!");
      });
    },
  },
}
</script>

<style>
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

.profileLogo{
  width: 100px;
  height: 100px;
}

.addressInline{
  display: flex;
  align-items: center;
  border-color: #9B7CF8;
  border-style: solid;
  border-radius: 15px;
  width: 500px;
  height: 40px;
}

.chargeB{
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

#metamaskAddress{
  width: 440px;
  /* justify-content: right; */
  /* justify-items: right; */
  /* justify-self: right; */
  text-align: left;
}


.holdingItem{
  border-radius: 15px;
  z-index: 0;
  border-color: #9B7CF8;
}


.artistCircle{
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

.nftInfo{
  justify-content: center;
  background-color: white;
  font-size: 10px;
  border-bottom-left-radius: 15px;
  margin-top: 118px;
}

</style>