package com.ben.java.springboot.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.jms.*;

/**
 * 消费者
 */
public class JMSConsumer {
    @Value("${spring.activemq.user}")
    private String user = "admin";
    @Value("${spring.activemq.password}")
    private String password = "admin";
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
        MessageConsumer messageConsumer;

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
            //通过会话创建一个消息消费者
            messageConsumer = session.createConsumer(destination);

            //使用消息消费者消费队列中的消息
            System.out.println("Accept Msg-----------------------------------------------------");
            while (true){
                TextMessage textMessage = (TextMessage) messageConsumer.receive(1000 * 10);
                if (textMessage != null) {
                    System.out.println("Accept Msg："+textMessage.getText());
                }else{
                    //break;

                }
            }
            //System.out.println("Accept Msg-----------------------------------------------------");
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
