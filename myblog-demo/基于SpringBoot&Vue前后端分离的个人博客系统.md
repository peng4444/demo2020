# 基于SpringBoot&Vue前后端分离的个人博客系统

[TOC]


## 参考资料
>> [参考开源项目：vueblog](https://github.com/MarkerHub/vueblog)
>> [参考视频资料：基于SpringBoot+Vue开发的前后端分离博客项目完整教学](https://www.bilibili.com/video/BV1PQ4y1P7hZ)
>> [参考开发文档：4小时开发一个SpringBoot+vue前后端分离博客项目](https://juejin.im/post/6844903823966732302)
>> [手把手教你Docker+nginx部署Springboot+vue前后端分离项目](https://juejin.cn/post/6886061338804617229/)
>> [教你Docker+nginx部署SpringBoot+vue前后端分离项目](https://www.bilibili.com/video/BV17A411E7aE)
>> [参考开源项目：vsblog](https://github.com/YUbuntu0109/vsblog)
##
[1.vue创建项目的步骤](https://blog.csdn.net/weixin_42218847/article/details/81363421)
[Vue之vue项目引入vuex](https://blog.csdn.net/weixin_40736319/article/details/89379474)
[Springboot Vue Login(从零开始实现Springboot+Vue登录)](https://blog.csdn.net/xiaojinlai123/article/details/90694372)
[超美观的 Vue+Element 开源后台管理 UI](https://mp.weixin.qq.com/s?__biz=Mzg2MjEwMjI1Mg==&mid=2247505441&idx=1&sn=700fe60a8d4e45c7cb528e8230a8ef43&chksm=ce0e61a2f979e8b468a84aee2bfef45329aaf76a3fcc055c9bf287ebc4e2e8a37c80f775d952&mpshare=1&scene=23&srcid=1119tBXDq9Ef3xoqBvi3x9OS&sharer_sharetime=1605769540447&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)
### 开发工具
```markdown
- idea
- mysql
- jdk 8
- maven
```
### 后端技术栈
```markdown
- SpringBoot
- mybatis plus
- shiro
- lombok
- redis
- hibernate validatior
- jwt
```
### 前端技术栈
```markdown
- vue
- element-ui
- axios
- mavon-editor
- markdown-it
- github-markdown-css
1.下载安装Node.js  node -v  、npm -v
2.安装淘宝npm npm install -g cnpm --registry=https://registry.npm.taobao.org
3.vue-cli 安装依赖包  cnpm install --g vue-cli   // -g表示全局安装，vue-cli是模块
4.创建项目框架 参考[1.vue创建项目的步骤](https://blog.csdn.net/weixin_42218847/article/details/81363421)
5.移动到创建的文件夹目录启动vue  $ npm run dev  //访问http://localhost:808查看前端主页
6.安装element-ui  $ cnpm install element-ui --save
7.安装axios  cnpm install axios --save
8.引入vuex  cnpm install vuex --save
9.引入vue-router cnpm install vue-router --save
```