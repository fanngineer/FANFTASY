<template >   
  <v-app class="page">
    <!-- <LoginUserHeaders/> -->
    <NotLoginUserHeaders/>
    <v-main >
      <router-view />
    </v-main>
    <AllFooter class="footer-space footer_section"/>
  </v-app>
</template>

<script>
import AllFooter from "@/components/headers/AllFooter.vue"
// import LoginUserHeaders from "@/components/headers/LoginUserHeaders.vue"
import NotLoginUserHeaders from "@/components/headers/NotLoginUserHeaders.vue"

window.onload = function(){
  setTimeout(function(){
    scrollTo(0,0);
  },100);
}


export default {
  name: 'App',
  components:{
    AllFooter,
    // LoginUserHeaders,
    NotLoginUserHeaders
  },
  data: () => ({
    //
  }),
}
</script>

<style>
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



</style>