<template>
  <div style="
    width: 100%;
    height: 80vh;
    /* background-color: white; */
    padding-top: 100px;
    text-align: center;
    /* display: flex; */
    justify-content: center;
    align-items: center;">
    <span class="minting-title">
      NFT 생성
    </span>
    <br>
    <br>
    <label for="">파일선택</label>  
    <!-- <input v-on:change="previewFiles(this.files)" type="file" accept="image/*, video/*, audio/*" style="border:solid"> -->
    <input type="file" @change="handleFileUpload" accept="image/*, video/*, audio/*"/>
    <br>
    <br>
    <label for="">제목</label>
    <input v-model="title" type="text" style="border:solid" placeholder="제목을 입력해주세요.">
    <br>
    <br>
    <label for="">내용</label>
    <textarea v-model="content" name="" id="" cols="30" rows="4" style="border:solid" placeholder="상세내용을 입력해주세요."></textarea>
    <br>
    <br>
    <label for="">발매 갯수</label>
    <input v-model="totalNum" type="text" style="border:solid" placeholder="발매할 NFT수를 입력해주세요.">
    <br>
    <br>
    <label for="">발매가격</label>
    <input v-model="originPrice" type="text" style="border:solid" placeholder="판매가격을 입력해주세요.">
    <br>
    <br>
    <label for="">판매종료일</label>
    <input v-model="endDate" type="date" style="border:solid" >

    <br>
    <br>
    <!-- <label for="">파일 타입</label>
    <input type="combobox" style="border:solid"> -->
    <button @click="this.addNFT()" class="minting-button">
      생성 시작
    </button>
  </div>
</template>

<script>
import VueCookies from "vue-cookies"
import Swal from 'sweetalert2'
import router from "@/router"

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
      fileType:"",
      selectedFile: null,
      endDate: null,
      now: Date.now(), 
      role: null,
      // a: Date.
    }
  },
  created(){
    this.getUserDetail()
    this.isLogin()
  },
  methods: {
    async getUserDetail(){
      try{
        const result = await this.$store.dispatch('userDetail')

        console.log("result : " + result)
        this.role = this.$store.state.role;
        console.log(this.role)
        this.isArtist()
      }
      catch (err){
        console.log(err)
      }
    },

    async isLogin(){
      if (VueCookies.get("nickname") === null){
        this.mem = true
        await Swal.fire({
          title: "로그인 되지 않았습니다.",
          text: "로그인 후 이용해주세요",
          icon: "warning" //"info,success,warning,error" 중 택1
        })
        router.go(-1)
      } else {
        this.mem = false
      }
    },

    isArtist(){
      if (this.role != 'artist') {
        Swal.fire({
          title: "접근 불가",
          text: "권한이 없습니다!",
          icon: "warning" //"info,success,warning,error" 중 택1
        })
        router.go(-1)
      }
    },

    handleFileUpload(event) {
      this.file = event.target.files[0];
      console.log(this.file);
      console.log(this.file.type.substring(0, 5))
      this.fileType = this.file.type.substring(0, 5)
      
    },
    addNFT() {
      const file = this.file
      const title = this.title
      const content = this.content
      const totalNum = this.totalNum
      const originPrice = this.originPrice
      const regArtistId = this.regArtistId
      let fileType = this.fileType
      const endDate = this.endDate + "T00:00:00"
      console.log(endDate)

      const payload = {
        file,
        title,
        content,
        totalNum,
        originPrice,
        regArtistId,
        fileType,
        endDate,
      }
      console.log("----------------------")
      console.log(payload)

      this.$store.dispatch("addNFT", payload)
    }
  },
}
</script>

<style>
.minting-title{
  font-size: 45px;
  font-weight: bold;
  color: #6a3fc1;
}
.minting-button{
  width: 20vw;
  height: 8vh;
  border-radius: 15px;
  font-size: 2vw;
  font-weight: bold;
  border: 1px #9B7CF8 solid;
  color: #9B7CF8; 
}
.minting-button:hover{
  background-color: #9B7CF8;
  color: white;
}
</style>