Component({

  /**
   * 组件的属性列表
   */
  options:{
    addGlobalClass: true,
  },

  /**
   * 组件的属性列表
   */
  properties: {
    
  },

  /**
   * 组件的初始数据
   */
  data: {
    useraddress:'',
    errorCode:false,
    user:{},
    role:'',
    userInfo: {},
        list_student:[
          {
            list_tool:[
                {
                    img:"../../../../assets/images/money.png",
                    name:"老师动态",
                    url:"/pages/biz/mine/Student/student_dynamic/student_dynamic"
                    
                },
                {   
                    img:"../../../../assets/images/card.png",
                    name:"签到",
                    url:"/pages/biz/mine/Student/student_sign/student_sign"
                }
            ]
        },
            {
                list_tool:[
                    {
                        img:"../../../../assets/images/photo.png",
                        url:"/pages/biz/mine/Student/my_Sign/my_Sign",
                        name:"签到记录"
                    },
                    {
                        img:"../../../../assets/images/sc_2.png",
                        name:"课程信息",
                        url:"/pages/biz/mine/Student/my_class/my_class"
                    }
                ]
            },
            
            {
                list_tool:[
                  {
                    img:"../../../../assets/images/card.png",
                    name:"意见反馈",
                    url:"/pages/biz/mine/advise/advise"
                  },
                    {
                        img:"../../../../assets/images/setting.png",
                        name:"设置",
                        url:"/pages/biz/mine/Setup/Setup"
                    }
                ]
            },
        ],
        list_teacher:[
          {
              list_tool:[
                  {
                      img:"../../../../assets/images/card.png",
                      name:"意见反馈",
                      url:"/pages/biz/mine/advise/advise"
                  },
                  {
                      img:"../../../../assets/images/setting.png",
                      name:"设置",
                      url:"/pages/biz/mine/Setup/Setup"
                  }
              ]
          },
      ],
  },

  /**
   * 组件的方法列表
   */
  methods: {
    toPersonal:function(){
      console.log("个人中心")
      wx.navigateTo({
        url:'personal/personal',
        success: function (res) {
          console.log(res)
        },
        fail:function(res){
          console.log(res)
        }
     })
    },
    goPage1:function(event){
      console.log(event.currentTarget.dataset.log+event.currentTarget.dataset.url);
      wx.navigateTo({
          url: event.currentTarget.dataset.url,
          success: function(res) {  
              console.log(res)
          },
          fail:function(res){
            console.log(res)
          }
      })
    },
},
  lifetimes: {
    created() {
      console.log("在组件实例刚刚被创建时执行")
    },
    attached() { 
      
      console.log("在组件实例进入页面节点树时执行")
    },
    ready() {
      console.log(wx.getStorageSync('errorCode'))
      if(wx.getStorageSync('errorCode')=='0'){
          console.log(wx.getStorageSync('errorCode'))
          var app=getApp()
          var that = this
          var address=wx.getStorageSync("user").address
          //调用应用实例的方法获取全局数据
          that.setData({
              errorCode:true,
              userInfo:app.globalData.userInfo,
              useraddress:address,
              role:wx.getStorageSync('user').role
          }) 
      }else{
        console.log(wx.getStorageSync('errorCode'))
        var that = this
        that.setData({
          errorCode:false
        })
      }
      console.log("在组件在视图层布局完成后执行")
    },
    moved() {
      console.log("在组件实例被移动到节点树另一个位置时执行")
    },
    detached() {
      console.log("在组件实例被从页面节点树移除时执行")
    },
    error() {
      console.log("每当组件方法抛出错误时执行")
    },
    /*组件所在页面的生命周期 */
    pageLifetimes: {
      show: function () {
        // 页面被展示
        console.log("页面被展示")
      },
      hide: function () {
        // 页面被隐藏
        console.log("页面被隐藏")
      },
      resize: function (size) {
        // 页面尺寸变化
        console.log("页面尺寸变化")
      }
    }
  }

  
})