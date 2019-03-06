public class DBWorkRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("DB related tasks are going to be done here - " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
            // Connect with database and initiate all the DB related tasks here.
            dbProcessing();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void dbProcessing() throws InterruptedException{
        Thread.sleep(8000);
    }
}
