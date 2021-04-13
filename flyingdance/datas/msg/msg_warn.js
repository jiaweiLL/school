Page({
  mixins: [require('../../assets/mixin/themeChanged')],
  data: {
    url:'',
  },
  gopage:function(event){
    wx.navigateBack({
      delta: 2
    })
    // wx.navigateTo({
    //     url: event.currentTarget.dataset.url,
    //     success: function(res) {  
    //         console.log(res)
    //     },
    //     fail:function(res){
    //       console.log(res)
    //     }
    // })
  },
  onLoad: function (options) {
    // var that=this
    // console.log(options.url)
    // that.setData({
    //   url=options.url
    // })
  }
});