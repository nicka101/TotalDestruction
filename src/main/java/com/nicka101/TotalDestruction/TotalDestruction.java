package com.nicka101.TotalDestruction;

import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class TotalDestruction extends JavaPlugin implements Listener{
	
	PluginDescriptionFile desc;
	Logger log = Logger.getLogger("Minecraft");
	protected static TotalDestruction p;
	protected static Random rand = new Random();
	
	public void onEnable(){
		p = this;
		this.getServer().getPluginManager().registerEvents(this, this);
		desc = this.getDescription();
		log.info("[" + this.desc.getName() + "] v" + this.desc.getVersion() +" Initialized");
	}
	
	public void onDisable(){
		desc = this.getDescription();
		log.info("[" + this.desc.getName() + "] v" + this.desc.getVersion() +" Shutdown Complete");
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event){
		if(event.getItem()==null || event.getItem().getType()!=Material.WOOD_SWORD || (event.getAction()!=Action.RIGHT_CLICK_BLOCK && event.getAction()!=Action.RIGHT_CLICK_AIR))return;
		Block toUse = event.getPlayer().getTargetBlock(null, 100);
		if(toUse==null)return;
		this.getServer().getScheduler().scheduleSyncDelayedTask(this, new DestructDelegate(toUse, toUse.getType()), 10);
	}
}
