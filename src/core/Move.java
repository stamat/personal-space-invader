package core;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class Move implements Observer {
	
	private JFrame frame;
	private double m;
	private double b;
	private boolean move = false;
	private int speed = 100;
	private Point sign;
	
	public Move(JFrame frame, Point destination) {
		this.frame = frame;
		Stepper step = new Stepper(speed);
		step.initStepper();
		step.addObserver(this);
		goTo(destination);
	}
	
	public void goTo(Point destination) {
		deriveLinearPath(destination);
		move=true;
	}
	
	public void deriveLinearPath(Point destination) {
		//sign = Line.quadrantPolarity(frame.getLocation(), destination);
		//m = Line.calcSlope(frame.getLocation(), destination);
		//b = (int) Line.calcIntercept(m, frame.getLocation());
		System.out.println(frame.getLocation());
		System.out.println(sign);
	}
	
	public void jump(){
		Point now = frame.getLocation();
		//System.out.println(m/m);
		//frame.setLocation((int) (now.x+(1*sign.x)),(int) (Line.calcLinear(sign.x*sign.y*m, b, now.x+(1*sign.y*-1))));
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(move) jump();
	}

}
