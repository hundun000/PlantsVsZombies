import java.awt.Rectangle;

public class Facade {
    private GamePanel gp;
    private Zombie zombie;
    private Pea pea;
    private FreezePea freezePea;

    public Facade (GamePanel gp){
        this.gp = gp;
    }


    public void gameOver() {
        gp.getMessageDialog().gameOverDialog();
        GameWindow.begin();
    }

    public void zombieCoillderAtPea(int lane, int posX) {
        Rectangle pRect = new Rectangle(posX, 130 + lane * 120, 28, 28);
        for (int i = 0; i < gp.getLaneZombies().get(lane).size(); i++) {
            Zombie z = gp.getLaneZombies().get(lane).get(i);
            Rectangle zRect = new Rectangle(z.getPosX(), 109 + lane * 120, 400, 120);

            if(pea.attackZombie(z, pRect,zRect,i)) break;
        }
    }

    public void zombieCoillderAtFreezePea(int lane, int posX) {
        Rectangle pRect = new Rectangle(posX, 130 + lane * 120, 28, 28);
        for (int i = 0; i < gp.getLaneZombies().get(lane).size(); i++) {
            Zombie z = gp.getLaneZombies().get(lane).get(i);
            Rectangle zRect = new Rectangle(z.getPosX(), 109 + lane * 120, 400, 120);

            if(freezePea.attackZombie(z, pRect,zRect,i)) break;
        }
    }

    public Collider peaCoillder(int lane, int posX) {
        Collider collided = null;

        for (int object = lane * 9; object < (lane + 1)* 9; object++) {
            final boolean intersectPlant = gp.getColliders()[object].assignedPlant != null && gp.getColliders()[object].isInsideCollider(posX);
            if (intersectPlant) {
                collided = gp.getColliders()[object];
            }
        }
        return collided;
    }

    public void setPea(Pea pea) {
        this.pea = pea;
    }

    public void setFreezePea(FreezePea freezePea) {
        this.freezePea = freezePea;
    }
}