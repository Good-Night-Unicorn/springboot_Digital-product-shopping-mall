<template>
	<div class="home-preview">




		<!-- 新闻资讯 -->
		<div id="animate_newsnews" class="news animate__animated">
			<div class="news_title_box">
				<span class="news_title">通知公告</span>
				<span class="news_subhead">{{'news'.toUpperCase()}}</span>
			</div>
			<div class="list list11 index-pv1">
				<div class="left_box">
					<div class="list-item" v-for="(item,index) in newsList" :key="index" v-if="index<Number(2)" @click="toDetail('newsDetail', item)">
						<div class="img"><img :src="baseUrl + item.picture" alt=""></div>
						<div class="infoBox">
							<div class="name">{{item.title}}</div>
							<div class="time">{{item.addtime}}</div>
							<div class="desc">{{ item.introduction }}</div>
						</div>
					</div>
				</div>
				<div class="right_box">
					<div class="list-item" :class="newsIndex11==index?'list-item-active':''" @click="newsTabClick11(index)"  v-for="(item,index) in newsList" :key="index" v-if="index>=Number(2)&&index<(Number(2) + Number(4))">
						<div class="name">{{item.title}}</div>
						<div class="list-item2" @click="toDetail('newsDetail', item)">
							<div class="desc">{{ item.introduction }}</div>
						</div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('news')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
		</div>
		<!-- 新闻资讯 -->
		<!-- 商品推荐 -->
		<div id="animate_recommendshumachanpin" class="recommend animate__animated">
			<div class="recommend_title_box">
				<span class="recommend_title">数码产品推荐</span>
				<span class="recommend_subhead">{{'shumachanpin'.toUpperCase()}} RECOMMEND</span>
			</div>
			<div v-if="shumachanpinRecommend.length" class="list list16 index-pv1">
				<div v-for="item,index in shumachanpinRecommend" :key="index"  @click="toDetail('shumachanpinDetail', item)" class="list-item animation-box">
					<div class="img">
						<img v-if="preHttp(item.chanpintupian)" :src="item.chanpintupian.split(',')[0]" alt="" />
						<img v-else :src="baseUrl + (item.chanpintupian?item.chanpintupian.split(',')[0]:'')" alt="" />
					</div>
					<div class="infoBox">
						<div class="info-left">
							<div class="name">{{item.chanpinmingcheng}}</div>
							<div class="name">{{item.chanpinleixing}}</div>
							<div class="price">￥{{item.price}}</div>
							<div class="time_item">
								<span class="icon iconfont icon-shijian21"></span>
								<span class="label">发布时间：</span>
								<span class="text">{{item.addtime.split(' ')[0]}}</span>
							</div>
							<div class="publisher_item">
								<span class="icon iconfont icon-geren16"></span>
								<span class="label">发布人：</span>
								<span class="text">{{item.dianpumingcheng}}</span>
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
								<span class="icon iconfont icon-chakan9"></span>
								<span class="label">浏览次数：</span>
								<span class="text">{{item.clicknum}}</span>
							</div>
						</div>
						<div class="desc ql-snow ql-editor" v-html="item.chanpinjieshao"></div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('shumachanpin')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
		</div>
		<!-- 商品推荐 -->
		<!-- 商品推荐 -->
		<div id="animate_recommendcuxiaohuodong" class="recommend animate__animated">
			<div class="recommend_title_box">
				<span class="recommend_title">促销活动推荐</span>
				<span class="recommend_subhead">{{'cuxiaohuodong'.toUpperCase()}} RECOMMEND</span>
			</div>
			<div v-if="cuxiaohuodongRecommend.length" class="list list16 index-pv1">
				<div v-for="item,index in cuxiaohuodongRecommend" :key="index"  @click="toDetail('cuxiaohuodongDetail', item)" class="list-item animation-box">
					<div class="img">
						<img v-if="preHttp(item.chanpintupian)" :src="item.chanpintupian.split(',')[0]" alt="" />
						<img v-else :src="baseUrl + (item.chanpintupian?item.chanpintupian.split(',')[0]:'')" alt="" />
					</div>
					<div class="infoBox">
						<div class="info-left">
							<div class="name">{{item.chanpinmingcheng}}</div>
							<div class="name">{{item.chanpinleixing}}</div>
							<div class="price">￥{{item.price}}</div>
							<div class="time_item">
								<span class="icon iconfont icon-shijian21"></span>
								<span class="label">发布时间：</span>
								<span class="text">{{item.addtime.split(' ')[0]}}</span>
							</div>
							<div class="publisher_item">
								<span class="icon iconfont icon-geren16"></span>
								<span class="label">发布人：</span>
								<span class="text">{{item.dianpumingcheng}}</span>
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
								<span class="icon iconfont icon-chakan9"></span>
								<span class="label">浏览次数：</span>
								<span class="text">{{item.clicknum}}</span>
							</div>
						</div>
						<div class="desc ql-snow ql-editor" v-html="item.chanpinjieshao"></div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('cuxiaohuodong')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
		</div>
		<!-- 商品推荐 -->
	</div>
</template>

<script>
import 'animate.css'
import Swiper from "swiper";

	export default {
		//数据集合
		data() {
			return {
				baseUrl: '',
				newsList: [],
				shumachanpinRecommend: [],
				cuxiaohuodongRecommend: [],


				newsIndex11: Number(2),



			}
		},
		created() {
			this.baseUrl = this.$config.baseUrl;
			this.getNewsList();
			this.getList();
		},
		mounted() {
			window.addEventListener('scroll', this.handleScroll)
			setTimeout(()=>{
				this.handleScroll()
			},100)
			
			this.swiperChanges()
		},
		beforeDestroy() {
			window.removeEventListener('scroll', this.handleScroll)
		},
		//方法集合
		methods: {
			newsTabClick11(index){
				this.newsIndex11 = index
			},
			swiperChanges() {
				setTimeout(()=>{
				},750)
			},


			handleScroll() {
				let arr = [
					{id:'about',css:'animate__'},
					{id:'system',css:'animate__'},
					{id:'animate_recommendshumachanpin',css:'animate__'},
					{id:'animate_recommendcuxiaohuodong',css:'animate__'},
					{id:'animate_newsnews',css:'animate__'},
				]
			
				for (let i in arr) {
					let doc = document.getElementById(arr[i].id)
					if (doc) {
						let top = doc.offsetTop
						let win_top = window.innerHeight + window.pageYOffset
						// console.log(top,win_top)
						if (win_top > top && doc.classList.value.indexOf(arr[i].css) < 0) {
							// console.log(doc)
							doc.classList.add(arr[i].css)
						}
					}
				}
			},
			preHttp(str) {
				return str && str.substr(0,4)=='http';
			},
			preHttp2(str) {
				return str && str.split(',w').length>1;
			},
			getNewsList() {
				let data = {
					page: 1,
					limit: 7,
					sort: 'addtime',
					order: 'desc'
				}
				this.$http.get('news/list', {params: data}).then(res => {
					if (res.data.code == 0) {
						this.newsList = res.data.data.list;
					
					}
				});
			},
			getList() {
				let autoSortUrl = "";
				let data = {}
				autoSortUrl = "shumachanpin/autoSort";
				if(localStorage.getItem('frontToken')) {
					autoSortUrl = "shumachanpin/autoSort2";
				}
				data = {
					page: 1,
					limit: 4,
					onshelves: 1,
				}
				this.$http.get(autoSortUrl, {params: data}).then(res => {
					if (res.data.code == 0) {
						this.shumachanpinRecommend = res.data.data.list;
					}
				});
				autoSortUrl = "cuxiaohuodong/autoSort";
				if(localStorage.getItem('frontToken')) {
					autoSortUrl = "cuxiaohuodong/autoSort2";
				}
				data = {
					page: 1,
					limit: 4,
					onshelves: 1,
				}
				this.$http.get(autoSortUrl, {params: data}).then(res => {
					if (res.data.code == 0) {
						this.cuxiaohuodongRecommend = res.data.data.list;
					}
				});
			
			},
			toDetail(path, item) {
				this.$router.push({path: '/index/' + path, query: {id: item.id}});
			},
			moreBtn(path) {
				this.$router.push({path: '/index/' + path});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.home-preview {
		margin: 0px auto;
		flex-direction: column;
		background: #fff;
		display: flex;
		width: 100%;
		.news {
			padding: 30px calc(50% - 625px);
			margin: 0;
			background: url(http://codegen.caihongy.cn/20241105/c9ba40563845438b8dd19c83a96af3b4.webp) no-repeat bottom;
			width: 100%;
			position: relative;
			order: 1;
			.news_title_box {
				padding: 0;
				margin: 0;
				background: none;
				width: 100%;
				position: relative;
				text-align: left;
				height: auto;
				.news_title {
					padding: 0;
					margin: 0 0 0px 0;
					color: #000000;
					background: none;
					display: inline-block;
					width: auto;
					font-size: 40px;
					line-height: inherit;
					height: auto;
				}
				.news_subhead {
					margin: 0 0 10px;
					color: #5DB5B7;
					display: block;
					width: 100%;
					font-size: 30px;
					line-height: 1.5;
					text-align: left;
				}
			}
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list11 {
				padding: 0;
				margin: 30px auto 0;
				overflow: hidden;
				background: none;
				display: flex;
				width: 100%;
				justify-content: space-between;
				.left_box {
					width: 48%;
					.list-item {
						border: none;
						cursor: pointer;
						padding: 0 0 25px;
						margin: 0;
						overflow: hidden;
						display: flex;
						width: 100%;
						border-width: 0;
						justify-content: space-between;
						.img {
							flex: 0 0 auto;
							width: 200px;
							height: 200px;
							img {
								object-fit: cover;
								width: 100%;
								transition: all 0.3s;
								height: 100%;
							}
						}
						.infoBox {
							padding: 20px;
							background: #F9F9F9;
							flex: 1 1 auto;
							width: 100%;
							.name {
								overflow: hidden;
								color: #666;
								white-space: nowrap;
								font-weight: 600;
								width: 100%;
								font-size: 16px;
								line-height: 26px;
								text-overflow: ellipsis;
							}
							.time {
								border-radius: 5px;
								margin: 10px 0;
								color: #fff;
								background: #5DB5B7;
								width: 150px;
								font-size: 14px;
								text-align: center;
							}
							.desc {
								overflow: hidden;
								color: #858585;
								text-indent: 2em;
								line-height: 24px;
								height: 72px;
							}
						}
					}
					.list-item:hover {
						.img {
							img {
								transform: scale(1.05);
							}
						}
						.infoBox {
							.name {
								color: #5DB5B7;
							}
						}
					}
				}
				.right_box {
					padding: 0 0 0 30px;
					overflow: hidden;
					width: 48%;
					height: 418px;
					.list-item {
						position: relative;
						.name {
							cursor: pointer;
							padding: 0 50px 0 25px;
							overflow: hidden;
							color: #353535;
							white-space: nowrap;
							font-weight: normal;
							width: 100%;
							font-size: 15px;
							line-height: 40px;
							position: relative;
							text-overflow: ellipsis;
						}
						.name::before {
							color:  #666;
							top: 0;
							font-weight: normal;
							font-size: 22px;
							line-height: 40px;
							position: absolute;
							right: 25px;
							content: "+";
						}
						.list-item2 {
							border: 1px dashed #bbbbbb;
							padding: 0 25px;
							margin: 0;
							overflow: hidden;
							text-indent: 2em;
							display: block;
							border-width: 0 0 1px;
							opacity: 0;
							transition: all 0.5s;
							height: 0;
							.desc {
								cursor: pointer;
								margin: 10px 0 0;
								overflow: hidden;
								color: #909090;
								font-size: 14px;
								line-height: 25px;
								height: 200px;
							}
						}
					}
					.list-item.list-item-active {
						.name {
							color: #FFF;
							background: #5DB5B7;
						}
						.name::before {
							color: #fff;
							content: "-";
						}
						.list-item2 {
							padding: 0 25px 16px;
							margin: 0 0 10px;
							display: block;
							opacity: 1;
							transition: all 0.5s;
							height: 174px;
						}
					}
				}
			}
			.moreBtn {
				border: 0;
				cursor: pointer;
				margin: 80px 0 0;
				top: 0;
				background: none;
				display: block;
				width: auto;
				line-height: 32px;
				position: absolute;
				right: calc(50% - 625px);
				border-bottom: 2px solid #5DB5B7;
				text-align: center;
				.text {
					color: #9E9E9E;
					font-size: 15px;
				}
				.icon {
					color: #000;
					display: none;
					font-size: 15px;
				}
			}
		}
		.recommend {
			padding: 0 calc(50% - 625px);
			margin: 0;
			background: url(http://codegen.caihongy.cn/20241105/de478d9056db48f2b1bb4df58e4742af.webp) no-repeat center;
			width: 100%;
			position: relative;
			order: 2;
			.recommend_title_box {
				padding: 0;
				margin: 40px auto 0;
				background: none;
				width: 100%;
				position: relative;
				text-align: left;
				height: auto;
				.recommend_title {
					padding: 0;
					margin: 0 0 0px 0;
					color: #000000;
					background: none;
					display: inline-block;
					width: auto;
					font-size: 40px;
					line-height: inherit;
					height: auto;
				}
				.recommend_subhead {
					margin: 0 0 10px;
					color: #5DB5B7;
					display: block;
					width: 100%;
					font-size: 30px;
					line-height: 1.5;
					text-align: left;
				}
			}
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
			
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, -10px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list16 {
				padding: 0;
				margin: 20px auto 0;
				color: #5DB5B7;
				background: none;
				display: flex;
				width: 100%;
				font-size: 14px;
				justify-content: space-between;
				flex-wrap: wrap;
				height: auto;
				.list-item {
					padding: 0;
					margin: 0 0 30px;
					background: none;
					display: flex;
					width: 49%;
					position: relative;
					height: auto;
					.img {
						border: 0px solid #777777;
						padding: 0px;
						overflow: hidden;
						background: none;
						width: 200px;
						height: 200px;
						img {
							object-fit: cover;
							display: block;
							width: 100%;
							height: 100%;
						}
					}
					.infoBox {
						padding: 0 10px;
						overflow: hidden;
						flex: 1;
						display: flex;
						height: auto;
						.info-left {
							padding: 0;
							width: 100%;
							.name {
								padding: 0 10px;
								overflow: hidden;
								color: #000000;
								white-space: nowrap;
								width: 100%;
								font-size: 20px;
								line-height: 30px;
								text-overflow: ellipsis;
							}
							.price {
								padding: 0 10px;
								color: #D00000;
								font-size: 16px;
								line-height: 2;
							}
							.time_item {
								padding: 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									display: none;
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
								padding: 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									display: none;
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
								padding: 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									display: none;
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
								padding: 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									display: none;
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
								padding: 0 10px;
								display: inline-block;
								.icon {
									margin: 0 2px 0 0;
									display: none;
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
							color: #666;
							flex: 1;
							display: none;
							font-size: 14px;
							line-height: 1.5;
						}
					}
				}
				.list-item:hover {
					cursor: pointer;
					background: #F4FFFF;
					.infoBox {
						.info-left {
							.name {
							}
							.price {
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
									color: #fff;
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
							color: #fff;
						}
					}
				}
			}
			.moreBtn {
				border: 0;
				cursor: pointer;
				margin: 80px 0 0;
				top: 0;
				background: none;
				display: block;
				width: auto;
				line-height: 32px;
				position: absolute;
				right: calc(50% - 625px);
				border-bottom: 2px solid #5DB5B7;
				text-align: center;
				.text {
					color: #9E9E9E;
					font-size: 15px;
				}
				.icon {
					color: #333;
					display: none;
					font-size: 15px;
				}
			}
		}
	}
</style>
