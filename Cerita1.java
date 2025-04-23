import greenfoot.*;

public class Cerita1 extends World {
    int time = 0;
    public Cerita1() {    
        super(1066, 600, 1);
        GreenfootImage bg = new GreenfootImage("MainMenu/Cerita.png");
        setBackground(bg);
        
        addObject(new Next(),975, 550);
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(getObjects(Next.class).get(0))) {
            Greenfoot.setWorld(new Level1());
        }
    }
}
