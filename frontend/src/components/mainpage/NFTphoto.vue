<template>
    <div style="margin: 0 40px;">
        <div>
            <router-link :to="{name: 'MarketDetailView', params:{id: photo.nftSource.nftSourceId}}">
                <v-img v-if="photo.nftSource.fileType === 'image'" :src="photo.nftSource.fileCID" alt="이미지" class="photo-frame"></v-img>
                <video v-if="photo.nftSource.fileType === 'video'" :src="photo.nftSource.fileCID" alt="비디오" class="photo-frame" ref="videoPlayer" @mouseover="playVideo" @mouseout="stopVideo" muted></video>
                <audio v-if="photo.nftSource.fileType === 'audio'" controls :src="photo.nftSource.fileCID" alt="오디오" class="photo-frame"></audio>
            </router-link> 
        </div>
    </div>
</template>

<script>
export default {
    name: "NFTphoto",
    data(){
        return {
            isPlaying: false,
            videoElement: null,
        }
    },
    props: {
        photo: Object
    },
    methods:{
        playVideo() {
            this.videoElement = this.$refs.videoPlayer;
            this.videoElement.addEventListener('ended', () => {
                this.videoElement.currentTime = 0;
                this.videoElement.play();
            });
            console.log('this.videoElement', this.videoElement)
            console.log('플레이 비디오~~ playVideo!')
            this.videoElement.play();
            this.isPlaying = true;
        },
        stopVideo() {
            console.log('스탑 비디오~~')
            this.videoElement.pause();
            this.isPlaying = false;
        },
    }
}

</script>

<style>
.photo-frame{
    border-radius: 15px; 
    width: 300px; 
    height: 500px;
    object-fit: fill;
}
</style>