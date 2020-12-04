# Vue学习总结
[参考书：Vue.js实战](https://www.baidu.com) [Vue.js实战实例代码](https://github.com/icarusion/vue-book)
[参考书：Vue.js权威指南](https://www.baidu.com)

[参考博客：深入浅出Vue](https://www.cnblogs.com/By-ruoyu/category/1378905.html)
[参考博客：随笔分类 - Vue](https://www.cnblogs.com/Yunya-Cnblogs/category/1803872.html)
[参考博客：vue.js合集](https://www.cnblogs.com/danvic712/p/9549100.html)
## 前端基础
### 前端三要素
```markdown
HTML（结构）：超文本标记语言（Hyper Text Markup Language），决定网页的结构和内容
CSS（表现）：层叠样式表（Cascading Style Sheets），设定网页的表现样式
JavaScript（行为）：是一种弱类型脚本语言，其源代码不需经过编译，而是由浏览器解释运行，用于控制网页的行为
```
### Vue基础
[一个后端开发的 Vue 笔记【入门级】](https://www.cnblogs.com/ideal-20/p/13624713.html)
[后端小白的VUE入门笔记, 前端高能慎入](https://www.cnblogs.com/ZhuChangwu/p/11303521.html)
#### Vue.js简介
>> 简单小巧的核心，渐进式技术拢，足以应付任何规模的应用。
```markdown
简单小巧是指Vue.js压缩后大小仅有17KB。所谓渐进式（Progressive），就是你可以一步一步、有阶段性地来使用Vue.js，不必一开始就使用所有的东西。
Vue的优点：
    1、体积小
        压缩后33K
    2、更高的运行效率
        基于虚拟dom一种可以预先通过javascript进行各种计算,把最终的DOM操作计算出来并优化的技术，由于这个DOM操作属于预处理操作，并没有真实的操作DOM，所以叫做虚拟DOM。
    3、双向数据绑定
        让开发者不用再去操作dom对象，把更多的精力投入到业务逻辑上
    4、生态丰富、学习成本低
        市场上拥有大量成熟、稳定的基于 vue.js 的 ui 框架、常用组件！
        拿来即用实现快速开发
        对初学者友好、入门容易、学习资料多
它提供了现代Web开发中常见的高级功能，比如：
    • 解耦视图与数据。
    • 可复用的组件
    • 前端路由
    • 状态管理
    • 虚拟DOM(Virtual DOM)
```
#### MVVM模式
![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/ff58572d768a495d9d1e2fc081a37a8e~tplv-k3u1fbpfcp-zoom-1.image)
```markdown
MVVM模式是由经典的软件架构MVC衍生来的 。当View（视图层）变化时，会自动更新到ViewModel（视图模型），反之亦然。
View和ViewModel之间通过双向绑定（data-binding）建立联系。
Vue.js通过MVVM的模式拆分为视图与数据两部分，并将其分离。因此，你只需要关心你的数据即可，DOM的事情Vue会帮你自动搞定。
    - Model（模型层）：表示 Javascript 数据对象
    - View（视图层）：表示 DOM，也可以简单理解为前端展示的内容
    - ViewModel：连接视图和数据，即用于双向绑定数据与页面
```
#### 双向数据绑定
```markdown
View和ViewModel之间的双向绑定，通俗的说就是：当数据发生变化时，视图也变化，而当视图发生变化的时候，数据也跟着变化。
Vue 提供了 v-model 指令，它能轻松实现表单输入和应用状态之间的双向绑定
```
#### 计算属性
```markdown
计算属性,说白了就是vue给我们的一块糖,让我们定制数据的变化规则,然后vue帮我们渲染在html页面上
 所谓计算属性，说白了，其实就是根据data中现有的属性计算得到一个新的属性 ，此外，计算属性函数是不需要我们手动执行的，会自动执行
    - 计算属性是针对data中的字段的操作
    - 计算属性中的每一个函数,都分两部分: get和set , 默认是get,作用是把这个方法的返回值渲染进页面, set方法,就是重新设置值, 然后get会重新渲染html
    - 计算属性是存在缓存的,key就是函数的名字,value就是计算得到的值
```
#### 内置指令与自定义指令
```markdown
v-once: 当标签中添加该属性指令时，标签渲染的内容将是固定的不会随着数据层数据的动态改变而进行改变。
v-html:　当标签中添加该属性指令时，该标签渲染的内容将会以HTML代码呈现。注意，该属性指令应该通过等号绑定一个数据。v-html="msg"
v-text: 和直接使用{{}}渲染内容无太大差异，如果渲染内容是一个HTML标签，其本质原理都是将<替换成&lt;，将>替换成&gt;。会预防XSS攻击。
v-show: 属性指令为false时将该标签将添加display:none的样式。v-show="false"
V-pre: 属性指令会将标签中的内容按照原本格式进行呈现，类似于<pre>标签。
v-clock: 在网络情况较差的环境下，可能会出现模板渲染不及时的问题。使用v-cloak属性指令可挡住{{}}模板语法，使用户获得更好的体验。
```
#### 组件
[Vue 组件化开发](https://www.cnblogs.com/Yunya-Cnblogs/p/14013159.html)
[Vue 组件化开发之插槽](https://www.cnblogs.com/Yunya-Cnblogs/p/14013464.html)
#### Axios
```markdown
我们在以前传统的开发中，我们一般会使用Ajax进行通信，而Vue.js作为一个视图层框架，并不支持Ajax的通信功能，所以可以使用Axios来实现Ajax的异步通信.
Axios：前端通信框架；因为Vue的边界很明确，就是为了处理DOM，所以并不具备通信能力，此时就需要额外使用一个通信框架与服务器交互；当然也可以直接选择使用JQuery提供的AJAX通信功能；
Axios特点：
    - 从浏览器中创建 XMLHttpRequests
    - 从 node.js 创建 http 请求
    - 支持 Promise API
    - 拦截请求和响应
    - 转换请求数据和响应数据
    - 取消请求
    - 自动转换 JSON 数据
    - 客户端支持防御 XSRF
```
### 进阶篇
[vue 自动化路由实现](https://www.cnblogs.com/mianbaodaxia/p/11452123.html)
#### Vue.js的工程化
#### Render函数
#### webpack的使用
#### Vue.js插件
[Vue-router插件使用](https://www.cnblogs.com/Yunya-Cnblogs/p/14017686.html)

### 实战篇
[当前标签：循序渐进VUE+Element](https://www.cnblogs.com/wuhuacong/tag/%E5%BE%AA%E5%BA%8F%E6%B8%90%E8%BF%9BVUE%2BElement/)

## Vue项目
[1.vue创建项目的步骤](https://blog.csdn.net/weixin_42218847/article/details/81363421)
[Vue之vue项目引入vuex](https://blog.csdn.net/weixin_40736319/article/details/89379474)
[利用HBuilder打包Vue开发的webapp为app](https://www.cnblogs.com/dengyao-blogs/p/11532133.html)
[vue-cli3 项目从搭建优化到docker部署0](https://blog.csdn.net/QQ729533020/article/details/99713936?utm_source=app)
## Vue项目
[从0开始，手把手教你用Vue开发一个答题App](https://www.cnblogs.com/songboriceboy/p/13265777.html)
[使用electron+vue开发一个跨平台todolist（便签）桌面应用](https://www.cnblogs.com/xhznl/p/14004992.html)
[跨平台桌面端视频资源播放器.简洁无广告.免费高颜值](https://github.com/Hunlongyu/ZY-Player)
[乐购商城（tesco-mall）是一套完善的微服务电商系统，由前台商城系统和后台管理系统构成，基于SpringBoot、SpringCloud、SpringCloud alibaba、Vue实现，采用前后端分离开发模式。](https://github.com/Jerusalem01/tesco-mall)
[基于Spring Boot、Spring Cloud、Vue.js 、Element UI实现，采用前后端分离架构的权限管理系统，JAVA快速开发平台。](https://gitee.com/liuge1988/kitty)
## Vue其他
[React合集](https://www.cnblogs.com/strick/category/1455720.html)
[使用 pdf.js 在网页中加载 pdf 文件](https://www.cnblogs.com/SavionZhang/p/11757849.html)
[flask+layui+echarts实现前端动态图展示数据](https://www.cnblogs.com/huxiansheng/p/11611178.html)
[vue+echarts+datav大屏数据展示及实现中国地图省市县下钻](https://www.cnblogs.com/weijiutao/p/13977011.html)
[快速上手 Echarts](https://www.cnblogs.com/stormlong/p/10923747.html)

## Mock
[Mockserver之Moco框架搭建使用](https://www.cnblogs.com/Sweettesting/p/13860291.html)
[mock.js+JOSN.server搭建](https://www.cnblogs.com/kagol/p/14083230.html)
