var add_template = `
<div id="t2" class="container">
    <br><br><br>
    <div class="container">
      <div>
        <h5><b>博客标题：</b></h5>
        <input type="text" class="form-control" v-model="article.title" />
      </div>

      <h5><b>内容：</b></h5>
      <vue-html5-editor @change="updateData" :content="article.content" :height="330" ref="editor"></vue-html5-editor>
      <br>
      <button type="button" class="btn-block btn-primary" v-on:click="Save">保存</button>
    </div>
    <foot1></foot1>
</div>
        `
const add = {
  data() {
    return {
      article: {title: '', content: ''},
      user: '',
      url:store.state.url,
    }
  },
  methods: {
    updateData: function (data) {
      // sync content to component
      this.article.content = data
    },
    //保存
    Save: function () {
      console.log(this.article);
      if (this.article.title == '' || this.article.content == '') {
        alert("标题或内容不能为空!");
        return;
      }

      this.article.content = this.article.content.replace(/<img/g, '<img  class="img-fluid" ')  //增加图片自适应样式
      axios.post(this.url + 'article', this.article)
        .then(response => console.log(response.data))
        .catch(error => console.log(error));// 请求失败处理

      //还原模板
      this.article = {title: '', content: ''}
      alert("添加成功");
    },
   

  },
  template: add_template
}
