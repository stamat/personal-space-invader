package core;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A funky class called Stepper that initializes a Step - TimerTask class
 * recursively. The main function is initialization of a step and sending a
 * event notifications to observing objects. If a system is without system
 * clock or doesn't have backup battery, Stepper is all the time measurement
 * the system needs.
 *
 * @author stamat
 *
 * @see Config
 */
public class Stepper extends Observable {

	/**
	 * Something.
	 */
	private int steps = 0; //number of steps the software has made
	private Timer stepTimer;
	private long step = 1000;

	/**
	 * Class constructor.
	 */
	public Stepper() {
		
	}
	
	public Stepper(long step) {
		this.step = step;
	}

	/**
	 * Timer initializer. Recursively initiates Step by the given step span
	 *
	 *  @see	Config.step and config.xml
	 */
	public final void initStepper() {
		// Notifies the process start
		setChanged();
		notifyObservers();

		//Schedules repetitive timer task
		stepTimer = new Timer();
		
		stepTimer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				step();
				}
			} , 0, step);
	}

	public void termStepper() {
		stepTimer.cancel();
		steps = 0;
	}

	public void pauseStepper() { 
		stepTimer.cancel(); }

	/**.
	 * A method that is activated on each step
	 */
	private void step() {
		steps++;
		setChanged();
		notifyObservers();
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
		pauseStepper();
		initStepper();
	}

	public long getStep() {
		return step;
	}

	public void setStep(long step) {
		this.step = step;
		pauseStepper();
		initStepper();
	}

}
