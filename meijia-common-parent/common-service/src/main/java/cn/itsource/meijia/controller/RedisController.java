package cn.itsource.meijia.controller;

import cn.itsource.meijia.util.RedisUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    /**
     * 将数据保存到redis中
     * @param key
     * @param value
     */
    @PostMapping("/redis")
    public void set(String key,String value){
        RedisUtils.INSTANCE.set(key,value);
    }

    /**
     * 从redis中获取数据
     * @param key
     * @return
     */
    @GetMapping("/redis")
    public String get(String key){
        return RedisUtils.INSTANCE.get(key);
    }

}
