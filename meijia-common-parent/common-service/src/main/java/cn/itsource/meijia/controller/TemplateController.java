package cn.itsource.meijia.controller;

import cn.itsource.meijia.util.VelocityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TemplateController {

    @PostMapping("/page")
    public void createStaticHtml(@RequestBody Map<String,Object> params){
        Object model = params.get("model");
        String templatePath = (String) params.get("templatePath");
        String targetPath = (String) params.get("targetPath");
        VelocityUtils.staticByTemplate(model,templatePath,targetPath);

    }
}
