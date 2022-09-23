package GUI;

import java.awt.EventQueue;

/**
 * Clase Main 
 * @author Arroyo Tomas (126078) - Alconcher Julian (126094)
 *
 */
public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.ProyectoEDD2022.setVisible(true);} catch (Exception e) {e.printStackTrace();}
			}
		});
	}
}
