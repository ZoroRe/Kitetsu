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
