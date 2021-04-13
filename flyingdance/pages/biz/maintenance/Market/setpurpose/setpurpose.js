var app = getApp()
var api = require('../../../../../config/api.js');
Page({
    data: {
        id:'',
        date:'',
        time:'',
        update:{},
        StudentData:{},
        cfollow_status:0,
        channelindex:0,
        consultation_method:'来电',
        channel:['兼职人员','课程顾问','老师地推','宣传页','朋友介绍'],
        array: ['待跟进', '跟进中', '已邀约', '已试听','已到访','已失效'],
        mode: [
          { name: '来电', value: '来电',checked: 'true' },
          { name: '网络', value: '网络' },
          { name: '来访', value: '来访',},
          { name: '其他', value: '其他' },
        ]
    },
    bindDateChange: function(e) {
      // console.log('picker发送选择改变，携带值为', e.detail.value)
      this.setData({
        date: e.detail.value
      })
    },
    bindPickerChange1: function(e) {
      var that=this
      that.setData({
        channelindex: e.detail.value
      })
      console.log(that.data.array[that.data.cfollow_status])
    },
    bindPickerChange: function(e) {
      var that=this
      that.setData({
        cfollow_status: e.detail.value
      })
      console.log(that.data.array[that.data.cfollow_status])
    },
    bindtimeChange: function(e) {
      // console.log('picker发送选择改变，携带值为', e.detail.value)
      this.setData({
        time: e.detail.value
      })
    },
    radioChange: function (e) {
      console.log('radio发生change事件，携带value值为：', e.detail.value)
      var that=this
      that.setData({
        consultation_method:e.detail.value
      })
    },
    sub:function(options){
        var teacher_id=wx.getStorageSync('user').id;
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
            var cname=options.detail.value.cname
            var csex=options.detail.value.csex 
            var cphone=options.detail.value.cphone
            var creturn_visit=that.data.time //回访日期
            var cage=that.data.date//生日
            var cfollow_status=that.data.array[that.data.cfollow_status]
            var channel=that.data.channel[that.data.channelindex]
            var communication_content=options.detail.value.communication_content
            var consulting_school=options.detail.value.consulting_school
            var csalesman=options.detail.value.csalesman
            var consultation_course=options.detail.value.consultation_course
            var cintroduce=options.detail.value.cintroduce
            var consultation_method=that.data.consultation_method
            var cparents=options.detail.value.cparents
            var cpurpose=options.detail.value.cpurpose
            wx.request({
                url: api.apiUrl+'MarketServlet?method=consultAddStudent&cname='+cname+"&csex="+csex+"&cphone="+cphone+"&creturn_visit="+creturn_visit+"&cage="+cage+"&cfollow_status="+cfollow_status+"&channel="+channel+"&communication_content="+communication_content+"&consulting_school="+consulting_school+"&csalesman="+csalesman+"&consultation_course="+consultation_course+"&cintroduce="+cintroduce+"&consultation_method="+consultation_method+"&cparents="+cparents+"&cpurpose="+cpurpose,
                data: {
                },
                method: 'GET',
                header: {
                'content-type': 'application/json' // 默认值
                },
                success: function (res) {
                  console.log(res.data[0])
                  if(res.data[0]=='-1'){
                    wx.showToast({
                      title: '该账号已存在', 
                      icon: 'none',
                      duration: 2000
                    })
                  }else{
                      wx.navigateTo({
                          url: '../../../../../datas/msg/msg_success',
                       })              
                      }              
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
      console.log("增加老师")
    }
})
