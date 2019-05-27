package cn.itsource.meijia.controller;

import cn.itsource.meijia.service.IBrandService;
import cn.itsource.meijia.domain.Brand;
import cn.itsource.meijia.query.BrandQuery;
import cn.itsource.meijia.util.AjaxResult;
import cn.itsource.meijia.util.LetterUtil;
import cn.itsource.meijia.util.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class BrandController {
    @Autowired
    public IBrandService brandService;

    /**
    * 保存和修改公用的
    * @param brand  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/brand",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Brand brand){
        try {
            if(brand.getId()!=null){
                brand.setUpdateTime(new Date().getTime());
                String firstLetter = LetterUtil.getFirstLetter(brand.getEnglishName());
                brand.setFirstLetter(firstLetter.toUpperCase());
                brandService.updateById(brand);
            }else{
                String firstLetter = LetterUtil.getFirstLetter(brand.getEnglishName());
                brand.setFirstLetter(firstLetter.toUpperCase());
                brand.setCreateTime(new Date().getTime());
                brandService.save(brand);
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
    @RequestMapping(value="/brand/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            brandService.removeById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取(重写了mapper.xml,关联了自己)
    @RequestMapping(value = "/brand/{id}",method = RequestMethod.GET)
    public Brand get(@PathVariable("id") Long id)
    {
        return brandService.getById(id);
    }


    /**
    * 查看所有信息
    * @return
    */
    @RequestMapping(value = "/brand/list",method = RequestMethod.GET)
    public List<Brand> list(){
        return brandService.list();
    }


    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @RequestMapping(value = "/brand/page", method = RequestMethod.POST)
    public PageList<Brand> page(@RequestBody BrandQuery query) {
        return brandService.getByQuery(query);
    }

    /**
     * 删除多个对象信息
     * @param ids
     * @return
     */
    @RequestMapping(value="/brand/deleteMany",method=RequestMethod.DELETE)
    public AjaxResult deleteMany(@RequestBody String ids){
        String[] split = ids.split(",");
        List<String> idList = new ArrayList<String>();
        for (String id : split) {
            idList.add(id);
        }
        try {
            brandService.removeByIds(idList);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    /**
     * 根据类型编号加载所有品牌
     * @param productTypeId
     * @return
     */
    @GetMapping("/brand/loadByProductType")
    public Map<String,Object> loadByProductType(@RequestParam("productTypeId") Long productTypeId){
        return brandService.loadByPrductTypeId(productTypeId);
    }
}
