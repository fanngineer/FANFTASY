<template>
  <div>
    <LoginUserHeaders/>
    <NotLoginUserHeaders/>
    <AllFooter/>
  </div>
</template>

<script>
import LoginUserHeaders from "@/components/Headers/LoginUserHeaders.vue"
import NotLoginUserHeaders from "@/components/Headers/NotLoginUserHeaders.vue"
import AllFooter from "@/components/Headers/AllFooter.vue"
export default {
  name: 'MyPageView',
  components:{
    LoginUserHeaders,
    NotLoginUserHeaders,
    AllFooter,
  },
}
</script>

<style>

</style>