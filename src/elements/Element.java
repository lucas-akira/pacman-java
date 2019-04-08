package elements;

import utils.Consts;
import utils.Position;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 * Projeto de POO 2017
 * 
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public abstract class Element implements Serializable{

    protected ImageIcon imageIcon;
    protected Position pos;
    protected boolean isTransposable; // Pode passar por cima?
    protected boolean isMortal;       // Se encostar, morre?
    protected int score;
    
    
    // Essa função é responsável por alterar a imagem de um elemento já criado
    final public void changeImage(String imageName){
        try {
            // A linha abaixo identifica o caminho da imagem que foi passada no construtor
            //imageIcon = new ImageIcon( Element.class.getResource(File.separator + imageName) );
            imageIcon = new ImageIcon( Element.class.getClassLoader().getResource(imageName) );
            Image img = imageIcon.getImage();
            BufferedImage bi = new BufferedImage(Consts.CELL_SIZE, Consts.CELL_SIZE, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.createGraphics();
            g.drawImage(img, 0, 0, Consts.CELL_SIZE, Consts.CELL_SIZE, null);
            imageIcon = new ImageIcon(bi);
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Construtor
    protected Element(String imageName) {
        this.pos = new Position(1, 1);
        this.isTransposable = true;
        this.isMortal = false;
        this.score = 0;
        
        this.changeImage(imageName);
    }
    
    public boolean overlap(Element elem) {
        double xDist = Math.abs(elem.pos.getX() - this.pos.getX());
        double yDist = Math.abs(elem.pos.getY() - this.pos.getY());
        
        if (xDist < 1.0 && yDist < 1.0)
            return true;
        else
            return false;
    }

    public String getStringPosition() {
        return ("(" + pos.getX() + ", " + pos.getY() + ")");
    }
    
    public boolean setPosition(double x, double y) {
        return pos.setPosition(x, y);
    }
    
    public Position getPosition(){
        return this.pos;
    }

    public boolean isTransposable() {
        return isTransposable;
    }

    public void setTransposable(boolean isTransposable) {
        this.isTransposable = isTransposable;
    }

    // É a função responsável por chamar a Drawing para desenhar o elemento
    abstract public void autoDraw(Graphics g);

    public boolean moveUp() {
        return this.pos.moveUp();
    }

    public boolean moveDown() {
        return this.pos.moveDown();
    }

    public boolean moveRight() {
        return this.pos.moveRight();
    }

    public boolean moveLeft() {
        return this.pos.moveLeft();
    }
    
    public boolean isMortal(){
        return isMortal;
    }
    
    public int getScore(){
        return this.score;
    }

    public boolean isMortal(Element eTemp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setMortal(boolean tr){
        this.isMortal = tr;
    }
}
