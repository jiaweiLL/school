var app = getApp()
var api = require('../../../../../config/api.js');
Page({
    data: {
        id:'',
        head_id:'',
        head_name:'',
        name:'',
        class_name:'',
        sno:''
    },
    updata:function(options){
      var that=this
      var id=this.data.id
      var school=wx.getStorageSync('user').school
      var schoolmaster=wx.getStorageSync('user').name
      var schoolmaster_id=wx.getStorageSync('user').id
      var head_id=that.data.head_id
      var head_name=that.data.head_name
      var money=options.detail.value.money
      var class_hour=options.detail.value.class_hour
      var class_name=that.data.class_name
      var name=that.data.name
      wx.showModal({
        title: '提示',
        content: '确定要提交吗？',
        success: function (sm) {
          if (sm.confirm) {
            
            wx.request({
                url: api.apiUrl+'SchoolServlet?method=payupdateStudent&name='+name+"&id="+id+"&school="+school+"&schoolmaster="+schoolmaster+"&schoolmaster_id="+schoolmaster_id+"&head_id="+head_id+"&head_name="+head_name+"&money="+money+"&class_hour="+class_hour+"&class_name="+class_name,
                data: {
                },
                method: 'GET',
                header: {
                'content-type': 'application/json' // 默认值
                },
                success: function (res) {
                  console.log(res)
                  wx.navigateTo({
                    url: '../../../../../datas/msg/msg_success',
                  })              
              },
              fail:function(res){
                wx.navigateTo({
                  url: '../../../../../datas/msg/msg_warn',
                }) 
              }
            
            })
          }else if (sm.cancel) {
            console.log('用户点击取消')
          }
        }
      })
    },
    onLoad: function (options) {
        var that=this
        var id=options.id
        console.log(id)
        wx.request({
            url: api.apiUrl+'SysServlet?method=getStudentDate&id='+id,
            data: {
            },
            method: 'GET',
            header: {
            'content-type': 'application/json' // 默认值
            },
            success: function (res) {
                console.log(res)
                that.setData({
                    id:options.id,
                    head_id:res.data[0].head_id,
                    head_name:res.data[0].head_name,
                    class_name:res.data[0].class_name,
                    name:res.data[0].name,
                    sno:res.data[0].sno
                })
                
            },
            fail:function(res){
            console.log(res)
            }     
            
        })
        
  }
})
