var qiniu = require('../../../../../utils/qiniuUploader.js');
var api = require('../../../../../config/api.js');
Page({
  data: {
    uploadToken: '',
    domain: '',
    picUrls: [],
    src: '',
    picSrc:'',
    videoSrc:''
  },
  sub:function(options){
    wx.showModal({
      title: '提示',
      content: '确定添加吗？',
      success: function (sm) {
        if (sm.confirm) {
          var that=this
          var picSrc=that.data.picSrc
          var videoSrc=that.data.videoSrc
          var putid=wx.getStorageSync('user').id
          var putname=wx.getStorageSync('user').name
          var title=options.detail.value.title
          var content=options.detail.value.content
        wx.request({
            url: api.apiUrl+'ResourceServlet?method=addNoticeVideo&picSrc='+picSrc+"&putid="+putid+"&putname="+putname+"&videoSrc="+videoSrc+"&title="+title+"&content="+content,
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
    onLoad:function(){
      this.getQiniuToken();  
    },
    getQiniuToken: function () {
      let that = this;
      let url = "https://www.flyingdance.vip/bananana/TokenServlet"
      // 服务器的api地址，就是访问上面写的服务端代码里面的函数，当然具体地址：http://localhost:8360/api/example/ 这个要看你电脑的本地的，不要看我是这样，直接抄过去，哈哈
      wx.request({
          url: url,
          success: function (res) {
            console.log(res)
              //都ok的话，data就得到了数据
              let token = res.data.uptoken;
              let domain = 'q9ljk0rvz.bkt.clouddn.com/';
              that.setData({
                  uploadToken: token,
                  domain: domain
              })
          }
      })

    },
    chooseImage: function (e) {
      var that = this;
      wx.chooseImage({
      // 微信提供的选择图片方法
          count: 4,
          // 上传数的限制，用手机上传选择相册时，会知道这个count的作用
          sizeType: ['original', 'compressed'],
          sourceType: ['album', 'camera'],
          success: function (res) {
              for (var index in res.tempFilePaths) {
                  // 这里写了个循环，这样可以选择多张图一起上传
                  var filePath = res.tempFilePaths[index];
                  qiniu.upload(filePath, (_res) => {
                  // 七牛提供的方法，引入那个文件的作用
                      var url = 'http://'+_res.imageURL;
                      that.data.picUrls.push(url)
                      var picSrc=that.data.picSrc+url+" ";
                      that.setData({
                          picSrc:picSrc,
                          picUrls: that.data.picUrls
                      });
                  }, (error) => {
                      console.log('error: ' + error);
                      // 这个很好用，调试时可以看七牛返回的错误
                  }, 
                  {
                      region: 'SCN',
                      domain: that.data.domain,
                      // getQiniuToken从服务器得来的domain
                      uptoken: that.data.uploadToken, 
                      // getQiniuToken从服务器得来的token
                  });
              }
          }
      })
  },
  //选择视频
  chooseVideo: function() {
    var that = this
    wx.chooseVideo({
      success: function(res) {
        that.setData({
          src: res.tempFilePath,
        })
                   var filePath = that.data.src
                  qiniu.upload(filePath, (_res) => {
                  // 七牛提供的方法，引入那个文件的作用
                      var url = 'http://'+_res.imageURL;
                      that.setData({
                        videoSrc:url
                      })
                  }, (error) => {
                      console.log('error: ' + error);
                      // 这个很好用，调试时可以看七牛返回的错误
                  }, 
                  {
                      region: 'SCN',
                      domain: that.data.domain,
                      // getQiniuToken从服务器得来的domain
                      uptoken: that.data.uploadToken, 
                      // getQiniuToken从服务器得来的token
                  });
      }
    })
  }
    
})
