package elements;

import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;
import utils.Position;

// Igual ao blinky para distância menor que D, aleatório depois

public class Clyde extends Ghost{

	private void move(Position p){
            if(dist(p.getX(),p.getY()) < 5){
                // random shit
            }
            else{
                setPosition(p.getX(),p.getY());
            }
            
        }
    
        public Clyde(String imagename){
		super(imagename);
	}
        
        @Override
        public void ai(Lolo lolo){
          
        }
       
	
}