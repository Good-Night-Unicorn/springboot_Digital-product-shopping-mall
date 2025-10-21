<template>
	<div>
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="'≡'">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index"><a>{{item.name}}</a></el-breadcrumb-item>
			</el-breadcrumb>
		</div>
	
		<div class="news-preview-pv">
			<el-form :inline="true" :model="formSearch" class="list-form-pv">
				<el-form-item class="search-item">
					<el-input v-model="title" placeholder="标题"></el-input>
				</el-form-item>
				<el-button class="search-btn" type="primary" @click="getNewsList(1)">
					<span class="icon iconfont icon-chakan14"></span>
					搜索
				</el-button>
			</el-form>
			
			<!-- category -->
			<div class="category-list">
				<div class="item" @click="categoryClick(0)" :class="categoryIndex == 0 ? 'active' : ''">全部</div>
				<div v-for="(item,index) in categoryList" @click="categoryClick(index+1)" :key="index" class="item" :class="categoryIndex == index+1 ? 'active' : ''">{{item.typename}}</div>
			</div>
			<div class="list10 index-pv1">
				<div v-for="(item,index) in newsList" :key="index" class="list-item animation-box" @click="toNewsDetail(item)">
					<div class="img">
						<img class="image" :src="baseUrl + item.picture" >
					</div>
					<div class="infoBox">
						<div class="infoLeft">
							<div class="name">{{item.title}}</div>
							<div class="time_item">
								<span class="icon iconfont icon-shijian21"></span>
								<span class="label">发布时间：</span>
								<span class="text">{{item.addtime.split(' ')[0]}}</span>
							</div>
							<div class="publisher_item">
								<span class="icon iconfont icon-geren16"></span>
								<span class="label">发布人：</span>
								<span class="text">{{item.name}}</span>
							</div>
							<div class="like_item">
								<span class="icon iconfont icon-zan10"></span>
								<span class="label">点赞：</span>
								<span class="text">{{item.thumbsupnum}}</span>
							</div>
							<div class="collect_item">
								<span class="icon iconfont icon-shoucang10"></span>
								<span class="label">收藏：</span>
								<span class="text">{{item.storeupnum}}</span>
							</div>
							<div class="view_item">
								<span class="icon iconfont icon-chakan2"></span>
								<span class="label">浏览次数：</span>
								<span class="text">{{item.clicknum}}</span>
							</div>
						</div>
						<div class="desc">{{item.introduction}}</div>
					</div>
				</div>
			</div>
		
			<el-pagination
				background
				id="pagination" class="pagination"
				:pager-count="7"
				:page-size="pageSize"
				:page-sizes="pageSizes"
				prev-text="<"
				next-text=">"
				:hide-on-single-page="false"
				:layout='["total","prev","pager","next","sizes","jumper"].join()'
				:total="total"
				@current-change="curChange"
				@prev-click="prevClick"
				@next-click="nextClick"
				></el-pagination>

			<!-- 热门信息 -->
			<div class="hot">
				<div class="hot-title">热门信息</div>
				<div class="hot-list">
					<div class="hot-item" v-for="item in hotList" :key="item.id" @click="toNewsDetail(item)">
						<img :src="baseUrl + item.picture" alt="">
						<div class="hot-name">{{ item.title }}</div>
						<div class="hot-time">{{item.addtime}}</div>
					</div>
				</div>
			</div>
			<!-- 最新动态 -->
			<div class="news">
				<div class="news-title">最新动态</div>
				<div class="news-list">
					<div class="news-item" v-for="item in recommendList" :key="item.id" @click="toNewsDetail(item)">
						<img :src="baseUrl + item.picture" alt="">
						<div class="news-name">{{ item.title }}</div>
						<div class="news-time">{{item.addtime}}</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		//数据集合
		data() {
			return {
				baseUrl: this.$config.baseUrl,
				breadcrumbItem: [
				  {
					name: '通知公告'
				  }
				],
				newsList: [],
				total: 1,
				pageSize: 10,
				pageSizes: [],
				totalPage: 1,
				layouts: '',
				title: '',
				categoryIndex: 0,
				categoryList: [],
				hotList: [],
				recommendList: [],
			}
		},
		created() {
			this.getCategoryList()
			
			this.getHotList()
			this.getRecommendList()
		},
		watch:{
			$route(newValue){
				this.getCategoryList()
			}
		},
		//方法集合
		methods: {
			getCategoryList(){
				this.$http.get('newstype/list', {}).then(res => {
					if (res.data.code == 0) {
						this.categoryList = res.data.data.list;
						if(this.$route.query.homeFenlei){
							for(let i=0;i<this.categoryList.length;i++) {
								if(this.$route.query.homeFenlei == this.categoryList[i].typename) {
									this.categoryIndex = i + 1;
									const currentRoute = this.$route;
									const routeWithoutQuery = { ...currentRoute };
									delete routeWithoutQuery.query;
									this.$router.replace(routeWithoutQuery)
									break;
								}
							}
						}
						this.getNewsList(1);
					}
				});
			},
			categoryClick(index) {
				this.categoryIndex = index
				this.getNewsList()
			},
			getNewsList(page) {
				let params = {page, limit: this.pageSize,sort:'addtime',order:'desc'};
				let searchWhere = {};
				if(this.title != '') searchWhere.title = '%' + this.title + '%';
				if(this.categoryIndex!=0){
					searchWhere.typename = this.categoryList[this.categoryIndex - 1].typename
				}
				this.$http.get('news/list', {params: Object.assign(params, searchWhere)}).then(res => {
					if (res.data.code == 0) {
						this.newsList = res.data.data.list;
						this.total = res.data.data.total;
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
						if(this.pageSizes.length==0){
							this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
						}
					}
				});
			},
			getHotList(){
				let params = {page:1, limit: 4,sort:'addtime',order:'desc'};
				this.$http.get('news/autoSort', {params: params}).then(res => {
					if (res.data.code == 0) {
						this.hotList = res.data.data.list;
					}
				});
			},
			getRecommendList(){
				let url = 'news/autoSort'
				if(localStorage.getItem('frontToken')){
					url = 'news/autoSort2'
				}
				let params = {page:1, limit: 4,sort:'addtime',order:'desc'};
				this.$http.get(url, {params: params}).then(res => {
					if (res.data.code == 0) {
						this.recommendList = res.data.data.list;
					}
				});
			},
			curChange(page) {
				this.getNewsList(page);
			},
			prevClick(page) {
				this.getNewsList(page);
			},
			nextClick(page) {
				this.getNewsList(page);
			},
			toNewsDetail(item) {
				this.$router.push({path: '/index/newsDetail', query: {id: item.id}});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.news-preview-pv {
				margin: 0px auto;
				color: #333;
				background: none;
				width: 1250px;
				font-size: 16px;
				position: relative;
				.list-form-pv {
						padding: 10px;
						background: none;
						display: flex;
						width: 100%;
						justify-content: center;
						align-items: center;
						flex-wrap: wrap;
						height: auto;
						.search-item {
								margin: 0 10px;
								.el-input {
										width: 100%;
									}
				.el-input /deep/ .el-input__inner {
										border: 1px solid #ccc;
										border-radius: 4px;
										padding: 0 10px;
										margin: 0;
										color: #333;
										width: auto;
										font-size: 16px;
										line-height: 42px;
										min-width: 350px;
										height: 42px;
									}
			}
			.search-btn {
								cursor: pointer;
								border: 0;
								border-radius: 4px;
								padding: 0px 15px;
								margin: 0 10px 0 0;
								color: #fff;
								background: #03abe9;
								width: auto;
								font-size: inherit;
								line-height: 42px;
								height: 42px;
								.icon {
										margin: 0 3px 0 0;
										color: #fff;
										font-size: inherit;
									}
			}
		}
		.category-list {
						padding: 20px 0;
						margin: 0;
						background: #fff;
						display: flex;
						gap: 20px;
						width: 100%;
						justify-content: center;
						flex-wrap: wrap;
						height: auto;
						.item {
								cursor: pointer;
								border-radius: 0;
								padding: 0;
								margin: 0;
								color: inherit;
								background: url(http://codegen.caihongy.cn/20241107/67909b0e3d9d4c95a6750d72379f0a31.webp) no-repeat center;
								display: flex;
								font-size: 16px;
								justify-content: center;
								align-items: center;
								min-width: 210px;
								height: 56px;
							}
			
			.item:hover {
								color: #fff;
								background: url(http://codegen.caihongy.cn/20241107/67909b0e3d9d4c95a6750d72379f0a31.webp) no-repeat center;
							}
			
			.item.active {
								color: #fff;
								background: url(http://codegen.caihongy.cn/20241107/67909b0e3d9d4c95a6750d72379f0a31.webp) no-repeat center;
							}
		}
		.list10 {
						padding: 0;
						color: #888;
						background: #fff;
						display: flex;
						width: 100%;
						font-size: 14px;
						justify-content: space-between;
						flex-wrap: wrap;
						height: auto;
						.list-item {
								cursor: pointer;
								border: 1px solid #fff;
								padding: 30px;
								margin: 0 0 20px;
								background: #F9F9F9;
								display: flex;
								width: 49%;
								position: relative;
								flex-wrap: wrap;
								height: auto;
								.img {
										padding: 0;
										overflow: hidden;
										width: 100%;
										height: 400px;
										img {
												object-fit: cover;
												display: block;
												width: 100%;
												height: 100%;
											}
				}
				.infoBox {
										padding: 10px;
										overflow: hidden;
										display: flex;
										width: 100%;
										flex-wrap: wrap;
										height: auto;
										.infoLeft {
												padding: 0;
												width: 100%;
												.name {
														padding: 0;
														overflow: hidden;
														color: #000000;
														white-space: nowrap;
														font-weight: 600;
														display: inline-block;
														width: 100%;
														font-size: 16px;
														line-height: 30px;
														text-overflow: ellipsis;
													}
						.time_item {
														padding: 0 10px 0 0;
														display: inline-block;
														.icon {
																margin: 0 2px 0 0;
																line-height: 28px;
															}
							.label {
																line-height: 1.5;
															}
							.text {
																line-height: 1.5;
															}
						}
						.publisher_item {
														padding: 0 10px 0 0;
														display: inline-block;
														.icon {
																margin: 0 2px 0 0;
																line-height: 28px;
															}
							.label {
																line-height: 1.5;
															}
							.text {
																line-height: 28px;
															}
						}
						.like_item {
														padding: 0 10px 0 0;
														display: inline-block;
														.icon {
																margin: 0 2px 0 0;
																line-height: 28px;
															}
							.label {
																line-height: 1.5;
															}
							.text {
																line-height: 28px;
															}
						}
						.collect_item {
														padding: 0 10px 0 0;
														display: inline-block;
														.icon {
																margin: 0 2px 0 0;
																line-height: 28px;
															}
							.label {
																line-height: 1.5;
															}
							.text {
																line-height: 28px;
															}
						}
						.view_item {
														padding: 0 10px 0 0;
														display: inline-block;
														.icon {
																margin: 0 2px 0 0;
																line-height: 28px;
															}
							.label {
																line-height: 1.5;
															}
							.text {
																line-height: 28px;
															}
						}
					}
					.desc {
												margin: 10px 0 0;
												overflow: hidden;
												color: #666;
												text-indent: 2em;
												width: 100%;
												font-size: 14px;
												line-height: 24px;
												height: 48px;
											}
				}
			}
			.list-item:hover {
								border: 1px solid #5DB5B7;
								background: #5DB5B7;
								.infoBox {
					.infoLeft {
						.name {
														color: #FFFFFF;
													}
						.time_item {
							.icon {
															}
							.label {
															}
							.text {
															}
						}
						.publisher_item {
							.icon {
															}
							.label {
															}
							.text {
															}
						}
						.like_item {
							.icon {
															}
							.label {
															}
							.text {
															}
						}
						.collect_item {
							.icon {
															}
							.label {
															}
							.text {
															}
						}
						.view_item {
							.icon {
															}
							.label {
															}
							.text {
															}
						}
					}
					.desc {
											}
				}
			}
		}
		.hot {
						border: 1px solid #5FB6B8;
						padding: 0 20px;
						margin: 20px 0;
						display: flex;
						clear: both;
						flex-direction: column;
						overflow: hidden;
						background: none;
						width: 100%;
						justify-content: center;
						align-items: center;
						height: auto;
						order: 100;
						.hot-title {
								border-radius: 0 0 30px 30px;
								padding: 0 ;
								color: #fff;
								background: #5FB6B8;
								width: 160px;
								font-size: 24px;
								line-height: 60px;
								text-align: center;
							}
			.hot-list {
								padding: 0;
								margin: 20px 0 0;
								background: none;
								display: flex;
								width: 100%;
								justify-content: space-between;
								flex-wrap: wrap;
								height: auto;
								.hot-item {
										cursor: pointer;
										padding: 0;
										background: #fff;
										width: 23%;
										height: auto;
										img {
												border-radius: 10px;
												object-fit: cover;
												display: block;
												width: 100%;
												height: 150px;
											}
					.hot-name {
												padding: 4px 5px 0;
												overflow: hidden;
												color: #000000;
												white-space: nowrap;
												font-size: 18px;
												line-height: 30px;
												text-overflow: ellipsis;
											}
					.hot-time {
												padding: 0 5px;
												color: #000000;
												font-size: 14px;
												line-height: 24px;
												text-align: left;
											}
				}
			}
		}
		.news {
						border: 1px solid #5FB6B8;
						padding: 0 20px;
						margin: 20px 0;
						display: flex;
						clear: both;
						flex-direction: column;
						overflow: hidden;
						background: none;
						width: 100%;
						justify-content: center;
						align-items: center;
						height: auto;
						order: 101;
						.news-title {
								border-radius: 0 0 30px 30px;
								padding: 0 ;
								color: #fff;
								background: #5FB6B8;
								width: 160px;
								font-size: 24px;
								line-height: 60px;
								text-align: center;
							}
			.news-list {
								padding: 0;
								background: none;
								width: 100%;
								height: auto;
								.news-item {
										cursor: pointer;
										margin: 20px 10px;
										background: #fff;
										display: inline-block;
										width: calc(25% - 20px);
										height: auto;
										img {
												border-radius: 10px;
												object-fit: cover;
												display: block;
												width: 100%;
												height: 150px;
											}
					.news-name {
												padding: 4px 5px 0;
												overflow: hidden;
												color: #000000;
												white-space: nowrap;
												font-size: 18px;
												line-height: 30px;
												text-overflow: ellipsis;
											}
					.news-time {
												padding: 0 5px;
												color: #000000;
												font-size: 14px;
												line-height: 24px;
												text-align: left;
											}
				}
			}
		}
	}
	
	.index-pv1 .animation-box {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
		z-index: initial;
	}
	
	.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
	}
	
	.index-pv1 .animation-box img {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
	}
	
	.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(0.98) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
</style>
