package com.txuritan.patch.main.lib;

import java.util.Random;

import com.txuritan.patch.main.blocks.Blocks;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class EventManager implements IWorldGenerator
{
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch (world.provider.dimensionId)
		{
			case -1:generateNether(world, random, chunkX * 16, chunkZ * 16);
			
			case 0:generateSurface(world, random, chunkX * 16, chunkZ * 16);
			
			case 1:generateEnd(world, random, chunkX * 16, chunkZ * 16);
		}
	}

	private void generateEnd(World world, Random random, int x, int z)
	{

	}

	private void generateSurface(World world, Random random, int x, int z)
	{
		this.addOreSpawn(Blocks.oreChromedigizoid, world, random, x, z, 16, 16, 2 + random.nextInt(2), 10, 1, 128);
		this.addOreSpawn(Blocks.oreCopper, world, random, x, z, 16, 16, 6 + random.nextInt(4), 25, 1, 128);
		this.addOreSpawn(Blocks.oreLead, world, random, x, z, 16, 16, 6 + random.nextInt(4), 25, 1, 128);
		this.addOreSpawn(Blocks.oreMagicite, world, random, x, z, 16, 16, 4 + random.nextInt(2), 10, 1, 40);
		this.addOreSpawn(Blocks.oreMecha, world, random, x, z, 16, 16, 4 + random.nextInt(2), 25, 1, 74);
		this.addOreSpawn(Blocks.oreMeowium, world, random, x, z, 16, 16, 5 + random.nextInt(2), 10, 1, 50);
	}

	private void generateNether(World world, Random random, int x, int z)
	{

	}

	public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY)
	{
		int maxPossY = minY + (maxY - 1);
		assert maxY > minY : "The maximum Y must be greater than the Minimum Y";
		assert maxX > 0 && maxX <= 16 : "addOreSpawn: The Maximum X must be greater than 0 and less than 16";
		assert minY > 0 : "addOreSpawn: The Minimum Y must be greater than 0";
		assert maxY < 256 && maxY > 0 : "addOreSpawn: The Maximum Y must be less than 256 but greater than 0";
		assert maxZ > 0 && maxZ <= 16 : "addOreSpawn: The Maximum Z must be greater than 0 and less than 16";

		int diffBtwnMinMaxY = maxY - minY;
		for (int x = 0; x < chancesToSpawn; x++)
		{
			int posX = blockXPos + random.nextInt(maxX);
			int posY = minY + random.nextInt(diffBtwnMinMaxY);
			int posZ = blockZPos + random.nextInt(maxZ);
			(new WorldGenMinable(block.blockID, maxVeinSize)).generate(world, random, posX, posY, posZ);
		}
	}
}