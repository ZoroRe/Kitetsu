package me.zoro.kitetsu.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author luguanquan
 * @date 2020-03-11 18:17
 * <p>
 * AOP，切面编程，主要使用 @Aspect 注解，一般设计上会自定义一个注解，然后这个注解结合通过 @Aspect 的类，指定切点为刚刚定义的注解，
 * 并选择在切点之前，之后，中间，返回，抛出时增加的代码，从而将核心业务和非核心业务区分出来。比如添加分布式锁，验证用户权限，日志等都可以
 * 通过切面实现。学会使用以下几个注解以及切点的表达式即可
 * @Aspect 切面实现类, @Pointcut 切点，参数为切点表达式,@Before、@After、@Around、@AfterReturning、@AfterThrowing 指定执行，并且
 * 注意被 @Aspect 表示的依然是个bean,需要用 @Component
 *</p>
 * <p>
 * 注意一个非常重要的点：比如常用的 aop 注解 @Async, 如果是在使用该注解的方法所在的类中内部自己调用，就不会生效，现在自定义的这个也一样，
 * 猜测原因是 aop 注解的切点，需要经过注入，然后有 Ioc容器 对其注入的类添加了切面代码，而调用自身的类，并不会加入切面代码。这个好好研究下
 * </p>
 */
@Aspect
@Component
@EnableAspectJAutoProxy
public class AspectStudy {

	/**
	 * 切点表达式，也可以直接在 @before, @after 等直接写表达式，但建议使用这中方式，一个表达式多处使用
	 * Pointcut 里的表达式要好好研究下，如何触发
	 */
//	@Pointcut("@annotation(me.zoro.kitetsu.spring.aop.AopStudy) execution(* *.*(..))")
	@Pointcut(value = "@within(me.zoro.kitetsu.spring.aop.AopStudy)|| @annotation(me.zoro.kitetsu.spring.aop.AopStudy)")
	public void pointcut() {
	}

	/**
	 * 在切点执行之前执行
	 */
	@Before("pointcut()")
	public void before() {
		System.out.println("AspectStudy.before()");
	}

	/**
	 * 在切点执行之后执行
	 * 可以取出来判断
	 */
	@After("pointcut()")
	public void after() {
		System.out.println("AspectStudy.after()");
	}

	/**
	 * 在切点执行周围执行，内部通过 ProceedingJoinPoint 来执行切点方法，注意这个 ProceedingJoinPoint 只能在 @Around 使用
	 * 如果在 @Before 或者 @After 等使用，会抛出异常
	 *
	 * @param pjp
	 */
	@Around("pointcut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		// 这是切点执行方法,可以在这上下添加其它代码
		System.out.println("AspectStudy.around() start");
		// 获取使用注解的方法签名
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		// 获取被注解上的方法
		Method targetMethod = signature.getMethod();
		// 如果注解有参数，通过这种方式获取
		AopStudy aopStudy = targetMethod.getAnnotation(AopStudy.class);
		String value = aopStudy.value();
		System.out.println("注解参数值 " + value);
		// 切点方法有返回值，通过这里调用然后最后要把返回值返回
		Object object = pjp.proceed();
		System.out.println("AspectStudy.around() end");
		return object;
	}

	/**
	 * 在切点返回值之后执行
	 */
	@AfterReturning("pointcut()")
	public void afterReturning() {
		System.out.println("AspectStudy.afterReturning()");
	}

	/**
	 * 在切点执行过程抛出异常后执行
	 */
	@AfterThrowing("pointcut()")
	public void afterThrowing() {
		System.out.println("AspectStudy.afterThrowing()");
	}
}
