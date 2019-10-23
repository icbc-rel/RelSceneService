package com.icbc.rel.hefei.controller.card;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.icbc.rel.hefei.entity.card.TaskInfo;
import com.icbc.rel.hefei.service.card.TaskInfoService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LLF
 * @date 2019/10/12 - 17:43
 */
@RestController
public class TaskInfoController {
    @Autowired
    private TaskInfoService taskInfoService;

    /**
     * excel����
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/taskinfo/import")
    public Map taskInfoImport(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        Map rtnMap = new HashMap(16);
        try {
            if (file.isEmpty()) {
                rtnMap.put("code", 1);
            }
            int begin = file.getOriginalFilename().indexOf(".");
            int last = file.getOriginalFilename().length();
            //����ļ���׺��
            String suffix = file.getOriginalFilename().substring(begin, last);
            List<TaskInfo> taskInfos = null;
            HttpSession session = request.getSession();
            if (session.getAttribute("taskInfos") == null) {
                taskInfos = new ArrayList<>(16);
            } else {
                taskInfos = (List<TaskInfo>) session.getAttribute("taskInfos");
            }

            Sheet sheet = null;
            if (suffix.endsWith(".xlsx")) {
                XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
                sheet = workbook.getSheetAt(0);
            } else {
                HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
                sheet = workbook.getSheetAt(0);
            }
            //���һ��
            int lastRow = sheet.getLastRowNum();
            for (int i = lastRow; i > 0; i--) {
                if (sheet.getRow(i) == null) {
                    lastRow = lastRow - 1;
                } else if (sheet.getRow(i).getCell(0) == null) {
                    lastRow = lastRow - 1;
                } else {
                    break;
                }
            }
            DecimalFormat df = new DecimalFormat("0");
            TaskInfo taskInfo = null;
            System.out.println(lastRow);
            for (int i = 1; i <= lastRow; i++) {
                taskInfo = new TaskInfo();
                //����
                taskInfo.setCusName(sheet.getRow(i).getCell(0).getStringCellValue());
                //�绰
                taskInfo.setPhone(df.format(sheet.getRow(i).getCell(1).getNumericCellValue()));
                //����
                taskInfo.setBirthday(DateUtil.parse((sheet.getRow(i).getCell(2).getStringCellValue())));
                taskInfos.add(taskInfo);
            }
            rtnMap.put("code", 0);
            session.setAttribute("taskInfos", taskInfos);
        } catch (Exception e) {
            rtnMap.put("code", 2);
            e.printStackTrace();
        }
        return rtnMap;
    }

    /**
     * ɾ��
     * @param taskInfo
     * @return
     */
    @DeleteMapping("/taskinfo")
    public String delete(@RequestBody TaskInfo taskInfo) {
        Map rtnMap = new HashMap<>(16);
        try {
            taskInfoService.delete(taskInfo.getId());
            rtnMap.put("message", "ɾ���ɹ�");
            rtnMap.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            rtnMap.put("message", "ɾ��ʧ��");
            rtnMap.put("success", false);
        }
        return JSON.toJSONString(rtnMap);
    }

    /**
     * ��ӽ���
     *
     * @return
     */
    @GetMapping("/staff/addStaff")
    public ModelAndView addStaff() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/staff/addStaff");
        return mav;
    }

    /**
     * ��ӽ������ݱ���
     *
     * @param request
     * @return
     */
    @PostMapping("/taskinfo/add")
    public Map addTask(@RequestBody TaskInfo taskInfo, HttpServletRequest request) {
        List<TaskInfo> taskInfos = null;
        Map rtnMap = null;
        try {
            taskInfo.setBirthday(DateUtil.parse(DateUtil.format(taskInfo.getBirthday(), "yyyy-MM-dd")));
            HttpSession session = request.getSession();
            if (session.getAttribute("taskInfos") == null) {
                taskInfos = new ArrayList<>(16);
            } else {
                taskInfos = (List<TaskInfo>) session.getAttribute("taskInfos");
            }
            taskInfos.add(taskInfo);
            session.setAttribute("taskInfos", taskInfos);
            rtnMap = new HashMap(16);
            rtnMap.put("code", 0);
        } catch (Exception e) {
            rtnMap.put("code", 1);
            e.printStackTrace();
        }
        return rtnMap;
    }

    /**
     * ��ȡsession�е�����
     * @param request
     * @return
     */
    @GetMapping("/getSessionTask")
    public Map getSessionTask(HttpServletRequest request) {
        //��ʾ����
        Integer limit = Integer.parseInt(request.getParameter("limit"));
        //��ǰҳ
        Integer page = Integer.parseInt(request.getParameter("page"));
        //��ýǱ�
        Integer beginCount = (page - 1) * limit;
        Integer lastCount = page * limit;
        Map rtnMap = new HashMap(16);
        try {
            HttpSession session = request.getSession();
            List<TaskInfo> taskInfos = null;
            if (session.getAttribute("taskInfos") != null) {
                taskInfos = (List<TaskInfo>) session.getAttribute("taskInfos");
                List<TaskInfo> pageData = new ArrayList<>(16);
                if (taskInfos.size() > 0) {
                    for (int i = beginCount; i < lastCount; i++) {
                        if (i < taskInfos.size()) {
                            pageData.add(taskInfos.get(i));
                        }
                    }
                    rtnMap.put("count", taskInfos.size());
                    rtnMap.put("data", pageData);
                    //��ȫ�����ݵ�������Ϊcount����ǰ̨��һ����������
                }
            }
            rtnMap.put("code", 0);
        } catch (Exception e) {
            rtnMap.put("code", 1);
            e.printStackTrace();
        }
        return rtnMap;
    }

    /**
     * ģ��
     * @param response
     * @param type
     */
    @GetMapping("/taskinfo/template")
    public void getTemplate(HttpServletResponse response, String type,HttpServletRequest request)  {
        try {
            InputStream is = null;
            String fileName = null;
            String fileDir = request.getSession().getServletContext().getRealPath("/") +"WEB-INF/file/";
            fileDir = fileDir.replace("\\", "/");
            if ("0".equals(type)) {
                fileName = "birthdayTemplate.xlsx";
                is = new FileInputStream(fileDir+fileName);
            } else if ("1".equals(type)) {
                fileName = "festivalTemplate.xlsx";
                is = new FileInputStream(fileDir+fileName);
            }
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            ServletOutputStream outputStream = response.getOutputStream();
            int iAvail = is.available();
            byte[] bytes = new byte[iAvail];
            is.read(bytes);
            outputStream.write(bytes);
            is.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("taskinfo/{id}")
    public Map getCard(@PathVariable Integer id, HttpServletRequest request) {
        Map rtnMap = null;
        try {
            //��ʾ����
            Integer limit = Integer.parseInt(request.getParameter("limit"));
            //��ǰҳ
            Integer page = Integer.parseInt(request.getParameter("page"));
            List<TaskInfo> taskInfos = taskInfoService.selectByPageAndCardId(id, limit, page);
            Map<String, Integer> count = taskInfoService.seleceCountByCardId(id);
            rtnMap = new HashMap(16);
            rtnMap.put("count", count.get("count"));
            rtnMap.put("data", taskInfos);
            rtnMap.put("code", 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtnMap;
    }

    /**
     * ����ͼ
     *
     * @param findDate
     * @param id
     * @return
     */
    @PostMapping("/taskinfo/figure")
    public Map getFigure(String findDate, Integer id) {
        Map rtnMap = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<TaskInfo> taskInfos = taskInfoService.selectByCardId(id);
            DateTime viewTime = null;
            Integer[] figureData = {0, 0, 0, 0};
            for (TaskInfo taskInfo : taskInfos) {
                if (taskInfo.getViewTime() != null) {
                    //��ʽ��
                    viewTime = DateUtil.parse(format.format(taskInfo.getViewTime()));
                    //�õ�ʵ�ʷ���ʱ��
                    viewTime = DateUtil.offsetHour(viewTime, -13);
                    long betweenDay = DateUtil.between(viewTime, DateUtil.parse(findDate), DateUnit.DAY);
                    //������һ���
                    if (betweenDay > 0) {
                        continue;
                    }
                    //�õ����Ǽ��㷢��
                    int time = Integer.parseInt(String.format("%tH", viewTime));
                    //0�㷢��
                    if (time == 0) {
                        figureData[0] = figureData[0] + 1;
                        continue;
                    }
                    //8�㷢��
                    if (time == 8) {
                        figureData[1] = figureData[1] + 1;
                        continue;
                    }
                    if (time == 12) {
                        figureData[2] = figureData[2] + 1;
                        continue;
                    }
                    if (time == 20) {
                        figureData[3] = figureData[3] + 1;
                        continue;
                    }
                }
            }
            StringBuilder figureDataStringBuilder = new StringBuilder();
            figureDataStringBuilder.append("[");
            for (int i = 0; i < figureData.length; i++) {
                figureDataStringBuilder.append(figureData[i]);
                if (i < figureData.length - 1) {
                    figureDataStringBuilder.append(",");
                }
            }
            figureDataStringBuilder.append("]");
            rtnMap = new HashMap(1);
            figureDataStringBuilder.toString();
            rtnMap.put("data", figureDataStringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtnMap;
    }
}
