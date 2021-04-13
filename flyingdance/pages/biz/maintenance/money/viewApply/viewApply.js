
var api = require('../../../../../config/api.js');
Page({
  data: {
    sumlength:0,
    pageNum:10,
    pageSize:10,
    inputShowed: false,
      inputVal: "",
      searchtext:'',
      school:'',
      issearch:0,
      searchrs:[],
    page:1,
    list:[]
  },
  topersonal:function(e){
    var aid=e.currentTarget.id
    wx.redirectTo({
      url: '../applyperson/applyperson?aid='+aid,
    })
  },
  onTabsItemTap: function (event) {
    var index = event.currentTarget.dataset['index'];
    this.setData({
      currentTabsIndex: index
    });
  },
  onLoad: function (options) {    
    var that=this
    var pageNum=that.data.pageNum;
    console.log(that.data.school)
    wx.request({
      url: api.apiUrl+'MoneyServlet?method=getApplySome&page=1'+"&pageNum="+pageNum+"&school="+wx.getStorageSync('user').school,
      method: "GET",
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        console.log(res)
        that.setData({
          list: res.data
        });
      }
    })
  },

 /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    
  },
 
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var that=this
    var pageNum=that.data.pageNum;
    console.log(that.data.school)
    wx.request({
      url: api.apiUrl+'MoneyServlet?method=getApplySome&page=1'+"&pageNum="+pageNum+"&school="+wx.getStorageSync('user').school,
      method: "GET",
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        console.log(res)
        that.setData({
          list: res.data
        });
      }
    })
  },
  showInput: function () {
    console.log("show")
    this.setData({
        inputShowed: true
    });

},
hideInput: function () {
    console.log("hide")
    this.setData({
        inputVal: "",
        inputShowed: false
    });
},
clearInput: function () {
    console.log("cl")
    this.setData({
        inputVal: ""
    });
},
inputTyping: function (e) { 
    var that=this
    this.setData({
        inputVal: e.detail.value
    });
    wx.request({
      url: api.apiUrl+'MoneyServlet?method=searchApply&searchtext='+e.detail.value+"&school="+wx.getStorageSync('user').school,
      data: {
      },
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
         console.log(res)
         console.log(res.data)
         if(res.data[0]==1){
           searchrs:0
         }else{
          that.setData({
            issearch:1,
            searchrs:res.data
          })
         }
         
      },
      fail:function(res){
       console.log(res)
      }
    })
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
    var that = this;
    that.setData({
      page:1
    })
    var pageNum=that.data.pageNum
    wx.request({
      url: api.apiUrl+'MoneyServlet?method=getApplySome&page=1'+"&pageNum="+pageNum+"&school="+wx.getStorageSync('user').school,
      method: "GET",
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        console.log(res)
        that.setData({
          list: res.data
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
    var page1=that.data.page;
    var pageNum=that.data.pageNum
    // 页数+1
    page1 = page1 + 1;
    that.setData({
      page:page1
    })
    wx.request({
      url: api.apiUrl+'MoneyServlet?method=getApplySome&page='+ page1+"&pageNum="+pageNum+"&school="+that.data.school,
      method: "GET",
      // 请求头部
      header: {
        'content-type': 'application/text'
      },
      success: function (res) {
        // 回调函数
        var list = that.data.list;
        const oldData = that.data.list;
        console.log(list)
        console.log(oldData)
        that.setData({
           list:oldData.concat(res.data)
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
      