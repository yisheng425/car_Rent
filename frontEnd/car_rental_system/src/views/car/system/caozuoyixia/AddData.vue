<template>
<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" > 
  <el-form-item label="汽车类型" prop="type" style="margin-left:30%;margin-top:10px">
    <el-input v-model="ruleForm.type" style="width:221.4px"></el-input>
  </el-form-item>
  <el-form-item label="汽车品牌" prop="brand" style="margin-left:30%;margin-top:-560px">
    <el-input v-model="ruleForm.brand" style="width:221.4px"></el-input>
  </el-form-item>
  <el-form-item label="租金" prop="rent_money" style="margin-left:30%;margin-top:-500px">
    <el-input v-model="ruleForm.rent_money" style="width:221.4px"></el-input>
  </el-form-item>
  <el-form-item label="押金" prop="deposit_money" style="margin-left:30%;margin-top:-440px">
    <el-input v-model="ruleForm.deposit_money" style="width:221.4px"></el-input>
  </el-form-item>
  <el-form-item label="颜色" prop="color" style="margin-left:30%;margin-top:-380px">
<el-input v-model="ruleForm.color" style="width:221.4px"></el-input>
  </el-form-item>
  <el-form-item label="车牌号" prop="number" style="margin-left:30%;margin-top:-320px">
<el-input v-model="ruleForm.number" style="width:221.4px"></el-input>
  </el-form-item>
  <el-form-item label="照片" prop="url" style="margin-left:30%;margin-top:-260px">
<el-input v-model="ruleForm.url" style="width:221.4px"></el-input>
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
          type: "",
          brand: "",
          rent_money: 0,
          deposit_money: 0,
          color: "",
          number:"",
          url: ""
        },
       
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            var update = {
                "car_type": this.ruleForm.type,
                "car_brand": this.ruleForm.brand,
                "car_rent_price": this.ruleForm.rent_money,
                "car_deposit_price": this.ruleForm.deposit_money,
                "car_color": this.ruleForm.color,
                "car_number": this.ruleForm.number,
                "pictureUrl":this.ruleForm.url
            }
            console.log(update)
              axios.post("http://localhost:8081/car/add_car", update)
              .then((res)=>{
                  console.log(res);

              })
            
            
            
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style scoped>

</style>