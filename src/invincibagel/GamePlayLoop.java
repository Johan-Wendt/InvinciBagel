/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invincibagel;

import javafx.animation.AnimationTimer;

/**
 *
 * @author johanwendt
 */
public class GamePlayLoop extends AnimationTimer{
    protected InvinciBagel invinciBagel;
    
    public GamePlayLoop(InvinciBagel iBagel) {
        invinciBagel = iBagel;
    }

    @Override
    public void handle(long now) {
        invinciBagel.getiBagel().update();
    }
    @Override
    public void start() {
        super.start();
    }
    @Override
    public void stop() {
        super.stop();
    }
    
}
