import greenfoot.*;

public class TryAgain extends Actor {
    public TryAgain() {
        setImage(new GreenfootImage("GameOver/tryagain.png"));
        getImage().scale(280, 180);
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (mouse != null) {
                int imageW = getImage().getWidth();
                int imageH = getImage().getHeight();

                int clickX = mouse.getX() - getX() + imageW / 2;
                int clickY = mouse.getY() - getY() + imageH / 2;

                if (clickX >= 0 && clickX < imageW && clickY >= 0 && clickY < imageH) {
                    int alpha = getImage().getColorAt(clickX, clickY).getAlpha();
                    if (alpha > 10) {
                        Greenfoot.playSound("click.wav");
                        GameData.reset();
                        Greenfoot.setWorld(new Level1());
                    }
                }
            }
        }
    }
}
