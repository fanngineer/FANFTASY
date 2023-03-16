<template>
  <hr />
  <br />
  <div class="footer">
    <img :src="require('@/assets/logo.png')" alt="" class="header-logo" />
    <img :src="require('@/assets/logo.png')" alt="" class="header-logo" />
    <img :src="require('@/assets/logo.png')" alt="" class="header-logo" />
  </div>
  <br />
</template>

<script>
export default {
  name: "AllFooter",
}
</script>

<style>
.footer {
  padding-top: 3px;
  display: flex;
  justify-content: space-evenly;
}
</style>
