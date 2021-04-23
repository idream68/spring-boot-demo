# WebFilter 使用说明
- 在 @WebFilter 注释的类不需要使用 @Component 进行注释，否则会拦截全部请求，而不是根据编写的规则
- 启动类需要使用 @ServletComponentScan 进行注释，使spring boot 可以扫描到 使用 @WebFilter 注释的类

