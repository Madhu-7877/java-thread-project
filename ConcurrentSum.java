import java.util.*;
import java.util.concurrent.*;

public class ConcurrentSum {

    public static long calculateSum(List<Integer> numbers, int numThreads) throws InterruptedException, ExecutionException {
        // Create a thread pool
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        // List to hold Future objects for partial sums
        List<Future<Long>> futures = new ArrayList<>();

        // Divide the list into chunks
        int chunkSize = (int) Math.ceil(numbers.size() / (double) numThreads);
        for (int i = 0; i < numbers.size(); i += chunkSize) {
            int start = i;
            int end = Math.min(i + chunkSize, numbers.size());

            // Submit tasks to the executor
            Callable<Long> task = () -> {
                long sum = 0;
                for (int j = start; j < end; j++) {
                    sum += numbers.get(j);
                }
                return sum;
            };

            futures.add(executor.submit(task));
        }

        // Combine results from all threads
        long totalSum = 0;
        for (Future<Long> future : futures) {
            totalSum += future.get();
        }

        // Shut down the executor
        executor.shutdown();

        return totalSum;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
    	// Generate a list of integers from 1 to 1,000,000
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 1_000_000; i++) {
            numbers.add(i);
        }

        // Calculate sum using 4 threads
        long sum = calculateSum(numbers, 4);

        // Output the result
        System.out.println("Sum: " + sum);
    }
}