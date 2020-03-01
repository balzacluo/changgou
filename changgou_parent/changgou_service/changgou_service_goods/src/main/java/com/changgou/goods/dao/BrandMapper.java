package com.changgou.goods.dao;

import com.changgou.goods.pojo.Brand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BrandMapper extends Mapper<Brand> {

    @Select("select * from tb_brand where id in(" +
            "select brand_id from tb_category_brand where category_id in(" +
            "select id from tb_category where name = #{categoryName}))")
    public List<Brand> findListByCategoryName(@Param("categoryName") String categoryName);

    @Select("select * from tb_brand b where EXISTS (SELECT 1 from tb_category_brand c where b.id = c.brand_id  and c.category_id =  #{categoryId})")
    public List<Brand> findListByCategoryId(@Param("categoryId") String categoryId);
}
