export default {
	baseUrl: 'http://localhost:8080/springbootg47d9qwc/',
	name: '/springbootg47d9qwc',
	indexNav: [
// frontMenu com.jlwl.entity.Menu@7b15aca8
		{
			name: '数码产品',
			url: '/index/shumachanpin',
		},
// frontMenu com.jlwl.entity.Menu@56920e4
		{
			name: '促销活动',
			url: '/index/cuxiaohuodong',
		},
		{
			name: '通知公告',
			url: '/index/news'
		},
	],
	cateList: [
		{
			name: '通知公告',
			refTable: 'newstype',
			refColumn: 'typename',
		},
	]
}
