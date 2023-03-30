<template>
  <!-- <div class="LogoTitle">
    <div class="title">회원 가입</div>
    <v-img v-if="this.$store.state.isFan" src="@/assets/fan-club.png" class="logo"></v-img>
    <v-img v-else src="@/assets/Micro.png" class="logo"></v-img>
  </div> -->
  <div class="signup-container">
    <div class="signup">
      <div class="info">
        <div class="signup-title">
          회원가입
        </div>
        <input type="text" class="nickname t" placeholder="닉네임" v-model="this.nickname"/>
        <!-- <input type="text" class="RealName" placeholder="실명" v-bind=""> -->
        <input type="text" class="email t" placeholder="이메일" v-model="this.email" />
        <input type="text" class="phone t" placeholder="전화번호" v-model="this.phone" />
        <input type="text" class="company t" v-if="!this.$store.state.isFan" placeholder="소속사" v-model="this.company" />
        <button class="connective" @click="connectWallet">
              <img :src="require('@/assets/metamask_fox.png')" alt="foxFace" style="width:40px; height: 40px;">
              <!-- <v-img src="@/assets/metamask_fox.png" alt="foxFace" style="width:40px; height: 40px;"></v-img> -->
              <p style="margin-left: 5px; margin-top: 5px; font-weight: bold;">
                메타마스크 연결하기
              </p>
        </button>
      </div>
      <div style="display: flex; align-items: center; margin-right: 20px;">
        <!-- <v-img src="@/assets/HERO.png" alt="" style="height: 70vh; width: 30vw;"></v-img> -->
        <video style="display:block; width:300px; max-width: 300%; border-radius: 15px;" autoplay loop muted>
          <source :src="require('@/assets/test.mp4')" type="video/mp4">
        </video>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "SignUpView",
  components: {

  },
  data () {

    return {
      address: null,
      email: null,
      nickname: null,
      phone: null,
      role: null,
      company: null,
    }
  },
  created () {
    if(this.$store.state.isFan){
      this.role = 'fan';
    }
    else{
      this.role = 'artist';
    }
      
  },
  methods: {
    async connectWallet(){ // 회원가입
      this.address = await this.$store.dispatch('getAccount');
      console.log(this.address);
      const payload = {
        address: this.address,
        email : this.email,
        nickname: this.nickname,
        phone: this.phone,
        role: this.role,
        company: this.company,
      }
      console.log(payload);
      this.$store.dispatch('LOGIN');
      // await this.$store.dispatch('signup', payload);
    }
  },
  computed () {

  }
}
</script>

<style>

.LogoTitle {
  display: flex;
  align-items: center;
  position: absolute;
  top: 20vh;
  left: 5vw;
}

.title {
  display: flex;
  /* height: 50px;
  width: 80px; */
  font-size: 40px;
  color: purple;
}

.logo {
  display: flex;
  /* vertical-align: top; */
  height: 30px;
  width: 60px;
  margin-right: 10px;
}

.signup-container{
  display: flex;
  justify-content: center;
  align-items: center;
  
}

.signup {
  display: flex;
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.5);
  /* min-height: 500px; */
  border-radius: 25px;
}

.info {
  display: flex;
  flex-direction: column;
  justify-content: center;

  background:rgba(#13232f,.9);
  padding: 40px;
  box-shadow:0 4px 10px 4px rgba(#13232f,.3);
}

.signup-title{
  text-align: center;
  font-family:'KCC-Ganpan';
  font-size: 30px;
  font-weight: 200;
  margin-bottom: 20px;
  background: linear-gradient(to right, #6A3FC1, #5B9BD5); 
  background-clip: text;
  -webkit-background-clip: text;
  color: transparent;
}

.connective {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 240px;
  height: 60px;
  background-color: #6A3FC1;
  border-radius: 15px;
  color: white;
}

.t {
  margin-bottom: 10px;
  background-color: rgba(blue, 0.3);
  border: solid 1px black;
  height: 40px;
  padding-top: 5px;
  padding-left: 10px;
  border-radius: 15px;
}

</style>