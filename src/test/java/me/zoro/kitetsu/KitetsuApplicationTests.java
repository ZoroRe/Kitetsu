package me.zoro.kitetsu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p>
 * 关于 junit5,推荐学习 https://www.ibm.com/developerworks/cn/java/j-introducing-junit5-part2-vintage-jupiter-extension-model/index.html
 * </p>
 * <p>
 * junit5 官方文档： https://junit.org/junit5/docs/current/user-guide/
 * </p>
 *
 */
// 这个设置可以模拟启动 web 服务，能写接口测试
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DisplayName("Kitetsu 鬼彻 利刃出鞘")
class KitetsuApplicationTests {

	@Test
	void contextLoads() {
	}

}
