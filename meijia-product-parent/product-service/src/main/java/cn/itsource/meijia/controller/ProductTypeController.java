package cn.itsource.meijia.controller;

import cn.itsource.meijia.service.IProductService;
import cn.itsource.meijia.service.IProductTypeService;
import cn.itsource.meijia.domain.ProductType;
import cn.itsource.meijia.query.ProductTypeQuery;
import cn.itsource.meijia.util.AjaxResult;
import cn.itsource.meijia.util.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class ProductTypeController {
    @Autowired
    public IProductTypeService productTypeService;
    @Autowired
    public IProductService productService;

    /**
    * 保存和修改公用的
    * @param productType  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/productType",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody ProductType productType){
        try {
            if(productType.getId()!=null){
                productType.setUpdateTime(new Date().getTime());
                productTypeService.updateById(productType);
            }else{
                productType.setCreateTime(new Date().getTime());
                productTypeService.save(productType);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @RequestMapping(value="/productType/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            productTypeService.removeById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取
    @RequestMapping(value = "/productType/{id}",method = RequestMethod.GET)
    public ProductType get(@PathVariable("id") Long id)
    {
        return productTypeService.getById(id);
    }


    /**
    * 查看所有信息
    * @return
    */
    @RequestMapping(value = "/productType/list",method = RequestMethod.GET)
    public List<ProductType> list(){
        return productTypeService.list();
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/productType/page",method = RequestMethod.POST)
    public PageList<ProductType> page(@RequestBody ProductTypeQuery query)
    {
        IPage<ProductType> productTypeIPage = productTypeService.page(new Page<>(query.getPage(), query.getSize()));
        return new PageList<>(productTypeIPage.getTotal(),productTypeIPage.getRecords());
    }

    /**
     * 加载菜单树
     * @return
     */
    @RequestMapping(value = "productType/tree", method = RequestMethod.GET)
    public List<ProductType> loadTreeDate(){
        return productTypeService.loadTreeData();
    }

    /**
     * 生成静态Html文件
     * @return
     */
    @GetMapping("/page")
    public AjaxResult createStaticHtml(){
        try {
            productTypeService.createStaticHtml();
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("生成对象失败！"+e.getMessage());
        }
    }

    /**
     * 删除多个对象信息
     * @param ids
     * @return
     */
    @RequestMapping(value="/productType/deleteMany",method=RequestMethod.DELETE)
    public AjaxResult deleteMany(@RequestBody String ids){
        System.out.println(ids);
        String[] split = ids.split(",");
        List<String> idList = new ArrayList<String>();
        for (String id : split) {
            idList.add(id);
        }
        try {
            productTypeService.removeByIds(idList);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    /**
     * 加载类型面包屑
     * @param productTypeId
     * @return
     */
    @GetMapping("/productType/crumb")
    public List<Map<String,Object>> loadCrumbs(Long productTypeId){
        return productService.loadCrumbs(productTypeId);
    }

}
