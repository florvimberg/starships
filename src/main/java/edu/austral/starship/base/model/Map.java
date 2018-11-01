package edu.austral.starship.base.model;

import edu.austral.starship.base.vector.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Florencia Vimberg
 */
public class Map {
    private List<Vector2> map;

    public Map() {
        this.map = new ArrayList<>(20);
    }
}
