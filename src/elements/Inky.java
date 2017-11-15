package elements;

import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;

// Inverso do Clyde

public class Inky extends Ghost{
    
        // x e y são do blinky, não do pacman
        private void move(double x, double y){
            if(dist(x,y) > 5){
                // random shit
            }
            else{
                setPosition(x,y);
            }
        }

	public Inky(String imagename){
		super(imagename);
	}

}