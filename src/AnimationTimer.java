//import javafx.scene.control.Label;  
//
///**
// * This class is an extension of the AnimationTimer in the javafx library used to render the
// * timer in the game appropriately in the minutes and seconds format decreasing.
// * 
// * @author Bill Xiang
// *
// */
//public class AnimationTimer extends javafx.animation.AnimationTimer {
//
//	private int time;
//	private int minutes;
//	private int seconds;
//	private int totalSeconds;
//	private int delay;
//	private String string;
//	private Label label;
//	private int previousTime;
//	
//	/**
//	 * Constructs an AnimationTimer object 
//	 * 
//	 * @param totalTime the total amount of time
//	 * @param d
//	 * @param l
//	 */
//	public AnimationTimer(int totalTime, int d, Label l) {
//		
//		totalSeconds = totalTime;
//		delay = d * (int)(10e9);
//		label = l;
//		time = 2 * 6 * (int)10e10;
//		minutes = time / (6 * (int) 10e10);
//		seconds = time / ((int)10e9);
//		string = "";
//	}
//	
//	@Override
//	public void handle(long currentTime) {//timeline
//		if (currentTime - previousTime >= delay);
////		delay *= 6 * (int)10e10;
//		System.out.println(seconds);
//		System.out.println(minutes);
//		if (time > 0) {
//			time--; 
//		}
//		//System.out.println(time);
//		string = minutes + ":" + seconds;
//		minutes = time / (6 * (int) 10e10);
//		seconds = time / ((int)10e9) % 60;
////		minutes = time / 180000;
////		seconds = (time / 30000) % 60;
//		label.setText(string);
//	}
//	
//	public int getPreviousTime() {
//		return previousTime;
//	}
//	
//	public void setPreviousTime(int prevTime) {
//		previousTime = prevTime;
//	}
//	
//	public int getDelay() {
//		return delay;
//	}
//	
//	public void setDelay(int d) {
//		delay = d;
//	}
//	
//	public int getTotalSeconds() {
//		return totalSeconds;
//	}
//	
//	public void setTotalSeconds(int tS) {
//		totalSeconds = tS;
//	}
//
//}
