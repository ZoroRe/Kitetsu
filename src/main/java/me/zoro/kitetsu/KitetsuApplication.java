package me.zoro.kitetsu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 陆冠铨
 * @date 2020-03-07
 * Kitetsu, 鬼彻,日本传说中的妖刀。其中海贼王中索隆持有一把三代鬼彻,五老星之一持有一把初代鬼彻。
 * 本项目主要汇集各种本来在学习、工作、面试准备工程汇聚的工具、尝试、算法等,取名鬼彻希望它如一把妖刀这样有强大力量。
 */
@EnableScheduling
@SpringBootApplication
public class KitetsuApplication {

	public static void main(String[] args) {
		SpringApplication.run(KitetsuApplication.class, args);
	}

}
