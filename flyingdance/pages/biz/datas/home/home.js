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
    list: [{
      title: '索引列表',
      img: 'https://image.weilanwl.com/color2.0/plugin/sylb2244.jpg',
      url: '/indexes/indexes'
  },
    {
      title: '微动画',
      img: 'https://image.weilanwl.com/color2.0/plugin/wdh2236.jpg',
      url: '/animation/animation'
    },
    {
      title: '全屏抽屉',
      img: 'https://image.weilanwl.com/color2.0/plugin/qpct2148.jpg',
      url: '/drawer/drawer'
    },
    {
      title: '垂直导航',
      img: 'https://image.weilanwl.com/color2.0/plugin/qpczdh2307.jpg',
      url: '/verticalnav/verticalnav'
    }
  ],
      elements: [{
        url:'../../../../../../../pages/biz/datas/Dance-list/Dance-list',
        title: '拉丁舞',
        name: 'layout',
        img: 'http://img.flyingdance.vip/ch1.jpg',
      },
      {
        url:'../../../../../../../pages/biz/datas/Dance-list/Dance-list',
        title: '爵士舞',
        name: 'background',
        img: 'http://img.flyingdance.vip/ch2.jpg',
      },
      {
        url:'../../../../../../../pages/biz/datas/Dance-list/Dance-list',
        title: '模特',
        name: 'text',
        img: 'http://img.flyingdance.vip/ch3.jpg',
      },
      {
        url:'../../../../../../../pages/biz/datas/Dance-list/Dance-list',
        title: '绘本舞',
        name: 'icon',
        img: 'http://img.flyingdance.vip/ch4.jpg',
      },
      {
        url:'../../../../../../../pages/biz/datas/Dance-list/Dance-list',
        title: '民族舞',
        name: 'button',
        img: 'http://q9ljk0rvz.bkt.clouddn.com/tmp/wxae4ce127bf8f7cc3.o6zAJs7q7HQ4XdkxvNEJ-NryzMb8.yrGsDIN2SIIP8411daa3302d07106760f62f325bbaa0.png',
      },
    ]
  },

  /**
   * 组件的方法列表
   */
  methods: {
    goPage:function(event){
      console.log(event)
      wx.navigateTo({
          url: event.currentTarget.dataset.url+'?title='+event.currentTarget.dataset.title,
          success: function(res) {  
              console.log(res)
          },
          fail:function(res){
            console.log(res)
          }
      })
    },
  }
})