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
    }
}
