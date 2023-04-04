<template>
  <br />
  <br />
  <div class="market-entire">
    <div style="min-width: 1140px; max-width: 1140px;"> 
      <div class="nftbar-container">
        <div class="nftbar">
          <div class="market-space">거래중인 NFT</div>
          <div class="df filter-search-drops">
            <div>
              <button
                v-on:click="selectNewest"
                v-if="orderType === 1 & saleType === 1"
                class="filter-btn"
              >
                최신순
              </button>
              <button v-on:click="selectNewest" v-else class="filter-btn2">
                최신순
              </button>
              <button
                v-on:click="selectHighAmmount"
                v-if="orderType === 2 & saleType === 1"
                class="filter-btn"
              >
                높은가격순
              </button>
              <button v-on:click="selectHighAmmount" v-else class="filter-btn2">
                높은가격순
              </button>
              <button
                v-on:click="selectLowAmmount"
                v-if="orderType === 3 & saleType === 1"
                class="filter-btn"
              >
                낮은가격순
              </button>
              <button v-on:click="selectLowAmmount" v-else class="filter-btn2">
                낮은가격순
              </button>
              <button
                v-on:click="selectNoSales"
                v-if="orderType === 3 & saleType === 2"
                class="filter-btn"
              >
                미판매
              </button>
              <button v-on:click="selectNoSales" v-else class="filter-btn2">
                미판매
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
                style="display: flex;                  
                justify-content: right;
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


        <NFTCard  v-for="(card, index) in cards" :key="index" :card="card" class="fl" />

      </div>
      <br />
    </div>
  </div>
</template>

<script>
import NFTCard from "@/components/resell/NFTCard.vue"

export default {
  name: "ResellListView",
  components: {
    NFTCard,
  },
  data() {
    return {
      // orderType: 0,
      artist: true,
      cards: [],
      orderType: 1,
      saleType: 1,
      keyword: "",
    }
  },
  created() {
    this.getMarket()
  },
  methods: {
    selectNewest() {
      this.orderType = 1
      this.saleType = 1
      this.getMarket()
    },
    selectHighAmmount() {
      this.orderType = 2
      this.saleType = 1
      this.getMarket()
    },
    selectLowAmmount() {
      this.orderType = 3
      this.saleType = 1
      this.getMarket()
    },
    selectNoSales() {
      this.orderType = 1
      this.saleType = 2
      this.getMarket()
    },
    searchEvents(){
      this.getMarket()
    },
    async getMarket() {
      const orderType = this.orderType
      const saleType = this.saleType
      const keyword = this.keyword
      const payload = {
        orderType,
        saleType,
        keyword,
      }
      await this.$store.dispatch("getMarket", payload)
      this.cards = this.$store.mcards
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
