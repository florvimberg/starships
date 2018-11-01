package edu.austral.starship.base.model;

import edu.austral.starship.base.collision.CollisionEngine;
import edu.austral.starship.base.vector.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Florencia Vimberg
 */
public class Model {
    private List<GameObject> objects;
    private Map map;
    private CollisionEngine collisionEngine;
    private List<Player> players;
//    private Visitor visitor;


    public Model() {
        this.objects = new ArrayList<>();
        this.map =  new Map();
        this.collisionEngine = new CollisionEngine();
        players = new ArrayList<>();
    }

    public void addObject(GameObject object) {
        objects.add(object);
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public List<GameObject> getObjects() {
        return objects;
    }

    public void checkOutOfBounds(){
        for (GameObject object: objects
             ) {
            object.outOfBounds();
        }
    }

    public void createAsteroid(){
        Random random = new Random();
        Asteroid asteroid =  new Asteroid(new Vector2(random.nextInt(600), random.nextInt(600)), new Vector2(random.nextInt(600), random.nextInt(600)));
        addObject(asteroid);
    }

    public void shootBullet(Player player, int size){
        Bullet bullet = new Bullet(size, player.getSpaceship().getDirection(),
                new Vector2(player.getSpaceship().getPosition().getX() + 5, player.getSpaceship().getPosition().getY() - 40));
        addObject(bullet);
    }

    public void checkCollisions(){
        collisionEngine.checkCollisions(objects);
    }

    public int nextMove(int counter, float timeSinceLastDraw){
        //mover, ver cuales se fueron, eliminar los que tienen health 0
        checkOutOfBounds();
        checkCollisions();
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getHealth() == 0){
                objects.remove(objects.get(i));
            }
        }
        counter++;
        if((int)timeSinceLastDraw % 10 == 0 && counter > 500){
            createAsteroid();
            return 0 ;
        }
        return counter;
    }
}
