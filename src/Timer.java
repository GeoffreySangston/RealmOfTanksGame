
public class Timer {
	long timerStartMillis;
	public Timer(){
		timerStartMillis = System.currentTimeMillis();
	}
	public long getLifeTime(){
		return System.currentTimeMillis() - timerStartMillis;
	}
}
