//index.js
//获取应用实例
var app = getApp()
var api = require('../../../../config/api');
Page({
  data:{
  },
  changeScreenLight:function(e){
    var that = this;
    //滑动拉杆获得值
    wx.setScreenBrightness({
      value: parseFloat(e.detail.value).toFixed(1)
    })
    //给屏幕亮度赋值
    wx.getScreenBrightness({
      success: function(res) {
        that.setData({
          ScreenBrightness: res.value
        })
      }
    })
  }
})
