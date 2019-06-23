import java.util.Timer;
import java.util.TimerTask;

public final class timer_test {

	public static void main(String[] args) {

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
   			public void run() {
       			System.out.println(" let the virtual dog out ");
    		}
  		}, 1000);

		timer.schedule(new TimerTask() {
   			public void run() {
       			timer.cancel();
    		}
  		}, 1000);
	}
}