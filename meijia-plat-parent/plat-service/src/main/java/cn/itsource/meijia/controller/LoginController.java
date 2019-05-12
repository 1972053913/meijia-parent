package cn.itsource.meijia.controller;

import cn.itsource.meijia.util.AjaxResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {

    /**
     * 因为前段传数据的格式是JSON，登录是用post请求，所以这里需要用@RequestBody注解，
     * 并且用map接收是因为只有两个参数，做好不要用实体类，浪费资源
     * @param map
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录接口")
    public AjaxResult login(@RequestBody Map<String,Object> map){
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        if("root".equals(username)&&"root".equals(password)){
            //登录成功
            return AjaxResult.me();
        }else {
            return AjaxResult.me().setSuccess(false).setMessage("登录失败！");
        }
    }

}
