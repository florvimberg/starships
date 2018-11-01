package edu.austral.starship.base.model;

import java.util.List;

/**
 * @author Florencia Vimberg
 */
public class Gun {
    private int gunModel;
    private List<Bullet> bullets;

    public boolean hasBullets(){
        return !bullets.isEmpty();
    }

    public void shoot(){

    }
}
