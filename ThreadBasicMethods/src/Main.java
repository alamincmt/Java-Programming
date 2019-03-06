public class Main {

    public static void main(String[] args) {
        System.out.println("\nwait, notify, notifyAll method implementation.....\n\n");

        Message message = new Message("Hi, Process My Message. ");
        Waiter waiter = new Waiter(message);
        new Thread(waiter, "waiter").start();

        Waiter waiter1 = new Waiter(message);
        new Thread(waiter1, "waiter1").start();

        Notifier notifier = new Notifier(message);
        new Thread(notifier, "notifier").start();
        System.out.println("All the threads are Started.\n");

    }
}
