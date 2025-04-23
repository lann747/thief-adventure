import greenfoot.*;

public class Wallet extends Actor {
    public Wallet() {
        setImage("Item/score.png");
        getImage().scale(32,32);
    }
    public void act() {
        setLocation(getX() - 10, getY());

        if (isTouching(Pencuri.class)) {
            Pencuri p = (Pencuri) getOneIntersectingObject(Pencuri.class);
            p.tambahSkor(10);
            Greenfoot.playSound("wallet.wav");
            getWorld().removeObject(this);
        }
    }
}
