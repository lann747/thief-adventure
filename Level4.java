import greenfoot.*;

public class Level4 extends World {
    private int timer = 0;
    private int maxTime = 75 * 60;

    private int walletTimer = 0;
    private int dollarTimer = 0;
    private int heartTimer = 0;
    private int temanSpawnTimer = 0;
    private int tongSpawnTimer = 0;

    private BackgroundLayer sky, houses, houses2, fontain, houses1, umbrella, road;
    private Pencuri pencuri;
    private Polisi polisi;
    private Scoreboard hud;
    public Level4() {
        super(1066, 600, 1, false);

        Pencuri.clearFollowers();
        MusicManager.playLoop();

        sky = new BackgroundLayer(new GreenfootImage("BGLevel4/Sky.png"), 1);
        addObject(sky, getWidth()/2, getHeight()/2);
        
        houses = new BackgroundLayer(new GreenfootImage("BGLevel4/houses.png"), 2.5);
        addObject(houses, getWidth()/2, getHeight()/2);

        houses2 = new BackgroundLayer(new GreenfootImage("BGLevel4/houses2.png"), 2.5);
        addObject(houses2, getWidth()/2, getHeight()/2);
        
        fontain = new BackgroundLayer(new GreenfootImage("BGLevel4/fountain&bush.png"), 2.5);
        addObject(fontain, getWidth()/2, getHeight()/2);

        houses1 = new BackgroundLayer(new GreenfootImage("BGLevel4/houses1.png"), 2.5);
        addObject(houses1, getWidth()/2, getHeight()/2);
        
        umbrella = new BackgroundLayer(new GreenfootImage("BGLevel4/umbrella&policebox.png"), 2.5);
        addObject(umbrella, getWidth()/2, getHeight()/2);

        road = new BackgroundLayer(new GreenfootImage("BGLevel4/road.png"), 2.5);
        addObject(road, getWidth()/2, getHeight()/2);
        
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
        houses.scroll(speed * 2.5);
        houses2.scroll(speed * 2.5);
        fontain.scroll(speed * 2.5);
        houses1.scroll(speed * 2.5);
        umbrella.scroll(speed * 2.5);
        road.scroll(speed * 2.5);

        int secondsLeft = (maxTime - timer) / 60;
        showText(secondsLeft + "s", 583, 20);
        if (timer >= maxTime) {
            MusicManager.stop();
            Greenfoot.setWorld(new Cerita2());
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

        if (temanSpawnTimer > Greenfoot.getRandomNumber(250) + 500) {
            addObject(new TemanPencuri(), getWidth() + 100, 525);
            temanSpawnTimer = 0;
        }

        if (tongSpawnTimer > Greenfoot.getRandomNumber(250) + 200) {
            addObject(new Tong(), getWidth() + 100, 525);
            tongSpawnTimer = 0;
        }
    }
}
