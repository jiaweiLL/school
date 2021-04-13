var app = getApp()
var api = require('../../../../../config/api');
Page({
    data: {
        id:'',
        update:{},
        StudentData:{}
    },
    sub:function(options){
        var name=wx.getStorageSync('user').name;
        var that = this
        var form =options.detail.value
         for(var item in form){
        if(!form[item]){ //验证form表单是否填写完整
          wx.showToast({
          title: '请填写标题和内容',
          icon: 'none',
          duration: 2000
         })
         return;
         }
      }
      wx.showModal({
        title: '提示',
        content: '确定要发布吗？',
        success: function (sm) {
          if (sm.confirm) {
            var title=options.detail.value.title
            var text1=options.detail.value.text1
            var name_id=wx.getStorageSync('user').id
            wx.request({
                url: api.apiUrl+'SchoolServlet?method=putNotice&name='+name+"&title="+title+"&text="+text1+"&name_id="+name_id,
                data: {
                },
                method: 'GET',
                header: {
                'content-type': 'application/json' // 默认值
                },
                success: function (res) {
                  console.log(res.data)
                   
                  wx.navigateTo({
                    url: '../../../../../datas/msg/msg_success',
                  })              
                 },
                  fail:function(res){
                    wx.navigateTo({
                      url: '../../../../../datas/msg/msg_warn',
                    }) 
                  }
            
            })
            } else if (sm.cancel) {
              console.log('用户点击取消')
            }
          }
        })
        
    },
    onLoad: function (options) {
      console.log("增加学生")
    }
})
