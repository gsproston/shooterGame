package shootergame;

public class health extends hud {
    private int health;
    health() {
        emptyPath = System.getProperty("user.dir")+"/sprites/hud/healthEmpty.png";
        textPath = System.getProperty("user.dir")+"/sprites/hud/healthText.png";
        leftPath = System.getProperty("user.dir")+"/sprites/hud/healthLeft.png";
        midPath = System.getProperty("user.dir")+"/sprites/hud/healthMiddle.png";
        rightPath = System.getProperty("user.dir")+"/sprites/hud/healthRight.png";
        emptyXpos = 12F;
        emptyYpos = 388F;
        textXpos = 28F;
        textYpos = 390F;
        leftXpos = 16F;
        leftYpos = 397F;
        rightXpos = 160F;
        rightYpos = 397F;
        midXpos = 33F;
        midYpos = 397F;
        health = 10;
    }    
    public int getHealth() {
        return health;
    }   
    public void setHealth(int newHealth) {
        health = newHealth;
    }
    public void healthDecrease() {
        health--;
    }
}
