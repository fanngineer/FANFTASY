<template>
  <div style="text-align: center;">
    <div class="nft-card-class">
      <router-link :to="{ name: 'MarketDetailView', params: { id: 1 }}">
        <v-card class="nft-card">      
          <v-card-title class="nft-card-title">
            <v-img src="@/assets/coin_hoyoen.png" alt="" class="nft-img"></v-img>
          </v-card-title>
          <v-card-item class="nft-card-item">
            <p class="item-name">
              아티스트
            </p>
            <p class="item-content">
              심호연
            </p>
            <p class="item-name">
              제목
            </p>
            <p class="item-content">
              노려보는 호연
            </p>
            <p class="item-name">
              가격
            </p>
            <p class="item-content">
              1.03FAN
            </p>
            <p class="item-name">
              잔여수량
            </p>
            <p class="item-content">
            19/50
            </p>
          </v-card-item>
        </v-card>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "NFTCard",

}
</script>

<style>
.nft-card {
  display: flex;
  float: left;
  width: 413px;
  height: 245px;
  border: solid 1px #6a3fc1;
  margin: 20px 20px;
  border-radius: 15px;
}

.nft-img {
  border-radius: 15px;
  border: solid 1px #D9D9D9;
  width: 164px;
  height: 222px;
  margin-top: auto;
  margin-bottom: auto;
  margin-left: 10px;
}

.item-name{
  color: #6A3FC1;
  text-align: left;
  font-size: 15px;
  font-weight: bolder;
}

.item-content{
  text-align: left;
  margin-left: 1vw;
  font-size: 15px;
}

.nft-card-class{
  display: inline-block;
  text-align: center;
}

.nft-card-title{
  padding: 0;
  display: flex;
  justify-content: center;
  position: relative;
}

.nft-card-item {
  font-size:larger;
  vertical-align: center;
  text-align: center;
}
</style>