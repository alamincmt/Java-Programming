public class MyDIApplicationWithSetter implements Consumer {

    private MessageService service;

    public MyDIApplicationWithSetter(){}

    // setter dependency injection
    public void setService(MessageService service){
        this.service = service;
    }

    @Override
    public void processMessages(String msg, String rec) {
        // todo: msg validation, manipulation logic
        this.service.sendMessage(msg, rec);
    }
}
