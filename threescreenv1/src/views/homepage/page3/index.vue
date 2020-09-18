<template>
    <div>
        <div class="divinputinsert">
            <el-form :inline="true" :model="btn3findFiletableData" class="demo-form-inline">
                <el-form-item :label="menuselectData[0]" class="elformitemtemplate">
                    <el-select size="small" v-model="btn3findFiletableData.secondmenu" :placeholder="placeholderData[0]">
                        <el-option v-for="item in secondmenuData" :key="item" :label="item" :value="item"></el-option>
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
                        :data="btn3tableData"
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
                            prop="leftscreenvideo"
                            label="左屏视频">

                        <template slot-scope="scope" v-if="scope.row.leftscreenvideo != null">
                            <el-popover placement="right" title="" trigger="hover">
                                <!--                                悬浮窗内的视频-->
                                <video controls :src="scope.row.leftscreenvideo" style="max-height: 27.8vh;max-width: 40.6vw" preload="none" :poster="globalURL + '/btn1files/timg.gif'"></video>
                                <!--                                表单中的视频-->
                                <video slot="reference" :src="scope.row.leftscreenvideo" :alt="scope.row.leftscreenvideo" style="max-height: 4.6vh;max-width: 6.8vw">视频</video>
                            </el-popover>
                        </template>

                    </el-table-column>
                    <el-table-column
                            prop="leftscreencontext"
                            :show-overflow-tooltip="true"
                            label="左屏文字">
                    </el-table-column>
                    <el-table-column
                            prop="rightscreenurl"
                            :show-overflow-tooltip="true"
                            label="右屏网址">
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
                btn3findFiletableData: {
                    secondmenu: null
                },
                // 菜单选项
                menuselectData: ['二级菜单'],
                // 输入框提示内容
                placeholderData: ['请选择'],
                // 二级下拉菜单内容
                secondmenuData: ['开天辟地','筑梦中国','伟大复兴'],
                // 条件搜索查询出来的表单数据
                btn3tableData: []
            }
        },
        methods: {
            // 条件检索按钮事件
            selectByCondition() {
                const _this = this;
                axios.post(api123.baseURL + '/btn3file/findbycondition', this.btn3findFiletableData).then(function (resp) {
                    console.log(resp.data.ListData)
                    if(resp.data.ListData == '请先选择查询条件')
                    {
                        _this.$message.error("请先选择查询条件");
                    }else
                    {
                        _this.btn3tableData = resp.data.ListData;
                        _this.$message.success("条件查询成功");
                    }
                });
            },
            // 跳转去添加页按钮
            toAddnNewFilePage() {
                if(this.btn3findFiletableData.secondmenu != null)
                {
                    this.$router.push({
                        path: '/page3addnewfile',
                        query: {
                            secondmenu: this.btn3findFiletableData.secondmenu
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
                    path: '/page3updatefile',
                    //需要传递的参数
                    query: {
                        id: row.id,
                        leftscreenvideo: row.leftscreenvideo,
                        leftscreenminiimg: row.leftscreenminiimg,
                        secondmenu: this.btn3findFiletableData.secondmenu
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
                    axios.delete(api123.baseURL + '/btn3file/deleteByFileId/' + row.id).then(function (resp) {
                        // console.log(resp.data);
                    });
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                    // this.reload(); 调用注入的方法达到刷新router-view的目的
                    sessionStorage.setItem('savesecondmenu', this.btn3findFiletableData.secondmenu);
                    location.reload();//页面刷新
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            }
        },
        created() {
            if(sessionStorage.getItem('savesecondmenu') != null)
            {
                this.btn3findFiletableData.secondmenu = sessionStorage.getItem('savesecondmenu');
            }else
            {
                this.btn3findFiletableData.secondmenu = this.$route.query.backsecondmenu
            }

            const _this = this;
            axios.post(api123.baseURL + '/btn3file/findbycondition', this.btn3findFiletableData).then(function (resp) {
                console.log(resp.data.ListData)
                if(resp.data.ListData == '请先选择查询条件')
                {
                    _this.$message.error("请先选择查询条件");
                }else
                {
                    _this.btn3tableData = resp.data.ListData;
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