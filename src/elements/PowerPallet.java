package elements;

import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;

public class PowerPallet extends Element {
	

	public PowerPallet(String imagename){
		super(imagename);
		this.isTransposable = true;
                this.score = 50;
	}
        
        @Override
        public void autoDraw(Graphics g){
            Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
        }
}
