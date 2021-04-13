var app = getApp()
var api = require('../../../../../config/api.js');
Page({
    data: {
        id:'',
        date:'',
        time:'',
        EntryData:[]
    },
    onLoad: function (options) {
      var that=this
      var eid=options.eid
      wx.request({
          url: api.apiUrl+'MoneyServlet?method=getEntrydata&eid='+eid,
          data: {
          },
          method: 'GET',
          header: {
          'content-type': 'application/json' // 默认值
          },
          success: function (res) {
              console.log(res)
              that.setData({
                 EntryData:res.data[0]
              })
            
          },
          fail:function(res){
          console.log(res)
          }
      
      })
  },
})
