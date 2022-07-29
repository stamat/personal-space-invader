package core;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class CreatureGraphic extends JComponent implements Observer { 

	private static final long serialVersionUID = 8266255046340194567L;
	private BufferedImage img1;
    private BufferedImage img2;
    private boolean anim = true;
    final Stepper step = new Stepper(1000);

public CreatureGraphic() {
	
	URL imgUrl1 = getClass().getClassLoader().getResource("rsc/invader1.png");
	URL imgUrl2 = getClass().getClassLoader().getResource("rsc/invader2.png");
	try {
		img1 = ImageIO.read(imgUrl1);
		img2 = ImageIO.read(imgUrl2);
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	
    step.addObserver(this);
    step.initStepper();
}

public void paintComponent(Graphics g) {
	BufferedImage img;
	if(anim){
		img=img1;
		anim=false;
	} else {
		img=img2;
		anim=true;
	}
    g.drawImage(img,0,0,null);
}

public BufferedImage getIconImage() {
	return img1;
}

@Override
public void update(Observable arg0, Object arg1) {
	repaint();
}



}