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

    <div>
      보유 NFT
    </div>
    <div>
      최신순
    </div>
    <div>
      <div>
        <div v-for="(i,n) in 10" v-bind:key="i">{{ n }} </div>
      </div>
    </div>

    <AllFooter/>
  </div>
</template>

<script>
import LoginUserHeaders from "@/components/headers/LoginUserHeaders.vue"
import NotLoginUserHeaders from "@/components/headers/NotLoginUserHeaders.vue"
import AllFooter from "@/components/headers/AllFooter.vue"
export default {
  name: 'MyPageView',
  data(){
    return {

      // address: '0x53fd1C95DCe6f1d00aA1Ad0296899b02efB20686',
      address: '와 배부르다',
    }
  },
  components:{
    LoginUserHeaders,
    NotLoginUserHeaders,
    AllFooter,
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


#metamaskAddress{
  width: 440px;
  /* justify-content: right; */
  /* justify-items: right; */
  /* justify-self: right; */
  text-align: left;
}

.logo {
  width: 20px;
  height: 20px;
}
</style>