<template>
<div style="width: 88%;margin-top:10px;">
    <el-form :inline="true" :model="formInline" class="demo-form-inline">   
        <el-form-item>
            <el-button type="primary" @click="adduser">添加</el-button> 

        </el-form-item>
    </el-form>

    <el-table fit :data="userList"  style="width: 80%;margin-left: 20px">
        <el-table-column disable prop="user_id" label="用户id" width="180">
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="180">
        </el-table-column>
        <el-table-column prop="email" label="邮箱" width="160">
        </el-table-column>
        
        <el-table-column label="操作" width="150">
            <template slot-scope="scope">
                <el-button type="primary" size="small" @click="userEdit(scope.$index, scope.row)">编辑</el-button>
                <el-button type="danger" size="small" @click="userDelete(scope.row.user_id)">删除</el-button>
            </template>
        </el-table-column>

    </el-table>

    <el-dialog title="编辑用户信息" :visible="userEditForm" >
        <el-form ref="editsForm" :model="editsForm" label-width="80px">
            <el-form-item label="用户id">
                <el-input  v-model="editsForm.user_id" max-length="12" disabled="disabled"></el-input>
            </el-form-item>
            
            <el-form-item label="姓名">
                <el-input v-model="editsForm.name"></el-input>
            </el-form-item>
            <el-form-item label="邮箱">
                <el-input v-model="editsForm.email"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="UserModifyEdit()">确定</el-button>
                <el-button @click="userEditForm = false">取消</el-button>

            </el-form-item>
        </el-form>
    </el-dialog>

    <el-dialog title="添加用户信息" :visible="userAddForm" >
        <el-form ref="addForm" :model="addForm" label-width="80px">

            <el-form-item label="手机号">
                <el-input v-model="addForm.phone_number"></el-input>
            </el-form-item>
            
            <el-form-item label="用户名">
                <el-input v-model="addForm.user_name"></el-input>
            </el-form-item>
            <el-form-item label="密码">
                <el-input v-model="addForm.password"></el-input>
            </el-form-item>
            <el-form-item label="姓名">
                <el-input v-model="addForm.name"></el-input>
            </el-form-item>
            <el-form-item label="邮箱">
                <el-input v-model="addForm.email"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="UserAdd(addForm)">确定</el-button>
                <el-button @click="userAddForm = false">取消</el-button>

            </el-form-item>
        </el-form>
    </el-dialog>

</div>
</template>

<script>
import axios from 'axios'
    export default {
        name: "Administrator",
        data() {
            return {
                userAddForm:false,
                userEditForm:false,
                addForm:{
                    user_name: "",
                    name: "",
                    phone_number: "",
                    password: "",
                    email: "",
                },
                userList: [{}
                ],
                modifyindex:0,
                editsForm:{
                    user_id:'',
                    name:'',
                    email:'',
                },

            }
        },
        methods: {
            
            UserAdd(formName) {
            console.log("123456")
              axios.post("http://localhost:8081/operator/create",
              {
                "user_name": this.addForm.user_name,
                "name": this.addForm.name,
                "phone_number": this.addForm.phone_number,
                "password": this.addForm.password,
                "email": this.addForm.email,
                "role_id": 1,
              }
              )
              .then((res)=>{
                const {status} = res;
                  console.log("done");
                  this.getAllData();
              })
          },   
            
            getAllData(){
            axios.get("http://localhost:8081/operator/getInfo")
            .then((res)=>{
             console.log(res);
             const {status,data} = res ;
             if(status===200){
              this.userList=data;
            }
            })
            },
            closeDialog(){
                this.userEditForm = false;
                this.userAddForm = false;
            },
            userEdit(index,row){
                this.userEditForm = true; //编辑信息模态框显示
                this.editsForm = Object.assign({}, row);//  获得所有数据显示在编辑信息模态框里面
                this.modifyindex = index
            },
            UserModifyEdit(){
                let modifyuser=this.editsForm;
                console.log(modifyuser);
                    axios.post('http://localhost:8081/operator/editInfo',
                    {
                "user_id": this.editsForm.user_id,
                "name": this.editsForm.name,
                "email": this.editsForm.email,
              })
                    .then(res=>{
                        console.log("modify  res:"+res)

                            this.getAllData();
                            this.userEditForm  = false;
                        this.$message('修改成功')
                        // }
                    })

            },
            adduser(){
                console.log('adduser');
                this.userAddForm = true; //编辑信息模态框显示

            },
            userDelete(ID){
        console.log(ID);
         axios.post("http://localhost:8081/operator/delete", {"user_id": ID})
       .then((res)=>{
        console.log(res);
          this.getAllData();

       })
       
     },  

        },
       created() {
            this. getAllData();
        }
    }
</script>

<style scoped>

</style>