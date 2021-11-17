package sort;

public class DiningPhilospher {
	
	public class Philosopher implements Runnable {
//10-10-21
	    private Object leftFork;
	    private Object rightFork;

	    public Philosopher(Object leftFork, Object rightFork) {
	        this.leftFork = leftFork;
	        this.rightFork = rightFork;
	    }
	    
	    private void doAction(String action) throws InterruptedException {
	        System.out.println(
	          Thread.currentThread().getName() + " " + action);
	        Thread.sleep(((int) (Math.random() * 100)));
	    }
	    @Override
	    public void run() {
	        try {
	            while (true) {
	                
	                // thinking
	                doAction(System.nanoTime() + ": Thinking");
	                synchronized (leftFork) {
	                    doAction(
	                      System.nanoTime() 
	                        + ": Picked up left fork");
	                    synchronized (rightFork) {
	                        // eating
	                        doAction(
	                          System.nanoTime() 
	                            + ": Picked up right fork - eating"); 
	                        
	                        doAction(
	                          System.nanoTime() 
	                            + ": Put down right fork");
	                    }
	                    
	                    // Back to thinking
	                    doAction(
	                      System.nanoTime() 
	                        + ": Put down left fork. Back to thinking");
	                }
	            }
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	            return;
	        }
	    }
	}

    public static void main(String[] args) throws Exception {
       DiningPhilospher x = new DiningPhilospher();
        final Philosopher[] philosophers = new Philosopher[5];
        Object[] forks = new Object[philosophers.length];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < philosophers.length; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];

            if (i == philosophers.length - 1) {
                
            	 philosophers[i] = x.new Philosopher(rightFork,leftFork );
            } else {
                philosophers[i] = x.new Philosopher(leftFork, rightFork);
            }
            
            Thread t 
              = new Thread(philosophers[i], "Philosopher " + (i + 1));
            t.start();
        }
    }
}