import greenfoot.*;

public class Level3 extends World {
    private int timer = 0;
    private int maxTime = 60 * 60;

    private int walletTimer = 0;
    private int dollarTimer = 0;
    private int heartTimer = 0;
    private int temanSpawnTimer = 0;
    private int tongSpawnTimer = 0;

    private BackgroundLayer sky, houses3, houded2, houses1, road, cross;
    private Pencuri pencuri;
    private Polisi polisi;
    private Scoreboard hud;
    public Level3() {
        super(1066, 600, 1, false);

        Pencuri.clearFollowers();
        MusicManager.playLoop();

        sky = new BackgroundLayer(new GreenfootImage("BGLevel3/sky.png"), 1);
        addObject(sky, getWidth()/2, getHeight()/2);
        
        houses3 = new BackgroundLayer(new GreenfootImage("BGLevel3/houses3.png"), 1.5);
        addObject(houses3, getWidth()/2, getHeight()/2);

        houded2 = new BackgroundLayer(new GreenfootImage("BGLevel3/houded2.png"), 2.5);
        addObject(houded2, getWidth()/2, getHeight()/2);
        
        houses1 = new BackgroundLayer(new GreenfootImage("BGLevel3/houses1.png"), 2.5);
        addObject(houses1, getWidth()/2, getHeight()/2);

        road = new BackgroundLayer(new GreenfootImage("BGLevel3/road.png"), 2.5);
        addObject(road, getWidth()/2, getHeight()/2);
        
        cross = new BackgroundLayer(new GreenfootImage("BGLevel3/crosswalk.png"), 2.5);
        addObject(cross, getWidth()/2, getHeight()/2);

        pencuri = new Pencuri();
        addObject(pencuri, 500, 480);

        polisi = new Polisi();
        addObject(polisi, 100, 480);

        hud = new Scoreboard();
        addObject(hud, 100, 20);
    }

    public void act() {
        double speed = pencuri.getSpeed();
        timer++;

        walletTimer++;
        dollarTimer++;
        heartTimer++;
        temanSpawnTimer++;
        tongSpawnTimer++;

        sky.scroll(speed * 1);
        houses3.scroll(speed * 1.5);
        houded2.scroll(speed * 2.5);
        houses1.scroll(speed * 2.5);
        road.scroll(speed * 2.5);
        cross.scroll(speed * 2.5);

        int secondsLeft = (maxTime - timer) / 60;
        showText(secondsLeft + "s", 583, 20);
        if (timer >= maxTime) {
            MusicManager.stop();
            Greenfoot.setWorld(new MenuVictory3());
        }

        if (GameData.nyawa <= 0) {
            MusicManager.stop();
            Greenfoot.setWorld(new MenuGameOver());
        }

        if (walletTimer > Greenfoot.getRandomNumber(1000) + 100) {
            addObject(new Wallet(), getWidth() + 100, Greenfoot.getRandomNumber(350) + 200);
            walletTimer = 0;
        }

        if (dollarTimer > Greenfoot.getRandomNumber(1000) + 25) {
            addObject(new Dollar(), getWidth() + 100, Greenfoot.getRandomNumber(350) + 200);
            dollarTimer = 0;
        }


        if (heartTimer > Greenfoot.getRandomNumber(250) + 1000) {
            addObject(new Heart(), getWidth() + 100, Greenfoot.getRandomNumber(350) + 200);
            heartTimer = 0;
        }

        if (temanSpawnTimer > Greenfoot.getRandomNumber(250) + 400) {
            addObject(new TemanPencuri(), getWidth() + 100, 525);
            temanSpawnTimer = 0;
        }

        if (tongSpawnTimer > Greenfoot.getRandomNumber(250) + 200) {
            addObject(new Tong(), getWidth() + 100, 525);
            tongSpawnTimer = 0;
        }
    }
}
