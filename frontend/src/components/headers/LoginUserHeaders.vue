<template>
  <div class="navbar">
    <span style="display: inline-block">
      <span class="headerlist-login">
        <a href="/" class="headerlist">
          <img
            :src="require('@/assets/logo.png')"
            alt="로고"
            class="header-logo-login"
          />FANFTASY
        </a>
      </span>
      <span class="headerlist-login">
        <router-link to="/market" style="text-decoration: none">NFT 마켓</router-link>
        |
        <router-link to="/community" style="text-decoration: none"
          >커뮤니티</router-link
        >
      </span>
    </span>
    <span style="display: inline-block">
      <label for="" class="box">
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
      </label>
      <span style="margin-left: 2vw">
        <a href="" class="icon-margin">
          <img :src="require('@/assets/chatting.png')" alt="채팅" />
        </a>
        <a href="" class="icon-margin">
          <img :src="require('@/assets/bell.png')" alt="알림" />
        </a>
        <a href="" class="icon-margin">
          <img :src="require('@/assets/wallet.png')" alt="지갑" />
        </a>
        <router-link to="/mypage" class="icon-margin">
          <img
            :src="require('@/assets/base_profile.png')"
            alt="기본 프로필 사진"
          />
        </router-link>
      </span>
    </span>
  </div>
</template>

<script>
export default {
  name: "LoginUserHeaders",
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

.header-logo-login {
  width: 35px;
  height: 35px;
  /* display: flex; */
  vertical-align: center;
}

.headerlist-login {
  font-size: large;
  text-decoration: none;
  margin-left: 2vw;
}

.box {
  width: 400px;
  height: 40px;
  border-radius: 15px;
  border: 1px solid;
  float: left;

  /* margin-left: 30vw; */
}
</style>
