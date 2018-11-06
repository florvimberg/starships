package edu.austral.starship.base.model;

/**
 * @author Florencia Vimberg
 */
public class Gun {
    private int amtBullets;

    public Gun() {
        amtBullets = 20;
    }

    public void deleteBullet(){
        amtBullets -= 1;
    }

    public void addBullet(int bullets){
        amtBullets += bullets;
    }

    public int getAmtBullets() {
        return amtBullets;
    }


}
