<template>
  <div class="home">
    <br />
    <carousel :items-to-show="1" :wrap-around="true" :autoplay="5000">
      <!-- max 1540 -->
      <slide v-for="slide in slides" :key="slide.id">
        <div class="carousel-item">
          <img :src="slide.image" alt="안나오냐" />
        </div>
      </slide>

      <template #addons>
        <navigation />
        <pagination />
      </template>
    </carousel>

    <p></p>
    <p></p>
    <h1 class="ranking-space">Ranking</h1>
    <br />
    <button v-on:click="selectNewest" v-if="sortnum === 0" class="selected-ranking-button">최신순</button>
    <button v-on:click="selectNewest" v-else class="unselected-ranking-button">최신순</button>
    <button v-on:click="selectSalesVolume" v-if="sortnum === 1" class="selected-ranking-button">
      판매량순
    </button>
    <button v-on:click="selectSalesVolume" v-else class="unselected-ranking-button">
      판매량순
    </button>
    <button v-on:click="selectSalesAmmount" v-if="sortnum === 2" class="selected-ranking-button">
      판매금액순
    </button>
    <button v-on:click="selectSalesAmmount" v-else class="unselected-ranking-button">
      판매금액순
    </button>
    <br />
    <br />
    <br />
    <br />
    <div>
      <RankingCard />
    </div>
    <router-link to="/market" style="text-decoration: none; color: black;">
      <div style="width:100%; height: 935px; background-color:chocolate; display: flex; justify-content: center; align-items: center;">  
        <div>
          등록된 아티스트의 NFT를 구매할 수 있습니다.
          <div>

            드롭스
          </div>
          드랍되었습니다~~~~
        </div>
      </div>
    </router-link>

    <router-link to="/community" style="text-decoration: none; color: black;">
      <div style="width:100%; height: 935px; background-color:tomato; display: flex; justify-content: center; align-items: center;">
        <div>
          리셀몰
        </div>
      </div>
    </router-link>
  </div>
</template>

<script>
import RankingCard from "@/components/mainpage/RankingCard.vue"
import { Carousel, Slide, Pagination, Navigation } from "vue3-carousel"
import "vue3-carousel/dist/carousel.css"
// import Web3 from "web3"
// import { Carousel, Slide} from 'vue3-carousel'

export default {
  name: "HomeView",
  components: {
    Carousel,
    Slide,
    Pagination,
    Navigation,
    RankingCard,
  },
  data() {
    return {
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
  mounted() {
    // Check if Web3 has already been injected by MetaMask
    if(typeof window.ethereum !== 'undefined'){
      console.log("설치되있음");
    }
    else {
      alert("저희 사이트는 METAMASK가 필수입니다.");
      location.href='https://chrome.google.com/webstore/detail/metamask/nkbihfbeogaeaoehlefnkodbefgpgknn';
    }
  },
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
  }
}
</script>

<style>
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
} */

.footer-space {
  position : relative;
  transform : translateY(-100%);
}
</style>
