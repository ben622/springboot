package com.ben.java.springboot.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.jms.*;

/**
 * 生产者
 */
public class JMSProducer {
    @Value("${spring.activemq.user}")
    private String user = "admin";
    @Value("${spring.activemq.password}")
    private String password = "";
    @Value("${spring.activemq.broker-url}")
    private String broker_url="tcp://47.100.201.99:61616";
    public void apply(String queue) {
        //1、连接工厂
        ConnectionFactory connectionFactory;
        //2、JMS链接,客户端和服务端之间的链接
        Connection connection = null;
        //3、JMS会话 建立在JMS连接上
        Session session;
        //4、JMS队列
        Destination destination;
        //5、JMS消息生产者
        MessageProducer messageProducer;

        connectionFactory = new ActiveMQConnectionFactory(user, password, broker_url);

        try {
            //通过连接工厂创建一个JMS链接
            connection = connectionFactory.createConnection();
            //开启JMS链接
            connection.start();
            /*
             * 通过JMS连接创建一个JMS会话
             *
             * createSession参数取值说明：
             *  第一个参数：为true表示启用事务
             *  第二个参数：消息的确认模式：
             *                 AUTO_ACKNOWLEDGE 自动签收
             *                 CLIENT_ACKNOWLEDGE 客户端自行调用
             *                 ACKNOWLEDGE 方法签收
             *                 DUPS_OK_ACKNOWLEDGE 不是必须签收
             * 消息可能会重复发送 在第二次重新传送消息的时候，消息头的JmsDelivered会被置为true标示当前消息已经传送过一次，
             * 客户端需要进行消息的重复处理控制。
             */
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            //通过会话创建一个消息队列
            destination = session.createQueue(queue);
            //通过会话创建一个消息生产者
            messageProducer = session.createProducer(destination);

            //使用消息生产者发送消息
            for (int i = 0; i < 1; i++) {
                TextMessage message = session.createTextMessage("this is "+queue+".timestamp:" + System.currentTimeMillis());
                messageProducer.send(message);

            }
            //提交会话
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
