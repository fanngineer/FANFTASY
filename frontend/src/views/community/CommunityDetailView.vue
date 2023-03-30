<template>
  <div>
    <LoginUserHeaders/>
    <NotLoginUserHeaders v-if="i == 1"/>
    
    <div>
      제목
    </div>

    <!-- <div v-if="this.$store.state.userName==article?.username" class="update-delete">
    </div> -->
    <div style="display: flex; width:120px; justify-content:space-between;">
      <button @click="updateArticle(article)">수정</button>
      <button @click="deleteArticle(article)">삭제</button>
    </div>
    <!-- 현재 로그인한 이용자와 게시글 작성자가 같으면 비활성화, 다르면 활성화 -->
    <button class="report-article-button" @click="reportArticle">
      <v-icon icon="mdi-bullhorn"/>
      <div>
        신고
      </div>
    </button>

    <!-- 게시글 분류 -->
    <!-- 팝니다 글이면 거래 트랜잭션을 발생시키는 버튼 존재 -->
    <!-- 클릭하면 바로 nft의 주인을 바꿔줘야 함 -->
    <!-- 삭제 할거면 판매 완료되면 찜해둔 사람한테 알람이 가야 함 -->
    <!-- 삭제 안할거면 그냥 판매 완료 아이콘이나 어쨌든 뭐가 뜸 -->
    <!-- 판매완료 boolean 값 있어야 함 -->


    <!-- 만약 이 게시글의 type이 팝니다(판매글)이면 찜하기 버튼 생성 -->
    <!-- 판매글에 대한 찜하기 -->
    <div>
      <button @click="likeArticle">
        <v-icon style="color:rgb(188, 188, 188)" icon="mdi-heart-outline"/>
        찜하기
      </button>
    </div>

    <div>
      
    </div>

    <div style="display: inline-flex;">
      작성자
      <div style="border-radius: 50%; background-color: aqua; width: 20px; height: 20px;"></div>
      조민수
      <div style="display: inline-flex;">
        <div>
          작성일자
          2023.03.21
        </div> 
        <div>
          조회수
          12
        </div>
      </div>
    </div>
    <hr>
    
    <div>
      내용
    </div>
    
    <hr>

    
    <div>
      댓글 [{{ commentCnt }}]
    </div>
    <div v-for="(comment, index) in comments" :key="index"  >
      <!-- 달린 댓글 -->
      <v-hover v-slot="{isHovering, props}">
        <div style="display: inline-flex;"  >
          <div :class="{'comment-hover': isHovering}" v-bind="props" style="display: inline-flex;">
            <img :src="require(`@/assets/${comment.thumbnail}`)" alt="image" style="height: 20px; width: 20px; border-radius: 50%;"> 
            {{ comment.name }}
            {{ comment.comment }}
            {{ comment.createdAt }}
            <div :class="{'show-kebab-menu' : isHovering}" style="color:transparent; margin-left: 5px;">
              <v-menu >
                <template v-slot:activator="{ props }">
                  <div v-bind="props" style="cursor: pointer;">
                    ⁝
                  </div>
                </template>
                <v-list style="width:80px; justify-content: center;">
                  <div @click="reportComment" style="color: black; justify-content: center; align-items: center; margin-left: 10px; display: inline-flex; cursor: pointer;" >
                    <v-icon style="color:rgb(106, 63, 193)" icon="mdi-bullhorn"/>
                    <div style="margin-left: 5px; margin-top: 4px;">
                      신고
                    </div> 
                  </div>
                </v-list>
              </v-menu>
            </div> 
          </div>
            
        </div>
      </v-hover>
    </div>

    <div>
      <!-- 댓글 작성창 -->
      <div style="display: inline-flex; align-items: center;">
        <v-img src="@/assets/base_profile.png" alt="?" style="width:20px; height:20px; border-radius: 50%;  object-fit: contain; object-position: 20% 50%;">
        </v-img>
        조민수
        <input type="text" v-model="comment" class="comment-write">
        <button class="comment-confirm" @click="createComment">
          작성
        </button>
      </div>
    </div>

  </div>
</template>

<script>
import LoginUserHeaders from '@/components/headers/LoginUserHeaders.vue';
import NotLoginUserHeaders from '@/components/headers/NotLoginUserHeaders.vue';
import { aliases, mdi } from 'vuetify/iconsets/mdi'
export default {
  icons: {
    defaultSet: 'mdi',
    aliases,
    sets: {
      mdi,
    }
  },
  components:{
    LoginUserHeaders,
    NotLoginUserHeaders,
  },
  data(){
    return {
      commentCnt: 0,
      
      // 게시글 객체 null로 초기화
      article: null,

      comments : [
        { pk: 1,
          thumbnail:'뉴진스_logo.jpg', 
          name:'그누그누',
          comment: '얼마주고 샀어요?',
          createdAt: '2023-03-22 09:40',
          isHovering: false,
        },
        { pk: 2,
          thumbnail: 'bts_logo.png',
          name:'현재현제',
          comment: '와 나도 갖고 싶다',
          createdAt:'2023-03-22 09:52',
          isHovering: false,
        }],
      
        // 내가 쓸 게시글 공백으로 초기화
        comment: '',
      


    }
  },
  created(){
    //내용, 댓글 가져오는 함수 실행
    this.commentCnt = this.comments.length
  },
  methods:{
    //게시글 내용 가져오는 axios get 함수를 store에서 dispatch

    //댓글 가져오는 axio get 함수
    //댓글 작성해서 axios post하는 함수
    createComment(){
      const content = this.comment
      if (content===''){
        alert('댓글 내용을 작성해주세욧!')
      } else {
        alert(content)
        this.comment = ''
      }
    },
    
    //게시글 삭제 함수
    deleteArticle(article){
      // const axios = 'd' 
      // axios({
      //   method: 'delete',
      //   url: `{API_URL}/community/${article.id}`,
      //   data:{
      //     userId : article.user,
      //     userName : this.$store.state.userName,
      //     pk_id : article.id 
      //   }
      // })
      // .then(()=>{
      //   this.getArticles()
      // })
      // .catch((err)=>{
      //   console.log(err)
      //   alert(err)
      // })
      alert((article,'삭제합니다~'))
    },

    //게시글 수정 함수
    updateArticle(){
      this.$router.push({name:'communityadd'})
    },

    likeArticle(){
      alert('이 판매글을 찜했습니다.')
    },

    // 게시글 신고 axios post 함수
    reportArticle(){
      // 게시글의 pk 정보와 신고자 id를 back에 보내면 될 듯? (json 정보에 이미 포함되어 있는 정보면 back에서 파싱해서 사용. 아니면 아예 front에서 분간해서 전송)
      alert('이 게시글을 신고하시겠습니까?')
    },

    // 댓글 신고 axios post 함수
    reportComment(){
      // 게시글과 동일
      alert('진짜 신고하시겠습니까?')
    },

    
  },
}
</script>

<style>
  .report-article-button{
    display: inline-flex;
    width: 100px;
    height: 30px;
    justify-content: center;
    align-items: center;
    font-size: 14px;
    border-radius: 15px;
    box-shadow: 0px 5px 5px gray; 
  }

  .comment-write{
    width: 887px;
    height: 34px;
    border-radius: 10px;
    border-style: solid;
  }

  .comment-confirm{
    margin-left: 10px;
    width: 98px;
    height: 50px;
    font-size: 24px;
    font-weight: bold;
    justify-content: center;
    align-items: center;
    border-radius: 10px;
    color: white;
    background-color: RGB(106, 63, 193)
  }

  .show-kebab-menu{
    color: rgba(0, 0, 0, 1) !important  ;
  }

</style>