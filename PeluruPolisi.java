import greenfoot.*;

public class PeluruPolisi extends Actor {
    public PeluruPolisi() {
        setImage("Polisi/Attack/Peluru.png");
        getImage().scale(24, 8);
    }

    public void act() {
        if (getWorld() == null) return;
    
        setLocation(getX() + 8, getY());
    
        if (getX() > getWorld().getWidth()) {
            getWorld().removeObject(this);
            return;
        }
    
        TemanPencuri teman = (TemanPencuri) getOneIntersectingObject(TemanPencuri.class);
        if (teman != null) {
            teman.mati();
            Greenfoot.playSound("damage.wav");
            getWorld().removeObject(this);
            return;
        }

        Pencuri target = (Pencuri) getOneIntersectingObject(Pencuri.class);
        if (target != null && !target.sedangLompat()) {
            if (isBoundingBoxColliding(this, target)) {
                target.kenaPeluru();
                Greenfoot.playSound("damage.wav");
                getWorld().removeObject(this);
            }
        }
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
