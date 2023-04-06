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
        <input type="text" class="name t" placeholder="이름(실명)" v-model="this.name"/>
        <input type="text" class="nickname t" placeholder="닉네임(2~8글자)" v-model="this.nickname"/>
        <input type="text" class="email t" placeholder="이메일(example@google.com)" v-model="this.email" />
        <input type="text" class="phone t" placeholder="전화번호(01012345678)" v-model="this.phone" />
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
import VueCookies from 'vue-cookies';
import Swal from 'sweetalert2'
import router from "@/router"

export default {
  name: "SignUpView",
  components: {

  },
  data () {

    return {
      address: null,
      name: null,
      email: null,
      nickname: null,
      phone: null,
      role: null,
      company: null,
      mem: false,
    }
  },
  created () {
    this.isLogin()

    if(this.$store.state.isFan){
      this.role = 'fan';
    }
    else{
      this.role = 'wait';
    }
    
  },
  methods: {
    isLogin(){
      if (VueCookies.get("nickname") != null){
        this.mem = true
        Swal.fire({
          title: "회원가입 실패",
          text: "이미 등록된 회원입니다.",
          icon: "warning" //"info,success,warning,error" 중 택1
        })
        router.go(-1)
      } else {
        this.mem = false
      }
    },

    async connectWallet(){ // 회원가입
      
      this.address = await this.$store.dispatch('getAccount');
      console.log(this.address);
      
      await this.$store.dispatch('changeNetWork');

      if(this.company === null){
        this.company = 'once';
      }
      this.$store.state.email = this.email;
      this.$store.state.address = this.address;
      this.$store.state.phone = this.phone;
      this.$store.state.nickname = this.nickname;
      this.$store.state.name = this.name;
      this.$store.state.role = this.role;
      this.$store.state.company = this.company;
      await this.$store.dispatch('signup');
      
      // await this.$store.dispatch('modiUserInfo');

      // await this.$store.dispatch('userDetail');
      // console.log(this.$store.state.nickname);
      
      // await this.$store.dispatch('LOGIN');

      // await this.$store.dispatch('modiUserInfo');
      
      console.log(this.$store.state.success);

      if(this.$store.state.success) {
        // await this.$store.dispatch('LOGIN');
        Swal.fire({
          title: "회원가입 성공",
          text: "회원가입 되었습니다. 로그인하여주십시오.",
          icon: "success" //"info,success,warning,error" 중 택1
        })
        // alert("회원가입 되었습니다. 로그인하여주십시오.");
        this.$router.push('/');
      }
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
  margin-top: 120px;
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