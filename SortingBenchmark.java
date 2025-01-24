import java.util.*;

public class SortingBenchmark {

    // Generate a large list of random integers
    public static List<Integer> generateLargeList(int size) {
        Random random = new Random();
        List<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt());
        }
        return list;
    }

    // Sort the list using Collections.sort()
    public static List<Integer> sort(List<Integer> list) {
        Collections.sort(list);
        return list;
    }


    public static void main(String[] args) {
        int size = 1_000_000; // Size of the list

        // Generate a large list of random integers
        List<Integer> numbers = generateLargeList(size);

        // Measure sorting time using Collections.sort()
        long startTime = System.nanoTime();
        List<Integer> sortedNumbers = sort(new ArrayList<>(numbers));
        long endTime = System.nanoTime();
        System.out.println("Sorting took: " + (endTime - startTime) + " ns");

    }
}
