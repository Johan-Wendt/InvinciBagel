/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invincibagel;

import javafx.scene.image.Image;

/**
 *
 * @author johanwendt
 */
public class Projectile extends Actor{
    public Projectile(String SVGdata, double xLocation, double yLocation, Image... spriteCels) {
        super(SVGdata, xLocation, yLocation, spriteCels);
        spriteFrame.setTranslateX(xLocation);
        spriteFrame.setTranslateY(yLocation);
        isFixed = false;
        isBonus = true;
        hasValu = true;
    }
    @Override
    public void update() {
    // Empty Method   
    }
}
