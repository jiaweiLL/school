var api = require('../../../../../config/api.js');
Page({
  mixins: [require('../../../../../assets/mixin/themeChanged')],
  data: {
      list: [
        {
          url:'../viewteacher/viewteacher',
          id: 'viewstudent',
          name: '查看老师',
          open: false,
        },
          {
              url:'../noticeteacher/noticeteacher',
              id: 'putdynamic',
              name: '发布动态',
              open: false,
          },
          {
            url:'../../Teacher/dynamic/dynamic',
            id: 'getdynamic',
            name: '查看动态',
            open: false,
         },
          {
            url:"../addteacher/addteacher",
            id: 'addteacher',
            name: '添加老师',
            open: false,
          },
          {
            url:"../../money/addEntry/addEntry",
            id: 'studentpay',
            name: '学校入账',
            open: false,
          },
          {
            url:"../../money/viewEntry/viewEntry",
            id: 'studentpay',
            name: '学校账目',
            open: false,
          },
          {
            url:"../../money/addStore/addStore",
            id: 'studentpay',
            name: '添加学校库存',
            open: false,
          },
          {
            url:"../../money/getStore/getStore",
            id: 'studentpay',
            name: '查看学校仓库',
            open: false,
          },
          {
            url:"../../money/viewApply/viewApply",
            id: 'studentpay',
            name: '学校申请记录',
            open: false,
          },
      ]
  },
  gopage:function(event){
    console.log(event.currentTarget.dataset.log+event.currentTarget.dataset.url);
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
