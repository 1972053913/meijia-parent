package cn.itsource.meijia.service.impl;

import cn.itsource.meijia.domain.Product;
import cn.itsource.meijia.mapper.ProductMapper;
import cn.itsource.meijia.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author lilin
 * @since 2019-05-16
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
