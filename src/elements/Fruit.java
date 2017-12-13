package elements;

import java.util.Timer;

public class Fruit extends BackgroundElement {
        public Timer t;
        public boolean isEaten = false;
	public Fruit(String imagename, int score, Timer t){
		super(imagename);
		this.isTransposable = true;
                this.score = score;
                this.t = t;
	}
        
        /*
        @Override
        public void autoDraw(Graphics g) {
            Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
        }
        */
        
}