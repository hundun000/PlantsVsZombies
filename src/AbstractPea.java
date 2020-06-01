import java.awt.Rectangle;

public abstract class AbstractPea {
	  private int posX;
	    protected GamePanel gp;
	    private int myLane;

	    public AbstractPea(GamePanel parent, int lane, int startX) {
	        this.gp = parent;
	        this.myLane = lane;
	        posX = startX;
	    }

	    public final void advance() {
	        Rectangle pRect = new Rectangle(posX, 130 + myLane * 120, 28, 28);
	        for (int i = 0; i < gp.getLaneZombies().get(myLane).size(); i++) {
	            Zombie z = gp.getLaneZombies().get(myLane).get(i);
	            Rectangle zRect = new Rectangle(z.getPosX(), 109 + myLane * 120, 400, 120);

	            /* edited */
	            if(attackZombie(z, pRect,zRect,i)) break;
	        }

	        setPosXX();
	    }

	    protected abstract void setPosXX();
	    
	    /* edited */
	    public boolean attackZombie(Zombie z, Rectangle pRect,Rectangle zRect,int i) {

	        final boolean zombiehasHealth = z.getHealth() >= 0;
	        boolean exit = false;

	        if (pRect.intersects(zRect)) {
	            z.setHealth(z.getHealth() - 300);

	            if (!zombiehasHealth) {
	                System.out.println("ZOMBIE DIED");

	                gp.getLaneZombies().get(myLane).remove(i);
	                GamePanel.setLevel(10);
	                exit = true;
	            }
	            gp.getLaneZombies().get(myLane).remove(this);

	        }
	        return exit;

	    }

	    public int getPosX() {
	        return posX;
	    }

	    public void setPosX(int posX) {
	        this.posX = posX;
	    }

	    public int getMyLane() {
	        return myLane;
	    }

	    public void setMyLane(int myLane) {
	        this.myLane = myLane;
	    }
}
