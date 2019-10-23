package com.icbc.rel.hefei.controller.card;

import com.alibaba.fastjson.JSON;
import com.icbc.rel.hefei.entity.card.TaskInfo;
import com.icbc.rel.hefei.service.card.TaskInfoService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author LLF
 * @date 2019/10/12 - 17:43
 */
@RestController
public class TaskInfoController {
    @Autowired
    private TaskInfoService taskInfoService;

    @PostMapping("/taskinfo/import")
    public String taskInfoImport(MultipartFile multipartFile) {
        Map rtnMap = new HashMap(16);
        if (multipartFile.isEmpty()) {
            rtnMap.put("message", "上传文件为空");
            rtnMap.put("success", false);
        }
        int begin = multipartFile.getOriginalFilename().indexOf(".");
        int last = multipartFile.getOriginalFilename().length();
        //获得文件后缀名
        String suffix = multipartFile.getOriginalFilename().substring(begin, last);
        if (!suffix.endsWith(".xlsx") && !suffix.endsWith(".xls")) {
            rtnMap.put("message", "上传失败，请上传xls、xlsx格式文件");
            rtnMap.put("success", false);
        }
        Sheet sheet = null;
        try {
            if (suffix.endsWith(".xlsx")) {
                XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
                sheet = workbook.getSheetAt(0);
            } else {
                HSSFWorkbook workbook = new HSSFWorkbook(multipartFile.getInputStream());
                sheet = workbook.getSheetAt(0);
            }
            //最后一行
            int lastRow = sheet.getLastRowNum();
            TaskInfo taskInfo = null;
            List<TaskInfo> taskInfos = new ArrayList<>(16);
            for (int i = 1; i <= lastRow; i++) {
                taskInfo = new TaskInfo();
                //姓名
                taskInfo.setCusName(sheet.getRow(i).getCell(0).getStringCellValue());
                //电话
                taskInfo.setPhone(sheet.getRow(i).getCell(1).getStringCellValue());
                //生日
                System.out.println(sheet.getRow(i).getCell(2).getStringCellValue());
                taskInfo.setTaskTime(stringToDateFormat(sheet.getRow(i).getCell(2).getStringCellValue()));
                taskInfos.add(taskInfo);
            }
            rtnMap.put("message", "上传成功");
            rtnMap.put("success", true);
        } catch (
                IOException e) {
            rtnMap.put("message", "上传失败，请按照模板的格式上传");
            rtnMap.put("success", false);
            e.printStackTrace();
        }
        return JSON.toJSONString(rtnMap);
    }

    @DeleteMapping("/taskinfo")
    public String delete(@RequestBody TaskInfo taskInfo) {
        Map rtnMap = new HashMap<>(16);
        try {
            taskInfoService.delete(taskInfo.getId());
            rtnMap.put("message", "删除成功");
            rtnMap.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            rtnMap.put("message", "删除失败");
            rtnMap.put("success", false);
        }
        return JSON.toJSONString(rtnMap);
    }

    /**
     * 字符串格式化为日期"yyyy-MM-dd
     *
     * @param date
     * @return
     */
    private Date stringToDateFormat(String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
