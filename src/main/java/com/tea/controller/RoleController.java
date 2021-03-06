package com.tea.controller;

import com.tea.entity.*;
import com.tea.service.PermissionService;
import com.tea.service.RoleService;
import com.tea.util.ResultUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    @PostMapping(value = "save")
   // @RequiresRoles(value = "admin")
    public Result save(Role role,@RequestParam List<Integer>  id_list){
        try {
            List<Permission> permissions = permissionService.findAll();
            Set<Permission> permissionSet = new HashSet<>();
            for (Integer id : id_list){
                for (Permission permission : permissions){
                    if (id.equals(permission.getId())){
                           permissionSet.add(permission);
                    }
                }
            }
            role.setPermissions(permissionSet);
            roleService.save(role);
            return ResultUtil.success();
        }catch (Exception e){
            return ResultUtil.error(500,e.getMessage());
        }
    }

    @GetMapping(value = "findAll")
//    @RequiresRoles(value = "admin")
    public Result findAll(){
        try {
            List<Role> roles = roleService.findAll();
            return ResultUtil.success(roles);
        }catch (Exception e){
            return ResultUtil.error(500,e.getMessage());
        }
    }
    //查询
    @GetMapping(value = "findById")
    public Result findById(Integer id, MultipartFile[] multipartFiles){
        try {
           Role role=roleService.findById(id);

            return ResultUtil.success(role);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(500,e.getMessage());
        }

    }
/*    @PostMapping(value = "save")
    public Result save(Role role){
        try {
           roleService.save(role);
            return ResultUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(500,e.getMessage());

        }
    }*/


}
