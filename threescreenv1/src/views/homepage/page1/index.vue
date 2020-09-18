<template>
    <div>
        <div class="divinputinsert">
            <el-form :inline="true" :model="btn1findFiletableData" class="demo-form-inline">
                <el-form-item :label="menuselectData[0]" class="elformitemtemplate">
                    <el-select size="small" v-model="btn1findFiletableData.secondmenu" :placeholder="placeholderData[0]"
                                @change="currStationChange">
                        <el-option v-for="item in secondmenuData" :key="item" :label="item" :value="item"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item :label="menuselectData[1]" class="elformitemtemplate">
                    <el-select size="small" v-model="btn1findFiletableData.thirdmenu" :placeholder="placeholderData[0]"
                               :disabled="showPanelthird"
                               @change="currStationChangethird">
                        <el-option v-for="item in thirdmenuData" :key="item" :label="item" :value="item"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item :label="menuselectData[2]" class="elformitemtemplate">
                    <el-select size="small" v-model="btn1findFiletableData.fourthmenu" :placeholder="placeholderData[0]"
                               :disabled="showPanelfourth"
                               @change="currStationChangefourth">
                        <el-option v-for="item in fourthmenuData" :key="item" :label="item" :value="item"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item :label="menuselectData[3]" class="elformitemtemplate">
                    <el-select size="small" v-model="btn1findFiletableData.fifthmenu" :placeholder="placeholderData[0]" :disabled="showPanelfifth">
                        <el-option v-for="item in fifthmenuData" :key="item" :label="item" :value="item"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item class="elformitemtemplate">
                    <el-button size="small" type="primary" style="width:5.2vw; margin-left: 3.2vw" @click="selectByCondition()">检索</el-button>
                </el-form-item>
            </el-form>
        </div>

        <!-- 分割线-->
        <HR class="hrline"></HR>

        <div class="addbtndiv1">
            <el-row>
                <el-button size="small" type="primary" @click="toAddnNewFilePage()">+添加新内容</el-button>
            </el-row>
        </div>

        <div class="elshowtablediv">
            <div>
                <el-table
                        class="eltable1"
                        :data="btn1tableData"
                        :header-cell-style="{background:'#F2F9FF', 'text-align':'center'}"
                        :cell-style="{'text-align':'center'}"
                        style="width: 85vw">
                    <el-table-column
                            v-if="false"
                            prop="id"
                            label="编号"
                            width="100">
                    </el-table-column>
                    <el-table-column
                            label="序号"
                            type="index"
                            width="100">
                    </el-table-column>
                    <el-table-column
                            prop="leftscreenminiimg"
                            label="左屏缩略图">

                        <template slot-scope="scope">
                            <el-popover placement="right" title="" trigger="hover">
<!--                                悬浮窗内的图片-->
                                <img :src="scope.row.leftscreenminiimg" style="max-height: 27.8vh;max-width: 40.6vw"/>
<!--                                表单中的图片-->
                                <img slot="reference" :src="scope.row.leftscreenminiimg" :alt="scope.row.leftscreenminiimg" style="max-height: 4.6vh;max-width: 6.8vw">
                            </el-popover>
                        </template>

                    </el-table-column>
                    <el-table-column
                            prop="leftscreencontext"
                            :show-overflow-tooltip="true"
                            label="左屏文字">
                    </el-table-column>
                    <el-table-column
                            prop="rightscreenimg"
                            label="右屏图片">

                        <template slot-scope="scope">
                            <el-popover placement="right" title="" trigger="hover">
                                <!--                                悬浮窗内的图片-->
                                <img :src="scope.row.rightscreenimg" style="max-height: 27.8vh;max-width: 40.6vw"/>
                                <!--                                表单中的图片-->
                                <img slot="reference" :src="scope.row.rightscreenimg" :alt="scope.row.rightscreenimg" style="max-height: 4.6vh;max-width: 6.8vw">
                            </el-popover>
                        </template>

                    </el-table-column>
                    <el-table-column
                            prop="rightscreenvideo"
                            label="右屏视频">

                        <template slot-scope="scope" v-if="scope.row.rightscreenvideo != null">
                            <el-popover placement="right" title="" trigger="hover">
                                <!--                                悬浮窗内的视频-->
                                <video controls :src="scope.row.rightscreenvideo" style="max-height: 27.8vh;max-width: 40.6vw" preload="none" :poster="globalURL + '/btn1files/timg.gif'"></video>
                                <!--                                表单中的视频-->
                                <video slot="reference" :src="scope.row.rightscreenvideo" :alt="scope.row.rightscreenvideo" style="max-height: 4.6vh;max-width: 6.8vw">视频</video>
                            </el-popover>
                        </template>


                    </el-table-column>
                    <el-table-column
                            prop="rightscreencontext"
                            :show-overflow-tooltip="true"
                            label="右屏文字">
                    </el-table-column>

                    <el-table-column label="操作" width="200">
                        <template slot-scope="scope">
                            <el-button
                                    circle
                                    size="mini"
                                    type="primary"
                                    icon="el-icon-edit"
                                    @click="handleEdit(scope.row)"></el-button>
                            <el-button
                                    circle
                                    size="mini"
                                    type="danger"
                                    icon="el-icon-delete"
                                    @click="handleDelete(scope.row)"></el-button>
                        </template>
                    </el-table-column>

                </el-table>
            </div>
        </div>
    </div>
</template>

<script>
    import api123 from '@/api/global_variable.js'
    export default {
        name: "index",
        data() {
            return {
                globalURL: api123.baseURL, // 全局url
                // 查询检索下拉框
                btn1findFiletableData: {
                    secondmenu: null,
                    thirdmenu: null,
                    fourthmenu: null,
                    fifthmenu: null,
                },
                // 菜单选项
                menuselectData: ['二级菜单','三级菜单','四级菜单','五级菜单'],
                // 输入框提示内容
                placeholderData: ['请选择'],
                // 二级下拉菜单内容
                secondmenuData: ['校党委会','校纪委会','校党委办公室','支部建设'],
                // 三级下拉菜单内容
                thirdmenuData: ['行政党支部','教务党支部','学生党支部','基础党支部','商贸信息党支部','会计党支部','外语党支部','现代服务党支部','离，退休党支部'],
                // 四级下拉菜单内容
                fourthmenuData: ['支部发展','支部动态'],
                // 五级下拉菜单内容
                fifthmenuData: ['理论学习','三会一课','考察考核','德育活动','学工例会和教务例会'],
                // 条件搜索查询出来的表单数据
                btn1tableData: [],
                // 各级菜单disabled判断
                showPanelthird: true,
                showPanelfourth: true,
                showPanelfifth: true,
                // 添加成功后回传的参数接收
                backmenuselectData: {
                    secondmenu: null,
                    thirdmenu: null,
                    fourthmenu: null,
                    fifthmenu: null,
                }

            }
        },

        methods: {
            // 条件检索按钮事件
            selectByCondition() {
                const _this = this;
                axios.post(api123.baseURL + '/btn1file/findbycondition', this.btn1findFiletableData).then(function (resp) {
                    console.log(resp.data.ListData)
                    if(resp.data.ListData == '请先选择查询条件')
                    {
                        _this.$message.error("请先选择查询条件");
                    }else
                    {
                        _this.btn1tableData = resp.data.ListData;
                        _this.$message.success("条件查询成功");
                    }
                });
            },
            // 跳转去添加页按钮
            toAddnNewFilePage() {
                if(this.btn1findFiletableData.secondmenu != null)
                {
                    this.$router.push({
                        path: '/page1addnewfile',
                        query: {
                            secondmenu: this.btn1findFiletableData.secondmenu,
                            thirdmenu: this.btn1findFiletableData.thirdmenu,
                            fourthmenu: this.btn1findFiletableData.fourthmenu,
                            fifthmenu: this.btn1findFiletableData.fifthmenu
                        }
                    });
                }else
                {
                    this.$message.error("请先选择需要添加新内容的菜单路径！");
                }

            },
            // 按行跳转去编辑页面
            handleEdit(row) {
                this.$router.push({
                    path: '/page1updatefile',
                    //需要传递的参数
                    query: {
                        id: row.id,
                        leftscreenminiimg: row.leftscreenminiimg,
                        rightscreenimg: row.rightscreenimg,
                        rightscreenvideo: row.rightscreenvideo,
                        secondmenu: this.btn1findFiletableData.secondmenu,
                        thirdmenu: this.btn1findFiletableData.thirdmenu,
                        fourthmenu: this.btn1findFiletableData.fourthmenu,
                        fifthmenu: this.btn1findFiletableData.fifthmenu,
                    }
                })

            },
            // 删除按钮
            handleDelete(row) {
                const _this = this;
                this.$confirm('此操作将永久删除该留言且不可恢复, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    axios.delete(api123.baseURL + '/btn1file/deleteByFileId/' + row.id).then(function (resp) {
                        // console.log(resp.data);
                    });
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                    // this.reload(); 调用注入的方法达到刷新router-view的目的
                    sessionStorage.setItem('savesecondmenu', this.btn1findFiletableData.secondmenu);
                    sessionStorage.setItem('savethirdmenu', this.btn1findFiletableData.thirdmenu);
                    sessionStorage.setItem('savefourthmenu', this.btn1findFiletableData.fourthmenu);
                    sessionStorage.setItem('savefifthmenu', this.btn1findFiletableData.fifthmenu);
                    location.reload();//页面刷新
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            // 二级菜单监听
            currStationChange(val) {
                console.log("二级菜单选择了："+ val);
                if( val === '支部建设')
                {
                    this.showPanelthird = false;
                    this.showPanelfourth = false;
                }else
                {
                    this.btn1findFiletableData.thirdmenu = null;
                    this.btn1findFiletableData.fourthmenu = null;
                    this.btn1findFiletableData.fifthmenu = null;
                    this.showPanelthird = true;
                    this.showPanelfourth = true;
                    this.showPanelfifth = true;
                }
            },
            // 三级菜单监听
            currStationChangethird(val) {
            },
            // 四级菜单监听
            currStationChangefourth(val) {
                if( val === '支部动态')
                {
                    this.showPanelfifth = false;
                }else
                {
                    this.showPanelfifth = true;
                }
            }
        },
        created() {
            // this.backmenuselectData.secondmenu = this.$route.query.backsecondmenu
            if(sessionStorage.getItem('savesecondmenu') != null)
            {
                this.btn1findFiletableData.secondmenu = sessionStorage.getItem('savesecondmenu')
                this.btn1findFiletableData.thirdmenu = sessionStorage.getItem('savethirdmenu')
                this.btn1findFiletableData.fourthmenu = sessionStorage.getItem('savefourthmenu')
                this.btn1findFiletableData.fifthmenu = sessionStorage.getItem('savefifthmenu')
            }else
            {
                console.log(this.$route.query.backsecondmenu);
                this.btn1findFiletableData.secondmenu = this.$route.query.backsecondmenu;
                this.btn1findFiletableData.thirdmenu = this.$route.query.backthirdmenu;
                this.btn1findFiletableData.fourthmenu = this.$route.query.backfourthmenu;
                this.btn1findFiletableData.fifthmenu = this.$route.query.backfifthmenu;
            }
            if(this.btn1findFiletableData.secondmenu === '支部建设')
            {
                this.showPanelthird = false;
                this.showPanelfourth = false;
                this.showPanelfifth = false;
            }

            const _this = this;
            axios.post(api123.baseURL + '/btn1file/findbycondition', this.btn1findFiletableData).then(function (resp) {
                console.log(resp.data.ListData)
                if(resp.data.ListData == '请先选择查询条件')
                {
                    _this.$message.error("请先选择查询条件");
                }else
                {
                    _this.btn1tableData = resp.data.ListData;
                    _this.$message.success("条件查询成功");
                }
            });
            sessionStorage.clear();

        }
    }
</script>

<style scoped>

    /*表单内每个组件边距模板*/
    .elformitemtemplate {
        margin: 0 0 0 1.2vw;
    }

    /*分割线*/
    .hrline {
        width: 85vw;
        color: #CCCCCC;
        margin: 2.8vh 1.2vw;
        opacity: 0.3;
    }

    /*菜单条件筛选选择下拉框*/
    .divinputinsert {
        margin: 3.4vh 0 0 0;
    }

    /*新增按钮*/
    .addbtndiv1 {
        margin: 0 0 0 1.2vw;
    }

    /*查询出来的表格*/
    .elshowtablediv {
        margin: 1.9vh 0 0 1.2vw;
    }

</style>