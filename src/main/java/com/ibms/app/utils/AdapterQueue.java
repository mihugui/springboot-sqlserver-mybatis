package com.ibms.app.utils;

import com.ibms.app.service.DataSave;
import com.tibco.tibjms.TibjmsQueueConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.*;

/**
 * @ClassNmae AdapterTopic
 * @Description TODO
 * @Author PWT
 * @Date 2018/8/30 9:10
 * @Version 1.0
 **/
@Component
public class AdapterQueue {

    private String serverUrl = "tcp://192.169.110.201:7222";
    private String userName = "admin";
    private String password  = "admin";
    private String queueName = "queue.mj.data";

    private Connection connection ;
    private Session session ;
    private Destination queues ;
    private MessageConsumer msgConsumer;

    @Autowired
    private DataSave dataSave;

    private static AdapterQueue sender = new AdapterQueue();

    public static AdapterQueue getInstance(){
        return sender;
    }

    @PostConstruct //通过@PostConstruct实现初始化bean之前进行的操作
    public void init() {
        sender = this;
        sender.dataSave = this.dataSave;
        // 初使化时将已静态化的testService实例化
    }


    public void createQueue(){
        try
        {
            ConnectionFactory factory = new TibjmsQueueConnectionFactory(serverUrl);
            connection = factory.createConnection(userName,password);
            connection.start();
            /* create the session */
            session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);

            queues = session.createQueue(queueName);
            /* create the producer */
            msgConsumer = session.createConsumer(queues);

            System.out.println("queue接受开始");
            msgConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message msg) {

                    try {
                        TextMessage message = (TextMessage)msg ;
                        String reciveMessage = message.getText();
                        System.out.println("收到消息:"+message.getText());
                        sender.dataSave.DataAnalysis(message.getText());
                        session.commit();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public void setServerUrl(String serverUrl)
    {
        this.serverUrl = serverUrl;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setQueueName(String queuename)
    {
        this.queueName = queuename;
    }

}
