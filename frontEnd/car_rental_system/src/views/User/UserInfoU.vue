<template>
    <div style="text-align: center">
        <el-form ref="form"
                 style="width: 400px;margin: 0 auto"
                 :rules="rules" :model="form"
                 label-width="80px" >
            <el-form-item label="用户名"  style="margin-top:20px">
                <el-input v-model="user.user_name" disabled></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="name" style="margin-top:-550px">
                <el-input v-model="form.name" ></el-input>
            </el-form-item>
            <el-form-item label="身份证号" prop="idpassword" style="margin-top:-490px">
                <el-input v-model="form.idpassword" ></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="sex" style="margin-top:-430px">
                <el-radio-group v-model="form.sex">
                    <el-radio label="男" value="man"></el-radio>
                    <el-radio label="女" value="woman"></el-radio>
                </el-radio-group>
            </el-form-item>
            
            

            <el-form-item style="margin-top:-370px">
                <el-button type="primary" @click="onSubmit">实名认证</el-button>
                <el-button @click="$router.push('/')">取消</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name: "Usermsg",
        data() {
            return {
                form: {
                    id:"",
                    user_name:"",
                    name: "",
                    idpassword:"",
                    sex:"",
                    password:"",
                },
                
                name:"",
                pwd:"",

                user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
            }
        },
        computed:{

        },
        methods: {
            //从axios获取登陆信息接口
            getaxioxData(){
                let loginname=sessionStorage.getItem('loginuser');//取值
                this.name=JSON.parse(loginname).username;//转换赋值
                this.pwd=JSON.parse(loginname).password;
                this.form=JSON.parse(sessionStorage.getItem('loginuser'))
                
                //  axios.get(`http://localhost:3000/userlist?username=${this.name}&password=${this.pwd}`).then((res)=>{
                //     const {status,data} = res;
                //     console.log(data,status);
                //     // this.form = data;
                //     // console.log(this.form);
                // })

            },

            //点击修改信息
            onSubmit() {
                
                var update = {
                    "user_id": this.user.user_id,
                    "realName": this.form.name,
                    "identity_id": this.form.idpassword
                }
                console.log(update)
                axios.post("http://localhost:8081/user/identity",update).then(res=>{
                    console.log(res.data);
                    this.$message('修改成功')
                })
                console.log('submit!');
            }
        },
        mounted() {
            this.getaxioxData()
        }
    }
</script>

<style scoped>

</style>