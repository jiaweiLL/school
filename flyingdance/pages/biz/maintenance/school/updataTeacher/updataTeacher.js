var app = getApp()
var api = require('../../../../../config/api.js');
Page({
    data: {
        id:'',
        update:{},
        teacherData:{}
    },
    updata:function(options){
      var that=this
      wx.showModal({
        title: '提示',
        content: '确定要更新吗？',
        success: function (sm) {
          if (sm.confirm) {
            var id=that.data.id
            console.log(options.detail.value)
            var name=that.data.teacherData.name
            var sno=options.detail.value.sno
            var password=options.detail.value.password
            var sex=options.detail.value.sex
            var age=options.detail.value.age
            var address=options.detail.value.address
            var phone=options.detail.value.phone
            var curriculum=options.detail.value.curriculum
            var teacher_level=options.detail.value.teacher_level
            wx.request({
                url: api.apiUrl+'SchoolServlet?method=updateTeacherData&name='+name+"&id="+id+"&sno="+sno+"&password="+password+"&sex="+sex+"&address="+address+"&phone="+phone+"&curriculum="+curriculum+"&age="+age+"&teacher_level="+teacher_level,
                data: {
                },
                method: 'GET',
                header: {
                'content-type': 'application/json' // 默认值
                },
                success: function (res) {
                    console.log("更新成功")
                    console.log(res.data)
                    wx.showToast({
                        title:'更新成功', 
                        icon: 'none',
                        duration: 2000,
                        success: function () {
                            wx.navigateBack({
                                delta: 1
                            })
                         },     
                    })               
                },
                fail:function(res){
                console.log(res)
                }
            
            })
          }else if (sm.cancel) {
            console.log('用户点击取消')
          }
        }
      })
    },
    onLoad: function (options) {
        var that=this
        var id=options.id
        console.log(id)
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
                    teacherData:res.data[0]
            })
            },
            fail:function(res){
            console.log(res)
            }
        
        })
  }
})
