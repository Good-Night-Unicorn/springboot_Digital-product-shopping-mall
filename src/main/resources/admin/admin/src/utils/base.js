const base = {
    get() {
        return {
            url : "http://localhost:8080/springbootg47d9qwc/",
            name: "springbootg47d9qwc",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springbootg47d9qwc/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "基于SpringBoot+Vue的数码产品购物商城的设计与实现"
        } 
    }
}
export default base
