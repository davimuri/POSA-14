
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;

/**
 * @class SimpleSemaphore
 *
 * @brief This class provides a simple counting semaphore
 *        implementation using Java a ReentrantLock and a
 *        ConditionObject.  It must implement both "Fair" and
 *        "NonFair" semaphore semantics, just liked Java Semaphores. 
 */
public class SimpleSemaphore {
    /**
     * Constructor initialize the data members.  
     */
    public SimpleSemaphore (int permits,
                            boolean fair)
    { 
        // TODO - you fill in here
    	mPermits = permits;
    	mReentrantLock = new ReentrantLock(fair);
    	mCondition = mReentrantLock.newCondition();
    }

    /**
     * Acquire one permit from the semaphore in a manner that can
     * be interrupted.
     */
    public void acquire() throws InterruptedException {
        // TODO - you fill in here
    	mReentrantLock.lockInterruptibly();
    	try {
    		while (mPermits == 0){
    			mCondition.await();
    		}
    		mPermits--;
    	} finally {
    		mReentrantLock.unlock();
    	}
    }

    /**
     * Acquire one permit from the semaphore in a manner that
     * cannot be interrupted.
     */
    public void acquireUninterruptibly() {
        // TODO - you fill in here
    	mReentrantLock.lock();
    	try {
    		while (mPermits == 0){
    			mCondition.awaitUninterruptibly();
    		}
    		mPermits--;
    	} finally {
    		mReentrantLock.unlock();
    	}
    }

    /**
     * Return one permit to the semaphore.
     */
    void release() {
        // TODO - you fill in here
    	mReentrantLock.lock();
    	try {
        	mPermits++;
        	mCondition.signal();
    	} finally {
    		mReentrantLock.unlock();
    	}
    }

    /**
     * Define a ReentrantLock to protect the critical section.
     */
    // TODO - you fill in here
    private final ReentrantLock mReentrantLock;
    
    /**
     * Define a ConditionObject to wait while the number of
     * permits is 0.
     */
    // TODO - you fill in here
    private final Condition mCondition;
    /**
     * Define a count of the number of available permits.
     */
    // TODO - you fill in here
    private volatile int mPermits;
}

