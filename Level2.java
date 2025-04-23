import greenfoot.*;

public class Level2 extends World {
    private int timer = 0;
    private int maxTime = 45 * 60;

    private int walletTimer = 0;
    private int dollarTimer = 0;
    private int heartTimer = 0;
    private int temanSpawnTimer = 0;
    private int tongSpawnTimer = 0;

    private BackgroundLayer sky, build, wall2, wall1, box, wheels, road;
    private Pencuri pencuri;
    private Polisi polisi;
    private Scoreboard hud;
    public Level2() {
        super(1066, 600, 1, false);
        
        Pencuri.clearFollowers();

        MusicManager.playLoop();

        sky = new BackgroundLayer(new GreenfootImage("BGLevel2/Sky.png"), 1);
        addObject(sky, getWidth()/2, getHeight()/2);
        
        build = new BackgroundLayer(new GreenfootImage("BGLevel2/buildings.png"), 1.5);
        addObject(build, getWidth()/2, getHeight()/2);

        wall2 = new BackgroundLayer(new GreenfootImage("BGLevel2/wall2.png"), 2.5);
        addObject(wall2, getWidth()/2, getHeight()/2);
        
        wall1 = new BackgroundLayer(new GreenfootImage("BGLevel2/wall1.png"), 2.5);
        addObject(wall1, getWidth()/2, getHeight()/2);
        
        box = new BackgroundLayer(new GreenfootImage("BGLevel2/boxes&container.png"), 2.5);
        addObject(box, getWidth()/2, getHeight()/2);
        
        wheels = new BackgroundLayer(new GreenfootImage("BGLevel2/wheels&hydrant.png"), 2.5);
        addObject(wheels, getWidth()/2, getHeight()/2);
        
        road = new BackgroundLayer(new GreenfootImage("BGLevel2/road&border.png"), 2.5);
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
        build.scroll(speed * 1.5);
        wall2.scroll(speed * 2.5);
        wall1.scroll(speed * 2.5);
        box.scroll(speed * 2.5);
        wheels.scroll(speed * 2.5);
        road.scroll(speed * 2.5);

        int secondsLeft = (maxTime - timer) / 60;
        showText(secondsLeft + "s", 583, 20);
        if (timer >= maxTime) {
            MusicManager.stop();
            Greenfoot.setWorld(new MenuVictory2());
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

        if (temanSpawnTimer > Greenfoot.getRandomNumber(250) + 300) {
            addObject(new TemanPencuri(), getWidth() + 100, 525);
            temanSpawnTimer = 0;
        }

        if (tongSpawnTimer > Greenfoot.getRandomNumber(250) + 200) {
            addObject(new Tong(), getWidth() + 100, 525);
            tongSpawnTimer = 0;
        }
    }
}
