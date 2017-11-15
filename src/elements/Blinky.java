package elements;

import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;

// Imita o Pacman com elementos aleatórios

public class Blinky extends Ghost{
	
        // Imita os movimentos do pacman
        private void move(double x, double y){
            setPosition(x,y);
        }

	public Blinky(String imagename){
		super(imagename);
	}
}