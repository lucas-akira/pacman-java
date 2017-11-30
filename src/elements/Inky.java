package elements;

import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;
import utils.Position;

// Inverso do Clyde

public class Inky extends Ghost{
    
        // x e y são do blinky, não do pacman
        private void move(Position p){
            if(dist(p.getX(),p.getY()) > 5){
                // random shit
            }
            else{
                setPosition(p.getX(),p.getY());
            }
        }

	public Inky(String imagename){
		super(imagename);
	}

}