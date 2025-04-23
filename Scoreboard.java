import greenfoot.*;

public class Scoreboard extends Actor {
    private GreenfootImage heartIcon;
    private GreenfootImage scoreIcon;
    private Font font = new Font("Arial", true, false, 48);

    public Scoreboard() {
        heartIcon = new GreenfootImage("HUD/heart.png");
        heartIcon.scale(60, 60);

        scoreIcon = new GreenfootImage("HUD/score.png");
        scoreIcon.scale(60, 60);

        update();
    }

    public void act() {
        update();
    }

    private void update() {
        GreenfootImage image = new GreenfootImage(600, 100);

        image.setColor(new Color(0, 0, 0, 120));
        image.fillRect(0, 0, 200, 40);

        image.drawImage(heartIcon, 210, 35);
        image.drawImage(scoreIcon, 350, 30);

        image.setColor(Color.WHITE);
        image.setFont(font);
        image.drawString("x" + GameData.nyawa, 270, 80);
        image.drawString("x" + GameData.skor, 410, 80);

        setImage(image);
    }
}
