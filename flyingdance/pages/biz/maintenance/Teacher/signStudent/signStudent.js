
var api = require('../../../../../config/api.js');
Page({
  data: {
    currentTabsIndex:0,
    sumlength:0,
    pageNum:5,
    pageSize:10,
    page:0,
    id:'',
    list:[]
  },
  onTabsItemTap: function (event) {
    var index = event.currentTarget.dataset['index'];
    this.setData({
      currentTabsIndex: index
    });
  },
  checkSign:function(e){
    var id=e.target.id
    console.log(id)
    wx.navigateTo({
      url: '../checkSign/checkSign?id='+id,
      success: function (res) {
        console.log(res)
      },
      fail:function(res){
       console.log(res)
      }      
    })
  },
  deleteSign:function(e){
    var id=e.target.id
    console.log(id)
    wx.showModal({
      title: '提示',
      content: '确定要删除吗？',
      success: function (sm) {
        if (sm.confirm) {
          wx.request({
            url: api.apiUrl+'NewsServlet?method=deleteSign&id='+id,
            method: "GET",
            header: {
              'content-type': 'application/json'
            },
            success: function (res) {
              console.log(res)
              wx.showToast({
                title: '删除成功', 
                icon: 'none',
                duration: 2000,
                success:function(res){
                  const pages = getCurrentPages()
                  const perpage = pages[pages.length - 1]
                  perpage.onShow()            
                }
              })
              
            }
          })
          } else if (sm.cancel) {
            console.log('用户点击取消')
          }
        }
      })
    
  },
  onLoad: function (options) {    
    
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
    var name_id=wx.getStorageSync('user').id;
    var pageNum=that.data.pageNum;
    wx.request({
      url: api.apiUrl+'NewsServlet?method=getAllSign&name_id='+name_id+"&page=1"+"&pageNum="+pageNum,
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
    var name_id=wx.getStorageSync('user').id;
    console.log("asd")
    var that = this;
    that.setData({
      page:1
    })
    var pageNum=that.data.pageNum
    wx.request({
      url: api.apiUrl+'NewsServlet?method=getAllSign&name_id='+name_id+"&page=1"+"&pageNum="+pageNum,
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
    var name_id=wx.getStorageSync('user').id
    var page1=that.data.page;
    var pageNum=that.data.pageNum
    // 页数+1
    page1 = page1 + 1;
    that.setData({
      page:page1
    })
    wx.request({
      url: api.apiUrl+'NewsServlet?method=getAllSign&name_id='+name_id+"&page="+ page1+"&pageNum="+pageNum,
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
      