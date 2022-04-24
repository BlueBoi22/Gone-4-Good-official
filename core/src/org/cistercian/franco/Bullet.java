package org.cistercian.franco;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends Rectangle{
    public Vector2 dif = new Vector2();
    float velocity = 60f;
    public int bulletpenetration = 3;


    public void update(){
        this.x += dif.x * velocity;
        this.y += dif.y * velocity;
   }


    Bullet(float x, float y,float endX, float endY){
        super(x, y, 10, 10);
        width = 10;
        height = 10;
        dif = new Vector2(endX, endY).nor();
    }   
}