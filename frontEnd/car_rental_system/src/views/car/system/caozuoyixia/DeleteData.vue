<template>
<el-main>
  
<!--表格
    data:数据绑定
    height：只要在el-table元素中定义了height属性，即可实现固定表头的表格，而不需要额外的代码。
    border：表格边框
    prop：字段属性需要跟表格的数据对应
    -->
    <el-table :data="tableData" :height="tableHeight" border style="width: 100%">
      <el-table-column prop="car_id" label="序号"></el-table-column>
        <el-table-column prop="car_type" label="类型"></el-table-column>
      <el-table-column prop="car_brand" label="品牌"></el-table-column>
      <el-table-column prop="car_rent_price" label="租金"></el-table-column>
      <el-table-column prop="car_deposit_price" label="押金"></el-table-column>
      <el-table-column prop="car_color" label="颜色"></el-table-column>
      <el-table-column prop="car_number" label="车牌号"></el-table-column>
      
      <el-table-column prop="car_picurl" label="照片"
      ><el-image  style="width: 100px; height: 100px" :src="car_picurl"></el-image 
      ></el-table-column>

      <el-table-column prop="state" label="状态"></el-table-column>
      <el-table-column>
        <template slot-scope="scope">
          <el-button type="danger" @click="del(scope.row.car_id)">删除</el-button>
        </template>
      </el-table-column>
</el-table>
     <!-- 分页组件-->
    
</el-main>
</template>
<script>
import axios from "axios"
import axios2 from "axios"
    export default {
        data(){
            return{
           userForm:{
               region:'Benz'
           },
            //表格高度 window.innerHeight窗口文档显示高度
      tableHeight: window.innerHeight,
      //表格数据绑定
       tableData: [
        
      ],
            }
        },
         //该钩子函数执行时所有的DOM挂载和渲染都已完成，此时在该钩子函数中进行任何DOM操作都不 会有问题
// 在数据变化后要执行的某个操作，而这个操作需要使用随数据改变而改变的DOM结构的时候，
// 这个操作都应该放进Vue.nextTick()的回调函数中
  created() {
    this.$nextTick(() => {
      this.tableHeight = window.innerHeight - 210; //后面的50：根据需求空出的高度，自行调整
      this.getAllData();
    });
  },
   methods: {
     
     getAllData(){
       axios.get('http://localhost:8081/car/GetCarBypage')
       .then((res)=>{
         console.log(res);
         const {status,data} = res ;
         if(status===200){
           this.tableData=data;
         }
       })
     },
     del(ID){
        console.log(ID);
         axios.post("http://localhost:8081/car/Delete_car", {"car_id": ID})
       .then((res)=>{
        console.log(res);
          axios.get('http://localhost:8081/car/GetCarBypage')
       .then((res)=>{
         console.log(this.userForm.userName);
         console.log(res);
         this.tableData=res.data;
       })

       })
       
      
     }
    }
    }
</script>
<style lang="scss" scoped>
.el-main{
    padding-top: 5px !important;
}
.aa{
    width: 300px;
    height: 28px;
    margin-bottom: 35px;
}
.searchBtn{
    margin-left: 30px;
}
</style>