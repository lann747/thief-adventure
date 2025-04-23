import greenfoot.*;

public class MenuVictory3 extends World {
    int time = 0;
    public MenuVictory3() {    
        super(1066, 600, 1);
        setBackground(new GreenfootImage("Victory/menang.png"));
    }

    public void act() {
        time++;
        if (time == 1) {
            Greenfoot.playSound("winSound.mp3");
        }

        if (time >= 200) {
            Greenfoot.setWorld(new Level4());
        }
    }
}
