<template>
    <div class="body">
  <el-form
         class="form-content "
      ref="register"
      :model="form"
      :rules="rules"
      >
      <el-form-item prop="name">
          <i class="el-icon-user-solid"></i>
          <el-input v-model="form.user_name" placeholder="用户名:"></el-input>
      </el-form-item>
     
      <el-form-item prop="password">
          <i class="el-icon-lock"></i>
          <el-input v-model="form.password" placeholder="输入密码:" type="password" show-password></el-input>  
      </el-form-item>
      <el-form-item prop="checkPass">
          <i class="el-icon-lock"></i>
          <el-input v-model="form.checkPass" placeholder="再次确认密码:" type="password" show-password></el-input>  
      </el-form-item>
      <el-form-item prop="phone_number">
          <i class="el-icon-phone"></i>
          <el-input v-model="form.phone_number" placeholder="手机号:"></el-input>
          <el-button class="btn-login" type="primary" @click="toconfirm()">发送验证码</el-button>
      </el-form-item>
  
      <el-form-item prop="confirm">
          <i class="el-icon-user-solid"></i>
          <el-input v-model="confirm" placeholder="验证码:"></el-input>
      </el-form-item>
      <el-form-item>
          <el-button class="btn-login" type="primary" @click="register()">Register</el-button>
      </el-form-item>
  </el-form>
</div>
  </template>
  
  <script>

import axios from "axios"

  export default {
    name: "register",
    data () {
      // 密码验证
      var validatePass = (rule,value,callback)=>{
          if(value === ''){
              callback(new Error('请输入密码'))
          }else{
               if(this.checkPass !== ''){
                  // this.$refs[form].validateField('checkPass')
                   this.$refs[form].validateField('checkPass')
               }
              callback()
          }
      };
      // 再次确认密码验证
      var validateCheckPass = (rule,value,callback)=>{
          if (value === '') {
              callback(new Error('请再次输入密码'));
          } else if (value !== this.form.password) {
              callback(new Error('两次输入密码不一致!'));
          } else {
              callback();
          }
      };
      // 手机号码验证
      var validatePhone = (rule,value,callback)=>{
          var reg=11 && /^((13|14|15|16|17|18|19)[0-9]{1}\d{8})$/;
          if (value === '') {
              callback(new Error('请输入手机号'));
          } else if (!reg.test(value)) {
              callback(new Error('手机号码格式不正确!'));
          } else {
              callback();
          }
      };
      return {
          form: {
               user_name: "",
               password:"",
               phone_number: "",     
           },         
           confirm: "",
           checkconfirm: 123456,
           rules: {
            phone_number:[{validator: validatePhone, trigger: 'blur'}],
            password:[{validator: validatePass, trigger: 'blur'}],
            checkPass:[{validator: validateCheckPass, trigger: 'blur'}]
        },
      }
   },
   methods: {   
    toconfirm() {    
        console.log(this.form.phone_number)     
        axios.post("http://localhost:8081/msg/sendVericode",{"phone_number": this.form.phone_number}).then(res =>{
                console.log(res)
                if(res.data==-1 || res.data == 500)
                {
                  this.$message({
                    message: "发送失败",
                    type: "error",
                    showClose: true,
                  });
                }   
                else{
                  this.checkconfirm=res.data
                }
            })
      },
      register() {
        if(this.checkconfirm != this.confirm) {
          this.$message({
                    message: "验证码错误",
                    type: "error",
                    showClose: true,
                  });
        } 
        else{
            axios.post("http://localhost:8081/login/checkUsername", {"user_name": this.form.user_name}).then(res =>{
            if(res.data == 1)
            {
                this.$message({
                    message: "用户名已存在",
                    type: "error",
                    showClose: true,
                  });
            }
            else{
                axios.post("http://localhost:8081/login/checkPhoneNumber", {"phone_number": this.form.phone_number}).then(res =>{
                    if(res.data == 1)
                    {
                        this.$message({
                            message: "手机号已注册，请找回密码",
                            type: "error",
                            showClose: true,
                        });
                    }
                    else{
                        console.log(this.form)
                        axios.post("http://localhost:8081/login/regist", this.form).then(res =>{
                            console.log(res)
                            if(res.data == 1)
                            {   
                                this.$message({
                                message: "注册成功",
                                showClose: true,
                                });
                                this.$router.push('/login')
                            }
                            else{
                            this.$message({
                                message: "注册失败",
                                type: "error",
                                showClose: true,
                            });
                            }
                    })
                    }
                })
                
        }
            })
        
        
            
        }
     
    },
   }
}
  </script>

<style scoped>


  
  