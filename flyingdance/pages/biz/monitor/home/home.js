
  function getRandomColor() {
    const rgb = []
    for (let i = 0; i < 3; ++i) {
      let color = Math.floor(Math.random() * 256).toString(16)
      color = color.length == 1 ? '0' + color : color
      rgb.push(color)
    }
    return '#' + rgb.join('')
  };
  var api = require('../../../../config/api.js');
  const backgroundAudioManager = wx.getBackgroundAudioManager();
  Component({
  
    /**
     * 组件的属性列表
     */
    options:{
      addGlobalClass: true,
    },
  
    /**
     * 组件的属性列表
     */
    properties: {
      
    },
  
    /**
     * 组件的初始数据
     */
    data:  {
      pageNum:2,
      pageSize:10,
      page:1,
      pageNumNotice:2,
      pageNotice:1,
      roll:0,
      isLoadedAll: false,
      musicIndex: null,
      videoIndex: null,
      RollPicture:[],
      currentTabsIndex: 0,
      currentIndex: 0,
      pageIndex: 1,
      loadnone:'',
      loadnoneNotice:'',
      indicatorDots: true,
      autoplay: true,
      interval: 3000,
      duration: 500,
      videoArray:[],
      audioArray:[],
    },
  
    /**
     * 组件的方法列表
     */
    methods: {
      isCard(e) {
        this.setData({
          isCard: e.detail.value
        })
      },
      tonoticeContent:function(e){
        var id=e.currentTarget.id
        console.log(e)
        console.log(e.currentTarget.id)
        wx.navigateTo({
          url: '/pages/biz/monitor/noticeContent/noticeContent?id='+id,
        })
      },
      loadmore:function(){
        var that=this
        wx.showLoading({
          title: '玩命加载中',
        })
        var page1=that.data.page
        var pageNum=that.data.pageNum
        // 页数+1
        page1 = page1 + 1;
        that.setData({
          page:page1
        })
        wx.request({
          url: api.apiUrl+'ResourceServlet?method=getNoticeVideoSome&page='+page1+"&pageNum="+pageNum,
          method: "GET",
          // 请求头部
          header: {
            'content-type': 'application/text'
          },
          success: function (res) {
            // 回调函数
            if(res.data==''){
                that.setData({
                loadnone:'已加载全部'
                })
            }
            console.log(res.data)
            const oldData = that.data.videoArray;
            that.setData({
              videoArray:oldData.concat(res.data)
            })
            // 隐藏加载框
            wx.hideLoading();
          }
        })
      },
      loadNotice:function(){
        var that=this
        wx.showLoading({
          title: '玩命加载中',
        })
        var pageNotice1=that.data.pageNotice
        var pageNumNotice1=that.data.pageNumNotice
        // 页数+1
        pageNotice1 = pageNotice1 + 1;
        that.setData({
          pageNotice:pageNotice1
        })
        wx.request({
          url: api.apiUrl+'ResourceServlet?method=getNoticeSome&page='+pageNotice1+"&pageNum="+pageNumNotice1,
          method: "GET",
          // 请求头部
          header: {
            'content-type': 'application/text'
          },
          success: function (res) {
            // 回调函数
            if(res.data==''){
                that.setData({
                  loadnoneNotice:'已加载全部'
                })
            }
            console.log(res.data)
            const oldData = that.data.audioArray;
            that.setData({
              audioArray:oldData.concat(res.data)
            })
            // 隐藏加载框
            wx.hideLoading();
          }
        })
      },
      handleChange: function (e) {
        this.setData({
          currentIndex: e.detail.current,
          bgColor: getRandomColor()
        })
      },
      onLoad: function (options) {
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
        this.data.videoArray[index].upStatus = !this.data.videoArray[index].upStatus;
        this.setData({
          videoArray: this.data.videoArray
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
        var length = this.data.videoArray.length;
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
    
    },
    lifetimes: {
      created() {
        
        console.log("在组件实例刚刚被创建时执行")
      },
      attached() { 
        var that=this
        wx.request({
          url: api.apiUrl+'ResourceServlet?method=getRollPicture',
          data: {
          },
          method: 'GET',
          header: {
          'content-type': 'application/json' // 默认值
          },
          success: function (res) {  
              that.setData({
                RollPicture:res.data
              })                     
           },
          fail:function(res){
          }        
          })
          var pageNum=that.data.pageNum
          var page=that.data.page
          wx.request({
            url: api.apiUrl+'ResourceServlet?method=getNoticeVideoSome&page='+page+"&pageNum="+pageNum,
            data: {
            },
            method: 'GET',
            header: {
            'content-type': 'application/json' // 默认值
            },
            success: function (res) {  
                console.log(res.data)
                that.setData({
                  videoArray:res.data
                })                     
             },
            fail:function(res){
            }        
            })  
          var pageNumNotice=that.data.pageNumNotice
          var pageNotice=that.data.pageNotice
            wx.request({
              url: api.apiUrl+'ResourceServlet?method=getNoticeSome&page='+pageNotice+"&pageNum="+pageNumNotice,
              data: {
              },
              method: 'GET',
              header: {
              'content-type': 'application/json' // 默认值
              },
              success: function (res) {  
                  console.log(res.data)
                  that.setData({
                    audioArray:res.data
                  })                     
               },
              fail:function(res){
              }        
              })  
        console.log("在组件实例进入页面节点树时执行")
      },
      ready() {
        
      },
      moved() {

        console.log("在组件实例被移动到节点树另一个位置时执行")
      },
      detached() {
        console.log("在组件实例被从页面节点树移除时执行")
      },
      error() {
        
        console.log("每当组件方法抛出错误时执行")
      },
      /*组件所在页面的生命周期 */
      pageLifetimes: {
        
        show: function () {
         
          // 页面被展示
          console.log("页面被展示")
        },
        hide: function () {
        
          // 页面被隐藏
          console.log("页面被隐藏")
        },
        resize: function (size) {
          // 页面尺寸变化
          console.log("页面尺寸变化")
        }
      }
    }
  })