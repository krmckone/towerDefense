/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.control.AbstractControl;
import java.util.ArrayList;
import com.jme3.scene.shape.Line;

/**
 *
 * @author Kaleb
 */
public class TowerControl extends AbstractControl {
    Node beam = new Node();
    GamePlayAppState state;
    
    public TowerControl(GamePlayAppState state){
        this.state = state;
    }

    @Override
    protected void controlUpdate(float tpf) {
        ArrayList reachable = new ArrayList();
        
            if (state.getCreepList().size() > 0) {
                for (int i = 0; i < state.getCreepList().size(); i++){
                    Geometry target = (Geometry) this.state.getCreepList().get(i);
                    if (target.getControl(CreepsControl.class) != null){
                        if (spatial.getLocalTranslation().distance(target.getLocalTranslation()) < 5) {
                                reachable.add(target);
                        }
                    }
                        
                }
                
            }
        if (reachable.size() > 0){
            for (int x = 0; x < reachable.size(); x++) {
                Charge currentCharge = (Charge) getCharges().get(0);
                Geometry currentTarget = (Geometry) reachable.get(x);
                currentTarget.getControl(CreepsControl.class).damageCreep(currentCharge.getDamage());
                currentCharge.shootBullet();
                Line shootLine = new Line(this.spatial.getLocalTranslation(), currentTarget.getLocalTranslation());
                Geometry beam = new Geometry("Beam", shootLine);
                beam.setMaterial(new Material(state.assetManager, "Common/MatDefs/Misc/Unshaded.j3md"));
                state.addBeam(beam);
            }
        }
    }
    
    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        
    }
    
    public int getDataIndex(){
        return this.spatial.getUserData("Index");
    }
    public int getChargesNum(){
        return getCharges().size();
    }
    public ArrayList getCharges(){
        return this.spatial.getUserData("chargesList");
    }
    public void returnCharge(Charge returnCharge){
        getCharges().add(returnCharge);
    }
    
}
