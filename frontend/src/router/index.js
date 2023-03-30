import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/mainpage/HomeView.vue';
import MyPageView from '../views/account/MyPageView.vue';
import MarketListView from '../views/market/MarketListView.vue';
import MarketDetailView from '../views/market/MarketDetailView.vue';
import MarketAddView from '../views/market/MarketAddView.vue';
import CommunityMainView from '../views/community/CommunityMainView.vue';
import SignUpView from '../views/account/SignUpView.vue';
import CommunityAddView from '../views/community/CommunityAddView.vue';
import CommunityDetailView from '../views/community/CommunityDetailView.vue';
import ResellListView from '../views/resell/ResellListView.vue';
import SignUpSelectView from '../views/account/SignUpSelectView.vue';

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
    path: '/resell',
    name: 'ResellListView',
    component: ResellListView
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
  routes
})

export default router
