package elements;

import control.GameController;
import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;
import java.util.ArrayList;
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
        
        @Override
        public void ai(ArrayList<Element> e, Lolo lolo, GameController c){
          
        }

}