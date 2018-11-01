package edu.austral.starship.base.model;

import edu.austral.starship.base.collision.Collisionable;
import edu.austral.starship.base.vector.Vector2;
import processing.core.PGraphics;
import processing.core.PImage;

import java.awt.*;
import java.util.HashMap;

/**
 * @author Florencia Vimberg
 */
public class Bullet extends GameObject {
    private int size;

    protected Bullet(int size, Vector2 direction, Vector2 position) {
        super(direction, position, size, size);
        this.size =  size;
    }

    @Override
    public void draw(Visitor visitor, PGraphics graphics, HashMap<String, PImage> images) {
        this.setShape(new Rectangle(((int) this.getPosition().getX()) + 1 , ((int) this.getPosition().getY()) + 1, size,size));
        visitor.drawBullet(this,graphics,images);
    }

    @Override
    public void move() {
        setPosition(new Vector2(getPosition().getX(), getPosition().getY() -1));
    }

    @Override
    public void outOfBounds() {

    }

    public int getSize() {
        return size;
    }


    @Override
    public void collisionedWith(GameObject gameObject) {
        gameObject.collisionedWithBullet(this);
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

    }
}
