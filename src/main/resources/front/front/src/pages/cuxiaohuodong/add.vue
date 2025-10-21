<template>
	<div class="add-update-preview">
		<el-form
			class="add-update-form"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="180px"
			>
			<el-form-item class="add-item" label="产品名称" prop="chanpinmingcheng">
				<el-input v-model="ruleForm.chanpinmingcheng" 
					placeholder="产品名称" clearable :disabled=" false  ||ro.chanpinmingcheng"></el-input>
			</el-form-item>
			<el-form-item class="add-item"  label="产品类型" prop="chanpinleixing">
				<el-select v-model="ruleForm.chanpinleixing" placeholder="请选择产品类型" :disabled=" false  ||ro.chanpinleixing" >
					<el-option
						v-for="(item,index) in chanpinleixingOptions"
						:key="index"
						:label="item"
						:value="item">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item class="add-item" label="品牌" prop="pinpai">
				<el-input v-model="ruleForm.pinpai" 
					placeholder="品牌" clearable :disabled=" false  ||ro.pinpai"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="型号" prop="xinghao">
				<el-input v-model="ruleForm.xinghao" 
					placeholder="型号" clearable :disabled=" false  ||ro.xinghao"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="产品图片" v-if="type!='cross' || (type=='cross' && !ro.chanpintupian)" prop="chanpintupian">
				<file-upload
					tip="点击上传产品图片"
					action="file/upload"
					:limit="3"
					:multiple="true"
					:fileUrls="ruleForm.chanpintupian?ruleForm.chanpintupian:''"
					@change="chanpintupianUploadChange"
					></file-upload>
			</el-form-item>
			<el-form-item class="add-item" v-else label="产品图片" prop="chanpintupian">
				<img v-if="ruleForm.chanpintupian.substring(0,4)=='http'" class="upload-img" v-bind:key="index" :src="ruleForm.chanpintupian.split(',')[0]">
				<img v-else class="upload-img" v-bind:key="index" v-for="(item,index) in ruleForm.chanpintupian.split(',')" :src="baseUrl+item">
			</el-form-item>
			<el-form-item class="add-item" label="店铺名称" prop="dianpumingcheng">
				<el-input v-model="ruleForm.dianpumingcheng" 
					placeholder="店铺名称" clearable :disabled=" false  ||ro.dianpumingcheng"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="单限" prop="onelimittimes">
				<el-input v-model.number="ruleForm.onelimittimes" 
					placeholder="单限" clearable :disabled=" false  ||ro.onelimittimes"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="库存" prop="alllimittimes">
				<el-input v-model.number="ruleForm.alllimittimes" 
					placeholder="库存" clearable :disabled=" false  ||ro.alllimittimes"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="倒计结束时间" prop="reversetime">
				<el-date-picker
					:disabled=" false  ||ro.reversetime"
					value-format="yyyy-MM-dd HH:mm:ss"
					v-model="ruleForm.reversetime" 
					type="datetime"
					placeholder="倒计结束时间">
				</el-date-picker>
			</el-form-item>
			<el-form-item class="add-item" label="积分" prop="jf">
				<el-input v-model.number="ruleForm.jf" 
					placeholder="积分" clearable :disabled=" false  ||ro.jf"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="价格" prop="price">
				<el-input-number v-model="ruleForm.price" placeholder="价格" :disabled=" false ||ro.price"></el-input-number>
			</el-form-item>
			<el-form-item class="add-item" label="产品介绍" prop="chanpinjieshao">
				<editor 
					v-model="ruleForm.chanpinjieshao" 
					class="editor" 
					action="file/upload">
				</editor>
			</el-form-item>

			<el-form-item class="add-btn-item">
				<el-button class="submitBtn"  type="primary" @click="onSubmit">
					<span class="icon iconfont icon-kaitongfuwu"></span>
					<span class="text">确定</span>
				</el-button>
				<el-button class="closeBtn" @click="back()">
					<span class="icon iconfont icon-shanchu1"></span>
					<span class="text">取消</span>
				</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				id: '',
				baseUrl: '',
				ro:{
					chanpinmingcheng : false,
					chanpinleixing : false,
					pinpai : false,
					xinghao : false,
					chanpinjieshao : false,
					chanpintupian : false,
					dianpumingcheng : false,
					onelimittimes : false,
					alllimittimes : false,
					thumbsupnum : false,
					crazilynum : false,
					clicktime : false,
					reversetime : false,
					clicknum : false,
					discussnum : false,
					jf : false,
					price : false,
					onshelves : false,
					vipprice : false,
					storeupnum : false,
				},
				type: '',
				userTableName: localStorage.getItem('UserTableName'),
				ruleForm: {
					chanpinmingcheng: '',
					chanpinleixing: '',
					pinpai: '',
					xinghao: '',
					chanpinjieshao: '',
					chanpintupian: '',
					dianpumingcheng: '',
					onelimittimes: Number('-1') ,
					alllimittimes: Number('-1') ,
					thumbsupnum: '',
					crazilynum: '',
					clicktime: '',
					reversetime: '',
					clicknum: '',
					discussnum: '',
					jf: '',
					price: '',
					onshelves: '',
					vipprice: '',
					storeupnum: '',
				},
				chanpinleixingOptions: [],


				rules: {
					chanpinmingcheng: [
					],
					chanpinleixing: [
					],
					pinpai: [
					],
					xinghao: [
					],
					chanpinjieshao: [
					],
					chanpintupian: [
					],
					dianpumingcheng: [
					],
					onelimittimes: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					alllimittimes: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					thumbsupnum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					crazilynum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					clicktime: [
					],
					reversetime: [
					],
					clicknum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					discussnum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					jf: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					price: [
						{ required: true, message: '价格不能为空', trigger: 'blur' },
						{ validator: this.$validate.isNumber, trigger: 'blur' },
					],
					onshelves: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					vipprice: [
						{ validator: this.$validate.isNumber, trigger: 'blur' },
					],
					storeupnum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
				},
				centerType: false,
			};
		},
		computed: {



		},
		components: {
		},
		created() {
			if(this.$route.query.centerType){
				this.centerType = true
			}
			//this.bg();
			let type = this.$route.query.type ? this.$route.query.type : '';
			this.init(type);
			this.baseUrl = this.$config.baseUrl;
		},
		methods: {
			getMakeZero(s) {
				return s < 10 ? '0' + s : s;
			},
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(type) {
				this.type = type;
				if(type=='cross'){
					var obj = JSON.parse(localStorage.getItem('crossObj'));
					for (var o in obj){
						if(o=='chanpinmingcheng'){
							this.ruleForm.chanpinmingcheng = obj[o];
							this.ro.chanpinmingcheng = true;
							continue;
						}
						if(o=='chanpinleixing'){
							this.ruleForm.chanpinleixing = obj[o];
							this.ro.chanpinleixing = true;
							continue;
						}
						if(o=='pinpai'){
							this.ruleForm.pinpai = obj[o];
							this.ro.pinpai = true;
							continue;
						}
						if(o=='xinghao'){
							this.ruleForm.xinghao = obj[o];
							this.ro.xinghao = true;
							continue;
						}
						if(o=='chanpinjieshao'){
							this.ruleForm.chanpinjieshao = obj[o];
							this.ro.chanpinjieshao = true;
							continue;
						}
						if(o=='chanpintupian'){
							this.ruleForm.chanpintupian = obj[o].split(",")[0];
							this.ro.chanpintupian = true;
							continue;
						}
						if(o=='dianpumingcheng'){
							this.ruleForm.dianpumingcheng = obj[o];
							this.ro.dianpumingcheng = true;
							continue;
						}
						if(o=='onelimittimes'){
							this.ruleForm.onelimittimes = obj[o];
							this.ro.onelimittimes = true;
							continue;
						}
						if(o=='alllimittimes'){
							this.ruleForm.alllimittimes = obj[o];
							this.ro.alllimittimes = true;
							continue;
						}
						if(o=='thumbsupnum'){
							this.ruleForm.thumbsupnum = obj[o];
							this.ro.thumbsupnum = true;
							continue;
						}
						if(o=='crazilynum'){
							this.ruleForm.crazilynum = obj[o];
							this.ro.crazilynum = true;
							continue;
						}
						if(o=='clicktime'){
							this.ruleForm.clicktime = obj[o];
							this.ro.clicktime = true;
							continue;
						}
						if(o=='reversetime'){
							this.ruleForm.reversetime = obj[o];
							this.ro.reversetime = true;
							continue;
						}
						if(o=='clicknum'){
							this.ruleForm.clicknum = obj[o];
							this.ro.clicknum = true;
							continue;
						}
						if(o=='discussnum'){
							this.ruleForm.discussnum = obj[o];
							this.ro.discussnum = true;
							continue;
						}
						if(o=='jf'){
							this.ruleForm.jf = obj[o];
							this.ro.jf = true;
							continue;
						}
						if(o=='price'){
							this.ruleForm.price = obj[o];
							this.ro.price = true;
							continue;
						}
						if(o=='onshelves'){
							this.ruleForm.onshelves = obj[o];
							this.ro.onshelves = true;
							continue;
						}
						if(o=='vipprice'){
							this.ruleForm.vipprice = obj[o];
							this.ro.vipprice = true;
							continue;
						}
						if(o=='storeupnum'){
							this.ruleForm.storeupnum = obj[o];
							this.ro.storeupnum = true;
							continue;
						}
					}
				}else if(type=='edit'){
					this.info()
				}
				// 获取用户信息
				this.$http.get(this.userTableName + '/session', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						var json = res.data.data;
						if((json.dianpumingcheng!=''&&json.dianpumingcheng) || json.dianpumingcheng==0){
							this.ruleForm.dianpumingcheng = json.dianpumingcheng;
							this.ro.dianpumingcheng = true;
						}
					}
				});
				this.$http.get('option/chanpinleixing/chanpinleixing', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.chanpinleixingOptions = res.data.data;
					}
				});

				if (localStorage.getItem('raffleType') && localStorage.getItem('raffleType') != null) {
					localStorage.removeItem('raffleType')
					setTimeout(() => {
						this.onSubmit()
					}, 300)
				}
			},

			// 多级联动参数
			// 多级联动参数
			info() {
				this.$http.get(`cuxiaohuodong/detail/${this.$route.query.id}`, {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.ruleForm = res.data.data;
					}
				});
			},
			// 提交
			async onSubmit() {
				if(this.ruleForm.price<0){
					this.$message.error("价格不能输入负数");
					return
				}
				if(this.ruleForm.vipprice<0){
					this.$message.error("VIP价格不能输入负数");
					return
				}
				if(this.ruleForm.alllimittimes<0){
					this.$message.error("库存不能输入负数");
					return
				}
				if(this.ruleForm.onelimittimes<0){
					this.$message.error("单次购买不能输入负数");
					return
				}
				await this.$refs["ruleForm"].validate(async valid => {
					if(valid) {
						if(this.type=='cross'){
							var statusColumnName = localStorage.getItem('statusColumnName');
							var statusColumnValue = localStorage.getItem('statusColumnValue');
							if(statusColumnName && statusColumnName!='') {
								var obj = JSON.parse(localStorage.getItem('crossObj'));
								if(!statusColumnName.startsWith("[")) {
									for (var o in obj){
										if(o==statusColumnName){
											obj[o] = statusColumnValue;
										}
									}
									var table = localStorage.getItem('crossTable');
									await this.$http.post(table+'/update', obj).then(res => {});
								}
							}
						}


						await this.$http.post(`cuxiaohuodong/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(async res => {
							if (res.data.code == 0) {
								this.$message({
									message: '操作成功',
									type: 'success',
									duration: 1500,
									onClose: () => {
										this.$router.go(-1);
										
									}
								});
							} else {
								this.$message({
									message: res.data.msg,
									type: 'error',
									duration: 1500
								});
							}
						});
					}
				});
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 返回
			back() {
				this.$router.go(-1);
			},
			chanpintupianUploadChange(fileUrls) {
				this.ruleForm.chanpintupian = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
			},
		}
	};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.add-update-preview {
		padding: 0 0 20px;
		margin: 0px auto;
		color: #666;
		background: #fff;
		width: 1200px;
		font-size: 16px;
		position: relative;
		.add-update-form {
			margin: 20px 0 0;
			width: 100%;
			position: relative;
			.add-item.el-form-item {
				padding: 0 0 10px;
				margin: 0 0 10px;
				background: none;
				border-color: #ccc;
				border-width: 0 0 1px;
				border-style: dashed;
				/deep/ .el-form-item__label {
					padding: 0 10px 0 0;
					color: #000000;
					font-weight: 500;
					width: 180px;
					font-size: inherit;
					line-height: 40px;
					text-align: center;
				}
				/deep/ .el-form-item__content {
					margin-left: 180px;
				}
				.el-input {
					width: auto;
				}
				.el-input /deep/ .el-input__inner {
					border: 1px solid #ddd;
					border-radius: 0px;
					padding: 0 12px;
					box-shadow: none;
					color: inherit;
					width: auto;
					font-size: 16px;
					height: 40px;
				}
				.el-input /deep/ .el-input__inner[readonly="readonly"] {
					border: 0;
					cursor: not-allowed;
					border-radius: 0px;
					padding: 0 12px;
					box-shadow: none;
					color: rgba(85, 85, 127, 1.0);
					background: none;
					width: auto;
					font-size: 16px;
					height: 40px;
				}
				.el-input-number /deep/ .el-input__inner {
					text-align: left;
					border: 1px solid #ddd;
					border-radius: 0px;
					padding: 0 12px;
					box-shadow: none;
					color: inherit;
					width: auto;
					font-size: 16px;
					height: 40px;
				}
				.el-input-number /deep/ .is-disabled .el-input__inner {
					text-align: left;
					border: 0;
					cursor: not-allowed;
					border-radius: 0px;
					padding: 0 12px;
					box-shadow: none;
					color: rgba(85, 85, 127, 1.0);
					background: none;
					width: auto;
					font-size: 16px;
					height: 40px;
				}
				.el-input-number /deep/ .el-input-number__decrease {
					display: none;
				}
				.el-input-number /deep/ .el-input-number__increase {
					display: none;
				}
				.el-select {
					width: auto;
				}
				.el-select /deep/ .el-input__inner {
					border: 1px solid #ddd;
					border-radius: 0px;
					padding: 0 10px;
					color: inherit;
					width: 100%;
					font-size: 16px;
					min-width: inherit !important;
					height: 40px;
				}
				.el-select /deep/ .is-disabled .el-input__inner {
					border: 0;
					cursor: not-allowed;
					border-radius: 0px;
					padding: 0 10px;
					box-shadow: none;
					color: inherit;
					background: none;
					width: 100%;
					font-size: 16px;
					height: 40px;
				}
				.el-date-editor {
					width: auto;
				}
				.el-date-editor /deep/ .el-input__inner {
					border: 1px solid #ddd;
					border-radius: 0px;
					padding: 0 10px 0 30px;
					box-shadow: none;
					color: inherit;
					width: auto;
					font-size: 16px;
					height: 40px;
				}
				.el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
					border: 0;
					cursor: not-allowed;
					border-radius: 0px;
					padding: 0 10px 0 30px;
					box-shadow: none;
					color: inherit;
					background: none;
					width: auto;
					font-size: 16px;
					height: 40px;
				}
				/deep/ .el-upload--picture-card {
					background: transparent;
					border: 0;
					border-radius: 0;
					width: auto;
					height: auto;
					line-height: initial;
					vertical-align: middle;
				}
				/deep/ .upload .upload-img {
					border: 1px solid #ddd;
					cursor: pointer;
					border-radius: 0px;
					color: #999;
					background: #fff;
					object-fit: cover;
					width: 80px;
					font-size: 26px;
					line-height: 60px;
					text-align: center;
					height: 60px;
				}
				/deep/ .el-upload-list .el-upload-list__item {
					border: 1px solid #ddd;
					cursor: pointer;
					border-radius: 0px;
					color: #999;
					background: #fff;
					object-fit: cover;
					width: 80px;
					font-size: 26px;
					line-height: 60px;
					text-align: center;
					height: 60px;
					font-size: 14px;
					line-height: 1.8;
				}
				/deep/ .el-upload .el-icon-plus {
					border: 1px solid #ddd;
					cursor: pointer;
					border-radius: 0px;
					color: #999;
					background: #fff;
					object-fit: cover;
					width: 80px;
					font-size: 26px;
					line-height: 60px;
					text-align: center;
					height: 60px;
				}
				/deep/ .el-upload__tip {
					color: #888;
					font-size: 16px;
				}
				.el-textarea /deep/ .el-textarea__inner {
					border: 1px solid #ddd;
					border-radius: 0px;
					padding: 12px;
					box-shadow: none;
					color: inherit;
					width: auto;
					font-size: 16px;
					min-height: 150px;
					min-width: 48%;
					height: auto;
				}
				.el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
					border: 0px solid #ddd;
					cursor: not-allowed;
					border-radius: 0px;
					padding: 12px;
					box-shadow: none;
					color: inherit;
					background: none;
					width: auto;
					font-size: 16px;
					min-height: 150px;
					min-width: 50%;
					height: auto;
				}
				/deep/ .el-input__inner::placeholder {
					color: #9E9E9E;
					font-size: inherit;
				}
				/deep/ textarea::placeholder {
					color: #9E9E9E;
					font-size: inherit;
				}
				.editor {
					background-color: #fff;
					border-radius: 0;
					padding: 0;
					box-shadow: none;
					margin: 0;
					width: 80%;
					min-height: 250px;
					border-color: #ccc;
					border-width: 1px;
					border-style: solid;
					height: auto;
				}
				.upload-img {
					object-fit: cover;
					width: 100px;
					height: 100px;
				}
				.viewBtn {
					border: 0;
					cursor: pointer;
					border-radius: 0px;
					padding: 0 20px;
					margin: 0;
					color: #333;
					background: #03abe930;
					display: inline-block;
					width: auto;
					font-size: 14px;
					line-height: 34px;
					height: 34px;
				}
				.viewBtn:hover {
				}
				.unviewBtn {
					border: 0;
					cursor: pointer;
					padding: 0 20px;
					margin: 0;
					color: #333;
					display: inline-block;
					font-size: 14px;
					line-height: 34px;
					border-radius: 0px;
					outline: none;
					background: #ddd;
					width: auto;
					height: 34px;
				}
				.unviewBtn:hover {
					background: #eee;
				}
			}
			.add-btn-item {
				padding: 0;
				margin: 30px 0;
				width: 100%;
				text-align: center;
				.submitBtn {
					border: 0;
					cursor: pointer;
					padding: 0 15px;
					margin: 0 20px 0 0;
					display: inline-block;
					font-size: 16px;
					line-height: 40px;
					border-radius: 2px;
					background: #66B9BB;
					width: auto;
					text-align: center;
					min-width: 120px;
					height: 40px;
					.icon {
						color: #fff;
					}
					.text {
						color: #fff;
					}
				}
				.submitBtn:hover {
					opacity: 0.8;
					.icon {
					}
					.text {
					}
				}
				.closeBtn {
					border: 0px solid #ddd;
					cursor: pointer;
					padding: 0 15px;
					margin: 0 100px 0 0;
					display: inline-block;
					font-size: 16px;
					line-height: 40px;
					border-radius: 2px;
					background: #858585;
					width: auto;
					text-align: center;
					min-width: 110px;
					height: 40px;
					.icon {
						color: #fff;
					}
					.text {
						color: #fff;
					}
				}
				.closeBtn:hover {
					opacity: 0.8;
					.icon {
					}
					.text {
					}
				}
			}
		}
	}
	.el-date-editor.el-input {
		width: auto;
	}
</style>
