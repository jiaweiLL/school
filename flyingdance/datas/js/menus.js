/* 背景颜色一览：
red:嫣红  orange:桔橙 yellow:明黄 olive:橄榄  green:森绿；
cyan:天青  blue:海蓝  purple:姹紫  mauve:木槿 pink:桃粉；
brown:棕褐 grey:玄灰  gray:草灰  black:墨黑 white:雅白 */

var agentMenus = {
  activeUrl: 'datas',
  list:[{
    currentUrl:"datas",
    unCheckImgUrl:"/assets/images/tabbar/videogrey.png",
    checkedImgUrl:"/assets/images/tabbar/videopink.png",
    btnType: 0,
    title:"视频"
  },{
    bindTap: "login",
    btnTitleTextColor: "",
    btnImgTextColor: "",
    btnBgColor: "pink",
    btnType: 1,
    title:"登陆"
  },{
    currentUrl:"monitor",
    unCheckImgUrl:"/assets/images/tabbar/noticegrey.png",
    checkedImgUrl:"/assets/images/tabbar/noticepink.png",
    btnType: 0,
    title:"公告"
  }]
}
var userMenus = {
  activeUrl: 'datas',
  list:[{
    currentUrl:"datas",
    unCheckImgUrl:"/assets/images/tabbar/videogrey.png",
    checkedImgUrl:"/assets/images/tabbar/videopink.png",
    btnType: 0,
    title:"视频"
  },{
    currentUrl:"monitor",
    unCheckImgUrl:"/assets/images/tabbar/noticegrey.png",
    checkedImgUrl:"/assets/images/tabbar/noticepink.png",
    btnType: 0,
    title:"公告"
  },{
    bindTap: "checklogin",
    currentUrl:"mine",
    unCheckImgUrl:"/assets/images/tabbar/mygrey.png",
    checkedImgUrl:"/assets/images/tabbar/mypink.png",
    btnType: 0,
    title:"我的"
  }]
}
var masterMenus = {
  activeUrl: 'datas',
  list:[{
    currentUrl:"datas",
    unCheckImgUrl:"/assets/images/tabbar/videogrey.png",
    checkedImgUrl:"/assets/images/tabbar/videopink.png",
    btnType: 0,
    title:"视频"
  },{
    currentUrl:"maintenance",
    unCheckImgUrl:"/assets/images/tabbar/powergrey.png",
    checkedImgUrl:"/assets/images/tabbar/powerpink.png",
    btnType: 0,
    title:"功能"
  },{
    currentUrl:"monitor",
    unCheckImgUrl:"/assets/images/tabbar/noticegrey.png",
    checkedImgUrl:"/assets/images/tabbar/noticepink.png",
    btnType: 0,
    title:"公告"
  },{
    bindTap: "checklogin",
    currentUrl:"mine",
    unCheckImgUrl:"/assets/images/tabbar/mygrey.png",
    checkedImgUrl:"/assets/images/tabbar/mypink.png",
    btnType: 0,
    title:"我的"
  }]
}

module.exports = {
  agentMenuData: agentMenus,
  masterMenuData: masterMenus,
  userMenuData: userMenus
}
