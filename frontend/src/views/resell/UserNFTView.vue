<template>
    <div style="margin-top:100px">
        {{this.$route.params.nftId}}
    </div>
</template>

<script>
export default{
    data(){
        return {
            
        }
    },
    props: {
        // nftId: '',
    },
    mounted(){
        console.log(this.$route.params.nftId)
    }
}
</script>

<style>

</style>