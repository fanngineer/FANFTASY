<template>
  <br />
  <br />
  <div>
    <div class="nftbar">
      <span style="display: inline-block">
        <h1 class="market-space">판매중인 NFT</h1>

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
          잔여량순
        </button>
        <button
          v-on:click="selectSalesVolume"
          v-else
          class="unselected-ranking-button"
        >
          잔여량순
        </button>
        <button
          v-on:click="selectHighAmmount"
          v-if="sortnum === 2"
          class="selected-ranking-button"
        >
          높은가격순
        </button>
        <button
          v-on:click="selectHighAmmount"
          v-else
          class="unselected-ranking-button"
        >
          높은가격순
        </button>
        <button
          v-on:click="selectLowAmmount"
          v-if="sortnum === 3"
          class="selected-ranking-button"
        >
          낮은가격순
        </button>
        <button
          v-on:click="selectLowAmmount"
          v-else
          class="unselected-ranking-button"
        >
          낮은가격순
        </button>
      </span>
      <label for="" class="box ib">
        
        <input type="text" class="notting search-input" placeholder="아티스트를 입력하세요"/>
        <button
          href=""
          class="notting"
          style="display: flex; justify-content: right; align-self: center"
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
    <div style="text-align: center;">
      <NFTCard v-for="i in 4" :key="i" class="nft-card-class"/>
      <br />
      <NFTCard v-for="i in 4" :key="i" class="nft-card-class"/>
      <br />
      <NFTCard v-for="i in 4" :key="i" class="nft-card-class"/>
      <br />
      <NFTCard v-for="i in 4" :key="i" class="nft-card-class"/>
      <br />
      <NFTCard v-for="i in 4" :key="i" class="nft-card-class"/>
    </div> 
    <br />
    <!-- <v-pagination :length="5"></v-pagination> -->
    <!-- <router-link :to="{ name: 'MarketDetailView', params: { id: 1 }}">
      <button v-if="artist===true" class="nft-add-btn">+ NFT 등록하기</button>
    </router-link> -->
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
      sortnum: 0,
      artist: true,
    }
  },
  methods: {
    selectNewest() {
      this.sortnum = 0
    },
    selectSalesVolume() {
      this.sortnum = 1
    },
    selectHighAmmount() {
      this.sortnum = 2
    },
    selectLowAmmount() {
      this.sortnum = 3
    },
  },
}
</script>

<style>
.nftbar {
  display: flex;
  justify-content: space-around;
  /* white-space: nowrap; */
  /* width: 100%; */
  overflow: hidden;
}

.market-space {
  display: flex;
  justify-content: left;
  /* margin-left: 7vw; */
  margin-right: 3vw;
  float: left;
}

.nft-add-btn {
  display: flex;
  /* right: 10px; */
  width: 246px;
  height: 67px;
  font-size: 24px;
  font-weight: bold;
  justify-content: center;
  align-items: center;
  border-radius: 15px;
  color: white;
  /* margin-top: 50px;
  margin-left: auto;
  margin-right: auto; */
  /* position: relative; */
  background-color: RGB(106, 63, 193);
}
</style>
