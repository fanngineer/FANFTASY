<template>
  <div class="navbar">
    <span>
      <a href="/" class="headerlist">
        <img src="@\assets\logo.png" alt="로고" class="logo">FANFTASY
      </a>
    </span>
    <span class="headerlist">
      <router-link to="/" style="text-decoration: none;">NFT 마켓</router-link> |
      <router-link to="/about" style="text-decoration: none;">커뮤니티</router-link>
    </span>
    <label for="" class="box" >
      <input type="text" class="notting search-input">
      <button href="" class="notting" style="display: flex; justify-content: right; align-self : center;">
        <img src="@/assets/Search.png" alt="" style="margin-top: 7px;">
      </button>
    </label>
    <span style="margin-left: 10vw">
      <a href="" class="icon-margin">
        <img src="@\assets\wallet.png" alt="지갑">
      </a>
      <a href="" class="icon-margin">
        <img src="@\assets\base_profile.png" alt="기본 프로필 사진"> 
      </a>
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
  height: 70px;
  display: flex;
  align-items: center;
}

.logo {
  width: 35px;
  height: 35px;
}

.headerlist {
  font-size: large;
  text-decoration: none;
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