package me.justindevb.VulcanReplay.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import me.justindevb.VulcanReplay.ListenerBase;
import me.justindevb.VulcanReplay.VulcanReplay;
import me.vagdedes.spartan.api.PlayerViolationCommandEvent;
import me.vagdedes.spartan.api.PlayerViolationEvent;

public class SpartanListener extends ListenerBase implements Listener {

	public SpartanListener(VulcanReplay vulcanReplay) {
		super(vulcanReplay);
		Bukkit.getPluginManager().registerEvents(this, VulcanReplay.getInstance());
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onFlagEvent(PlayerViolationEvent event) {
		Player p = event.getPlayer();

		if (alertList.contains(p.getName()))
			return;

		alertList.add(p.getName());

		startRecording(p, getReplayName(p, event.getHackType().toString()));
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPunish(PlayerViolationCommandEvent event) {
		final Player p = event.getPlayer();

		if (!punishList.contains(p.getName()))
			punishList.add(p.getName());
	}

}
