# 自定义bean名称生成器
1. 继承类：AnnotationBeanNameGenerator，并重写方法：buildDefaultBeanName
2. 在启动类中添加注释：@ComponentScan(nameGenerator = BeanNameGeneratorApplication.SpringBeanNameGenerator.class) 使用自定义类
3. 此时默认使用的类名简称获取bean会失效，要使用自定义的生成名称