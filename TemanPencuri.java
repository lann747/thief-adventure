import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

public class TemanPencuri extends Actor {
    GreenfootImage[] idleImages;
    GreenfootImage[] runImages;
    GreenfootImage[] jumpImages;
    GreenfootImage[] deadImages;

    Actor target;

    int velocityY = 0;

    int idleIndex = 0;
    int runIndex = 0;
    int jumpIndex = 0;
    int deadIndex = 0;

    int frameDelay = 5;
    int jumpDelay = 10; 
    int deadDelay = 5;

    int frameCount = 0;
    int jumpTimer = -1;
    int incomingJumpSpeed = -25;
    int deadFrameCounter = 0;

    boolean onGround = true;
    boolean isFollowing = false;
    boolean isJumping = false;
    boolean sedangMati = false;
    boolean sudahDihapus = false;

    public TemanPencuri() {
        loadIdleImages();
        loadRunImages();
        loadJumpImages();
        loadDeadImages();
        setImage(idleImages[0]);
    }

    public void act() {
        if (getWorld() == null) return;
        
        if (sedangMati) {
            animateMati();
            return;
        }

        animate();
        setLocation(getX() - 10, getY());

        if (getX() <= 0) {
            if (getWorld() != null) {
                getWorld().removeObject(this);
            }
            return;
        }

        if (!isFollowing) {
            checkForPencuri();
        } else {
            followPencuri();
            handleJumpFollow();
        }

        applyGravity();
    }

    void checkForPencuri() {
        if (getWorld() == null) return;
    
        Pencuri p = (Pencuri)getOneIntersectingObject(Pencuri.class);
        if (p != null) {
            isFollowing = true;
    
            if (Pencuri.followers.size() > 0) {
                Actor lastFollower = Pencuri.followers.get(Pencuri.followers.size() - 1);
                if (lastFollower.getWorld() != null) {
                    target = lastFollower;
                } else {
                    target = p;
                }
            } else {
                target = p;
            }
    
            Pencuri.followers.add(this);
    
            if (target != null && target.getWorld() != null && getWorld() != null) {
                int jarakX = 30;
                setLocation(target.getX() - jarakX, target.getY());
            }
    
            idleIndex = 0;
            runIndex = 0;
            jumpIndex = 0;
        }
    }

    void followPencuri() {
        if (getWorld() == null || target == null || target.getWorld() == null) return;
    
        int dx = target.getX() - getX();
        if (dx > 30) {
            setLocation(getX() + 10, getY());
        }
    }

    public void jump() {
        velocityY = incomingJumpSpeed;
        onGround = false;
        isJumping = true;
        jumpIndex = 0;
    }

    void handleJumpFollow() {
        if (jumpTimer > 0) {
            jumpTimer--;
        } else if (jumpTimer == 0) {
            jump();
            jumpTimer = -1;
        }
    }

    public void mulaiLoncatDenganDelay(int jumpSpeed) {
        if (onGround && jumpTimer == -1) {
            this.incomingJumpSpeed = jumpSpeed;
            jumpTimer = Greenfoot.getRandomNumber(10);
        }
    }

    public void mati() {
        sedangMati = true;
        deadIndex = 0;
        deadFrameCounter = 0;

        if (Pencuri.followers.contains(this)) {
            Pencuri.followers.remove(this);
        }
    }

    void applyGravity() {
        velocityY += 1;
        setLocation(getX(), getY() + velocityY);

        if (getY() >= 480) {
            setLocation(getX(), 480);
            velocityY = 0;
            onGround = true;
            isJumping = false;
        }
    }

    void animate() {
        frameCount++;
        if (frameCount >= frameDelay) {
            frameCount = 0;

            if (isJumping) {
                setImage(jumpImages[jumpIndex]);
                jumpIndex = (jumpIndex + 1) % jumpImages.length;
            } else if (isFollowing) {
                setImage(runImages[runIndex]);
                runIndex = (runIndex + 1) % runImages.length;
            } else {
                setImage(idleImages[idleIndex]);
                idleIndex = (idleIndex + 1) % idleImages.length;
            }
        }
    }

    void animateMati() {
        if (getWorld() == null) return;
    
        if (!sudahDihapus) {
            deadFrameCounter++;
    
            if (deadIndex < deadImages.length && deadFrameCounter >= deadDelay) {
                deadFrameCounter = 0;
    
                if (getWorld() != null) {
                    setImage(deadImages[deadIndex]);
                    deadIndex++;
                }
            } else if (deadIndex >= deadImages.length) {
                sudahDihapus = true;
            }
        }
    
        if (sudahDihapus && getWorld() != null) {
            getWorld().removeObject(this);
        }
    }

    void loadIdleImages() {
        idleImages = new GreenfootImage[7];
        for (int i = 0; i < idleImages.length; i++) {
            idleImages[i] = new GreenfootImage("TemanPencuri/Idle/Idle_0" + i + ".png");
                idleImages[i].scale(85, 180);
        }
    }

    void loadRunImages() {
        runImages = new GreenfootImage[6];
        for (int i = 0; i < runImages.length; i++) {
            runImages[i] = new GreenfootImage("TemanPencuri/Run/Run_0" + i + ".png");
            runImages[i].scale(100, 180);
        }
    }

    void loadJumpImages() {
        jumpImages = new GreenfootImage[8];
        for (int i = 0; i < jumpImages.length; i++) {
            jumpImages[i] = new GreenfootImage("TemanPencuri/Jump/Jump_0" + i + ".png");
            jumpImages[i].scale(110, 280);
        }
    }

    void loadDeadImages() {
        deadImages = new GreenfootImage[5];
        for (int i = 0; i < deadImages.length; i++) {
            deadImages[i] = new GreenfootImage("TemanPencuri/Dead/Dead_0" + i + ".png");
            deadImages[i].scale(170, 180);
        }
    }
}
