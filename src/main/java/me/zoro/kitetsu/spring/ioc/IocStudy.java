package me.zoro.kitetsu.spring.ioc;

/**
 * @author luguanquan
 * @date 2020-03-12 06:48
 * <p>
 * IoC: Inverse of Control, 控制反转，何为控制返回 ？在传统开发中，我们在对象里要使用其他类的对象的方法，就要在内部创建一个对象，即
 * 通过 new 来创建一个对象并依赖它使用它，这就是自身控制。而通知反转就是如果需要一个对象，我们只需要声明需要的接口或类(@Autowired),然
 * 后IoC容器会根据我们的需求注入我们需要的对象，这个时候依赖的对象不再是自己 new, 不再自己控制，而是由 IoC 容器控制，这便是控制反转。
 * 所以，控制反转和依赖注入(Dependency Injection)是一个意思。Ioc/DI 简单描述就是一个对象如果声明需要什么对象，就有 IoC 容器创建、管
 * 理,new 出来，通过 setter 设置需要的对象。当然如果真在开发一个IoC，细节远不止这么简单。所以对于接口，类，在 Spring 的Ioc 中如果有多个
 * 子类，可能无法成功注入对象。类似 Dagger 以前是可以添加标识符的，在Spring 中这个得再了解下。
 * </p>
 */
public class IocStudy {
}
