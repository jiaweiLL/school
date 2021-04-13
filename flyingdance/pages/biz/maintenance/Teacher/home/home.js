Page({
  // mixins: [require('../mixin/themeChanged')],
  data: {
      list: [
        {
          url:'../viewstudent/viewstudent',
          id: 'viewstudent',
          name: '查看学生',
          open: false,
        },
          {
              url:'../noticeStudent/noticeStudent',
              id: 'putdynamic',
              name: '发布动态',
              open: false,
          },
          {
            url:'../dynamic/dynamic',
            id: 'getdynamic',
            name: '查看动态',
            open: false,
         },
          {
              url:'../putSign/putSign',
              id: 'putsign',
              name: '发布签到',
              open: false,
          },
          {
            url:'../signStudent/signStudent',
            id: 'getsign',
            name: '查看签到',
            open: false,
        },
          
          {
              url:'../schooldynamic/schooldynamic',
              id: 'getmaster',
              name: '校长动态',
              open: false,
          },
          {
            url:"../addstudent/addstudent",
            id: 'addstudent',
            name: '添加学生',
            open: false,
          },
          {
            url:"../putExcle/putExcle",
            id: 'addstudent',
            name: '导出学生',
            open: false,
          }
      ]
  },
  gopage:function(event){
    wx.navigateTo({
        url: event.currentTarget.dataset.url,
        success: function(res) {  
            console.log(res)
        },
        fail:function(res){
          console.log(res)
        }
    })
  },
  changeTheme: function() {
      console.log(this.data);
      getApp().themeChanged(this.data.theme === 'light' ? 'dark' : 'light');
  }
});
