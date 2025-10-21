<template>
	<div :style='{"padding":"0 0 20px","margin":"0px auto","color":"#666","background":"#fff","width":"1200px","fontSize":"16px","position":"relative"}'>
		<div class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div v-if="storeupType==1" class="section-title" :style='{"padding":"0","margin":"0 1060px 20px 0","color":"#fff","borderRadius":"0px 0px 30px 30px","textAlign":"center","background":"#5FB6B8","display":"inline-block","width":"190px","fontSize":"22px","lineHeight":"60px","height":"60px"}'>我的收藏</div>
		<el-form :inline="true" :model="formSearch" class="formSearch">
			<el-form-item>
				<el-input v-model="formSearch.name" placeholder="名称"></el-input>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" @click="getStoreupList(1)">查询</el-button>
			</el-form-item>
		</el-form>
		<div style="display: flex;flex-wrap: wrap">
			<div style="width: 23%;margin: 0 1% 20px" v-for="(item, index) in storeupList" :key="index" @click="toDetail(item)">
				<el-card :body-style="{ padding: '0px', cursor: 'pointer' }">
					<el-image v-if="item.picture && item.picture.substr(0,4)=='http'" :src="item.picture" fit="fill" class="image"></el-image>
					<el-image v-else-if="item.picture&& item.picture.substr(0,4)!='http'" :src="baseUrl + item.picture" fit="fill" class="image"></el-image>
					<div style="padding: 14px;">
						<span v-if="item.remark">{{item.name}}</span>
						<span v-if="!item.remark">{{item.name}}</span>
					</div>
				</el-card>
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
			:style='{"padding":"0","boxShadow":"0px 4px 10px 0px rgba(0,0,0,0.3)","margin":"20px auto","whiteSpace":"nowrap","color":"#333","alignItems":"center","textAlign":"center","display":"flex","clear":"both","justifyContent":"center","overflow":"hidden","borderRadius":"30px","width":"100%","fontSize":"16px","fontWeight":"500","order":"50","height":"60px","maxWidth":"800px"}'
			@current-change="curChange"
			@prev-click="prevClick"
			@size-change="sizeChange"
			@next-click="nextClick"
			></el-pagination>
	
	</div>
</template>

<script>
	import config from '@/config/config'
	export default {
		data() {
			return {
				layouts: '',
				baseUrl: config.baseUrl,
				formSearch: {
					name: ''
				},
				storeupType: 1,
				storeupList: [],
				total: 1,
				pageSize: 8,
				pageSizes: [],
				totalPage: 1
			}
		},
		created() {
			this.storeupType = localStorage.getItem('storeupType');
			this.getStoreupList(1);
		},
		methods: {
			backClick() {
				this.$router.push('/index/center')
			},
			getStoreupList(page) {
				let params = {page, limit: this.pageSize, type: this.storeupType, userid: Number(localStorage.getItem('frontUserid')),sort:"addtime",order:"desc"};
				let searchWhere = {
				};
				if (this.formSearch.name != '') searchWhere.name = '%' + this.formSearch.name + '%';
				this.$http.get('storeup/list', {params: Object.assign(params, searchWhere)}).then(res => {
					if (res.data.code == 0) {
						this.storeupList = res.data.data.list;
						this.total = res.data.data.total;
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
						if(this.pageSizes.length==0){
							this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
						}
					}
				});
			},
			curChange(page) {
				this.getStoreupList(page);
			},
			prevClick(page) {
				this.getStoreupList(page);
			},
			sizeChange(size){
				this.pageSize = size
				this.getStoreupList(1);
			},
			nextClick(page) {
				this.getStoreupList(page);
			},
			toDetail(item) {
				this.$router.push({path: `/index/${item.tablename}Detail`, query: {id:item.refid,storeupType:1}});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.section {
		width: 900px;
		margin: 0 auto;
	}

	.formSearch {
		text-align: right;
	}
	.image {
		height: 233px;
		width: 100%;
		display: block;
	}
	
</style>
