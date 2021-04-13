var app = getApp()
var api = require('../../../../../config/api.js');
Page({
    data: {
        id:'',
        update:{},
        StudentData:{}
    },
    updata:function(options){
      var that=this
      var id=that.data.id
      wx.showModal({
        title: '提示',
        content: '确定更新吗？',
        success: function (sm) {
          if (sm.confirm) {
              var that=this        
              var name=options.detail.value.name
              var phone=options.detail.value.phone
              var password=options.detail.value.password
              var sex=options.detail.value.sex
              var address=options.detail.value.address
              var level=options.detail.value.level
              wx.request({
                  url: api.apiUrl+'SysServlet?method=updateData&name='+name+"&id="+id+"&phone="+phone+"&password="+password+"&sex="+sex+"&address="+address+"&level="+level,
                  data: {
                  },
                  method: 'GET',
                  header: {
                  'content-type': 'application/json' // 默认值
                  },
                  success: function (res) {
                    console.log(res)
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
            }
          }
        })
    },
    onLoad: function (options) {
        var that=this
        var id=options.id
        wx.request({
            url: api.apiUrl+'SysServlet?method=getStudentDate&id='+id,
            data: {
            },
            method: 'GET',
            header: {
            'content-type': 'application/json' // 默认值
            },
            success: function (res) {
                console.log(res)
                that.setData({
                    id:options.id,
                    StudentData:res.data[0]
            })
            },
            fail:function(res){
            console.log(res)
            }
        
        })
    }
})
