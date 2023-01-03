<template>
  <div class="navbar">
    <el-menu
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      :collapse="$store.state.isCollapse"
      :default-openeds="openeds"
    >
    
    <body>
      <div id="app">

        <p v-if=user.type>
          <el-submenu index="1">
  <template slot="title">
    <i class="el-icon-s-goods"></i>
    <span>汽车租赁</span>
  </template>
  <el-menu-item-group>
   
    <el-menu-item index="1-1" @click="$router.push('/carU')"><i class="el-icon-truck"></i>车辆信息</el-menu-item>
 
  </el-menu-item-group>


</el-submenu>

<el-submenu index="2">
  <template slot="title">
    <i class="el-icon-document"></i>
    <span>订单管理</span>
  </template>
  <el-menu-item-group>
    <el-menu-item index="2-1" @click="$router.push('/orderU')">
      <i class="el-icon-s-finance"></i>我的订单
    </el-menu-item>
  </el-menu-item-group>
</el-submenu>

<el-submenu index="3">
  <template slot="title">
    <i class="el-icon-setting"></i>
    <span>个人信息</span>
  </template>
  <el-menu-item-group>
    <el-menu-item index="3-1" @click="$router.push('/UserInfoU')">
      <i class="el-icon-user"></i>用户信息
    </el-menu-item>

  </el-menu-item-group>
</el-submenu>


<el-submenu index="4">
  <template slot="title">
    <i class="el-icon-document"></i>
    <span>公告系统</span>
  </template>
  <el-menu-item-group>
    <el-menu-item index="4-1" @click="$router.push('/showannounce')">
    <i class="el-icon-s-finance"></i>公告
    
  </el-menu-item>
      
    
  </el-menu-item-group>
</el-submenu>
        </p>
        
			<p v-else>
        <el-submenu index="1">
    <template slot="title">
      <i class="el-icon-s-goods"></i>
      <span>商品管理</span>
    </template>
    <el-menu-item-group>
      <template slot="title">
        <i class="el-icon-truck"></i>汽车信息管理
      </template>
      <el-menu-item index="1-1" @click="$router.push('/benz')"><i class="el-icon-truck"></i>车辆信息</el-menu-item>
      
      <el-menu-item index="1-2" @click="$router.push('/addcar')"><i class="el-icon-truck"></i>添加汽车信息</el-menu-item>
      <el-menu-item index="1-3" @click="$router.push('/deletecar')"><i class="el-icon-truck"></i>删除汽车信息</el-menu-item>
    </el-menu-item-group>
     

  </el-submenu>

  <el-submenu index="2">
    <template slot="title">
      <i class="el-icon-document"></i>
      <span>订单管理</span>
    </template>
    <el-menu-item-group>
      <el-menu-item index="2-1" @click="$router.push('/ongoing')">
        <i class="el-icon-s-finance"></i>所有订单
      </el-menu-item>
    </el-menu-item-group>
  </el-submenu>

  <el-submenu index="3">
    <template slot="title">
      <i class="el-icon-setting"></i>
      <span>系统管理</span>
    </template>
    <el-menu-item-group>
      <el-menu-item index="3-1" @click="$router.push('/usersystem')">
        <i class="el-icon-user"></i>用户信息管理
      </el-menu-item>
      
     
    </el-menu-item-group>
  </el-submenu>


  <el-submenu index="4">
    <template slot="title">
      <i class="el-icon-document"></i>
      <span>公告管理</span>
    </template>
    <el-menu-item-group>
      <el-menu-item index="4-1" @click="$router.push('/showannounce')">
        <i class="el-icon-s-finance"></i>公告
        
      </el-menu-item>
      <el-menu-item index="4-2" @click="$router.push('/addannounce')">
        <i class="el-icon-s-finance"></i>添加公告
      </el-menu-item>
      <el-menu-item index="4-3" @click="$router.push('/deleteannounce')">
        <i class="el-icon-s-finance"></i>删除公告
      </el-menu-item>
    </el-menu-item-group>
  </el-submenu>

      </p>

      

    </div>

    </body>

</el-menu>
</div>
</template>

<script>
export default {
data() {
return {
openeds: ["2", "3"],
judge:"",

user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},

is_flag: 0,
};
},
created(){
},
methods: {
handleOpen(key, keyPath) {
console.log("open" + key, keyPath);
},
handleClose(key, keyPath) {
console.log("close" + key, keyPath);
},
},
};
</script>

<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
width: 200px;
min-height: 650px;
}
</style>