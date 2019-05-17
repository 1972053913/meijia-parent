package cn.itsource.meijia.service.impl;

import cn.itsource.meijia.domain.ProductType;
import cn.itsource.meijia.mapper.ProductTypeMapper;
import cn.itsource.meijia.service.IProductTypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品目录 服务实现类
 * </p>
 *
 * @author lilin
 * @since 2019-05-16
 */
@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {

    /**
     * 查询菜单树
     * @return
     */
    @Override
    public List<ProductType> loadTreeData() {
        //这是通过递归查询，传入父ID
        //return loadDateTree(0L);
        //这是循环查询
        return loadDataTree();
    }


    /**
     * 封装方法，递归查询
     * @return
     */
    public List<ProductType> loadDateTree(Long pid){
        //查询一级子菜单
        List<ProductType> children = baseMapper.selectList(new QueryWrapper<ProductType>().eq("pid", pid));
        //递归的出口
        if(children==null||children.size()==0){
            return null;
        }
        //循环遍历一级子菜单，并查询是否有下一级，有就递归
        for (ProductType child : children) {
            List<ProductType> children2 = loadDateTree(child.getId());
            child.setChildren(children2);
        }
        return children;
    }


    /**
     * 循环查询，只查一次更快
     * @return
     */
    private List<ProductType> loadDataTree(){
        //查询所有
        List<ProductType> productTypes = baseMapper.selectList(null);

        Map<Long,ProductType> map = new HashMap<>();
        for (ProductType productType : productTypes) {
            map.put(productType.getId(),productType);
        }
        List<ProductType> list = new ArrayList<>();
        //找出一级子菜单
        for (ProductType productType : productTypes) {
            //表示这是父级菜单
            if (productType.getPid() == 0) {
                list.add(productType);
            }else {
                map.get(productType.getPid()).getChildren().add(productType);
            }
        }
        return list;
    }
}
