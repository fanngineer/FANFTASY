<template>
  <LoginUserHeaders/>
  <NotLoginUserHeaders  v-if="i == 1"/>
  <br />
  <div>
    <!-- 작성이면 placeholder가 빈칸이고, 취소하면 전체 게시글로 이동 -->
    <!-- 수정이면 placeholder에 기존 내용이 있고, 취소할 때 본래 게시글로 이동 -->
    <div>글쓰기</div>
    <div>
      제목
      <input type="text" id="title" v-model.trim="title" style="border-style: solid; border-color: rgb(188, 188, 188); border-radius: 15px;">
    </div>

    <div>
      게시판
      <v-combobox v-model="boardType" :items="articleType" style="width:120px; height: 80px;">

      </v-combobox>
    </div>

    <div>
      <textarea v-model.trim="content" name="" id="" cols="30" rows="10" style="border-color: black; border-style: solid;"></textarea>
    </div>

    <div style="width: 211px">
      <router-link to="/community" style="text-decoration: none;">
        <button class="add-cancel" >
          취소
        </button>
      </router-link>
    </div>
    <button class="add-confirm" @click="addConfirm">
      작성
    </button>
    

  </div>
</template>

<script>
import LoginUserHeaders from '@/components/headers/LoginUserHeaders.vue';
import NotLoginUserHeaders from '@/components/headers/NotLoginUserHeaders.vue';
import Swal from 'sweetalert2'

export default {
  components:{
    LoginUserHeaders,
    NotLoginUserHeaders,
  },
  data(){
    return{
      title: null,
      boardType: null,
      content: null,
      articleType: ['일반글','팝니다','삽니다'],
    }
  },
  methods:{
    //addConfirm 함수는 post axios로 db에 보냄과 동시에 community로 리다이렉트
    addConfirm(){
      if (!this.title){
        Swal.fire({
          title: "METAMASK 필수",
          text: "저희 사이트는 METAMASK가 필수입니다.",
          icon: "info" //"info,success,warning,error" 중 택1
        })
        alert('제목을 입력해주세요')
      } else if (!this.boardType) {
        alert('게시글 분류를 선택해주세요')
      } else if (!this.content) {
        alert('내용을 입력해주세요')
      } else {
        console.log(this.title, this.boardType, this.content)
        //axios 문 
      }
    },
  }
}
</script>

<style>
  .add-cancel{
    width: 211px;
    height: 55px;
    border-radius: 15px;
    background-color: rgb(188, 188, 188);
    font-size: 24px;
    font-weight: bold;
    color: white;
  }
  
  .add-confirm{
    width: 211px;
    height: 55px;
    border-radius: 15px;
    background-color: rgb(106,63,193);
    font-size: 24px;
    font-weight: bold;
    color: white;
  }
</style>