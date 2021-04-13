var app = getApp()
var api = require('../../../../../config/api.js');
Page({
    data: {
        id:'',
        update:{},
        teacherData:{},
        phonenum: ""
    },
     //打电话  还是   添加到联系人   或者复制联系方式
  viewClick01: function (e) {
      var that=this
    var teacher_phone =that.data.teacherData.phone;
    //显示“呼叫”、“添加联系人”弹窗
    wx.showActionSheet({
      itemList: ['呼叫', '添加联系人','复制联系方式'],
      success: function (res) {
        console.log("点击电话 res：", res)
        if (res.tapIndex == 0) {//直接呼叫
          wx.makePhoneCall({
            phoneNumber: teacher_phone,
            success: function (res_makephone) {
              console.log("呼叫电话返回：", res_makephone)
            }
          })
        } else if (res.tapIndex == 1) {//添加联系人
          wx.addPhoneContact({
            firstName: '',
            mobilePhoneNumber: teacher_phone,
            success: function (res_addphone) {
              console.log("电话添加联系人返回：", res_addphone)
            }
          })
        } else if (res.tapIndex == 2){
          wx.setClipboardData({
            data: teacher_phone,
            success(res) {
              wx.getClipboardData({
                success(resx) {
                 wx.showToast({
                   title: '复制成功',
                   icon:'none'
                 })
                 console.log(resx)
                }
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
