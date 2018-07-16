public class Character extends MovingImage {
	// FIELDS
	private double vx, vy;
	private boolean surface;
	// CONSTRUCTOR
	public Character(int x, int y) {
		super("mario.png",x,y,50,50);
		
		vx = vy = 0; 
		surface = false;
	}
	
	// METHODS
	public void walk(int dir) {
		// WALK!
		// dir:1 - move right, -1 move right
		vx = dir*10;
		moveByAmount((int)vx, 0);
	}
	
	public void jump() {
		// JUMP!
		if (surface == true)
		{
			vy = -15;
			moveByAmount(0, (int)vy);
		}
	}
	
	public void falldown() {
		// jump down!
		if (surface == false)
		{
			vy = 15;
			moveByAmount(0, (int)vy);
		}
	}
	
	public void quit() {
		// quit
			vy = 20000;
			moveByAmount(0, (int)vy);
	}
	
	public void fall(MovingImage platform) {
		// FALL!
		surface = false;
		vy += .5;
	
		
		if (platform.isPointInImage(getX(), getY()+ getHeight()) == true)
		{
			surface = true;
			vy = 0;
		}
		moveByAmount(0, (int)vy);
	}
	
	
}