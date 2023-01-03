<template>
    <el-main>
      
       
     
    <!--表格
        data:数据绑定
        height：只要在el-table元素中定义了height属性，即可实现固定表头的表格，而不需要额外的代码。
        border：表格边框
        prop：字段属性需要跟表格的数据对应
        -->
        <el-table :data="tableData" :height="tableHeight" border style="width: 100%">
          <el-table-column prop="id" label="序号"></el-table-column>
          <el-table-column prop="title" label="公告标题"></el-table-column>
          <!--el-table-column prop="content" label="公告内容"></el-table-column-->
          <el-table-column prop="sendtime" label="创建时间"></el-table-column>
          <el-table-column prop="operation" label="查看公告">
            <template slot-scope="scope">
            <el-button type="primary" title="下单" icon="el-icon-edit" size="mini" @click="dialogFormVisible = true, show(scope.row.content)">
              </el-button>
      </template>
    </el-table-column>
        </el-table>

        <el-dialog title="公告内容" :visible.sync="dialogFormVisible">
          <span>{{this.currentContent}}</span>
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
                  dialogFormVisible: false,
                  currentContent: "",

        
          //表格数据绑定
           tableData: [],
          con:'this.content'
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
       axios.get("http://localhost:8081/announcement/GetAnnouncementByPage")
       .then((res)=>{
         console.log(res);
         const {status,data} = res ;
         if(status===200){
           this.tableData=data;
         }
       })
     },

      show(content){
        this.currentContent = content;
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