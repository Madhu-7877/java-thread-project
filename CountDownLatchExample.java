import java.util.concurrent.*;
import java.util.Random;

public class CountDownLatchExample {

    public static void executeTasks(int numWorkers) throws InterruptedException {
        // Initialize CountDownLatch with the number of worker threads
        CountDownLatch latch = new CountDownLatch(numWorkers);
        Random random = new Random();

        // Create and start worker threads
        for (int i = 1; i <= numWorkers; i++) {
            int workerId = i;
            new Thread(() -> {
                try {
                    System.out.println("Worker " + workerId + " is starting its task.");
                    // Simulate work by sleeping for a random time
                    Thread.sleep(random.nextInt(2000) + 1000);
                    System.out.println("Worker " + workerId + " has completed its task.");
                } catch (InterruptedException e) {
                    System.out.println("Worker " + workerId + " was interrupted.");
                } finally {
                    // Decrement the latch count
                    latch.countDown();
                }
            }).start();
        }

        // Main thread waits until all workers to finish
        latch.await();
        System.out.println("All tasks completed.");
    }

    public static void main(String[] args) throws InterruptedException {
        int numWorkers = 5; // Number of worker threads

        System.out.println("Starting tasks...");
        executeTasks(numWorkers); // Start and wait for tasks to complete
    }
}
