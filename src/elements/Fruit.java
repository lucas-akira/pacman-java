package elements;

import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;

public class Fruit extends Element {
	
	private int timeOnScreen() {
            return 0;
        }


	public Fruit(String imagename){
		super(imagename);
		this.isTransposable = true;
	}
        public void autoDraw(Graphics g) {
            
        }
}