var app = getApp()
var api = require('../../../../../config/api.js');
Page({
    data: {
        id:'',
        date:'',
        time:'',
        ApplyData:[]
    },
    onLoad: function (options) {
      var that=this
      var aid=options.aid
      wx.request({
          url: api.apiUrl+'MoneyServlet?method=getApplydata&aid='+aid,
          data: {
          },
          method: 'GET',
          header: {
          'content-type': 'application/json' // 默认值
          },
          success: function (res) {
              console.log(res)
              that.setData({
                  ApplyData:res.data[0]
              })
            
          },
          fail:function(res){
          console.log(res)
          }
      
      })
  },
})
