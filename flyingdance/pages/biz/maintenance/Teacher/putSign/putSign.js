var app = getApp()
var dateTimePicker = require('../../../../../utils/dateTimePicker.js');
var api = require('../../../../../config/api.js');
function withData(param){
  return param < 10 ? '0' + param : '' + param;
};
Page({
    data: {
      date: '2018-10-01',
      time: '12:00',
      dateTimeArray: null,
      dateTime: null,
      dateTimeArray1: null,
      dateTime1: null,
      startYear: 2000,
      endYear: 2050,
      index: 0,
    },
    
    subSign:function(options){
        var name=wx.getStorageSync('user').name;
        var that = this
        var mon=withData(that.data.dateTime1[1]+1)
        var d=withData(that.data.dateTime1[2]+1)
        var starttime='20'+that.data.dateTime1[0]+mon+d+that.data.dateTime1[3]+that.data.dateTime1[4]
        var month=withData(that.data.dateTime[1]+1)
        var day=withData(that.data.dateTime[2]+1)
        var endtime='20'+that.data.dateTime[0]+month+day+that.data.dateTime[3]+that.data.dateTime[4]
        var form =options.detail.value
         for(var item in form){
        if(!form[item]){ //验证form表单是否填写完整
          wx.showToast({
          title: '请将信息填写完整',
          icon: 'none',
          duration: 2000
         })
         return;
         }
      }
      wx.showModal({
        title: '提示',
        content: '确定要发布吗？',
        success: function (sm) {
          if (sm.confirm) {
            var index=that.data.index
            var text1=options.detail.value.text1
            var name_id=wx.getStorageSync('user').id
            var sign_class=options.detail.value.sign_class
            wx.request({
                url: api.apiUrl+'NewsServlet?method=putSign&name='+name+"&text1="+text1+"&name_id="+name_id+"&sign_class="+sign_class+"&starttime="+starttime+"&endtime="+endtime,
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
            } else if (sm.cancel) {
              console.log('用户点击取消')
            }
          }
        })
        
    },
    changeDate(e){
      this.setData({ date:e.detail.value});
    },
    changeTime(e){
      this.setData({ time: e.detail.value });
    },
    changeDateTime(e){
      this.setData({ dateTime: e.detail.value });
    },
    changeDateTime1(e) {
      this.setData({ dateTime1: e.detail.value });
    },
    changeDateTimeColumn(e){
      var arr = this.data.dateTime, dateArr = this.data.dateTimeArray;
      arr[e.detail.column] = e.detail.value;
      dateArr[2] = dateTimePicker.getMonthDay(dateArr[0][arr[0]], dateArr[1][arr[1]]);
      var that=this
      that.setData({
        dateTimeArray: dateArr,
        dateTime: arr
      });
    },
    changeDateTimeColumn1(e) {
      var arr = this.data.dateTime1, dateArr = this.data.dateTimeArray1;
      arr[e.detail.column] = e.detail.value;
      dateArr[2] = dateTimePicker.getMonthDay(dateArr[0][arr[0]], dateArr[1][arr[1]]);
      var that=this
      that.setData({ 
        dateTimeArray1: dateArr,
        dateTime1: arr
      });
    },
    onLoad: function (options) {
        var obj = dateTimePicker.dateTimePicker(this.data.startYear, this.data.endYear);
        var obj1 = dateTimePicker.dateTimePicker(this.data.startYear, this.data.endYear);
        // 精确到分的处理，将数组的秒去掉
        var lastArray = obj1.dateTimeArray.pop();
        var lastTime = obj1.dateTime.pop();
        this.setData({
          dateTime: obj.dateTime,
          dateTimeArray: obj.dateTimeArray,
          dateTimeArray1: obj1.dateTimeArray,
          dateTime1: obj1.dateTime
        });
    },
    onshow:function(){
var obj = dateTimePicker.dateTimePicker(this.data.startYear, this.data.endYear);
        var obj1 = dateTimePicker.dateTimePicker(this.data.startYear, this.data.endYear);
        // 精确到分的处理，将数组的秒去掉
        var lastArray = obj1.dateTimeArray.pop();
        var lastTime = obj1.dateTime.pop();
        this.setData({
          dateTime: obj.dateTime,
          dateTimeArray: obj.dateTimeArray,
          dateTimeArray1: obj1.dateTimeArray,
          dateTime1: obj1.dateTime
        });
    }
})
