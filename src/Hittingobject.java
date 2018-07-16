public class Hittingobject extends MovingImage{
	private double fallingv;
	public Hittingobject(String img, int x, int y, int w, int h) {
		super("Asteroid.PNG", x, y, w, h);
		fallingv = 0;
	}

	public  void fall(MovingImage platform) {
		// this method let the missille fall down randomly from any direction
		// FALL!
		fallingv += .5;
		moveByAmount(0, (int)fallingv);
	}
	
	public boolean istouch(Character c){

		int lefthx = this.getX();
		int righthx = this.getX() + this.getWidth();
		int yposih= this.getY() + this.getHeight();
		
		boolean istouch;
		
		if (c.isPointInImage(lefthx, yposih) || c.isPointInImage(righthx, yposih) )
		{
			istouch = true;
		}
		else
		{
			istouch = false;
		}
		
		return istouch;
		
	}	
}