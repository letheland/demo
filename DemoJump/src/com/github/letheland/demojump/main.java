package com.github.letheland.demojump;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class main  extends JavaPlugin implements Listener{
	static List<cooldown> c;  //¿‰»¥ ˝æ›
	long cooldown_time = 3000; //¿‰»¥ ±º‰
	@Override
	public void onEnable() {
		c = new ArrayList<cooldown>();
		System.out.println("∂˛∂ŒÃ¯");
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.getPlayer().setAllowFlight(true);
		c.add(new cooldown(e.getPlayer(),System.currentTimeMillis()));
	}
	
	@EventHandler
	public void onJump(PlayerToggleFlightEvent e) {
		for(cooldown c_:c) {
			if(c_.p == e.getPlayer()) {
				if(System.currentTimeMillis()-cooldown_time >= c_.time) {
					e.setCancelled(true);
					Vector v = new Vector(0, 1 ,0);
					 v = v.add(e.getPlayer().getLocation().getDirection().multiply(2));
					e.getPlayer().setVelocity(v);
					e.getPlayer().setFlying(false);
					c_.time = System.currentTimeMillis();
					return;
					}else{
						e.setCancelled(true);
						e.getPlayer().sendMessage("°Ïc¿‰»¥÷–...  £”‡: "+(c_.time + cooldown_time - System.currentTimeMillis())+"ms");
						return;
					}
				}
			}
		e.setCancelled(true);
		}
	@EventHandler
	public void onLeft(PlayerQuitEvent e) {
		for(cooldown c_:c) {
			if(c_.p == e.getPlayer()) {
				c.remove(c_);
			}
		}
	}
	}

