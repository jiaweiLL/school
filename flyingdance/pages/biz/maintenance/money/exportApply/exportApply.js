var app = getApp()
var api = require('../../../../../config/api.js');
Page({
    data: {
        id:'',
        date:'',
        time:'',
        coursetype:''
    },
    radioChange: function (e) {
      var that=this
      that.setData({
        coursetype:e.detail.value
      })
    },
    bindDateChange: function(e) {
      console.log('picker发送选择改变，携带值为', e.detail.value)
      this.setData({
        date: e.detail.value
      })
    },
    bindtimeChange: function(e) {
      console.log('picker发送选择改变，携带值为', e.detail.value)
      this.setData({
        time: e.detail.value
      })
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
       var starttime=that.data.date
       var endtime=that.data.time
       var coursetype=that.data.coursetype
       var adepartment=options.detail.value.adepartment
       var receive=options.detail.value.receive
            var subject=options.detail.value.subject
            wx.request({
                url: api.apiUrl+'MoneyServlet?method=exportApply&receive='+receive+"&subject="+subject+"&adepartment="+adepartment+"&starttime="+starttime+"&endtime="+endtime+"&coursetype="+coursetype,
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
      console.log("导出账目")
    }
})
