package elements;

import control.GameController;
import static elements.Lolo.STOP;
import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;
import java.util.ArrayList;
import utils.Position;

/**
 * Projeto de POO 2017
 * 
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public class Ghost extends Element{
    private int contIntervals;
    protected int tryMove, lastMove, currentMove;
    protected boolean blue = false;
    protected boolean returning = false;
    
        public static final int STOP = 0;
        public static final int MOVE_LEFT = 1;
        public static final int MOVE_RIGHT = 2;
        public static final int MOVE_UP = 3;
        public static final int MOVE_DOWN = 4;
        
        private int movDirection = STOP;
    
    public Ghost(String imageName) {
        super(imageName);
        this.isTransposable = false;
        this.isMortal = true;
    }
    
    @Override
    public void autoDraw(Graphics g){
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());   
    }
    
    
    protected double dist(double x, double y){
        return Math.sqrt(x*x + y*y);
    }
    
    public void ai(ArrayList<Element> e, Lolo lolo, GameController c){
        
    }
    
    
    public void backToLastPosition(){
        this.pos.comeBack();
    }
    
    public void setMovDirection(int direction) {
        this.movDirection = direction;
    }
    
    public void setTryMove(int direction) {
        this.tryMove = direction;
    }
    
    public int getMovDirection(){
        return movDirection;
    }
    
    public void setCurrentMove(int move){
        this.currentMove = move;
    }
    
    public void die(Position pos){
        if(this.pos.getX() == pos.getX() && this.pos.getY() == pos.getY()){
            this.returning = false;
            System.out.println("Cheguei na minha casa");
            if(this.blue){
                this.changeImage("blue-ghost.png");
            } else {
                if(this instanceof Blinky)
                    this.changeImage("blinky.png");
                else if(this instanceof Pinky)
                    this.changeImage("pinky.png");
                else if(this instanceof Inky)
                    this.changeImage("inky.png");
                else if(this instanceof Clyde)
                    this.changeImage("clyde.png");
            }
        } else if(this.pos.getX() > pos.getX()){
            setMovDirection(MOVE_UP);
            move();
        }
        if(this.pos.getX() < pos.getX()){
            setMovDirection(MOVE_DOWN);
            move();
        }
        if(this.pos.getY() < pos.getY()){
            setMovDirection(MOVE_RIGHT);
            move();
        }
        if(this.pos.getY() > pos.getY()){
            setMovDirection(MOVE_LEFT);
            move();
        }
    }
    
    public void runAway(ArrayList<Element> e, Lolo lolo, GameController c){
        if(dist((this.pos.getX() - lolo.getPosition().getX()), (this.pos.getY() - lolo.getPosition().getY())) < 6){
            if(this.pos.getX() < lolo.getPosition().getX()){
                this.setMovDirection(MOVE_UP);
                this.setCurrentMove(MOVE_UP);
                this.correctBuggyMovement(e, c);
            }
            if(this.pos.getX() > lolo.getPosition().getX()){
                this.setMovDirection(MOVE_DOWN);
                this.setCurrentMove(MOVE_DOWN);
                this.correctBuggyMovement(e, c);
            }
            if(this.pos.getY() < lolo.getPosition().getY()){
                this.setMovDirection(MOVE_LEFT);
                this.setCurrentMove(MOVE_LEFT);
                this.correctBuggyMovement(e, c);
            }
            if(this.pos.getY() > lolo.getPosition().getY()){
                this.setMovDirection(MOVE_RIGHT);
                this.setCurrentMove(MOVE_RIGHT);
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
    
    public void correctBuggyMovement(ArrayList<Element> el, GameController c){

        if (tryMove != 0) {
            setMovDirection(tryMove);
            move();
            if (c.isValidPosition(el, this)) {
                currentMove = tryMove;
                lastMove = tryMove;
                tryMove = 0;
            } else {
                currentMove = lastMove;
                backToLastPosition();
                setMovDirection(currentMove);
                move();
                
                if (!c.isValidPosition(el, this)) {
                    tryMove = 0;
                    backToLastPosition();
                    setMovDirection(STOP);
                }
            }

        }else{
            move();
            if (!c.isValidPosition(el, this)) {
                tryMove = currentMove;
    		backToLastPosition();
                if(tryMove != lastMove){
                    setMovDirection(lastMove);
                    move();
                    if (!c.isValidPosition(el, this)) {
                        tryMove = 0;
                        backToLastPosition();
                        setMovDirection(STOP);
                    }
                }else{
                    setMovDirection(STOP);
                }
                currentMove = lastMove;
            	}else{
                    if(tryMove == 0)
                        lastMove = currentMove;
                }
        }
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
    
    public boolean getBlue(){
        return this.blue;
    }
    
    public void setBlue(boolean blue){
        this.blue = blue;
    }
    
    public boolean getReturning(){
        return this.returning;
    }
    
    public void setReturning(boolean returning){
        this.returning = returning;
    }
    
}