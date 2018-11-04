package edu.austral.starship.base.controllers;

import edu.austral.starship.base.framework.GameFramework;
import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.framework.WindowSettings;
import edu.austral.starship.base.model.*;
import edu.austral.starship.base.vector.Vector2;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.event.KeyEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class PrincipalController extends  ObservableKeyEvent implements GameFramework {
    private HashMap<String, PImage> images;
    private Model model = new Model();
    private Visitor visitor = new Visitor();
    private Player player1 = new Player("Player 1", new Vector2(0,0), new Vector2(200, 500));
    private Player player2 = new Player("Player 2", new Vector2(0,0), new Vector2(300,500));
//    private Player player3 = new Player("Player 3", new Vector2(0,0), new Vector2(400,500));
    private int counter = 300;
    private List<Player> players = new ArrayList<>();
    private List<List<Integer>> keyActions =  new ArrayList<>();

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

        players.add(player1);
        players.add(player2);
//        players.add(player3);

        List<Integer> keyActions1 = new ArrayList<>();
        keyActions1.add(PConstants.UP);
        keyActions1.add(PConstants.DOWN);
        keyActions1.add(PConstants.LEFT);
        keyActions1.add(PConstants.RIGHT);
        keyActions1.add(PConstants.SHIFT);
        keyActions.add(keyActions1);

        List<Integer> keyActions2 =  new ArrayList<>();
        keyActions2.add(java.awt.event.KeyEvent.VK_W);
        keyActions2.add(java.awt.event.KeyEvent.VK_S);
        keyActions2.add(java.awt.event.KeyEvent.VK_A);
        keyActions2.add(java.awt.event.KeyEvent.VK_D);
        keyActions2.add(java.awt.event.KeyEvent.VK_1);
        keyActions.add(keyActions2);

//        List<Integer> keyActions3 =  new ArrayList<>();
//        keyActions3.add(java.awt.event.KeyEvent.VK_T);
//        keyActions3.add(java.awt.event.KeyEvent.VK_G);
//        keyActions3.add(java.awt.event.KeyEvent.VK_F);
//        keyActions3.add(java.awt.event.KeyEvent.VK_H);
//        keyActions3.add(java.awt.event.KeyEvent.VK_ALT);
//        keyActions.add(keyActions3);

        model.createAsteroid();

        for (int i = 0; i < players.size(); i++) {
            PlayerController playerController = new PlayerController(players.get(i), model, keyActions.get(i));
            addObserver(playerController);
            model.addObject(players.get(i).getSpaceship());
        }
    }


    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
    graphics.background(255,255,255);
        int xHeart = 0;
        int yHeart = 15;
        int xBullets = 65;
        int yBullets = 15;
        int xPoints = 90;
        int yPoints = 15;
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).getAmtLives() == 0 || players.get(i).getSpaceship().getGun().getAmtBullets() == 0){
                gameOver(graphics);
                return;
            } else {
                for (Integer event: keySet
                        ) {
                    notifyAll(event);
                }
                players.get(i).drawHearts(graphics, images, xHeart , yHeart);
                players.get(i).drawBullets(graphics, images, xBullets, yBullets);
                players.get(i).drawPoints(graphics, images, xPoints, yPoints);
                counter = model.nextMove(counter, timeSinceLastDraw);
                xHeart += 180;
                xBullets += 180;
                xPoints += 180;
            }
        }
        List<GameObject> objects = model.getObjects();
        for (GameObject object : objects
                ) {
            if (object.getHealth() > 0){
                object.draw(visitor, graphics, images);
                object.move();
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
        graphics.background(0,0,0);
        for (int i = 0; i < model.getObjects().size(); i++) {
            model.getObjects().remove(i);
        }
        Player winner = players.get(0);
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).getPoints() > winner.getPoints()){
                winner = players.get(i);
            }
        }
        graphics.fill(255,255,255);
        graphics.textSize(50);
        graphics.text("GAME OVER", 140, 250);
        graphics.text(winner.getName() + " wins", 140, 350);
    }
}
