import greenfoot.*;

public class Back extends Actor {
    public Back() {
        setImage("MainMenu/back.png");
        getImage().scale(180, 180);
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.playSound("click.wav");
            Greenfoot.setWorld(new MainMenu());
        }
    }
}
