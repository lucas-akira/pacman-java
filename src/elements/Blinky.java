package elements;

import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;
import utils.Position;

// Imita o Pacman com elementos aleatórios

public class Blinky extends Ghost{
    
        public static final int STOP = 0;
        public static final int MOVE_LEFT = 1;
        public static final int MOVE_RIGHT = 2;
        public static final int MOVE_UP = 3;
        public static final int MOVE_DOWN = 4;
        
        private int movDirection = STOP;
	
        // Imita os movimentos do pacman
        
        public void setMovDirection(int direction) {
            movDirection = direction;
        }
    
        public void move() {
        switch (movDirection) {
            case MOVE_LEFT:
                this.moveLeft();
                break;
            case MOVE_RIGHT:
                this.moveRight();
                break;
            case MOVE_UP:
                this.moveUp();
                break;
            case MOVE_DOWN:
                this.moveDown();
                break;
            default:
                break;
        }
    }
	public Blinky(String imagename){
		super(imagename);
	}
        
        @Override
        public void ai(Lolo lolo){
          
        }
}