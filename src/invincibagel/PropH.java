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
public class PropH extends Actor{

    public PropH(String SVGdata, double xLocation, double yLocation, Image... spriteCels) {
        super(SVGdata, xLocation, yLocation, spriteCels);
        this.setIsFlipH(true);
        spriteFrame.setScaleX(-1);
        spriteFrame.setTranslateX(xLocation);
        spriteFrame.setTranslateY(yLocation);
    }

    @Override
    public void update() {
    }
    
}
