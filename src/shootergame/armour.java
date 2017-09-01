package shootergame;

public class armour extends hud {
    private String nextEmptyPath, secondEmptyPath, nextSecondEmptyPath, endSecondEmptyPath, secondLeftPath;
    private int maxArmour, armour;
    private float nextXpos, nextYpos, secondEmptyXpos, secondEmptyYpos, nextSecondXpos, nextSecondYpos, secondEndXpos, secondEndYpos;
    armour() {
        emptyPath = System.getProperty("user.dir")+"/sprites/hud/armourFirstEmpty.png";
        nextEmptyPath = System.getProperty("user.dir")+"/sprites/hud/armourFirstNextEmpty.png";
        secondEmptyPath = System.getProperty("user.dir")+"/sprites/hud/armourSecondEmpty.png";
        nextSecondEmptyPath = System.getProperty("user.dir")+"/sprites/hud/armourSecondNextEmpty.png";
        endSecondEmptyPath = System.getProperty("user.dir")+"/sprites/hud/armourSecondEndEmpty.png";
        secondLeftPath = System.getProperty("user.dir")+"/sprites/hud/armourSecondLeft.png";
        leftPath = System.getProperty("user.dir")+"/sprites/hud/armourLeft.png";
        midPath = System.getProperty("user.dir")+"/sprites/hud/armourMiddle.png";
        rightPath = System.getProperty("user.dir")+"/sprites/hud/armourRight.png";
        emptyXpos = 12F;
        emptyYpos = 416F;
        secondEmptyXpos = 12F;
        secondEmptyYpos = 425F;
        leftXpos = 16F;
        leftYpos = 416F;
        midXpos = 25F;
        midYpos = 416F;
        rightXpos = 33F;
        rightYpos = 416F;
        nextXpos = 40F;
        nextYpos = 416F;
        nextSecondXpos = 24F;
        nextSecondYpos = 425F;
        secondEndXpos = 168F;
        secondEndYpos = 425F;
        maxArmour = 40;
        armour = 40;
    }
    public String getNextEmptyPath() {
        return nextEmptyPath;
    }
    public String getSecondEmptyPath() {
        return secondEmptyPath;
    }
    public String getSecondLeftPath() {
        return secondLeftPath;
    }
    public String getNextSecondPath() {
        return nextSecondEmptyPath;
    }
    public String getSecondEndPath() {
        return endSecondEmptyPath;
    }
    public int getArmour() {
        return armour;
    }
    public int getMaxArmour() {
        return maxArmour;
    }
    public float getSecondEndXpos() {
        return secondEndXpos;
    }
    public float getSecondEndYpos() {
        return secondEndYpos;
    }
    public float getNextSecondXpos() {
        return nextSecondXpos;
    }
    public float getNextSecondYpos() {
        return nextSecondYpos;
    }
    public float getSecondEmptyXpos() {
        return secondEmptyXpos;
    }
    public float getSecondEmptyYpos() {
        return secondEmptyYpos;
    }
    public float getNextXpos() {
        return nextXpos;
    }
    public float getNextYpos() {
        return nextYpos;
    }
}
