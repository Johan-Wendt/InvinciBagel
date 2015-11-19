/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invincibagel;

import java.util.Random;
import javafx.scene.image.Image;

/**
 *
 * @author johanwendt
 */
public class Enemy extends Actor {
    protected static final Random randomNum = new Random();
    int attackCounter = 0;
    int attackFrequency = 250;
    boolean takeSides = false;
    int attackBoundary = 300;
    boolean onScreen = false;
    boolean callAttack = false;
    boolean shootBullet = false;
    int spriteMoveR, spriteMoveL, destination;
    int randomLocation, randomOffset, bulletRange, bulletOffset;
    InvinciBagel invincibagel;

    public Enemy(InvinciBagel iBagel, String SVGdata, double xLocation, double yLocation, Image... spriteCels) {
        super(SVGdata, xLocation, yLocation, spriteCels); spriteFrame.setTranslateX(xLocation); spriteFrame.setTranslateY(yLocation);
        invincibagel = iBagel;
        isAlive = true;
        isBonus = true;
        hasValu = true;
    }
    @Override
    public void update() {
        if(!callAttack) {
            if(attackCounter >= attackFrequency) {
                attackCounter = 0;
                spriteFrame.setTranslateY(randomNum.nextInt(attackBoundary)); 
                spriteMoveR = 700;
                spriteMoveL = -70;
                randomLocation = randomNum.nextInt(attackBoundary);
                spriteFrame.setTranslateY(randomLocation);
                randomOffset = randomLocation + 5;
                callAttack = true;
            } 
            else attackCounter+=1; 
        }
        else initiateAttack(); 
    }

    private void initiateAttack() {
        if(!takeSides) {
            spriteFrame.setScaleX(1); 
            this.setIsFlipH(false);
            if(!onScreen) {
                destination = 500; 
                if(spriteMoveR >= destination) {
                    spriteMoveR-=2; 
                    spriteFrame.setTranslateX(spriteMoveR);
                } 
                else {
                    // ShootProjectile();
                    bulletOffset = 480;
                    shootBullet = true;
                    onScreen = true;
                } 
                if(onScreen) {
                    destination = 700; 
                    if(spriteMoveR <= destination) {
                        spriteMoveR+=1;
                        spriteFrame.setTranslateX(spriteMoveR); 
                    }
                    else {
                        onScreen = false; 
                        takeSides = true; 
                        callAttack = false;
                    }
                }
            }
        }
        if(takeSides) { 
            spriteFrame.setScaleX(-1);
        } 
        this.setIsFlipH(true);
        if(!onScreen) {
            destination = 100;
            if(spriteMoveL <= destination) {
                spriteMoveL+=2;
                spriteFrame.setTranslateX(spriteMoveL); 
            } 
            else {
                        // ShootProjectile();
                onScreen = true; 
            }
            if(onScreen) {
                destination = -70; 
                if(spriteMoveL >= destination) {
                    spriteMoveL-=1;
                    spriteFrame.setTranslateX(spriteMoveL); 
                }
                else {
                    onScreen = false; takeSides = false; callAttack = false;
                }
            }
        } 
    }
    
    private void shootProjectile() {
        if(!takeSides) {
            invincibagel.iBullet.spriteFrame.setTranslateY(randomOffset);
            invincibagel.iBullet.spriteFrame.setScaleX(-0.5); 
            invincibagel.iBullet.spriteFrame.setScaleY(0.5);
            bulletRange = -50;
            if(bulletOffset >= bulletRange) {
                bulletOffset-=4;
                invincibagel.iBullet.spriteFrame.setTranslateX(bulletOffset); 
            }
            else {
                shootBullet = false;
            }
        }
        if(takeSides) {
            invincibagel.iBullet.spriteFrame.setTranslateY(randomOffset);
            invincibagel.iBullet.spriteFrame.setScaleX(0.5);
            invincibagel.iBullet.spriteFrame.setScaleY(0.5);
            bulletRange = 624;
            if(bulletOffset <= bulletRange) { 
                bulletOffset+=4;
                invincibagel.iBullet.spriteFrame.setTranslateX(bulletOffset); 
            }
            else {
                shootBullet = false; 
            }
        } 
    }
}
