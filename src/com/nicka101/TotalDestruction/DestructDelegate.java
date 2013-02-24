package com.nicka101.TotalDestruction;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class DestructDelegate implements Runnable{
	
	private Block b;
	private Material toMatch;
	
	public DestructDelegate(Block b, Material mat){
		this.b = b;
		this.toMatch = mat;
	}
	
	public void run(){
		if(TotalDestruction.p==null)return;
		if(this.b.getType()!=this.toMatch)return;
		b.setType(Material.AIR);
		b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, 90);
		for(BlockFace face : BlockFace.values()){
			TotalDestruction.p.getServer().getScheduler().scheduleSyncDelayedTask(TotalDestruction.p, new DestructDelegate(b.getRelative(face), this.toMatch), (int)TotalDestruction.rand.nextInt(18)+2);
		}
	}
}
