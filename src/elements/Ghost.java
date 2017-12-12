package elements;

import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;

/**
 * Projeto de POO 2017
 * 
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public class Ghost extends Element{
    private int contIntervals;
    
    public Ghost(String imageName) {
        super(imageName);
        this.isTransposable = false;
        this.isMortal = true;
    }
    
    
    protected double dist(double x, double y){
            return Math.sqrt(x*x + y*y);
        }
    
    public void ai(Lolo lolo){
        
    }

    @Override
    public void autoDraw(Graphics g){
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());   
    }
}