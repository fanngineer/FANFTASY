<template>
    <h1 class="anim-h1">
        <div>
            <span class="anim-span">NFT를&nbsp;</span>
            <span class="anim-span">통해&nbsp;</span>
            <span class="anim-span">팬과&nbsp;</span>
            <span class="anim-span">아티스트가&nbsp;</span>
            <span class="anim-span">하나되는&nbsp;</span>
            <span class="anim-span">세상&nbsp;</span>
        </div>
        <div class="main-title-fanftasy" style=" font-size: 40px; text-align: end;">
          FANFTASY
        </div>
    </h1>
    
</template>
<script>
export default {
  name: "animMain",

}
</script>

<style>

.anim-h1 {
  font-family: "Kern";
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

.main-title-fanftasy{
    background: linear-gradient(90deg,#ab6bff,#9ad5f9 15%,#575df0 35%,#ab6bff 50%,#9ad5f9 65%,#575df0 85%,#ab6bff);
    -webkit-text-fill-color: transparent;
    background-clip: text;
    animation: color-change 3.0s linear forwards;
    background-size: 200% 100%;
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
</style>