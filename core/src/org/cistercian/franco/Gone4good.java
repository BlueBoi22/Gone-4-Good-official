package org.cistercian.franco;

import javax.lang.model.util.ElementScanner6;
import javax.xml.transform.TransformerFactoryConfigurationError;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import org.w3c.dom.Text;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Table.Debug;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import java.awt.*;
import java.io.Console;
import java.util.ArrayList;


public class Gone4good extends ApplicationAdapter {
	SpriteBatch batch;
	OrthographicCamera camera;
	Texture items;
	Texture background;
	Texture bulletpng;
	Texture Micheal;
	TextureRegion billHud;
	TextureRegion billHead;
	TextureRegion billStanding;
	TextureRegion zombie;
	TextureRegion bullet;
	TextureRegion baseTile1;
	TextureRegion baseTile2;
	TextureRegion baseTile3;
	TextureRegion baseTile4;
	TextureRegion leftWallTile;
	TextureRegion botWallTile;
	TextureRegion rightWallTile;
	TextureRegion topWallTile;
	TextureRegion botleftcorner;
	TextureRegion botrightcorner;
	TextureRegion topleftcorner;
	TextureRegion toprightcorner;
	Animation MichealM;
	Animation billWalking;
	Animation billShootingStart;
	Animation billShootWalk;
	Animation billShootContinue;
	Animation zombieWalk;
	float animationTime = 0;
	int x = 0;
	int y = 0;
	int q = -10;
	boolean walking;
	ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
	int headdir = 0;
	ArrayList<Zombie> zombieList = new ArrayList<Zombie>();
	public int ammo = 150;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
    	camera.setToOrtho(false, 1920, 1080);

		batch = new SpriteBatch();
		items = new Texture("Gone 4 Good.png");
		background = new Texture("Backgrounds.png");
		bulletpng = new Texture("Bullet.png");
		Micheal = new Texture("Michealsheet.png");
		billHud = new TextureRegion(items, 0, 0, 184,184);
		billHead = new TextureRegion(new Texture("head.png"));
		billStanding = new TextureRegion(items, 550, 0, 184, 184);
		bullet = new TextureRegion(items, 736, 821, 184, 10);
		zombie = new TextureRegion(items, 552, 368, 184, 184);
		zombieWalk = new Animation(0.2f, new TextureRegion(items, 552, 368, 184, 184), new TextureRegion(items, 368, 230, 184, 184));
		billWalking = new Animation(0.2f, new TextureRegion(items, 550, 0, 184, 184), new TextureRegion(items, 736, 0, 184, 184));
		zombie = new TextureRegion(items, 552, 368, 184, 184);
		zombieWalk = new Animation(0.2f, new TextureRegion(items, 552,368,184,184), new TextureRegion(items, 736, 368, 184, 184));
		billShootingStart = new Animation(.02f, new TextureRegion(items, 0, 184, 184, 184), new TextureRegion(items, 184, 184, 184, 184), new TextureRegion(items, 368, 184, 184, 184), new TextureRegion(items, 552, 184, 184, 184));
		billShootContinue = new Animation(.02f, new TextureRegion(items, 368, 184, 184, 184), new TextureRegion(items, 552, 184, 184, 184));
		billShootWalk = new Animation(0.2f, new TextureRegion(items, 736, 184, 184, 184), new TextureRegion(items, 0, 368, 184, 184), new TextureRegion(items, 184, 368, 184, 184), new TextureRegion(items, 184, 552, 184, 184));
		bullet = new TextureRegion(bulletpng, 0, 1, 64, 5);
		MichealM = new Animation(0.2f, new TextureRegion(Micheal, 0, 0, 122, 122), new TextureRegion(Micheal, 168, 44, 32, 15));
		
		baseTile1 = new TextureRegion(background, 0, 0, 460, 460);
		baseTile2 = new TextureRegion(background, 460, 0, 460, 460);
		baseTile3 = new TextureRegion(background, 920, 0, 460, 460);
		baseTile4 = new TextureRegion(background, 1380, 0, 460, 460);

		leftWallTile = new TextureRegion(background, 1840, 0, 460, 460);
		botWallTile = new TextureRegion(background, 2300, 0, 460, 460);
		rightWallTile = new TextureRegion(background, 2760, 0, 460, 460);
		topWallTile = new TextureRegion(background, 3223, 0, 460, 460);

		topleftcorner = new TextureRegion(background, 3680, 0, 460, 460);
		toprightcorner = new TextureRegion(background, 4141, 0, 460, 460);
		botleftcorner = new TextureRegion(background, 4600, 0, 460, 460);
		botrightcorner = new TextureRegion(background, 5060, 0, 460, 460);
	

		for (int i = 0; i < 10; i++) {
			zombieList.add(new Zombie((float) Math.random() * 5000, (float) Math.random() * 5000, (float) (Math.random() * 2) -1,(float)  (Math.random() * 2) -1));
		}
	}

	
	@Override
	public void render () {
		
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
		}
		animationTime += Gdx.graphics.getDeltaTime();
		Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(touchPos);
		ScreenUtils.clear(1, 0, 0, 1);
		Gdx.gl.glClearColor(.5f, .5f, .5f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		Gdx.graphics.getWidth();
		Gdx.graphics.getHeight();
		batch.setProjectionMatrix(camera.combined);
		camera.position.set(new Vector2(x, y), 0);
		
		
		batch.draw(baseTile1, 460, 1380);
		batch.draw(baseTile2, 460, 1840);
		batch.draw(botleftcorner, 0, 920);
		batch.draw(leftWallTile, 0, 1380);
		batch.draw(leftWallTile, 0, 1840);
		batch.draw(topleftcorner, 0, 2300);
		batch.draw(topWallTile, 460, 2300);
		batch.draw(topWallTile, 920, 2300);
		batch.draw(toprightcorner, 1380, 2300);
		batch.draw(toprightcorner, 1840, 1840);
		batch.draw(rightWallTile, 1840, 1380);
		batch.draw(toprightcorner, 2300, 920);
		batch.draw(rightWallTile, 2300, 460);
		batch.draw(botrightcorner, 2300, 0);


		


	
		if (Gdx.input.isKeyPressed(Input.Keys.W)){
			y -= q;
			walking = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)){
			y += q;
			walking = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)){
			x += q;
			walking = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)){
			x -= q;
			walking = true;
		}
		else if (! Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A) && ! Gdx.input.isKeyPressed(Input.Keys.S) && ! Gdx.input.isKeyPressed(Input.Keys.W)) {
			walking = false;
		}
		if (walking){
		batch.draw(billWalking.getKeyFrame(animationTime, Animation.ANIMATION_LOOPING), x, y);
		}
		else if (!walking){
		batch.draw(billStanding, x, y);
		}
		batch.draw(billHud, 0, 0);
		float angle = MathUtils.atan2( (touchPos.y - y), (touchPos.x  - x));
		float degrees = (float) (180.0 * angle / Math.PI);
		batch.draw(billHead, x + 900 - 875, y + 605 - 450, 20, 0, 32, 40, 1, 1, degrees);
		
			
		

		System.out.println(degrees);
		float angle2 = MathUtils.atan2( (touchPos.y - 450), (touchPos.x  - 875));
		float degrees2 = (float) (180.0 * angle2 / Math.PI);
		if(Gdx.input.justTouched() && ammo > 1) {
			bulletList.add(new Bullet(x, y, touchPos.x - x, touchPos.y - y));
			ammo -= 1;
		}

		for(Bullet i : bulletList){
			i.update();
			if (i.bulletpenetration > 0) {
				batch.draw(bullet, i.x, i.y, 0, 0, i.width, 3, 10, 1, (float) Math.toDegrees(MathUtils.atan2(i.dif.y, i.dif.x)));
			}
		}
		
		for(Zombie t : zombieList){
			t.update(bulletList);
			if (!t.dead) {
				batch.draw(zombieWalk.getKeyFrame(animationTime, Animation.ANIMATION_LOOPING), t.x, t.y);
			}
		}
	
		camera.update();
		batch.end();
	}
	@Override
	public void dispose () {
		batch.dispose();
		items.dispose();
		background.dispose();
	}
}