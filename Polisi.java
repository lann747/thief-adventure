import greenfoot.*;

public class Polisi extends Actor {
    GreenfootImage[] runImages;
    GreenfootImage[] attackFrames;

    private int runFrameIndex = 0;
    private int attackFrameIndex = 0;
    private int frameDelay = 5;
    private int frameCounter = 0;

    private boolean isAttacking = false;
    private boolean hasShotThisAttack = false;

    private long lastShotTime = 0;
    private int shootInterval = 8000;
    private int speed = 0;

    public Polisi() {
        loadRunImages();
        loadAttackFrames();
        setImage(runImages[0]);
    }

    public void act() {
        long currentTime = System.currentTimeMillis();

        if (!isAttacking && currentTime - lastShotTime >= shootInterval +  Greenfoot.getRandomNumber(10000)) {
            isAttacking = true;
            attackFrameIndex = 0;
            hasShotThisAttack = false;
            lastShotTime = currentTime;
        }

        frameCounter++;
        if (frameCounter >= frameDelay) {
            frameCounter = 0;

            if (isAttacking) {
                animateAttack();
            } else {
                animateRun();
            }
        }
    }

    private void loadRunImages() {
        runImages = new GreenfootImage[6];
        for (int i = 0; i < runImages.length; i++) {
            runImages[i] = new GreenfootImage("Polisi/Run/Run_0" + i + ".png");
            runImages[i].scale(100, 180);
        }
    }

    private void loadAttackFrames() {
        attackFrames = new GreenfootImage[6];
        for (int i = 0; i < attackFrames.length; i++) {
            attackFrames[i] = new GreenfootImage("Polisi/Attack/Attack_0" + i + ".png");
            attackFrames[i].scale(135, 180);
        }
    }

    private void animateRun() {
        setImage(runImages[runFrameIndex]);
        runFrameIndex = (runFrameIndex + 1) % runImages.length;
    }

    private void animateAttack() {
        setImage(attackFrames[attackFrameIndex]);

        if (attackFrameIndex == 2 && !hasShotThisAttack) {
            PeluruPolisi peluru = new PeluruPolisi();
            getWorld().addObject(peluru, getX() + 30, getY());
            Greenfoot.playSound("peluru.wav");
            hasShotThisAttack = true;
        }

        attackFrameIndex++;
        if (attackFrameIndex >= attackFrames.length) {
            isAttacking = false;
            runFrameIndex = 0;
            setImage(runImages[runFrameIndex]);
        }
    }

}
