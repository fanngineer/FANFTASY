<template>
  <div style="text-align: center;">
    <div class="nft-card-class">
      <!-- {{ card }} -->
      <router-link :to="{ name: 'MarketDetailView', params: { id: card.nftSourceId }}">
        <v-card class="nft-card">      
          <v-card-title class="nft-card-title">
            <v-img v-if="card.fileType === 'image'" :src="card.fileCID" alt="" class="nft-img"></v-img>
            <video v-if="card.fileType === 'video'" :src="card.fileCID" alt="" class="nft-img" ref="videoPlayer" @mouseover="playVideo" @mouseout="stopVideo" muted></video>
            <audio v-if="card.fileType === 'audio'" controls :src="card.fileCID" alt="오디오" class="nft-img"></audio>
          </v-card-title>

          <div style="width: 220px;">
            <v-card-item class="nft-card-item">
              <p class="item-name">
                {{ card.title }}  
              </p>
              <p class="item-content">
                <span style="font-size: 12px;">
                  by
                </span>
                <span>
                  {{ card.regArtist.nickname }}
                </span>
              </p>
              <p class="item-content">
                {{ card.originPrice }} FTS
              </p>
              <p class="item-content">
                {{card.remainNum}} / {{ card.totalNum }}개
              </p>
            </v-card-item>
          </div>
        </v-card>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "NFTCard",
  data(){
    return {
      isPlaying: false,
      videoElement: null
    }
  },
  props: {
    card: Object
  },
  methods: {
    
    playVideo() {
      this.videoElement = this.$refs.videoPlayer;
      this.videoElement.addEventListener('ended', () => {
        this.videoElement.currentTime = 0;
        this.videoElement.play();
      });
      this.videoElement.play();
      this.isPlaying = true;
    },
    stopVideo() {
      this.videoElement.pause();
      this.isPlaying = false;
    }
  }
}
</script>

<style>
.nft-card {
  display: flex;
  float: left;
  /* width: 413px; */
  /* height: 245px; */
  width: 245px;
  height: 360px;
  border: solid 1px #6a3fc1;
  margin: 20px 20px;
  border-radius: 15px;
  flex-direction: column;
  padding-top: 10px;
  align-items: center;
}

.nft-img {
  border-radius: 15px;
  /* border: solid 1px #D9D9D9; */
  width: 164px;
  height: 222px;
  margin-top: auto;
  margin-bottom: auto;
  margin-left: 10px;
}

.nft-img :hover{
  transition: 0.5s;
  border-radius: 15px;
  transform: rotateX(-25deg) rotateY(25deg);
}
.nft-img :not(:hover){
  transition: 0.5s;
  border-radius: 15px;
  transform: rotateX(0deg) rotateY(0deg);
}

.item-name{
  color: #6A3FC1;
  text-align: left;
  font-size: 16px;
  font-weight: bolder;
  
  /* 제목 말줄임표 CSS */
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.item-content{
  text-align: left;
  margin-left: 1vw;
  font-size: 14px;
}

.nft-card-class{
  display: inline-block;
  text-align: center;
  min-width: 100% ;
}

.nft-card-title{
  padding: 0;
  display: flex;
  justify-content: center;
  position: relative;
  margin-top: 10px;
}

.nft-card-item {
  font-size:larger;
  /* vertical-align: center; */
  /* text-align: center; */
  /* text-align: start; */
}


</style>