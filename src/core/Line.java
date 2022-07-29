package core;

import java.awt.Point;

public class Line {
	
	private Double slope;
	private Double intercept;
	private double distance;
	private Point polarity;

	public Line() {
		
	}
	
	public Line(Point p1, Point p2) {
		slope = calcSlope(p1, p2);
		intercept = calcIntercept(slope, p2);
		distance = calcDistance(p1, p2);
		polarity = quadrantPolarity(p1, p2);
	}
	
	public Double calcSlope(Point p1, Point p2) {
		Double m = 0.0;
		double div1 = p1.y - p2.y;
		double div2 = p1.x - p2.x;
		
		if(div1 == 0) {
			m = 0.0;
		}
		else if(div2 == 0) {
			m = null;
		}
		else if(div1 != 0 && div2 !=0)
			m = div1/div2;
		return m;
	}
	
	public double calcDistance(Point p1, Point p2) {
		double c = Math.sqrt(Math.pow((p2.x-p1.x),2) + Math.pow(p2.y-p1.y,2));
		return c;
	}
	
	public Double calcIntercept(Double m, Point p1) {
		Double b = null;
		if(m != null)
			b = p1.y-m*p1.x;
		return b;
	}
	
	public Double calcLinear(double x) {
		Double y = null;
		if(slope != null)
			y = slope*x+intercept;
		else
			System.err.println("Slope is unexistent => Line is vertical");
		return y;
	}
	
	public Point quadrantPolarity(Point p1, Point p2) {
		Point p = new Point();
		p.x = polarity(p1.x, p2.x);
		p.y = polarity(p1.y, p2.y);
		
		return p;
	}
	
	public int polarity(double a, double b) {
		int i = 0;
		if(a>b) i =-1;
		if(a<b) i = 1;
		
		return i;
	}

	public Double getSlope() {
		return slope;
	}

	public Double getIntercept() {
		return intercept;
	}

	public double getDistance() {
		return distance;
	}

	public Point getPolarity() {
		return polarity;
	}
}
