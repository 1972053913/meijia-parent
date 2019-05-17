package cn.itsource.meijia.mapper;

import cn.itsource.meijia.domain.Brand;
import cn.itsource.meijia.query.BrandQuery;
import cn.itsource.meijia.util.PageList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 品牌信息 Mapper 接口
 * </p>
 *
 * @author lilin
 * @since 2019-05-16
 */
public interface BrandMapper extends BaseMapper<Brand> {

    IPage<Brand> selectByQuery(Page<Brand> page, @Param("query") BrandQuery query);
}
