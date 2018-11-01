package edu.austral.starship.base.controllers;

import edu.austral.starship.base.model.Model;
import edu.austral.starship.base.model.Player;
import processing.core.PConstants;

import processing.event.KeyEvent;

/**
 * @author Florencia Vimberg
 */
public class PlayerController implements KeyEventObserver {
    private Player player;
    private Model model;

    public PlayerController(Player player, Model model) {
        this.player = player;
        this.model = model;
    }

    @Override
    public void onKeyEvent(int keyEvent) {
        switch (keyEvent){
            case PConstants.UP:
                player.getSpaceship().moveUp();
                break;
            case PConstants.DOWN:
                player.getSpaceship().moveDown();
                break;
            case PConstants.LEFT:
                player.getSpaceship().moveLeft();
                break;
            case PConstants.RIGHT:
                player.getSpaceship().moveRight();
                break;
        }
    }

    @Override
    public void onKeyEventKeyPressed(int keyEvent){
        switch (keyEvent){
            case PConstants.SHIFT:
                model.shootBullet(player, 25);
                break;
        }
    }
}
