public class SMSServiceImpl implements MessageService {
    @Override
    public void sendMessage(String msg, String rec) {
        // logic to send message here
        System.out.println("SMS sent to " + rec + " with Message " + msg);
    }
}
