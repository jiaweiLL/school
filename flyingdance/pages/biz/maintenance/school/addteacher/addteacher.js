var app = getApp()
var api = require('../../../../../config/api.js');
Page({
    data: {
        id:'',
        date:'',
        time:'',
        update:{},
        StudentData:{}
    },
    bindDateChange: function(e) {
      // console.log('picker发送选择改变，携带值为', e.detail.value)
      this.setData({
        date: e.detail.value
      })
    },
    bindtimeChange: function(e) {
      // console.log('picker发送选择改变，携带值为', e.detail.value)
      this.setData({
        time: e.detail.value
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
            var name=options.detail.value.name
            var sno=options.detail.value.sno
            var password=options.detail.value.password
            var sex=options.detail.value.sex
            var age=options.detail.value.age
            var address=options.detail.value.address
            var curriculum=options.detail.value.curriculum
            var head_id=wx.getStorageSync('user').id
            var head=wx.getStorageSync('user').name
            var phone=options.detail.value.phone
            var teacher_level=options.detail.value.teacher_level
            var school=wx.getStorageSync('user').school
            var time=options.detail.value.time
            wx.request({
                url: api.apiUrl+'SchoolServlet?method=addTeacher&name='+name+"&sno="+sno+"&password="+password+"&sex="+sex+"&address="+address+"&age="+age+"&curriculum="+curriculum+"&head_id="+head_id+"&phone="+phone+"&head="+head+"&teacher_level="+teacher_level+"&school="+school+"&time="+time,
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
