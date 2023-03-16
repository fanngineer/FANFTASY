<template >   
  <v-app class="page">
    <v-main >
      <router-view />
    </v-main>
    <AllFooter class="footer-space"/>
  </v-app>
</template>

<script>
import AllFooter from "@/components/headers/AllFooter.vue"


export default {
  name: 'App',
  components:{
    AllFooter
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