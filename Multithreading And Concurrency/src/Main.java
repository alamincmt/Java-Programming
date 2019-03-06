public class Main {

    public static void main(String[] args) {
        System.out.println("This project demonstrates all about Multithreading and Concurrency - \n\n");


        Thread thread1 = new Thread(new DBWorkRunnable(), "Thread1Runnable");
        Thread thread2 = new Thread(new DBWorkRunnable(), "Thread2Runnable");
        System.out.println("Starting Runnable threads - ");
        thread1.start();
        thread2.start();
        System.out.println("Runnable Threads are started. \n\n");

        Thread thread3 = new MyThreadExample("Thread3Thread");
        Thread thread4 = new MyThreadExample("Thread4Thread");
        thread3.start();
        thread4.start();
        System.out.println("Threads of Thread class are started.");


        // This code sections is for understanding about join() method.
        Thread thread5 = new Thread(new DBWorkRunnable(), "thread5");
        Thread thread6 = new Thread(new DBWorkRunnable(), "thread6");
        Thread thread7 = new Thread(new DBWorkRunnable(), "thread7");

        thread5.start();

        //start thread6 after waiting for 2 seconds or if thread5 dead
        try {
            thread5.join(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread6.start();

        //start thread7 only when thread5 is dead
        try {
            thread5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread7.start();

        //let all threads finish execution before finishing main thread
        try {
            thread5.join();
            thread6.join();
            thread7.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("All threads are dead, exiting main thread");
    }
}
