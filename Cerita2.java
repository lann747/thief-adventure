import greenfoot.*;

public class Cerita2 extends World {
    int time = 0;
    private GreenfootImage heartIcon;
    private GreenfootImage scoreIcon;
    private Font font = new Font("Arial", true, false, 48);
    public Cerita2() {    
        super(1066, 600, 1);
        GreenfootImage bg = new GreenfootImage("Victory/Cerita2.png");
        setBackground(bg);
        
        scoreIcon = new GreenfootImage("HUD/score.png");
        scoreIcon.scale(60, 60);
        update();
    }
    
    public void act() {
        time++;
        update();
    
        if (time == 1) {
            Greenfoot.playSound("winSound.mp3");
        }
        
        if(time >= 400) {
            Greenfoot.setWorld(new MainMenu());
        }
    }
    
    private void update() {
        GreenfootImage bg = getBackground();
        bg.clear();
        GreenfootImage base = new GreenfootImage("Victory/Cerita2.png");
        bg.drawImage(base, 0, 0);
        bg.drawImage(scoreIcon, 310, 440);
        bg.setColor(Color.WHITE);
        bg.setFont(font);
        bg.drawString("x" + GameData.skor, 375, 495);
    }
}
