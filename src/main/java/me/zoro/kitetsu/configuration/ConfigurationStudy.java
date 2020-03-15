package me.zoro.kitetsu.configuration;

/**
 * @author luguanquan
 * @date 2020-03-15 08:54
 *
 * <p>
 * 从Spring3.0，@Configuration用于定义配置类，可替换xml配置文件，被注解的类内部包含有一个或多个被@Bean注解的方法，
 * 这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，
 * 并用于构建bean定义，初始化Spring容器。
 *
 * 注意：@Configuration注解的配置类有如下要求：
 *
 * @Configuration不可以是final类型；
 * @Configuration不可以是匿名类；
 * 嵌套的configuration必须是静态类。
 * </p>
 * <p>
 * 另外，以前很多 xml 的配置，现在使用 SprintBoot 默认已经有默认配置，除非需要指定修改。比如现在这个项目 application.yaml 都没有
 * redis 配置，因为连接的是默认的端口、localhost 等，所以不手动写也能正常使用。
 * </p>
 */
public class ConfigurationStudy {
}
