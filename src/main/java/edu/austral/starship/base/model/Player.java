package edu.austral.starship.base.model;

import edu.austral.starship.base.controllers.SpaceshipObserver;
import edu.austral.starship.base.vector.Vector2;
import processing.core.PGraphics;
import processing.core.PImage;

import java.util.HashMap;

/**
 * @author Florencia Vimberg
 */
public class Player implements SpaceshipObserver {
    private Spaceship spaceship;
    private int amtLives;
    private String name;
    private float points;

    public Player(String name) {
        this.name = name;
        spaceship = new Spaceship(new Vector2(0,0), new Vector2(270,550));
        spaceship.addObserver(this);
        amtLives = 3;
        points = 0;
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public int getAmtLives() {
        return amtLives;
    }

    public String getName() {
        return name;
    }

    public float getPoints() {
        return points;
    }

    @Override
    public void onCrash() {
        amtLives -=1;
    }

    public void drawHearts(PGraphics graphics, HashMap<String,PImage> images) {
        int x = 0;
        int y = 15;
        graphics.text(name, 5,15);
        graphics.fill(0,0,0);
        graphics.textSize(14);
        for (int i = 0; i < this.getAmtLives(); i++) {
            graphics.image(images.get("heart"), x, y,20,25);
            x += 20;
        }
    }

    public void drawBullets(PGraphics graphics, HashMap<String, PImage> images){
        int x = 65;
        int y = 15;
        graphics.text(spaceship.getGun().getAmtBullets(), x, y);
        y = 20;
        graphics.image(images.get("bullet"), x, y , 15,15);

    }
}
