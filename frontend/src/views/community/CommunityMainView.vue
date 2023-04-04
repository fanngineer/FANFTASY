<template>
  <br>
  <div class="community-menu">
    <div>
      <input type="text" v-model="searchItem" placeholder="글 검색">
      <v-icon icon="mdi-magnify"  @click="searchArticle"></v-icon>
    </div>

    <div class="community-menu">
      <input type="button" value="전체보기" @click="callAll" style="margin-right: 10px;">
      <input type="button" value="일반글" @click="callGeneral" style="margin-right: 10px;">
      <input type="button" value="삽니다" @click="callPurchase" style="margin-right: 10px;">
      <input type="button" value="팝니다" @click="callSales" style="margin-right: 10px;"> 
    </div>
  </div>
  <hr>
  <!-- 게시글 목록 -->
  <table>
    <thead>
      <tr>
        <th>No</th>
        <th>제목</th>
        <th>작성자</th>
        <th>날짜</th>
        <th>조회수</th>
      </tr>
    </thead>
    <tbody>
      
      <tr v-for="(article, index) in articles" :key="index">
    
        <td>
          {{ articles.length - index }}
        </td>
        <td>
          <div style="align-self: auto; font-size: 14px;">
            <img :src="require(`@/assets/${article.thumbnail}`)" alt="image" style="height: 20px; width: 20px;"> 
            <router-link :to="{name:'communitydetail', params:{number:articles.length - index }}" style="text-decoration:none; color: black;">
              {{ article.title }}
            </router-link>
          </div>
        </td>
        <td>
          {{ article.writer }}
        </td>
        <td>
          {{ article.createdAt }}
        </td>
        <td>
          {{ article.viewed }}
        </td>

        <!-- router에 detail용 community/:number 대충 만들어 둠. CommunityAddView 대충 틀 짜둠 -->

      </tr>
    </tbody>
  </table>

  <!-- 페이지네이션 -->
  <div class="btn-cover">
    <button :disabled="pageNum===0" @click="prevPage" class="page-btn">이전</button>
    <span class="page-count"> {{pageNum+1}} / {{pageCount}} 페이지 </span>
    <button :disabled="pageNum >= pageCount-1" @click="nextPage" class="page-btn">다음</button>
  </div>

  <div style="width: 211px">
    <router-link to="/community/add" style="text-decoration: none;">
      <button class="write-button" >
        글쓰기
      </button>
    </router-link>
  </div>
 
</template>

<script>

export default {
  name: "communityMain",
  components: {

  },
  data() {
    return {

      searchItem: '',

// 버튼을 누르면 computed로 type별 필터링해서 articles를 반환하고 그걸 토대로 테이블에 출력하면 된다... 어떻게??
      articles: [],
      // articlesData : axios로 불러오는 데이터
      articlesData : [
        { thumbnail: 'ImageGallery.png',
        type: '일반글',
        title:'제가 산 NFT 좀 제발 봐주세요',
        writer: '조민수',
        createdAt: '2023-03-02',
        viewed: 12,
      },
      { thumbnail: 'ImageGallery.png',
        type: '팝니다',
        title:'[팝니다] 현제 NFT 팝니다',
        writer: '조민수',
        createdAt: '2023-03-02',
        viewed: 32,
      },
      { thumbnail: 'ImageGallery.png',
        type: '일반글',
        title:'제가 산 NFT 봐주세요',
        writer: '조민수',
        createdAt: '2023-03-02',
        viewed: 45,
      },
      { thumbnail: 'Document.png',
        type: '삽니다',
        title:'[삽니다] 호연 NFT 갖고 계신분 계신가요...',
        writer: '조민수',
        createdAt: '2023-03-02',
        viewed: 62,
      },
      { thumbnail: 'Document.png',
        type: '일반글',
        title:'A306 이번 NFT 살만할까요...',
        writer: '조민수',
        createdAt: '2023-03-02',
        viewed: 145,
      },
    ],
    // 페이지네이션용 데이터
    listArray:[],
    pageNum: 0,
    pageSize: 3
    }
  },
  mounted() {
    // axios({
    //     method: 'get',
    //     url: `/api/board/${type}/${keyword}`
    //   })
    //   .then((res)=>{
    //     console.log(res)
    //     this.articleData = res.data
    //   })
    //   .catch((err)=>{
    //     console.log(err)
    //   })
    this.articles = this.articlesData
  },  
  methods:{
    searchArticle(){
      const item = this.searchItem
      alert(item)

      this.articles = this.articlesData
      const newArticles = []
      this.articles.filter((article)=>{
        alert(item)
        alert(article.title)
        if (item in article.title){
          newArticles.push(article)
        }
      })
      this.articles = newArticles
    },

    // 게시글 분류 선택
    callAll(){
      this.articles = this.articlesData
    },
    callGeneral(){
      this.articles = this.articlesData
      const newArticles = []
      this.articles.filter((article)=>{
        if (article.type==='일반글'){
          newArticles.push(article)
        }
      })
      this.articles = newArticles
    },
    callPurchase(){
      this.articles = this.articlesData
      const newArticles = []
      this.articles.filter((article)=>{
        if (article.type==='삽니다'){
          newArticles.push(article)
        }
      })
      this.articles = newArticles
    },
    callSales(){
      this.articles = this.articlesData
      const newArticles = []
      this.articles.filter((article)=>{
        if (article.type==='팝니다'){
          newArticles.push(article)
        }
      })
      this.articles = newArticles
    },
    // 페이지네이션
    nextPage(){
      this.pageNum+=1;
    },
    prevPage(){
      this.pageNum-=1;
    },
    // 게시글 작성
    writeArticle(){

    }
  },
  computed: {
    // pageCount(){
    //   const aray = this.listArray.concat(this.articles1)
    //   let listLeng = aray.length;
    //   let listSize = this.pageSize;
    //   let page = Math.floor((listLeng-1)/listSize)+1;
    //   return page;
    // },
    // articles1(){
    //   const aaa = this.$store.state.articles.slice().reverse() 
    //   return aaa
    // },
    // pagenatedData(){
    //   const aray = this.listArray.concat(this.articles)
    //   const start = this.pageNum * this.pageSize;
    //   const end = start + this.pageSize;  
    //   return aray.slice(start,end);
    // }
  },
  
}
</script>

<style>
  .community-menu{
    margin-top: 100px;
    display: flex;
  }

  .th {
    padding: 0px 100px;
  }

  .write-button{
    display: flex;
    right: 10px;
    width: 211px;
    height: 55px;
    font-size: 24px;
    font-weight: bold;
    justify-content: center;
    align-items: center;
    border-radius: 15px;
    color: white;
    background-color: RGB(106, 63, 193);
  }
  .btn-cover{
  margin-top: 10px;
  margin-bottom: 10px;
  }
  .btn-cover .page-btn{
    width: 5rem;
    border-radius: 10px;
    border: #fff;
    font-weight: bolder;
  }
  .btn-cover .page-count {
    font-weight: bolder;
    padding: 0 1rem;
  }

</style>