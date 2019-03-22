package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.core.util.BigDecimalUtil;
import cn.hbis.erp.core.util.ExportExcelUtil;
import cn.hbis.erp.modular.system.entity.ReportVarietySteelBean;
import cn.hbis.erp.modular.system.mapper.ReportTechnologyMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 子公司品种钢情况 服务实现类
 * </p>
 *
 *
 *
 */
@Service
public class ReportTechnologyService extends ServiceImpl<ReportTechnologyMapper, ReportVarietySteelBean> {

    @Resource
    private ReportTechnologyMapper reportTechnologyMapper;
    /**
     * @Title: varietySteelState
     * @Description: 1.子公司品种钢完成情况
     * @author: zhangry12988
     * @date: 2017-1-16 下午5:46:59
     * @param
     * @return
     * @return: List<ReportVarietySteelBen>
     */
    public List<ReportVarietySteelBean> subsidiaryVarietySteel(String startDate, String endDate) {
        List<ReportVarietySteelBean> list = reportTechnologyMapper.subsidiaryVarietySteel(startDate, endDate);
        return this.dealVarietySteel(list);
    }
    /**
     * @Title subsidiaryVarietySteelItem
     * @Description 1.子公司品种钢完成情况 明细
     * @param
     * @return List<ReportVarietySteelBean>
     * @throws
     */
    public List subsidiaryVarietySteelItemPage(String companyId, String startDate, String endDate) {
//        Page page = PageFactory.defaultPage();
        List list = reportTechnologyMapper.item_subsidiaryVarietySteel_count(companyId, startDate, endDate);
        List list1 = reportTechnologyMapper.item_subsidiaryVarietySteel_data(companyId, startDate, endDate);
        list.addAll(list1);
        return list;
    }

    /**
     * @Title: dealVarietySteel
     * @Description: 处理汇总和占比
     * @author: zhangry12988
     * @date: 2017-1-16 下午5:47:39
     * @param list
     * @return: List<ReportVarietySteelBen>
     */
    private List<ReportVarietySteelBean> dealVarietySteel(List<ReportVarietySteelBean> list) {
        List<ReportVarietySteelBean> returnList = new ArrayList<ReportVarietySteelBean>();
        if (null != list && list.size() > 0) {
            for (ReportVarietySteelBean bean : list) {
                if (null == bean.getCompanyName()) {
                    bean.setCompanyName("集团汇总");
                }
                bean.setTotalSteel(BigDecimalUtil.formart(bean.getTotalSteel()));
                bean.setTotalSteelVarieties(BigDecimalUtil.formart(bean.getTotalSteelVarieties()));
                bean.setScaleSteel(BigDecimalUtil
                        .topercent(BigDecimalUtil.div(bean.getTotalSteelVarieties(), bean.getTotalSteel(), 4)));
                bean.setFeaturesProducts(BigDecimalUtil.formart(bean.getFeaturesProducts()));
                bean.setHighProducts(BigDecimalUtil.formart(bean.getHighProducts()));
                bean.setSteelVarieties(BigDecimalUtil.formart(bean.getSteelVarieties()));
                returnList.add(bean);
            }
        }
        return returnList;
    }
    /**
     * @Title: exportVarietySteelState
     * @Description:导出 河钢集团各子分公司品种钢完成情况
     * @author: zhangry12988
     * @date: 2017-1-16 下午5:46:43
     * @param
     * @param request
     * @param response
     * @return: void
     */
    /*public void exportSubsidiaryVarietySteel(String startDate, String endDate, HttpServletRequest request,
                                             HttpServletResponse response) {
        try {
            *//**
             * 获取表头
             *//*
            String[] exportHeader = { "单位", "钢材总量(吨)", "品种钢总量(吨)", "品种钢比例(%)", "特色战略产品(吨)", "高端产品(吨)", "一般品种钢(吨)" };
            String fileName = "子公司品种钢情况";
            //构建excel名称
            ExportExcelUtil.setFileName(fileName, request, response);
            OutputStream out = new BufferedOutputStream(response.getOutputStream());
            //
            List<ReportVarietySteelBean> exportData = this.subsidiaryVarietySteel(startDate, endDate);
            //开始导出
            this.createExportExcels(fileName, exportData, exportHeader, response, out, startDate, endDate);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    /**
     * @Title: createExportExcels
     * @Description:  创建excel并填充数据
     * @author: zhangry12988
     * @date: 2017-1-16 下午5:47:15
     * @param fileName
     * @param exportData
     * @param exportHeader
     * @param response
     * @param out
     * @param
     * @return: void
     */
    /*private void createExportExcels(String fileName, List<ReportVarietySteelBean> exportData, String[] exportHeader,
                                    HttpServletResponse response, OutputStream out, String startDate, String endDate) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(fileName);
        // 标题
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        // 创建格式
        HSSFCellStyle titleStyle = ExcelExportUtil.getTitleCellStyle(workbook);
        HSSFCellStyle headStyle = ExcelExportUtil.getBoldCellStyle(workbook);
        HSSFCellStyle nomalStyle = ExcelExportUtil.getNomalCellStyle(workbook);

        ExcelExportUtil.fillCell(row, 0, titleStyle, fileName);
        // 合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, exportHeader.length - 1));
        row = sheet.createRow(2);
        *//**
         * 查询条件
         *//*
        String queryDate = "发货日期:";
        if (null != query.getQueryDate() && !"".equals(query.getQueryDate())) {
            ExcelExportUtil.fillCell(row, 0, nomalStyle, queryDate + query.getQueryDate());
        } else {
            ExcelExportUtil.fillCell(row, 0, nomalStyle, queryDate + DateUtil.getNowMonth());
        }
        *//**
         * 数据表头“单位.....品种钢(吨)”
         *//*
        row = sheet.createRow(3);
        ExportExcelUtil.setFileHeader(exportHeader, sheet, row, cell, headStyle);
        *//**
         * 填充数据
         *//*
        if (exportData != null && !exportData.isEmpty()) {
            for (int i = 0; i < exportData.size(); i++) {
                row = sheet.createRow(i + 4);
                ReportVarietySteelBean item = exportData.get(i);
                if (null != item.getCompanyName() && !"".equals(item.getCompanyName())) {
                    ExcelExportUtil.fillCell(row, 0, nomalStyle, StringUtil.isNotEmpty(item.getCompanyName()));
                }
                if (null != item.getVarietyClass() && !"".equals(item.getVarietyClass())) {
                    ExcelExportUtil.fillCell(row, 0, nomalStyle, StringUtil.isNotEmpty(item.getVarietyClass()));
                }
                ExcelExportUtil.fillCell(row, 1, nomalStyle, item.getTotalSteel().doubleValue());
                ExcelExportUtil.fillCell(row, 2, nomalStyle, item.getTotalSteelVarieties().doubleValue());
                ExcelExportUtil.fillCell(row, 3, nomalStyle, StringUtil.isNotEmpty(item.getScaleSteel()));
                ExcelExportUtil.fillCell(row, 4, nomalStyle, item.getFeaturesProducts().doubleValue());
                ExcelExportUtil.fillCell(row, 5, nomalStyle, item.getHighProducts().doubleValue());
                ExcelExportUtil.fillCell(row, 6, nomalStyle, item.getSteelVarieties().doubleValue());
            }
        } else {
            row = sheet.createRow(4);
            ExcelExportUtil.fillCell(row, 0, nomalStyle, "无记录！");
            // 合并单元格
            sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, exportHeader.length - 1));
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
