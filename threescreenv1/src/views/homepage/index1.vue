<template>
    <div>
        <el-container class="homepagecontainer">
<!--            <el-header class="elheader1"><span class="headerspan1">党建三联屏后台</span></el-header>-->
            <el-container>
                <el-aside class="elaside1">
                    <el-menu class="elmenu1" :default-openeds="activeIndex"
                             background-color="#001529"
                             active-text-color="#ffd04b">
                        <el-menu-item class="elmenusystemtitle">
                            <el-row class="elrow1">党建三联屏后台</el-row>
                        </el-menu-item>
                        <el-menu-item index="1">
<!--                            <span><el-image class="elimg1" :src="require(`@/assets/btn1ico.png`)"></el-image></span>-->
                            <span><router-link tag="li" class="routerdecorate" to="/index1/page1index1"><el-image class="elimg1" :src="require(`@/assets/btn1ico.png`)"></el-image>学校党建</router-link></span>
                        </el-menu-item>
                        <el-menu-item index="2">
<!--                            <span><el-image class="elimg1" :src="require(`@/assets/btn2ico.png`)"></el-image></span>-->
                            <span><router-link tag="li" class="routerdecorate" to="/index1/page2index1"><el-image class="elimg1" :src="require(`@/assets/btn2ico.png`)"></el-image>党建学习</router-link></span>
                        </el-menu-item>
                        <el-menu-item index="3">
<!--                            <span><el-image class="elimg1" :src="require(`@/assets/btn3ico.png`)"></el-image></span>-->
                            <span><router-link tag="li" class="routerdecorate" to="/index1/page3index1"><el-image class="elimg1" :src="require(`@/assets/btn3ico.png`)"></el-image>我爱你中国</router-link></span>
                        </el-menu-item>
                    </el-menu>

                </el-aside>
                <el-main>
                    <el-row class="elmainelrow1">
                        <el-input type="hidden"></el-input>
                        <el-input type="hidden"></el-input>
                    </el-row>
<!--                    <div class="elmaindiv1">-->
<!--                        <div class="elmiandiv1_1">-->
<!--                            <span style="font-size: 1.2vw;font-weight: bolder; color: #457DDD;">|</span>-->
<!--                            <span style="color: #457DDD; font-size: 0.9vw; margin-left: 0.4vw" v-model="dashboardfirst">{{dashboardfirst}}</span>-->
<!--                            <span style="font-size: 1.0vw; color: #457DDD;">／</span>-->
<!--                            <span>{{dashboardsecond}}</span>-->
<!--                        </div>-->
<!--                    </div>-->
                    <!--                    <div class="elmaindiv2"><router-view></router-view></div>-->

                    <!--                    套个div和transition做加载动画-->
                    <div class="elmaindiv2">
                        <transition name="slide-fade">
                            <router-view></router-view>
                        </transition>
                    </div>


                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "index1",
        data() {
            return {
                activeIndex: ["1"], // 设置了默认展开菜单是空
            }
        },
        watch: {

        },
        methods: {

        }
    }
</script>

<style scoped>

    .homepagecontainer {
        width: 100vw;
        /*height: 100vh;*/
    }

    .el-header {
        background-color: #B3C0D1;
        color: #333;
        text-align: left;
        line-height: 60px;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        /*line-height: 200px;*/
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
        padding: 0;
        /*line-height: 160px;*/
    }


    .elmaindiv2 {
        border: 1px solid #FFFFFF;
        /*margin-left: 1vw;*/
        /*margin-top: 1.5vh;*/
        width: 87.5vw;
        /*height: 85.4vh;*/
        margin: 1.5vh 1vw 1.2vh 1vw; /*两个参数就是上下 左右 四个就是上右下左*/
        background-color: #FFFFFF;
    }

    /*header的样式*/
    .elheader1 {
        background-color: RGB(255,179,73);
    }

    /*header里面左边字的样式*/
    .headerspan1 {
        width: 7.6vw;
        height: 1.7vh;
        font-size: 0.9vw;
        font-family: Source Han Sans SC;
        font-weight: 400;
        color: #FFFFFF;
        line-height: 2.7vh;
    }

    /*aside的navmenu菜单的样式*/
    .elmenu1 {
        width: 10.45vw;
        height: 100vh;
        text-align: left;
        color: #FFFFFF;

    }


    /*aside的样式*/
    .elaside1 {
        width: 10.4vw!important;
        background-color: #002140;
        box-shadow: 5px 0px 14px 5px rgba(0, 0, 0, 0.19);
        -webkit-box-shadow: 5px 0px 14px 5px rgba(0, 0, 0, 0.19);
        -moz-box-shadow: 5px 0px 14px 5px rgba(0, 0, 0, 0.19);
        -o-box-shadow: 5px 0px 14px 5px rgba(0, 0, 0, 0.19);
        z-index: 1;


        overflow-x: hidden;
    }

    /*顶部导航树路径div*/
    .elmiandiv1_1 {
        margin: 1.2vh 0 0 1vw;
    }


    /*切换动画1 给transform上一层div加class名字index-content节课*/
    .index-content{
        /*width: 100%;*/
        /*//解决出现横向滚动条问题*/
        overflow-x: hidden;

        border: 1px solid #FFFFFF;
        /*margin-left: 1vw;*/
        /*margin-top: 1.5vh;*/
        width: 87.5vw;
        /*height: 85.4vh;*/
        margin: 1.5vh 1vw 1.2vh 1vw; /*两个参数就是上下 左右 四个就是上右下左*/
        background-color: #FFFFFF;

    }
    .v-enter{
        opacity: 0.2;
        transform: translateX(0%);
    }
    .v-leave-to{
        opacity: 0.2;
        transform: translateX(100%);
        /*// 解决页面从上往下位移问题*/
        position: absolute;
    }
    .v-enter-active,.v-leave-active{
        transition: all 1.5s ease;
    }
    /*切换动画1结束 给transform上一层div加class名字index-content节课*/



    /*切换动画2 直接给transform加name=slide-fade即可，包住routervoiew*/
    .slide-fade{
        position: absolute;left:0;right: 0;
    }
    .slide-fade-enter-active {
        transition: all 1.2s ease;
    }
    .slide-fade-leave-active {

        transition: all .1s cubic-bezier(2.0, 0.5, 0.8, 1.0);
    }
    .slide-fade-enter, .slide-fade-leave-to
    {
        left:0;right: 0;
        transform: translateX(50px);
        opacity: 0;
    }
    /*切换动画2结尾 直接给transform加name=slide-fade即可，包住routervoiew*/

    /*routerlink去除下划线颜色*/
    .routerdecorate {
        text-decoration: none;
        color: #FFFFFF;
    }

    /*三联屏标题*/
    .elrow1 {
        font-size: 1vw;
        font-weight: 500;
        font-family: Source Han Sans SC;
        color: #FFFFFF;
    }

    /*标题外包el menu item背景色*/
    .elmenusystemtitle {
        background-color: #002140!important;
    }

    /*设置选中时颜色*/
    .el-menu-item.is-active {
        background-color: #3370ff !important;
        color: #fff;
    }

    /*图标位置*/
    .elimg1 {
        margin: 0 0.4vh 0 0;
        height: 1.8vh;
    }

    /*elmain里面顶部空白div*/
    .elmainelrow1 {
        background-color: #FFFFFF;
    }

</style>