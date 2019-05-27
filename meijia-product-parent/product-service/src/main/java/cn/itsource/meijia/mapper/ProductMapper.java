package cn.itsource.meijia.mapper;

import cn.itsource.meijia.domain.Product;
import cn.itsource.meijia.query.ProductQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品 Mapper 接口
 * </p>
 *
 * @author lilin
 * @since 2019-05-20
 */
public interface ProductMapper extends BaseMapper<Product> {

    IPage<Product> selectByQuery(Page<Product> page,@Param("query") ProductQuery query);

    /**
     * 商品上架
     * @param ids
     * @param onSaleTime
     */
    void onSale(@Param("ids") List<Long> ids,@Param("onSaleTime") long onSaleTime);

    void offSale(@Param("ids")List<Long> ids,@Param("offSaleTime") long offSaleTime);
}
