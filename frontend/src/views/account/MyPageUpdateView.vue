<template>
  <div class="update-container">
    <div style="flex-direction: column;">
      <div class="profile-update" @mouseover="this.isHovered = true" @mouseout="this.isHovered = false">
        <label for="uploadImg">
          프로필 사진
          <div class="profile-cover"></div>
          <div class="profile-img-upload-btn">
            <img :src="this.profileImg" style="border-radius: 50%; width: 184px; height: 184px; object-fit: fill;" alt="no">
            <v-icon v-show="this.isHovered === true" icon="mdi-fountain-pen-tip" style="z-index: 3; position: absolute;"></v-icon>
          </div>
        </label>
        <input id="uploadImg" type="file" ref="fileInput" @change="uploadFile">
      </div>
      
      
      <br>
      <br>
      <br>
      <br>

      <div>
        <label for="nickname">닉네임</label>
        <div>
          <input v-model="this.$store.state.nickname" id="nickname" type="text" placeholder="닉네임을 적어주세요">
        </div>
      </div>

      <!-- <div>
        <label for="bio">자기 소개</label>
        <div>
          <textarea id="bio" type="text" maxlength="140" placeholder="자신을 소개해주세요!"></textarea>
        </div>
      </div> -->
      <br>
      <div>
        <button class="edit-profile-confirm" @click="confirmEditProfile">수정</button>
        <router-link to="/mypage" style="text-decoration: none;">
          <button  class="edit-profile-cancel">취소</button>
        </router-link>
      </div>

    </div>

  </div>
</template>

<script>
// import axios from 'axios';
import  VueCookies  from 'vue-cookies';

export default {
  name: 'MyPageUpdateView',
  data(){
    return {
      userData : [],
      file: null,
      profileImg: VueCookies.get('profileImage'),
      isHovered: false,
      
    }
  },
  created() {
    this.getUserDetail();
  },
  mounted() {
    // this.getUserDetail();  
  },
  methods: {
    uploadFile() {
      console.log('이미지 바꿉니다')
      this.$store.profileImg = this.$refs.fileInput.files[0];
      this.$store.dispatch('modiUserImg')
    },
    
    confirmEditProfile(){
      this.$store.dispatch('modiUserInfo')
      // this.$router.go(-1)
    },

    async getUserDetail(){
      try{
        const result = await this.$store.dispatch('userDetail')

        console.log("result : " + result)        
      }
      catch (err){
        console.log(err)
      }
    },

    
    
  }
}
</script>

<style>
.update-container{
  display: flex;
  justify-content: center;
  padding-top: 90px;
}
.profile-update{
  height: 154px;
  position: relative;
}

/* #uploadImg{
  display: none;
} */

#nickname{
  border: 1px solid rgb(218,210,233);
  padding: 10px;
  border-radius: 15px;
}
#bio{
  border: 1px solid rgb(218,210,233);
  padding: 10px;
  border-radius: 15px;

}

.profile-img-upload-btn{
  width: 154px;
  height: 154px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 0px 5px 5px gray; 
}
.profile-cover{
  position: absolute;
  /* display: flex; */
  justify-content: center;
  align-items: center;
  width: 154px;
  height: 154px;
  border-radius: 50%;
  opacity: 0;
}
.profile-update:hover .profile-cover{
  opacity: 1;
  width: 154px;
  height: 154px;
  border-radius: 50%;
  background-color: rgba(0, 0, 0, 0.4);
}

.update-container{
  padding-top: 90px;
  display: flex;
  justify-content: center;
  min-height: 90vh;
}

.edit-profile-confirm{
  background-color: rgb(106, 63, 193);
  color: white;
  font-size: 24px;
  width:100px;
  height: 40px;
  border-radius: 15px;
}
.edit-profile-cancel{
  background-color: rgb(217, 217, 217);
  color: rgb(51,51,51);
  font-size: 24px;
  width:100px;
  height: 40px;
  border-radius: 15px;
}
</style>