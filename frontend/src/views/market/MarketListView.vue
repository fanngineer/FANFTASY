<template>
  <br />
  <br />
  <div class="market-entire">
    <div style="min-width: 1140px; max-width: 1140px;"> 
      <div class="nftbar-container">
        <div class="nftbar">
          <div class="market-space">판매중인 NFT</div>
          <div class="df filter-search-drops">
            <div>
              <button
                v-on:click="selectNewest"
                v-if="orderType === 1"
                class="filter-btn"
              >
                최신순
              </button>
              <button v-on:click="selectNewest" v-else class="filter-btn2">
                최신순
              </button>
              <button
                v-on:click="selectHighAmmount"
                v-if="orderType === 4"
                class="filter-btn"
              >
                높은가격순
              </button>
              <button v-on:click="selectHighAmmount" v-else class="filter-btn2">
                높은가격순
              </button>
              <button
                v-on:click="selectLowAmmount"
                v-if="orderType === 3"
                class="filter-btn"
              >
                낮은가격순
              </button>
              <button v-on:click="selectLowAmmount" v-else class="filter-btn2">
                낮은가격순
              </button>
              <button
                v-on:click="selectSalesVolume"
                v-if="orderType === 2"
                class="filter-btn"
              >
                잔여수량순
              </button>
              <button v-on:click="selectSalesVolume" v-else class="filter-btn2">
                잔여수량순
              </button>
            </div>


            <label for="" class="box ib">
              <input
                type="text"
                class="notting search-input"
                placeholder="검색어를 입력하세요"
                v-model="this.keyword"
                />
              <button
                @click="searchEvents"
                class="notting"
                style="display: flex;                  justify-content: right;
                align-self: center;
                "
              >
                <img
                  :src="require('@/assets/Search.png')"
                  alt=""
                  style="margin-top: 7px"
                />
              </button>
            </label>
          </div>
          <br />
          <hr />
          <br />
        </div>
      </div>
      <div style="text-align: center; padding-top: 168px">
        <!-- <br>
        <p>
          {{ this.cards }}

        </p> -->

        <NFTCard  v-for="(card, index) in cards" :key="index" :card="card" class="fl" />

        <!-- <NFTCard v-for="i in cards" :key="i" class="nft-card-class"/> -->
        <!-- <br /> -->
        <!-- <p>asdfasdf</p> -->
        <!-- <NFTCard v-for="i in 4" :key="i" class="nft-card-class" />
        <br />
        <NFTCard v-for="i in 4" :key="i" class="nft-card-class" />
        <br />
        <NFTCard v-for="i in 4" :key="i" class="nft-card-class" />
        <br />
        <NFTCard v-for="i in 4" :key="i" class="nft-card-class" />
        <br />
        <NFTCard v-for="i in 4" :key="i" class="nft-card-class" /> -->
      </div>
      <br />
    </div>
  </div>
</template>

<script>
import NFTCard from "@/components/market/NFTCard.vue"

export default {
  name: "MarketListView",
  components: {
    NFTCard,
  },
  data() {
    return {
      // orderType: 0,
      artist: true,
      cards: [],
      orderType: 1,
      page: 0,
      keyword: "",
    }
  },
  created() {
    this.getDrops()
  },
  methods: {
    selectNewest() {
      this.orderType = 1
      this.getDrops()
    },
    selectSalesVolume() {
      this.orderType = 2
      this.getDrops()
    },
    selectHighAmmount() {
      this.orderType = 4
      this.getDrops()
    },
    selectLowAmmount() {
      this.orderType = 3
      this.getDrops()
    },
    searchEvents(){
      this.getDrops()
    },
    async getDrops() {
      const orderType = this.orderType
      const page = this.page
      const keyword = this.keyword
      const payload = {
        orderType,
        page,
        keyword,
      }
      await this.$store.dispatch("getDrops", payload)
      console.log(this.cards)
      this.cards = this.$store.cards
      console.log(this.cards)
      console.log("123456789")
    },
  },
}
</script>

<style>
.market-entire {
  padding-top: 22px;
  display: flex;
  justify-content: center;
}

.nftbar-container {
  background-color: white;
  position: fixed;
  padding-top: 20px;
  z-index: 1;
}

.nftbar {
  width: 75rem;
}

.market-space {
  font-family: notosans;
  font-size: 40px;
  margin-bottom: 10px;
}

.filter-search-drops {
  justify-content: space-between;
}

.nft-add-btn {
  display: flex;
  width: 246px;
  height: 67px;
  font-size: 24px;
  font-weight: bold;
  justify-content: center;
  align-items: center;
  border-radius: 15px;
  color: white;
  background-color: RGB(106, 63, 193);
}

.filter-btn2 {
  width: 120px;
  height: 40px;
  display: inline-block;
  position: relative;
  font-weight: bolder;
}
.filter-btn2:after {
  border-top-right-radius: 10px;
  border-top-left-radius: 10px;
  position: absolute;
  content: "";
  bottom: -5%;
  display: block;
  background: #6a3fc1;
  width: 120px;
  animation: btn-click2 1s forwards;
  z-index: -1;
}
@keyframes btn-click2 {
  0% {
    font-weight: bolder;
    /* opacity: 0.5; */
    height: 100%;
  }
  100% {
    font-weight: bolder;
    opacity: 0;
    height: 0px;
  }
}
.filter-btn2:hover {
  font-weight: bolder;
  color: #6a3fc1;
}

.filter-btn {
  width: 120px;
  height: 40px;
  display: inline-block;
  position: relative;
  font-weight: bolder;
  color: white;
}
.filter-btn:after {
  border-top-right-radius: 10px;
  border-top-left-radius: 10px;
  position: absolute;
  content: "";
  bottom: -5%;
  display: block;
  background: #6a3fc1;
  width: 120px;
  animation: btn-click 1s forwards;
  z-index: -1;
}
@keyframes btn-click {
  0% {
    color: red;
    font-weight: bolder;
    opacity: 0;
    height: 0px;
  }
  100% {
    color: yellowgreen;
    font-weight: bolder;
    /* opacity: 0.5; */
    height: 100%;
  }
}

.card-break {
  flex-basis: 100%;
  /* height: 0; */
}
</style>
