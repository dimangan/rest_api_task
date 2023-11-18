package com.example.rest_api;

import com.example.rest_api.db.Regions;
import com.example.rest_api.db.RegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/rest-api", produces = "application/json")
public class RegionController {
    @Autowired
    private RegionMapper regionMapper;

    @CachePut(cacheNames = {"regionsCache"}, key = "#regions.id", unless = "#regions.id == null ||"+
    "#regions.region_name == null || #regions.cut_region_name == null")
    @PostMapping(value = "/regions")
    public Regions createRegion(Regions regions){
        if(regions.getId() != null && regions.getRegion_name() != null && regions.getCut_region_name() != null)
            regionMapper.createRegion(regions);
        return regions;
    }

    @GetMapping("/regions")
    public List<Regions> getAllRegions(){
        return regionMapper.getAllRegions();
    }

    @Cacheable(cacheNames = {"regionsCache"}, key = "#id", unless = "#result == null")
    @GetMapping("/regions/{id}")
    public Regions getRegionById(@PathVariable("id") Long id){
        return regionMapper.getRegionById(id);
    }

    @CacheEvict(cacheNames = {"regionsCache"}, key = "#regions.id", condition = ("#regions.id != null &&"+
            "#regions.region_name != null && #regions.cut_region_name != null"))
    @PutMapping("/regions")
    public void updateRegionPut(Regions regions){
        if(regions.getId() != null && regions.getRegion_name() != null && regions.getCut_region_name() != null){
            regionMapper.updateRegionPut(regions);
        }
    }

    @CacheEvict(cacheNames = {"regionsCache"}, key = "#regions.id", condition = ("#regions.id != null &&" +
            "(#regions.region_name != null || #regions.cut_region_name != null)"))
    @PatchMapping("/regions")
    public void updateRegionPatch(Regions regions){
        if(regions.getId() != null){
            if(regions.getRegion_name() != null){
                if(regions.getCut_region_name() != null){
                    regionMapper.updateRegionPut(regions);
                }
                else{
                    regionMapper.updateRegionName(regions);
                }
            }
            else if(regions.getCut_region_name() != null){
                regionMapper.updateRegionCutName(regions);
            }
        }
    }

    @CacheEvict(cacheNames = {"regionsCache"}, key = "#id")
    @DeleteMapping("/regions")
    public void deleteRegion(Long id){
        regionMapper.deleteRegion(id);
    }
}
