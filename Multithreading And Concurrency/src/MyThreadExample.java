public class MyThreadExample extends Thread {

    public MyThreadExample(String name){
        super(name);
    }

    @Override
    public void run() {
        System.out.println("Thread example - START " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
            // some tasks to do
            downloadFileFromOnline();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void downloadFileFromOnline() throws InterruptedException{
        // You may download file here.
        Thread.sleep(6500);
    }
}
