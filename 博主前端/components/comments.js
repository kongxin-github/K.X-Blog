var comments_template = `
  <div>
    <br>
    <h1 class="display-3 text-warning"><center>评论管理</center></h1>
    <br>
    <div class="container r" id="t2">
      <div class="list-group" v-for="article in articles">
        <div class="list-group-item"  v-on:click="selectitem(article.id)">
          <h4 class="list-group-item-heading" >
            文章标题：{{article.title}}
          </h4>
          
        </div>
        <div class="media border p-3" v-for="(item,index) in article.commentList">
            <img :src="url+item.user.photo" alt="John Doe" class="mr-3 mt-3 rounded-circle" style="width:40px;">
            <div class="media-body">
                <b>{{item.user.nickname}}</b>
                <a v-on:click="Delete(index,article.commentList)" class="pull-right" href="javascript:;">删除</a>
                <p style="text-indent:1em">{{item.content}}</p>      
            </div>
            </div>
        <div>
      </div>
    </div>
    <foot1></foot1>
  </div>
  `
const comments = {
  data() {
    return {
        articles: '',
        url: store.state.url
    }
  },
  methods: {
    selectitem(id) {
      router.push({
        path: 'detail',
        query: { id: id }
      })
    },
    Delete(index,arr) {
      //数据库操作
      axios.delete(store.state.url + 'comment/' + arr[index].id+'/'+arr[index].articleid)
        .then(response => {
          console.log(response.data);
          arr.splice(index, 1);//从下标i开始删除1个元素：删除下标为i的元素
        })
        .catch(error => console.log(error));// 请求失败处理
    },
  
  },
  mounted() {
    //发送get请求
    axios.get(store.state.url + 'comments')
      .then(response => (this.articles = response.data))
      .catch(error => console.log(error));// 请求失败处理
  },
  template: comments_template
}
