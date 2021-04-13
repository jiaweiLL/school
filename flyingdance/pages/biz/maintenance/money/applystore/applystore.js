var app = getApp()
var api = require('../../../../../config/api.js');
Page({
    data: {
        id:'',
        date:'',
        time:'',
        StoreData:[]
    },
    bindtimeChange: function(e) {
      // console.log('picker发送选择改变，携带值为', e.detail.value)
      this.setData({
        time: e.detail.value
      })
    },
    onLoad: function (options) {
      var that=this
      var sid=options.sid
      wx.request({
          url: api.apiUrl+'MoneyServlet?method=getStoredata&sid='+sid,
          data: {
          },
          method: 'GET',
          header: {
          'content-type': 'application/json' // 默认值
          },
          success: function (res) {
              console.log(res)
              that.setData({
                  StoreData:res.data[0]
              })
            
          },
          fail:function(res){
          console.log(res)
          }
      
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
        content: '确定申请吗？',
        success: function (sm) {
          if (sm.confirm) {
            wx.showLoading({
              title: '玩命加载中',
            })
            var atime=that.data.time
            var aproduct_name=that.data.StoreData.sproduct_Name
            var abrand=that.data.StoreData.sbrand
            var aspecifications=that.data.StoreData.specifications
            var acategory=that.data.StoreData.scategory
            var abuy_price=that.data.StoreData.sbuy_price
            console.log(typeof(that.data.StoreData.sbuy_price))
            var aunit=that.data.StoreData.sunit
            var anumber=options.detail.value.anumber
            var adepartment=options.detail.value.adepartment
            var aname=options.detail.value.aname
            var ause=options.detail.value.ause
            var snumber=that.data.StoreData.snumber
            var sid=that.data.StoreData.sid
            wx.request({
                url: api.apiUrl+'MoneyServlet?method=addApply&atime='+atime+"&aproduct_name="+aproduct_name+"&abrand="+abrand+"&aspecifications="+aspecifications+"&acategory="+acategory+"&abuy_price="+abuy_price+"&aunit="+aunit+"&anumber="+anumber+"&adepartment="+adepartment+"&aname="+aname+"&ause="+ause+"&snumber="+snumber+"&sid="+sid,
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
})
