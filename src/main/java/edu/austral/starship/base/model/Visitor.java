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
        graphics.image(images.get("spaceship"), spaceship.getPosition().getX(), spaceship.getPosition().getY(), 40,40);
    }

    public void drawAsteroid(Asteroid asteroid, PGraphics graphics, HashMap<String, PImage> images){
        graphics.image(images.get("asteroid"),asteroid.getPosition().getX(), asteroid.getPosition().getY(), 35,35);
    }

    public void drawBullet(Bullet bullet, PGraphics graphics, HashMap<String, PImage> images){
        graphics.image(images.get("bullet"), bullet.getPosition().getX(), bullet.getPosition().getY(), bullet.getSize(),bullet.getSize());
    }

    public void drawBulletPackage(BulletPackage bulletPackage, PGraphics graphics, HashMap<String, PImage> images){
        graphics.text(bulletPackage.getAmtBullets(), bulletPackage.getPosition().getX(), bulletPackage.getPosition().getY());
        graphics.image(images.get("bullet"), bulletPackage.getPosition().getX(), bulletPackage.getPosition().getY(), bulletPackage.getSize(), bulletPackage.getSize());
    }
}
