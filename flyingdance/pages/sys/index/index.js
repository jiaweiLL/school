// pages/_index/_index.js
var menus  = require('../../../datas/js/menus');
var api = require('../../../config/api');
Page({
  /**
   * 页面的初始数据
   */
  data: {
    /* 声明权限数据 */
    roleId: "",
    /* 声明跳转Target */
    PageCur: "datas",
    /* 声明菜单数据 */
    menus: {},
    /* 扫码数据 */
    scanRs:{}
  },

  /* ColorUI页面跳转方式 */
  NavChange(e) {
    var cur = e.currentTarget.dataset.cur;
    if(cur){
      this.setData({
        PageCur: cur,
        "menus.activeUrl": cur
      })
    }
  },
  /* 自定义绑定事件 */
  login: function (){
    wx.navigateTo({
      url: '../../login/login',
    })
  },
  getUserInfo: function(e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    /* 
      获取角色信息
      ...
    */

    options.roleId = wx.getStorageSync('role');
    /* roleId 1:学生*/
    if(options.roleId == 1){
      this.setData({
        roleId: options.roleId,
        menus: menus.userMenuData
      })
      /* roleId 1:老师*/
    } else if(options.roleId == 2){
      this.setData({
        roleId: options.roleId,
        menus: menus.masterMenuData
      })
    }else{
      this.setData({
        roleId: options.roleId,
        menus: menus.agentMenuData
      })
    }
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

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})