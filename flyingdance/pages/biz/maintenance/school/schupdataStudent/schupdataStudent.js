var app = getApp()
var api = require('../../../../../config/api.js');
Page({
    data: {
        id:'',
        StudentData:{}
    },
    topay:function(e){
      console.log(e)
      wx.navigateTo({
        url: '../payupdataStudent/payupdataStudent?id='+e.currentTarget.id,
      })
    },
    delete:function(){
      wx.showModal({
        title: '提示',
        content: '确定删除吗？',
        success: function (sm) {
          if (sm.confirm) {
            var id=this.data.id
            console.log(id)
            wx.request({
                url: api.apiUrl+'SysServlet?method=deleteDate&id='+id,
                data: {
                },
                method: 'GET',
                header: {
                'content-type': 'application/json' // 默认值
                },
                success: function (res) {
                    console.log(res)
                    wx.showToast({
                        title:'删除成功', 
                        icon: 'none',
                        duration: 2000,
                        success: function () {
                            wx.redirectTo({
                                url: '../viewstudent/viewstudent',
                              })
                        }, 
                      })
                },
                fail:function(res){
                console.log(res)
                }
              })
            
          }else{
          }
        }
        })
    },
    updata:function(options){
      var that=this
      wx.showModal({
        title: '提示',
        content: '确定要更新吗？',
        success: function (sm) {
          if (sm.confirm) {
            var id=that.data.id
            var name=options.detail.value.name
            var sno=options.detail.value.sno
            var password=options.detail.value.password
            var sex=options.detail.value.sex
            var address=options.detail.value.address
            var age=options.detail.value.age
            var parents=options.detail.value.parents
            var phone=options.detail.value.phone
            var curriculum=options.detail.value.curriculum
            var class_hour=options.detail.value.class_hour
            var level=options.detail.value.level
            var class_name=options.detail.value.class_name
            wx.request({
                url: api.apiUrl+'SchoolServlet?method=schupdateStudentData&name='+name+"&id="+id+"&sno="+sno+"&password="+password+"&sex="+sex+"&address="+address+"&age="+age+"&parents="+parents+"&phone="+phone+"&curriculum="+curriculum+"&class_hour="+class_hour+"&level="+level+"&class_name="+class_name,
                data: {
                },
                method: 'GET',
                header: {
                'content-type': 'application/json' // 默认值
                },
                success: function (res) {
                    console.log(res)
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
          }else{
            console.log("用户点击取消")
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
                    StudentData:res.data[0]
            })
            },
            fail:function(res){
            console.log(res)
            }
        
        })
    },
    delete:function(){
      var that=this
      var id=that.data.id
      console.log("删除")
      wx.showModal({
        title: '提示',
        content: '确定要更新吗？',
        success: function (sm) {
          if (sm.confirm) {
            wx.request({
              url: api.apiUrl+'SysServlet?method=deleteDate&id='+id,
              data: {
              },
              method: 'GET',
              header: {
              'content-type': 'application/json' // 默认值
              },
              success: function (res) {
                  console.log(res)
                  wx.showToast({
                      title:'删除成功', 
                      icon: 'none',
                      duration: 2000,
                      success: function () {
                          wx.redirectTo({
                              url: '../viewTeaStudent/viewTeaStudent',
                            })
                       }, 
                    })
              },
              fail:function(res){
              console.log(res)
              }
          
          })
          }else{
            console.log("用户点击取消")
          }
        }
      })
      
  }
})
