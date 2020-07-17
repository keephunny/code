controller层参数接收全部用包括类型 
因为包装类型可以接收空参数 不符合格式的日期 整型 默认处理为null，同时也可以精准捕获异常信息，给出业务提示信息。
基本类型如果接收异常参数，则会抛出异常，且不容易精确捕获处理，并给出业务提示。


如果超过两个参数，则封装成对象转输 int不能为空 Integer可以为null
查询方法全部以query开头
删除方法全部以delete开发
修改方法全部以update开发
不允许使用map传递参数

### 统一包装返回
可以使用interceptor 拦戴器的方式实现
类似于spring cloud gateway 统一包装对象输出
zuulFilter
包装类型包括
null
基本类型
包装类型
集合

小数格式化 不要全局设置，小数位0也保留
### 统一异常处理
处理404异常 区分静态资源请求
  spring.mvc:
    #不用handler捕获异常，让springboot处理
    throw-exception-if-no-handler-found: true
    
   @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public RespResult handleNoHandlerFoundException(HttpServletRequest request,  NoHandlerFoundException e) {
        logger.error(request.getRequestURI() + " " + e.getMessage(), e);
        String msg = "请求地址不存在";
    }
    
日期 数字 参数校验 自定义提示信息参考    
```
    GlobalControllerAdvice
    RequestQueryPageVo
        @NotNull(message = "起始页不允许为空")
        @Min(value = 0, message = "起始页不允许小于0")
        private Integer start = 0;
    BaseController.checkParam
    
    @GetMapping("/queryAll")
    public RespResult queryAll(@Valid RequestQueryPageVo queryPageVo, BindingResult bindingResult) {
        RespResult responseVo = checkParam(bindingResult);
        if (responseVo.getCode() != HttpStatus.OK.value()) {
            return responseVo;
        }
    }
    
    
    
    @Validated
    public class TestController extends BaseController 
    单参数校验，需要在类头部添加@Validated
    public String test3(@NotBlank  String name) 
```
controller logger继承自base

统一鉴权

### 统一xss过滤
添加白名单配置 不过滤静态资源
针对指定角色用户 全部放开
只针对json请求 普通页面请求不处理
只针对写入请求，查询请求不过滤
去掉swagger druidMonitor
去掉上传文件接口
文件名过滤
过滤@RequestBody参数值
路径匹配正则

只替换<script> 不区分大小 中间有空格
    <style> </style>
    <a> </a>
    

   //如果是ajax返回指定数据
        String requestedWith = httpReq.getHeader("X-Requested-With");
        System.out.println(requestedWith);
        if (StringUtils.isNotEmpty(requestedWith) && StringUtils.equals(requestedWith, "XMLHttpRequest")) {
        }

### 安全停上
http://localhost:8080/actuator/shutdown
增加webfilter 该地址的权限判断 可根据登录用户 或地址IP     

### 健康状况
### projo层划分



单表查询 传对象进去
mapper xml 自动适配各字段条件，避免每查个字段都写个方法。



### valid参数校验
#### 单个参数校验
```
    @Validated
    public class UserInfoController
        public RespResult deleteByKey(@Max(value = 1) Integer id) {
            //此方法是通过异常抛出 校验信息，且提示信息不好定制控制，不建议使用。
            //单参数校验 建议使用业务代码实现，或抽像出通用方法
            //多参数方法校验 结构更加清晰，信息可控友好。
        }
    }

```
#### 对像参数校验

#### 常用类型校验

#### 自定义校验注解


    String message() default "不允许超过指定长度{length}";
    {变量名}可以动态获取自定义注相关属性，避免在业务代码里重复编写。
    
    参考学习源码 相关的提示信息都是从外部配置文件读取，汉字全部转unicode字符。

#### 未解决问题
* 时间跨度的精准计算
例如1年的限制，2018-09-09 00:00:00 至 2019-09-09 12:00:00是超过1年12小的，需要精确计算到秒。

* 考虑前端参数超级大参数 或大文件的拉截
如果超级大的参数，虽然不走业务逻辑，但也会走校验代码接收与返回会占用大量资源。建议在前置或nginx端进行拦截处理。

### 数据持久化问题
1. 分库分表机制

2. ORM机制
将代码生成工具的模块化代码与业务自定义代码做到物理分隔。





### monitor监控
mysql数据库主从监控
是否完全一致，延时时间
表的总行数
分库分表


日志