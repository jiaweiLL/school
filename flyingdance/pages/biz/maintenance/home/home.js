Component({
  
  /**
   * 组件的属性列表
   */
  options:{
    addGlobalClass: true,
  },

  /**
   * 组件的初始数据
   */
  data: {
    useraddress:'',
    errorCode:false,
    user:{},
    role:'',
    gridCol:4,
    iconList: [{
      title: '教师',
      name: 'teacher',
      icon: 'cardboardfill',
      color: 'red',
      badge: 0,
    }, {
      title: '人事部',
      name: 'personnel',
      icon: 'friendaddfill',
      color: 'orange',
      badge: 0,
    }, {
      title: '财务部',
      name: 'money',
      icon: 'moneybagfill',
      color: 'yellow',
      badge: 0,
    }, {
      title: '教学部',
      name: 'study',
      icon: 'noticefill',
      color: 'olive',
      badge: 0,
    }, {
      title: '校区',
      name: 'school',
      icon: 'upstagefill',
      color: 'cyan',
      badge: 0,
    }, {
      title: '运营部',
      name: 'operate',
      icon: 'explore',
      color: 'blue',
      badge: 0,
    }, {
      title: '市场部',
      name: 'market',
      icon: 'shopfill',
      color: 'purple',
      badge: 0,
    }, {
      icon: 'questionfill',
      color: 'mauve',
      badge: 0,
      name: '帮助'
    }, {
      icon: 'commandfill',
      color: 'purple',
      badge: 0,
      name: '问答'
    }, {
      icon: 'brandfill',
      color: 'mauve',
      badge: 0,
      name: '版权'
    }],
   
  },

  /**
   * 组件的方法列表
   */
  methods: {
    showModal(e) {
      this.setData({
        modalName: e.currentTarget.dataset.target
      })
    },
    hideModal(e) {
      this.setData({
        modalName: null
      })
    },
    gridchange: function (e) {
      this.setData({
        gridCol: e.detail.value
      });
    },
    gridswitch: function (e) {
      this.setData({
        gridBorder: e.detail.value
      });
    },
    ListTouchEnd(e) {
      if (this.data.ListTouchDirection =='left'){
        this.setData({
          modalName: e.currentTarget.dataset.target
        })
      } else {
        this.setData({
          modalName: null
        })
      }
      this.setData({
        ListTouchDirection: null
      })
    },
    goPage1:function(event){
      console.log(event.currentTarget.id)
      var power=wx.getStorageSync('user').power.toString()
      console.log(power)
      var powerpeople=event.currentTarget.id
      if(powerpeople=="teacher"){
        if(power.indexOf('1')==-1){
          wx.showToast({
            title: '没有权限',
            icon: 'warn',
            duration: 2000,
          })       
        }else{
          wx.navigateTo({
            url:"/pages/biz/maintenance/Teacher/home/home",
            success: function(res) {  
                console.log(res)
            },
            fail:function(res){
              console.log(res)
            }
          })
        }
      }else if(powerpeople=="school"){      
        console.log(power)
        if(power.indexOf("2")==-1){
          wx.showToast({
            title: '没有权限',
            icon: 'success_no_circle',
            duration: 2000,
            success: function(res) {  
              wx.switchTab({
                url: '../home/home',  
              })
            },
            fail:function(res){
              console.log(res)
            }
          })
          
        }else{
          wx.navigateTo({
            url:"/pages/biz/maintenance/school/home/home",
            success: function(res) {  
                console.log(res)
            },
            fail:function(res){
              console.log(res)
            }
          })
        }
      }else if(powerpeople=="personnel"){
        if(power.indexOf("3")>=0){
          wx.navigateTo({
            url: "/pages/biz/maintenance/Personnel/home/home",
            success: function(res) {  
                console.log(res)
            },
            fail:function(res){
              console.log(res)
            }
          })
        }else{       
          wx.showToast({
            title: '没有权限',
            icon: 'success_no_circle',
            duration: 2000,
          })
        }
      }else if(powerpeople=="operate"){
        if(power.indexOf("7")>=0){
          wx.navigateTo({
            url: "/pages/biz/maintenance/operate/home/home",
            success: function(res) {  
                console.log(res)
            },
            fail:function(res){
              console.log(res)
            }
          })
        }else{       
          wx.showToast({
            title: '没有权限',
            icon: 'success_no_circle',
            duration: 2000,
          })
        }
      }else if(powerpeople=="market"){
        if(power.indexOf("5")>=0){
          wx.navigateTo({
            url: "/pages/biz/maintenance/Market/home/home",
            success: function(res) {  
                console.log(res)
            },
            fail:function(res){
              console.log(res)
            }
          })
        }else{       
          wx.showToast({
            title: '没有权限',
            icon: 'success_no_circle',
            duration: 2000,
          })
        }
      }else if(powerpeople=="money"){
        if(power.indexOf("4")>=0){
          wx.navigateTo({
            url: "/pages/biz/maintenance/money/home/home",
            success: function(res) {  
                console.log(res)
            },
            fail:function(res){
              console.log(res)
            }
          })
        }else{       
          wx.showToast({
            title: '没有权限',
            icon: 'success_no_circle',
            duration: 2000,
          })
        }
      }else if(powerpeople=="study"){
        if(power.indexOf("6")>=0){
          wx.navigateTo({
            url: "/pages/biz/maintenance/education/home/home",
            success: function(res) {  
                console.log(res)
            },
            fail:function(res){
              console.log(res)
            }
          })
        }else{       
          wx.showToast({
            title: '没有权限',
            icon: 'success_no_circle',
            duration: 2000,
          })
        }
      }else{           
        wx.showToast({
          title: '没有权限',
          icon: 'success_no_circle',
          duration: 2000,
        })
      }
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