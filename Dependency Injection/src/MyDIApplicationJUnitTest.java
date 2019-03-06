import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyDIApplicationJUnitTest {

    private MessageServiceInjector injector;

    @Before
    public void setUp(){
        // mock the injector with anonymous class
        injector = new MessageServiceInjector() {
            @Override
            public Consumer getConsumer() {
                return new MyDIApplication(new MessageService() {
                    @Override
                    public void sendMessage(String msg, String rec) {
                        System.out.println("Mock Message Service implementation.");
                    }
                });
            }
        };
    }

    @Test
    public void test(){
        Consumer consumer = injector.getConsumer();
        consumer.processMessages("Hi ALAMIN", "al-amin@technobd.com");
    }

    @After
    public void tear(){
        injector = null;
    }
}
