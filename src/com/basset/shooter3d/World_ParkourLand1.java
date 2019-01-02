package com.basset.shooter3d;

import java.util.ArrayList;
import java.util.Random;

import com.osreboot.ridhvl2.HvlCoord3;
import com.osreboot.ridhvl2.HvlMath;

public class World_ParkourLand1 {

	public static ArrayList<Volume> createWorld(){
		ArrayList<Volume> world = new ArrayList<>();
		
		//baseplate
		for(float x = -1000; x < 1000; x += 100){
			for(float y = -1000; y < 1000; y += 100){
				world.add(new Volume(0, x, y - 500, 100, 100, 
						Math.random() < 0.95f ? Main.IDX_FLOOR_ROCK : Main.IDX_FLOOR_ROCK2));
			}
		}
		
		world.add(new Volume(-1000, 0, -500, 500, 500, Main.IDX_FLOOR_ROCK));
		
		world.addAll(generateJumpChain(100, -50, -250, -300, -400, -250, -800, 5, new HvlCoord3(10f, 20f, 40f), 24f, 8f, Main.IDX_FLOOR_TECH));
		
		return world;
	}
	
	private static ArrayList<Volume> generateJumpChain(long seed, float h, float x, float y, float h2, float x2, float y2, 
			int vCount, HvlCoord3 vVariation, float vSize, float vSizeVariation, int vTextureIndex){
		ArrayList<Volume> world = new ArrayList<>();
		
		Random r = new Random(seed);
		
		for(float i = 0; i < vCount; i++){
			float p = i/vCount;
			
			world.add(new Volume(
					HvlMath.map(p, 0, 1, h, h2) + ((r.nextFloat() - 0.5f) * 2f * vVariation.y), 
					HvlMath.map(p, 0, 1, x, x2) + ((r.nextFloat() - 0.5f) * 2f * vVariation.x), 
					HvlMath.map(p, 0, 1, y, y2) + ((r.nextFloat() - 0.5f) * 2f * vVariation.z), 
					vSize + ((r.nextFloat() - 0.5f) * 2f * vSizeVariation), vSize + ((r.nextFloat() - 0.5f) * 2f * vSizeVariation), vTextureIndex));
		}
		
		return world;
	}
	
}
