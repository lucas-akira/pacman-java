package elements;

import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;

// Paralelo ao Pacman em y, aleatório em x

public class Pinky extends Ghost{

        // Encurrala o Pacman
        private void move(double x, double y){
            // y = random shit
            setPosition(x,y);
        }

	public Pinky(String imagename){
		super(imagename);
	}
	
}