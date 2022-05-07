package com.example.pandemic.controller;

import com.example.pandemic.entity.Cases;
import com.example.pandemic.service.CaseService;
import com.example.pandemic.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author codejie
 * @since 2022-04-27
 */

@RestController
public class CaseController {
    @Autowired
    private CaseService caseService;
    //所有病例数据
    @PostMapping(value = "/getallcase")
    public Result<List<Cases>> getAllCase(){
        List<Cases> allCases =caseService.getAllCase();
        if(allCases !=null){
            return  Result.success(200,"post请求成功", allCases);
        }else{
            return Result.error(500,"post请求失败", allCases);
        }
    }
    // 根据cid获取病例信息--成功
    @PostMapping(value = "/getcasebycid")
    public Result<Cases> getCaseByCid(int cid){
       Cases cases = (Cases) caseService.FindCaseByCid(cid);
       if(cases!=null)
       {
           return Result.success(200,"post请求成功",cases);
       }else{
           return Result.error(500,"post请求失败");
       }
    }
    //    新增确诊病例--成功
    @PostMapping(value = "/addCase")
    public Result<?>addCase(Cases cases){
        // cases为前端表单对象，表单项字段与实体类一致
        caseService.AddCase(cases);
        // for debug
        //System.out.print(cases.getAge());
        return Result.success(200,"添加成功");
    }
    //删除指定病例
    @PostMapping(value = "/deleCase")
    public Result<?>deleCase(long cid){
        caseService.DeleCaseByCid(cid);
        return Result.success(200,"删除成功");
    }

    //
    @PostMapping(value = "/updateCase")
    public Result<?>updateCase(Cases cases){
        caseService.UpdateCase(cases);
        return Result.success(200,"更新成功");
    }
}
