package com.example.pandemic.controller;

import com.example.pandemic.entity.Cases;
import com.example.pandemic.mapper.CaseMapper;
import com.example.pandemic.util.Result;
import com.example.pandemic.util.excel.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author codejie
 * @since 2022-05-06
 */

@Controller
@CrossOrigin /**解决跨域问题*/
@RequestMapping(value = "/excel")
public class ExcelController {
    @Autowired
    private CaseMapper caseMapper;
//后端IP
    private static final String IP="http://localhost";
//    端口
    @Value("${server.port}")
    private String port;

    ExcelUtils excelUtils=new ExcelUtils();

    //导出excel数据
    @RequestMapping(value = "/export")
    @ResponseBody
    public Result<List<Cases>> getCase(HttpServletResponse response)  {
        List<Cases> allCases =caseMapper.allcases();
        if(allCases !=null){
            try {
                excelUtils.excelExport("病例表", allCases,response);
            }catch (IOException e){
                e.printStackTrace();
            }
            return  Result.success(200,"导出数据成功");
        }else{
            //如果为空，则不导出
            return Result.error(204,"无资源可导出");
        }
    }

}
