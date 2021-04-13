var api = require('../../../../../config/api.js');
Page({
  data: {
      list: [
        {
          url:'../getStore/getStore',
          id: 'viewstudent',
          name: '查看库存',
          open: false,
        },
          {
              url:'../addStore/addStore',
              id: 'putdynamic',
              name: '添加库存',
              open: false,
          },
          {
            url:'../viewApply/viewApply',
            id: 'putdynamic',
            name: '查看申请',
            open: false,
          },
          {
            url:'../addEntry/addEntry',
            id: 'putdynamic',
            name: '入账',
            open: false,
          },
          
          {
            url:'../viewEntry/viewEntry',
            id: 'putdynamic',
            name: '查账',
            open: false,
          },
          {
            url:'../exportApply/exportApply',
            id: 'putdynamic',
            name: '导出',
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
