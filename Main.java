package br.com.seudominio.template;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	public static Main m;
	public PluginManager pm = Bukkit.getPluginManager();

	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Template] " + ChatColor.GREEN + "Plugin Ativado");
		pm.registerEvents(this, this);
	}

	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Template] " + ChatColor.RED + "Plugin Desativado");
	}

	public void onLoad() {
		m = this;
		createConfig();
	}

	public void createConfig() {
		if (!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		}
		File file = new File(getDataFolder(), "config.yml");
		if (!file.exists()) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Template] " + ChatColor.YELLOW + "Arquivo <config.yml> nao encontrado. Criando estrutura default.");
			saveDefaultConfig();
		} else {
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Template] " + ChatColor.YELLOW + "Arquivo <config.yml> encontrado! Carregando estrutura default.");
		}
	}

	public String getMsgLogin() {
		String s = getConfig().getString("messages.login");
		return s;
	}

	public String getMsgLogout() {
		String s = getConfig().getString("messages.logout");
		return s;
	}

	public String getMsgDeath() {
		String s = getConfig().getString("messages.death");
		return s;
	}

	public void setMsgLogin(String s) {
		getConfig().set("messages.login", s);
		saveConfig();
	}

	public void setMsgLogout(String s) {
		getConfig().set("messages.login", s);
		saveConfig();
	}

	public void setMsgDeath(String s) {
		getConfig().set("messages.login", s);
		saveConfig();
	}

	@EventHandler
	public void JoinMessage(PlayerJoinEvent e) {
		e.setJoinMessage(ChatColor.AQUA + "[Template] " + ChatColor.GREEN + getMsgLogin());
	}

	@EventHandler
	public void QuitMessage(PlayerQuitEvent e) {
		e.setQuitMessage(ChatColor.AQUA + "[Template] " + ChatColor.GREEN + getMsgLogout());
	}

	@EventHandler
	public void DeathMessage(PlayerDeathEvent e) {
		e.setDeathMessage(ChatColor.AQUA + "[Template] " + ChatColor.RED + getMsgDeath());
	}

}

// e.setDeathMessage(ChatColor.AQUA + "[Template] " + e.getEntity().getDisplayName() + " morreu de desgosto!");
