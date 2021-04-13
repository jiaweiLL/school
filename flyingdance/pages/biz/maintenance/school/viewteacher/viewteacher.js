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
        text: '编辑',
        src: '/page/weui/cell/icon_love.svg', // icon的路径
      },{
        text: '查看学生',
        extClass: 'test',
        src: '/page/weui/cell/icon_star.svg', // icon的路径
      },{
        type: 'warn',
        text: '删除',
        extClass: 'test',
          src: '/page/weui/cell/icon_del.svg', // icon的路径
      }],
    });
    wx.request({
      url: api.apiUrl+'SysServlet?method=getAllStudent&head_id='+head_id,
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
        url: '../updataTeacher/updataTeacher?id='+teacher_id,
      })
    }else if(index==1){
      wx.navigateTo({
        url: '../viewTeaStudent/viewTeaStudent?id='+teacher_id,
      })
    }else if(index==2){
      console.log("shanchu")
    }
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
topersonal:function(e){
  var id=e.target.id
  console.log(id)
  wx.redirectTo({
    url: '../schupdataStudent/schupdataStudent?id='+id,
  })
},
inputTyping: function (e) { 
    var that=this
    this.setData({
        inputVal: e.detail.value
    });
    var schoolmaster_id = wx.getStorageSync('user').id
    var searchtext=e.detail.value
    wx.request({
      url: api.apiUrl+'SchoolServlet?method=schoolmasterSearch&schoolmaster_id='+schoolmaster_id+"&searchtext="+searchtext,
      data: {
      },
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
         that.setData({
          issearch:1,
          searchrs:res.data
         })
      },
      fail:function(res){
       console.log(res)
      }
    })  
}
})
      