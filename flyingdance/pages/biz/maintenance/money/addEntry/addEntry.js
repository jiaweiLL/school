var app = getApp()
var api = require('../../../../../config/api.js');
Page({
    data: {
        id:'',
        date:'',
        time:'',
    },
    bindtimeChange: function(e) {
      // console.log('picker发送选择改变，携带值为', e.detail.value)
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
       wx.showModal({
        title: '提示',
        content: '确定添加吗？',
        success: function (sm) {
          if (sm.confirm) {
            wx.showLoading({
              title: '玩命加载中',
            })
            var etime=that.data.time
            var eremit_account=options.detail.value.eremit_account
            var eremit_money=options.detail.value.eremit_money
            var eremit_note=options.detail.value.eremit_note
            var estoreman=options.detail.value.estoreman
            var eleadman=options.detail.value.eleadman
            var edepartment=options.detail.value.edepartment
            wx.request({
                url: api.apiUrl+'MoneyServlet?method=addEntry&etime='+etime+"&eremit_account="+eremit_account+"&eremit_money="+eremit_money+"&eremit_note="+eremit_note+"&estoreman="+estoreman+"&eleadman="+eleadman+"&edepartment="+edepartment,
                data: {
                },
                method: 'GET',
                header: {
                'content-type': 'application/json' // 默认值
                },
                success: function (res) {
                  console.log(res.data[0])
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
      console.log("增加库存")
    }
})
