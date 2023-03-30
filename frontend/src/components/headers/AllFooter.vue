<template>
  <hr />
  <br />
  <div class="footer">
    <img :src="require('@/assets/logo.png')" alt="" class="header-logo" />
    <span style="display: inline-block;">
      <span>
        서울 3반
      </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <span>
        김성환, 신선호, 심호연, 장근우, 조민수, 최현제
      </span>
    </span>
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
