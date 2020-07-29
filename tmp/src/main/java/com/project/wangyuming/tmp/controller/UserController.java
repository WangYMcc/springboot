package com.project.wangyuming.tmp.controller;

import com.project.wangyuming.core.Result;
import com.project.wangyuming.core.ResultGenerator;
import com.project.wangyuming.tmp.model.User;
import com.project.wangyuming.tmp.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangyuming
 * @date 2020/05/16
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation("添加 User")
    @PostMapping
    public Result add(@RequestBody User user) {
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("删除 User")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("更新 User")
    @PutMapping
    public Result update(@RequestBody User user) {
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("通过 Id 查询 User 详情")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @ApiOperation("分页查询 User 列表")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation("分页查询 User 列表")
    @GetMapping("/getString")
    public String getString() {
        return "hello";
    }
}
