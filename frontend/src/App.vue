<template >   
  <v-app class="page">
    <LoginUserHeaders v-if="a===1"/>
    <NotLoginUserHeaders />
    <v-main >
      <router-view />
    </v-main>
    <AllFooter class="footer-space"/>
  </v-app>
</template>

<script>
import AllFooter from "@/components/headers/AllFooter.vue"
import LoginUserHeaders from "@/components/headers/LoginUserHeaders.vue"
import NotLoginUserHeaders from "@/components/headers/NotLoginUserHeaders.vue"


export default {
  name: 'App',
  components:{
    AllFooter,
    LoginUserHeaders,
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
  overflow-x: auto;
  overflow:auto;
}

</style>