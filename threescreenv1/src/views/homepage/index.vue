<template>
    <div>

        <el-form :model="insertUsertableData" :rules="rules" ref="insertUsertableData" class="demo-form-inline">
            <el-form-item label="secondmenu" prop="secondmenu">
                <el-input size="small" v-model="insertUsertableData.secondmenu" placeholder="请输入二级菜单"></el-input>
            </el-form-item>
            <el-form-item label="thirdmenu" prop="thirdmenu">
                <el-input size="small" v-model="insertUsertableData.thirdmenu" placeholder="请输入三级菜单"></el-input>
            </el-form-item>
            <el-form-item label="fourthmenu" prop="fourthmenu">
                <el-input size="small" v-model="insertUsertableData.fourthmenu" placeholder="请输入四级菜单"></el-input>
            </el-form-item>
            <el-form-item label="fifthmenu" prop="fifthmenu">
                <el-input size="small" v-model="insertUsertableData.fifthmenu" placeholder="请输入五级菜单"></el-input>
            </el-form-item>

            <el-upload
                    class="upload-demo"
                    drag
                    action="http://192.168.31.100:8080/btn1file/upload"
                    :on-preview="handlePreview"
                    :on-remove="handleRemove"
                    :before-remove="beforeRemove"
                    multiple
                    :limit="4"
                    :data="{UploadFileType:selectFileType.leftminiimg}"
                    :on-exceed="handleExceed"
                    :file-list="fileList">
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传左屏缩略图</em></div>
                <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
<!--                <el-button size="small" type="primary">点击上传左屏缩略图</el-button>-->
<!--                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
            </el-upload>

            <el-form-item label="leftscreencontext" prop="leftscreencontext">
                <el-input size="small" v-model="insertUsertableData.leftscreencontext" placeholder="请输入左屏内容"></el-input>
            </el-form-item>

            <el-upload
                    class="upload-demo"
                    drag
                    action="http://192.168.31.100:8080/btn1file/upload"
                    :on-preview="handlePreview"
                    :on-remove="handleRemove"
                    :before-remove="beforeRemove"
                    multiple
                    :limit="4"
                    :data="{UploadFileType:selectFileType.rightimg}"
                    :on-exceed="handleExceed"
                    :file-list="fileList">
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传右屏图片</em></div>
                <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
                <!--                <el-button size="small" type="primary">点击上传左屏缩略图</el-button>-->
                <!--                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
            </el-upload>

            <el-upload
                    class="upload-demo"
                    drag
                    action="http://192.168.31.100:8080/btn1file/upload"
                    :on-preview="handlePreview"
                    :on-remove="handleRemove"
                    :before-remove="beforeRemove"
                    multiple
                    :limit="4"
                    :data="{UploadFileType:selectFileType.rightvideo}"
                    :on-exceed="handleExceed"
                    :file-list="fileList">
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传右屏视频</em></div>
                <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
                <!--                <el-button size="small" type="primary">点击上传左屏缩略图</el-button>-->
                <!--                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
            </el-upload>

            <el-form-item label="rightscreencontext" prop="rightscreencontext">
                <el-input type="textarea" size="small" v-model="insertUsertableData.rightscreencontext" placeholder="请输入右屏内容"></el-input>
            </el-form-item>
        </el-form>
        <el-button size="small" type="primary" @click="submitForm('insertUsertableData')" style="width: 5.2vw">保存</el-button>


    </div>
</template>

<script>
    import api123 from '@/api/global_variable.js'
    export default {
        name: "index",
        data() {
            return {
                fileList: [],
                insertUsertableData: {
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
            const that = this
            // function upload(obj) {
            //     var formData = new FormData();
            //     formData.append("fileName", obj.file[0]);
            //
            //     $.ajax({
            //         type:"post",
            //         url:"http://localhost:8181/upload",
            //         data:formData,
            //         success:function (data) {
            //             console.log(data);
            //         }
            //     })
            // }
        },
        methods: {
            handleRemove(file, fileList) {
                // console.log(file, fileList);
            },
            handlePreview(file) {
                // console.log(file);
            },
            handleExceed(files, fileList) {
                this.$message.warning(`当前限制选择 4 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
            },
            beforeRemove(file, fileList) {
                return this.$confirm(`确定移除 ${ file.name }？`);
            },
            // 添加新三连屏btn1的信息按钮事件
            submitForm(formName) {
                console.log('这是api123baseURL'+api123.baseURL)
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        // this.$message.success("校验成功！")
                        const _this = this;
                        axios.post(api123.baseURL + '/btn1file/btn1addonefile', this.insertUsertableData).then(function (resp) {
                            if(resp.data == '添加人员信息成功')
                            {
                                _this.$message.success(resp.data);
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
        }
    }
</script>

<style scoped>

</style>