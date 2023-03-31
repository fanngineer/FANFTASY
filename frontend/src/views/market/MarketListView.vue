<template>
  <br />
  <br />
  <div class="market-entire">
    <div>
      <div class="nftbar-container">
        <div class="nftbar">
          <div class="market-space">
            판매중인 NFT
    
          </div>
          <div class="df filter-search-drops">
            <!-- <div style="display: inline-block">
              <button v-on:click="selectNewest" v-if="sortnum === 0" class="selected-ranking-button">최신순</button>
              <button v-on:click="selectNewest" v-else  class="unselected-ranking-button">최신순</button>
              <button v-on:click="selectSalesVolume" v-if="sortnum === 1" class="selected-ranking-button">잔여량순</button>
              <button v-on:click="selectSalesVolume" v-else class="unselected-ranking-button">잔여량순</button>
              <button v-on:click="selectHighAmmount" v-if="sortnum === 2" class="selected-ranking-button">높은가격순</button>
              <button v-on:click="selectHighAmmount" v-else class="unselected-ranking-button">높은가격순</button>
              <button v-on:click="selectLowAmmount"  v-if="sortnum === 3" class="selected-ranking-button">낮은가격순</button>
              <button v-on:click="selectLowAmmount" v-else class="unselected-ranking-button">낮은가격순</button>
            </div> -->
            
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
.market-entire{
  padding-top: 22px;
  display: flex;
  justify-content: center;
}

.nftbar-container{
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

.filter-search-drops{
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


.filter-btn2{
  width: 120px;
  height: 40px;
  display: inline-block;
  position: relative;
  font-weight: bolder;
}
.filter-btn2:after{
  border-top-right-radius: 10px;
  border-top-left-radius: 10px;
  position: absolute;
  content:"";
  bottom: -5%;
  display: block;
  background: #6a3fc1;
  width: 120px;
  animation: btn-click2 1.0s forwards;
  z-index: -1;
}
@keyframes btn-click2{
  0%{
    font-weight: bolder;
    /* opacity: 0.5; */
    height: 100%
  }
  100%{
    font-weight: bolder;
    opacity: 0;
    height: 0px;
  }
}
.filter-btn2:hover{
  font-weight: bolder;
  color: #6a3fc1;
}

.filter-btn{
  width: 120px;
  height: 40px;
  display: inline-block;
  position: relative;
  font-weight: bolder;
  color: white;
}
.filter-btn:after{
  border-top-right-radius: 10px;
  border-top-left-radius: 10px;
  position: absolute;
  content:"";
  bottom: -5%;
  display: block;
  background: #6a3fc1;
  width: 120px;
  animation: btn-click 1.0s forwards;
  z-index: -1;
}
@keyframes btn-click{
  0%{
    color: red;
    font-weight: bolder;
    opacity: 0;
    height: 0px;
  }
  100%{
    color: yellowgreen;
    font-weight: bolder;
    /* opacity: 0.5; */
    height: 100%;
  }
}


</style>
