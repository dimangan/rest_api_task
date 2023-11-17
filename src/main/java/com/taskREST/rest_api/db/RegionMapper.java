package com.taskREST.rest_api.db;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RegionMapper {

    @Insert("insert into regions (id, region_name, cut_region_name) values (#{id}, #{region_name}, #{cut_region_name})")
    void createRegion(Regions regions);
    @Select("select * from regions where id = #{id}")
    Regions getRegionById(Long id);
    @Select("select * from regions")
    List<Regions> getAllRegions();
    @Update("update regions set region_name = #{region_name}, cut_region_name = #{cut_region_name} where id = #{id}")
    void updateRegionPut(Regions regions);
    @Update("update regions set region_name = #{region_name} where id = #{id} ")
    void updateRegionName(Regions regions);
    @Update("update regions set cut_region_name = #{cut_region_name} where id = #{id} ")
    void updateRegionCutName(Regions regions);
    @Delete("delete from regions where id = #{id}")
    void deleteRegion(Long id);
}
