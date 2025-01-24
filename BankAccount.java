import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
	
    private double balance;
    private final ReentrantLock lock = new ReentrantLock();

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Method to deposit money
    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " deposited " + amount + ". Current balance: " + balance);
        } finally {
            lock.unlock();
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                System.out.println(Thread.currentThread().getName() + " withdrew " + amount + ". Current balance: " + balance);
            } else {
                System.out.println(Thread.currentThread().getName() + " tried to withdraw " + amount + ", but insufficient balance. Current balance: " + balance);
            }
        } finally {
            lock.unlock();
        }
    }

    // Method to get the current balance
    public double getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);

        // Define a task for deposit and withdraw operations
        Runnable task = () -> {
            account.deposit(500);
            account.withdraw(200);
        };

        // Create a thread pool with 5 threads
        ExecutorService executor = Executors.newFixedThreadPool(5);
        // Submit tasks to the thread pool
        for (int i = 0; i < 5; i++) {
            executor.submit(task);
        }

        // Shutdown the executor and wait for tasks to complete
        executor.shutdown();

        // Print the final balance
        System.out.println("Final balance: " + account.getBalance());
    }
}


