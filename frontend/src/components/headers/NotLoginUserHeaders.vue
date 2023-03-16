<template>
  <div class="navbar">
    <span class="headerlist">
      <a href="/" class="headerlist">
        <img :src="require('@/assets/logo.png')" alt="로고" class="header-logo">FANFTASY
      </a>
    </span>
    <span class="headerlist">
      <router-link to="/" style="text-decoration: none;">NFT 마켓</router-link> |
      <router-link to="/about" style="text-decoration: none;">커뮤니티</router-link>
    </span>
    <label for="" class="box" >
      <input type="text" class="notting search-input">
      <button href="" class="notting" style="display: flex; justify-content: right; align-self : center;">
        <img :src="require('@/assets/Search.png')" alt="" style="margin-top: 7px;">
      </button>
    </label>
    <span style="margin-left: 10vw">
      <a href="" class="icon-margin">
        <img :src="require('@/assets/wallet.png')" alt="지갑">
      </a>
      <router-link to="/mypageview">
        <img
          :src="require('@/assets/base_profile.png')"
          alt="기본 프로필 사진"  
        />
      </router-link>
    </span>
  </div>
</template>

<script>
export default {
  name: 'NotLoginUserHeaders',
}
</script>

<style>
.navbar {
  height: 100px;
  display: flex;
  align-items: center;
  background: white;
}

.header-logo {
  /* display: flex; */
  float: left;
  vertical-align: center;
  width: 35px;
  height: 35px;
}

.headerlist {
  font-size: large;
  text-decoration: none;
  /* margin-left: 2vw; */
}

.box {
  width:400px;
  height:40px;
  border-radius: 15px;
  border: 1px solid #BCBCBC;
}

.notting{
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
}

.icon-margin {
  margin-right: 2vw;
}
</style>