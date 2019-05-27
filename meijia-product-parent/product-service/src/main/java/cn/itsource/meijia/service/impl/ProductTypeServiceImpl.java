package cn.itsource.meijia.service.impl;

import cn.itsource.meijia.client.RedisClient;
import cn.itsource.meijia.client.TemplateClient;
import cn.itsource.meijia.domain.ProductType;
import cn.itsource.meijia.mapper.ProductTypeMapper;
import cn.itsource.meijia.service.IProductTypeService;
import cn.itsource.meijia.util.StrUtils;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;

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

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private TemplateClient templateClient;


    /**
     * 查询菜单树
     *
     * @return
     */
    @Override
    public List<ProductType> loadTreeData() {
        //先从缓存中查
        String productTypes = redisClient.get("productTypes");
        if(StringUtils.isEmpty(productTypes)){
            List<ProductType> list = loadDataTree();
            String string = JSONArray.toJSONString(list);
            redisClient.set("productTypes",string);
            return list;
        }else {
            //转换为list
            List<ProductType> list = JSONArray.parseArray(productTypes, ProductType.class);
            return list;
        }
    }


    /**
     * 这是创建静态html页面
     */
    @Override
    public void createStaticHtml() {
        //这是先根据product.type.vm模板生成 product.type.vm.html
        String templatePath="E:\\JetBrains\\meijia\\meijia-parent\\meijia-product-parent\\product-service\\src\\main\\resources\\template\\product.type.vm";
        String targetPath="E:\\JetBrains\\meijia\\meijia-parent\\meijia-product-parent\\product-service\\src\\main\\resources\\template\\product.type.vm.html";
        List<ProductType> list = loadDataTree();
        Map<String,Object> map = new HashMap<>();
        map.put("model",list);
        map.put("templatePath",templatePath);
        map.put("targetPath",targetPath);
        templateClient.createStaticPage(map);

        //再根据home.vm生成home.html
        templatePath="E:\\JetBrains\\meijia\\meijia-parent\\meijia-product-parent\\product-service\\src\\main\\resources\\template\\home.vm";
        targetPath="E:\\JetBrains\\meijia\\meijia-web-parent\\ecommerce\\home.html";
        map = new HashMap<>();
        Map<String,Object> model = new HashMap<>();
        model.put("staticRoot","E:\\JetBrains\\meijia\\meijia-parent\\meijia-product-parent\\product-service\\src\\main\\resources\\");
        map.put("model",model);
        map.put("templatePath",templatePath);
        map.put("targetPath",targetPath);
        templateClient.createStaticPage(map);

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

    @Override
    public boolean save(ProductType entity) {
        boolean save = super.save(entity);
        sychornizedOperate();
        return save;
    }

    @Override
    public boolean removeById(Serializable id) {
        boolean remove = super.removeById(id);
        sychornizedOperate();
        return remove;
    }

    @Override
    public boolean updateById(ProductType entity) {
        boolean update = super.updateById(entity);
        sychornizedOperate();
        return update;
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        boolean byIds = super.removeByIds(idList);
        sychornizedOperate();
        return byIds;
    }

    /**
     * 增删改需要改变缓存
     */
    private void updateRedis(){
        List<ProductType> productTypes = loadDataTree();
        //转成json字符串缓存到redis中
        String jsonString = JSONArray.toJSONString(productTypes);
        redisClient.set("productTypes",jsonString);
    }

    /**
     * 这个是将redis和模板生成静态化页面结合了
     */
    private void sychornizedOperate(){
        updateRedis();
        createStaticHtml();
    }
}
