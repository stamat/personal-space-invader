package core;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Paint;
import java.awt.Point;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;
import javax.swing.JWindow;

public class CreatureWindow extends JWindow {
    
	private static final long serialVersionUID = 1460710672290061048L;
	Point click = new Point();
    private JPanel jPanel1;
    private static final CreatureWindow INSTANCE = new CreatureWindow();


    private CreatureWindow() {
    	super(translucencyCapable());
    }
    
    public void init() {
    	 
         initComponents();
         jPanel1.setOpaque(true);
         jPanel1.setBackground(new Color(240, 240, 240, 128));

         setLocationRelativeTo(null);

 		installListeners();
 		
 		AWTUtilitiesWrapper.setWindowOpaque(this, false);
 		
 		setSize(114,84);
         CreatureGraphic tb = new CreatureGraphic();
         //Move m = new Move(frame,new Point(2800,1200));
         //PowerMath.calcSlope(new Point(1530,60), new Point(1650,70));
         add(tb);
         
         //setFocusableWindowState(false);
 	   // setFocusable(false);
         
         setVisible(true);
    }

	private void initComponents() {

        jPanel1 = new JPanel() {

			private static final long serialVersionUID = 5549049806317380341L;

			protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D) {

                    Paint p = new Color(240, 240, 240, 0);

                    Graphics2D g2d = (Graphics2D)g;
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    super.paintComponent(g);
                }
            }
        };
        
        setAlwaysOnTop(true);

        jPanel1.setDoubleBuffered(false);
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.BorderLayout());

        getContentPane().add(jPanel1, BorderLayout.CENTER);

        pack();
    }
    
    private void installListeners() {
    
	   addMouseListener( new MouseAdapter() {
	   
	        public void mousePressed(MouseEvent e) {
	            click = e.getPoint(); 
	            getComponentAt(click);
	            if(e.getButton()==3){
					PopupMenu.getInstance().displayPopup(e.getLocationOnScreen());
	            }
	        }
	    });
	    
	 
	   addMouseMotionListener( new MouseMotionAdapter() {
	    	
	        public void mouseDragged(MouseEvent e) {
	            int thisX = getLocation().x; int thisY = getLocation().y;
	 
	            int xMoved = ( thisX + e.getX() ) - ( thisX + click.x );
	            int yMoved = ( thisY + e.getY() ) - ( thisY + click.y );
	            
	            int X = thisX + xMoved; int Y = thisY + yMoved; setLocation( X, Y );
	            
	        }
	    }); 
    }
    
    private static GraphicsConfiguration translucencyCapable() {
    	GraphicsConfiguration translucencyCapableGC = null;

		GraphicsEnvironment env =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] devices = env.getScreenDevices();

        for (int i = 0; i < devices.length && translucencyCapableGC == null; i++) {
            GraphicsConfiguration[] configs = devices[i].getConfigurations();
            for (int j = 0; j < configs.length && translucencyCapableGC == null; j++) {
                if (AWTUtilitiesWrapper.isTranslucencyCapable(configs[j])) {
                    translucencyCapableGC = configs[j];
                }
            }
        }
        
        return translucencyCapableGC;
    }
    
    public static CreatureWindow getInstance() {
        return INSTANCE;
    }

}
