package com.ben.java.springboot.activemq;

public class ActiveMQ {
    private static JMSConsumer consumer = new JMSConsumer();
    private static JMSProducer producer = new JMSProducer();


    public static void main(String args[]){
        /*for (int i = 0; i < 5; i++) {
            producer.apply("test_queue"+i);
        }*/

        //consumer.apply("test_queue0");

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    producer.apply("chatroom:visitor->zhangsan");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                consumer.apply("chatroom:visitor->zhangsan");
            }
        }).start();
    }
}
