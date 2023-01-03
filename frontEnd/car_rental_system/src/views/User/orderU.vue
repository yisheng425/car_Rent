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
        <el-table-column prop="order_id" label="订单编号"></el-table-column>
        <el-table-column prop="rent_price" label="租金"></el-table-column>
          <el-table-column prop="deposit_price" label="押金"></el-table-column>
        <el-table-column prop="car_id" label="车"></el-table-column>
        <el-table-column prop="total_price" label="总金额"></el-table-column>
        <el-table-column prop="begintime" label="起始时间"></el-table-column>
        <el-table-column prop="endtime" label="结束时间"></el-table-column>
        <el-table-column prop="state" label="状态"></el-table-column>
        <el-table-column prop="user_id" label="用户id"></el-table-column>
        <el-table-column prop="operation" label="操作">
        <template slot-scope="scope">
        <el-button type="success"  icon="el-icon-edit" size="mini" @click="dialogVisible = true, orderId(scope.row.order_id,scope.row.state)">取消</el-button>
        <br>
        <br>
        <el-button type="success"  icon="el-icon-edit" size="mini" @click="dialogVisible2 = true, orderId(scope.row.order_id,scope.row.state), checkPay(scope.row.state)">支付</el-button>
      </template>
    </el-table-column>

    </el-table>


    <el-dialog
  title="订单？"
  :visible.sync="dialogVisible"
  width="30%"
  center>
  <span>是否对订单有疑意？</span>
  <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="del() ,dialogVisible = false">取消订单</el-button>
  </span>
</el-dialog>

  <el-dialog
    title="支付"
    :visible.sync="dialogVisible2"
    width="30%"
    center>
    <span>请选择支付方式</span>
    <span slot="footer" class="dialog-footer">
  <el-button @click="dialogVisible2 = false">取 消</el-button>
  <el-button type="primary" @click="WechatPay() ,dialogVisible2 = false">微信支付</el-button>
  <el-button type="primary" @click="AliPay() ,dialogVisible2 = false">支付宝支付</el-button>
</span>
</el-dialog>

         <!-- 分页组件-->
        
    </el-main>
    </template>
    <script>
    import axios from "axios"
        export default {
            data(){
                return{


                    dialogVisible:false,
                    dialogVisible2:false,
                  total:0,
                  currentpage1:1,
                  pagesize:9,
                  searchmap:{},
               userForm:{
                   userName:""
               },
               user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
                //表格高度 window.innerHeight窗口文档显示高度
          tableHeight: window.innerHeight,
          //表格数据绑定
           tableData: [],
           current_order_id: 0,
           current_state: 0,
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
          console.log("123123");
          console.log(this.user.user_id);
          axios.post("http://localhost:8081/order/GetOrderList", {"user_id": this.user.user_id}).then((res)=>{
         console.log(res.data);
         this.tableData=res.data;
       })
     },
        del(ID){
          if(this.current_state == 1){
            axios.post("http://localhost:8081/pay/refundAll", {"order_id": this.current_order_id})
            axios.post("http://localhost:8081/order/cancel", {"order_id": this.current_order_id})
       .then((res)=>{
         const {status,data} =res;
         if(status===200)
         {
          axios.post("http://localhost:8081/order/GetOrderList", {"user_id": this.user.user_id})
       .then((res)=>{
         this.tableData=res.data;
       })
         }
       })
          }

          if(this.current_state == 2){
            axios.post("http://localhost:8081/pay/refund", {"order_id": this.current_order_id})
       .then((res)=>{
         const {status,data} =res;
         if(status===200)
         {
          axios.post("http://localhost:8081/order/GetOrderList", {"user_id": this.user.user_id})
       .then((res)=>{
         this.tableData=res.data;
       })
         }
       })
          }
          if(this.current_state >2 || this.current_state == 0){
            this.$message({
                                message: "无法取消订单",
                                type: "error",
                                showClose: true,
                            });
          }
         
      },
      orderId(ID,state)
      {
        
        this.current_order_id = ID;
        this.current_state = state;
      },
      checkPay(state)
      {
        if(state == 1){
          this.$message({
                                message: "已经完成支付",
                                type: "error",
                                showClose: true,
                            });
                            this.dialogVisible2 = false;
        }
        if(state > 1){
          this.$message({
                                message: "无法支付",
                                type: "error",
                                showClose: true,
                            });
                            this.dialogVisible2 = false;
        }
      },
      WechatPay(){
        if(this.current_state == 0){
          axios.post("http://localhost:8081/pay/payment", {"order_id": this.current_order_id, "payInfo": "微信支付"})
       .then((res)=>{
         console.log(res.data)
       })
       axios.post("http://localhost:8081/order/checkOrder", {"order_id": this.current_order_id, "state": 1})
       .then((res)=>{
         console.log(res.data)

         axios.post("http://localhost:8081/order/GetOrderList", {"user_id": this.user.user_id})
       .then((res)=>{
         this.tableData=res.data;
       })

       })

        }
        
      },
      AliPay(){
        if(this.current_state == 0){
        axios.post("http://localhost:8081/pay/payment", {"order_id": this.current_order_id, "payInfo": "支付宝支付"})
       .then((res)=>{
         console.log(res.data)
       })
       axios.post("http://localhost:8081/order/checkOrder", {"order_id": this.current_order_id, "state": 1})
       .then((res)=>{
         console.log(res.data)

         axios.post("http://localhost:8081/order/GetOrderList", {"user_id": this.user.user_id})
       .then((res)=>{
         this.tableData=res.data;
       })

       })
      }
      },
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
    