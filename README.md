spring-mybatis.xml：整合spring与mybatis，并实现spring基本功能。
spring-mvc.xml：配置使用spring-mvc。
jdbc.properties:资源文件，用来保存数据库相关信息。
log4j.properties:配置日志文件。

classpath:java或者resources目录

加速构建maven项目：archetypeCatalog=internal

IE返回json变成下载文件的原因：
    IE不支持类型为：application/json;charset=UTF-8
    可将其转化为：  text/html;charset=UTF-8

SqlServer为手动导入的jar，IDEA在打包运行时，Module的依赖包需要手动导入

CREATE TABLE 测试表(
    唯一ID INT identity(1,1),
    用户名 NVARCHAR(255),
    密码 NVARCHAR(255)
)