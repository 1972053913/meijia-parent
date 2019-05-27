package cn.itsource.meijia.service;

import cn.itsource.meijia.domain.Sku;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * SKU 服务类
 * </p>
 *
 * @author lilin
 * @since 2019-05-22
 */
public interface ISkuService extends IService<Sku> {

    List<Sku> getByProductId(Long productId);
}
