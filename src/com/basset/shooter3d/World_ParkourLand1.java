package com.basset.shooter3d;

import java.util.ArrayList;
import java.util.Random;

import com.osreboot.ridhvl2.HvlCoord3;
import com.osreboot.ridhvl2.HvlMath;

public class World_ParkourLand1 {

	public static ArrayList<Volume> createWorld(){
		ArrayList<Volume> world = new ArrayList<>();

		//baseplate
		for(float x = -1100; x < 1100; x += 100){
			for(float y = -1100; y < 1100; y += 100){
				world.add(new Volume(0, x, y - 500, 100, 100, 
						Math.random() < 0.95f ? Main.IDX_FLOOR_ROCK : Main.IDX_FLOOR_ROCK2));
			}
		}
		
		//scenery
		world.addAll(generateJumpChain(0, 
				new HvlCoord3(-1000, -500, -1500), 
				new HvlCoord3(1000, -500, -1500), 
				20, new HvlCoord3(0f, 100f, 0f), 100f, 0f, Main.IDX_FLOOR_TECH));
		
		world.addAll(generateJumpChain(0, 
				new HvlCoord3(-1000, -500, 500), 
				new HvlCoord3(1000, -500, 500), 
				20, new HvlCoord3(0f, 100f, 0f), 100f, 0f, Main.IDX_FLOOR_TECH));
		
		world.addAll(generateJumpChain(0, 
				new HvlCoord3(-1000, -500, -1500), 
				new HvlCoord3(-1000, -500, 500), 
				20, new HvlCoord3(0f, 100f, 0f), 100f, 0f, Main.IDX_FLOOR_TECH));
		
		world.addAll(generateJumpChain(0, 
				new HvlCoord3(1000, -500, -1500), 
				new HvlCoord3(1000, -500, 500), 
				20, new HvlCoord3(0f, 100f, 0f), 100f, 0f, Main.IDX_FLOOR_TECH));

		//tower part 1
		world.add(new Volume(-1000, 0, -500, 500, 500, Main.IDX_FLOOR_ROCK));

		world.addAll(generateJumpChain(100, 
				new HvlCoord3(-250, -50, -300), 
				new HvlCoord3(-250, -400, -800), 
				5, new HvlCoord3(10f, 20f, 40f), 24f, 8f, Main.IDX_FLOOR_TECH));
		
		world.addAll(generateJumpChain(102, 
				new HvlCoord3(-200, -400, -750), 
				new HvlCoord3(350, -600, -750), 
				4, new HvlCoord3(40f, 20f, 10f), 24f, 8f, Main.IDX_FLOOR_TECH));
		
		world.addAll(generateJumpChain(100, 
				new HvlCoord3(250, -900, -300), 
				new HvlCoord3(250, -500, -800), 
				4, new HvlCoord3(10f, 0f, 40f), 24f, 8f, Main.IDX_FLOOR_TECH));

		return world;
	}

	private static ArrayList<Volume> generateJumpChain(long seed, HvlCoord3 start, HvlCoord3 end, 
			int vCount, HvlCoord3 vVariation, float vSize, float vSizeVariation, int vTextureIndex){
		ArrayList<Volume> world = new ArrayList<>();

		Random r = new Random(seed);

		for(float i = 0; i < vCount; i++){
			float p = i/vCount;

			world.add(new Volume(
					HvlMath.map(p, 0, 1, start.y, end.y) + ((r.nextFloat() - 0.5f) * 2f * vVariation.y), 
					HvlMath.map(p, 0, 1, start.x, end.x) + ((r.nextFloat() - 0.5f) * 2f * vVariation.x), 
					HvlMath.map(p, 0, 1, start.z, end.z) + ((r.nextFloat() - 0.5f) * 2f * vVariation.z), 
					vSize + ((r.nextFloat() - 0.5f) * 2f * vSizeVariation), vSize + ((r.nextFloat() - 0.5f) * 2f * vSizeVariation), vTextureIndex));
		}

		return world;
	}

}
