<template>
  <div class="anim-entire">
    <div class="anim-h1 font-n">
        <div>
            <span class="anim-span fanftasy-nft">NFT</span>
            <span class="anim-span">를&nbsp;</span>
            <span class="anim-span">통해&nbsp;</span>
            <span class="anim-span">팬과&nbsp;</span>
            <span class="anim-span">아티스트가&nbsp;</span>
            <span class="anim-span">하나되는&nbsp;</span>
            <span class="anim-span">세상&nbsp;</span>
        </div>
        <div class="main-title-fanftasy" style=" text-align: end; ">
          FANFTASY
        </div>
      </div>
    <div class="banner-container">
      <img :src="require('@/assets/nft_main(1).jpg')" alt="nft예시1" style="width:200px" class="nft-banner"/>
      <img :src="require('@/assets/nft_main(2).jpg')" alt="nft예시2" style="width:200px" class="nft-banner"/>
      <img :src="require('@/assets/nft_main(3).jpg')" alt="nft예시3" style="width:200px" class="nft-banner"/>
      <img :src="require('@/assets/nft_main(4).jpg')" alt="nft예시4" style="width:200px" class="nft-banner"/>
      <img :src="require('@/assets/nft_main(5).jpg')" alt="nft예시5" style="width:200px" class="nft-banner"/>
    </div>
  </div>
</template>
<script>
export default {
  name: "animMain",

}
</script>

<style>
.anim-entire{
  display:flex; 
  justify-content: space-between; 
  align-items: center;
  width: 1040px;
  height: 400px;
}

.anim-h1 {
  /* font-family: kern; */
  max-width: 40ch;
  text-align: center;
  transform: scale(0.94);
  animation: scale 3s forwards cubic-bezier(0.5, 1, 0.89, 1);
}
@keyframes scale {
  100% {
    transform: scale(1);
  }
}

.anim-span {
  display: inline-block;
  font-size: 45px;
  opacity: 0;
  filter: blur(4px);
}

.anim-span:nth-child(1) {
  animation: fade-in 0.8s 0.1s forwards cubic-bezier(0.11, 0, 0.5, 0);
}

.anim-span:nth-child(2) {
  animation: fade-in 0.8s 0.2s forwards cubic-bezier(0.11, 0, 0.5, 0);
}

.anim-span:nth-child(3) {
  animation: fade-in 0.8s 0.3s forwards cubic-bezier(0.11, 0, 0.5, 0);
}

.anim-span:nth-child(4) {
  animation: fade-in 0.8s 0.4s forwards cubic-bezier(0.11, 0, 0.5, 0);
}

.anim-span:nth-child(5) {
  animation: fade-in 0.8s 0.5s forwards cubic-bezier(0.11, 0, 0.5, 0);
}

.anim-span:nth-child(6) {
  animation: fade-in 0.8s 0.6s forwards cubic-bezier(0.11, 0, 0.5, 0);
}
 
.anim-span:nth-child(7) {
  animation: fade-in 0.8s 0.7s forwards cubic-bezier(0.11, 0, 0.5, 0);
}


@keyframes fade-in {
  100% {
    opacity: 1;
    filter: blur(0);
  }
}

.fanftasy-nft{
  background: linear-gradient(90deg,#6A3FC1,#5B9BD5);
  -webkit-text-fill-color: transparent;
  background-clip: text;
  animation: color-change 3.0s linear forwards;
  background-size: 100% 100%;
}

.main-title-fanftasy{
    background: linear-gradient(to right, #ab6bff,#9ad5f9 15%,#575df0 35%,#ab6bff 50%,#9ad5f9 65%,#575df0 85%,#ab6bff);
    -webkit-text-fill-color: transparent;
    background-clip: text;
    animation: color-change 3.0s linear forwards;
    background-size: 100% 100%;
    font-size: 60px;
}

@keyframes color-change{
    0% {
        background-position: 0;
        opacity: 0;
        filter: blur(4px);
    }
    100% {
        background-position: 100%;
        opacity: 1;
        filter: blur(0);
    }
}


.banner-container{
  width: 200px;
  margin-left: 20x;
  position:relative;
}

.nft-banner{
  border-radius: 15px;
  top: 0px;
}

.nft-banner:nth-child(1){
  position:absolute;
  animation: fade-banner1 50s  infinite;
}
@keyframes fade-banner1 {
  0%{
    opacity: 1;
  }
  20%{
    opacity: 0;
  }
  40%{
    opacity: 0;
  }
  60%{
    opacity: 0;  
  }
  80%{
    opacity: 0;
  }
  100%{
    opacity: 1;
  }
}

.nft-banner:nth-child(2){
  position:absolute;
  animation: fade-banner2 50s  infinite;
}
@keyframes fade-banner2 {
  0%{
    opacity: 0;
  }
  20%{
    opacity: 1;
  }
  40%{
    opacity: 0;
  }
  60%{
    opacity: 0;  
  }
  80%{
    opacity: 0;
  }
  100%{
    opacity: 0;
  }
}


.nft-banner:nth-child(3){
  position:absolute;
  animation: fade-banner3 50s  infinite;
}
@keyframes fade-banner3 {
  0%{
    opacity: 0;
  }
  20%{
    opacity: 0;
  }
  40%{
    opacity: 1;
  }
  60%{
    opacity: 0;  
  }
  80%{
    opacity: 0;
  }
  100%{
    opacity: 0;
  }
}


.nft-banner:nth-child(4){
  position:absolute;
  animation: fade-banner4 50s infinite;
}
@keyframes fade-banner4 {
  0%{
    opacity: 0;
  }
  20%{
    opacity: 0;
  }
  40%{
    opacity: 0;
  }
  60%{
    opacity: 1;  
  }
  80%{
    opacity: 0;
  }
  100%{
    opacity: 0;
  }
}

.nft-banner:nth-child(5){
  animation: fade-banner5 50s  infinite;
}
@keyframes fade-banner5 {
  0%{
    opacity: 0;
  }
  20%{
    opacity: 0;
  }
  40%{
    opacity: 0;
  }
  60%{
    opacity: 0;  
  }
  80%{
    opacity: 1;
  }
  100%{
    opacity: 0;
  }
}

</style>