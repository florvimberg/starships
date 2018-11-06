package edu.austral.starship.base.model;

import processing.core.PGraphics;
import processing.core.PImage;

import java.util.HashMap;

/**
 * @author Florencia Vimberg
 */
public interface IVisitor {
    void drawSpaceship(Spaceship spaceship, PGraphics graphics, HashMap<String, PImage> images);
    void drawBullet(Bullet bullet, PGraphics graphics, HashMap<String, PImage> images);
    void drawAsteroid(Asteroid asteroid, PGraphics graphics, HashMap<String, PImage> images);
    void drawBulletPackage(BulletPackage bulletPackage, PGraphics graphics, HashMap<String, PImage> images);
}
