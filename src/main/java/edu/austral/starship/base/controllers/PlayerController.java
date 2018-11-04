package edu.austral.starship.base.controllers;

import edu.austral.starship.base.model.Model;
import edu.austral.starship.base.model.Player;
import processing.core.PConstants;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Florencia Vimberg
 */
public class PlayerController implements KeyEventObserver {
    private Player player;
    private Model model;
    private List<Integer> keyActions; //arriba abajo izq der shoot

    public PlayerController(Player player, Model model, List<Integer> keyActions) {
        this.player = player;
        this.model = model;
        this.keyActions = keyActions;
    }

    @Override
    public void onKeyEvent(int keyEvent) {
        if(keyEvent == keyActions.get(0)){
            player.getSpaceship().moveUp();
        } else if (keyEvent == keyActions.get(1)) {
            player.getSpaceship().moveDown();
        } else if(keyEvent == keyActions.get(2)){
            player.getSpaceship().moveLeft();
        } else if (keyEvent == keyActions.get(3)){
            player.getSpaceship().moveRight();
        }
    }

    @Override
    public void onKeyEventKeyPressed(int keyEvent){
        if(keyEvent == keyActions.get(4)){
            model.shootBullet(player, 25);
        }
    }
}
