<template>
    <div>
        <div class="pagetitlediv1"><span>{{pagetitle}}</span></div>
        <div class="headerlinediv1"><HR class="hrline"></HR></div>
        <div>
            <el-form :model="ruleForm" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="二级菜单:" prop="secondmenu" label-width="4.5vw">
                    <!--                <el-input v-model="ruleForm.secondmenu" disabled></el-input>-->
                    <el-row>{{ruleForm.secondmenu}}</el-row>
                </el-form-item>
                <el-form-item label="左屏网址:" prop="leftscreenurl" label-width="4.5vw">
                    <el-input class="inputleftcontext1"
                              type="text"
                              v-model="ruleForm.leftscreenurl"
                              maxlength="134"
                              placeholder="请输入文字内容(134个字以内)"
                              show-word-limit></el-input>
                </el-form-item>
                <el-form-item label="右屏图片:" label-width="4.5vw">
                    <el-upload
                            class="upload-demo"
                            drag
                            :action="globalURL + '/btn2file/upload'"
                            :on-preview="handlePreview"
                            :on-remove="handleRemove"
                            :before-remove="beforeRemove"
                            :before-upload="beforeAvatarUpload"
                            multiple
                            :limit="1"
                            :data="{UploadFileType:selectFileType.rightimg}"
                            :on-exceed="handleExceed"
                            :file-list="fileListimg2">
                        <i class="el-icon-upload"></i>
                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传右屏图片</em></div>
                        <div class="el-upload__tip" slot="tip">只能上传<span style="color: red">jpg/png</span>文件且大小<span style="color: red">不超过10MB</span>,尺寸参考:1920x1080</div>
                        <!--                <el-button size="small" type="primary">点击上传左屏缩略图</el-button>-->
                        <!--                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
                    </el-upload>
                </el-form-item>
                <el-form-item label="右屏文字:" label-width="4.5vw" prop="rightscreencontext">
                    <el-input class="inputrightcontext1"
                              type="textarea"
                              v-model="ruleForm.rightscreencontext"
                              maxlength="10000"
                              placeholder="请输入文字内容(10000个字以内)"
                              :rows="7"
                              show-word-limit></el-input>
                </el-form-item>

                <el-form-item class="elformitembtn1">
                    <el-button size="small" class="answerbutton1" type="primary" @click="submitForm('ruleForm')">保存</el-button>
                    <el-button size="small" class="cancelbutton1" @click="cancelForm()">取消</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import api123 from '@/api/global_variable.js'
    export default {
        name: "updatefile.vue",
        data() {
            return {
                globalURL: api123.baseURL, // 全局url
                pagetitle: '编辑',
                ruleForm: {
                    id: '',
                    secondmenu: '',
                    leftscreenurl: '',
                    rightscreenimg: '',
                    rightscreencontext: ''
                },
                fileListimg2: [
                    {
                        name: null,
                        url: null
                    }
                ],
                // 给每个upload组件动态绑定自定义参数:data
                UploadFileType: null,
                selectFileType: {
                    rightimg: 1
                }
            }
        },
        created() {
            console.log("传过来的row.id" + this.$route.query.id);
            const _this = this;
            this.fileListimg2[0].name = this.$route.query.rightscreenimg;
            axios.get(api123.baseURL + '/btn2file/btn2findById/' + this.$route.query.id).then(function (resp) {
                console.log("后端返回的数据" + resp.data);
                _this.ruleForm = resp.data;
            })
        },
        methods: {
            handleRemove(file, fileList) {
                // console.log(file, fileList);
            },
            handlePreview(file) {
                // console.log(file);
            },
            handleExceed(files, fileList) {
                this.$message.warning(`当前限制只能选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
            },
            beforeRemove(file, fileList) {
                return this.$confirm(`确定移除 ${ file.name }？,非法类型文件强制上传会失败`);
            },
            // 添加新三连屏btn1的信息按钮事件
            submitForm(formName) {
                console.log('这是api123baseURL'+api123.baseURL)
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        // this.$message.success("校验成功！")
                        const _this = this;
                        axios.put(api123.baseURL + '/btn2file/btn2updateById', this.ruleForm).then(function (resp) {
                            if(resp.data == 'success')
                            {
                                _this.$alert('修改成功！', '消息', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.go(-1);
                                    }
                                });
                            }else
                            {
                                _this.$message.error(resp.data);
                            }
                        });
                    } else {
                        this.$message.error("Please input correct data!")
                        return false;
                    }
                });
            },
            // 取消提交
            cancelForm() {
                this.$router.go(-1);
            },
            // 图片文件上传类型限制
            beforeAvatarUpload(file) {
                const isJPG = file.type === 'image/jpeg'; // 限制上传jpg格式
                const isPNG = file.type === 'image/png'; // 限制上传png格式
                const isLt10M = file.size / 1024 / 1024 < 10; // 限制大小10MB以内

                if (!isJPG && !isPNG) {
                    this.$message.error('上传的图片只能是 JPG 或 PNG 格式!');
                }
                if (!isLt10M) {
                    this.$message.error('上传的图片大小不能超过 10MB!');
                }

                return isJPG || isPNG && isLt10M;

            }
        }
    }
</script>

<style scoped>
    .demo-ruleForm {
        width: 52.1vw;
        margin: 3.7vh auto;
    }

    /*标题div*/
    .pagetitlediv1 {
        color: #457DDD;
        margin: 1.5vh 0 0 1.2vw!important;
    }

    /*分割线div*/
    .hrline {
        width: 85vw;
        border: 0.1vh solid #457DDD;
        margin: 0.4vh 1.2vw;
        opacity: 0.3;
    }

    /*确认取消按钮组*/
    .elformitembtn1 {
        text-align: center;
    }

    /*确认取消按钮通用样式*/
    .answerbutton1, .cancelbutton1 {
        width: 5.2vw;
    }

    /*取消按钮单独样式*/
    .cancelbutton1 {
        margin-left: 3.5vw;
    }

    /*右屏限制文字显示区域的样式文字*/
    .inputrightcontext1 {
        padding-right: 3.8vw;
    }

    /*左屏限制文字显示区域的样式文字*/
    .inputleftcontext1 {
        padding-right: 3vw;
    }


</style>