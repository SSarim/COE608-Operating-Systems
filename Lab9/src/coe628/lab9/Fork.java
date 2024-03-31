package coe628.lab9;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Fork {
    Semaphore semaphore = new Semaphore(1);
    int id; 
    
   /**
    * Author: Sarim Shahwar
     * Constructs a Fork with a specified identifier.
     * 
     * @param id The unique identifier for the fork.
     */
    public Fork(int id) {
        this.id = id;
    }
    
    /**
     * Returns the fork's identifier, adjusted to be 1-indexed for user-friendly output.
     * 
     * @return The fork's identifier, incremented by 1 for display purposes.
     */
    int getId() {
        return (id + 1);
    }
    
    /**
     * Attempts to acquire this fork for a philosopher. If the fork is currently being used
     * (semaphore permit is 0), the philosopher will wait until the fork becomes available.
     * 
     * @param philosopherId The identifier of the philosopher attempting to acquire the fork.
     */
    void get_fork(int philosopherId) {
        try {
            // Check if the semaphore's value is 0 (fork is in use) before attempting to acquire it.
            if(semaphore.getvalue() <= 0) {
                System.out.println("Philosopher " + philosopherId + " is waiting for Fork " + getId());
            }
            semaphore.down(); // Attempt to acquire the fork, blocking if necessary.
        } catch (InterruptedException ex) {
            ex.printStackTrace(System.out); // Log any InterruptedException that occurs.
        }
    }
    void put_fork() {
        try {  
            semaphore.up(); //Release the fork by incrementing the semaphore's permit count
        } catch (InterruptedException ex) {
            Logger.getLogger(Fork.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    boolean isFree() {
        return semaphore.getvalue() > 0;  // Return whether the fork is available.
    }
}
