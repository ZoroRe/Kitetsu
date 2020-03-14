package me.zoro.kitetsu.model;

import lombok.Data;

import java.util.Date;

/**
 * @author luguanquan
 * @date 2020-03-13 23:49
 * <p>
 * 以 DO 结尾的对象和数据库对象一一对应
 */
@Data
public class UserDO {
	private Long id;
	private String name;
	private Integer gender;
	private String birthday;
	private Date createTime;
	private Date updateTime;
}
