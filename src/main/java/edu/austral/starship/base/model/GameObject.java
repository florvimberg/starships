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
public abstract class GameObject implements Collisionable<GameObject> {
    private float health;
    private Vector2 position;
    private Vector2 direction;
    private Shape shape;

    protected GameObject(Vector2 direction, Vector2 position, int width, int height) {
        this.direction = direction;
        this.position = position;
        this.shape = new Rectangle(((int) position.getX()), ((int) position.getY()), width,height);
        this.health = 100;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public abstract void collisionedWithBulletPackage(BulletPackage bulletPackage);

    public abstract void collisionedWithAsteroid(Asteroid asteroid);

    public abstract void collisionedWithSpaceship(Spaceship spaceship);

    public abstract void collisionedWithBullet(Bullet bullet);

    public abstract void draw(Visitor visitor, PGraphics graphics, HashMap<String, PImage> images);

    public abstract void move();

    public abstract void outOfBounds();

    //metodos giladas q ni se si estan bien
}
