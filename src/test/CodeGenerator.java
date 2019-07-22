import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;

public class CodeGenerator {
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(new File("").getAbsolutePath() + "/src/main/java");
        gc.setFileOverride(false);
        gc.setOpen(false);
        gc.setEnableCache(false);
        gc.setAuthor("jiarsi");
        gc.setSwagger2(true);
        gc.setActiveRecord(true);
        //resultmap结果集映射
        gc.setBaseResultMap(false);
        gc.setBaseColumnList(false);
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sWrapper");
        gc.setServiceImplName("%sService");
        gc.setControllerName("%sController");
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.ORACLE);
        dsc.setDriverName("oracle.jdbc.driver.OracleDriver");
        //正式服务器
//        dsc.setUrl("jdbc:oracle:thin:@18.0.126.1:1521:coredb1");
//        dsc.setUsername("hbistc_ol");
//        dsc.setPassword("hbistc_ol2012");
        //测试服务器
        dsc.setUrl("jdbc:oracle:thin:@221.192.142.22:1521:orcl");
        dsc.setUsername("hbistc_test");
        dsc.setPassword("hbistc_test");
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("cn.hbis.erp.modular.system");
        pc.setEntity("entity");
        pc.setController("controller");
        pc.setXml("mapper.mapping");
        pc.setMapper("mapper");
        pc.setServiceImpl("service");
        pc.setService("warpper");
        // 策略配置 数据库表配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setSkipView(true);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setInclude("CRM_RESOURCE_ALLOCATION");//在这里改表名
        strategy.setEntityColumnConstant(false);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setEntityTableFieldAnnotationEnable(true);
        new AutoGenerator()//// 代码生成器
                .setGlobalConfig(gc)
                .setDataSource(dsc)
                .setPackageInfo(pc)
                .setStrategy(strategy)
                .execute();
    }
}
