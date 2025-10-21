import VueRouter from 'vue-router'
//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Storeup from '../pages/storeup/list'
import AddrList from '../pages/shop-address/list'
import AddrAdd from '../pages/shop-address/addOrUpdate'
import Order from '../pages/shop-order/list'
import OrderConfirm from '../pages/shop-order/confirm'
import Cart from '../pages/shop-cart/list'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import payList from '../pages/pay'

import yonghuList from '../pages/yonghu/list'
import yonghuDetail from '../pages/yonghu/detail'
import yonghuAdd from '../pages/yonghu/add'
import shangjiaList from '../pages/shangjia/list'
import shangjiaDetail from '../pages/shangjia/detail'
import shangjiaAdd from '../pages/shangjia/add'
import chanpinleixingList from '../pages/chanpinleixing/list'
import chanpinleixingDetail from '../pages/chanpinleixing/detail'
import chanpinleixingAdd from '../pages/chanpinleixing/add'
import shumachanpinList from '../pages/shumachanpin/list'
import shumachanpinDetail from '../pages/shumachanpin/detail'
import shumachanpinAdd from '../pages/shumachanpin/add'
import cuxiaohuodongList from '../pages/cuxiaohuodong/list'
import cuxiaohuodongDetail from '../pages/cuxiaohuodong/detail'
import cuxiaohuodongAdd from '../pages/cuxiaohuodong/add'
import chatmessageList from '../pages/chatmessage/list'
import chatmessageDetail from '../pages/chatmessage/detail'
import chatmessageAdd from '../pages/chatmessage/add'
import friendList from '../pages/friend/list'
import friendDetail from '../pages/friend/detail'
import friendAdd from '../pages/friend/add'
import chargerecordList from '../pages/chargerecord/list'
import chargerecordDetail from '../pages/chargerecord/detail'
import chargerecordAdd from '../pages/chargerecord/add'
import newstypeList from '../pages/newstype/list'
import newstypeDetail from '../pages/newstype/detail'
import newstypeAdd from '../pages/newstype/add'
import discussshumachanpinList from '../pages/discussshumachanpin/list'
import discussshumachanpinDetail from '../pages/discussshumachanpin/detail'
import discussshumachanpinAdd from '../pages/discussshumachanpin/add'
import discusscuxiaohuodongList from '../pages/discusscuxiaohuodong/list'
import discusscuxiaohuodongDetail from '../pages/discusscuxiaohuodong/detail'
import discusscuxiaohuodongAdd from '../pages/discusscuxiaohuodong/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'storeup',
					component: Storeup
				},
                {
                    path: 'shop-address/list',
                    component: AddrList
                },
                {
                    path: 'shop-address/addOrUpdate',
                    component: AddrAdd
                },
				{
					path: 'shop-order/order',
					component: Order
				},
				{
					path: 'cart',
					component: Cart
				},
				{
					path: 'shop-order/orderConfirm',
					component: OrderConfirm
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'yonghu',
					component: yonghuList
				},
				{
					path: 'yonghuDetail',
					component: yonghuDetail
				},
				{
					path: 'yonghuAdd',
					component: yonghuAdd
				},
				{
					path: 'shangjia',
					component: shangjiaList
				},
				{
					path: 'shangjiaDetail',
					component: shangjiaDetail
				},
				{
					path: 'shangjiaAdd',
					component: shangjiaAdd
				},
				{
					path: 'chanpinleixing',
					component: chanpinleixingList
				},
				{
					path: 'chanpinleixingDetail',
					component: chanpinleixingDetail
				},
				{
					path: 'chanpinleixingAdd',
					component: chanpinleixingAdd
				},
				{
					path: 'shumachanpin',
					component: shumachanpinList
				},
				{
					path: 'shumachanpinDetail',
					component: shumachanpinDetail
				},
				{
					path: 'shumachanpinAdd',
					component: shumachanpinAdd
				},
				{
					path: 'cuxiaohuodong',
					component: cuxiaohuodongList
				},
				{
					path: 'cuxiaohuodongDetail',
					component: cuxiaohuodongDetail
				},
				{
					path: 'cuxiaohuodongAdd',
					component: cuxiaohuodongAdd
				},
				{
					path: 'chatmessage',
					component: chatmessageList
				},
				{
					path: 'chatmessageDetail',
					component: chatmessageDetail
				},
				{
					path: 'chatmessageAdd',
					component: chatmessageAdd
				},
				{
					path: 'friend',
					component: friendList
				},
				{
					path: 'friendDetail',
					component: friendDetail
				},
				{
					path: 'friendAdd',
					component: friendAdd
				},
				{
					path: 'chargerecord',
					component: chargerecordList
				},
				{
					path: 'chargerecordDetail',
					component: chargerecordDetail
				},
				{
					path: 'chargerecordAdd',
					component: chargerecordAdd
				},
				{
					path: 'newstype',
					component: newstypeList
				},
				{
					path: 'newstypeDetail',
					component: newstypeDetail
				},
				{
					path: 'newstypeAdd',
					component: newstypeAdd
				},
				{
					path: 'discussshumachanpin',
					component: discussshumachanpinList
				},
				{
					path: 'discussshumachanpinDetail',
					component: discussshumachanpinDetail
				},
				{
					path: 'discussshumachanpinAdd',
					component: discussshumachanpinAdd
				},
				{
					path: 'discusscuxiaohuodong',
					component: discusscuxiaohuodongList
				},
				{
					path: 'discusscuxiaohuodongDetail',
					component: discusscuxiaohuodongDetail
				},
				{
					path: 'discusscuxiaohuodongAdd',
					component: discusscuxiaohuodongAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
