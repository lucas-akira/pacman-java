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
            if(lolo.getMovDirection() == MOVE_LEFT || lolo.getMovDirection() == MOVE_RIGHT){
                if(this.pos.getY() > lolo.getPosition().getY()){
                    this.setMovDirection(MOVE_LEFT);
                    this.setCurrentMove(MOVE_LEFT);
                    this.correctBuggyMovement(e, c);
                }
                else if(this.pos.getY() < lolo.getPosition().getY()){
                    this.setMovDirection(MOVE_RIGHT);
                    this.setCurrentMove(MOVE_RIGHT);
                    this.correctBuggyMovement(e, c);
                }
                else {
                    int rand = (int) Math.round(Math.random()*4);
                    this.setTryMove(rand);
                    this.correctBuggyMovement(e, c);
                }
            }
            else if(lolo.getMovDirection() == MOVE_UP || lolo.getMovDirection() == MOVE_DOWN){
                if(this.pos.getX() > lolo.getPosition().getX()){
                    this.setMovDirection(MOVE_UP);
                    this.setCurrentMove(MOVE_UP);
                    this.correctBuggyMovement(e, c);
                }
                else if(this.pos.getX() < lolo.getPosition().getX()){
                    this.setMovDirection(MOVE_DOWN);
                    this.setCurrentMove(MOVE_DOWN);
                    this.correctBuggyMovement(e, c);
                }
                else {
                    if(Math.random() < Consts.IA_FACIL){
                        int rand = (int) Math.round(Math.random()*4);
                        this.setTryMove(rand);
                        this.correctBuggyMovement(e, c);
                    }
                    else{
                        this.ai(e, lolo, c);
                    }
                }
            }
            else {
                int rand = (int) Math.round(Math.random()*4);
                this.setTryMove(rand);
                this.correctBuggyMovement(e, c);
                
            }
        }
	
}