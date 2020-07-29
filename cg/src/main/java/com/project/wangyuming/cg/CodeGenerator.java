package com.project.wangyuming.cg;

import com.google.common.base.CaseFormat;
import com.project.wangyuming.core.util.PrintUtil;
import com.project.wangyuming.core.util.StringUtil;
import freemarker.template.TemplateExceptionHandler;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.project.wangyuming.cg.ParameterReader.*;

/**
 * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
 */
public class CodeGenerator {

    private static final String classpath = CodeGenerator.class.getResource("").getPath();

    private static final String TEMPLATE_FILE_PATH = classpath.substring(0, classpath.length() - 27)
            + "/template";//模板位置

    private static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());//@date
    private static final String DEFAULT_FILE = "code-generator.properties";

    public static void generate() {
        PrintUtil.println("代码生成 ...");
        Map<String, String> params = ParameterReader.read(DEFAULT_FILE);
        PrintUtil.println(params.toString(), "配置参数：", StringUtil.EMPTY);

        genCode(params.get(TABLES_KEY), params);
    }

    /**
     * @see #generateModelAndMapper()
     */
    public static void generateModelAndMapper() {
        Map<String, String> params = ParameterReader.read(DEFAULT_FILE);
        PrintUtil.println(params.toString(), "配置参数：", StringUtil.EMPTY);

        genModelAndMapper(params.get(TABLES_KEY), params);
    }

    /**
     * @see #genControllers(String, Map)
     */
    public static void generateController() {
        Map<String, String> params = ParameterReader.read(DEFAULT_FILE);
        PrintUtil.println(params.toString(), "配置参数：", StringUtil.EMPTY);

        genControllers(params.get(TABLES_KEY), params);
    }

    /**
     * @see #genServices(String, Map)
     */
    public static void generateServiceAndImpl() {
        Map<String, String> params = ParameterReader.read(DEFAULT_FILE);
        PrintUtil.println(params.toString(), "配置参数：", StringUtil.EMPTY);

        genServices(params.get(TABLES_KEY), params);
    }

    /**
     * 通过数据表名称生成代码，Model 名称通过解析数据表名称获得，下划线转大驼峰的形式。
     * 如输入表名称 "t_user_detail" 将生成 TUserDetail、TUserDetailMapper、TUserDetailService ...
     *
     * @param tableNames 数据表名称...
     */
    private static void genCode(String tableNames, Map<String, String> params) {
        genCodeByCustomModelName(tableNames, params);
    }

    /**
     * 通过数据表名称，和自定义的 Model 名称生成代码
     * 如输入表名称 "t_user_detail" 和自定义的 Model 名称 "User" 将生成 User、UserMapper、UserService ...
     *
     * @param tableNames 数据表名称
     */
    private static void genCodeByCustomModelName(String tableNames, Map<String, String> params) {
        genModelAndMapper(tableNames, params);
        genServices(tableNames, params);
        genControllers(tableNames, params);
    }

    /**
     * 为指定 context 添加指定表名的配置
     * @param tableNames 指定表名，以逗号隔开
     * @param context 上下文
     */
    private static void addTablesConfig(String tableNames, Context context) {

        for(String tableName : tableNames.split(",")) {

            TableConfiguration tableConfiguration = new TableConfiguration(context);
            tableConfiguration.setTableName(tableName);
            tableConfiguration.setGeneratedKey(new GeneratedKey("id", "Mysql", true, null));
            context.addTableConfiguration(tableConfiguration);
        }
    }

    /**
     * 生成 model 及 mapper
     * @param tableNames 表名，逗号隔开
     * @param params 生成所需参数集合
     */
    private static void genModelAndMapper(String tableNames, Map<String, String> params) {
        PrintUtil.template("开始生成 Model 及 Mapper ...",() -> {
            PrintUtil.println(tableNames, "涉及库表：", StringUtil.EMPTY);
        });

        Context context = new Context(ModelType.FLAT);
        context.setId("Potato");
        context.setTargetRuntime("MyBatis3Simple");
        context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
        context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");

        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setConnectionURL(params.get(JDBC_URL_KEY));
        jdbcConnectionConfiguration.setUserId(params.get(JDBC_USERNAME_KEY));
        jdbcConnectionConfiguration.setPassword(params.get(JDBC_PASSWORD_KEY));
        jdbcConnectionConfiguration.setDriverClass(params.get(JDBC_DRIVER_CLASS_KEY));
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setConfigurationType("tk.mybatis.mapper.generator.MapperPlugin");
        pluginConfiguration.addProperty("mappers", params.get(MAPPER_IMPLEMENTS_INTERFACE_KEY));
        context.addPluginConfiguration(pluginConfiguration);

        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetProject(params.get(JAVA_PATH_KEY));
        javaModelGeneratorConfiguration.setTargetPackage(params.get(MODEL_PACKAGE_KEY));
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetProject(params.get(RESOURCES_PATH_KEY));
        sqlMapGeneratorConfiguration.setTargetPackage("mapper");
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetProject(params.get(JAVA_PATH_KEY));
        javaClientGeneratorConfiguration.setTargetPackage(params.get(MAPPER_PACKAGE_KEY));
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

        addTablesConfig(tableNames, context);

        List<String> warnings;
        MyBatisGenerator generator;
        try {
            Configuration config = new Configuration();
            config.addContext(context);
            config.validate();

            boolean overwrite = true;
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            warnings = new ArrayList<>();
            generator = new MyBatisGenerator(config, callback, warnings);
            generator.generate(null);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("生成Model和Mapper失败", e);
        }

        if (generator.getGeneratedJavaFiles().isEmpty() || generator.getGeneratedXmlFiles().isEmpty()) {
            throw new RuntimeException("生成Model和Mapper失败：" + warnings);
        }
        else {
            List<GeneratedJavaFile> files = generator.getGeneratedJavaFiles();
            PrintUtil.template("生成文件列表如下：", () -> {
                files.forEach(file -> {
                    PrintUtil.println(file.getFileName(), file.getTargetPackage() + ".", StringUtil.EMPTY);
                });
            });

        }
    }

    /**
     * 生成 Service 及 ServiceImpl
     * @param tableNames
     * @param params
     * @see #genService(String, Map)
     */
    private static void genServices(String tableNames, Map<String, String> params) {

        PrintUtil.template("开始生成 Service 及 ServiceImpl ...", () -> {
            for(String tableName : tableNames.split(",")) {
                genService(tableName, params);
            }
        });
    }

    /**
     * 生成 Service 及 ServiceImpl
     * @param tableName
     * @param params
     */
    private static void genService(String tableName, Map<String, String> params) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", params.get(AUTHOR_KEY));
            String modelNameUpperCamel = tableNameConvertUpperCamel(tableName);

            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName));
            data.put("basePackage", params.get(BASE_PACKAGE_KEY));
            data.put("servicePackage", params.get(SERVICE_PACKAGE_KEY));
            data.put("serviceImplPackage", params.get(SERVICE_IMPL_KEY));

            File file = new File(params.get(SERVICE_PACKAGE_PATH_KEY) + modelNameUpperCamel + "Service.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("service.ftl").process(data,
                    new FileWriter(file));

            PrintUtil.println(modelNameUpperCamel + "Service.java");

            File file1 = new File(params.get(SERVICE_IMPL_PATH_KEY) + modelNameUpperCamel + "ServiceImpl.java");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            cfg.getTemplate("service-impl.ftl").process(data,
                    new FileWriter(file1));
            PrintUtil.println(modelNameUpperCamel + "ServiceImpl.java");
        }
        catch (Exception e) {
            throw new RuntimeException("生成Service失败", e);
        }
    }

    private static void genControllers(String tableNames, Map<String, String> params) {

        PrintUtil.template("开始生成 Controller ...", () -> {
            for(String tableName : tableNames.split(",")) {
                genController(tableName, params);
            }
        });
    }

    private static void genController(String tableName, Map<String, String> params) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", params.get(AUTHOR_KEY));
            String modelNameUpperCamel = tableNameConvertUpperCamel(tableName);
            data.put("baseRequestMapping", modelNameConvertMappingPath(modelNameUpperCamel));
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
            data.put("basePackage", params.get(BASE_PACKAGE_KEY));
            data.put("controllerPackage", params.get(CONTROLLER_PACKAGE_KEY));

            File file = new File(params.get(CONTROLLER_PACKAGE_PATH_KEY) + modelNameUpperCamel + "Controller.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("controller-restful.ftl").process(data, new FileWriter(file));
            //cfg.getTemplate("controller.ftl").process(data, new FileWriter(file));
            //cfg.getTemplate("controller.ftl").process(data, new FileWriter(file));
            PrintUtil.println(modelNameUpperCamel + "Controller.java");
        }
        catch (Exception e) {
            throw new RuntimeException("生成 Controller 失败", e);
        }

    }

    private static freemarker.template.Configuration getConfiguration() throws IOException {

        freemarker.template.Configuration cfg
                = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);

        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    private static String tableNameConvertLowerCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
    }

    private static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

    }

    private static String tableNameConvertMappingPath(String tableName) {

        tableName = tableName.toLowerCase();//兼容使用大写的表名
        return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
    }

    private static String modelNameConvertMappingPath(String modelName) {
        String tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, modelName);
        return tableNameConvertMappingPath(tableName);
    }

}
