package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.modular.system.entity.ProtocolAccountDetails;
import cn.hbis.erp.modular.system.mapper.ProtocolAccountDetailsMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 协议户明细表 服务实现类
 * </p>
 *
 *
 *
 */
@Service
public class ProtocolAccountDetailsService extends ServiceImpl<ProtocolAccountDetailsMapper, ProtocolAccountDetails> {

    @Resource
    private ProtocolAccountDetailsMapper protocolAccountDetailsMapper;

    /**
     * 查询协议户明细列表
     *
     *
     */
    public Page<Map<String, Object>> searchList(String mainSalesRegional, String beginTime, String endTime, String protocolYear, String steelMills) {
        Page page = PageFactory.defaultPage();
        return this.baseMapper.searchProtocolAccountDetailList(page, mainSalesRegional, beginTime, endTime, protocolYear, steelMills);
    }


    public List<ProtocolAccountDetails> excleIn(String filepath, String years) {//year 协议年份
        List list = new ArrayList<>();
        ProtocolAccountDetails pad = null;
        int rowNum= 0;
        try {
            InputStream is = new FileInputStream(filepath);//"h:/excleTest/bookPoi.xls"
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
            // 获取选项卡对象 第0个选项卡 , 因为我们这里只有一个选项卡，如果你每个选项卡的内容是一样，可以通过循环取出
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
            // 循环取出每行的值
            for ( rowNum = 2 ; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                pad = new ProtocolAccountDetails();
                //注意Poi读取的内容是有类型的，处理起来也jxl有所不同
                //获取excel表格中每一列的值赋到实体类的字段中
                pad.setProtocolYear(years);
                pad.setUploadTime(new Date());
                pad.setAccountName(hssfRow.getCell(1).getStringCellValue());
                String supplymode = hssfRow.getCell(2).getStringCellValue();
                if(supplymode.equals("直供")||supplymode.equals("三方")||supplymode.equals("自办公司")){
                    pad.setSupplyMode(supplymode);
                }
                String varietes = hssfRow.getCell(3).getStringCellValue();
                if(varietes.equals("热板")||varietes.equals("酸洗")||varietes.equals("镀锌")||varietes.equals("钢筋")){
                    pad.setVarieties(varietes);
                }else if(varietes.equals("彩涂")||varietes.equals("冷板")||varietes.equals("宽厚板")||varietes.equals("普线")){
                    pad.setVarieties(varietes);
                }else if(varietes.equals("品种线")||varietes.equals("优钢")||varietes.equals("锚杆钢")||varietes.equals("角钢")){
                    pad.setVarieties(varietes);
                }else if(varietes.equals("矿用钢")){
                    pad.setVarieties(varietes);
                }
                pad.setMainSalesRegional(hssfRow.getCell(4).getStringCellValue());
                HSSFCell numCell2 =  hssfRow.getCell(5,hssfRow.RETURN_BLANK_AS_NULL);
                String numcell2 = numCell2+"";
                if(numcell2 == null ||numcell2.equals("1.0")||numcell2.equals("null")){
                    pad.setAidedSalesRegionalOne("");
                }else{
                    pad.setAidedSalesRegionalOne(hssfRow.getCell(5).getStringCellValue());
                }

                HSSFCell numCell =  hssfRow.getCell(6,hssfRow.RETURN_BLANK_AS_NULL);
                String numcell = numCell+"";
                if(numcell == null ||numcell.equals("1.0")||numcell.equals("null")){
                    pad.setAidedSalesRegionalTwo("");
                }else{
                    pad.setAidedSalesRegionalTwo(hssfRow.getCell(6).getStringCellValue());
                }
                String mills = hssfRow.getCell(7).getStringCellValue()+"";
                if(mills.equals("唐钢")||mills.equals("邯钢")||mills.equals("宣钢")||mills.equals("承钢")||mills.equals("舞钢")){
                    pad.setSteelMills(mills);
                }
                pad.setAnnualAgreementVolume(String.valueOf( hssfRow.getCell(8).getNumericCellValue()));
                list.add(pad);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("第"+rowNum+"行出现错误");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
}

