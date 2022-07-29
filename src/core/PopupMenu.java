package core;

import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JWindow;

public class PopupMenu extends JPopupMenu{
	
	private static final long serialVersionUID = -2756706641987409018L;
	private JWindow win = CreatureWindow.getInstance();
	  private SystemTray tray = Tray.getInstance().getTray();
	  private TrayIcon trayIcon = Tray.getInstance().getTrayIcon();
	  
	  private static final PopupMenu INSTANCE = new PopupMenu();

	
	 private PopupMenu() {
		 
	 }
	 
	 public static PopupMenu getInstance() {
	        return INSTANCE;
	 }
	  
	  public void init() {
		  URL infoURL = getClass().getClassLoader().getResource("rsc/info.png");
		  URL cancelURL = getClass().getClassLoader().getResource("rsc/cancel.png");
		  URL helpURL = getClass().getClassLoader().getResource("rsc/help.png");
		  URL optionsURL = getClass().getClassLoader().getResource("rsc/options.png");
		  URL statusURL = getClass().getClassLoader().getResource("rsc/status.png");
		  URL connectURL = getClass().getClassLoader().getResource("rsc/connect.png");
		  URL moodURL = getClass().getClassLoader().getResource("rsc/mood.png");
		  URL hungerURL = getClass().getClassLoader().getResource("rsc/hunger.png");
		  URL healthURL = getClass().getClassLoader().getResource("rsc/health.png");
		  URL strengthURL = getClass().getClassLoader().getResource("rsc/strength.png");
		  
		  JMenuItem moodMI = new JMenuItem("⎕⎕⎕⎕⎕⎕⎕", new ImageIcon(moodURL));
		  moodMI.setToolTipText("Mood");
		  add(moodMI);
		  
		  JMenuItem hungerMI = new JMenuItem("⎕⎕⎕⎕⎕⎕⎕⎕⎕", new ImageIcon(hungerURL));
		  hungerMI.setToolTipText("Hunger");
		  add(hungerMI);
		  
		  JMenuItem healthMI = new JMenuItem("⎕⎕⎕⎕⎕⎕⎕⎕", new ImageIcon(healthURL));
		  healthMI.setToolTipText("Health");
		  add(healthMI);
		  
		  JMenuItem strengthMI = new JMenuItem("⎕⎕⎕⎕", new ImageIcon(strengthURL));
		  strengthMI.setToolTipText("Strength");
		  add(strengthMI);
		  
		  addSeparator();
		  
		  JMenuItem statusMI = new JMenuItem("Status", new ImageIcon(statusURL));
		  add(statusMI);
		  
		  JMenuItem connectMI = new JMenuItem("Connection: off", new ImageIcon(connectURL));
		  add(connectMI);
		  
		  JMenuItem settingsMI = new JMenuItem("Settings", new ImageIcon(optionsURL));
		  add(settingsMI);
		  
		  JMenuItem visibleCBMI = new JCheckBoxMenuItem("Visible");
		  visibleCBMI.setMargin(new Insets(0,20,0,0));
		  visibleCBMI.setSelected(true);
		  visibleCBMI.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	toggleVisible();
	            }});
		  add(visibleCBMI);
		
		  JMenuItem freeroamCBMI = new JCheckBoxMenuItem("Free roam");
		  freeroamCBMI.setMargin(new Insets(0,20,0,0));
		  freeroamCBMI.setSelected(true);
		  //freeroamCBMI.
		  add(freeroamCBMI);
		  
		  JMenuItem ontopCBMI = new JCheckBoxMenuItem("Always on top");
		  ontopCBMI.setMargin(new Insets(0,20,0,0));
		  ontopCBMI.setSelected(true);
		  ontopCBMI.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	toggleAlwaysOnTop();
	            }});
		  add(ontopCBMI);
		  
		  addSeparator();
		  
		  JMenuItem helpMI = new JMenuItem("Help", new ImageIcon(helpURL));
		  add(helpMI);
		  
		  JMenuItem aboutMI = new JMenuItem("About", new ImageIcon(infoURL));
		    aboutMI.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	URL imgUrl1 = getClass().getClassLoader().getResource("rsc/invader1.png");
	                Image ico = new ImageIcon(imgUrl1).getImage();
	                JFrame frame = new JFrame();
	                frame.setIconImage(ico);
	                
	                JOptionPane.showMessageDialog(frame,
	          			    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. \n" +
	          			    "Etiam aliquam varius lectus, eu venenatis eros tristique \n" +
	          			    "id. Phasellus felis felis, gravida ullamcorper tincidunt \n" +
	          			    "ac, lobortis ac massa. Pellentesque condimentum lectus \n" +
	          			    "purus, nec molestie massa. Donec a dolor nec nisl vestibulum \n" +
	          			    "mattis. Sed libero velit, faucibus ut congue quis, \n" +
	          			    "volutpat quis nisl. Ut tortor velit, accumsan in scelerisque \n" +
	          			    "quis, tincidunt eu nisi. Suspendisse convallis ullamcorper tellus vel mollis.",
	          			    "Digizoa",
	          			    JOptionPane.INFORMATION_MESSAGE,
	          			    new ImageIcon(imgUrl1));
		                  }});
		  add(aboutMI);

		    addSeparator();

		    JMenuItem exitMI = new JMenuItem("Exit", new ImageIcon(cancelURL));
		    exitMI.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	      	      tray.remove(trayIcon);
	      	      System.exit(0);
	                  }});
		    add(exitMI);
	  }
	  
	  public void toggleVisible() {
			if(win.isVisible()){
				win.setVisible(false);
			} else {
				win.setVisible(true);
			}
		}
		
		public void toggleAlwaysOnTop() {
			if(win.isAlwaysOnTop()){
				win.setAlwaysOnTop(false);
				win.toBack();
			} else {
				win.setAlwaysOnTop(true);
			}
		}
		
		public void displayPopup(Point p) {
			displayPopup(p.x, p.y);
		}
		
		public void displayPopup(int x, int y) {	
			setLocation(x, y);
			setInvoker(this);
			setVisible(true);
		}

}
