public class Waiter implements Runnable{

    private Message message;

    public Waiter(Message message){
        this.message = message;
    }


    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        synchronized (message){
            try {
                System.out.println(threadName + " waiting to get notified at time:"+System.currentTimeMillis());
                message.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(threadName + " waiter thread got notified at time:"+System.currentTimeMillis());
            //process the message now
            System.out.println(threadName + " processed: "+ message.getMessage());
        }
    }
}
