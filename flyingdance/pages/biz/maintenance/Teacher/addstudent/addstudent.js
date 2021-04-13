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
        console.log('picker发送选择改变，携带值为', e.detail.value)
        this.setData({
          date: e.detail.value
        })
      },
      bindtimeChange: function(e) {
        console.log('picker发送选择改变，携带值为', e.detail.value)
        this.setData({
          time: e.detail.value
        })
      },
    sub:function(options){
        
        console.log(options.detail.value.sex)
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
            console.log("start")
            var name=options.detail.value.name
            var sno=options.detail.value.sno
            var password=options.detail.value.password
            var sex=options.detail.value.sex
            var age=options.detail.value.age
            var address=options.detail.value.address
            var class_name=options.detail.value.class_name
            var class_hour=options.detail.value.class_hour
            var head=wx.getStorageSync('user').name;
            var head_id=wx.getStorageSync('user').id
            var school=wx.getStorageSync('user').school
            var schoolmaster=wx.getStorageSync('user').schoolmaster
            var schoolmaster_id=wx.getStorageSync('user').schoolmaster_id
            var school=wx.getStorageSync('user').school
            var curriculum=wx.getStorageSync('user').curriculum
            var parents=options.detail.value.parents
            var phone=options.detail.value.phone
            var renew=options.detail.value.renew          
            var time=options.detail.value.time
            var level=options.detail.value.level
            wx.request({
                url: api.apiUrl+'SysServlet?method=addstudent&name='+name+"&head="+head+"&sno="+sno+"&password="+password+"&sex="+sex+"&address="+address+"&age="+age+"&curriculum="+curriculum+"&class_hour="+class_hour+"&head_id="+head_id+"&parents="+parents+"&phone="+phone+"&renew="+renew+"&class_name="+class_name+"&time="+time+"&level="+level+"&school="+school+"&schoolmaster="+schoolmaster+"&schoolmaster_id="+schoolmaster_id,
                data: {
                },
                method: 'GET',
                header: {
                'content-type': 'application/json' // 默认值
                },
                success: function (res) {
                  console.log(res)
                  if(res.data[0]=='-1'){
                    wx.showToast({
                      title: '该账号已存在', 
                      icon: 'none',
                      duration: 2000
                    })
                  }else if(res.statusCode=='500'){
                    wx.showToast({
                      title: '请正确输入信息数字或文字', 
                      icon: 'none',
                      duration: 2000
                    })
                  }
                  else{
                      wx.navigateTo({
                          url: '../../../../../datas/msg/msg_success',
                       })              
                      }            
              },
              fail:function(res){
                console.log(res)
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
      console.log("增加学生")
    }
})
