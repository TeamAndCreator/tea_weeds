package com.tea.controller;

import com.tea.entity.Genus;
import com.tea.entity.Result;
import com.tea.entity.Spec;
import com.tea.repository.SpecRepository;
import com.tea.service.RoleService;
import com.tea.service.SpecService;
import com.tea.util.ResultUtil;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "spec")
public class SpecController {
    @Resource
    private SpecService specService;
    @Resource
    private RoleService roleService;
    @GetMapping(value = "getRegisterCode")
    public Result getRegisterCode(){
        try {
            return ResultUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(500,e.getMessage());
        }
    }
    //查询所有
    @GetMapping(value = "findAll")
    public  Result findAll(){
        try {
            List spec=specService.findALL();
            return ResultUtil.success(spec);

        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(500,e.getMessage());
        }
    }
    //保存
    @PostMapping(value = "save")
    public Result save(Spec spec){
        try {

            specService.save(spec);
            return ResultUtil.success();


        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(500, e.getMessage());

        }
    }
    //单个删除
    @DeleteMapping(value = "deleteById")
    public  Result deleteById(Integer id){
        try {
            specService.deleteById(id);
            return ResultUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(500,e.getMessage());
        }


    }
    //查询
    @GetMapping(value = "findById")
    public Result findById(Integer id){
        try {
            Spec spec=specService.findById(id);
            return ResultUtil.success(spec);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(500,e.getMessage());
        }

    }
    //    //批量删除
    @Transactional
    @DeleteMapping(value = "deleteByIds")
    public  Result deleteByIds(@ApiParam(name = "ids", value = "需删除属的id数组", required = true) @RequestParam(value ="ids")List<Integer> ids){
        try {
            specService.deleteByIds(ids);
            return ResultUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(500,e.getMessage());
        }

    }

}
