import greenfoot.*;

public class BackgroundLayer extends Actor {
    private GreenfootImage image;
    
    private double scrollSpeed;
    private double xOffset;
    public BackgroundLayer(GreenfootImage image, double scrollSpeed) {
        this.scrollSpeed = scrollSpeed;
        this.image = new GreenfootImage(image); 
        this.image.scale(1066, 600); 
    }
    
    public void updateImage() {
        int worldWidth = getWorld().getWidth();
        int worldHeight = getWorld().getHeight();
        GreenfootImage bg = new GreenfootImage(worldWidth, worldHeight);

        int imgWidth = image.getWidth();
        int imgHeight = image.getHeight();

        int startX = -(int)(xOffset % imgWidth);
        if (startX > 0) startX -= imgWidth;

        for (int x = startX; x < worldWidth; x += imgWidth) {
            bg.drawImage(image, x, 0);
        }

        setImage(bg);
    }    

    @Override
    protected void addedToWorld(World world) {
        updateImage();
    }
    
    public void scroll(double speed) {
        xOffset = (xOffset + speed) % image.getWidth();
        updateImage();
    }

    public void stop() {
        scrollSpeed = 0;
    }
}


