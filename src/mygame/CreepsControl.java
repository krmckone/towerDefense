/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

/**
 *
 * @author Kaleb
 */
public class CreepsControl extends AbstractControl {
    GamePlayAppState state;
    
    public CreepsControl(GamePlayAppState state){
        this.state = state;
    }

    @Override
    protected void controlUpdate(float tpf) {
        if ( getCreepHealth() >  0) {
            spatial.move(new Vector3f(0,0,-0.002f));
        }
        else if ( getCreepHealth() <=  0) {
            state.addBudget();
            state.creepNode.detachChild(spatial); 
        }
        if (spatial.getWorldTranslation().z <= 0){
            state.dealDamage();
            state.creepNode.detachChild(spatial);    
        } 
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }
    
    public int getCreepIndex(){
        return this.spatial.getUserData("index");
    }
    public int getCreepHealth(){
       return this.spatial.getUserData("health");
    }
    public void damageCreep(int dealt){
        this.spatial.setUserData("Health", getCreepHealth() - dealt);
    }
    
}
