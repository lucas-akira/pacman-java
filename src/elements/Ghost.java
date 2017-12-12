package elements;

import control.GameController;
import static elements.Lolo.STOP;
import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Projeto de POO 2017
 * 
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public class Ghost extends Element{
    private int contIntervals;
    private int tryMove, lastMove, currentMove;
    private boolean blue = false;
    
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
    
}