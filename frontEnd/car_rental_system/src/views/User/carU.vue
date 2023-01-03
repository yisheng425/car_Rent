<template>
    <el-main>

    <el-dialog title="完成实名认证" :visible.sync="dialogVisible1" width="30%" center>
      <p v-if="isidentified">

      </p>
  <span>你未完成身份验证，请前往完成实名认证。</span>
  <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible1 = false">取 消</el-button>
    <el-button type="primary" @click="dialogVisible1 = false,$router.push('/UserInfoU')">确 定</el-button>
  </span>
</el-dialog>
    
    <!--表格
        data:数据绑定
        height：只要在el-table元素中定义了height属性，即可实现固定表头的表格，而不需要额外的代码。
        border：表格边框
        prop：字段属性需要跟表格的数据对应
        -->
        <el-table 
        
        :data="tableData.slice((currentpage1-1)*pagesize,currentpage1*pagesize)" :height="tableHeight" border style="width: 100%">
            <el-table-column prop="car_type" label="汽车类型"></el-table-column>
          <el-table-column prop="car_brand" label="汽车品牌"></el-table-column>
          <el-table-column prop="car_color" label="汽车颜色"></el-table-column>
          <el-table-column prop="car_rent_price" label="日租价格"></el-table-column>
          <el-table-column prop="car_deposit_price" label="押金价格"></el-table-column>

          
          <el-table-column prop="pictureUrl" label="照片">
        <template slot-scope="scope">
          <img :src="scope.row.pictureUrl" style="width: 150px;height: 150px">
        </template>
        
    </el-table-column>
    
          <el-table-column prop="operation" label="操作">
          <template slot-scope="scope">

          <el-button type="success" title="下单" icon="el-icon-edit" size="mini" @click="dialogVisible = true ,trans(scope.row.car_deposit_price,scope.row.car_rent_price, scope.row.car_id)"></el-button>
      </template>
    </el-table-column>
    </el-table>
    

    <el-dialog
  title="是否要租这俩车"
  :visible.sync="dialogVisible"
  width="30%"
  center>

  <el-date-picker
      v-model="searchStart"
        type="datetime"
       placeholder="选择起始日期">
    </el-date-picker>
    <el-date-picker
     v-model="searchEnd"
    type="datetime"
   placeholder="选择结束日期">
    </el-date-picker>

    <h4>是否购买保险</h4>
    <el-select v-model="secu_value" placeholder="请选择">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
  <h4>总金额
    {{  }}
  </h4>
  <el-input
  placeholder=""
  v-model="money"
  :disabled="true">
</el-input>
  <span slot="footer" class="dialog-footer">
    <el-button @click="sum()">计 算</el-button>
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="createOrder(), dialogVisible = false">确 定</el-button>
  </span>
</el-dialog>


         <!-- 分页组件-->
    </el-main>
    </template>
    <script>
    import axios from "axios"
    import axios2 from "axios"
        export default {
            data(){
                return{

                  options: [{
                  value: 1,
                  label: '是'
                  }, {
                  value: 0,
                label: '否'
                  }
                ],
                rent_p:'',
                deposit_p:'',
              value: '',
              secu_value: '',
              money:'' ,
              searchStart:'' ,
              searchEnd:'',

                  isidentified:'',
                  dialogVisible:false,
                  dialogVisible1:false,
                  dialogVisible2:false,
                  total:0,
                  currentpage1:1,
                  pagesize:9,
                  searchmap:{},

                  ruleForm: {
          type: '',
          brand: '',

          color: '',
          number:'',
          url: '',
         
        },

        orderCreateForm:{
          car_id: 0,
          rent_price: 0,
          deposit_price: 0,
          total_price: 0,
        },
                //表格高度 window.innerHeight窗口文档显示高度
          tableHeight: window.innerHeight,
          //表格数据绑定
           tableData: [],

           user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},

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
        this.open()
      },
       methods: {
        trans(car_deposit_price,car_rent_price, car_id)
        {
          this.deposit_p=car_deposit_price;
          this.rent_p=car_rent_price;
          this.orderCreateForm.car_id = car_id;
        },
        sum()
        {
          console.log((this.searchEnd-this.searchStart)/86400000);
          this.money=((this.searchEnd-this.searchStart)/86400000)*this.rent_p+this.deposit_p+this.secu_value*200;
          this.orderCreateForm.rent_price = ((this.searchEnd-this.searchStart)/86400000)*this.rent_p;
        },
        vertify()
        {
          axios.get('http://localhost:3000/Benz')
          .then((res)=>{
          console.log(res);
         const {status,data} = res ;
         if(status===200){
           this.tableData=data;
         }
       })
        },
        getAllData(){
          axios.get("http://localhost:8081/car/GetUnrentedCarNP")
       .then((res)=>{
         console.log(res);
         const {status,data} = res ;
         if(status===200){
           this.tableData=data;
         }
       })
     },
     submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            console.log(this.ruleForm.type)

              axios.post('http://localhost:3000/Benz',{
                car_type: this.ruleForm.type,
               car_brand: this.ruleForm.brand,
          car_rent_money: this.ruleForm.rent_money,
          car_deposit_money:this.ruleForm.deposit_money,
          car_color:this.ruleForm.color,
          car_number:this.ruleForm.number,
          pictureUrl:this.ruleForm.url,
          state: 1
              })
              .then((res)=>{
                const {status} = res;
                if(status===201)
                {
                  console.log("done");
                }
              })
            
            
            
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },

        open() {
          console.log(this.user.user_id)
          console.log("123123")
          axios.post("http://localhost:8081/user/IsIdentity", {"user_id": this.user.user_id}).then(res =>{
            console.log(res.data)
            
            if(res.data == 0){
              console.log("2222")
              this.dialogVisible1 = true;
            }
          })
          
      },

      createOrder(){
        axios.post("http://localhost:8081/order/create",
        {
          "rent_price": this.orderCreateForm.rent_price,
          "deposit_price": this.deposit_p,
          "total_price": this.money,
          "security_id": 1,
          "user_id": this.user.user_id,
          "car_id": this.orderCreateForm.car_id,
          "begintime": this.searchStart.toLocaleString(),
          "endtime": this.searchEnd.toLocaleString()
        }).then(res =>{
            console.log(res.data)
        })
      },
        
      AliPay(){
        axios.post("http://localhost:8081/pay/payment",)
      },

      Wechat(){
        axios.post("http://localhost:8081/pay/payment",)
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
    