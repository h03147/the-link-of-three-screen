<template>
    <div>
        <div class="elshowtablediv">
            <div>
                <el-table
                        class="eltable1"
                        :data="btn2tableData"
                        :header-cell-style="{background:'#F2F9FF', 'text-align':'center'}"
                        :cell-style="{'text-align':'center'}"
                        border
                        style="width: 85vw">
                    <el-table-column
                            prop="secondmenu"
                            width="162"
                            label="二级菜单">
                    </el-table-column>
                    <el-table-column
                            prop="leftscreenurl"
                            :show-overflow-tooltip="true"
                            label="左屏网址">
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
                            prop="rightscreencontext"
                            :show-overflow-tooltip="true"
                            label="右屏文字">
                    </el-table-column>

                    <el-table-column label="操作" width="128">
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
                // 条件搜索查询出来的表单数据
                btn2tableData: [],
                // 清空当前行数据用的
                ruleForm: {
                    id: '',
                    secondmenu: '',
                    leftscreenurl: null,
                    rightscreenimg: null,
                    rightscreencontext: null
                }
            };
        },
        methods: {
            // 按行跳转去编辑页面
            handleEdit(row) {
                this.$router.push({
                    path: '/page2updatefile',
                    //需要传递的参数
                    query: {
                        id: row.id,
                        rightscreenimg: row.rightscreenimg
                    }
                })

            },
            // 删除按钮
            handleDelete(row) {
                const _this = this;
                this.$confirm('此操作执行后不可撤销, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.ruleForm.id = row.id;
                    this.ruleForm.secondmenu = row.secondmenu;
                    axios.put(api123.baseURL + '/btn2file/btn2updateById', this.ruleForm).then(function (resp) {
                        // console.log(resp.data);
                    });
                    this.$message({
                        type: 'success',
                        message: '重置成功!'
                    });
                    // this.reload(); 调用注入的方法达到刷新router-view的目的
                    // location.reload();//页面刷新

                    //延迟1秒来缓存数据
                    setTimeout( function(){
                        location.reload();
                    }, 1 * 1000 );//延迟1000毫米

                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消重置'
                    });
                });
            }
        },
        created() {
            const _this = this;
            axios.get(api123.baseURL + '/btn2file/btn2findAll').then(function (resp) {
                    _this.btn2tableData = resp.data;
            });
        }
    }
</script>

<style scoped>
    /*查询出来的表格*/
    .elshowtablediv {
        margin: 1.9vh 0 1.9vh 1.2vw;
    }

</style>