<template>
  <div class="div-ranking-card">
    <v-card class="ranking-card">      
      <v-img src="@/assets/뉴진스_logo.jpg" alt="" class="card-circle-img"></v-img>  
      <v-card-title style="padding: 0; display: flex; justify-content: center; position: relative;" >
        <v-img src="@/assets/뉴진스.png" alt="" class="card-img"></v-img>
      </v-card-title>
      <v-card-item style="font-size:larger; vertical-align: center; text-align: center; ">
        <br>
        New Jeans
      </v-card-item>
    </v-card>
    <v-card class="ranking-card">      
      <v-img src="@/assets/bts_logo.png" alt="" class="card-circle-img"></v-img>
      <v-img src="@/assets/방탄소년단.png" alt="" class="card-img"></v-img>
      <v-card-title style="padding: 0;">
      </v-card-title>
      <v-card-item style="font-size:larger; vertical-align: center; text-align: center; ">
        <br>
        BTS
      </v-card-item>
    </v-card>
    <v-card class="ranking-card">      
      <v-img src="@/assets/bigbang_logo.png" alt="" class="card-circle-img"></v-img>
      <v-img src="@/assets/빅뱅.jpg" alt="" class="card-img"></v-img>
      <v-card-title style="padding: 0;">
        
      </v-card-title>
      <v-card-item style="font-size:larger; vertical-align: center; text-align: center; ">
        <br>
        BIG BANG
      </v-card-item>
    </v-card>
    <v-card class="ranking-card">      
      <v-img src="@/assets/임영웅_logo.png" alt="" class="card-circle-img"></v-img>  
      <v-img src="@/assets/임영웅.jpg" alt="" class="card-img"></v-img>
      <v-card-title style="padding: 0;">
        
      </v-card-title>
      <v-card-item style="font-size:larger; vertical-align: center; text-align: center; ">
        <br>
        임영웅
      </v-card-item>
    </v-card>
    <v-card class="ranking-card">      
      <v-img src="@/assets/조민수.png" alt="" class="card-circle-img"></v-img>
      <v-img src="@/assets/minsu.jpg" alt="" class="card-img"></v-img>
      <v-card-title style="padding: 0;">
        
      </v-card-title>
      <v-card-item style="font-size:larger; vertical-align: center; text-align: center; ">
        <br>
        M.S.JO
      </v-card-item>
    </v-card>
  </div>
  <br>
</template>

<script>
export default {
  name: "RankingCard",

}
</script>

<style>
.div-ranking-card{
  display: flex;
  margin-left: 15vw;
}

.ranking-card{
  width: 208px;
  height: 284px;
  margin-right: 30px;
  /* border: solid white; */
  box-shadow: 0px 5px 5px gray;
}

.card-img {
  width: 206px;
  height: 206px;
  object-fit: cover;
  /* display: flex; */
  position: relative;
  /* z-index: 9; */
}
.card-circle-img {
  width: 50px;
  height: 50px;
  /* display: flex; */
  position: absolute;
  justify-content: center;
  text-align: center;
  vertical-align: middle;
  margin-top: 180px;
  margin-left: 78px;
  z-index: 9;
  border: 12px white !important;
  border-radius: 50%;
}
</style>