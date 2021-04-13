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
        console.log(options.detail.value)
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
            var stime=that.data.time
            var sproduct_Name=options.detail.value.sproduct_Name
            var sbrand=options.detail.value.sbrand
            var specifications=options.detail.value.specifications
            var scategory=options.detail.value.scategory
            var sbuy_price=options.detail.value.sbuy_price
            var sguide_price=options.detail.value.sguide_price
            var sunit=options.detail.value.sunit
            var snumber=options.detail.value.snumber
            var school=options.detail.value.school
            wx.request({
                url: api.apiUrl+'MoneyServlet?method=addStore&stime='+stime+"&sproduct_Name="+sproduct_Name+"&sbrand="+sbrand+"&specifications="+specifications+"&scategory="+scategory+"&sbuy_price="+sbuy_price+"&sguide_price="+sguide_price+"&sunit="+sunit+"&snumber="+snumber+"&school="+school,
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
