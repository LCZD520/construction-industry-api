package com.industry;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成
 *
 * @author lc
 */
public class CodeGenerator {
    public static void main(String[] args) {
        // 自动生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 获取项目路径
        String projectPath = System.getProperty("user.dir");
        // 生成目录
        gc.setOutputDir(projectPath + "\\src\\main\\java");
        // 开发人员
        gc.setAuthor("lc");
        // 不打开输出目录
        gc.setOpen(false);
        // 设置ID类型:雪花算法自增
        gc.setIdType(IdType.AUTO);
        // 实体类命名方式 xxxEntity
        gc.setEntityName("%sDO");
        // 时间类型对应策略
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);
        // controller 命名方式
        gc.setControllerName("%sController");
        // service 命名方式
        gc.setServiceName("%sService");
        // service impl 命名方式
        gc.setServiceImplName("%sServiceImpl");
        // 是否覆盖已有文件（默认false）
        gc.setFileOverride(true);
        // 在xml中添加二级缓存配置
        gc.setEnableCache(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置对象
        DataSourceConfig dsc = new DataSourceConfig();
        // 驱动名称
        dsc.setDriverName("com.mysql.jdbc.Driver");
        // 驱动连接的URL
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/construction_industry_api?useSSL=false&useUnicode=true&characterEncoding=UTF-8&tinyInt1isBit=false");
        // 数据库连接用户名
        dsc.setUsername("root");
        // 数据库连接密码
        dsc.setPassword("123456");
        // 设置数据库类
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        // 父包名
        pc.setParent("com.industry");
        pc.setEntity("bean.entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");
        mpg.setPackageInfo(pc);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 数据库表映射到实体的命名策略 下划线转驼峰
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 数据库表字段映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        // 开启lombok模型
        strategy.setEntityLombokModel(true);
        // Boolean类型字段移除is前缀
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        // 生成字段注解
        strategy.setEntityTableFieldAnnotationEnable(true);
        // 生成 @RestController 控制器
        strategy.setRestControllerStyle(true);
        // 乐观锁属性名
//        strategy.setVersionFieldName("version");
        // 逻辑删除属性名称
//        strategy.setLogicDeleteFieldName("deleted");
        // 接口请求路径为中划线
        strategy.setControllerMappingHyphenStyle(true);
        // 自定义继承的Entity类全称，带包名
//        strategy.setSuperEntityClass("com.lc.entity.BaseEntity");
//        strategy.setSuperEntityColumns("gmt_create","gmt_modified","deleted","version");

        // 生成的表名
        strategy.setInclude(
                "t_user_role");
        // 去除表前缀
        strategy.setTablePrefix("t_");
        // 自动填充配置
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill creatorId = new TableFill("creator_id", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        TableFill regeneratorId = new TableFill("regenerator_id", FieldFill.INSERT_UPDATE);
        List<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(creatorId);
        tableFills.add(gmtModified);
        tableFills.add(regeneratorId);
        strategy.setTableFillList(tableFills);

        mpg.setStrategy(strategy);

        // 执行
        mpg.execute();
    }
}
