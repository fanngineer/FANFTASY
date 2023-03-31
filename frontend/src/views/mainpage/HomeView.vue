<template>
  <div class="home my-template-container">
    <div class="content section" style="vertical-align: center">
      <div class="content1 df">
        <span class="fl main-ff" style="vertical-align: center">
          <!-- <p class="main-font main-font-1">NFT를 통해 팬과 아티스트가 하나되는 세상</p>
          <br> 
          <p class="main-font main-font-2">FANFTASY</p> -->
          <animMain />
        </span>
        <span style="padding-top: 20vh; border-color: black">
          <!-- <v-img src="@/assets/maingif.gif" style="width:350px; height: 550px;"></v-img> -->
        </span>
      </div>
      <div style="text-align: center; font-size: 30px; margin-top: 90px;">스크롤</div>
      <div style="text-align: center;">
        <v-img src="@/assets/chevron-double-down.png" alt="" style="width: 35px; margin: auto;"></v-img>
      </div>
    </div>
    <!-- <carousel :items-to-show="1" :wrap-around="true" :autoplay="5000">
      <slide v-for="slide in slides" :key="slide.id">
        <div class="carousel-item">
          <img :src="slide.image" alt="안나오냐" />
        </div>
      </slide>

      <template #addons>
        <navigation />
        <pagination />
      </template>
    </carousel> -->

    <div class="content section">
      <div
        style="
          width: 100%;
          height: 100vh;
          background-color: white;
          padding-top: 100px;
          text-align: center;
          /* display: flex; */
          justify-content: center;
          align-items: center;
        "
      >
        <!-- <h1 class="ranking-space">Ranking</h1>
          <br />
          <button
          v-on:click="selectNewest"
          v-if="sortnum === 0"
          class="selected-ranking-button"
          >
          최신순
        </button>
        <button
        v-on:click="selectNewest"
        v-else
        class="unselected-ranking-button"
        >
        최신순
      </button>
      <button
      v-on:click="selectSalesVolume"
      v-if="sortnum === 1"
            class="selected-ranking-button"
          >
          판매량순
        </button>
        <button
        v-on:click="selectSalesVolume"
        v-else
        class="unselected-ranking-button"
        >
        판매량순
      </button>
      <button
      v-on:click="selectSalesAmmount"
      v-if="sortnum === 2"
      class="selected-ranking-button"
      >
      판매금액순
    </button>
    <button
    v-on:click="selectSalesAmmount"
    v-else
    class="unselected-ranking-button"
    >
    판매금액순
  </button> -->
        <router-link to="/drops" style="text-decoration: none; color: black">
          <span style="font-size: 45px">🎁Drops</span>
        </router-link>
        <br />
        <br />
        <div style="font-size: 25px">
          등록된 아티스트의 NFT를 구매할 수 있습니다.
        </div>
        <br />
        <div>
          <NFTCard v-for="i in 4" :key="i" class="nft-card-class"/>
          <!-- <br />
          <NFTCard v-for="i in 4" :key="i" class="nft-card-class"/> -->
        </div>
      </div>
    </div>

    <div class="content section">
      <div
      style="
            width: 100%;
            height: 100vh;
            background-color: rgb(218, 227, 243);
            padding-top: 100px;
            text-align: center;
            /* display: flex; */
            justify-content: center;
            align-items: center;
            "
        >
        <router-link to="/market" style="text-decoration: none; color: black">
          <span style="font-size: 45px">🙋‍♀️리셀몰🙋‍♂️</span>
        </router-link>
          <br />
          <br />
          <div style="font-size: 25px">
            유저간 보유 NFT를 사고 팔 수 있습니다.
          </div>
        </div>
    </div>

    <div class="content section">
      <div
      style="
            width: 100%;
            height: 100vh;
            background-color: white;
            padding-top: 100px;
            text-align: center;
            /* display: flex; */
            justify-content: center;
            align-items: center;
            "
        >
        <router-link to="/mypage" style="text-decoration: none; color: black">
          <span style="font-size: 45px">✨나의 NFT</span>
        </router-link>
        <br>
        <br>
        <div class="df cen" v-if="a == 1">
          <div style="width: 300px; height: 500px; margin: 0 40px;">
            <v-img src="@/assets/nft_main(1).jpg" alt="" style=" border-radius: 3%;">
            </v-img>
          </div>
          <div style="width: 300px; height: 500px; margin: 0 40px;">
            <v-img src="@/assets/nft_main(1).jpg" alt="" style=" border-radius: 3%;">
            </v-img>
          </div>
          <div style="width: 300px; height: 500px; margin: 0 40px;">
            <v-img src="@/assets/nft_main(1).jpg" alt="" style=" border-radius: 3%;">
            </v-img>
          </div>
        </div>
        <div v-else style="margin: 33vh;">
          <span style="font-size: 30px">
            현재 보유하고 있는 NFT가 없습니다. NFT를 구매해보세요!
          </span>
          <br>
          <br>
          <br>
          <div class="">
            <span class="">
              <router-link to="/drops" style="text-decoration: none; color: black">
                아티스트가 만든 NFT를 구매해보세요
              </router-link>
            </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span class="">
              <router-link to="/market" style="text-decoration: none; color: black">
                사용자간 거래를 해보세요
              </router-link>
            </span>
          </div>
        </div>
        <br>
        <span>
          <router-link to="/mypage" style="text-decoration: none; color: white;">
            <div v-show="a==1" class="go-my-box">
              <p style="padding-top: 7px; font-size: 16px;">
                SEE MORE MY NFT
              </p> 
            </div>
          </router-link>
        </span>
      </div>
    </div>
  </div>
</template>

<script>
import animMain from "@/components/mainpage/anim.vue"
import NFTCard from "@/components/market/NFTCard.vue"
// import RankingCard from "@/components/mainpage/RankingCard.vue"
// import { Carousel, Slide, Pagination, Navigation } from "vue3-carousel"
// import "vue3-carousel/dist/carousel.css"
// import VueCookies from "vue-cookies"
// import Web3 from "web3"
// import { Carousel, Slide} from 'vue3-carousel'

// window.addEventListener("wheel", function(e){
//     e.preventDefault();
// },{passive : false})

window.onload = function () {
  const elm = document.querySelectorAll(".section")
  const elmCount = elm.length
  elm.forEach(function (item, index) {
    item.addEventListener("mousewheel", function (event) {
      event.preventDefault()
      let delta = 0

      if (!event) event = window.event
      if (event.wheelDelta) {
        delta = event.wheelDelta / 120
        if (window.opera) delta = -delta
      } else if (event.detail) delta = -event.detail / 3

      let moveTop = window.scrollY
      let elmSelector = elm[index]

      // wheel down : move to next section
      if (delta < 0) {
        if (elmSelector !== elmCount - 1) {
          try {
            moveTop =
              window.pageYOffset +
              elmSelector.nextElementSibling.getBoundingClientRect().top
          } catch (e) {
            // console.log("aa")
          }
        }
      }
      // wheel up : move to previous section
      else {
        if (elmSelector !== 0) {
          try {
            moveTop =
              window.pageYOffset +
              elmSelector.previousElementSibling.getBoundingClientRect().top
          } catch (e) {
            // console.log("aa")
          }
        }
      }

      // const body = document.querySelector('html');
      window.scrollTo({ top: moveTop, left: 0, behavior: "smooth" })
    })
  })
  setTimeout(function () {
    scrollTo(0, 0)
  }, 100)
}

// window.on("wheel", function(e) {
//     if(mHtml.is(":animated")) return;
//     if(e.originalEvent.deltaY > 0) {
//         if(page == 4) return;
//         page++;
//     } else if(e.originalEvent.deltaY < 0) {
//         if(page == 1) return;
//         page--;
//     }
//     var posTop =(page-1) * window.height();
//     mHtml.animate({scrollTop : posTop});
// })

export default {
  name: "HomeView",
  components: {
    // Carousel,
    // Slide,
    // Pagination,
    // Navigation,
    // RankingCard,
    NFTCard,
    animMain,
  },
  data() {
    return {
      a: 2,
      slides: [
        {
          id: 1,
          image:
            "https://lab.ssafy.com/s08-blockchain-contract-sub2/S08P22A306/-/raw/dev-front/frontend/src/assets/btsFrame.png",
        },
        {
          id: 2,
          image:
            "https://lab.ssafy.com/s08-blockchain-contract-sub2/S08P22A306/-/raw/dev-front/frontend/src/assets/HERO.png",
        },
        {
          id: 3,
          image:
            "https://lab.ssafy.com/s08-blockchain-contract-sub2/S08P22A306/-/raw/dev-front/frontend/src/assets/Musical.png",
        },
      ],
      sortnum: 0,
    }
  },
  watch: {},
  created() {
    
  },
  mounted() {},
  methods: {
    selectNewest() {
      this.sortnum = 0
    },
    selectSalesAmmount() {
      this.sortnum = 2
    },
    selectSalesVolume() {
      this.sortnum = 1
    },
  },
}
</script>

<style>
html ::-webkit-scrollbar {
  display: none;
}

html,
body {
  width: 100%;
  height: 100%;
}

.carousel-item {
  height: 300px;
  /* width: 100%; */
  background-color: white;
  color: var(--vc-clr-white);
  font-size: 20px;
  border-radius: 8px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.ranking-space {
  display: flex;
  justify-content: left;
  margin-left: 15vw;
  margin-right: 6vw;
  float: left;
}

.selected-ranking-button {
  width: 88px;
  height: 28px;
  left: 273px;
  top: 47px;
  float: left;
  background: #6a3fc1;
  border-radius: 10px;
  border: none;
  margin: 10px 1vw;
  font: bold;
  color: white;
}

.unselected-ranking-button {
  width: 88px;
  height: 28px;
  left: 273px;
  top: 47px;
  float: left;
  background: #d9d9d9;
  border-radius: 10px;
  border: none;
  margin: 10px 1vw;
  font: bold;
  color: #6a3fc1;
  cursor: pointer;
}

/* .home {
  min-width: 1540px;
  overflow-x: auto;
  overflow:auto;
} */

/* .footer-space {
  position : relative;
  transform : translateY(-100%);
} */

.content {
  width: 100% !important;
  height: 100vh !important;
  /* background-color: aliceblue; */
  background-image: url(@/assets/maingif3.gif);
  background-size: 100% 101vh;
  position: relative;
  z-index: 0;
  overflow: hidden;
}


.content1 {
  /* display: inline-block; */
  vertical-align: middle;
  justify-content: space-evenly;
}

.content2 {
  
  background-image: url(@/assets/배경.jpg) !important;
}

.main-ff {
  /* padding-top: 44vh; */
  padding-top: 22vh;
  width: 880px;
}

.main-font {
  font-size: 40px;
}

.main-font-1 {
  text-align: start;
}

.main-font-2 {
  text-align: end;
}

.go-my-box {
  width: 300px;
  height: 40px;
  /* font-size: 30px; */
  border: solid 1px;
  border-radius: 50px;
  margin: auto;
  background: #9B7CF8;
  vertical-align: middle;
  font-family: KCC-Ganpan;
}
</style>
