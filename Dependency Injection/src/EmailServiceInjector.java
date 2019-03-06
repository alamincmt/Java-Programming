public class EmailServiceInjector implements MessageServiceInjector {
    @Override
    public Consumer getConsumer() {
        return new MyDIApplication(new EmailServiceImpl());
    }


    // Codes for setter dependency injection
    /*@Override
    public Consumer getConsumer() {
        MyDIApplicationWithSetter app = new MyDIApplicationWithSetter();
        app.setService(new EmailServiceImpl());
        return app;
    }*/
}
