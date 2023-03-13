<template>
  <div class="home">
    <LoginUserHeaders />
    <NotLoginUserHeaders />
    <!-- <carousel>
      <slide>
        <img src="@/assets/BTS.png" alt="">
      </Slide>
      <slide>
        <img src="@/assets/HERO.png" alt="">
      </Slide>
      <slide>
        <img src="@/assets/Musical.png" alt="">
      </Slide>
    </carousel> -->
    <carousel :items-to-show="1">
      <slide v-for="slide in 10" :key="slide">
        <div class="carousel_item">
          <!-- <img :src="slide.image" alt="안나오냐"> -->
        </div>
      </slide>

    <!-- <template #addons>
      <navigation />
      <pagination />
    </template> -->
  </carousel>
  <AllFooter />
  </div>
</template>

<script>
// @ is an alias to /src
import LoginUserHeaders from "@/components/Headers/LoginUserHeaders.vue"
import NotLoginUserHeaders from "@/components/Headers/NotLoginUserHeaders.vue"
import AllFooter from "@/components/Headers/AllFooter.vue"
// import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel'
import { Carousel, Slide} from 'vue3-carousel'

export default {
  name: 'HomeView',
  components: {
    LoginUserHeaders,
    NotLoginUserHeaders,
    Carousel,
    Slide,
    AllFooter,
    // Pagination,
    // Navigation,
  },
  data() {
    return {
      // slides:[
      //   {
      //     id: 1,
      //     image: "https://lab.ssafy.com/s08-blockchain-contract-sub2/S08P22A306/-/raw/mainpage/frontend/src/assets/BTS.png"
      //   },
      //   {
      //     id: 2,
      //     image: "https://lab.ssafy.com/s08-blockchain-contract-sub2/S08P22A306/-/raw/mainpage/frontend/src/assets/HERO.png"
      //   },
      //   {
      //     id: 3,
      //     image: "https://lab.ssafy.com/s08-blockchain-contract-sub2/S08P22A306/-/raw/mainpage/frontend/src/assets/Musical.png"
      //   }]
    }
  }
}
</script>

<style>
.carousel_item {
  min-height: 200px;
  width: 100%;
  background-color: var(--vc-clr-primary);
  color: var(--vc-clr-white);
  font-size: 20px;
  border-radius: 8px;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>