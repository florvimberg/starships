package edu.austral.starship.base.model;

import edu.austral.starship.base.vector.Vector2;
import processing.core.PGraphics;
import processing.core.PImage;

import java.awt.*;
import java.util.HashMap;

/**
 * @author Florencia Vimberg
 */
public class BulletPackage extends Bullet {
    private int amtBullets;

    protected BulletPackage(int size, Vector2 direction, Vector2 position, int amtBullets) {
        super(size, direction, position);
        this.amtBullets = amtBullets;
    }

    public int getAmtBullets() {
        return amtBullets;
    }

    @Override
    public void draw(Visitor visitor, PGraphics graphics, HashMap<String, PImage> images) {
        this.setShape(new Rectangle(((int) this.getPosition().getX()) + 1 , ((int) this.getPosition().getY()) + 1, getSize(),getSize()));
        visitor.drawBulletPackage(this,graphics,images);
    }

    @Override
    public void move() {
    }

    @Override
    public void collisionedWith(GameObject gameObject) {
        gameObject.collisionedWithBulletPackage(this);
    }

    @Override
    public void collisionedWithAsteroid(Asteroid asteroid) {
        this.setHealth(0);
    }

    @Override
    public void collisionedWithSpaceship(Spaceship spaceship) {
        this.setHealth(0);
    }

    @Override
    public void collisionedWithBullet(Bullet bullet) {
        this.setHealth(0);
    }
}
