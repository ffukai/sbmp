package com.fjl.sbmp;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SbmpApplicationTests {

    @Test
    public void generateCode() {
        String packageName = "com.fjl.sbmp";
        boolean serviceNameStartWithI = true;//user -> UserService, 设置成true: user -> IUserService
        generateByTables(serviceNameStartWithI, packageName, "kettle_test");
    }

    private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        String projectPath = System.getProperty("user.dir");
//      1、初始化代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
//      2、配置 GlobalConfig
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(projectPath + "/src/main/java")
                .setOpen(false)
                .setActiveRecord(true) // 类似于建造者模式直接 实体类点 dao 的操作方法
                .setAuthor("范井龙")
                .setBaseResultMap(true);
        if (!serviceNameStartWithI) {
            globalConfig.setServiceName("%sService");
        }
        autoGenerator.setGlobalConfig(globalConfig);
//      3、配置数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=UTC")
                .setUsername("root")
                .setPassword("123456")
                .setDriverName("com.mysql.cj.jdbc.Driver");
        autoGenerator.setDataSource(dataSourceConfig);

//      4、包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.fjl.sbmp") //设置此项目包名
                .setController("controller")
                .setService("service")
                .setServiceImpl("service.impl")
                .setEntity("model")
                .setMapper("dao");
        autoGenerator.setPackageInfo(packageConfig);
//      5、配置模板
        TemplateConfig templateConfig = new TemplateConfig();
//        关闭默认 xml 生成，调整生成 至 根目录
        templateConfig.setXml(null);
        autoGenerator.setTemplate(templateConfig);

//      6、设置模板引擎
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());

//      7、注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        //设置生成模板
        FileOutConfig fileOutConfig = new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        };
        focList.add(fileOutConfig);
        cfg.setFileOutConfigList(focList);
        autoGenerator.setCfg(cfg);
//      8、策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityTableFieldAnnotationEnable(true)
                .setEntityLombokModel(true)
                .setEntityBuilderModel(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        // strategy.setInclude(new String[] { "user" }); // 需要生成的表
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        // 自定义实体父类
        // strategy.setSuperEntityClass("com.baomidou.demo.TestEntity");
        // 自定义实体，公共字段
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // 自定义 mapper 父类
        // strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
        // 自定义 service 父类
        // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
        // 自定义 service 实现类父类
        // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
        // 自定义 controller 父类
        // strategy.setSuperControllerClass("com.baomidou.demo.TestController");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuilderModel(true);
        autoGenerator.setStrategy(strategyConfig);

//      9、执行
        autoGenerator.execute();
    }

}
