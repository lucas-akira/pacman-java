package elements;

import control.GameController;
import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;
import java.util.ArrayList;
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
        public void ai(ArrayList<Element> e, Lolo lolo, GameController c){
            if(blue){
                this.runAway(e, lolo, c);
            }else {
                if(dist(this.pos.getX()-lolo.getPosition().getX(), this.pos.getY() - lolo.getPosition().getY()) > 8){
                    if(this.pos.getX() > lolo.getPosition().getX() && this.lastMove != MOVE_DOWN){
                        this.setMovDirection(MOVE_UP);
                        this.setCurrentMove(MOVE_UP);
//                        this.setTryMove(MOVE_UP);
                        this.correctBuggyMovement(e, c);
                    }
                    if(this.pos.getX() < lolo.getPosition().getX() && this.lastMove != MOVE_UP){
                        this.setMovDirection(MOVE_DOWN);
                        this.setCurrentMove(MOVE_DOWN);
//                        this.setTryMove(MOVE_DOWN);
                        this.correctBuggyMovement(e, c);
                    }
                    if(this.pos.getY() > lolo.getPosition().getY()  && this.lastMove != MOVE_RIGHT){
                        this.setMovDirection(MOVE_LEFT);
                        this.setCurrentMove(MOVE_LEFT);
//                        this.setTryMove(MOVE_LEFT);
                        this.correctBuggyMovement(e, c);
                    }
                    if(this.pos.getY() < lolo.getPosition().getY()  && this.lastMove != MOVE_LEFT){
                        this.setMovDirection(MOVE_RIGHT);
                        this.setCurrentMove(MOVE_RIGHT);
//                        this.setTryMove(MOVE_RIGHT);
                        this.correctBuggyMovement(e, c);
                    }
                } else {
                    int rand;
                    do{
                        rand = (int) Math.round(Math.random()*4);
                        
                    }while(((rand == MOVE_LEFT && lastMove == MOVE_RIGHT)
                        ||(rand == MOVE_RIGHT && lastMove == MOVE_LEFT)
                        ||(rand == MOVE_UP && lastMove == MOVE_DOWN)
                        ||(rand == MOVE_DOWN && lastMove == MOVE_UP)));
  //                  this.setMovDirection(rand);
 //                   this.setCurrentMove(rand);
                    
                    this.setTryMove(rand);
                    this.correctBuggyMovement(e, c);
                }
            }
        }
       
	
}