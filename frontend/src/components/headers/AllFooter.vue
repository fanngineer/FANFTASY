<template>
  <hr>
  <br>
  <div class="footer">
    <img :src="require('@/assets/logo.png')" alt="" class="logo">
    <img :src="require('@/assets/logo.png')" alt="" class="logo">
    <img :src="require('@/assets/logo.png')" alt="" class="logo">
  </div>
  <br>
</template>

<script>
export default {

}
</script>

<style>
.footer {
  display: flex;
  justify-content: space-evenly;
}

</style>