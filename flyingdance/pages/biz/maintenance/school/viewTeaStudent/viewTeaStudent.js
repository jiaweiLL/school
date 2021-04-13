var sear={}
var api = require('../../../../../config/api.js');
Page({
  data: {
      listData:[],
      inputShowed: false,
      inputVal: "",
      searchtext:'',
      issearch:0,
      searchrs:[],
      teacher_id:''
  },
  topersonal:function(e){
    var id=e.target.id
    console.log(id)
    wx.redirectTo({
      url: '../schupdataStudent/schupdataStudent?id='+id,
    })
  },
  onLoad: function (options) {
   var that=this
   var head_id=options.id
   that.setData({
     teacher_id:head_id
   })
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
          listData:res.data
        })
     },
     fail:function(res){
      console.log(res)
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
    var head_id = that.data.teacher_id;
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
      