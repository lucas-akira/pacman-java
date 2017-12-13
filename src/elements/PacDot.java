package elements;

public class PacDot extends BackgroundElement  {
	

	public PacDot(String imagename){
		super(imagename);
		this.isTransposable = true;
                this.score = 10;
	}
        /*
        @Override
        public void autoDraw(Graphics g){
            Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
        }
        */
}