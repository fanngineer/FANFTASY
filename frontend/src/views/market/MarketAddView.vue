<template>
  <div style="
    width: 100%;
    height: 100vh;
    /* background-color: white; */
    padding-top: 100px;
    text-align: center;
    /* display: flex; */
    justify-content: center;
    align-items: center;">
    <span style="font-size: 45px">
      NFT 생성
    </span>
    <br>
    <br>
    <label for="">파일선택</label>
    <!-- <input v-on:change="previewFiles(this.files)" type="file" accept="image/*, video/*, audio/*" style="border:solid"> -->
    <input type="file" @change="handleFileUpload">
    <br>
    <br>
    <label for="">제목</label>
    <input v-model="title" type="text" style="border:solid">
    <br>
    <br>
    <label for="">내용</label>
    <textarea v-model="content" name="" id="" cols="30" rows="10" style="border:solid"></textarea>
    <br>
    <br>
    <label for="">발매 갯수</label>
    <input v-model="totalNum" type="text" style="border:solid">
    <br>
    <br>
    <label for="">발매가격</label>
    <input v-model="originPrice" type="text" style="border:solid">
    <br>
    <br>
    <!-- <label for="">파일 타입</label>
    <input type="combobox" style="border:solid"> -->
    <button @click="this.addNFT()">
      NFT 생성
    </button>
  </div>
</template>

<script>
import VueCookies from "vue-cookies"

export default {
  name: "MarketAddView",
  data() {
    return {
      file: null,
      title:"",
      content:"",
      totalNum:"",
      originPrice:"",
      regArtistId: VueCookies.get("userId"),
      fileType:"image",
    }
  },
  methods: {
    handleFileUpload(event) {
      this.file = event.target.files[0];
      console.log(this.file);
    },
    addNFT() {
      const file = this.file
      const title = this.title
      const content = this.content
      const totalNum = this.totalNum
      const originPrice = this.originPrice
      const regArtistId = this.regArtistId
      let fileType = this.fileType


      const payload ={
        file,
        title,
        content,
        totalNum,
        originPrice,
        regArtistId,
        fileType
      }
      console.log("----------------------")
      console.log(payload)

      this.$store.dispatch("addNFT", payload)
    }
  },
}
</script>

<style>

</style>