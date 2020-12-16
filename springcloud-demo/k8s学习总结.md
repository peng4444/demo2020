# K8S学习总结

[TOC]

## K8S
[K8S安装](参考书: k8s权威指南)
[K8S入门](https://www.funtl.com/zh/service-mesh-kubernetes/)

[当前标签：kubernetes](https://www.cnblogs.com/zhanglianghhh/tag/kubernetes/default.html?page=2)

[随笔分类 - Kubernetes](https://www.cnblogs.com/dukuan/category/1320955.html)
[随笔分类 - K8S](https://www.cnblogs.com/zzq6032010/category/1877374.html)
[容器编排系统之Kubernetes基础入门](https://www.cnblogs.com/qiuhom-1874/p/14126750.html)
[简单了解一下K8S，并搭建自己的集群](https://www.cnblogs.com/detectiveHLH/p/12048795.html)
[6 个 K8s 日志系统建设中的典型问题，你遇到过几个？](https://www.cnblogs.com/alisystemsoftware/p/11544392.html)
[spring-cloud-kubernetes官方demo运行实战](https://www.cnblogs.com/bolingcavalry/p/11445732.html)
[从零开始入门 K8s| 详解 Pod 及容器设计模式](https://www.cnblogs.com/alisystemsoftware/p/11551525.html)
[入门级实操教程！从概念到部署，全方位了解K8S Ingress！](https://www.cnblogs.com/rancherlabs/p/12034075.html)
[超长干货丨Kubernetes网络快速入门完全指南](https://www.cnblogs.com/rancherlabs/p/12101762.html)
[Kubernetes+Docker+Istio 容器云实践](https://www.cnblogs.com/yixinjishu/p/11691932.html)
[k8s 开船记-首航：博客站点从 docker swarm 切换到 k8s](https://www.cnblogs.com/cmt/p/12033446.html)
### K8S简介
```markdown
Kubernetes是Google 2014年创建管理的，是Google 10多年大规模容器管理技术Borg的开源版本。
    Kubernetes是容器集群管理系统，是一个开源的平台，可以实现容器集群的自动化部署、自动扩缩容、维护等功能。
    使用 Kubernetes 我们可以：
        - 快速部署应用
        - 快速扩展应用
        - 无缝对接新的应用功能
        - 节省资源，优化硬件资源的使用
Kubernetes的目标是促进完善组件和工具的生态系统，以减轻应用程序在公有云或私有云中运行的负担。
特点
    - 可移植： 支持公有云，私有云，混合云，多重云（多个公共云）
    - 可扩展： 模块化，插件化，可挂载，可组合
    - 自动化： 自动部署，自动重启，自动复制，自动伸缩/扩展
```
### 为什么需要 Kubernetes
```markdown
可以在物理或虚拟机的Kubernetes集群上运行容器化应用，Kubernetes能提供一个以 “容器为中心的基础架构”，
满足在生产环境中运行应用的一些常见需求，如：
    - 多个进程协同工作
    - 存储系统挂载
    - 应用健康检查
    - 应用实例的复制
    - 自动伸缩/扩展
    - 注册与发现
    - 负载均衡
    - 滚动更新
    - 资源监控
    - 日志访问
    - 调试应用程序
    - 提供认证和授权
```
### K8S架构
[概念总结](https://www.funtl.com/zh/service-mesh-kubernetes/%E6%A6%82%E5%BF%B5%E6%80%BB%E7%BB%93.html)
![K8S架构](https://www.funtl.com/assets1/Lusifer_20190531065907.png)
### K8S安装配置
### K8S命令
###
### K8S集群部署
[高可用的K8S集群部署方案](https://www.cnblogs.com/ants/p/11489598.html)
### 
[Kubernetes笔记（一）：十分钟部署一套K8s环境](https://www.cnblogs.com/spec-dog/p/12793018.html)
[Kubernetes笔记（二）：了解k8s的基本组件与概念](https://www.cnblogs.com/spec-dog/p/12849328.html)
[Kubernetes笔记（三）：Gitlab+Jenkins Pipeline+Docker+k8s+Helm自动化部署实践](https://www.cnblogs.com/spec-dog/p/12874295.html)
[Kubernetes笔记（四）：详解Namespace与资源限制ResourceQuota，LimitRange](https://www.cnblogs.com/spec-dog/p/13035898.html)
[Kubernetes笔记（五）：了解Pod（容器组）](https://www.cnblogs.com/spec-dog/p/13651965.html)
[Kubernetes笔记（六）：了解控制器 —— Deployment](https://www.cnblogs.com/spec-dog/p/14003370.html)
