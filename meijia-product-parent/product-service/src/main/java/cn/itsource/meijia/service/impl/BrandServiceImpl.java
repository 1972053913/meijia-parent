package cn.itsource.meijia.service.impl;

import cn.itsource.meijia.domain.Brand;
import cn.itsource.meijia.mapper.BrandMapper;
import cn.itsource.meijia.query.BrandQuery;
import cn.itsource.meijia.service.IBrandService;
import cn.itsource.meijia.util.PageList;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * <p>
 * 品牌信息 服务实现类
 * </p>
 *
 * @author lilin
 * @since 2019-05-16
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {


    @Override
    public PageList<Brand> getByQuery(BrandQuery query) {
        Page<Brand> page = new Page<>(query.getPage(),query.getSize());
        IPage<Brand> brandIPage = baseMapper.selectByQuery(page, query);
        return new PageList<Brand>(brandIPage.getTotal(),brandIPage.getRecords());
    }

    /**
     * 这是加载品牌类型以及首字母
     * @param productTypeId
     * @return
     */
    @Override
    public Map<String, Object> loadByPrductTypeId(Long productTypeId) {
        Map<String,Object> map = new HashMap<>();
        List<Brand> brands = baseMapper.selectList(new QueryWrapper<Brand>().eq("product_type_id", productTypeId));
        map.put("brands",brands);
        //首字母,去重，排序
        TreeSet<String> letters = new TreeSet<>();
        for (Brand brand : brands) {
            letters.add(brand.getFirstLetter());
        }
        map.put("letters",letters);
        return map;
    }
}
