var notices_template = `
  <div>
    <br>
    <h1 class="display-3 text-warning"><center>公告栏</center></h1>
    <br>
    <div class="container r" id="t2">
    <br><br>
      <div class="media border p-3" v-for="(item,index) in notices" v-on:click="selectitem(item.id)">
        <div class="media-body">
          <h3>{{item.title}}</h3><br>
          <p>发表日期：{{item.created}} &emsp; &emsp;<a href="javascript:;" @click.stop="Delete($event,index)">删除</a> &emsp; <a  @click.stop="Modify($event,item.id)" href="javascript:;">修改</a></p>
        </div>
      </div>
      <br><br>
      <button type="button" class="btn btn-primary btn-lg" v-on:click="Addnotice">发布新公告</button>
    </div>
  </div>
  `
const notices = {
  data() {
    return {
      notices: '',
      url: store.state.url
    }
  },
  methods: {
    selectitem(id) {
      router.push({
        path: 'noticeDetail',
        query: { id: id }
      })
    },
    Delete(e, index) {
      //阻止冒泡
      e.stopPropagation();
      //数据库操作
      axios.delete(store.state.url + 'notice/' + this.notices[index].id)
        .then(response => {
          console.log(response.data);
          this.notices.splice(index, 1);//从下标i开始删除1个元素：删除下标为i的元素
        })
        .catch(error => console.log(error));// 请求失败处理
    },
    Modify(e, id) {
      e.stopPropagation();
      router.push({
        path: 'noticeEdit',
        query: { id: id },
      })
    },
    Addnotice() {
      router.push({
        path: 'noticeAdd',
      })
    }
  },
  mounted() {
    //发送get请求
    axios.get(store.state.url + 'notices')
      .then(response => this.notices = response.data)
      .catch(error => console.log(error));// 请求失败处理
  },
  template: notices_template
}
