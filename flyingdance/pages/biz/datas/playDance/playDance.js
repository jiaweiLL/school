var api = require('../../../../config/api');
Page({
  data: {
      id:'',
      DanceData:[]
  },
  onLoad: function (options) {
    var that = this
    console.log(options)
    var id=options.id;
    that.setData({
      id:options.id
    })
    wx.request({
      url: api.apiUrl+'ResourceServlet?method=getDanceData&id='+id,
      data: {
      },
      method: 'GET',
      header: {
      'content-type': 'application/json' // 默认值
      },
      success: function (res) {
          console.log(res)
          console.log(res.data)
          that.setData({
              DanceData:res.data[0]
          })
      },
      fail:function(res){
      console.log(res)
      }
  
  })
  }
})