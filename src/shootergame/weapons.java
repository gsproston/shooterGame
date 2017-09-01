package shootergame;

public class weapons {
    public static String weapon[];
    private int clipSize[], clips[], bulletsInClip[], weaponDifference[], bulletSpeedMultiplier[];
    private int numWeapons = 3;
    public weapons() {
        weapon = new String[numWeapons];
        clipSize = new int[numWeapons];
        clips = new int[numWeapons];
        bulletsInClip = new int[numWeapons];
        bulletSpeedMultiplier = new int[numWeapons];
        weaponDifference = new int[numWeapons];
        
        //start array
        
        weapon[0] = "pistol";
        clipSize[0] = 7;
        clips[0] = 1;
        bulletsInClip [0] = 7;
        bulletSpeedMultiplier[0] = 10;
        weaponDifference[0] = 0;
        weapon[1] = "Slingshot";
        clipSize[1] = 1;
        clips[1] = 1;
        bulletsInClip [1] = 1;
        bulletSpeedMultiplier[1] = 5;
        weaponDifference[1] = 0;
        weapon[2] = "BaconShotgun";
        clipSize[2] = 4;
        clips[2] = 1;
        bulletsInClip [2] = 4;
        bulletSpeedMultiplier[2] = 7;
        weaponDifference[2] = 0;
        
        //end array      
        
    }
    public int getNumWeapons() {
        return numWeapons;
    }
    public String getWeapon(int num) {
        return weapon[num];
    }
    public int getClipSize(int num) {
        return clipSize[num];
    }
    public int getClips(int num) {
        return clips[num];
    }
    public int getBulletsInClip(int num) {
        return bulletsInClip[num];
    }
    public int getBulletSpeedMultiplier(int num) {
        return bulletSpeedMultiplier[num];
    }
    public int getWeaponDifference(int num) {
        return weaponDifference[num];
    }
    public void bulletDecrease(int num, int decrease) {
        if (bulletsInClip[num] > 0) {
            bulletsInClip[num] = bulletsInClip[num] - decrease;
        }
    }
    public void reload(int num) {
        bulletsInClip[num] = clipSize[num];
    }
}
