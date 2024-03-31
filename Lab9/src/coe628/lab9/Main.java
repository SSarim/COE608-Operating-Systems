//Author: Sarim Shahwar

package coe628.lab9;

public class Main {
    static final int NUM_PHILOSOPHERS = 5;
    static DiningPhilosophers[] philosophers = new DiningPhilosophers[NUM_PHILOSOPHERS];
    static Fork[] forks = new Fork[NUM_PHILOSOPHERS];
    
    public static void main(String[] args) {
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new Fork(i);
        }
        
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            Fork leftfork = forks[i];
            Fork rightfork = forks[(i + 1) % NUM_PHILOSOPHERS];
            if (i == NUM_PHILOSOPHERS - 1) {
                // Last philosopher picks up the right fork first to avoid deadlock
                philosophers[i] = new DiningPhilosophers(i, rightfork, leftfork);
            } else {
                philosophers[i] = new DiningPhilosophers(i, leftfork, rightfork);
            }
            new Thread(philosophers[i]).start();
        }
    }
}
