package edu.austral.starship.base.controllers;

import edu.austral.starship.base.framework.GameFramework;
import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.framework.WindowSettings;
import edu.austral.starship.base.model.*;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.event.KeyEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class PrincipalController extends  ObservableKeyEvent implements GameFramework {
    private HashMap<String, PImage> images;
    private Model model = new Model();
    private Visitor visitor = new Visitor();
    private Player player1 = new Player("Player 1");
    private int counter = 300;

    public HashMap loadImages(ImageLoader imageLoader){
        HashMap map = new HashMap<String, PImage>();
        map.put("spaceship", imageLoader.load("spaceship.png"));
        map.put("asteroid", imageLoader.load("asteroid.png"));
        map.put("bullet", imageLoader.load("bullet.jpg"));
        map.put("heart", imageLoader.load("heart.png"));
        return map;
    }

    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        images = loadImages(imageLoader);
        windowsSettings
            .setSize(600, 600)
            .enableHighPixelDensity();
        PlayerController playerController1 = new PlayerController(player1, model);
        addObserver(playerController1);
        model.addObject(player1.getSpaceship());
        model.createAsteroid();
    }


    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
    graphics.background(255,255,255);
        if (player1.getAmtLives() == 0 || player1.getSpaceship().getGun().getAmtBullets() == 0){
            gameOver(graphics);
        } else {
            for (Integer event: keySet
                    ) {
                notifyAll(event);
            }
            player1.drawHearts(graphics, images);
            player1.drawBullets(graphics, images);
            counter = model.nextMove(counter, timeSinceLastDraw);
            List<GameObject> objects = model.getObjects();
            for (GameObject object : objects
                    ) {
                if (object.getHealth() > 0){
                    object.draw(visitor, graphics, images);
                    object.move();
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
        notifyAllKeyPressed(event.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent event) {

    }

    private void gameOver(PGraphics graphics){
        graphics.background(0,0,0 );
        graphics.text("GAME OVER", 160, 300);
        graphics.fill(255,0,0);
        graphics.textSize(50);

    }
}
