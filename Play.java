import greenfoot.*;

public class Play extends Actor
{
    public Play() {
        setImage("MainMenu/play.png");
        getImage().scale(180, 180);
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
                        Greenfoot.delay(3);
                        Greenfoot.setWorld(new Cerita1());
                    }
                }
            }
        }
    }
}
