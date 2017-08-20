package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {
        AppSettings settings = new AppSettings(true);
        settings.setTitle("Tower Defense Demo");
        settings.setSettingsDialogImage("Interface/splashScreen.jpg");
        Main app = new Main();
        app.setSettings(settings);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        Node playerNode = new Node();
        Node towerNode = new Node();
        Node creepNode = new Node();
        rootNode.attachChild(playerNode);
        rootNode.attachChild(towerNode);
        rootNode.attachChild(creepNode);
        
        //make the player base
        Vector3f baseLocation = new Vector3f(0,-3.8f,33);
        Geometry base = createPlayerBase(baseLocation);
        playerNode.attachChild(base);
        
        //make the floor
        Vector3f floorPos = new Vector3f(0f, -4f, 0f);
        Box floorBox = new Box(10, 0.1f, 33);
        Geometry floorGeom = new Geometry("Box", floorBox);
        floorGeom.setLocalTranslation(floorPos);
        Material floorMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        floorMat.setColor("Color", ColorRGBA.Orange);
        floorGeom.setMaterial(floorMat);
        rootNode.attachChild(floorGeom);
        
        //make creeps
        Vector3f creepLocation01 = new Vector3f(3, -4f, -10);
        Geometry creepGeom01 = createCreep(creepLocation01);
        creepNode.attachChild(creepGeom01);
        
        Vector3f creepLocation02 = new Vector3f(-3, -4f, -8);
        Geometry creepGeom02 = createCreep(creepLocation02);
        creepNode.attachChild(creepGeom02);
        
        Vector3f creepLocation03 = new Vector3f(-1, -4f, -6);
        Geometry creepGeom03 = createCreep(creepLocation03);
        creepNode.attachChild(creepGeom03);
        
        //make towers
        Vector3f towerLocation01 = new Vector3f(-4, -3.8f, 25);
        Geometry towerGeom01 = createTower(towerLocation01);
        towerNode.attachChild(towerGeom01);
        
        Vector3f towerLocation02 = new Vector3f(4, -3.8f, 25);
        Geometry towerGeom02 = createTower(towerLocation02);
        towerNode.attachChild(towerGeom02);
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
        Box tower = new Box(1,2,1);
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

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
