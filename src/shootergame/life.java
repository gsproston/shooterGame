package shootergame;

public class life extends hud {
    private int life = 96;
    life() {
        emptyPath = System.getProperty("user.dir")+"/sprites/hud/lifeEmpty.png";
        textPath = System.getProperty("user.dir")+"/sprites/hud/lifeText.png";
        leftPath = System.getProperty("user.dir")+"/sprites/hud/lifeLeft.png";
        midPath = System.getProperty("user.dir")+"/sprites/hud/lifeMiddle.png";
        rightPath = System.getProperty("user.dir")+"/sprites/hud/lifeRight.png";
        emptyXpos = 12F;
        emptyYpos = 7F;
        textXpos = 27F;
        textYpos = 9F;
        leftXpos = 14F;
        leftYpos = 16F;
        rightXpos = 777F;
        rightYpos = 16F;
        midXpos = 24F;
        midYpos = 16F;
    }    
        public int getLife() {
            return life;
        }
        public void lifeDecrease() {
            life--;
        }
        public void setLife(int newLife) {
            life = newLife;
        }
}
