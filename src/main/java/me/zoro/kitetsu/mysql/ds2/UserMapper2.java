package me.zoro.kitetsu.mysql.ds2;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author luguanquan
 * @date 2020-03-19 22:50
 */
@Mapper
public interface UserMapper2 {

	@Select("SELECT COUNT(*) FROM test_user")
	Long count();
}
