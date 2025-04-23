import greenfoot.*;

public class MenuGameOver extends World {
    public MenuGameOver() {    
        super(1066, 600, 1);
        setBackground(new GreenfootImage("GameOver/gameover.png"));
        
        Greenfoot.playSound("loseSound.mp3");
        
        addObject(new TryAgain(), 940, 530);
    }
}