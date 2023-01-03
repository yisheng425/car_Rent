<template>
    <div class="body">
  <el-form
         class="form-content "
      ref="gorget"
      :model="form"
      :rules="rules"
      >
      <el-form-item prop="name">
          <i class="el-icon-user-solid"></i>
          <el-input v-model="form.name" placeholder="用户名:"></el-input>
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
          <el-button class="btn-login" type="primary" @click="forget()">下一步</el-button>
      </el-form-item>
  </el-form>
</div>
  </template>
  
  <script>
  export default {
    name: "forget",
    data () {
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
               user_name:'',
               phone_number: '',     
           },         
           confirm:"",
           checkconfirm:"",
           rules: {
            phone_number:[{validator: validatePhone, trigger: 'blur'}],
        },
      }
   },
   methods: {   
    toconfirm() {         
        this.request.post("http://localhost:8081/register/test", this.form.phone_number).then(res =>{
                console.log(res)
                if(res==-1)
                {
                  this.$message({
                    message: "发送失败",
                    type: "error",
                    showClose: true,
                  });
                }
                else{
                  this.checkconfirm=res
                }
            })
      },
      forget() {    
        this.$router.push('/modify')
        if(this.checkconfirm!==confirm) {
          this.$message({
                    message: "验证码错误",
                    type: "error",
                    showClose: true,
                  });
        } 
        else{
            this.request.post("http://localhost:8081/login/test", this.form).then(res =>{
                            console.log(res)
                            if(res==1)
                            {
                                this.$router.push('/modify')
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
    }
     
   }
   }
  
  </script>


  
  