package com.tommytony.war;

import org.bukkit.Location;
import org.bukkit.Material;

import com.tommytony.war.volumes.CenteredVolume;
import com.tommytony.war.volumes.Volume;

public class Monument {
	private Location location;
	private CenteredVolume volume;
	
	private Team ownerTeam = null;
	private final String name;
	private Warzone warzone;
	
	public Monument(String name, War war, Warzone warzone, Location location) {
		this.name = name;
		this.location = location;
		this.warzone = warzone;
		volume = new CenteredVolume("name", 
						warzone.getWorld().getBlockAt(location.getBlockX(), 
												location.getBlockY() + 2, 
												location.getBlockZ()), 
						5, war, warzone);
		volume.saveBlocks();
		this.addMonumentBlocks();
	}
	
	public void addMonumentBlocks() {
		this.ownerTeam = null;
		int x = location.getBlockX();
		int y = location.getBlockY();
		int z = location.getBlockZ();
		
		// center
		warzone.getWorld().getBlockAt(x, y, z).getState().setType(Material.Air);
		warzone.getWorld().getBlockAt(x, y-1, z).setType(Material.Soil);
		
		// inner ring
		warzone.getWorld().getBlockAt(x+1, y-1, z+1).setType(Material.Obsidian);
		warzone.getWorld().getBlockAt(x+1, y-1, z).setType(Material.Obsidian);
		warzone.getWorld().getBlockAt(x+1, y-1, z-1).setType(Material.Obsidian);
		
		warzone.getWorld().getBlockAt(x, y-1, z+1).setType(Material.Obsidian);
		warzone.getWorld().getBlockAt(x, y-1, z).setType(Material.Obsidian);
		warzone.getWorld().getBlockAt(x, y-1, z-1).setType(Material.Obsidian);
		
		warzone.getWorld().getBlockAt(x-1, y-1, z+1).setType(Material.Obsidian);
		warzone.getWorld().getBlockAt(x-1, y-1, z).setType(Material.Obsidian);
		warzone.getWorld().getBlockAt(x-1, y-1, z-1).setType(Material.Obsidian);
		
		// outer ring
		
		warzone.getWorld().getBlockAt(x+2, y-1, z+2).setType(Material.LightStone);
		warzone.getWorld().getBlockAt(x+2, y-1, z+1).setType(Material.Obsidian);
		warzone.getWorld().getBlockAt(x+2, y-1, z).setType(Material.Obsidian);
		warzone.getWorld().getBlockAt(x+2, y-1, z-1).setType(Material.Obsidian);
		warzone.getWorld().getBlockAt(x+2, y-1, z-2).setType(Material.LightStone);
		
		warzone.getWorld().getBlockAt(x-1, y-1, z+2).setType(Material.Obsidian);
		warzone.getWorld().getBlockAt(x-1, y-1, z-2).setType(Material.Obsidian);
		
		warzone.getWorld().getBlockAt(x, y-1, z+2).setType(Material.Obsidian);
		warzone.getWorld().getBlockAt(x, y-1, z-2).setType(Material.Obsidian);
		
		warzone.getWorld().getBlockAt(x+1, y-1, z+2).setType(Material.Obsidian);
		warzone.getWorld().getBlockAt(x+1, y-1, z-2).setType(Material.Obsidian);
		
		warzone.getWorld().getBlockAt(x-2, y-1, z+2).setType(Material.LightStone);
		warzone.getWorld().getBlockAt(x-2, y-1, z+1).setType(Material.Obsidian);
		warzone.getWorld().getBlockAt(x-2, y-1, z).setType(Material.Obsidian);
		warzone.getWorld().getBlockAt(x-2, y-1, z-1).setType(Material.Obsidian);
		warzone.getWorld().getBlockAt(x-2, y-1, z-2).setType(Material.LightStone);
		
		// center block level ring
		warzone.getWorld().getBlockAt(x+1, y, z+1).setType(Material.Step);
		warzone.getWorld().getBlockAt(x+1, y, z).setType(Material.Obsidian);
		warzone.getWorld().getBlockAt(x+1, y, z-1).setType(Material.Step);
		
		warzone.getWorld().getBlockAt(x, y, z+1).setType(Material.Obsidian);
		warzone.getWorld().getBlockAt(x, y, z-1).setType(Material.Obsidian);
		
		warzone.getWorld().getBlockAt(x-1, y, z+1).setType(Material.Step);
		warzone.getWorld().getBlockAt(x-1, y, z).setType(Material.Obsidian);
		warzone.getWorld().getBlockAt(x-1, y, z-1).setType(Material.Step);
	}

	public boolean isNear(Location playerLocation) {
		int x = location.getBlockX();
		int y = location.getBlockY();
		int z = location.getBlockZ();
		int playerX = (int)playerLocation.getBlockX();
		int playerY = (int)playerLocation.getBlockY();
		int playerZ = (int)playerLocation.getBlockZ();
		int diffX = Math.abs(playerX - x);
		int diffY = Math.abs(playerY - y);
		int diffZ = Math.abs(playerZ - z);
		if(diffX < 6 && diffY < 6 && diffZ < 6) {
			return true;
		}
		return false;
	}
	
	public boolean isOwner(Team team) {
		if(team == ownerTeam) {
			return true;
		}
		return false;
	}
	
	public boolean hasOwner() {
		return ownerTeam != null;
	}
	
	public void capture(Team team) {
		ownerTeam = team;
	}
	
	public void uncapture() {
		ownerTeam = null;

		
	}
	
	public void remove() {
		volume.resetBlocks();
	}

	public Location getLocation() {
		return location;
	}

	public void setOwnerTeam(Team team) {
		this.ownerTeam = team;
		
	}

	public String getName() {
		return name;
	}

	public void setLocation(Location location) {
		this.location = location;
		
		volume.resetBlocks();
		volume.changeCenter(location);
		this.addMonumentBlocks();
	}

	public Volume getVolume() {
		return volume;
	}

	
}