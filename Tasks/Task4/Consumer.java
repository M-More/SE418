import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
 
public class Consumer implements Runnable {
	private BlockingQueue<RequestData> queue;
    private static final int SLEEPTIME=1000;
    private long timeout = 100;
	
	public Consumer(BlockingQueue<RequestData> queue) {		
		this.queue = queue;
	}
 
	@Override
	public void run() {
		System.out.println("start Consumer id= "+ Thread .currentThread().getId());
		Random r = new Random();
			try {
				while(true){
					RequestData data= queue.take();
					if(null!= data){
                        if (System.currentTimeMillis()-data.getEnqueueTime() > timeout){
                            System.out.println("data: " + data. getData() + " timeout!");
                        }
                        else{
                            System.out.println("Consumer handle data: "+data.getData());
                        }
						Thread.sleep(r.nextInt(SLEEPTIME));
					}
				}
			} catch (InterruptedException e) {				
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}	
	} 
}
