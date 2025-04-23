import greenfoot.*;

public class MainMenu extends World {
    public MainMenu() {    
        super(1066, 600, 1);
        GreenfootImage bg = new GreenfootImage("MainMenu/MainMenu.jpg");
        setBackground(bg);
        
        Pencuri.clearFollowers();
        GameData.reset();
        
        addObject(new Play(), getWidth()/2, getHeight()/2 - 140);
        
        addObject(new Hint(), getWidth()/2, getHeight()/2 - 20);
        
        addObject(new Credits(), getWidth()/2, getHeight()/2 + 100);
        
        addObject(new Exit(), getWidth()/2, getHeight()/2 + 220);
    }
}
