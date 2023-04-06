import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/mainpage/HomeView.vue';
import MyPageView from '../views/account/MyPageView.vue';
import MyPageUpdateView from '../views/account/MyPageUpdateView';
import MarketListView from '../views/market/MarketListView.vue';
import MarketDetailView from '../views/market/MarketDetailView.vue';
import MarketAddView from '../views/market/MarketAddView.vue';
import CommunityMainView from '../views/community/CommunityMainView.vue';
import SignUpView from '../views/account/SignUpView.vue';
import CommunityAddView from '../views/community/CommunityAddView.vue';
import CommunityDetailView from '../views/community/CommunityDetailView.vue';
import ResellListView from '../views/resell/ResellListView.vue';
import ResellDetailView from '../views/resell/ResellDetailView.vue';
import SignUpSelectView from '../views/account/SignUpSelectView.vue';
import UserNFTView from '../views/resell/UserNFTView.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/community',
    name: 'community',
    component: CommunityMainView
  },
  {
    path: '/community/add',
    name: 'communityadd',
    component: CommunityAddView
  },
  {
    path: '/community/:number',
    name: 'communitydetail',
    component: CommunityDetailView
  },
  {
    path: '/mypage',
    name: 'mypageview',
    component: MyPageView
  },
  {
    path: '/mypageupdate',
    name: 'mypageupdate',
    component: MyPageUpdateView
  },
  {
    path: '/drops',
    name: 'MarketListView',
    component: MarketListView
  },
  {
    path: '/drops/add',
    name: 'MarketAddView',
    component: MarketAddView
  },
  {
    path: '/drops/:id',
    name: 'MarketDetailView',
    component: MarketDetailView
  },
  {
    path: '/market',
    name: 'ResellListView',
    component: ResellListView
  },
  {
    path: '/market/:id',
    name: 'ResellDetailView',
    component: ResellDetailView
  },
  {
    path: '/mypage/:nftId',
    name: 'UserNFTView',
    component: UserNFTView
  },
  {
    path: '/signup',
    name: 'SignUpview',
    component: SignUpView
  },
  {
    path: '/select',
    name: 'SignUpSelectView',
    component: SignUpSelectView,
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  //페이지 이동 시 스크롤 맨 위로 지정
  scrollBehavior(){
    return {top: 0}
  },
})

export default router
