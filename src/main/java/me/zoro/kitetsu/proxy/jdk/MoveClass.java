package me.zoro.kitetsu.proxy.jdk;

import lombok.extern.slf4j.Slf4j;

/**
 * @author luguanquan
 * @date 2020/5/17 11:08 上午
 */
@Slf4j
public class MoveClass implements MoveInterface {

	@Override
	public boolean fly() {
		log.info("Notice: now is invoke fly method in MoveClass");
		return true;
	}

	@Override
	public boolean run() {
		log.info("Notice: now is invoke run method in MoveClass");
		return true;
	}

	@Override
	public boolean swim() {
		log.info("Notice: now is invoke swim method in MoveClass");
		return true;
	}
}
