import greenfoot.*;

public class Heart extends Actor {
    public Heart() {
        setImage("Item/heart.png");
        getImage().scale(32, 32);
    }
    
    public void act() {
        setLocation(getX() - 10, getY());
        
        if (isTouching(Pencuri.class)) {
            Pencuri p = (Pencuri) getOneIntersectingObject(Pencuri.class);
            p.tambahNyawa(1);
            Greenfoot.playSound("heart.mp3");
            getWorld().removeObject(this);
        }
    }
}
