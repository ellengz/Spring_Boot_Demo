# Spring Boot Demo

## About
A Spring Boot practice project based on online courses https://www.imooc.com/learn/810, https://www.imooc.com/learn/767  
Functions include:  
* CRUD for model "Stuff"  
* Global exception handling
* Unit Test
* AOP

## Notes
### @SpringBootApplication
equals @EnableAutoConfiguration + @ComponentScan + @Configuration

### @RestController
equals @Controller + @ResponseBody  
response will be a JSON string

### @Autowired
autowire beans by dependency injection  
can be used for constructor, setter method, fields(*not recommended, why?*)

### @RequestMapping(path, method)
* @GetMapping
* @PostMapping
* @PutMapping
* @DeleteMapping
```java
// update by id
@PutMapping(value = "/stuff/{id}")
public Result stuffUpdate(@PathVariable("id") Integer id,
                         @RequestParam("name") String name,
                         @RequestParam("quantity") Integer quantity){
...
}
```
### @Repository
data access and storage

### @Transactional
the transaction will succeed only when all parts of it succeed, otherwise roll back

### application.yml
configuration properties, more elegant compared with application.properties  
in this case application-dev.yml will be used  
**application.yml**
```java
spring:
  profiles:
    active: dev
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/dbdemo?useSSL=true
    username: 
    password: 
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```
**application-dev.yml**
```java
server:
  port: 8080
  servlet:
    context-path: /demo
```
### AOP
* @Pointcut
* @Before - methods below under @Before show how to understand a HttpServletRequest
* @After
* @AfterReturning
```java
@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);
    
    @Pointcut("execution(public * com.ellen.demo.controller.StuffController.*(..))")
    public void log() {}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // url
        logger.info("url={}", request.getRequestURL());

        // http methods
        logger.info("method={}", request.getMethod());

        // ip
        logger.info("ip={}", request.getRemoteAddr());

        // class methods
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        // arguments
        logger.info("args={}", joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter() {
        logger.info("66666666666");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Optional object) {
        logger.info("response={}", object.toString());
    }
}
