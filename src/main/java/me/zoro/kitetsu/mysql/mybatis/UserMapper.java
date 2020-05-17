package me.zoro.kitetsu.mysql.mybatis;

import me.zoro.kitetsu.model.UserDO;
import org.apache.ibatis.annotations.*;

/**
 * @author luguanquan
 * @date 2020-03-13 23:52
 *
 * <p>
 * mybatis3 doc:https://mybatis.org/mybatis-3/zh/java-api.html
 * </p>
 *<p>
 * 通过去掉很多注释，可以发现 mybatis 使用 mybatis-spring-boot-starter 默认配置有了，只要数据库连接正确，直接开箱即用了
 *</p>
 * <p>
 *  这个项目在本机上通过 docker 安装了一个 mysql,端口和配置一样，如果需要通过 docker 安装 mysql 测试，可以按照如下步骤安装。
 *  0. 通过 docker 拉去 mysql 镜像
 *    docker pull mysql
 *  1.创建数据库保存文件的目录,在 docker 安装mysql 映射过来，我选择在Documents 下创建，先进入Documents 后执行
 *    mkdir -p mysql/data
 *  2.启动镜像并关联目录(本机端口30301映射到docker 3306， 第2步创建的目录映射到docker里mysql中，这里用户名 root,密码 LGQ@19920408)
 *   docker run -p 30301:3306 --name mysql  -v ~/Documents/mysql/data:/var/lib/mysql -e
 *   MYSQL_ROOT_PASSWORD=LGQ@19920408  -d mysql
 * </p>
 */
@Mapper
public interface UserMapper {

	/**
	 * 插入一个新用户
	 * @param user
	 */
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	@Insert("INSERT INTO user(name, birthday, gender) VALUES(#{name}, #{birthday}, #{gender})")
	void insert(UserDO user);

	/**
	 * 根据 id 获取用户信息
	 * @param id
	 * @return
	 */
	@Select("SELECT id, name, birthday, gender FROM user WHERE id = #{id}")
	UserDO findById(@Param("id") Long id);

	/**
	 * 根据 id 删除一个用户
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM user WHERE id = #{id}")
	Integer deleteById(@Param("id") Long id);
}
