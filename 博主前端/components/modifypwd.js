var modifypwd_template = `
<div class="container">
  <br>
      <h1 class="display-3 text-warning"><center>K.X的博客</center></h1>
  <br>
  <div class="row">
    <div class="col-md-3">
    </div>
    <div class="container col-md-6" id="t3">
      <br>
      <h4 class="text-center">修改密码</h4><br>

          <div class="form-group">
            <label for="username"><b>用户名:</b></label>&nbsp;
            <input type="text" v-model="user.username" class="form-control" id="username" disabled="disabled">
          </div>
          <div class="form-group">
              <label for="pwd">原密码:</label>&nbsp;
              <input type="password" v-model="pwd" class="form-control" id="pwd">
              <label for="newpwd">新密码:</label>&nbsp;
              <input type="password" v-model="newpwd" class="form-control" id="newpwd">
              <label for="qrpwd">确认密码:</label>&nbsp;
              <input type="password" v-model="qrpwd" class="form-control" id="qrpwd">
          </div>
          <br>
          <div class="form-group">
              <button v-on:click="modifypwd_1" class="btn btn-primary btn-block">确定</button>
          </div>
    </div>
    <div class="col-md-3">
    </div>
  </div>
</div>
  `
const modifypwd = {
  data() {
    return {
      user: '',
      pwd: '',
      newpwd: '',
      qrpwd: '',
      url: store.state.url,
    }
  },
  methods: {
    modifypwd_1() {
        if(this.newpwd==''||this.pwd==''){
            alert("密码不能为空！");
            return;
        }
        if(this.newpwd!=this.qrpwd){
            alert("密码不一致！");
            return;
        }
        this.user.password=this.pwd;
        axios.post(store.state.url + 'isPwd/', this.user)
            .then(response => {
                 if(response.data){
                      this.user.password=this.qrpwd;
                      axios.put(store.state.url + 'setuser/', this.user)
                      .then(response => {
                          if(response.data=="success"){
                            alert("修改成功");
                            axios.post(this.url + 'logout')
                                  .then(response => {
                                      var temps = response.data;
                                      if (temps.status == 'success') {
                                          router.push({
                                              path: '/'
                                              })
                                          }
                                      })
                            .catch(error => console.log(error));// 请求失败处理
                          }
                      })
                      .catch(error => console.log(error));// 请求失败处理
                  }else{
                    alert("原密码错误！");
                    return;
                  }
            })
            .catch(error => console.log(error));// 请求失败处理
    },
  },
  mounted() {
    axios.get(store.state.url+'getuser')
    .then(response => {this.user = response.data;
      console.log(this.user)
    })
    .catch(error => console.log(error));// 请求失败处理
  },
  template: modifypwd_template
}
