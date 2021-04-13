var qiniu = require('../../../../../utils/qiniuUploader.js');
var api = require('../../../../../config/api.js');
Page({
  data: {
        imgbox: [],
        fileIDs: [],
      files:[],
      uploadToken: '',
      domain: '',
      picUrls: [],
      strUrl:''
  },
  upimg:function(){
    var that=this
    var strUrl=that.data.strUrl
    var putid=wx.getStorageSync('user').id
    var putname=wx.getStorageSync('user').name
    wx.request({
        url: api.apiUrl+'ResourceServlet?method=updateRollPicture&strUrl='+strUrl+"&putid="+putid+"&putname="+putname,
        data: {
        },
        method: 'GET',
        header: {
        'content-type': 'application/json' // 默认值
        },
        success: function (res) {   
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
  },
  onLoad() {
    this.setData({
        selectFile: this.selectFile.bind(this),
        uplaodFile: this.uplaodFile.bind(this)
    })
    this.getQiniuToken();
        // 载入时就去取token
  },
  chooseImage: function (e) {
    var that = this;
    wx.chooseImage({
        count: 1,
        sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
        sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
        success: function (res) {
           // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
            that.setData({
                files: that.data.files.concat(res.tempFilePaths)
            });
            
        }
    })
    },
    selectFile(files) {
    console.log('files', files)
    // 返回false可以阻止某次文件上传
    },
    uploadError(e) {
        console.log('upload error', e.detail)
    },
    uploadSuccess(e) {
        console.log('upload success', e.detail)
    },
    previewImage: function(e){
        wx.previewImage({
            current: e.currentTarget.id, // 当前显示图片的http链接
            urls: this.data.files // 需要预览的图片http链接列表
        })
    },
    uplaodFile(files) {
        var that=this
        var tempFilePaths = files.tempFilePaths;
        var object = {};
        // 文件上传的函数，返回一个promise
        for (var i = 0; i < tempFilePaths.length; i++) {
            var filePath = files.tempFilePaths[i];
            qiniu.upload(filePath, (_res) => {
                // 七牛提供的方法，引入那个文件的作用
                var url = 'http://'+_res.imageURL;
                that.data.picUrls.push(url)
                var strUrl=that.data.strUrl+url+" ";
                that.setData({
                    strUrl:strUrl
                })
                object['urls'] = that.data.picUrls;
                if(that.data.picUrls.length==tempFilePaths.length){                  
                    return new Promise((resolve, reject) => {
                        resolve(object)
                        setTimeout(() => {
                            reject('some error')
                        }, 3000)
                    })
                }
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
//   chooseImage: function (e) {
//     var that = this;
//     wx.chooseImage({
//     // 微信提供的选择图片方法
//         count: 1,
//         // 上传数的限制，用手机上传选择相册时，会知道这个count的作用
//         sizeType: ['original', 'compressed'],
//         sourceType: ['album', 'camera'],
//         success: function (res) {
//             console.log(res)
//             console.log(res.tempFilePaths.length)
//             if(res.tempFilePaths.length>1){
//                 wx.showToast({
//                   title: '只能选一张',
//                 })
//             }
//             var filePath = res.tempFilePaths[0];
//             qiniu.upload(filePath, (_res) => {
//             // 七牛提供的方法，引入那个文件的作用
//                 var url = 'http://'+_res.imageURL;
//                 console.log(url)
//                 that.data.picUrls.push(url)
//                 that.setData({
//                     picUrls: that.data.picUrls
//                 });
//                 console.log(that.data.picUrls)
//             }, (error) => {
//                 console.log('error: ' + error);
//                 // 这个很好用，调试时可以看七牛返回的错误
//             }, 
//             {
//                 region: 'SCN',
//                 domain: that.data.domain,
//                 // getQiniuToken从服务器得来的domain
//                 uptoken: that.data.uploadToken, 
//                 // getQiniuToken从服务器得来的token
//             });
//             for (var index in res.tempFilePaths) {
//                 // 这里写了个循环，这样可以选择多张图一起上传
               
//             }
//         }
//     })
//   },
});