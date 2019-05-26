public  final class RequestData {
    private final int requestNum;
    private long enqueueTime;
 
	public RequestData(int d, long time) {
        this.requestNum = d;
        this.enqueueTime = time;
	}
	
	public int getData(){
		return this.requestNum;
    }
    
    public long getEnqueueTime(){
        return this.enqueueTime;
    }
}