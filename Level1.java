import greenfoot.*;

public class Level1 extends World {
    private int timer = 0;
    private int maxTime = 30 * 60;
    private int walletTimer = 0;
    private int dollarTimer = 0;
    private int heartTimer = 0;
    private int temanSpawnTimer = 0;
    private int tongSpawnTimer = 0;
    private int boxTimer = 0;
    
    private boolean tsunamiActive = false;
    private int tsunamiTimer = 0;
    private int tsunamiFrameIndex = 0;
    private int tsunamiFrameDelay = 5;
    private int tsunamiFrameTimer = 0;

    private BackgroundLayer sky, city, road, back, houses3, mini;
    private Pencuri pencuri;
    private Scoreboard hud;
    public Level1() {    
        super(1066, 600, 1, false);
        
        Pencuri.clearFollowers();
        
        MusicManager.playLoop();
        
        sky = new BackgroundLayer(new GreenfootImage("BGLevel1/Sky.png"), 0.5);
        back = new BackgroundLayer(new GreenfootImage("BGLevel1/back.png"), 1.0);
        houses3 = new BackgroundLayer(new GreenfootImage("BGLevel1/houses3.png"), 2.0);
        city = new BackgroundLayer(new GreenfootImage("BGLevel1/houses1.png"), 2.0);
        mini = new BackgroundLayer(new GreenfootImage("BGLevel1/minishop&callbox.png"), 2.0);
        road = new BackgroundLayer(new GreenfootImage("BGLevel1/road&lamps.png"), 2.0);
        
        addObject(sky, getWidth()/2, getHeight()/2);
        addObject(back, getWidth()/2, getHeight()/2);
        addObject(houses3, getWidth()/2, getHeight()/2);
        addObject(city, getWidth()/2, getHeight()/2);
        addObject(mini, getWidth()/2, getHeight()/2);
        addObject(road, getWidth()/2, getHeight()/2);
        
        pencuri = new Pencuri();
        addObject(pencuri, 500, 480);
        
        addObject(new Polisi(), 100, 480);

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
        boxTimer++;

        sky.scroll(speed * 1);
        back.scroll(speed * 1.5);
        mini.scroll(speed * 2.5);
        houses3.scroll(speed * 2.5);
        city.scroll(speed * 2.5);
        road.scroll(speed * 2.5);
        
        int secondsLeft = (maxTime - timer) / 60;
        showText(secondsLeft + "s", 583, 20);
        if (timer >= maxTime) {
            MusicManager.stop();
            Greenfoot.setWorld(new MenuVictory());
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

        if (temanSpawnTimer > Greenfoot.getRandomNumber(250) + 200) {
            addObject(new TemanPencuri(), getWidth() + 100, 525);
            temanSpawnTimer = 0;
        }

        if (tongSpawnTimer > Greenfoot.getRandomNumber(250) + 150) {
            addObject(new Tong(), getWidth() + 100, 525);
            tongSpawnTimer = 0;
        }
    }
}
