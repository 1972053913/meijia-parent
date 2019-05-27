package cn.itsource.meijia.service;

import cn.itsource.meijia.domain.Product;
import cn.itsource.meijia.domain.Specification;
import cn.itsource.meijia.query.ProductQuery;
import cn.itsource.meijia.util.PageList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品 服务类
 * </p>
 *
 * @author lilin
 * @since 2019-05-20
 */
public interface IProductService extends IService<Product> {

    PageList<Product> getByQuery(ProductQuery query);

    List<Specification> getViewProperties(Long productId);

    void saveViewProperties(List<Specification> specifications, Long productId);

    List<Specification> getSkuProperties(Long productId);

    void saveSkuProperties(List<Specification> specifications, Long productId, List<Map<String, String>> skus);

    void onSale(List<Long> idList);

    void offSale(List<Long> idList);

    List<Map<String,Object>> loadCrumbs(Long productTypeId);
}
