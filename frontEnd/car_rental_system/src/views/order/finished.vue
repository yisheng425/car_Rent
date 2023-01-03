<template>
    <el-main>
      <el-form :model="userForm" size="mini" label-width="80px">
    
    </el-form>
    <!--表格
        data:数据绑定
        height：只要在el-table元素中定义了height属性，即可实现固定表头的表格，而不需要额外的代码。
        border：表格边框
        prop：字段属性需要跟表格的数据对应
        -->
        <el-table 
        :data="tableData.slice((currentpage1-1)*pagesize,currentpage1*pagesize)" :height="tableHeight" border style="width: 100%">
          <el-table-column prop="id" label="订单序号"></el-table-column>
          <el-table-column prop="name" label="用户姓名"></el-table-column>
            <el-table-column prop="carType" label="汽车类型"></el-table-column>
          <el-table-column prop="carNo" label="车牌号"></el-table-column>
          <el-table-column prop="date" label="起始终止时间"></el-table-column>
          <el-table-column prop="PricePerDay" label="日租价格"></el-table-column>
          <el-table-column prop="PriceDeposit" label="押金价格"></el-table-column>
          <el-table-column prop="PriceSecurity" label="保险价格"></el-table-column>
          <el-table-column prop="sta" label="状态"></el-table-column>
          

    </el-table>


   

         <!-- 分页组件-->
        
    </el-main>
    </template>
    <script>
    import axios from "axios"
        export default {
            data(){
                return{
                    
                  total:0,
                  currentpage1:1,
                  pagesize:9,
                  searchmap:{},
               userForm:{
                   userName:""
               },
                //表格高度 window.innerHeight窗口文档显示高度
          tableHeight: window.innerHeight,
          //表格数据绑定
           tableData: [{
            id:'1',
            name:'1',
            carType:'1',
            carNo:'1',
            date:'1',
            PricePerDay:'1',
            PriceDeposit:'1',
            PriceSecurity:'1',
            sta:'1',
           }
          ],
                }
            },
             //该钩子函数执行时所有的DOM挂载和渲染都已完成，此时在该钩子函数中进行任何DOM操作都不 会有问题
    // 在数据变化后要执行的某个操作，而这个操作需要使用随数据改变而改变的DOM结构的时候，
    // 这个操作都应该放进Vue.nextTick()的回调函数中
      mounted() {
        this.$nextTick(() => {
          this.tableHeight = window.innerHeight - 210; //后面的50：根据需求空出的高度，自行调整
          this.getAllData();
        });
      },
       methods: {
         
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
    