import java.util.concurrent.*;

public class SimpleScheduler {

    public static void main(String[] args) {
        // Create a ScheduledExecutorService with a single thread
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Define the task to be executed
        Runnable task = () -> System.out.println("Task executed at: " + System.currentTimeMillis());

        // Schedule the task to run at a fixed rate of 1 second
        ScheduledFuture<?> scheduledFuture = scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);

        // Stop the scheduler after 5 seconds
        scheduler.schedule(() -> {
            System.out.println("Stopping scheduler...");
            scheduledFuture.cancel(false); // Cancel the fixed-rate task
            scheduler.shutdown(); // Shut down the scheduler
        }, 4, TimeUnit.SECONDS);
    }
}
