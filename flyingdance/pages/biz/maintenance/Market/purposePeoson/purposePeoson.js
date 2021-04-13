var app = getApp()
var api = require('../../../../../config/api.js');
Page({
    data: {
        cid:'',
        update:{},
        StudentData:{},
        array:[],
        cfollow_status:0
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
    bindPickerChange: function(e) {
      var that=this
      that.setData({
        cfollow_status: e.detail.value
      })
      console.log(that.data.array[that.data.cfollow_status])
    },
    onLoad: function (options) {
        var that=this
        var cid=options.cid
        wx.request({
            url: api.apiUrl+'MarketServlet?method=getConsultStudent&cid='+cid,
            data: {
            },
            method: 'GET',
            header: {
            'content-type': 'application/json' // 默认值
            },
            success: function (res) {
                console.log(res)
                that.setData({
                    cid:options.cid,
                    StudentData:res.data[0]
                })
                var StudentData=that.data.StudentData
                if(StudentData.cfollow_status=='待跟进'){
                  that.setData({
                    array: ['待跟进', '跟进中', '已邀约', '已试听','已到访','已失效']
                  })                    
                }else if(StudentData.cfollow_status=='跟进中'){
                  that.setData({
                    array: ['跟进中', '已邀约', '已试听','已到访','已失效','待跟进']
                  })            
                }else if(StudentData.cfollow_status=='已邀约'){
                  that.setData({
                    array: ['已邀约', '已试听','已到访','已失效','待跟进','跟进中']
                  })                   
                }else if(StudentData.cfollow_status=='已试听'){
                  that.setData({
                    array: ['已试听','已到访','已失效','待跟进','跟进中','已邀约']
                  })                
                }else if(StudentData.cfollow_status=='已到访'){
                  that.setData({
                    array: ['已到访','已失效','待跟进','跟进中','已邀约','已试听']
                  })                   
                }else if(StudentData.cfollow_status=='已失效'){
                  that.setData({
                    array: ['已失效','待跟进','跟进中','已邀约','已试听','已到访']
                  })                 
                }else{
                  that.setData({
                    array: ['待跟进','跟进中', '已邀约', '已试听','已到访','已失效']
                  })   
                }
            },
            fail:function(res){
            console.log(res)
            }
        
        })
    }
})
