import java.util.concurrent.LinkedBlockingDeque;

public class RequestDeque<E> extends LinkedBlockingDeque<E>{
    private int threshold = 3;

    public RequestDeque(int i) {
	}

	@Override
    public E take() throws InterruptedException{
        if (this.size() < threshold){
            return this.takeLast();
        }
        else {
            return this.takeFirst();
        }
    }
}