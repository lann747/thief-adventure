import greenfoot.*;

public class Dollar extends Actor
{
    public Dollar() {
        setImage("Item/dollar.png");
        getImage().scale(32,32);
    }
    
    public void act() {
        setLocation(getX() - 10, getY());

        if (isTouching(Pencuri.class)) {
            Pencuri p = (Pencuri) getOneIntersectingObject(Pencuri.class);
            p.tambahSkor(5);
            Greenfoot.playSound("gem.wav");
            getWorld().removeObject(this);
        }
    }
}
