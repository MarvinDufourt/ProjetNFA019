package cnam.net.application;

import java.awt.EventQueue;
import cnam.net.swingViews.Gui1;

public class Lancement {
    public static void main(String[] args) {

        // Lancement IHM
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui1 frame = new Gui1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
        
        

    }
}
