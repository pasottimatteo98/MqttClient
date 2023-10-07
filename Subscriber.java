
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import java.util.Date;

public class Subscriber {
//"tcp://smartcity-challenge.edu-al.unipmn.it:1883";
    public static final String BROKER_URL = "tcp://smartcity-challenge.edu-al.unipmn.it:1883";
    private Date date = new Date();
    private static final String USERNAME="pissir";
    private static final String PASSWORD="pissir2020";
    public static final String TOPICALL = "pissir/all/cmd";
    public static final String TOPIC = "pissir/20035991/1/cmd";
    //We have to generate a unique Client id.
    String clientId = Long.toString(date.getTime()) + "-sub";
    private MqttClient mqttClient;

    public Subscriber(String hosturl) {
	String hurl = BROKER_URL;
	if(hosturl != "") hurl = hosturl;
	System.out.println("Connecting to: "+hurl);

        try {
            mqttClient = new MqttClient(hurl, clientId);


        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void start() {
        try {

            mqttClient.setCallback(new SubscribeCallback());
            MqttConnectOptions connOpt=new MqttConnectOptions();
            connOpt.setCleanSession(false);
            connOpt.setUserName(USERNAME);
            connOpt.setPassword(PASSWORD.toCharArray());
            mqttClient.connect(connOpt);

            //Subscribe to all subtopics of home
            final String topicAll = TOPICALL;
            final String topic = TOPIC;
            mqttClient.subscribe(topicAll);
            mqttClient.subscribe(topic);
            System.out.println("Subscriber is now listening to "+topic);

        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String args[]) {
        final Subscriber subscriber;
	String hosturl = "";
	if(args.length >= 1) hosturl = "tcp://"+args[0]; 
	subscriber = new Subscriber(hosturl);
        subscriber.start();
	
	
    }

}
