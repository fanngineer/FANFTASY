<template>
  <div class="mypage-entire">
    <div>
      <!-- 프로필 이미지, 메타마스크 주소, NFT 충전 버튼 -->
      <div style="display: flex; justify-content: center; width: 800px;">
        <div>
          <!-- 프로필 이미지 -->
          <div class="circle1">
            <div class="circle2">
              <div class="circle3">
                <div class="profile-update">
                  <img :src="this.userData.profileImg" style="border-radius: 50%; width: 154px; height: 154px; object-fit: fill;" alt="프로필사진" />
                </div>
              </div>
            </div>
          </div>
          
          <div style="margin: 10px; font-weight: bold; font-size: 32px;">
            <span>
              {{ this.userData.nickname }} 님
            </span>
          </div>
        </div>
      </div>
      <br>
      <br>
      <div class="mypage-filter-tab" >
        <p style="font-size: 30px;">
          보유 NFT
        </p>
        
      </div>
  
      <hr style="width:100%">
  
      <!-- 메뉴 콤보박스 -->
      <!-- <div style="display: flex; justify-content: end; margin-top: 10px;">
        <div style="margin-left:14px; width:200px; border-radius: 50% !important;">
          <div>
            <select  class="input-category input font-medium" v-model="selected" name="" id="" @change="this.onItemSelected()">
              <option value="1" selected>최신 순</option>
              <option value="2">이름 순 : A→Z</option>
              <option value="3">이름 순 : Z→A</option>
            </select>
          </div>
        </div>
      </div> -->

      
      
      <v-container style="width:800px">
        <v-row>
          <v-col v-for="(nft, index) in userNFTs" :key="index" cols="12" md="3">
            <div class="own-nfts-card">
              <router-link :to="{name:'MarketDetailView', params:{id: nft.nftSource.nftSourceId}}" style="min-width:164px; text-decoration: none; color:black; display:flex; flex-direction: column; justify-content: space-between;">
                <div>
                  <!-- <img :src="nft.nftSource.fileCID" alt="no" class="nft-img-owned"> -->
                  <v-img v-if="nft.nftSource.fileType === 'image'" :src="nft.nftSource.fileCID" alt="이미지" class="nft-img-owned"></v-img>
                  <video v-if="nft.nftSource.fileType === 'video'" :src="nft.nftSource.fileCID" alt="비디오" class="nft-img-owned" ref="videoPlayer" @mouseover="playVideo" @mouseout="stopVideo" muted></video>
                  <audio v-if="nft.nftSource.fileType === 'audio'" controls :src="nft.nftSource.fileCID" alt="오디오" class="nft-img-owned"></audio>
                </div>
                <div style="margin-left: 5px; margin-bottom: 15px">
                  <div class="item-name" style="font-size:20px;">
                    {{ nft.nftSource.title }}
                  </div>
                  <div style="font-size:15px">
                    by {{ nft.nftSource.regArtist.nickname }}
                  </div>
                  <!-- <div style="font-size:15px">
                    {{ nft.nftSource.originPrice }} FTS
                  </div> -->
                  <div style="font-size:15px">
                    <span>
                      #{{ nft.editionNum }}
                    </span>
                    <span style="font-size:10px">
                      edition
                    </span>
                  </div>
                  
                </div>
              </router-link>
            </div>
          </v-col>
        </v-row>
      </v-container>
      
      

    </div>
    

  </div>
</template>

<script>

export default {
  name: 'UserPageView',
  components: {
    
  },
  data(){
    return {
      userNFTs: [],
      userData : [],
      selected : 1,
    }
  },
  async created() {
    this.getOtherUserDetail();
  },
  methods:{
    async getOtherUserDetail(){
      try{
        await this.$store.dispatch('otherUserDetail', this.$route.params.id)
        this.userData = this.$store.state.otheruser.data
        this.userNFTs = this.userData.nftDetailDTOList
        console.log(this.userData)
      }
      catch (err){
        console.log(err)
      }
    },
  },
  watch() {

  },
  mounted() {
  },
  computed: {
  },
}
</script>

<style>

</style>
