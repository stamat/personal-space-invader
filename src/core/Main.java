package core;

import java.awt.EventQueue;
import java.awt.Point;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
        	public void run() {
                    
        			CreatureWindow.getInstance().init();
        			Tray.getInstance().init();
        			PopupMenu.getInstance().init();
        			
        			Line l = new Line(new Point(1,2), new Point(5,2));
        			System.out.println(l.getIntercept());
                    //Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

                    //System.out.println(dim);
            }
        });
    }
}
