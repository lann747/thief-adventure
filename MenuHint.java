import greenfoot.*;

public class MenuHint extends World {
    public MenuHint() {    
        super(1066, 600, 1);
        GreenfootImage bg = new GreenfootImage("MainMenu/MenuHint.png");
        setBackground(bg);
        
        addObject(new Back(),100, 530);
    }
}
