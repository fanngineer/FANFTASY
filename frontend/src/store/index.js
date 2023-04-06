import { createStore } from "vuex"
import VueCookies from "vue-cookies"
import axios from "axios"
import createPersistedState from "vuex-persistedstate"
import router from "@/router"
import Swal from 'sweetalert2'
import SaleABI from "../../path/to/SaleABI.json";
import SaleFactoryABI from "../../path/to/SaleFactoryABI.json";
import Web3 from "web3"

const web3 = new Web3(window.ethereum);
const saleFactoryContractAddress = '0x0509b43FF9CcAC684ef00bc82020208b6F86d156';
const saleFactoryContract = new web3.eth.Contract(SaleFactoryABI, saleFactoryContractAddress);
const API_URL = "https://fanftasy.kro.kr/api"
// const API_URL = "http://70.12.247.102:8080/api"
// const API_URL = "http://192.168.91.150:8080/api"
// const API_URL = "http://70.12.247.124:8080/api"
// const API_URL = "http://70.12.246.214:8080/api"
// const API_URL = "http://localhost:8080/api",
const store = createStore({
  plugins: [createPersistedState()],
  data: {
    cards:[]
  },
  state: {
    CurrentAccount: null,
    // RefreshToken: null,
    AccessToken: VueCookies.get("AccessToken"),
    chainId: "0x538",
    rpcUrl: "https://fanftasy.kro.kr/network",
    currentChainId: null,
    userId: VueCookies.get("userId"),
    isFan: true,
    isMember: false,
    isLogIn: VueCookies.isKey("AccessToken"),
    isSame: false,
    name: null,
    nickname: VueCookies.get("nickname"),
    address: null,
    phone: null,
    role: null,
    email: null,
    company: null,
    totalPrice: null,
    totalSales: null,
    profileImg: null,
    orderType: 1,
    page: 0,
    keyword: "",
    success: false,
    haveNet: null,
    cards: [],
    card: [],
    userNFTs: [],
    userLikeDropsNFTs: [],
    userLikeMarketNFTs: [],
    mcards: [],
    mcard: [],
    otheruser:[],
    resellDetailNFTs:[],
    SaleContractAddress: null, 
    nftId: null, 
    price: null, 
    err: null,
  },
  getters: {
    isLogin: function () {
      return VueCookies.isKey("AccessToken")
    },
  },
  mutations: {
    async installedMetamask() {
      // metamask 확장자가 설치 되어있는지 확인하는 method
      // Check if Web3 has already been injected by MetaMask
      if (typeof window.ethereum !== "undefined") {
        console.log("설치되있음")
      } else {
        await Swal.fire({
          title: "METAMASK 필수",
          text: "저희 사이트는 METAMASK가 필수입니다.",
          icon: "info" //"info,success,warning,error" 중 택1
        })
        // alert("저희 사이트는 METAMASK가 필수입니다.")
        //METAMASK 설치 페이지가 새 창에 뜸
        window.open(
          "https://chrome.google.com/webstore/detail/metamask/nkbihfbeogaeaoehlefnkodbefgpgknn",
          "_blank"
        )
      }
    },
    
    LogOut() {
      this.state.CurrentAccount = null;
      this.state.AccessToken = null;
      this.state.address = null;
      // this.state.RefreshToken = null;
      VueCookies.remove("Account");
      VueCookies.remove("AccessToken");
      VueCookies.remove("nickname");
      VueCookies.remove("profileImage");
      VueCookies.remove("userId");
      // VueCookies.remove("RefreshToken");
      router.push('/')

      console.log("로그아웃");
    },
  },
  actions: {
    async changeNetWork() {
      const chain = await window.ethereum.request({ method: "eth_chainId" })
      console.log(chain)
      this.state.currentChainId = chain
      this.state.haveNet = false
      if (chain !== this.state.chainId) {
        await window.ethereum
          .request({
            method: "wallet_switchEthereumChain",
            params: [
              {
                chainId: this.state.chainId,
              },
            ],
          })
          .then((res) => {
            console.log(res)
            console.log(this.state.success)
            this.state.success = true
            this.state.haveNet = true
          })
          .catch((err) => {
            console.log(err)
            console.log(err.code)
            if (err.code === 4902) {
              this.state.haveNet = false
              this.state.success = true
            }
          })
        console.log("다름")
      } else {
        console.log("같음")
        this.haveNet = true
        this.state.success = true
      }
    },

    async addNetWork() {
      if (this.state.haveNet === false) {
        await window.ethereum
          .request({
            method: "wallet_addEthereumChain",
            params: [
              {
                chainId: this.state.chainId,
                chainName: "A306FANFTASY",
                rpcUrls: [this.state.rpcUrl],
                nativeCurrency: {
                  name: "FTS",
                  symbol: "FTS",
                  decimals: 18,
                },
              },
            ],
          })
          .catch((err) => {
            console.log(err)
          })
      }
    },

    async getAccount() {
      // 계정 불러오기
      this.state.CurrentAccount = (
        await window.ethereum.request({ method: "eth_requestAccounts" })
      )[0]
      this.state.address = this.state.CurrentAccount
    },

    async LOGIN() { // 우리 회원인지 확인하고, 회원이면 토큰을 받고, 비회원이면 404 에러
      this.commit('installedMetamask');
      await this.dispatch('changeNetWork');
      await this.dispatch('addNetWork');
      await this.dispatch('getAccount');
      this.state.isMember = false;
      // console.log(this.state.address);
      await axios({
        method: "post",
        url: `${API_URL}/user/login`,
        params: {
          address: this.state.address, //지갑 주소
        },
      })
      .then((res) => {
        console.log(res);
        console.log(res.headers.accesstoken);
        console.log(res.data.data);
        
        // console.log(this.state.profileImage);
        VueCookies.set('Account', res.data.data.address, '3h');
        VueCookies.set('userId', res.data.data.userId, '3h');
        VueCookies.set('nickname', res.data.data.nickname, '3h');
        VueCookies.set('AccessToken', res.headers.accesstoken, '3h');
        if(res.data.data.profileImg === null || res.data.data.profileImg === undefined) {
          VueCookies.set('profileImage', 'https://fanftasy.s3.ap-northeast-2.amazonaws.com/profileImg/8c64c983-1b80-40fb-bcc1-366f3322cbb2.png', '3h');
        }
        else {
          VueCookies.set('profileImage', res.data.data.profileImg, '3h');
        }
        this.state.isMember = !this.state.isMember;
        this.state.success = true;
      }) 
      .catch((error) => {
        
        console.log(error);
        // console.log(error.response.data.success);
        if(!error.response.data.success){ // 회원이 아닙니다.
          this.state.success = false;
          this.state.isMember = false;
        }
        else {
          this.state.success = false;
          this.state.isMember = null;
        }
        console.log(this.state.isMember);
      })
    },

    async sameAccount() {
      // 쿠키와 메타마스크의 현재 지갑 주소를 비교해서 같으면 true, 다르면 false
      await this.dispatch("getAccount")
      if (VueCookies.isKey("Account") === true) {
        // 이미 로그인을 했을 경우
        if (VueCookies.get("Account") === (await window.ethereum.request({ method: "eth_requestAccounts" }))[0]) {
          // 마지막으로 로그인 했을 때의 쿠키와 현재의 메타마스크에 연결되있는 계정 주소가 같을 경우
          this.state.isSame = true
          this.state.success = true
        } else {
          // 다르면 다시 로그인 절차를 밟아야 한다.
          this.state.isSame = false
          this.commit("LogOut")
          this.dispatch("LOGIN")
          this.state.success = false
          router.push('/')
        }
      } else {
        // 로그인이 안되어 있을 경우x`
        this.state.success = false
        router.push('/')
      }
    },

    async signup(context, payload) {
      console.log(this.state.address)
      console.log(this.state.CurrentAccount)
      await this.dispatch("changeNetWork")
      await this.dispatch("addNetWork")
      await axios({
        method: "post",
        url: `${API_URL}/user/join`,
        data: {
          address: this.state.CurrentAccount, //지갑 주소
          name: this.state.name,
          email : this.state.email, //이메일
          nickname: this.state.nickname, //닉네임
          phone: this.state.phone, //전화번호
          role: this.state.role, //아티스트 or 팬
          company: this.state.company, //소속사
          profileImg: "https://fanftasy.s3.ap-northeast-2.amazonaws.com/profileImg/8c64c983-1b80-40fb-bcc1-366f3322cbb2.png",
        },
      })
        .then((res) => {
          console.log(res)
          this.state.success = true
        })
        .catch((error) => {
          console.log(error)
          this.state.success = false
          Swal.fire({
            title: "회원가입 실패",
            text: "회원가입에 실패하였습니다. 올바른 양식을 입력해주세요",
            icon: "error" //"info,success,warning,error" 중 택1
          })
          router.go()
        })
    },

    async userDetail() {
      const address = (
        await window.ethereum.request({ method: "eth_requestAccounts" })
      )[0]
      console.log(address)
      await axios({
        method: "get",
        url: `${API_URL}/user/detail`,
        params: {
          address: address,
        },
      })
        .then((res) => {
          // console.log('res :', res)
          // console.log('res.data :', res.data)
          console.log('res.data.data : ', res.data.data)
          this.state.role = res.data.data.role
          this.state.nickname = res.data.data.nickname
          console.log(this.state.role)
        })
        .catch((err) => {
          console.log(err)
          Swal.fire({
            title: "실패",
            text: "로그인 해주세요.",
            icon: "error" //"info,success,warning,error" 중 택1
          })
        })
    },

    async otherUserDetail(context, uid) {
      await axios({
        method: "get",
        url: `${API_URL}/user/other/${uid}`,
      })
        .then((res) => {
          console.log('res.data.data : ', res.data)
          this.state.otheruser = res.data
        })
        .catch((err) => {
          console.log(err)
        })
    },

    async userNFTs(context, type){
      const uid = VueCookies.get("userId"); 
      console.log('uid :',uid)
      await axios({
        method: "get",
        url: `${API_URL}/nft/user/${uid}`,
        params: {
          type,
        }
      })
      .then((res)=>{
        console.log('userNFTs : ', res)
        this.userNFTs = res.data.data
      })
      .catch((err)=>{
        console.log(err)
        this.state.success = false
      })
    },

    async modiUserImg(){
      const id = VueCookies.get("userId");
      let formData = new FormData();
      formData.append('profileImg', this.profileImg);
      console.log('this.file = ',formData, this.profileImg)
      await axios({
        headers: {
          'Content-Type': 'multipart/form-data'
        },
        method: "post",
        url: `${API_URL}/user/profile/${id}`,
        data: formData,
      })
      .then((res)=>{
        VueCookies.set('profileImage', res.data.data, '3h');
        console.log(this.profileImg)
        console.log(res.data.data)
        this.state.success = true
        // this.$router.go(0)
        router.go(0)
      })
      .catch((err)=>{
        console.log(err)
        this.state.success = false
      })
    },


    async modiUserInfo() {
      const address = (
        await window.ethereum.request({ method: "eth_requestAccounts" })
      )[0]
      await axios({
        method: "put",
        url: `${API_URL}/user/modi`,
        data: {
          address: address,
          nickname: this.state.nickname,
        },
      })
        .then((res) => {
          VueCookies.set('nickname', this.state.nickname, '3h');
          console.log(res)
          this.state.success = true
          // router.go(-1);
          // router.push("/mypage")
          router.go(0)
        })
        .catch((err) => {
          console.log(err)
          this.state.success = false
        })
    },

    async getDrops(context, payload) {
      const orderType = payload.orderType
      const page = payload.page
      const keyword = payload.keyword
      console.log(payload)
      await axios({
        methos: "get",
        url: `${API_URL}/nft/drops`,
        params: {
          orderType: orderType,
          page: page,
          keyword: keyword,
        },
      })
        .then((res) => {
          this.cards = res.data.data
        })
        .catch((err) => {
          console.log(err)
        })
    },
    
    async getDropsDetail(context, NFTId) {
      const uid = VueCookies.get("userId")
      await axios({
        method: "get",
        url: `${API_URL}/nft/drops/${NFTId}`,
        params: {
          userId: uid
        }
      })
        .then((res) => {
          console.log(res)
          this.card = res.data
        })
        .catch((err) => {
          console.log(err)
        })
    },
    
    async getMarket(context, payload) {
      const orderType = payload.orderType
      const saleType = payload.saleType
      const keyword = payload.keyword
      console.log(payload)
      await axios({
        method: "get",
        url: `${API_URL}/nft/market`,
        params: {
          orderType: orderType,
          saleType: saleType,
          keyword: keyword,
        },
      })
      .then((res) => {
        this.mcards = res.data.data
          
        })
        .catch((err) => {
          console.log(err)
        })
    },
      
    async getMarketDetail(context, nftSourceId) {
      // const currentPrice = payload.currentPrice
      const uid = VueCookies.get("userId")
      await axios({
        method: "get",
        url: `${API_URL}/nft/market/${nftSourceId}`,
        params: {
          userId: uid,
        }
      })
        .then((res) => {
          console.log("123156453895125661548653468534")
          console.log(res)
          this.state.price = (res.data.data.currentPrice) * (10**18);
          this.state.nftId = res.data.data.nftId;
          this.state.SaleContractAddress = res.data.data.saleContract;
          this.mcard = res.data
          this.dispatch('getDropsNftId', res.data.data.nftSourceId)
        })
        .catch((err) => {
          console.log(err)
        })
      },

    addNFT(context, payload) {
      
      Swal.fire({
        title: "생성 중...",
        text: "NFT를 생성중입니다. 잠시만 기다려주세요",
        showConfirmButton: false,
      })

      const formData = new FormData();
      formData.append("file", payload.file);
      const ppayload = {
        title: payload.title,
        content: payload.content,
        totalNum: payload.totalNum,
        originPrice : payload.originPrice,
        regArtistId : payload.regArtistId,
        fileType : payload.fileType,
      }

      formData.append("info", JSON.stringify(ppayload));
      formData.append("endDate", payload.endDate);
      
      console.log(formData)
      axios({
        method: "post",
        url: `${API_URL}/nft`,
        headers:{'Content-Type':'Multipart/form-data'},
        data: formData
      })
      .then((res) => {
        console.log(res)
      })
      .then(()=>{
        Swal.fire({
          title: "성공",
          text: "NFT 생성이 완료되었습니다!",
          icon: "success" //"info,success,warning,error" 중 택1
        });
        router.go(-1)
      })
      .catch((err) => {
        console.log(err)
        Swal.fire({
          title: "실패",
          text: "NFT 생성이 실패하였습니다.",
          icon: "error" //"info,success,warning,error" 중 택1
        });
        router.go()
      })
    },

    dropsLike(context, nftSourceId) {
      const uid = VueCookies.get("userId")
      axios({
        method: "get",
        url: `${API_URL}/like/source`,
        params: {
          nftSourceId: nftSourceId,
          userId: uid,
        }
      })
      .then((res) => {
        console.log(res)
      })
      .catch((err) => {
        console.log(err)
      })
    },

    dropsUnLike(context, nftSourceId) {
      const uid = VueCookies.get("userId")
      axios({
        method: "get",
        url: `${API_URL}/like/source-cancel`,
        params: {
          nftSourceId: nftSourceId,
          userId: uid,
        }
      })
      .then((res) => {
        console.log(res)
      })
      .catch((err) => {
        console.log(err)
      })
    },

    marketLike(context, nftId) {
      const uid = VueCookies.get("userId")
      axios({
        method: "get",
        url: `${API_URL}/like/nft`,
        params: {
          nftId: nftId,
          userId: uid,
        }
      })
      .then((res) => {
        console.log(res)
      })
      .catch((err) => {
        console.log(err)
      })
    },

    marketUnLike(context, nftId) {
      const uid = VueCookies.get("userId")
      axios({
        method: "get",
        url: `${API_URL}/like/nft-cancel`,
        params: {
          nftId: nftId,
          userId: uid,
        }
      })
      .then((res) => {
        console.log(res)
      })
      .catch((err) => {
        console.log(err)
      })
    },

    getuserLikeDropsNFTs() {
      const uid = VueCookies.get("userId")
      axios({
        method: "get",
        url: `${API_URL}/like/source/${uid}`,
      })
      .then((res) => {
        this.userLikeDropsNFTs = res.data.data
      })
      .catch((err) => {
        console.log(err)
      })
    },
    
    getuserLikeMarketNFTs() {
      const uid = VueCookies.get("userId")
      axios({
        method: "get",
        url: `${API_URL}/like/nft/${uid}`,
      })
      .then((res) => {
        console.log("123485678956")
        console.log(res.data.data)
        this.userLikeMarketNFTs = res.data.data
      })
      .catch((err) => {
        console.log(err)
      })
    },

    async getDropsNftId(context, payload) {
      const accessToken = VueCookies.get('AccessToken')
      console.log(`${API_URL}/nft/buy/` + payload);
      axios({
        method: "get",
        url: `${API_URL}/nft/buy/` + payload,
        headers: {
          Authorization: 'Bearer ' + accessToken,
        }
      })
      .then((res) => {
        console.log(res);
        console.log(res.data);
        console.log(res.data.messege);
        if(res.data.messege === "SUCCESS"){
          console.log(res.data.data.nftId);
          console.log(res.data.data.contractAddress);
          console.log(res.data.data.price);
          const payload = {nftId: res.data.data.nftId, contractAddress: res.data.data.contractAddress, price: res.data.data.price};
          this.dispatch('BuyToBlockChain', payload);
        }
        else{
          console.log(res);
        }
      })
      .catch((err) => {
        console.log(err);
      })
    },

    async BuyToBlockChain(context, payload) {
      context.dispatch('sameAccount');
      const SaleContractAddress = payload.contractAddress;
      const SaleContract = new web3.eth.Contract(SaleABI, SaleContractAddress);
      const account  = VueCookies.get('Account');
      console.log(account);
      const price = web3.utils.toWei((payload.price), "ether");
      console.log(price);
      //  구매인데 판매 중인 nft의 가격을 value에 입력이 되어야한다.
      // const ans = SaleContract.methods.purchase().send({ from : account, value : price });
      SaleContract.methods.purchase().send({ from : account, value : price })
      .on("receipt", function(receipt) {
        console.log("Buy Receipt : ");
        console.log(receipt);

        const accessToken = VueCookies.get('AccessToken');
        axios ({
          method: "put",
          url: `${API_URL}/nft/buy`,
          headers: {
            Authorization: 'Bearer ' + accessToken,
          },
          data: {
            nftId: payload.nftId,
            buyerId: VueCookies.get('userId'),
          }
        })
        .then((res) => {
          console.log(res.data.message);
        })
        .catch((err) => {
          alert(err);
        });
      })
      .on("error", function(err) {
        console.error(err);
        alert(err);
      });
    },

    async resellDetailNFTs(context, NFTId) {
      try {
        const response = await axios({
          method: "get",
          url: `${API_URL}/nft/resell/${NFTId}`,
        });
        return response.data.data;
      } catch (err) {
        console.log(err);
      }
    },

    async createSaleToBlockChain(context, payload) {
      context.dispatch('sameAccount');
      const amount = web3.utils.toWei( payload.price, "ether");
      const account = VueCookies.get('Account');
      
      (await saleFactoryContract.methods.createSale(payload.tokenId, amount).send({ from: account })
      .on("transactionHash", function(hash) {
        console.log("Transaction hash: " + hash);
      })
      .on("receipt", function(receipt) {
        console.log("Create Sale Receipt : ");
        console.log(receipt);

        saleFactoryContract.getPastEvents('NewSale', {
          filter: { itemId : [ payload.tokenId ] },
          fromBlock: 0,
          toBlock: 'latest',
        }, async function(err, events){
          console.log(err);
          console.log('event : ', events);
          console.log('events[events.length - 1] : ', events[events.length - 1]);
          console.log('events[events.length - 1].returnValues : ', events[events.length - 1].returnValues);
          console.log('events[events.length - 1].returnValues._saleContract : ', events[events.length - 1].returnValues._saleContract);


          context.state.SaleContractAddress = events[events.length - 1].returnValues._saleContract;
          console.log('this.state,SaleContractAddress',context.state.SaleContractAddress);
        })
        .then((events) => {
          context.state.SaleContractAddress = events[events.length - 1].returnValues._saleContract;
          console.log('this.state,SaleContractAddress(events) :',context.state.SaleContractAddress);
          const payload1 = {
            nftId: payload.tokenId,
            contractAddress: context.state.SaleContractAddress,
            price: payload.price,
          };
          console.log('payload1 :', payload1)
          context.dispatch('resellRegistration', payload1);

        });
      })
      .on("error", function(err) {
        console.error(err);
        context.state.err = err;
      }));
    },

    async resellRegistration(context, payload1){
      const accessToken = VueCookies.get('AccessToken');
      axios({
        method: "put",
        url: `${API_URL}/nft/resell`,
        headers: {
          Authorization: 'Bearer ' + accessToken,
        },
        data: {
          nftId: payload1.nftId,
          contractAddress: payload1.contractAddress,
          price: payload1.price,
        }
      })
      .then((res) => {
        console.log(res);
        console.log(res.data.messege);
      })
      .catch((err) => {
        console.log(err);
      })
    },

  },
    modules: {},
  })
  
  export default store
