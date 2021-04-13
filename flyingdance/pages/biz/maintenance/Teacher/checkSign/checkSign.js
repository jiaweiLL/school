var account;
var api = require('../../../../../config/api.js');
Page({
  data: {
    newsid:'',
    currentTabsIndex:0,
    StudentSign:[],
    StudentNoSign:[]
  },
  onTabsItemTap: function (event) {
    var index = event.currentTarget.dataset['index'];
    this.setData({
      currentTabsIndex: index
    });
  },
  onLoad: function (options) {
    var that = this
    var newsid=options.id;
    console.log("初始化")
    this.setData({
      newsid:newsid,
    });
   
  },
  onShow:function(){
    console.log("onshow")
    var that = this
    var teacher_id=wx.getStorageSync('user').id
    var newsid=that.data.newsid
    this.setData({
      newsid:newsid,
      slideButtons: [{
        text: '替签',
        data:''
      }],
    });
    wx.request({
      url: api.apiUrl+'NewsServlet?method=getcheckSign&newsid='+newsid,
      data: {
      },
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
         console.log(res)
         console.log(res.data)
         that.setData({
          StudentSign:res.data
         })
      },
      fail:function(res){
       console.log(res)
      }
    }),
    console.log("getNoSign")
    wx.request({
      url: api.apiUrl+'NewsServlet?method=getNoSign&newsid='+newsid+"&teacher_id="+teacher_id,
      data: {
      },
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
         console.log(res)
         console.log("获取未签到学生")
         that.setData({
          StudentNoSign:res.data
         })
      },
      fail:function(res){
       console.log(res)
      }
    })
  },
  slideButtonTap(e) {
    var that=this
    console.log(e)
    var name_id=e.currentTarget.id
    var teacher=wx.getStorageSync('user').name
    var teacher_id=wx.getStorageSync('user').id
    var school=wx.getStorageSync('user').school
    var newsid=that.data.newsid;
    wx.showModal({
      title: '提示',
      content: '确定要替签吗？',
      success: function (sm) {
        if (sm.confirm) {
          wx.request({
            url: api.apiUrl+'NewsServlet?method=forSign&newsid='+newsid+"&teacher="+teacher+"&teacher_id="+teacher_id+"&name_id="+name_id+"&school="+school,
            method: "GET",
            header: {
              'content-type': 'application/json'
            },
            success: function (res) {
              console.log(res)           
                wx.showToast({
                  title: '签到成功', 
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
    
  }
})
      