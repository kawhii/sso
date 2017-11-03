# 验证模块接口说明文档

* 信息发送者`ISender`（主导发送信息对象，例如发送校验码到手机，发送到邮箱）
* 信息存储`IStore`（负责存储发送者发出的信息）
* 信息生成器`InformativeGenerator`（负责生成发送者需要发送的信息）
* 信息校验器`IValidator`（负责对数据进行校验）

## 流程
1. 信息生成器负责生成数据提供给信息发送者
2. 发送成功保存
3. 校验成功删除

## 配置

```properties
#验证码发送邮箱
sso.validate.mail.enable=true
sso.validate.mail.from=${spring.mail.username}
sso.validate.mail.content=统一门户注册验证码为：%s
sso.validate.mail.subject=统一门户注册
```

## 程序发送

```java
@Autowired
private DefaultValidateService validateService;

//验证
@PostMapping
public String registry(Model model, HttpServletRequest request, @Valid RegistryInfoVo registryInfoVo) {
        ValidateResult result = validateService.validate(
                new MailValidateCredential(request.getSession().getId(), registryInfoVo.getEmail(),
                        "registry", registryInfoVo.getValidateCode()));
    if (result == ValidateResult.FAIL) {
        model.addAttribute("validateError", "验证码错误");
    } else if (result == ValidateResult.EXPIRED) {
        model.addAttribute("validateError", "验证码已过期");
    }
    return "registryView";
}


//发送
validateService.send(new MailCredential(request.getSession().getId(), mail, "registry"));
           
```
