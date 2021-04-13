var app = getApp()
var api = require('../../../../../config/api.js');
Page({
    data: {
        id:'',
        date:'',
        time:'',
        update:{},
        StudentData:{}, 
        power:'人事3',
        items: [
          { name: '校长2', value: '校长',  },
          { name: '财务47', value: '财务' },
          { name: '人事37', value: '人事',checked: 'true' },
          { name: '市场57', value: '市场' },
          { name: '教务67', value: '教务' },
        ]
    },
    radioChange: function (e) {
      console.log('radio发生change事件，携带value值为：', e.detail.value)
      var that=this
      that.setData({
        power:e.detail.value
      })
    },
    bindDateChange: function(e) {
      //生日
      // console.log('picker发送选择改变，携带值为', e.detail.value)
      this.setData({
        date: e.detail.value
      })
    },
    bindtimeChange: function(e) {
      //入职时间
      // console.log('picker发送选择改变，携带值为', e.detail.value)
      this.setData({
        time: e.detail.value
      })
    },
    sub:function(options){
        var that = this
        var power=that.data.power
        var form =options.detail.value
        if(options.detail.value.school!= undefined){
            var school=options.detail.value.school
        }else{
          var school='none';
        }
        console.log(power)
        console.log(school)
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
            var phone=options.detail.value.phone
            
            var time=options.detail.value.time
            wx.request({
                url: api.apiUrl+'PersonnelServlet?method=addPeople&name='+name+"&sno="+sno+"&password="+password+"&sex="+sex+"&address="+address+"&age="+age+"&phone="+phone+"&school="+school+"&time="+time+"&power="+power,
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
