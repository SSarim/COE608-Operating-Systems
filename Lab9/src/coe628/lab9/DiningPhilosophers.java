//Author: Sarim Shahwar
package coe628.lab9;

import java.util.concurrent.ThreadLocalRandom;

// The DiningPhilosophers class extends Thread to allow each philosopher to operate in a separate thread.
public class DiningPhilosophers extends Thread {

    int n; // Identifier for the philosopher (1-indexed for user-friendly output).
    Fork leftfork, rightfork; // The forks on either side of this philosopher.
    static int completed = 0; // Static counter to track how many philosophers have completed eating.

    // Constructor for DiningPhilosophers. Initializes a philosopher with their forks and an identifier.
    public DiningPhilosophers(int num, Fork left, Fork right) {
        this.n = num + 1; // Adjusting the index to be 1-indexed.
        this.leftfork = left; // Assigning the left fork to this philosopher.
        this.rightfork = right; // Assigning the right fork to this philosopher.
    }

    @Override
    public void run() {
        think(); // Philosopher thinks before attempting to eat.
        leftfork.get_fork(n); // Attempt to acquire the left fork.
        System.out.println("Fork " + leftfork.getId() + " taken by Philosopher " + n);
        rightfork.get_fork(n); // Attempt to acquire the right fork.
        System.out.println("Fork " + rightfork.getId() + " taken by Philosopher " + n);
        eat(); // Philosopher eats after acquiring both forks.
        System.out.println("Philosopher " + n + " completed his dinner");
        System.out.println("Philosopher " + n + " released fork " + leftfork.getId());
        leftfork.put_fork(); // Release the left fork after eating.
        System.out.println("Philosopher " + n + " released fork " + rightfork.getId());
        rightfork.put_fork(); // Release the right fork after eating.
        synchronized (DiningPhilosophers.class) {
            completed++; // Increment the counter for philosophers who have completed eating.
            System.out.println("Till now num of philosophers completed dinner are " + completed);
        }
        think(); // Philosopher thinks after eating.
    }

    // Method for philosopher to "think", includes a randomized sleep period.
    void think() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(100, 200));
        } catch (InterruptedException ex) {
            ex.printStackTrace(System.out);
        }
    }

    // Method for philosopher to "eat", includes a randomized sleep period.
    void eat() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(100, 200));
        } catch (InterruptedException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
