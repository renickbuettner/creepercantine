package com.speedyphil.Utils;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;

public class BlockUtils 
{
	public Block[] getOutlineBlocks(Block block, Material material)
	{
		List<Material> mats = new ArrayList();
		mats.add(material);
		return this.getOutlineBlocks(block, mats);
	}
	
	public Block[] getOutlineBlocks(Block block, List<Material> material)
	{
		Block start = block;
		Block b1 = null;
		Block b2 = null;
		Block b3 = null;
		
		for(int i = 0; i < 4; i++)
		{
			BlockFace face = BlockFace.values()[i];
			if(this.blockTypeEquals(block.getRelative(face), material))
			{
				while((block = this.getRelativeIfMat(block, face, material)) != null)
				{
					b1 = block;
				}

				BlockFace oface = BlockFace.values()[(i + 2) <= 3 ? (i + 2) : (i+2-4)];
				start = start.getRelative(face);
				while((start = this.getRelativeIfMat(start, oface, material)) != null)
				{
					b2 = start;
				}
				
				if(b2 != null)
				{
					BlockFace uface = BlockFace.values()[(i + 1) <= 3 ? (i + 1) : (i+1-4)];
					if(!this.blockTypeEquals(b2.getRelative(uface), material))
						uface = BlockFace.values()[(i + 3) <= 3 ? (i + 3) : (i+3-4)];
					
					while((b2 = this.getRelativeIfMat(b2, uface, material)) != null)
					{
						b3 = b2;
					}
				}
				i = 4;
			}
		}
		if(b1 != null && b3 != null)
		{
			Block[] bs = new Block[2];
			bs[0] = b1;
			bs[1] = b3;
			return bs;
		}
		return null;
	}

	public Block[] getDoorSide(Block door, Block[] blocks, List<Material> material)
	{
		Block[] w = new Block[2];
		BlockFace face = this.getFaceFromDoor(door.getData());
		w[0] = blocks[0];
		w[1] = blocks[1]; 
		for(int i = 0; i < w.length; i++)
		{
			while(this.blockTypeEquals(w[i].getRelative(face), material))
				w[i] = w[i].getRelative(face);
		}
		return w;
	}
	
	public Block getRelativeIfMat(Block block, BlockFace dir, List<Material> material){
		for(Material mat : material)
			if(block.getRelative(dir).getType() == mat)
				return block.getRelative(dir);
		return null;
	}
	
	public Block getSignAttachedBlock(Sign sign)
	{
		org.bukkit.material.Sign signData = (org.bukkit.material.Sign) sign.getData();
		return sign.getBlock().getRelative(signData.getAttachedFace());
	}

	public BlockFace getSignAttachedFace(Sign sign)
	{
		org.bukkit.material.Sign signData = (org.bukkit.material.Sign) sign.getData();
		return signData.getAttachedFace();
	}
	
	public Block getButtonAttachedBlock(Block b)
	{
		org.bukkit.material.Button signData = (org.bukkit.material.Button) b.getState().getData();
		return b.getRelative(signData.getAttachedFace());
	}

	public BlockFace getButtonAttachedFace(Block b)
	{
		org.bukkit.material.Button signData = (org.bukkit.material.Button) b.getState().getData();
		return signData.getAttachedFace();
	}

	public List<Block> getBlocksInArea(Location l1, Location l2, Material mat)
	{
		List<Material> mats = new ArrayList();
		mats.add(mat);
		return this.getBlocksInArea(l1, l2, mats);
	}
	
	public List<Block> getBlocksInArea(Location l1, Location l2, List<Material> mats)
	{
		List<Block> bl = new ArrayList();
        int x1 = l1.getBlockX();
        int y1 = l1.getBlockY();
        int z1 = l1.getBlockZ();

        int x2 = l2.getBlockX();
        int y2 = l2.getBlockY();
        int z2 = l2.getBlockZ();
        

        int xStart = 0; int xEnd = 0; int yStart = 0; int yEnd = 0; int zStart = 0; int zEnd = 0;

        if (x1 < x2) {
          xStart = x1;
          xEnd = x2;
        }
        if (x1 > x2) {
          xStart = x2;
          xEnd = x1;
        }
        if (x1 == x2) {
          xStart = x1;
          xEnd = x1;
        }
        
        if (y1 < y2) {
            yStart = y1;
            yEnd = y2;
          }
          if (y1 > y2) {
            yStart = y2;
            yEnd = y1;
          }
          if (y1 == y2) {
            yStart = y1;
            yEnd = y1;
          }

        if (z1 < z2) {
          zStart = z1;
          zEnd = z2;
        }
        if (z1 > z2) {
          zStart = z2;
          zEnd = z1;
        }
        if (z1 == z2) {
          zStart = z1;
          zEnd = z1;
        }

        for(int x = xStart; x <= xEnd; x++)
        {
        	for(int y = yStart; y <= yEnd; y++)
        	{
	        	for(int z = zStart; z <= zEnd; z++)
	        	{
	        		Block tempBlock = l1.getWorld().getBlockAt(x, y, z);
	        		if(this.blockTypeEquals(tempBlock, mats))
	        		{
	        			bl.add(tempBlock);
	        		}
	        	}
        	}
        }
        return bl;
	}
	
	public void setArea(Location l1, Location l2, Material mat)
	{
		setArea(l1, l2, mat, 0);
	}
	
	public void setArea(Location l1, Location l2, Material mat, int data)
	{
        int x1 = l1.getBlockX();
        int y1 = l1.getBlockY();
        int z1 = l1.getBlockZ();

        int x2 = l2.getBlockX();
        int y2 = l2.getBlockY();
        int z2 = l2.getBlockZ();
        

        int xStart = 0; int xEnd = 0; int yStart = 0; int yEnd = 0; int zStart = 0; int zEnd = 0;

        if (x1 < x2) {
          xStart = x1;
          xEnd = x2;
        }
        if (x1 > x2) {
          xStart = x2;
          xEnd = x1;
        }
        if (x1 == x2) {
          xStart = x1;
          xEnd = x1;
        }
        
        if (y1 < y2) {
            yStart = y1;
            yEnd = y2;
          }
          if (y1 > y2) {
            yStart = y2;
            yEnd = y1;
          }
          if (y1 == y2) {
            yStart = y1;
            yEnd = y1;
          }

        if (z1 < z2) {
          zStart = z1;
          zEnd = z2;
        }
        if (z1 > z2) {
          zStart = z2;
          zEnd = z1;
        }
        if (z1 == z2) {
          zStart = z1;
          zEnd = z1;
        }

        for(int x = xStart; x <= xEnd; x++)
        {
        	for(int y = yStart; y <= yEnd; y++)
        	{
	        	for(int z = zStart; z <= zEnd; z++)
	        	{
	        		Block tempBlock = l1.getWorld().getBlockAt(x, y, z);
	        		tempBlock.setType(mat);
	        		tempBlock.setData((byte)data);
	        	}
        	}
        }
	}
	
	public Block[] getInnerBlocks(Location l1, Location l2, int r)
	{
		Block[] bl = new Block[2];
        int x1 = l1.getBlockX();
        int y1 = l1.getBlockY();
        int z1 = l1.getBlockZ();

        int x2 = l2.getBlockX();
        int y2 = l2.getBlockY();
        int z2 = l2.getBlockZ();
        

        int xStart = 0; int xEnd = 0; int yStart = 0; int yEnd = 0; int zStart = 0; int zEnd = 0;

        if (x1 < x2) {
          xStart = x1;
          xEnd = x2;
        }
        if (x1 > x2) {
          xStart = x2;
          xEnd = x1;
        }
        if (x1 == x2) {
          xStart = x1;
          xEnd = x1;
        }
        
        if (y1 < y2) {
            yStart = y1;
            yEnd = y2;
          }
          if (y1 > y2) {
            yStart = y2;
            yEnd = y1;
          }
          if (y1 == y2) {
            yStart = y1;
            yEnd = y1;
          }

        if (z1 < z2) {
          zStart = z1;
          zEnd = z2;
        }
        if (z1 > z2) {
          zStart = z2;
          zEnd = z1;
        }
        if (z1 == z2) {
          zStart = z1;
          zEnd = z1;
        }
        
        bl[0] = l1.getWorld().getBlockAt(xStart+r, l2.getBlockY(), zStart+r);
        bl[1] = l1.getWorld().getBlockAt(xEnd-r, l2.getBlockY(), zEnd - r);
        return bl;
	}
	
	public boolean isOnly(Location l1, Location l2, Material mat)
	{
        int x1 = l1.getBlockX();
        int y1 = l1.getBlockY();
        int z1 = l1.getBlockZ();

        int x2 = l2.getBlockX();
        int y2 = l2.getBlockY();
        int z2 = l2.getBlockZ();
        

        int xStart = 0; int xEnd = 0; int yStart = 0; int yEnd = 0; int zStart = 0; int zEnd = 0;

        if (x1 < x2) {
          xStart = x1;
          xEnd = x2;
        }
        if (x1 > x2) {
          xStart = x2;
          xEnd = x1;
        }
        if (x1 == x2) {
          xStart = x1;
          xEnd = x1;
        }
        
        if (y1 < y2) {
            yStart = y1;
            yEnd = y2;
          }
          if (y1 > y2) {
            yStart = y2;
            yEnd = y1;
          }
          if (y1 == y2) {
            yStart = y1;
            yEnd = y1;
          }

        if (z1 < z2) {
          zStart = z1;
          zEnd = z2;
        }
        if (z1 > z2) {
          zStart = z2;
          zEnd = z1;
        }
        if (z1 == z2) {
          zStart = z1;
          zEnd = z1;
        }
        
        for(int x = xStart; x <= xEnd; x++)
        {
        	for(int y = yStart; y <= yEnd; y++)
        	{
	        	for(int z = zStart; z <= zEnd; z++)
	        	{
	        		Block tempBlock = l1.getWorld().getBlockAt(x, y, z);
	        		if(tempBlock.getType() != mat)
	        			return false;
	        	}
        	}
        }
        return true;
	}

	public int getPerimeter(Location l1, Location l2)
	{
        int x1 = l1.getBlockX();
        int z1 = l1.getBlockZ();

        int x2 = l2.getBlockX();
        int z2 = l2.getBlockZ();

        int xStart = 0; int xEnd = 0; int zStart = 0; int zEnd = 0;

        if (x1 < x2) {
          xStart = x1;
          xEnd = x2;
        }
        if (x1 > x2) {
          xStart = x2;
          xEnd = x1;
        }
        if (x1 == x2) {
          xStart = x1;
          xEnd = x1;
        }

        if (z1 < z2) {
          zStart = z1;
          zEnd = z2;
        }
        if (z1 > z2) {
          zStart = z2;
          zEnd = z1;
        }
        if (z1 == z2) {
          zStart = z1;
          zEnd = z1;
        }
        
        return (((zEnd+1 - zStart) * 2) + (xEnd+1 - xStart - 2) * 2);
	}
	
	public boolean blockTypeEquals(Block b, List<Material> mat){
		for(Material m : mat)
			if(b.getType() == m)
				return true;
		return false;
	}

	public BlockFace getFaceFromDoor(int i) {
		switch(i)
		{
			case 1: return BlockFace.NORTH;
			case 2: return BlockFace.EAST;
			case 3: return BlockFace.SOUTH;
			case 0: return BlockFace.WEST;
			default: return BlockFace.NORTH;
		}
	}

	public boolean isNextTo(Block b1, Block b2)
	{
		for(int i = 0; i < 4; i++){
			BlockFace face = BlockFace.values()[i];
			if(b2 == b1.getRelative(face))
				return true;
		}
		System.out.println("false");
		return false;
	}

	public List<Block> getSurroundingBlock(Block block[], int r, List<Material> mat)
	{
		List<Block> blocks = new ArrayList();
		
		Location l1 = block[0].getLocation();
		Location l2 = block[1].getLocation();
		
        int x1 = l1.getBlockX();
        int y1 = l1.getBlockY();
        int z1 = l1.getBlockZ();

        int x2 = l2.getBlockX();
        int y2 = l2.getBlockY();
        int z2 = l2.getBlockZ();
        

        int xStart = 0; int xEnd = 0; int yStart = 0; int yEnd = 0; int zStart = 0; int zEnd = 0;

        if (x1 < x2) {
          xStart = x1;
          xEnd = x2;
        }
        if (x1 > x2) {
          xStart = x2;
          xEnd = x1;
        }
        if (x1 == x2) {
          xStart = x1;
          xEnd = x1;
        }
        
        if (y1 < y2) {
            yStart = y1;
            yEnd = y2;
          }
          if (y1 > y2) {
            yStart = y2;
            yEnd = y1;
          }
          if (y1 == y2) {
            yStart = y1;
            yEnd = y1;
          }

        if (z1 < z2) {
          zStart = z1;
          zEnd = z2;
        }
        if (z1 > z2) {
          zStart = z2;
          zEnd = z1;
        }
        if (z1 == z2) {
          zStart = z1;
          zEnd = z1;
        }

        for(int x = xStart; x <= xEnd; x++)
        {
        	for(int y = yStart; y <= yEnd; y++)
        	{
	        	for(int z = zStart; z <= zEnd; z++)
	        	{
	        		if((xStart == x) || (x == xEnd) || (z == zEnd) || (z == zStart))
	        		{
	        			Block tempBlock = l1.getWorld().getBlockAt(x, y, z);
		        		for(int i = 0; i < 4; i++)
		        		{
		        			BlockFace face = BlockFace.values()[i];
		        			Block m = tempBlock;

		        			for(int j = 0; j < r; j++)
		        			{
		        				m = m.getRelative(face);
			        			if(m.getLocation().getBlockX() >= xStart && m.getLocation().getBlockX() <= xEnd && m.getLocation().getBlockY() >= yStart && m.getLocation().getBlockY() <= yEnd && m.getLocation().getBlockZ() >= zStart && m.getLocation().getBlockZ() <= zEnd){
			        				j = r;
			        				continue;
			        			}
		        				if(this.blockTypeEquals(m, mat))
		        					blocks.add(m);
		        			}
		        		}
	        		}
	        	}
        	}
        }
        return blocks;
	}
	
	public BlockFace getOpposite(BlockFace f)
	{
		int fi = 0;
		for(int i = 0; i < 4; i++)
			if(BlockFace.values()[i] == f)
				fi = i;
		return BlockFace.values()[(fi + 2) <= 3 ? (fi + 2) : (fi+2-4)];
	}

	public boolean isInArea(Location l1, Location l2, Location l3)
	{
        int x1 = l1.getBlockX();
        int y1 = l1.getBlockY();
        int z1 = l1.getBlockZ();

        int x2 = l2.getBlockX();
        int y2 = l2.getBlockY();
        int z2 = l2.getBlockZ();
        

        int xStart = 0; int xEnd = 0; int yStart = 0; int yEnd = 0; int zStart = 0; int zEnd = 0;

        if (x1 < x2) {
          xStart = x1;
          xEnd = x2;
        }
        if (x1 > x2) {
          xStart = x2;
          xEnd = x1;
        }
        if (x1 == x2) {
          xStart = x1;
          xEnd = x1;
        }
        
        if (y1 < y2) {
            yStart = y1;
            yEnd = y2;
          }
          if (y1 > y2) {
            yStart = y2;
            yEnd = y1;
          }
          if (y1 == y2) {
            yStart = y1;
            yEnd = y1;
          }

        if (z1 < z2) {
          zStart = z1;
          zEnd = z2;
        }
        if (z1 > z2) {
          zStart = z2;
          zEnd = z1;
        }
        if (z1 == z2) {
          zStart = z1;
          zEnd = z1;
        }

        if((xStart <= l3.getBlockX() && xEnd >= l3.getBlockX()) && (yStart <= l3.getBlockY() && yEnd >= l3.getBlockY()) && (zStart <= l3.getBlockZ() && zEnd >= l3.getBlockZ()))
        	return true;
		return false;
	}

	public float getSize(Location l1, Location l2)
	{
        int x1 = l1.getBlockX();
        int y1 = l1.getBlockY();
        int z1 = l1.getBlockZ();

        int x2 = l2.getBlockX();
        int y2 = l2.getBlockY();
        int z2 = l2.getBlockZ();
        

        int xStart = 0; int xEnd = 0; int yStart = 0; int yEnd = 0; int zStart = 0; int zEnd = 0;

        if (x1 < x2) {
          xStart = x1;
          xEnd = x2;
        }
        if (x1 > x2) {
          xStart = x2;
          xEnd = x1;
        }
        if (x1 == x2) {
          xStart = x1;
          xEnd = x1;
        }
        
        if (y1 < y2) {
            yStart = y1;
            yEnd = y2;
          }
          if (y1 > y2) {
            yStart = y2;
            yEnd = y1;
          }
          if (y1 == y2) {
            yStart = y1;
            yEnd = y1;
          }

        if (z1 < z2) {
          zStart = z1;
          zEnd = z2;
        }
        if (z1 > z2) {
          zStart = z2;
          zEnd = z1;
        }
        if (z1 == z2) {
          zStart = z1;
          zEnd = z1;
        }
        
        int diffX = xEnd - xStart + 1;
        int diffY = yEnd - yStart + 1;
        int diffZ = zEnd - zStart + 1;
        return diffX*diffY*diffZ;
	}
	
	public boolean isWallsOnlyMaterial(Location l1, Location l2, Material mat, int data)
	{		
        int x1 = l1.getBlockX();
        int z1 = l1.getBlockZ();

        int x2 = l2.getBlockX();
        int z2 = l2.getBlockZ();
        

        int xStart = 0; int xEnd = 0; int zStart = 0; int zEnd = 0;

        if (x1 < x2) {
          xStart = x1;
          xEnd = x2;
        }
        if (x1 > x2) {
          xStart = x2;
          xEnd = x1;
        }
        if (x1 == x2) {
          xStart = x1;
          xEnd = x1;
        }
        
        if (z1 < z2) {
          zStart = z1;
          zEnd = z2;
        }
        if (z1 > z2) {
          zStart = z2;
          zEnd = z1;
        }
        if (z1 == z2) {
          zStart = z1;
          zEnd = z1;
        }

        for(int x = xStart; x <= xEnd; x++)
        {
        	for(int z = zStart; z <= zEnd; z++)
        	{
        		if((xStart == x) || (x == xEnd) || (z == zEnd) || (z == zStart))
        		{
        			Block b = l1.getWorld().getBlockAt(x, l1.getBlockY(), z);
        			if(b.getType() != mat || b.getData() != (byte)data)
        				return false;
        		}
        	}
        }
        return true;
	}
	
	public Block[] getStartEndBlock(Location l1, Location l2)
	{
        int x1 = l1.getBlockX();
        int y1 = l1.getBlockY();
        int z1 = l1.getBlockZ();

        int x2 = l2.getBlockX();
        int y2 = l2.getBlockY();
        int z2 = l2.getBlockZ();
        

        int xStart = 0; int xEnd = 0; int yStart = 0; int yEnd = 0; int zStart = 0; int zEnd = 0;

        if (x1 < x2) {
          xStart = x1;
          xEnd = x2;
        }
        if (x1 > x2) {
          xStart = x2;
          xEnd = x1;
        }
        if (x1 == x2) {
          xStart = x1;
          xEnd = x1;
        }
        
        if (y1 < y2) {
            yStart = y1;
            yEnd = y2;
          }
          if (y1 > y2) {
            yStart = y2;
            yEnd = y1;
          }
          if (y1 == y2) {
            yStart = y1;
            yEnd = y1;
          }

        if (z1 < z2) {
          zStart = z1;
          zEnd = z2;
        }
        if (z1 > z2) {
          zStart = z2;
          zEnd = z1;
        }
        if (z1 == z2) {
          zStart = z1;
          zEnd = z1;
        }
        Block[] blocks = new Block[2];
        blocks[0] = new Location(l1.getWorld(), xStart, yStart, zStart).getBlock();
        blocks[1] = new Location(l2.getWorld(), xEnd, yEnd, zEnd).getBlock();
        return blocks;
	}
}
