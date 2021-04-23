# @Aspect 使用方法
- 使用className作为切点（或许有误，不确定是不是只作用于类）
  @Pointcut("execution(public * com.springboot.demo.aspect.controller..*.*(..))")
- 使用注解作为切点
  @Pointcut("@annotation(com.springboot.demo.aspect.annotation.WebLog)")
  
1. @Around
   环绕切点运行
2. @Before
   在切点之前运行
3. @After
   在切点之后运行


