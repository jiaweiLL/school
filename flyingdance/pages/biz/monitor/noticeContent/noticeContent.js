var api = require('../../../../config/api.js');
Page({
  data: {
      id:'',
      NoticeData:[]
  },
  onLoad: function (options) {
    var that = this
    console.log(options)
    var id=options.id;
    that.setData({
      id:options.id
    })
    wx.request({
      url: api.apiUrl+'ResourceServlet?method=getNoticeData&id='+id,
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
              NoticeData:res.data[0]
          })
      },
      fail:function(res){
      console.log(res)
      }
  
  })
  }
})