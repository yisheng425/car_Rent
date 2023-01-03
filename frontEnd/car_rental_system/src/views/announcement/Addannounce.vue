<template>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" > 
      

      <el-form-item label="你的账户" prop="user_id" style="margin-left:30%;margin-top:10px">
        <el-input v-model="user.user_name" style="width:221.4px"></el-input>
      </el-form-item>
      <el-form-item label="公告标题" prop="title" style="margin-left:30%;margin-top:-560px">
        <el-input v-model="ruleForm.title" style="width:221.4px"></el-input>
      </el-form-item>
      
      <el-form-item label="公告内容" prop="content" style="margin-left:30%;margin-top:-500px">
        <el-input 
        type="textarea"
        :rows="5"
        v-model="ruleForm.content" style="width:221.4px"></el-input>
      </el-form-item>
     
     
      
      
      <el-form-item style="margin-left:30%;margin-top:-150px">
        <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
    </template>
    
    <script>
    import axios from "axios"
      export default {
        data() {
          return {
            ruleForm: {
              title: "",
              user_id: 0,
              content: ""
              
            },
            
            user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
            
          };
        },
        created(){
        this.showtime();
        },
        methods: {
          showtime() {
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate();
        var hour = date.getHours();
        var minutes = date.getMinutes();
        var second = date.getSeconds();
        this.nowTime =
        year +
        "." +
        (month < 10 ? "0" + month : month) +
        "." +
        day +
        " " +
        " " +
        hour +
        ":" +
        (minutes <10 ? "0"+minutes  : minutes)+
        ":" +
        (second < 10 ? "0" + second : second) +
        "" ;
        },


          submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            console.log("123456")

              axios.post("http://localhost:8081/announcement/create",
              {
                "title": this.ruleForm.title,
                "user_id": this.user.user_id,
                "content": this.ruleForm.content
              }
              )
              .then((res)=>{
                const {status} = res;
                  console.log("done");
                  /*
                
                */
              })
            
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
           
        }
      }
    </script>
    
    <style scoped>
    
    </style>