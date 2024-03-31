//Sarim Shahwar
package coe628.lab9;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A <code>Semaphore</code> object implements a semaphore (invented by Edsger
 * Dijkstra).
 * <p>
 * A semaphore controls access to resources. The number of resources currently
 * available is the Semaphore <em>value</em>.
 * </p>
 *
 * @see <a href="http://en.wikipedia.org/wiki/Semaphore_(programming)">wikipedia
 * article</a>
 * @author Sarim Shahwar
 */
public class Semaphore {

    private int value;

    /**
     * Create a semaphore.
     *
     * @param value The initial value of the Semaphore ( must be &ge; 0).
     */
    public Semaphore(int value) {
        this.value = value;
    }

    /**
     * Increment the number of available resources. This method never blocks. It
     * may wakeup a Thread waiting for the Semaphore.
     */
    public synchronized void up() throws InterruptedException {
        value++;// Increment the semaphore's count, signaling the release of a resource.
        notify();// Wake up one waiting thread, if any. The woken thread will compete for lock acquisition.
    }

    /**
     * Request a resource. If no resources are available, the calling Thread
     * block until a resource controlled by the Semaphore becomes available.
     */
    public synchronized void down() throws InterruptedException {
        if (value == 0) { // Check if resources are available.
            try {
                wait(); // If not, block and wait until a resource is released.
            } catch (InterruptedException e) {
                e.printStackTrace(); // Print the interrupted exception stack trace.
                System.exit(0); // Exit the program, might not be the best approach for handling interruptions.
            }
        }
        value--; // Decrement the semaphore's count, signaling the acquisition of a resource.
    }

    public synchronized int getvalue() {
        return value; // Return the current count of the semaphore.
    }
}
