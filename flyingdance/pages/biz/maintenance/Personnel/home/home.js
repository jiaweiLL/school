var api = require('../../../../../config/api.js');
Page({
  mixins: [require('../../../../../assets/mixin/themeChanged')],
  data: {
      list: [
        {
          url:"../addPeople/addPeople",
          id: 'addteacher',
          name: '添加员工',
          open: false,
        },      
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
