controller层参数接收全部用包括类型 
因为包装类型可以接收空参数 不符合格式的日期 整型 默认处理为null，同时也可以精准捕获异常信息，给出业务提示信息。
基本类型如果接收异常参数，则会抛出异常，且不容易精确捕获处理，并给出业务提示。


如果超过两个参数，则封装成对象转输 int不能为空 Integer可以为null
查询方法全部以query开头
删除方法全部以delete开发
修改方法全部以update开发
不允许使用map传递参数


统一异常处理
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
```
controller logger继承自base

统一鉴权
统一xss过滤

