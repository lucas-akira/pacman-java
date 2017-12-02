package elements;

import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;

public class PacDot extends Element {
	

	public PacDot(String imagename){
		super(imagename);
		this.isTransposable = true;
                this.score = 10;
	}
        
        @Override
        public void autoDraw(Graphics g){
            Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
        }
}