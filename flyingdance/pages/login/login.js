var app=getApp()
var api = require('../../config/api');
Page({
  data: {
    sno: '',
    password: '',
    result:'',
    userInfo: {},
    loadProgress:0
  },
  onLoad: function () {
    var that = this
    wx.clearStorage()
    wx.clearStorageSync()
  },
  loadProgress(){
    this.setData({
      loadProgress: this.data.loadProgress+3
    })
    if (this.data.loadProgress<100){
      setTimeout(() => {
        this.loadProgress();
      }, 100)
    }else{
      this.setData({
        loadProgress: 0
      })
    }
  },
  // 获取输入账号
  phoneInput: function (e) {
    this.setData({
      sno: e.detail.value
    })
  },
  // 获取输入密码
  passwordInput: function (e) {
    this.setData({
      password: e.detail.value
    })
  },
  bindGetUserInfo: function (e) {
    if (e.detail.userInfo) {
     //用户按了允许授权按钮
     var that = this;
     //插入登录的用户的相关信息到数据库
     //授权成功后，跳转进入小程序首页
      this.logined();
    } else {
     //用户按了拒绝按钮
     wx.showModal({
      title:'警告',
      content:'您点击了拒绝授权，将无法进入小程序，请授权之后再进入!!!',
      showCancel:false,
      confirmText:'返回授权',
      success:function(res){
       if (res.confirm) {
        console.log('用户点击了“返回授权”')
       }
      }
     })
    }
   },
   //获取用户信息接口
   queryUsreInfo: function () {
    wx.request({
     url: getApp().globalData.urlPath + 'hstc_interface/queryByOpenid',
     data: {
      openid: getApp().globalData.openid
     },
     header: {
      'content-type': 'application/json'
     },
     success: function (res) {
      console.log(res.data);
      getApp().globalData.userInfo = res.data;
     }
    });
   },
  logined:function(){
    var that = this ;
    wx.request({
      url: api.apiUrl+'SysServlet?method=logincheck',
      data: {
        sno:that.data.sno,//参数
        password: that.data.password,//参数
      },
      method: 'GET',//方法为get
      header: {
        'content-type': 'application/json' //默认值
      },
      success: function (res) {//成功
        console.log(res)
        console.log("收到的数据：" + res.data[0].errorCode);//打印收到的结果res里的内容
        console.log("收到的数据：" + res.data[0].errorMsg);
        if(res.data[0].errorCode==1){
          wx.showToast({
            title: '用户名或密码错误', 
            icon: 'none',
            duration: 2000
          })
        }
        else{
          wx.showToast({
            title: '登录成功',
            icon: 'success',
            duration: 2000,
            success:function(){
              wx.showToast({
                title: '登录成功',
                icon: 'success',
                duration: 2000,
              })
              wx.setStorageSync("errorCode",0)
              wx.setStorageSync("user",res.data[0].result)
              wx.setStorageSync("role",res.data[0].result.role)
              wx.reLaunch({
                url: '../sys/index/index',
                fail(ex){
                  console.log(ex)//fail can not redirectTo a tabbar page"
                  wx.switchTab({
                    url: '/pages/user/user',
                    success(res){
                      let page = getCurrentPages().pop();
                      if(page == undefined || page == null){
                            return
                      }
                      page.onLoad();
                }
                  })
                }
              })
            }
          })
          }
        },
      
      
      fail: function (res) {
        console.log(res);
      }
    })
  },
})
