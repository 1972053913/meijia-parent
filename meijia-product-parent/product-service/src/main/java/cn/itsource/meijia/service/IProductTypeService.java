package cn.itsource.meijia.service;

import cn.itsource.meijia.domain.ProductType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品目录 服务类
 * </p>
 *
 * @author lilin
 * @since 2019-05-16
 */
public interface IProductTypeService extends IService<ProductType> {

    List<ProductType> loadTreeData();
}
