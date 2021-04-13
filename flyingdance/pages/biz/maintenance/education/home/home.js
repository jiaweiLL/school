var api = require('../../../../../config/api.js');
Page({
  mixins: [require('../../../../../assets/mixin/themeChanged')],
  data: {
      list: [
        {
          url:"../alterPicture/alterPicture",
          id: 'addteacher',
          name: '轮播图',
          open: false,
        },
        {
          url:'../putVideo/putVideo',
          id: 'viewstudent',
          name: '上传公告视频',
          open: false,
        },
          {
              url:'../putNotice/putNotice',
              id: 'putNotice',
              name: '发布学校公告',
              open: false,
          },
          {
            url:'../putDance/putDance',
            id: 'getdynamic',
            name: '上传舞蹈视频',
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
