<template>
  <div class="market-detail-entire">
    <div class="cen" style="width:fit-content;">
      
      <div style="display: inline-flex; justify-content: center;">
        
        <!-- 이미지, 기타 정보 세로로 담는 div -->
        <div style="margin-right:10px; margin-bottom: 10vh;">
          
          <!-- 이미지 -->
          <div class="cen" style="height: 500px; ">
            <v-img
              :src="this.card.data.fileCID"
              alt=""
              class="nft-detail-img"
            ></v-img>
          </div>
          
          
          
          <div style="width: 500px; white-space:pre-line; margin:20px">
            <p style="font-size: 20px; font-weight:1000; text-indent: -15px;">상세 정보</p> 
            <p style="font-size: 14px; font-weight:500">
              {{ this.card.data.content }}
              <!-- {{ this.card.data }} -->
            </p>
          </div>
          <hr>
          <div style="width: 500px; margin:20px">
            <p style="font-size: 20px; font-weight:1000; text-indent: -15px;">NFT 정보</p>
            <div>
              <p>소유자 : {{ this.card.data.regArtist.nickname }}</p>
              <p>블록체인 : </p>
              <p>컨트랙트 주소 : </p>
              <p>토큰 표준 : </p>
            </div>
          </div>
          <hr>
          <div style="width: 500px; margin:20px">
            <p style="font-size: 20px; font-weight:1000; text-indent: -15px;">거래 정보</p>
            <p>
              <table>
                <thead>
                  <tr>
                    <th >거래일시</th>
                    <th >NFT 에디션 번호</th>
                    <th >가격</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(log, index) in tradeLog" :key="index"> 
                    <td>{{ log.date }}</td>
                    <td>{{ log.edition }}</td>
                    <td>{{ log.price }}</td>
                  </tr>
                </tbody>
              </table>
            </p>
          </div>
          <hr>
        </div>
        
        <!-- nft 기본 정보 박스(우측) -->
        <div class="detail-parent">
          <div class="nft-detail-parent">
              <div class="nft-detail-box1">
                <br />

                <h1>{{ this.card.data.title }}</h1>
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
                    <p>{{ this.card.data.regArtist.nickname }}</p>
                  </span>
                  <span class="ib">
                    <v-img
                      src="@/assets/photo.png"
                      alt="사진타입"
                      class="nft-detail-small-img fl"
                    ></v-img>
                    <p>Type</p>
                    <p>{{ this.card.data.fileType }}</p>
                  </span>
                  <span class="ib">
                    <v-img
                      src="@/assets/Heart_Icon.png"
                      alt=""
                      class="nft-detail-small-img fl"
                    ></v-img>
                    <p style="margin-top: 12px">{{this.card.data.likeNum}}</p>
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
      </div>
  
      <!-- 주의사항 -->
      <div style="display: flex; justify-content: center; margin-bottom: 60px;">
          <ui>
            <span style="font-weight: bold; font-size: 24px; background: linear-gradient(to right, #6A3FC1, #5B9BD5); color: transparent; -webkit-background-clip: text; margin: 15px;" >
              FANFTASY 유의사항
            </span>
            <li>
              구매는 메타마스크 계좌 내 판타지(FTS)가 차감되는 방식으로 진행됩니다.
            </li>
            <li>
              구매 거래가 체결되면 거래 취소가 불가하므로 신중하게 결정하여 진행해주세요.
            </li>
            <li>
              거래가 완료되면 해당 금액은 메타마스크 내 거래 가능 금액에서 제외됩니다.
            </li>
            <li>
              메타마스크 계좌 내 계좌에서 판타지(FTS)가 차감되어 거래가 체결됩니다.
            </li>
            <li>
              NFT의 외부 입출금은 현재 지원하지 않습니다. 
            </li>
            <li>
              해당 NFT에 연계된 디지털상품 관련 분쟁 (지식재산권 분쟁 포함)이 발생한 경우 해당 NFT에 대한 거래지원이 종료될 수 있습니다.
            </li>
            <li>
              NFT 소유자는 NFT와 연계된 “디지털 저작물“을 비상업적인 용도로 사용<br>
              (개인 SNS 내 업로드하는 행위 및 디지털 저작물 원본 그대로 개인적 용도로 사용하는 행위)<br>
              할 수 있는 권리와 NFT 입출금, 거래 등이 지원되는 경우 지원되는 방식에 따라 NFT의 소유권을 타인에게 이전할 수 있는 권리를 갖습니다.
            </li>
          </ui>
      </div>
  
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return{
      tradeLog : [
        {date:'2023-03-27 17:28', edition:'#2', price:'1.25 FTS'},
        {date:'2023-03-27 17:28', edition:'#2', price:'1.25 FTS'},
        {date:'2023-03-27 17:28', edition:'#2', price:'1.25 FTS'},
      ],
      NFTId: this.$route.params.id,
      card: [],
    }
  },
  created() {
    this.getDropsDetail()
  },
  methods: {
    async getDropsDetail() {
      
      await this.$store.dispatch("getDropsDetail", this.NFTId )
      // console.log(this.card)
      this.card = this.$store.card
      // console.log(this.card)
      // console.log("123456789")
    },
  },

}
</script>

<style>
.market-detail-entire{
  display: flex; 
  justify-content: center;
  margin-top: 100px;
}

.detail-parent {
  /* height: 1400px; */
  margin-bottom: 10vh;
}


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

/* nft 정보 상자 css */
.nft-detail-parent{
  position:sticky; 
  top: 100px;  
  /* height: 480px;  */
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

li{
  padding-left: 30px; 
  text-indent: -20px;
  margin-bottom: 5px;
}

</style>
