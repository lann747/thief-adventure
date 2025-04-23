import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

public class Pencuri extends Actor {
    GreenfootImage[] runImages;
    GreenfootImage[] jumpImages;
    GreenfootImage[] deadImages;

    public static List<TemanPencuri> followers = new ArrayList<>();
    
    int frameDelay = 5;
    int frameCount = 0;

    int speed = 2;
    int ySpeed = 0;
    
    int runIndex = 0;
    int jumpIndex = 0;
    int deadIndex = 0;
    
    boolean isJumping = false;
    boolean isDead = false;

    public Pencuri() {
        if (GameData.nyawa <= 0) {
            isDead = true;
        }
        loadRunImages();
        loadJumpImages();
        loadDeadImages();
        setImage(runImages[0]);
    }

    public void act() {
        if (isDead) {
            animateDead();
            return;
        }
        
        Pencuri.bersihkanFollowersInvalid();
        
        handleMovement();
        applyGravity();
        animate();
    }

    public int getNyawa() {
        return GameData.nyawa;
    }

    public int getSkor() {
        return GameData.skor;
    }

    public void kenaPeluru() {
        GameData.nyawa--;
    }

    public void kenaObstacle() {
        GameData.nyawa--;
    }

    public void tambahSkor(int jumlah) {
        GameData.skor += jumlah;
    }

    public void tambahNyawa(int jumlah) {
        GameData.nyawa += jumlah;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean sedangLompat() {
        return isJumping;
    }
    
    public static void clearFollowers() {
        followers.clear();
    }
    
    public static void bersihkanFollowersInvalid() {
        followers.removeIf(f -> f.getWorld() == null);
    }

    private void handleMovement() {
        if (Greenfoot.isKeyDown("space") && !isJumping) {
            ySpeed = -30;
            isJumping = true;
            jumpIndex = 0;
    
            List<TemanPencuri> toRemove = new ArrayList<>();
    
            for (TemanPencuri teman : followers) {
                if (teman.getWorld() != null) {
                    teman.mulaiLoncatDenganDelay(-20);
                } else {
                    toRemove.add(teman);
                }
            }
    
            followers.removeAll(toRemove);
        }
    }

    private void applyGravity() {
        setLocation(getX(), getY() + ySpeed);
        ySpeed += 2;

        if (getY() >= 480) {
            setLocation(getX(), 480);
            ySpeed = 0;
            isJumping = false;
        }
    }

    private void animate() {
        frameCount++;
        if (frameCount < frameDelay) return;
        frameCount = 0;

        if (isJumping) {
            setImage(jumpImages[jumpIndex]);
            jumpIndex = (jumpIndex + 1) % jumpImages.length;
        } else {
            setImage(runImages[runIndex]);
            runIndex = (runIndex + 1) % runImages.length;
        }
    }

    private void animateDead() {
        setImage(deadImages[deadIndex]);
        deadIndex = (deadIndex + 1) % deadImages.length;
    }

    private void loadRunImages() {
        runImages = new GreenfootImage[6];
        for (int i = 0; i < runImages.length; i++) {
            runImages[i] = new GreenfootImage("Pencuri/Run/Run_0" + i + ".png");
            runImages[i].scale(100, 180);
        }
    }

    private void loadJumpImages() {
        jumpImages = new GreenfootImage[8];
        for (int i = 0; i < jumpImages.length; i++) {
            jumpImages[i] = new GreenfootImage("Pencuri/Jump/Jump_0" + i + ".png");
            jumpImages[i].scale(110, 280);
        }
    }

    private void loadDeadImages() {
        deadImages = new GreenfootImage[9];
        for (int i = 0; i < deadImages.length; i++) {
            deadImages[i] = new GreenfootImage("Pencuri/Dead/Dead_0" + i + ".png");
            deadImages[i].scale(100, 180);
        }
    }
}
