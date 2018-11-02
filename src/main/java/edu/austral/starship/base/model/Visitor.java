package edu.austral.starship.base.model;

import processing.core.PGraphics;
import processing.core.PImage;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

/**
 * @author Florencia Vimberg
 */
public class Visitor {
    public void drawSpaceship(Spaceship spaceship, PGraphics graphics, HashMap<String, PImage> images){
        drawLimits(spaceship.getShape(), graphics);
        graphics.image(images.get("spaceship"), spaceship.getPosition().getX(), spaceship.getPosition().getY(), 40,40);
    }

    public void drawAsteroid(Asteroid asteroid, PGraphics graphics, HashMap<String, PImage> images){
        drawLimits(asteroid.getShape(), graphics);
        graphics.image(images.get("asteroid"),asteroid.getPosition().getX(), asteroid.getPosition().getY(), 35,35);
    }

    public void drawBullet(Bullet bullet, PGraphics graphics, HashMap<String, PImage> images){
        drawLimits(bullet.getShape(), graphics);
        graphics.image(images.get("bullet"), bullet.getPosition().getX(), bullet.getPosition().getY(), bullet.getSize(),bullet.getSize());
    }

    public void drawBulletPackage(BulletPackage bulletPackage, PGraphics graphics, HashMap<String, PImage> images){
        drawLimits(bulletPackage.getShape(), graphics);
        graphics.text(bulletPackage.getAmtBullets(), bulletPackage.getPosition().getX(), bulletPackage.getPosition().getY());
        graphics.image(images.get("bullet"), bulletPackage.getPosition().getX(), bulletPackage.getPosition().getY(), bulletPackage.getSize(), bulletPackage.getSize());
    }


    void drawLimits(Shape shape, PGraphics graphics){
        float x = (float) shape.getBounds2D().getX();
        float y = (float) shape.getBounds2D().getY();
        float width = (float) shape.getBounds2D().getWidth();
        float height = (float) shape.getBounds2D().getHeight();
        graphics.point(x, y);
        graphics.point((x + width), y);
        graphics.point(x, (y + height));
        graphics.point( x + width, y + height);
    }
}
