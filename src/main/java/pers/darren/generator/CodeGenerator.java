package pers.darren.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.ibatis.annotations.Mapper;

import static com.baomidou.mybatisplus.generator.config.OutputFile.xml;
import static com.baomidou.mybatisplus.generator.config.rules.DateType.TIME_PACK;
import static com.baomidou.mybatisplus.generator.config.rules.DbColumnType.INTEGER;
import static java.sql.Types.TINYINT;
import static java.util.Collections.singletonMap;

public class CodeGenerator {
    public static void main(String[] args) {
        // 1. 数据库连接信息
        String url = "jdbc:mysql://localhost:3306/mybatis-plus-demo?createDatabaseIfNotExist=true&characterEncoding=utf8&useUnicode=true&useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "abc123";
        String driverName = "com.mysql.cj.jdbc.Driver";

        // 2. 创建代码生成器
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> builder
                        .author("Darren Luo")
                        .outputDir("src/main/java")
                        .commentDate("yyyy-MM-dd HH:mm:ss")
                        .dateType(TIME_PACK))
                .packageConfig(builder -> builder
                        .parent("pers.darren")
                        .entity("entity")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .controller("controller")
                        .pathInfo(singletonMap(xml, "src/main/resources/mapper")))
                .dataSourceConfig(builder -> builder
                        .driverClassName(driverName)
                        .typeConvert(new MySqlTypeConvert())
                        .typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            if (metaInfo.getJdbcType().TYPE_CODE == TINYINT) {
                                return INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        }))
                .strategyConfig(builder -> builder
                        .addInclude("user", "role")
                        .entityBuilder()
                        .enableTableFieldAnnotation()
                        .enableFileOverride()
                        .mapperBuilder()
                        .enableBaseResultMap()
                        .enableBaseColumnList()
                        .mapperAnnotation(Mapper.class)
                        .enableFileOverride()
                        .serviceBuilder()
                        .enableFileOverride()
                        .controllerBuilder()
                        .enableRestStyle()
                        .enableFileOverride())
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
