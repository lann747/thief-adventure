import greenfoot.*;

public class Next extends Actor {
    public Next() {
        setImage("Victory/next.png");
        getImage().scale(180, 180);
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.playSound("click.wav");
            Greenfoot.delay(3);
            Greenfoot.setWorld(new Level1());
        }
        
    }
}
