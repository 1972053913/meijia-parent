package cn.itsource.meijia.service;

import cn.itsource.meijia.domain.Brand;
import cn.itsource.meijia.query.BrandQuery;
import cn.itsource.meijia.util.PageList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 品牌信息 服务类
 * </p>
 *
 * @author lilin
 * @since 2019-05-16
 */
public interface IBrandService extends IService<Brand> {

    PageList<Brand> getByQuery(BrandQuery query);

    Map<String,Object> loadByPrductTypeId(Long productTypeId);
}
