package me.zoro.kitetsu.stream.kafka;

/**
 * @author luguanquan
 * @date 2020-03-19 22:29
 *
 * 参考 https://mp.weixin.qq.com/s/HJ1OPiXF8jbC-qdn1U4z7g
 * 首先，我们需要自己搭建一个 kafka,这里简单搭建一个包含两个实例的集群。这里直接放到一个 docker 容器中共同运行。
 * <p>
 * 安装
 * 1.创建本包一致的 zk-single-kafka-multiple.yml 文件(名字可任取)
 * 2. 执行 docker-compose -f zk-single-kafka-multiple.yml up
 * 3. 如需停止执行 docker-compose -f zk-single-kafka-multiple.yml down
 * </p>
 * <p>
 * 测试
 * 1.进入 kafka 命令行交互
 *   docker exec -ti docker_kafka1_1 bash
 * 2.列出所有 topic
 *   kafka-topics --describe --zookeeper zoo1:2181
 * 3.创建一个 topic
 * kafka-topics --create --topic test --partitions 3 --zookeeper zoo1:2181 --replication-factor 1 Created topic test.
 * 4.消费者订阅主体
 * kafka-console-consumer --bootstrap-server localhost:9092 --topic test send hello from console -producer
 * 5.生成者发送消息
 * kafka-console-producer --broker-list localhost:9092 --topic test
 * > send hello from console -producer
 * </p>
 */
public class KafkaProducer {
}
