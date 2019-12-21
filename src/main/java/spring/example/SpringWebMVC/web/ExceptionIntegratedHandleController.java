package spring.example.SpringWebMVC.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/exceptionIntegratedHandle")
public class ExceptionIntegratedHandleController {

    @RequestMapping("/{id}")
    // @ResponseBody 注解表示该方法的返回的结果直接写入 HTTP 响应正文（ResponseBody）中；一般在异步获取数据时使用。
    @ResponseBody
    // @PathVariable 注解用来获取请求路径（url ）中的动态参数。
    public Object hello(@PathVariable String id) {
        if ("1".equals(id)) {
            // 用于演示: 手动抛出异常, 期望被 SpringMVC 处理
            throw new RuntimeException("手动抛出异常, 期望被 SpringMVC 处理");
        } else if ("2".equals(id)) {
            int value = 1 / 0;
            return "手动运算错误";
        } else {
            return "no exception";
        }
    }
}
