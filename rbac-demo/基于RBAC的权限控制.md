# 基于RBAC的权限控制
>> RBAC（Role-Based Access Control ），即基于角色的访问控制模型。
>> [基于RBAC的权限控制浅析（结合Spring Security）](https://www.cnblogs.com/liruilong/p/12993346.html)
## 数据库模型
user -> role -> right

## 具体实现
### 权限控制大概执行流程
```markdown
用户登录之后首先进行身份验证,成功之后获取当前用户的所有角色,之后根据角色加载对应的权限菜单,这里默认不加载没有权限的菜单,
当存在直接输入URL路径的情况时,对于登录用户的每一个请求,都会通过鉴权处理,分析角色。最后通过权限的判断分析是否可以访问菜单资源。
```
## 参考资料

