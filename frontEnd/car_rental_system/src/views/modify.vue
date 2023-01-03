<template>
    <div class="body">
  <el-form
         class="form-content "
      ref="modify"
      :model="form"
      :rules="rules"
      >
      <el-form-item prop="password">
          <i class="el-icon-lock"></i>
          <el-input v-model="form.password" placeholder="输入密码:" type="password" show-password></el-input>  
      </el-form-item>
      <el-form-item prop="checkPass">
          <i class="el-icon-lock"></i>
          <el-input v-model="form.checkPass" placeholder="再次确认密码:" type="password" show-password></el-input>  
      </el-form-item>
      <el-form-item>
          <el-button class="btn-login" type="primary" @click="modify()">修改密码</el-button>
      </el-form-item>
  </el-form>
</div>
  </template>
  
  <script>
  export default {
    name: "modify",
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
      return {
          form: {
               password:'',
     
               checkPass:'', 
           },         
           rules: {
            password:[{validator: validatePass, trigger: 'blur'}],
            checkPass:[{validator: validateCheckPass, trigger: 'blur'}]
        },
      }
   },
   methods: {   
      modify() {    
        
            this.$router.push('/')
            this.request.post("http://localhost:8081/register/test", this.form.password).then(res =>{
                console.log(res)
                if(res==-1)
                {
                  this.$message({
                    message: "修改失败",
                    type: "error",
                    showClose: true,
                  });
                }
                else{
                    this.$router.push('/')
                }
            })
        
    }
   }
   }
  
  </script>



  
  