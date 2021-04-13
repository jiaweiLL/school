var api = require('../../../../../config/api.js');
Page({
  data: {
    currentTabsIndex:0,
    id:'',
    sumlength:0,
    pageNum:3,
    pageSize:10,
    page:0,
    list:[],
    signbtn:'点击签到'
  },
  onTabsItemTap: function (event) {
    var index = event.currentTarget.dataset['index'];
    this.setData({
      currentTabsIndex: index
    });
  },
  toSign:function(e){
    var that=this;
    var name=wx.getStorageSync('user').name;
    var newsid=e.target.id;
    var head_name=wx.getStorageSync('user').head_name;
    var head_id=wx.getStorageSync('user').head_id;
    var name_id=wx.getStorageSync('user').id;
    var class_name=wx.getStorageSync('user').class_name
    var school=wx.getStorageSync('user').school
    wx.request({
      url: api.apiUrl+'StudentServlet?method=toSign&newsid='+newsid+"&name="+name+"&head_name="+head_name+"&head_id="+head_id+"&name_id="+name_id+"&class_name="+class_name+"&school="+school,
      method: "GET",
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        console.log(res.data)
        if(res.data==2){
          wx.showToast({
            title: '您早已签到（：', 
            icon: 'none',
            duration: 2000,
          })
        }else if(res.data==1){
          wx.showToast({
            title: '签到成功', 
            icon: 'none',
            duration: 2000,
          })
        }else if(res.data==0){
          wx.showToast({
            title: '未到签到时间）：', 
            icon: 'none',
            duration: 2000,
          })
        }else if(res.data==-1){
          wx.showToast({
            title: '已过签到时间）：', 
            icon: 'none',
            duration: 2000,
          })
        }
      }
       
    })
  },
  onLoad: function (options) {   
    var that=this
    var name=wx.getStorageSync('user').name;
    var head_id=wx.getStorageSync('user').head_id;
    var sign_class=wx.getStorageSync('user').class_name
    var head_name=wx.getStorageSync('user').head_name;
    var pageNum=that.data.pageNum;
    that.setData({
      id:wx.getStorageSync('user').id
    })
    wx.request({
      url: api.apiUrl+'StudentServlet?method=getStuAllSign&head_id='+head_id+"&sign_class="+sign_class+"&page=1"+"&pageNum="+pageNum,
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
    var head_id=wx.getStorageSync('user').head_id;
    var sign_class=wx.getStorageSync('user').class_name
    var that = this;
    that.setData({
      page:1
    })
    var pageNum=that.data.pageNum
    wx.request({
      url: api.apiUrl+'StudentServlet?method=getStuAllSign&head_id='+head_id+"&sign_class="+sign_class+"&page=1"+"&pageNum="+pageNum,
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
    var head_id=wx.getStorageSync('user').head_id
    var sign_class=wx.getStorageSync('user').class_name
    var page1=that.data.page;
    var pageNum=that.data.pageNum
    // 页数+1
    page1 = page1 + 1;
    that.setData({
      page:page1
    })
    wx.request({
      url: api.apiUrl+'StudentServlet?method=getStuAllSign&head_id='+head_id+"&sign_class="+sign_class+"&page="+ page1+"&pageNum="+pageNum,
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
      