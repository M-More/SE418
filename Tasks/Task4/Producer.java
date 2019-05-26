import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable{
	private volatile boolean isRunning= true;

	private BlockingQueue<RequestData> queue;
	
	private static AtomicInteger count = new AtomicInteger();
	 
	private static final int SLEEPTIME=1000;
	
	public Producer(BlockingQueue<RequestData> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		Random r  = new Random();
		System.out.println("start producer id = "+ Thread .currentThread().getId());
		try{
			while(isRunning){
				Thread.sleep(r.nextInt(SLEEPTIME));
                RequestData data= new RequestData(count.incrementAndGet(), System.currentTimeMillis());
				System.out.println("data:" + data.getData() + " is put into queue ");
				if(!queue.offer(data,2,TimeUnit.SECONDS)){  // add data to the queue
					System.out.println("faile to put data:  "+ data);
				}
			}
		}catch (InterruptedException e){
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
 
	public void stop(){
		isRunning=false;
	}
}