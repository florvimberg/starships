package edu.austral.starship.base.controllers;

/**
 * @author Florencia Vimberg
 */
public interface KeyEventObserver {
    void onKeyEvent(int keyCode);

    void onKeyEventKeyPressed(int keyCode);
}
