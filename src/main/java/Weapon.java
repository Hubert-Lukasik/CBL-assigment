package src.main.java;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Timer;


public class Weapon {
    Entity userEntity;
    int damage;
    Timer cooldownTimer;



    public void setDamage(int d) {
        damage = d;
    }

    public void swingWeapon(boolean up, boolean down, boolean left, boolean right) {
        Image swingImage;
        Rectangle swingRectangle = new Rectangle();
        
        if (userEntity.checkDamage(swingRectangle)) {

        }

    }

    public Weapon(Entity e) {
        userEntity = e;
    }
}
