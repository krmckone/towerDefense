/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

/**
 *
 * @author Kaleb
 */
public class Charge {
    int damage;
    int bulletsLeft;
    
    public Charge(int damage, int bulletsLeft){
        this.damage = damage;
        this.bulletsLeft = bulletsLeft;
    }
    
    public int getDamage() {
        return damage;
    }
    public int getBulletsLeft() {
        return bulletsLeft;
    }
    public void shootBullet() {
        this.bulletsLeft--;
    }
}
  
