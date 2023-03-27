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
        <router-link to="/market" class="header-router" style="margin: 0px 30px;">DROPS</router-link>
        <router-link to="/resell" class="header-router" style="margin: 0px 30px;"
          >MARKET</router-link
        >
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
        <router-link v-if="isLogin===True" to="/mypage" class="icon-profile">
          <img
          class="profile-image"
          :src="require('@/assets/base_profile.png')"
          alt="기본 프로필 사진"
          />
          <p class="nick">{{ this.nickname }} 님</p>
        </router-link>
        <p v-else class="header-router" @click="this.isMember()" style="font-size: 17px;">로그인</p>
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
export default {
  name: "NotLoginUserHeaders",
  data() {
    return {
      nickname: '신선호'
    }
  },
  methods: {
    isMember() {
      if(this.$store.commit('installedMetamask')){ // 메타마스크가 설치되어 있으면
        console.log("설치 되어있음");
      }
    },
  }
}
</script>

<style>
.navbar {
  height: 100px;
  display: flex;
  align-items: center;
  background: white;
  justify-content: space-between;
}

.header-router {
  text-decoration: none !important;
  font-size: 20px;
  vertical-align: center;
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
}

.nick {
  display: flex;
  vertical-align: center;
}
</style>
