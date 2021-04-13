var app = getApp()
var api = require('../../../../config/api');
Page({
    data: {
      mobileModel:'',
      mobileePixelRatio:'',
      windowWidth:'',
      windowHeight:'',
      language:'',
      version:'',
      system: ''
    },
    onLoad: function (options) {
      var that=this;
      wx.getSystemInfo({
        success: function(res) {
          console.log(res)
          that.setData({
            mobileModel:res.model,
            mobileePixelRatio:res.pixelRatio,
            windowWidth:res.windowWidth,
            windowHeight:res.windowHeight,
            language:res.language,
            version:res.version,
            system:res.system
          })
        }
      })
    }
})
