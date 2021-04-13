//test.js
var api = require('../../../../config/api');
let leftHeight = 0, rightHeight = 0; //分别定义左右两边的高度
let query;
Page({
 data: {
  pageNum:3,
  page:1,
    DanceList:[],
    title:'',
    roll:0,
    index:0,
    isLoadedAll: false,
		musicIndex: null,
		videoIndex: null,
		currentTabsIndex: 0,
    pageIndex: 1,
    indicatorDots: true,
    autoplay: true,
    interval: 3000,
    duration: 500
 },
 toDance:function(e){
  var id=e.currentTarget.idupStatus
  console.log(e)
  console.log(e.currentTarget.id)
  wx.navigateTo({
    url: '/pages/biz/datas/playDance/playDance?id='+id,
  })
},
 onLoad(e) {
   var that=this
   console.log(e)
   console.log(e.title)
   that.setData({
     title:e.title
   })
   var coursetype=that.data.title
  var pageNum=that.data.pageNum;
  wx.request({
    url: api.apiUrl+'ResourceServlet?method=getDance&coursetype='+coursetype+"&page=1"+"&pageNum="+pageNum,
    method: "GET",
    header: {
      'content-type': 'application/json'
    },
    success: function (res) {
      console.log(res)
      that.setData({
        DanceList: res.data
      });
    }
  })
 },
 onTabsItemTap: function (event) {
  var index = event.currentTarget.dataset['index'];
  this.setData({
    currentTabsIndex: index
  });
  //tab切换时停止音乐播放
  backgroundAudioManager.stop();

  //tab切换时停止视频播放
  var videoContextPrev = wx.createVideoContext('video' + this.data.videoIndex);
  videoContextPrev.stop();

  //将当前播放视频、音频的index设置为空
  this.setData({
    musicIndex: null,
    videoIndex: null,
  })
},
//展开
//原本没有upStatus这个字段，所以默认值为false
upDown(event) {
  var index = event.currentTarget.dataset['index'];
  this.data.DanceList[index].upStatus = !this.data.DanceList[index].upStatus;
  this.setData({
    DanceList: this.data.DanceList
  })
},
//播放音频
musicPlay(event) {
  var src = event.currentTarget.dataset['src'];
  var index = event.currentTarget.dataset['index'];
  this.setData({
    musicIndex: index,
    audioSrc: src
  });
  
  backgroundAudioManager.src = src;
  backgroundAudioManager.play()

},
//停止音频
musicPause(event) {
  this.setData({
    musicIndex: null
  });
  backgroundAudioManager.pause();
},
//播放视频
videoPlay(event) {
  var length = this.data.DanceList.length;
  var index = event.currentTarget.dataset['index'];

  if (!this.data.videoIndex) { // 没有播放时播放视频
    this.setData({
      videoIndex: index
    })
    var videoContext = wx.createVideoContext('video' + index)
    videoContext.play()
  } else {
    //停止正在播放的视频
    var videoContextPrev = wx.createVideoContext('video' + this.data.videoIndex)
    videoContextPrev.stop()
    //将点击视频进行播放
    this.setData({
      videoIndex: index
    })
    var videoContextCurrent = wx.createVideoContext('video' + index)
    videoContextCurrent.play()
  }
},
 onshow:function(){
  
 },
 
  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    
  },
 
  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    
  },
 
  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    wx.showNavigationBarLoading();
    var coursetype=that.data.title
    var that = this;
    that.setData({
      page:1
    })
    var pageNum=that.data.pageNum
    wx.request({
      url: api.apiUrl+'ResourceServlet?method=getDance&coursetype='+coursetype+"&page=1"+"&pageNum="+pageNum,
      method: "GET",
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        console.log(res)
        that.setData({
          Dancelist: res.data
        });
        // 隐藏导航栏加载框
        wx.hideNavigationBarLoading();
        // 停止下拉动作
        wx.stopPullDownRefresh();
      }
    })

  },
 
  /**
   * 页面上拉触底事件的处理函数
   */
/**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    console.log("页面上拉触底事件的处理函数")
    var that = this;
    // 显示加载图标
    wx.showLoading({
      title: '玩命加载中',
    })
    var coursetype=that.data.title
    var page1=that.data.page;
    var pageNum=that.data.pageNum
    // 页数+1
    page1 = page1 + 1;
    that.setData({
      page:page1
    })
    wx.request({
      url: api.apiUrl+'ResourceServlet?method=getDance&coursetype='+coursetype+"&page="+ page1+"&pageNum="+pageNum,
      method: "GET",
      // 请求头部
      header: {
        'content-type': 'application/text'
      },
      success: function (res) {
        // 回调函数
        const oldData = that.data.DanceList;
        console.log(oldData)
        that.setData({
           DanceList:oldData.concat(res.data)
        })
        // 隐藏加载框
        wx.hideLoading();
      }
    })
 
  },

/**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  },
})