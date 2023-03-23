<template>
  <div class="df cen">
    <div class="cen">
      <v-img
        src="@/assets/coin_hoyoen.png"
        alt=""
        class="nft-detail-img"
      ></v-img>
    </div>
    <div>
      <div class="nft-detail-box1">
        <br />
        <h1>노려보는 호연</h1>
        <br />
        <h4>코딩 하며 잘 안풀릴 때 부르면 "노려보는 호연"이 나온다</h4>
        <br />
        <br />
        <div class="sb">
          <span class="ib">
            <v-img
              src="@/assets/심호연.png"
              alt="호연"
              class="nft-detail-small-img fl"
            ></v-img>
            <p>Artist</p>
            <p>H.Y Shim</p>
          </span>
          <span class="ib">
            <v-img
              src="@/assets/photo.png"
              alt="사진타입"
              class="nft-detail-small-img fl"
            ></v-img>
            <p>Type</p>
            <p>Photo</p>
          </span>
          <span class="ib">
            <v-img
              src="@/assets/Heart_Icon.png"
              alt=""
              class="nft-detail-small-img fl"
            ></v-img>
            <p style="margin-top: 12px">132</p>
            <br />
          </span>
        </div>
        <br />
        <br />
      </div>

      <div class="nft-detail-box">
        <div class="sa">
          <span class="ib" style="margin-top: 30px;">
            <p class="nft-item-name">판매가격</p>
            <p class="nft-item-content">1.03NFN</p>
          </span>
          <span class="ib" style="margin-top: 30px;">
            <p class="nft-item-name">잔여수량</p>
            <p class="nft-item-content">19/50</p>
          </span>
        </div>
        <button class="purchase-btn">구매하기</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {}
</script>

<style>
.nft-detail-img {
  width: 372px;
  height: 504px;
  margin-right: 166px;
  float: left;
}

.nft-detail-small-img {
  width: 40px;
  height: 40px;
  margin-right: 10px;
  margin-top: 3px;
}

.sa {
  display: flex;
  justify-content: space-around;
}

.sb {
  display: flex;
  justify-content: space-between;
}

.ib {
  display: inline-block;
}

.nft-detail-box {
  width: 495px;
  height: 235px;
  background: #f2f3f6;
  border-radius: 20px;
  /* text-align: center; */
  /* display: flex; */
  /* justify-content: space-around; */
}

.nft-detail-box1 {
  width: 445px;
  height: 235px;
  margin-left: 10px;
}

.purchase-btn {
  display: flex;
  /* right: 10px; */
  width: 415px;
  height: 70px;
  font-size: 36px;
  font-weight: bold;
  justify-content: center;
  align-items: center;
  border-radius: 15px;
  color: white;
  margin-top: 50px;
  margin-left: auto;
  margin-right: auto;
  position: relative;
  background-color: RGB(106, 63, 193);
}

.nft-item-name {
  color: #7780a1;
  text-align: center;
  font-size: 14px;
}

.nft-item-content {
  text-align: center;
  /* margin-left: 1vw; */
  font-size: 24px;
  font-weight: bold;
}

.df {
  display: flex;
}

.fl {
  float: left;
}

.cen {
  justify-content: center;
}
</style>
