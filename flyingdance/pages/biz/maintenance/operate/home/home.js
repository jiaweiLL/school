var account;
var api = require('../../../../../config/api.js');
Page({
  mixins: [require('../../../../../assets/mixin/themeChanged')],
  data: {
    currentTabsIndex:0,
    allteacher:[],
    listData:[],
      inputShowed: false,
      inputVal: "",
      searchtext:'',
      issearch:0,
      searchrs:[]
  },
  onTabsItemTap: function (event) {
    var index = event.currentTarget.dataset['index'];
    this.setData({
      currentTabsIndex: index
    });
  },
  onLoad: function (options) {
    
  },
  onShow:function(){
    var that = this
    var head_id=wx.getStorageSync('user').id
    this.setData({
      slideButtons: [{
        text: '信息',
        src: '/page/weui/cell/icon_love.svg', // icon的路径
      },{
        text: '老师',
        extClass: 'test',
        src: '/page/weui/cell/icon_star.svg', // icon的路径
      }],
    });
    wx.request({
      url: api.apiUrl+'SysServlet?method=getAllschool',
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
          allteacher:res.data
         })
      },
      fail:function(res){
       console.log(res)
      }
    })
  },
  slideButtonTap(e) {
    console.log('slide button tap', e.detail.index)   
    var that=this
    var index=e.detail.index
    var teacher_id=e.currentTarget.id
    if(index==0) {
      wx.navigateTo({
        url: '../updataSchool/updataSchool?id='+teacher_id,
      })
    }else if(index==1){
      wx.navigateTo({
        url: '../ViewSchoolTea/ViewSchoolTea?id='+teacher_id,
      })
    }
  },
})