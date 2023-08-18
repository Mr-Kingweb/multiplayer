package com.example.first_floor.generator.controller;


import com.example.first_floor.generator.domain.JsonResult;
import com.example.first_floor.generator.domain.PackingInfo;
import com.example.first_floor.generator.intercept.LoginCut;
import com.example.first_floor.generator.service.PackingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.jar.JarEntry;

/**
 * 包装控制器
 *
 * @author JJ_un
 * @date 2023/08/07
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pack")
public class PackingController {

    @Autowired
    private PackingInfoService packingInfoService;

    @PostMapping("/insertData")
    public JsonResult insertData(@RequestBody PackingInfo packingInfo) {
        System.out.println(packingInfo);
        return packingInfoService.insertAll(packingInfo);
    }

    @LoginCut
    @GetMapping("/getPackingInfo")
    public List<PackingInfo> getPackingInfo(@RequestParam String username) {
        return packingInfoService.getAll();
    }

    @LoginCut
    @GetMapping("/deletePack")
    public  JsonResult deletePack(@RequestParam String username,@RequestParam Integer id){
        return packingInfoService.deleteById(id);
    }
}
