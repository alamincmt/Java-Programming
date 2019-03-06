public class Main {

    public static void main(String[] args) {
        System.out.println("This project demonstrates about Dependency Injection - ");

        String msg = "Hi Al-Amin";
        String email = "alamin.fits@gmail.com";
        String phone = "+8801738001777";

        MessageServiceInjector injector = null;
        Consumer app = null;

        // send email
        injector = new EmailServiceInjector();
        app = injector.getConsumer();
        app.processMessages(msg, email);

        // send sms
        injector = new SMSServiceInjector();
        app = injector.getConsumer();
        app.processMessages(msg, phone);
    }
}
