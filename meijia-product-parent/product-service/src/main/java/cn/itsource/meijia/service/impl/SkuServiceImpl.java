package cn.itsource.meijia.service.impl;

import cn.itsource.meijia.domain.Sku;
import cn.itsource.meijia.mapper.SkuMapper;
import cn.itsource.meijia.service.ISkuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * SKU 服务实现类
 * </p>
 *
 * @author lilin
 * @since 2019-05-22
 */
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements ISkuService {

    @Override
    public List<Sku> getByProductId(Long productId) {
        return baseMapper.selectList(new QueryWrapper<Sku>().eq("productId",productId));
    }
}
