var app = getApp()
var api = require('../../../../../config/api.js');
Page({
    data: {
        id:'',
    },
    sub:function(options){
        var that = this
        var form =options.detail.value
         for(var item in form){
        if(!form[item]){ //验证form表单是否填写完整
          wx.showToast({
          title: '请将信息填写完整',
          icon: 'none',
          duration: 2000
         })
         return;
         }
       }
       wx.showLoading({
         title: '正在加载中...',
       })
       var receive=options.detail.value.receive
            var subject=options.detail.value.subject
            wx.request({
                url: api.apiUrl+'TeacherServlet?method=putExcle&receive='+receive+"&subject="+subject+"&id="+wx.getStorageSync('user').id,
                data: {
                },
                method: 'GET',
                header: {
                'content-type': 'application/json' // 默认值
                },
                success: function (res) {
                  console.log(res)
                  if(res.data[0]=='false'){
                    wx.showToast({
                      title: '邮件发送失败', 
                      icon: 'none',
                      duration: 2000
                    })
                  }else{
                      wx.navigateTo({
                          url: '../../../../../datas/msg/msg_success',
                       })              
                      }            
                  },
                  fail:function(res){
                    wx.navigateTo({
                      url: '../../../../../datas/msg/msg_warn',
                    }) 
                  }
            
            })       
    },
    onLoad: function (options) {
      console.log("增加学生")
    }
})
