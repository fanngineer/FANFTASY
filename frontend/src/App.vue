<template >   
  <v-app class="page">
    <NotLoginUserHeaders />
    <!-- <LoginUserHeaders v-else /> -->
    <v-main >
      <router-view />
      <AllFooter class="footer-space footer_section"/>
    </v-main>
    <div id="gotoup" style="cursor:pointer; display: none;" onclick="window.scrollTo({top:0, behavior: 'smooth'});">
      <span class="goto-up">
        <v-img src="@/assets/arrow-up-circle.png" alt="" style="width: 50px; margin-left: 4px;"></v-img>
      </span>
    </div>
  </v-app>
</template>

<script>
import AllFooter from "@/components/headers/AllFooter.vue"
// import LoginUserHeaders from "@/components/headers/LoginUserHeaders.vue"
import NotLoginUserHeaders from "@/components/headers/NotLoginUserHeaders.vue"
import VueCookies from "vue-cookies"

window.onload = function(){
  setTimeout(function(){
    scrollTo(0,0);
  },100);
}


window.addEventListener('scroll', () => {
  const div1 = document.getElementById('gotoup');
  const scrollPosition = window.scrollY;

  if (scrollPosition > 500) {
    div1.style.display = 'block';
  } else {
    div1.style.display = 'none';
  }
});

// window.scroll(function() {
//   if (this.scrollPosition > 500) {
//     document.querySelector('#gotoup').display = 'block';
//   } else {
//     document.querySelector('#gotoup').display = 'none';
//   }
// });
  

export default {
  name: 'App',
  components:{
    AllFooter,
    // LoginUserHeaders,
    NotLoginUserHeaders
  },
  data: () => ({
    
  }),
  created() {
    // console.log(this.IsLOGIN());
    console.log(this.$store.getters.isLogin);
    console.log(VueCookies.isKey('AccessToken'));
    console.log(VueCookies.get('nickname'));
    console.log(VueCookies.isKey('AccessToken'));
  },
  watch() {
    
  },
  computed: {
    // 반응형으로 계산된 속성
    IsLOGIN() {
      return VueCookies.isKey('AccessToken');
    }
  },
}
</script>

<style>
*{cursor: none;}
.cursor{
  position: absolute;
  z-index: 1000;
  border: 1px solid #000;
  width: 2rem;
  height: 2rem;
  pointer-events: none;
  transform: translate(-50%, -50%);
}

.page{
  white-space: nowrap;
  width: 100%;
  /* overflow-x: auto;
  overflow: auto; */
  font-family: notosans-thin;
}

.font-k {
  font-family: kern;
}

.font-nt {
  font-family: notosans-thin;
}

.font-n {
  font-family: notosans;
}

@font-face {
  font-family:'KCC-Ganpan';
  src: url('assets/fonts/KCC-Ganpan.ttf') format('truetype');
  font-weight: 400;
}

@font-face {
  font-family:'kern';
  src: url('assets/fonts/Kern Standard Trial Black.otf') format('truetype');
  font-weight: 400;
}

@font-face {
  font-family:'notosans';
  src: url('assets/fonts/NotoSansKR-Black.otf') format('truetype');
  font-weight: 400;
}

@font-face {
  font-family:'notosans-thin';
  src: url('assets/fonts/NotoSansKR-Medium.otf') format('truetype');
  font-weight: 400;
}

.goto-up {
  position: fixed;
  bottom: 40px;
  right: 90px;
  text-align: center;
}

</style>