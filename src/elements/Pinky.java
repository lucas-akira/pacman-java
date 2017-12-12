package elements;

import control.GameController;
import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;
import java.util.ArrayList;
import utils.Position;

// Paralelo ao Pacman em y, aleatório em x

public class Pinky extends Ghost{

        // Encurrala o Pacman
        private void move(Position p){
            // y = random shit
            setPosition(p.getX(),p.getY());
        }

	public Pinky(String imagename){
		super(imagename);
	}
        
        @Override
        public void ai(ArrayList<Element> e, Lolo lolo, GameController c){
          
        }
	
}