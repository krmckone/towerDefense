/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import java.util.ArrayList;
import mygame.Charges;

/**
 *
 * @author Kaleb
 */
public class GamePlayAppState extends AbstractAppState {
    private Camera cam;
    private SimpleApplication app;
    private Node rootNode;
    private Node beamNode;
    private AssetManager assetManager;
    Node playerNode = new Node();
    Node towerNode = new Node();
    Node creepNode = new Node();
    private int level;
    private int score;
    private int health;
    private int budget;
    boolean lastGameWon;
    public ArrayList creepList = new ArrayList();
    
    @Override
    public void update(float tpf) {}
    
    @Override public void cleanup() {
    rootNode.detachChild(playerNode);
    rootNode.detachChild(towerNode);
    rootNode.detachChild(creepNode);
    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app){
        super.initialize(stateManager, app);
        this.app =  (SimpleApplication) app;
        this.assetManager = this.app.getAssetManager();
        this.cam = this.app.getCamera();
        this.rootNode = this.app.getRootNode();
        this.health = 10;
        this.creepList = new ArrayList();
       
        
        //attach the nodes
        rootNode.attachChild(playerNode);
        rootNode.attachChild(towerNode);
        rootNode.attachChild(creepNode);
        
        //make the player base
        Vector3f baseLocation = new Vector3f(0,0,0);
        Geometry base = createPlayerBase(baseLocation);
        playerNode.attachChild(base);
        
        //make the floor
        Vector3f floorPos = new Vector3f(0, 0f, 16.5f);
        Box floorBox = new Box(5, 0, 16.5f);
        Geometry floorGeom = new Geometry("Box", floorBox);
        floorGeom.setLocalTranslation(floorPos);
        Material floorMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        floorMat.setColor("Color", ColorRGBA.Orange);
        floorGeom.setMaterial(floorMat);
        rootNode.attachChild(floorGeom);
        
        //make creeps
        Vector3f creepLocation0 = new Vector3f(0, 0, 31);
        Geometry creepGeom0 = createCreep(creepLocation0);
        creepGeom0.setUserData("index", 0);
        creepGeom0.setUserData("health", 100);
        creepGeom0.addControl(new CreepsControl(this));
        creepList.add(creepGeom0);
        creepNode.attachChild(creepGeom0);
        
        Vector3f creepLocation1 = new Vector3f(0, 0, 29);
        Geometry creepGeom1 = createCreep(creepLocation1);
        creepGeom1.setUserData("index", 1);
        creepGeom1.setUserData("health", 100);
        creepGeom1.addControl(new CreepsControl(this));
        creepList.add(creepGeom0);
        creepNode.attachChild(creepGeom1);
        
        Vector3f creepLocation2 = new Vector3f(0, 0, 27);
        Geometry creepGeom2 = createCreep(creepLocation2);
        creepGeom2.setUserData("index", 2);
        creepGeom2.setUserData("health", 100);
        creepGeom2.addControl(new CreepsControl(this));
        creepList.add(creepGeom0);
        creepNode.attachChild(creepGeom2);
        
        //make towers
        Vector3f towerLocation0 = new Vector3f(4, 1, 20);
        Geometry towerGeom0 = createTower(towerLocation0);
        towerGeom0.setUserData("index", 0);
        towerGeom0.setUserData("chargesList", makeCharges(3));
        towerGeom0.setUserData("chargesNum", 3);
        towerGeom0.addControl(new TowerControl(this));
        towerNode.attachChild(towerGeom0);
        
        Vector3f towerLocation1 = new Vector3f(-4, 1, 20);
        Geometry towerGeom1 = createTower(towerLocation1);
        towerGeom1.setUserData("index", 1);
        towerGeom1.setUserData("chargesList", makeCharges(3));
        towerGeom1.setUserData("chargesNum", 3);
        towerGeom0.addControl(new TowerControl(this));
        towerNode.attachChild(towerGeom1);
        
    }
    
    private Geometry createPlayerBase(Vector3f position){
        Box base = new Box(1.5f,0.5f,0.5f);
        Geometry baseGeom = new Geometry("Base", base);
        baseGeom.setLocalTranslation(position);
        Material baseMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        baseMat.setColor("Color", ColorRGBA.Yellow);
        baseGeom.setMaterial(baseMat);
        return baseGeom;
    }
    
    private Geometry createTower(Vector3f position){
        Box tower = new Box(1,1,1);
        Geometry towerGeom = new Geometry("Tower", tower);
        towerGeom.setLocalTranslation(position);
        Material towerMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        towerMat.setColor("Color", ColorRGBA.Green);
        towerGeom.setMaterial(towerMat);
        return towerGeom;
    }
    
    private Geometry createCreep(Vector3f position){
        Box creep = new Box(0.5f,0.5f,0.5f);
        Geometry creepGeom = new Geometry("Creep", creep);
        creepGeom.setLocalTranslation(position);
        Material creepMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        creepMat.setColor("Color", ColorRGBA.Black);
        creepGeom.setMaterial(creepMat);
        return creepGeom;  
    }
    
    
    public int getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }

    public int getHealth() {
        return health;
    }
    
    public void dealDamage() {
        this.health--;
    }
    
    public void addBudget() {
        this.budget = this.budget + 5;
    }
    
    public boolean isLastGameWon() {
        return lastGameWon;
    }
    
    public ArrayList  makeCharges(int chargeNumber){
        ArrayList chargeList = new ArrayList();
        for (int i = 0; i < chargeNumber; i++){
            chargeList.add(new Charges(5,2));
        }
        return chargeList;
    }
    public ArrayList getCreepList(){
        return creepList;
    }
}
