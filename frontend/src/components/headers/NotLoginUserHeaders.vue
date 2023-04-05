<template>
  <div class="navbar">
    <span style="display: inline-block">
      <span class="headerlist">
        <a href="/" class="headerlist">
          <img
            :src="require('@/assets/logo.png')"
            alt="로고"
            class="header-logo"
          />
        </a>
      </span>
      <span class="headerlist">
        <!-- {{ currentRouteName }} -->
        

        <router-link to="/drops" v-if="currentRouteName==='MarketListView'" class="header-router font-k header-tab-clicked" style="margin: 0px 30px;">DROPS</router-link>
        <router-link to="/drops" v-else-if="currentRouteName==='MarketDetailView'" class="header-router font-k header-tab-clicked" style="margin: 0px 30px;">DROPS</router-link>
        <router-link to="/drops" v-else class="header-router font-k header-tab" style="margin: 0px 30px;">DROPS</router-link>
        <router-link to="/market" v-if="currentRouteName==='ResellListView'" class="header-router font-k header-tab-clicked" style="margin: 0px 30px;">MARKET</router-link>
        <router-link to="/market" v-else class="header-router font-k header-tab" style="margin: 0px 30px;">MARKET</router-link>
        <!-- <router-link to="/community" class="header-router"
          >커뮤니티</router-link
        > -->
      </span>
    </span>
    <span class="headerlist">
      <!-- <label for="" class="box">
        <input type="text" class="notting search-input" />
        <button
          href=""
          class="notting"
          style="display: flex; justify-content: right; align-self: center"
        >
          <img
            :src="require('@/assets/Search.png')"
            alt=""
            style="margin-top: 7px"
          />
        </button>
      </label> -->
      <span style="margin-right: 10vw; display: flex; vertical-align: center;">
        <!-- <a href="" class="icon-profile">
          <img :src="require('@/assets/wallet.png')" alt="지갑" />
        </a> -->
        <!-- <router-link to="/login" class="icon-profile"> -->
          <!-- <img
            :src="require('@/assets/base_profile.png')"
            alt="기본 프로필 사진"
          /> -->

        <!-- </router-link> -->
        <router-link v-if="IsLOGIN" to="/mypage" class="icon-profile" style="display:flex;">
          <p style="width:30px; height: 30px; border-radius: 50%; overflow: hidden; margin-right: 10px;">
            <img
            class="profile-image"
            :src="this.profileImage"
            alt=""
            style="object-fit: cover;"
            />
          </p>
          <p class="nick">{{ this.nickname }} 님</p>
        </router-link>
        <p v-if="IsLOGIN" @click="logOut()" style="cursor: pointer;">로그아웃</p>
        <p v-else class="header-router" @click="this.isMember()" style="font-size: 17px; cursor: pointer;">로그인</p>
        <!-- <p v-if="isLogin===True" class="header-router" @click="this.isMember()" style="font-size: 17px;">Login</p>
        <router-link v-else to="/mypage" class="icon-profile">
          <img
            :src="require('@/assets/base_profile.png')"
            alt="기본 프로필 사진"
          />
          {{ this.nickname }} 님
        </router-link> -->
        
      </span>
    </span>
  </div>
</template>

<script>
import VueCookies from "vue-cookies"
export default {
  name: "NotLoginUserHeaders",
  data() {
    return {
      nickname: VueCookies.get('nickname'),
      logIn: VueCookies.isKey('AccessToken'),
      profileImage: VueCookies.get('profileImage'),
    }
  },
  mounted() {
    if (this.profileImage === null) {
        this.profileImage = 'https://fanftasy.s3.ap-northeast-2.amazonaws.com/profileImg/8c64c983-1b80-40fb-bcc1-366f3322cbb2.png'
        console.log(this.profileImage)
      }
  },
  watch: {
    nickname: function () {
      console.log(VueCookies.get('nickname'));

      console.log(VueCookies.isKey('AccessToken'));
      this.logIn = VueCookies.isKey('AccessToken');
    },
    profileImage: function () {
      console.log(VueCookies.get('profileImage'));
      
    },
  },
  computed: {
    // 반응형으로 계산된 속성
    IsLOGIN() {
      console.log(this.logIn);
      return this.logIn === true;
    },
    currentRouteName(){
      // console.log(this.$route.name)
      return this.$route.name
    },
    marketClicked(){
      const mc = this.$route.name;
      console.log(mc, 'dfdf')
      if (mc.indexOf('Market')>=0) {
        console.log('네')
        return '네';
      }
      return '아니오';
    },
  },
  methods: {
    
    async isMember() {
      this.$store.state.isMember = false;
      await this.$store.dispatch('LOGIN');

      console.log(this.$store.state.isMember);

      const Member = this.$store.state.isMember;
      console.log(VueCookies.get('profileImage'));
      if(VueCookies.get('profileImage') === null){
        this.profileImage = 'https://fanftasy.s3.ap-northeast-2.amazonaws.com/profileImg/8c64c983-1b80-40fb-bcc1-366f3322cbb2.png';
      }
      else{
        this.profileImage = VueCookies.get('profileImage');
        console.log(this.profileImage);
      }
      
      if(Member === true){
        console.log("회원입니다.");
        this.$router.go(this.$router.currentRoute);
      }
      else if(Member === false) {
        console.log("회원이 아닙니다.");
        this.$router.push('/select');
      }
      else if(Member === null) {
        console.log("무슨 에러인지 모르겠습니다.");
      }
      // setTimeout(function() {
      // }, 500);
      
    },
    logOut() {
      this.$store.commit('LogOut');
      if(!VueCookies.isKey('AccessToken')) {
        this.$router.go(this.$router.currentRoute);
      }
      else {
        console.log("무슨 에러인지 모르겠습니다.");
      }
    },
  }
}
</script>

<style>
.navbar {
  height: 70px;
  width: 100%;
  display: flex;
  align-items: center;
  /* background: white; */
  justify-content: space-between;

  position: fixed;

  background-color: white;
  /* background-color: rgba(255,255,255,0.0); */

  z-index: 3;
  /* border-bottom: solid black; */
  box-shadow: 0 3px 3px rgba(188, 188, 188, 0.5)
  /* opacity: 0.5; */
}
.navbar::after {
  width: 100%;
  height: 100px;
  /* display: flex; */
  position: fixed;
  /* background: none; */
  top: 0;
  left: 0;
  z-index: 3;
  /* opacity: 0.1; */
}

.header-router {
  text-decoration: none !important;
  font-size: 20px;
  vertical-align: center;
  color: black;
}

.header-tab-clicked{
  background: linear-gradient(90deg,#6A3FC1,#5B9BD5);
  -webkit-text-fill-color: transparent;
  background-clip: text;
  background-size: 100% 100%;
  position: relative;
}
.header-tab-clicked:after{
  position: absolute;
  content:  "";
  display: block;
  width: 100%;
  height: 2px;
  background: linear-gradient(90deg,#6A3FC1,#5B9BD5);
  -webkit-text-fill-color: transparent;
}

.header-tab:hover{
  background: linear-gradient(90deg,#6A3FC1,#5B9BD5);
  -webkit-text-fill-color: transparent;
  background-clip: text;
  background-size: 100% 100%;
  transition: 0.5s;
}
.header-tab:not(:hover){
  transition: 0.5s;
  background-size: 0% 0%;
}

.header-tab{
  display: inline-block;
  position: relative;  
}

.header-tab:after{
  position: absolute;
  content:  "";
  display: block;
  width: 0%;
  height: 2px;
  background: linear-gradient(90deg,#6A3FC1,#5B9BD5);
  -webkit-text-fill-color: transparent;
  transition: width 0.3s ease 0s, left 0.3s ease 0s;
}

.header-tab:hover:after{
  width: 100%;
}


.header-logo {
  /* display: flex; */
  /* float: left; */
  vertical-align: center;
  /* width: 35px; */
  height: 35px;
}

.headerlist {
  display: flex;
  float: left;
  font-size: large;
  text-decoration: none;
  margin-left: 2vw;
  vertical-align: center;
}

.box {
  width: 400px;
  height: 40px;
  border-radius: 15px;
  border: 1px solid #bcbcbc;
  float: left;
}

.notting {
  background: none;
  border: none;
}

.search-input {
  width: 350px;
  height: 39px;
  border-radius: 15px;
  display: flex;
  justify-content: left;
  float: left;
  padding-left: 10px;
}

.icon-profile {
  /* display: flex; */
  margin-right: 2vw;
  text-decoration: none;
  color: black;
  /* vertical-align: center; */
}

.profile-image{
  display: flex;
  float: left;
  width: 30px;
  height: 30px;
}

.nick {
  display: flex;
  vertical-align: center;
}
</style>
