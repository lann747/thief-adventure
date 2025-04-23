import greenfoot.*;

public class MenuCredits extends World {
    public MenuCredits()
    {    
        super(1066, 600, 1);
        GreenfootImage bg = new GreenfootImage("MainMenu/MenuCredits.png");
        setBackground(bg);
        
        addObject(new Back(),100, 530);
    }
}
