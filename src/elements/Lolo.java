package elements;

import control.GameController;
import control.Stage;
import utils.Drawing;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Projeto de POO 2017
 * 
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public class Lolo extends Element  implements Serializable{
    
    public static final int STOP = 0;
    public static final int MOVE_LEFT = 1;
    public static final int MOVE_RIGHT = 2;
    public static final int MOVE_UP = 3;
    public static final int MOVE_DOWN = 4;
    
    private int lives = 3;
    private int extraLivesFactor = 10000;
    private int movDirection = STOP;
    private int tryMove, lastMove, currentMove; // Variáveis para controlar a movimentação
    public int totalDots = 0;   // Variável para contar o total de dots na tela
    private int killStreak = 0; // Variável para contar quantos fantasmas matou sob efeito de uma power pellet
    
    public int getExtraLivesFactor() {
        return extraLivesFactor;
    }
    
    public void addExtraLivesFactor(int addition) {
        this.extraLivesFactor += addition;
    }

    public void setKillStreak(int killStreak) {
        this.killStreak = killStreak;
    }

    public int getKillStreak() {
        return killStreak;
    }

    public void addKillStreak(int addAmount) {
        this.killStreak += addAmount;
    }
   
    public Lolo(String imageName) {
        super(imageName);
    }
    
    @Override
    public void autoDraw(Graphics g){
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
    }

    public void backToLastPosition(){
        this.pos.comeBack();
    }
    
    public void setMovDirection(int direction) {
        movDirection = direction;
    }
    
    public int getMovDirection(){
        return movDirection;
    }
    
    // Com esse método, a imagem do Pacman só se altera quando ele efetivamente muda de direção
    public void correctBuggyAnimation(){
        switch(lastMove){
            case MOVE_UP:
                this.changeImage("pacman_u.png");
                break;
                
            case MOVE_DOWN:
                this.changeImage("pacman_d.png");
                break;
            
            case MOVE_LEFT:
                this.changeImage("pacman_l.png");
                break;
                
            case MOVE_RIGHT:
                this.changeImage("pacman_r.png");
                break;
        }
    }
    
    
    // Método usado para corrigir a movimentação travada do pacman devido a construção do tabuleiro
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
        correctBuggyAnimation();
    }
    
    public int getScore(){
        return this.score;
    }
    
    public void setScore(int score){
        this.score = score;
    }
    
    public void addScore(int score){
        this.score += score;
    }
    
    public void setCurrentMove(int move){
        this.currentMove = move;
    }
    
    public int getLife() { 
        return lives; 
    }
    
    public void addLife(int lives) {
        this.lives += lives;
    }
    
    public void pacmanDies(ArrayList<Element> elements){
        Element elemAux;
        
        this.lives--;
        this.setPosition(13,10);
        this.setMovDirection(STOP);
        
        elemAux = (Blinky) elements.get(1);
        elemAux.setPosition(10,8);
        
        elemAux = (Pinky) elements.get(2);
        elemAux.setPosition(10, 9);
        
        elemAux = (Inky) elements.get(3);
        elemAux.setPosition(10, 10);
        
        elemAux = (Clyde) elements.get(4);
        elemAux.setPosition(10, 11);
        
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
}
