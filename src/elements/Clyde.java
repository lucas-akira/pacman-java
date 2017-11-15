package elements;

import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;

// Igual ao blinky para distância menor que D, aleatório depois

public class Clyde extends Ghost{

	private void move(double x, double y){
            if(dist(x,y) < 5){
                // random shit
            }
            else{
                setPosition(x,y);
            }
            
        }
    
        public Clyde(String imagename){
		super(imagename);
	}
       
	
}