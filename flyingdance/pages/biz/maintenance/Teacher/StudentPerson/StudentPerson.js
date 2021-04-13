var app = getApp()
var api = require('../../../../../config/api.js');
Page({
    data: {
        id:'',
        StudentData:{}
    },
    update:function(){
        var id=this.data.id
        console.log("update")
        wx.navigateTo({
          url: '../updateStudent/updateStudent?id='+id,
          success: function (res) {
            console.log(res)
            },
            fail:function(res){
            console.log(res)
            }
            
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
    onLoad: function (options) {
        // wx.showNavigationBarLoading();
        var that = this
        var id=options.id;
        that.setData({
            id:options.id
        })
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
            console.log(res.data[0])
            that.setData({
                StudentData:res.data[0]
            })
        },
        fail:function(res){
        console.log(res)
        }
    
    })
    }
})
