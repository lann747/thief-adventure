import greenfoot.*; 

public class Ledakan extends Actor {
    private int duration = 20;

    public Ledakan() {
        setImage("Obstacle/ledakan.png");
    }

    public void act() {
        duration--;
        if (duration <= 0) {
            getWorld().removeObject(this);
        }
    }
}
