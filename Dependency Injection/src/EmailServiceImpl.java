public class EmailServiceImpl implements MessageService {
    @Override
    public void sendMessage(String msg, String rec) {
        // todo: logic to send email here.
        System.out.println("Email sent to " + rec + " with Message = " + msg);
    }
}
