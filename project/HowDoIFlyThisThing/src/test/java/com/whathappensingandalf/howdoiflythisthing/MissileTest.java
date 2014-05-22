package com.whathappensingandalf.howdoiflythisthing;

import static org.junit.Assert.*;

import java.util.HashSet;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import org.junit.Test;

import com.whathappensingandalf.howdoiflythisthing.IGameObject.Type;

public class MissileTest {

	@Test
	public void testMissilePoint2fVector2fVector2fVector2fIntInt() {
		Missile m=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		assertTrue(m.getPosition().x==10);
		assertTrue(m.getPosition().y==11);
		assertTrue(m.getVelocity().x==2);
		assertTrue(m.getVelocity().y==3);
		assertTrue(m.getAcceleration().x==4);
		assertTrue(m.getAcceleration().y==5);
		assertTrue(m.getDirection().x==1);
		assertTrue(m.getDirection().y==6);
	}

	@Test
	public void testMissileMissile() {
		Missile m1=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		Missile m=new Missile(m1);
		assertTrue(m.getPosition().x==10);
		assertTrue(m.getPosition().y==11);
		assertTrue(m.getVelocity().x==2);
		assertTrue(m.getVelocity().y==3);
		assertTrue(m.getAcceleration().x==4);
		assertTrue(m.getAcceleration().y==5);
		assertTrue(m.getDirection().x==1);
		assertTrue(m.getDirection().y==6);
	}

	@Test
	public void testMove() {
		Missile m=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		Timestep t=new Timestep();
		t.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.start();
		m.move(t);
		assertTrue(m.getPosition().x==10+t.getDelta()*m.getVelocity().x);
		assertTrue(m.getPosition().y==11+t.getDelta()*m.getVelocity().y);
		
	}

	@Test
	public void testGetVelocity() {
		Missile m=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		assertTrue(m.getVelocity().x==2);
		assertTrue(m.getVelocity().y==3);
	}

	@Test
	public void testGetAcceleration() {
		Missile m=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		assertTrue(m.getAcceleration().x==4);
		assertTrue(m.getAcceleration().y==5);
	}

	@Test
	public void testGetDirection() {
		Missile m=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		assertTrue(m.getDirection().x==1);
		assertTrue(m.getDirection().y==6);
	}

	@Test
	public void testGetPosition() {
		Missile m=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		assertTrue(m.getPosition().x==10);
		assertTrue(m.getPosition().y==11);
	}

	@Test
	public void testGetRotVelocity() {
		Missile m=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		assertTrue(m.getVelocity().x==2);
		assertTrue(m.getVelocity().y==3);
	}

	@Test
	public void testGetRotAcceleration() {
		Missile m=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		assertTrue(m.getRotAcceleration()==0.0f);
	}

	@Test
	public void testSetVelocity() {
		Missile m=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		m.setVelocity(new Vector2f(10,11));
		assertTrue(m.getVelocity().x==10);
		assertTrue(m.getVelocity().y==11);
	}

	@Test
	public void testSetAcceleration() {
		Missile m=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		m.setAcceleration(new Vector2f(15,20));
		assertTrue(m.getAcceleration().x==15);
		assertTrue(m.getAcceleration().y==20);
	}

	@Test
	public void testSetDirection() {
		Missile m=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		m.setDirection(new Vector2f(2,1));
		assertTrue(m.getDirection().x==2);
		assertTrue(m.getDirection().y==1);
	}

	@Test
	public void testSetPosition() {
		Missile m=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		m.setPosition(new Point2f(100,200));
		assertTrue(m.getPosition().x==100);
		assertTrue(m.getPosition().y==200);
	}

	@Test
	public void testSetRotVelocity() {
		Missile m=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		m.setRotVelocity(10);
		assertTrue(m.getRotVelocity()==10);
	}

	@Test
	public void testSetRotAcceleration() {
		Missile m=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		m.setRotAcceleration(20);
		assertTrue(m.getRotAcceleration()==20);
	}

	@Test
	public void testCollideDetection() {
		Missile m1=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		Missile m2=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		assertTrue(m1.collideDetection(m2));
	}

	@Test
	public void testGetHeight() {
		Missile m=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		assertTrue(m.getHeight()==10);
	}

	@Test
	public void testGetWidth() {
		Missile m=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		assertTrue(m.getWidth()==5);
	}

	@Test
	public void testGetType() {
		Missile m=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		assertTrue(m.getType().equals(Type.MISSILE.toString()));
	}

	@Test
	public void testGetDamage() {
		Missile m=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		assertTrue(m.getDamage()==2);
	}


	@Test
	public void testClone() {
		Missile m=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		Missile clone=m.clone();
		assertFalse(clone==m);
		assertTrue(m.getPosition().x==clone.getPosition().x);
		assertTrue(m.getPosition().y==clone.getPosition().y);
		assertTrue(m.getVelocity().x==clone.getVelocity().x);
		assertTrue(m.getVelocity().y==clone.getVelocity().y);
		assertTrue(m.getAcceleration().x==clone.getAcceleration().x);
		assertTrue(m.getAcceleration().y==clone.getAcceleration().y);
		assertTrue(m.getDirection().x==clone.getDirection().x);
		assertTrue(m.getDirection().y==clone.getDirection().y);
		assertTrue(m.getWidth()==clone.getWidth());
		assertTrue(m.getHeight()==clone.getHeight());
		assertTrue(m.getRotVelocity()==clone.getRotVelocity());
		assertTrue(m.getRotAcceleration()==clone.getRotAcceleration());
	}

	@Test
	public void testGetCollectionDrawables() {
		Missile m=new Missile(new Point2f(10,11), new Vector2f(2,3), new Vector2f(4,5), new Vector2f(1,6), 5, 10);
		HashSet<DrawableData> set=new HashSet();
		set.addAll(m.getCollectionDrawables());
		for(DrawableData d:set){
			assertTrue(d.getDirection().x==m.getDirection().x);
			assertTrue(d.getDirection().y==m.getDirection().y);
			assertTrue(d.getHeight()==m.getHeight());
			assertTrue(d.getWidth()==m.getWidth());
			assertTrue(d.getPosition().x==m.getPosition().x);
			assertTrue(d.getPosition().y==m.getPosition().y);
		}
	}

}
