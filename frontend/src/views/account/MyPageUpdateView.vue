<template>
  <div class="update-container">
    <div style="flex-direction: column;">
      <div class="profile-update">
        <label for="uploadImg">
          <div class="profile-cover"></div>
          <div class="profile-img-upload-btn">
            <v-icon icon="mdi-fountain-pen-tip"></v-icon>
          </div>
        </label>
        <input id="uploadImg" type="file" ref="fileInput" @change="uploadFile">
      </div>
      
      <img :src="this.$store.profileImg" alt="no">
      

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

export default {
  name: 'MyPageUpdateView',
  data(){
    return {
      userData : [],
      file: null,
      
    }
  },
  mounted() {
    // this.getUserDetail();
  },
  methods: {
    uploadFile() {
      // console.log('이미지 바꿉니다')
      this.$store.profileImg = this.$refs.fileInput.files[0];
      // console.log('파일입니다 =>',this.file)
      // const formData = new FormData();
      // formData.append('profileImg', this.file);

      // for (var key of formData.keys()){
      //   console.log('key:',key)
      // }
      // for (var vale of formData.values()){
      //   console.log('vale :',vale)
      // }
      // // this.$store.state.nickname = 
      // this.$store.state.profileImg = formData

      // axios.post('/api/upload', formData, {
      //   headers: {
      //     'Content-Type': 'multipart/form-data'
      //   }
      // })
      // .then(response => {
      //   console.log(response.data);
      // })
      // .catch(error => {
      //   console.log(error);
      // });
      // console.log('엥?', formData);
      // console.log(this.$store.state.profileImg)
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
        const nickname = this.$store.state.nickname2;
        const a = this.$store.state.name;
        const c = document.getElementById('nickname')
        const b= this.$store.state.profileImg;
        console.log('안녕', a, b, nickname);
        c.placeholder = nickname
        // if (this.$store.state.success){
        // }
      }
      catch (err){
        console.log(err)
      }
      // finally{

      // }
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
  height: 184px;
  position: relative;
}

#uploadImg{
  /* display: none; */
}

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
  width: 184px;
  height: 184px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 0px 5px 5px gray; 
}
.profile-cover{
  position: absolute;
  width: 184px;
  height: 184px;
  border-radius: 50%;
  opacity: 0;
}
.profile-update:hover .profile-cover{
  opacity: 1;
  width: 184px;
  height: 184px;
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