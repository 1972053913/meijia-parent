package cn.itsource.meijia.service;

import cn.itsource.meijia.ProductApplication;
import cn.itsource.meijia.client.RedisClient;
import cn.itsource.meijia.domain.ProductType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest(classes = ProductApplication.class)
@RunWith(SpringRunner.class)
public class IProductTypeServiceTest {

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private IProductTypeService productTypeService;

    @Test
    public void loadTreeData() {
        List<ProductType> productTypes = productTypeService.loadTreeData();
        for (ProductType productType : productTypes) {
            System.out.println(productType);
        }
    }

    @Test
    public void string1(){
        redisClient.set("aa","bbbbbb");
    }

    @Test
    public void string2(){
        String aa = redisClient.get("aa");
        System.out.println(aa);
    }
}