package core;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Tray {

	private BufferedImage image = null;
	private SystemTray tray = null;
	private TrayIcon trayIcon = null;
	
	private static final Tray INSTANCE = new Tray();

	private Tray() {
		
	}
	
	public void init() {
		setLookAndFeel();
		
 		if (SystemTray.isSupported()) {
 			tray = SystemTray.getSystemTray();

 			URL imgUrl1 = getClass().getClassLoader().getResource("rsc/icon.png");

      		try {
				image = ImageIO.read(imgUrl1);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			trayIcon = new TrayIcon(image, "VLife");
			trayIcon.addMouseListener(new MouseListener(){
				public void mousePressed(MouseEvent arg0) {
					if(arg0.getButton()==3){
							PopupMenu.getInstance().displayPopup(arg0.getLocationOnScreen());
					}
					
				}
				@Override
				public void mouseClicked(MouseEvent arg0) {
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
				}
			});
			
		try {
            tray.add(trayIcon);
          } catch (AWTException e) {
        	  System.err.println(e.getMessage());
          }

 		}
	}
	
	private void setLookAndFeel() {
		try {
 			UIManager.setLookAndFeel(
 			            UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			System.err.println(e1.getMessage());
 		} catch (InstantiationException e1) {
 			System.err.println(e1.getMessage());
 		} catch (IllegalAccessException e1) {
 			System.err.println(e1.getMessage());
 		} catch (UnsupportedLookAndFeelException e1) {
 			System.err.println(e1.getMessage());
 		}
	}
	
	public static Tray getInstance() {
        return INSTANCE;
    }

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public SystemTray getTray() {
		return tray;
	}

	public void setTray(SystemTray tray) {
		this.tray = tray;
	}

	public TrayIcon getTrayIcon() {
		return trayIcon;
	}

	public void setTrayIcon(TrayIcon trayIcon) {
		this.trayIcon = trayIcon;
	}

}
