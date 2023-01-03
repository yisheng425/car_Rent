<template>
  <div class="loginbody">
    <div class="logindata">
      <div class="logintext">
        <h2>Welcome</h2>
      </div>
      <div class="formdata">
        <el-form ref="form" :model="form" :rules="rules">
          <el-form-item prop="user_name">
            <el-input
              v-model="form.user_name"
              clearable
              placeholder="请输入用户名"
            ></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input
              v-model="form.password"
              clearable
              placeholder="请输入密码"
              show-password
            ></el-input>
          </el-form-item>
        </el-form>
      </div>
      <div class="tool">
        <!-- <div>
          <el-checkbox v-model="checked" @change="remenber"
            >记住密码</el-checkbox
          >
        </div> -->
        <div>
          <span class="shou" @click="forgetpas">忘记密码？</span>
        </div>
      </div>
      <div class="butt">
        <el-button type="primary" @click="login">登录</el-button
        >
        <el-button class="shou" @click="register">注册</el-button>
        <el-button class="shou" @click="findpass">找回密码</el-button>
      </div>
    </div>
  </div>
</template>

<script>
// import { login } from "@/api/login";
// import { setToken } from "@/request/auth";

import axios from "axios"

export default {
  name: "login",
  data() {
    return {
      form: {
         user_name: "admin9527",
         password: ""       
      },
      checked: false,
      rules: {
        user_name: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          // { max: 10, message: "不能大于10个字符", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
         // { max: 10, message: "不能大于10个字符", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    login() {       

      axios.post("http://localhost:8081/login/login", this.form).then(res =>{
              console.log(res)
                if(res.data.type == 0)
                {
                    localStorage.setItem("user",JSON.stringify(res.data))  //存储用户信息到浏览器
                    this.$router.push('/')
                }
                if(res.data.type == 1)
                {
                    localStorage.setItem("user",JSON.stringify(res.data))  //存储用户信息到浏览器
                    this.$router.push('/')
                }
          })
    },
    // login(form) {
    //   this.$refs[form].validate((valid) => {
    //     if (valid) {
    //       login(this.form)
    //         .then((res) => {
    //           if (res.code === 200) {
    //             setToken(res.data.token);
    //             localStorage.setItem("USERNAME", res.data.user_name);
    //             this.$message({
    //               message: "登录成功啦",
    //               type: "success",
    //               showClose: true,
    //             });
    //             this.$router.replace("/");
    //           } else {
    //             this.$message({
    //               message: "账户名或密码错误",
    //               type: "error",
    //               showClose: true,
    //             });
    //           }
    //         })
    //         .catch((err) => {
    //           this.$message({
    //             message: "账户名或密码错误",
    //             type: "error",
    //             showClose: true,
    //           });
    //         });
    //     } else {
    //       return false;
    //     }
    //   });
    // },
    // remenber(data){
    //   this.checked=data
    //   if(this.checked){
    //       localStorage.setItem("news",JSON.stringify(this.form))
    //   }else{
    //     localStorage.removeItem("news")
    //   }
    // },
    forgetpas() {
      this.$router.push('/forget')
    },
    register() {
      this.$router.push('/register')
    },
    findpass() {
      this.$router.push('/findpass')
    },
  },
};
</script>

<style scoped>
.loginbody {
  width: 100%;
  height: 100%;
  min-width: 1000px;
  background-image: url("../assets/login_1.png");
  background-size: 100% 100%;
  background-position: center center;
  overflow: auto;
  background-repeat: no-repeat;
  position: fixed;
  line-height: 100%;
  padding-top: 150px;
}

.logintext {
  margin-bottom: 20px;
  line-height: 50px;
  text-align: center;
  font-size: 30px;
  font-weight: bolder;
  color: white;
  text-shadow: 2px 2px 4px #000000;
}

.logindata {
  width: 400px;
  height: 300px;
  transform: translate(-50%);
  margin-left: 50%;
}

.tool {
  display: flex;
  justify-content: space-between;
  color: #606266;
}

.butt {
  margin-top: 10px;
  text-align: center;
}

.shou {
  cursor: pointer;
  color: #606266;
}

/*ui*/
/* /deep/ .el-form-item__label {
  font-weight: bolder;
  font-size: 15px;
  text-align: left;
}

/deep/ .el-button {
  width: 100%;
  margin-bottom: 10px;

} */
</style>
