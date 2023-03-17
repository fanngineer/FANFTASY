<template>
  <div class="home">
    <LoginUserHeaders />
    <NotLoginUserHeaders v-if="i == 1"/>
    <br />
    <carousel :items-to-show="1" :wrap-around="true" :autoplay="5000">
      <!-- max 1540 -->
      <slide v-for="slide in slides" :key="slide.id">
        <div class="carousel_item">
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
    <button v-on:click="openNew" class="selected-ranking-button">최신순</button>
    <button v-on:click="openNew" class="unselected-ranking-button">
      판매량순
    </button>
    <button v-on:click="openNew" class="unselected-ranking-button">
      판매금액순
    </button>
    <br />
    <br />
    <br />
    <br />
    <div>
      <RankingCard />
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import LoginUserHeaders from "@/components/headers/LoginUserHeaders.vue"
import NotLoginUserHeaders from "@/components/headers/NotLoginUserHeaders.vue"

import RankingCard from "@/components/mainpage/RankingCard.vue"
import { Carousel, Slide, Pagination, Navigation } from "vue3-carousel"
import "vue3-carousel/dist/carousel.css"
// import { Carousel, Slide} from 'vue3-carousel'

export default {
  name: "HomeView",
  components: {
    LoginUserHeaders,
    NotLoginUserHeaders,
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
            "https://lab.ssafy.com/s08-blockchain-contract-sub2/S08P22A306/-/raw/mainpage/frontend/src/assets/BTS.png",
        },
        {
          id: 2,
          image:
            "https://lab.ssafy.com/s08-blockchain-contract-sub2/S08P22A306/-/raw/mainpage/frontend/src/assets/HERO.png",
        },
        {
          id: 3,
          image:
            "https://lab.ssafy.com/s08-blockchain-contract-sub2/S08P22A306/-/raw/mainpage/frontend/src/assets/Musical.png",
        },
      ],
    }
  },
}
</script>

<style>
.carousel_item {
  height: 300px;
  width: 100%;
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
