<template>
  <div class="market-detail-entire">
    <div class="cen" style="width:fit-content;">
      
      <div style="display: inline-flex; justify-content: center;">
        
        <!-- 이미지, 기타 정보 세로로 담는 div -->
        <div style="margin-right:10px; margin-bottom: 10vh;">
          
          <!-- 이미지 -->
          <div class="cen" style="height: 500px; ">
            <v-img
              v-if="this.card.data.fileType === 'image'"
              :src="this.card.data.fileCID"
              alt=""
              class="nft-detail-img"
            ></v-img>
            <iframe 
            v-if="this.card.data.fileType === 'video'" 
            :src="this.card.data.fileCID" 
            alt="" 
            class="nft-detail-img"
            autoplay="1"
            ></iframe>
            <audio v-if="this.card.data.fileType === 'audio'" controls :src="this.card.data.fileCID" alt="오디오" class="nft-img"></audio>
          </div>
          
          
          
          <div style="width: 500px; white-space:pre-line; margin:20px">
            <p style="font-size: 20px; font-weight:1000; text-indent: -15px;">상세 정보</p> 
            <p style="font-size: 14px; font-weight:500">
              {{ this.card.data.content }}
              <!-- {{ this.card }} -->
            </p>
          </div>
          <hr>
          <div style="width: 500px; margin:20px">
            <p style="font-size: 20px; font-weight:1000; text-indent: -15px;">NFT 정보</p>
            <div>
              <!-- <p>소유자 : {{ this.card.data.regArtist.nickname }}</p> -->
              <p class="df" style="justify-content: space-between;">
                  <span class="df">
                    소유자 : {{ this.card.data.regArtist.nickname }}
                  </span>
                  <span class="df" style="justify-content: end;">
                    <router-link v-if="this.card.data.regArtist.userId != this.userId" :to="{ name: 'userpageview', params: {id: this.card.data.regArtist.userId }}">정보 더보기</router-link>
                    <router-link v-else :to="{name: 'mypageview'}">정보 더보기</router-link>
                  </span>
                </p>
              <p>블록체인 : FANFTASY </p>
              <p>컨트랙트 주소 : 0xaeb2DC75a63352947Bdbe6d9E6A37c0752481007 </p>
              <p>토큰 표준 : ERC721 </p>
            </div>
          </div>
          <hr>
          <div style="width: 500px; margin:20px">
            <p style="font-size: 20px; font-weight:1000; text-indent: -15px;">거래 정보</p>
            <p style="justify-content: space-between">
              <table>
                <thead>
                  <tr class=""  style="justify-content: space-between; text-align: left; margin-left: 10px;">
                    <th >발매일시</th>
                    <th >발매 NFT 에디션 개수</th>
                    <th >가격</th>
                  </tr>
                </thead>
                <tbody style="text-align: left;">
                  <!-- <tr v-for="(log, index) in tradeLog" :key="index"> 
                    <td>{{ log.date }}</td>
                    <td>{{ log.edition }}</td>
                    <td>{{ log.price }}</td>
                  </tr> -->
                  <td>{{ this.card.data.regDate }}</td>
                  <td>{{ this.card.data.totalNum }}</td>
                  <td>{{ this.card.data.originPrice }}</td>
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
                <h4>{{ this.card.data.content }}</h4>
                <br />
                <br />
                <div class="sb">
                  <span class="ib">
                    <!-- <v-img
                      :src="this.card.data.regArtist.profileImg"
                      alt="프로필 사진"
                      class="nft-detail-small-img fl"
                    ></v-img> -->
                    <span style="width: 40px; height: 40px; border-radius: 50%; overflow: hidden; ">
                      <img
                      class="profile-image"
                      :src="this.card.data.regArtist.profileImg"
                      alt=""
                      style="object-fit: fill; width: 35px; height: 35px; border-radius: 50%; margin-right: 10px; margin-top: 8px;"
                      />
                    </span>
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
                      v-if="userId === null"
                      src="@/assets/Heart_Icon.png"
                      alt=""
                      class="nft-detail-small-img fl"
                    ></v-img>
                    <v-img
                      v-if="card.data.userLike===true & userId != null"
                      src="@/assets/Heart_Icon.png"
                      alt=""
                      class="nft-detail-small-img fl"
                      @click="unLike()"
                      style="cursor: pointer;"
                    ></v-img>
                    <v-img
                      v-if="card.data.userLike===false & userId != null"
                      src="@/assets/empty_Heart_Icon.png"
                      alt=""
                      class="nft-detail-small-img fl"
                      @click="Like()"
                      style="cursor: pointer;"
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
                    <p class="nft-item-content">{{this.card.data.originPrice}} FTS</p>
                  </span>
                  <span class="ib" style="margin-top: 30px;">
                    <p class="nft-item-name">잔여수량</p>
                    <p class="nft-item-content">{{this.card.data.remainNum}} / {{this.card.data.totalNum}}개</p>
                  </span>
                </div>
                <button class="purchase-btn" @click="buy()">구매하기</button>
              </div>
      
          </div>
        </div>
      </div>
  
      <!-- 주의사항 -->
      <div style="margin-bottom: 15px;">
          <ui>
            <span style="font-weight: bold; font-size: 24px; background: linear-gradient(to right, #6A3FC1, #5B9BD5); color: transparent; -webkit-background-clip: text; margin: 15px;" >
              FANFTASY 유의사항
            </span>
            <div style="font-size:14px; color: rgb(100,100,100);">
              <li style="color:#6A3FC1; font-weight: bold;">
                거래 수수료의 일부는 환경 보호를 위한 기금에 기부됩니다.
              </li>
              <li>
                구매는 메타마스크 계좌 내 에프티에스(FTS) 토큰이 차감되는 방식으로 진행됩니다.
              </li>
              <li>
                구매 거래가 체결되면 거래 취소가 불가하므로 신중하게 결정하여 진행해주세요.
              </li>
              <li>
                거래가 완료되면 해당 금액은 메타마스크 내 거래 가능 금액에서 제외됩니다.
              </li>
              <li>
                메타마스크 계좌 내 계좌에서 에프티에스(FTS) 토큰이 차감되어 거래가 체결됩니다.
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

            </div>
          </ui>
      </div>
  
    </div>
  </div>
</template>

<script>
import VueCookies from "vue-cookies"

export default {
  data(){
    return{
      // tradeLog : [
      //   {date:'2023-03-27 17:28', edition:'#2', price:'1.25 FTS'},
      //   {date:'2023-03-27 17:28', edition:'#2', price:'1.25 FTS'},
      //   {date:'2023-03-27 17:28', edition:'#2', price:'1.25 FTS'},
      // ],
      NFTId: this.$route.params.id,
      card: [],
      profile: VueCookies.get('profileImage'),
      userId: VueCookies.get('userId'),
      address: VueCookies.get('Account'),
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

    Like() {
      this.$store.dispatch("dropsLike", this.NFTId )
      this.card.data.userLike = true
      this.card.data.likeNum += 1
    },
    unLike() {
      this.$store.dispatch("dropsUnLike", this.NFTId )
      this.card.data.userLike = false
      this.card.data.likeNum -= 1
    },
    buy() {
      console.log(this.NFTId);
      this.$store.dispatch("getDropsNftId", this.NFTId )
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
  margin-bottom: 3px;
}

</style>
