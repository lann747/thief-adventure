import greenfoot.*;

public class Tong extends Actor {
    private boolean sudahMeledak = false;

    public Tong() {
        setImage("Obstacle/tong.png");
        getImage().scale(50, 70);
    }

    public void act() {
        if (getWorld() == null || sudahMeledak) return;

        setLocation(getX() - 10, getY());
    
        if (getX() < -50) {
            getWorld().removeObject(this);
            return;
        }

        boolean harusMeledak = false;
    
        Pencuri pencuri = (Pencuri) getOneIntersectingObject(Pencuri.class);
        if (pencuri != null && !pencuri.sedangLompat() && isBoundingBoxColliding(this, pencuri)) {
            pencuri.kenaObstacle();
            harusMeledak = true;
        }
    
        TemanPencuri teman = (TemanPencuri) getOneIntersectingObject(TemanPencuri.class);
        if (teman != null && !teman.isJumping && isBoundingBoxColliding(this, teman)) {
            teman.mati();
            harusMeledak = true;
        }
    
        if (harusMeledak) {
            ledakkan();
        }
    }

    private void ledakkan() {
        if (sudahMeledak) return;
        sudahMeledak = true;
    
        World dunia = getWorld();
        if (dunia == null) return;
    
        dunia.addObject(new Ledakan(), getX(), getY());
        Greenfoot.playSound("ledakan.wav");
        dunia.removeObject(this);
    }

    private boolean isBoundingBoxColliding(Actor a, Actor b) {
        GreenfootImage imgA = a.getImage();
        GreenfootImage imgB = b.getImage();

        int ax = a.getX() - imgA.getWidth() / 2 + 6;
        int ay = a.getY() - imgA.getHeight() / 2 + 6;
        int aw = imgA.getWidth() - 12;
        int ah = imgA.getHeight() - 12;

        int bx = b.getX() - imgB.getWidth() / 2 + 6;
        int by = b.getY() - imgB.getHeight() / 2 + 6;
        int bw = imgB.getWidth() - 12;
        int bh = imgB.getHeight() - 12;

        return ax < bx + bw && ax + aw > bx && ay < by + bh && ay + ah > by;
    }
}
