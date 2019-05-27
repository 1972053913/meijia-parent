package cn.itsource.meijia.repository;

import cn.itsource.meijia.domain.ProductDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductDocRepository extends ElasticsearchRepository<ProductDoc,Long> {
}
