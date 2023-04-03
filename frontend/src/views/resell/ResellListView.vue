<template>
  <br />
  <br />
  <div class="resell-entire">
    <div>
      <div class="nftbar-container">
        <div class="nftbar">
          <div class="market-space">
            거래중인 NFT
          </div>
          <div class="df filter-search-drops">
            <div>
              <button v-on:click="selectNewest" v-if="sortnum === 0" class="filter-btn"> 최신순 </button>
              <button v-on:click="selectNewest" v-else class="filter-btn2"> 최신순 </button>
              <button v-on:click="selectHighAmmount" v-if="sortnum === 2" class="filter-btn">높은가격순</button>
              <button v-on:click="selectHighAmmount" v-else class="filter-btn2">높은가격순</button>
              <button v-on:click="selectLowAmmount"  v-if="sortnum === 3" class="filter-btn">낮은가격순</button>
              <button v-on:click="selectLowAmmount" v-else class="filter-btn2">낮은가격순</button>
            </div>
            
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
          <hr>
          <br>
        </div>
      </div>
      <div style="text-align: center; padding-top: 168px;">
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
    </div>
    
  </div>
</template>

<script>
import NFTCard from "@/components/market/NFTCard.vue"

export default {
  name: 'ResellListView',

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
};

</script>

<style>
.resell-entire{
  display: flex;
  justify-content: center;
  padding-top: 22px;
}

</style>
