var app = getApp()
var api = require('../../../../../config/api.js');
Page({
    data: {
        update:{},
        StoreData:{},
    },
    toapply:function(e){
      var sid=e.currentTarget.id
      console.log(sid)
      wx.navigateTo({
        url: '../applystore/applystore?sid='+sid,
      })
    },
    updata:function(options){
      var that=this
      var cid=that.data.cid
      wx.showModal({
        title: '提示',
        content: '确定更新吗？',
        success: function (sm) {
          if (sm.confirm) {
            var cname=options.detail.value.cname
            var csex=options.detail.value.csex 
            var cphone=options.detail.value.cphone
            var creturn_visit=options.detail.value.creturn_visit //回访日期
            var cage=options.detail.value.cage//生日
            var cfollow_status=that.data.array[that.data.cfollow_status]
            var channel=options.detail.value.channel
            var communication_content=options.detail.value.communication_content
            var consulting_school=options.detail.value.consulting_school
            var csalesman=options.detail.value.csalesman
            var consultation_course=options.detail.value.consultation_course
            var cintroduce=options.detail.value.cintroduce
            var consultation_method=options.detail.value.consultation_method
            var cparents=options.detail.value.cparents
            var cpurpose=options.detail.value.cpurpose
            
              wx.request({
                  url: api.apiUrl+'MarketServlet?method=updataData&cid='+cid+'&cname='+cname+"&csex="+csex+"&cphone="+cphone+"&creturn_visit="+creturn_visit+"&cage="+cage+"&cfollow_status="+cfollow_status+"&channel="+channel+"&communication_content="+communication_content+"&consulting_school="+consulting_school+"&csalesman="+csalesman+"&consultation_course="+consultation_course+"&cintroduce="+cintroduce+"&consultation_method="+consultation_method+"&cparents="+cparents+"&cpurpose="+cpurpose,
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
    }
})
