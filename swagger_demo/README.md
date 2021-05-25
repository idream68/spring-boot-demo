# swagger 使用例子

使用 
implementation group: 'io.springfox', name: 'springfox-swagger2', version: '3.0.0'
implementation group: 'io.springfox', name: 'springfox-swagger-ui', version: '3.0.0'
访问 /swagger-ui.html 可能会出现   Whitelabel Error Page  错误

改成使用
implementation group: 'io.springfox', name: 'springfox-boot-starter', version: '3.0.0'
并将访问地址修改为：
/swagger-ui/
最后一个 / 不能省略
