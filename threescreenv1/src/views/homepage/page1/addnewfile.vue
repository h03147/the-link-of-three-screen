<template>
    <div>
        <div class="pagetitlediv1"><span>{{pagetitle}}</span></div>
        <div class="headerlinediv1"><HR class="hrline"></HR></div>
        <div class="pageinputmaindiv1">
            <el-form :model="insertNewFiletableData" :rules="rules" ref="insertNewFiletableData" class="demo-form-inline">
                <el-form-item label="左屏缩略图:" label-width="4.5vw" v-if="showPanel">
                <el-upload
                        class="upload-demo"
                        drag
                        :action="globalURL + '/btn1file/upload'"
                        :on-preview="handlePreview"
                        :on-remove="handleRemove"
                        :before-remove="beforeRemove"
                        :before-upload="beforeAvatarUpload"
                        multiple
                        :limit="1"
                        :data="{UploadFileType:selectFileType.leftminiimg}"
                        :on-exceed="handleExceed"
                        :file-list="fileList">
                    <i class="el-icon-upload"></i>
                    <div class="el-upload__text">将文件拖到此处，或<em>点击上传左屏缩略图</em></div>
                    <div class="el-upload__tip" slot="tip">只能上传<span style="color: red">jpg/png</span>文件且大小<span style="color: red">不超过10MB</span>,尺寸参考:1920x1080</div>
                    <!--                <el-button size="small" type="primary">点击上传左屏缩略图</el-button>-->
                    <!--                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
                </el-upload>
                </el-form-item>

                <el-form-item label="左屏文字:" label-width="4.5vw" prop="leftscreencontext" v-if="showPanel">
                    <el-input class="inputleftcontext1" type="textarea" :rows="5" show-word-limit maxlength="50" size="small" v-model="insertNewFiletableData.leftscreencontext" placeholder="请输入左屏内容"></el-input>
                </el-form-item>
                <el-form-item label="右屏图片:" label-width="4.5vw" v-if="showPanel">
                    <el-upload
                            class="upload-demo"
                            drag
                            :action="globalURL + '/btn1file/upload'"
                            :on-preview="handlePreview"
                            :on-remove="handleRemove"
                            :before-remove="beforeRemove"
                            :before-upload="beforeAvatarUpload"
                            multiple
                            :limit="1"
                            :data="{UploadFileType:selectFileType.rightimg}"
                            :on-exceed="handleExceed"
                            :file-list="fileList">
                        <i class="el-icon-upload"></i>
                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传右屏图片</em></div>
                        <div class="el-upload__tip" slot="tip">只能上传<span style="color: red">jpg/png</span>文件且大小<span style="color: red">不超过10MB</span>,尺寸参考:1920x1080</div>
                        <!--                <el-button size="small" type="primary">点击上传左屏缩略图</el-button>-->
                        <!--                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
                    </el-upload>
                </el-form-item>

                <el-form-item label="右屏视频:" label-width="4.5vw" v-if="showPanel">
                    <el-upload
                            class="upload-demo"
                            drag
                            :action="globalURL + '/btn1file/upload'"
                            :on-preview="handlePreview"
                            :on-remove="handleRemove"
                            :before-remove="beforeRemove"
                            :before-upload="beforeAvatarUploadvideo"
                            multiple
                            :limit="1"
                            :data="{UploadFileType:selectFileType.rightvideo}"
                            :on-exceed="handleExceed"
                            :file-list="fileList">
                        <i class="el-icon-upload"></i>
                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传右屏视频</em></div>
                        <div class="el-upload__tip" slot="tip">只能上传<span style="color: red">mp4</span>格式视频，编码参考H.264/AVC，否则可能无法播放，大小不超过<span style="color: red">150MB</span>,否则会上传失败
                        </div>
                        <!--                <el-button size="small" type="primary">点击上传左屏缩略图</el-button>-->
                        <!--                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
                    </el-upload>
                </el-form-item>

                <el-form-item label="右屏文字:" prop="rightscreencontext" label-width="4.5vw">
                    <el-input :rows="7" class="inputrightcontext1" show-word-limit maxlength="10000" type="textarea" size="small" v-model="insertNewFiletableData.rightscreencontext" placeholder="请输入右屏文字"></el-input>
                </el-form-item>
            </el-form>
            <div class="bottombtndiv1">
                <el-button size="small" type="primary" @click="submitForm('insertNewFiletableData')" style="width: 5.2vw">保存</el-button>
                <el-button size="small" @click="cancelForm()" style="width: 5.2vw; margin-left: 3.5vw">取消</el-button>
            </div>
        </div>
    </div>
</template>

<script>
    import api123 from '@/api/global_variable.js'
    export default {
        name: "addnewfile",
        data() {
            return {
                globalURL: api123.baseURL, // 全局url
                pagetitle: '添加新内容',
                showPanel: true, // v-if判断组件是否隐藏的条件
                fileList: [],
                insertNewFiletableData: {
                    secondmenu: null,
                    thirdmenu: null,
                    fourthmenu: null,
                    fifthmenu: null,
                    leftscreenminiimg: null,
                    leftscreencontext: null,
                    rightscreenimg: null,
                    rightscreenvideo: null,
                    rightscreencontext: null,
                    lujing: null,
                },
                rules: {
                    secondmenu: [
                        {required: true, message: '二级菜单不能为空！', trigger: 'blur' },
                        { min: 1, max: 32, message: '长度在 1 到 32 个字符', trigger: 'change' }
                    ]
                },
                // 给每个upload组件动态绑定自定义参数:data
                UploadFileType: null,
                selectFileType: {
                    leftminiimg: 0,
                    rightimg: 1,
                    rightvideo: 2
                }
            };
        },
        created() {
            this.insertNewFiletableData.secondmenu = this.$route.query.secondmenu;
            this.insertNewFiletableData.thirdmenu = this.$route.query.thirdmenu;
            this.insertNewFiletableData.fourthmenu = this.$route.query.fourthmenu;
            this.insertNewFiletableData.fifthmenu = this.$route.query.fifthmenu;
            // console.log(this.insertNewFiletableData.toString())
            // this.$message.success(this.insertNewFiletableData.toString());
            if(this.insertNewFiletableData.fourthmenu === '支部发展')
            {
                this.showPanel = false;
            }

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
                        axios.post(api123.baseURL + '/btn1file/btn1addonefile', this.insertNewFiletableData).then(function (resp) {
                            if(resp.data == '添加文件信息成功')
                            {
                                _this.$message.success(resp.data);

                                //延迟1秒来缓存数据
                                setTimeout( function(){
                                    _this.$router.push({
                                        path: '/index1/page1index1',
                                        query: {
                                            backsecondmenu: _this.insertNewFiletableData.secondmenu,
                                            backthirdmenu: _this.insertNewFiletableData.thirdmenu,
                                            backfourthmenu: _this.insertNewFiletableData.fourthmenu,
                                            backfifthmenu: _this.insertNewFiletableData.fifthmenu
                                        }
                                    });
                                }, 1 * 1000 );//延迟1000毫米

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

            },
            // 视频文件上传类型限制
            beforeAvatarUploadvideo(file) {
                const isMP4 = file.type === 'video/mp4'; // 限制上传mp4格式
                const isLt100M = file.size / 1024 / 1024 < 150; // 限制大小100MB以内

                if (!isMP4) {
                    this.$message.error('上传的视频只能是 MP4 格式!');
                }
                if (!isLt100M) {
                    this.$message.error('上传的视频大小不能超过 150MB!');
                }

                return isMP4 && isLt100M;

            }
        }
    }
</script>

<style scoped>

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

    /*form提交表单的样式*/
    .pageinputmaindiv1 {

        width: 52.1vw;
        margin: 3.7vh auto;
    }

    /*上传组件通用大小*/
    .upload-demo {
    }

    /*按钮的div样式*/
    .bottombtndiv1 {
        text-align: center;
    }

    /*右屏限制文字显示区域的样式文字*/
    .inputrightcontext1 {
        padding-right: 4vw;
    }

    /*左屏限制文字显示区域的样式文字*/
    .inputleftcontext1 {
        padding-right: 2.5vw;
    }

</style>