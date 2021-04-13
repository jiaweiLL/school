var account;
var api = require('../../../../../config/api.js');
Page({
  mixins: [require('../../../../../assets/mixin/themeChanged')],
  data: {
    currentTabsIndex:0,
    allteacher:[],
    listData:[],
    head_id:'',
      inputShowed: false,
      inputVal: "",
      searchtext:'',
      id:'',
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
    var that = this
    var head_id=options.id
    this.setData({
      head_id:options.id,
    });
  },
  onShow:function(){
    var that = this
    var head_id=that.data.head_id
    this.setData({
      slideButtons: [{
        text: '信息',
        src: '/page/weui/cell/icon_love.svg', // icon的路径
      },{
        text: '学生',
        extClass: 'test',
        src: '/page/weui/cell/icon_star.svg', // icon的路径
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
        url: '../../school/updataTeacher/updataTeacher?id='+teacher_id,
      })
    }else if(index==1){
      wx.navigateTo({
        url: '../../school/viewTeaStudent/viewTeaStudent?id='+teacher_id,
      })
    }
  },
  showInput: function () {
    this.setData({
        inputShowed: true
    });

},
hideInput: function () {
    this.setData({
        inputVal: "",
        inputShowed: false
    });
},
clearInput: function () {
    this.setData({
        inputVal: ""
    });
},
inputTyping: function (e) { 
    var that=this
    this.setData({
        inputVal: e.detail.value
    });
    var head_id = that.data.head_id
    var searchtext=e.detail.value
    wx.request({
      url: api.apiUrl+'SysServlet?method=search&head_id='+head_id+"&searchtext="+e.detail.value,
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
           searchrs:res.data
         })
      },
      fail:function(res){
       console.log(res)
      }
    })
}
})
      