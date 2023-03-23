import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/mainpage/HomeView.vue'
import MyPageView from '../views/account/MyPageView.vue';
import MarketListView from '../views/market/MarketListView.vue';
import MarketDetailView from '../views/market/MarketDetailView.vue';
import MarketAddView from '../views/market/MarketAddView.vue';
import CommunityMainView from '../views/community/CommunityMainView.vue';

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
    path: '/market/add',
    name: 'MarketAddView',
    component: MarketAddView
  },
  {
    path: '/market/:id',
    name: 'MarketDetailView',
    component: MarketDetailView
  },
  {
    path: '/community',
    name: 'CommunityMainView',
    component: CommunityMainView
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
