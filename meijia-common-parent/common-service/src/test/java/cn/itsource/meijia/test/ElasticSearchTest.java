package cn.itsource.meijia.test;

import cn.itsource.meijia.CommonApplication;
import cn.itsource.meijia.domain.ProductDoc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonApplication.class)
public class ElasticSearchTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void test(){
        elasticsearchTemplate.deleteIndex("meijia");
        elasticsearchTemplate.createIndex("meijia");
        elasticsearchTemplate.putMapping(ProductDoc.class);
    }
}
