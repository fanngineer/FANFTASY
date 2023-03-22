import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/mainpage/HomeView.vue';
import MyPageView from '../views/account/MyPageView.vue';
import MarketListView from '../views/market/MarketListView.vue';
import CommunityMainView from '../views/community/CommunityMainView.vue';
import SignUpView from '../views/account/SignUpView.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/mypage',
    name: 'mypageview',
    component: MyPageView
  },
  {
    path: '/market',
    name: 'MarketListView',
    component: MarketListView
  },
  {
    path: '/community',
    name: 'CommunityMainView',
    component: CommunityMainView
  },
  {
    path: '/signup',
    name: 'SignUpview',
    component: SignUpView
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
