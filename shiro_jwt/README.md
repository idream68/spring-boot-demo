# shiro + JWT 示例

JWT token 一次签发，永久有效，退出token依然有效，失效只能等到超过有效期，使用subject.logout 只会执行删除session之类的操作，并没有进行token的销毁

使用自定义退出：

需要注释

ShiroConfig 类中的  logout 
//        filterChainDefinitionMap.put("/logout", "logout"); // 退出登录

有本拦截器则会使用 shiro 自己的退出，需要实现 LogoutFilter 接口 （未验证）

遗留问题: 退出后原token依然有效，暂未查找到响应方法，退出有问题

思路：在token中添加生成时间信息并在cache中保存token生成时间，退出时，修改用户token，并修改生成时间，之后登录获取token生成时间，比较两个时间是否
相同， 相同则认为登录，不相同则认为已退出（未进行代码验证）


参考：https://blog.csdn.net/pengjunlee/article/details/95600843