package com.windows.Period2;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.Event.Result;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Period2 extends JavaPlugin implements Listener {
	
	String prefix = "§e[WINDOWS] §f";
	List<String> lore = new ArrayList<String>();
	private final String USER_AGENT = "Mozilla/5.0";
	private String Address = "http://ipip.kr";
	private URL Url;
	private BufferedReader br;
	private HttpURLConnection con;
	private String protocol = "GET";
	private String IP = null;
	private String Domain = "";
	String a1;
	int n1;
	
	public void onEnable() {
		try {
			Url = new URL(this.Address);
		} catch (MalformedURLException e) {
		}
		try {
			con = (HttpURLConnection)Url.openConnection();
		} catch (IOException e) {
		}
		try {
			con.setRequestMethod(protocol);
		} catch (ProtocolException e) {
		}
		con.setRequestProperty("USER-Agent", USER_AGENT);
		try {
			br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
		} catch (IOException e) {
		}
		String line;
		String ip = null;
		try {
			while((line = br.readLine())!= null){
			if (line.startsWith("<title>Your IP is ")){
				ip = line.replaceAll("Your IP is ", "").replaceAll("<title>", "").replaceAll("</title>", "");
			}
			}
		} catch (NullPointerException e1) {
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "                       [ Period System 2 ]");
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "인터넷 연결 상태가 올바르지 않습니다.");
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "서버를 종료합니다.");
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
				try {
				Thread.sleep(10000L);
			} catch (InterruptedException e) {
			}
				Bukkit.shutdown();
		} catch (IOException e) {
		}
		try {
			br.close();
		} catch (IOException e) {
		}
  	      try {
	  	        IP = InetAddress.getAllByName(Domain)[0].getHostAddress();
	  	        if (!ip.equalsIgnoreCase(IP)) {
	  	        	Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "                       [ Period System 2 ]");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "구매자 도메인의 아이피와 일치하지 않습니다.");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "서버를 종료합니다.");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
	  				Thread.sleep(10000L);
	  				Bukkit.shutdown();
	  	        }
	  	      }
	  	      catch (UnknownHostException e1) {
		  	    	Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "                       [ Period System 2 ]");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "구매 시 등록했던 도메인이 유효하지 않습니다.");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "서버를 종료합니다.");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
	  				try {
						Thread.sleep(10000L);
					} catch (InterruptedException e) {
					}
	  				Bukkit.shutdown();
	  	      } catch (InterruptedException e) {
			}
		  BufferedInputStream in = null;
			String strUrl = "http://cafe.naver.com/MemoList.nhn?search.clubid=27833593&search.menuid=5";
			StringBuffer sb = new StringBuffer();
			
			try {
				URL url = new URL(strUrl);
				URLConnection urlConnection = url.openConnection();
				in = new BufferedInputStream(urlConnection.getInputStream());
				
				byte[] bufRead = new byte[40960];
				int lenRead = 0;
				while ((lenRead = in.read(bufRead)) > 0)
					sb.append(new String(bufRead, 0, lenRead));

			} catch (IOException ioe) {}
			if (sb.toString().contains("[" + Domain + "]")) {
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "                       [ Period System 2 ]");
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "해당 플러그인은 현재 차단된 상태이므로 사용하실 수 없습니다.");
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "서버를 종료합니다.");
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
			try {
				Thread.sleep(10000L);
			} catch (InterruptedException e1) {
			}
			Bukkit.shutdown();
			return;
			}
		getServer().getPluginManager().registerEvents(this, this);
			Bukkit.getConsoleSender().sendMessage("§e[WINDOWS] §a기간제아이템2 플러그인 활성화");
			if (Bukkit.getPluginManager().getPlugin("Period System") == null) {
				new BukkitRunnable(){
					@Override
					public void run(){
						task();
					}
				}.runTaskTimer(this,0,10);
			}
			new BukkitRunnable(){
				@Override
				public void run(){
					task2();
				}
			}.runTaskTimer(this,0,10);
	}
	
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§e[WINDOWS] §c기간제아이템2 플러그인 비활성화");
	}
	
	public void DecompileProtect() {
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10));
		list.stream().filter((Integer num) -> num % 2 == 0);
	}
	
	public String now() {
		return new SimpleDateFormat("yyyyMMddHHmm", Locale.KOREA).format(new Date());
	}
	
	public void task() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			scanEquip1(p);
			scanEquip2(p);
			scanEquip3(p);
			scanEquip4(p);
			for (int i = 0; i < p.getInventory().getContents().length; i++) {
				if (p.getInventory().getContents()[i] != null && p.getInventory().getContents()[i].hasItemMeta() && p.getInventory().getContents()[i].getItemMeta().hasLore()) {
					for (int n = 0; n < p.getInventory().getContents()[i].getItemMeta().getLore().size(); n++) {
						String a = p.getInventory().getContents()[i].getItemMeta().getLore().get(n);
						if (a.split(" ")[0].equals("§e[-]§f")) {
							String sto = a.split(" ")[1] + a.split(" ")[2] + a.split(" ")[3] + a.split(" ")[5] + a.split(" ")[6];
							String sto2 = sto.replaceAll("년", "").replaceAll("월", "").replaceAll("일", "").replaceAll("시", "").replaceAll("분", "");
							double date = Double.parseDouble(sto2) - Double.parseDouble(now());
							if (date <= 0) {
								if (p.getInventory().getContents()[i].hasItemMeta() && p.getInventory().getContents()[i].getItemMeta().getDisplayName() != null) {
									p.sendMessage(prefix + p.getInventory().getContents()[i].getItemMeta().getDisplayName() + " §b의 사용 기간이 만료되어 소멸되었습니다.");
									p.getInventory().remove(p.getInventory().getContents()[i]);
									return;
								} else if (p.getInventory().getContents()[i].hasItemMeta() && p.getInventory().getContents()[i].getItemMeta().getDisplayName() == null) {
								p.sendMessage(prefix + p.getInventory().getContents()[i].getType() + " §b의 사용 기간이 만료되어 소멸되었습니다.");
								p.getInventory().remove(p.getInventory().getContents()[i]);
								return;
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void task2() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			Primium(p);
		}
	}
	
	public void Primium(Player p) {
		for (int i = 0; i < p.getInventory().getContents().length; i++) {
			if (p.getInventory().getContents()[i] != null && p.getInventory().getContents()[i].hasItemMeta() && p.getInventory().getContents()[i].getItemMeta().hasLore()) {
				lore.clear();
				for (int n = 0; n < p.getInventory().getContents()[i].getItemMeta().getLore().size(); n++) {
					String a = p.getInventory().getContents()[i].getItemMeta().getLore().get(n);
					lore.add(a);
				}
				for (int n = 0; n < p.getInventory().getContents()[i].getItemMeta().getLore().size(); n++) {
					String a = p.getInventory().getContents()[i].getItemMeta().getLore().get(n);
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.MONTH , cal.get(Calendar.MONTH) + 1);
					if (a.split(" ")[0].equals("§f기간제설정")) {
						if (a.split(" ").length != 6) {
							return;
						}
						if (a.split(" ")[1].matches("^[0-9]+년$")) {
							a1 = a.split(" ")[1].replaceAll("년", "");
							n1 = Integer.parseInt(a1);
							cal.set(Calendar.YEAR , cal.get(Calendar.YEAR) + n1);
						}
						if (a.split(" ")[2].matches("^[0-9]+월$")) {
							a1 = a.split(" ")[2].replaceAll("월", "");
							n1 = Integer.parseInt(a1);
							cal.set(Calendar.MONTH , cal.get(Calendar.MONTH) + n1);
						}
						if (a.split(" ")[3].matches("^[0-9]+일$")) {
							a1 = a.split(" ")[3].replaceAll("일", "");
							n1 = Integer.parseInt(a1);
							cal.set(Calendar.DATE , cal.get(Calendar.DATE) + n1);
						}
						if (a.split(" ")[4].matches("^[0-9]+시간$")) {
							a1 = a.split(" ")[4].replaceAll("시간", "");
							n1 = Integer.parseInt(a1);
							cal.set(Calendar.HOUR_OF_DAY , cal.get(Calendar.HOUR_OF_DAY) + n1);
						}
						if (a.split(" ")[5].matches("^[0-9]+분$")) {
							a1 = a.split(" ")[5].replaceAll("분", "");
							n1 = Integer.parseInt(a1);
							cal.set(Calendar.MINUTE , cal.get(Calendar.MINUTE) + n1);
						}
						String month = "" + cal.get(Calendar.MONTH);
						String date = "" + cal.get(Calendar.DATE);
						String hour = "" + cal.get(Calendar.HOUR_OF_DAY);
						String minute = "" + cal.get(Calendar.MINUTE);
						if (cal.get(Calendar.MONTH) < 10) {
							month = "0" + cal.get(Calendar.MONTH);
						}
						if (cal.get(Calendar.DATE) < 10) {
							date = "0" + cal.get(Calendar.DATE);
						}
						if (cal.get(Calendar.HOUR_OF_DAY) < 10) {
							hour = "0" + cal.get(Calendar.HOUR_OF_DAY);
						}
						if (cal.get(Calendar.MINUTE) < 10) {
							minute = "0" + cal.get(Calendar.MINUTE);
						}
				        lore.remove(n);
				        lore.add("§e[-]§f " + cal.get(Calendar.YEAR) + "년 " + month + "월 " + date + "일 - " + hour + "시 " + minute + "분 까지");
				        ItemStack item = p.getInventory().getContents()[i];
				        ItemMeta meta = item.getItemMeta();
				        meta.setLore(lore);
				        item.setItemMeta(meta);
				        p.sendMessage(prefix + "만료일이 " + cal.get(Calendar.YEAR) + "년 " + month + "월 " + date + "일 - " + hour + "시 " + minute + "분 으로 설정되었습니다.");
				        lore.clear();
				        return;
					}
				}
			}
		}
	}
	
	public void scanTop(InventoryOpenEvent event) {
		if (Bukkit.getPluginManager().getPlugin("Period System") != null) {
			return;
		}
		Player player = (Player)event.getPlayer();
		for (int i = 0; i < event.getView().getTopInventory().getSize(); i++) {
			if (event.getView().getTopInventory().getContents()[i] != null && event.getView().getTopInventory().getContents()[i].hasItemMeta() && event.getView().getTopInventory().getContents()[i].getItemMeta().hasLore()) {
				for (int n = 0; n < event.getView().getTopInventory().getContents()[i].getItemMeta().getLore().size(); n++) {
					String a = event.getView().getTopInventory().getContents()[i].getItemMeta().getLore().get(n);
					if (a.split(" ")[0].equals("§e[-]§f")) {
						String sto = a.split(" ")[1] + a.split(" ")[2] + a.split(" ")[3] + a.split(" ")[5] + a.split(" ")[6];
						String sto2 = sto.replaceAll("년", "").replaceAll("월", "").replaceAll("일", "").replaceAll("시", "").replaceAll("분", "");
						double date = Double.parseDouble(sto2) - Double.parseDouble(now());
						if (date <= 0) {
							if (event.getView().getTopInventory().getContents()[i].hasItemMeta() && event.getView().getTopInventory().getContents()[i].getItemMeta().getDisplayName() != null) {
							player.sendMessage(prefix + event.getView().getTopInventory().getContents()[i].getItemMeta().getDisplayName() + " §b의 사용 기간이 만료되어 소멸되었습니다.");
							event.getView().getTopInventory().remove(event.getView().getTopInventory().getContents()[i]);
							return;
							}
							player.sendMessage(prefix + event.getView().getTopInventory().getContents()[i].getType() + " §b의 사용 기간이 만료되어 소멸되었습니다.");
							event.getView().getTopInventory().remove(event.getView().getTopInventory().getContents()[i]);
							return;
						}
					}
				}
			}
		}
	}
	
	public void scanBottom(InventoryOpenEvent event) {
		if (Bukkit.getPluginManager().getPlugin("Period System") != null) {
			return;
		}
		Player player = (Player)event.getPlayer();
		for (int i = 0; i < event.getView().getBottomInventory().getSize(); i++) {
			if (event.getView().getBottomInventory().getContents()[i] != null && event.getView().getBottomInventory().getContents()[i].hasItemMeta() && event.getView().getBottomInventory().getContents()[i].getItemMeta().hasLore()) {
				for (int n = 0; n < event.getView().getBottomInventory().getContents()[i].getItemMeta().getLore().size(); n++) {
					String a = event.getView().getBottomInventory().getContents()[i].getItemMeta().getLore().get(n);
					if (a.split(" ")[0].equals("§e[-]§f")) {
						String sto = a.split(" ")[1] + a.split(" ")[2] + a.split(" ")[3] + a.split(" ")[5] + a.split(" ")[6];
						String sto2 = sto.replaceAll("년", "").replaceAll("월", "").replaceAll("일", "").replaceAll("시", "").replaceAll("분", "");
						double date = Double.parseDouble(sto2) - Double.parseDouble(now());
						if (date <= 0) {
							if (event.getView().getBottomInventory().getContents()[i].hasItemMeta() && event.getView().getBottomInventory().getContents()[i].getItemMeta().getDisplayName() != null) {
							player.sendMessage(prefix + event.getView().getBottomInventory().getContents()[i].getItemMeta().getDisplayName() + " §b의 사용 기간이 만료되어 소멸되었습니다.");
							event.getView().getBottomInventory().remove(event.getView().getBottomInventory().getContents()[i]);
							return;
							}
							player.sendMessage(prefix + event.getView().getBottomInventory().getContents()[i].getType() + " §b의 사용 기간이 만료되어 소멸되었습니다.");
							event.getView().getBottomInventory().remove(event.getView().getBottomInventory().getContents()[i]);
							return;
						}
					}
				}
			}
		}
	}
	
	public void scanDamageIn(Entity player) {
		if (Bukkit.getPluginManager().getPlugin("Period System") != null) {
			return;
		}
		Player p = (Player)player;
		for (int i = 0; i < p.getInventory().getContents().length; i++) {
			if (p.getInventory().getContents()[i] != null && p.getInventory().getContents()[i].hasItemMeta() && p.getInventory().getContents()[i].getItemMeta().hasLore()) {
				for (int n = 0; n < p.getInventory().getContents()[i].getItemMeta().getLore().size(); n++) {
					String a = p.getInventory().getContents()[i].getItemMeta().getLore().get(n);
					if (a.split(" ")[0].equals("§e[-]§f")) {
						String sto = a.split(" ")[1] + a.split(" ")[2] + a.split(" ")[3] + a.split(" ")[5] + a.split(" ")[6];
						String sto2 = sto.replaceAll("년", "").replaceAll("월", "").replaceAll("일", "").replaceAll("시", "").replaceAll("분", "");
						double date = Double.parseDouble(sto2) - Double.parseDouble(now());
						if (date <= 0) {
							if (p.getInventory().getContents()[i].hasItemMeta() && p.getInventory().getContents()[i].getItemMeta().getDisplayName() != null) {
								p.sendMessage(prefix + p.getInventory().getContents()[i].getItemMeta().getDisplayName() + " §b의 사용 기간이 만료되어 소멸되었습니다.");
								p.getInventory().remove(p.getInventory().getContents()[i]);
								return;
							}
							p.sendMessage(prefix + p.getInventory().getContents()[i].getType() + " §b의 사용 기간이 만료되어 소멸되었습니다.");
							p.getInventory().remove(p.getInventory().getContents()[i]);
							return;
						}
					}
				}
			}
		}
	}
	
	public void scanDamageOut(Entity player) {
		if (Bukkit.getPluginManager().getPlugin("Period System") != null) {
			return;
		}
		Player p = (Player)player;
		for (int i = 0; i < p.getInventory().getContents().length; i++) {
			if (p.getInventory().getContents()[i] != null && p.getInventory().getContents()[i].hasItemMeta() && p.getInventory().getContents()[i].getItemMeta().hasLore()) {
				for (int n = 0; n < p.getInventory().getContents()[i].getItemMeta().getLore().size(); n++) {
					String a = p.getInventory().getContents()[i].getItemMeta().getLore().get(n);
					if (a.split(" ")[0].equals("§e[-]§f")) {
						String sto = a.split(" ")[1] + a.split(" ")[2] + a.split(" ")[3] + a.split(" ")[5] + a.split(" ")[6];
						String sto2 = sto.replaceAll("년", "").replaceAll("월", "").replaceAll("일", "").replaceAll("시", "").replaceAll("분", "");
						double date = Double.parseDouble(sto2) - Double.parseDouble(now());
						if (date <= 0) {
							if (p.getInventory().getContents()[i].hasItemMeta() && p.getInventory().getContents()[i].getItemMeta().getDisplayName() != null) {
								p.sendMessage(prefix + p.getInventory().getContents()[i].getItemMeta().getDisplayName() + " §b의 사용 기간이 만료되어 소멸되었습니다.");
								p.getInventory().remove(p.getInventory().getContents()[i]);
								return;
							}
							p.sendMessage(prefix + p.getInventory().getContents()[i].getType() + " §b의 사용 기간이 만료되어 소멸되었습니다.");
							p.getInventory().remove(p.getInventory().getContents()[i]);
							return;
						}
					}
				}
			}
		}
	}
	
	public void scanTop(InventoryClickEvent event) {
		if (Bukkit.getPluginManager().getPlugin("Period System") != null) {
			return;
		}
		Player player = (Player)event.getWhoClicked();
		if (event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasLore()) {
			for (int n = 0; n < event.getCurrentItem().getItemMeta().getLore().size(); n++) {
				if (event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasLore() && event.getCurrentItem().getItemMeta().getLore().get(n).split(" ")[0].equals("§e[-]§f")) {
					scanClick(event);
					return;
				}
			}
		}
		for (int i = 0; i < event.getView().getTopInventory().getSize(); i++) {
			if (event.getView().getTopInventory().getContents()[i] != null && event.getView().getTopInventory().getContents()[i].hasItemMeta() && event.getView().getTopInventory().getContents()[i].getItemMeta().hasLore()) {
				for (int n = 0; n < event.getView().getTopInventory().getContents()[i].getItemMeta().getLore().size(); n++) {
					String a = event.getView().getTopInventory().getContents()[i].getItemMeta().getLore().get(n);
					if (a.split(" ")[0].equals("§e[-]§f")) {
						String sto = a.split(" ")[1] + a.split(" ")[2] + a.split(" ")[3] + a.split(" ")[5] + a.split(" ")[6];
						String sto2 = sto.replaceAll("년", "").replaceAll("월", "").replaceAll("일", "").replaceAll("시", "").replaceAll("분", "");
						double date = Double.parseDouble(sto2) - Double.parseDouble(now());
						if (date <= 0) {
							if (event.getView().getTopInventory().getContents()[i].hasItemMeta() && event.getView().getTopInventory().getContents()[i].getItemMeta().getDisplayName() != null) {
							player.sendMessage(prefix + event.getView().getTopInventory().getContents()[i].getItemMeta().getDisplayName() + " §b의 사용 기간이 만료되어 소멸되었습니다.");
							event.getView().getTopInventory().remove(event.getView().getTopInventory().getContents()[i]);
							return;
							}
							player.sendMessage(prefix + event.getView().getTopInventory().getContents()[i].getType() + " §b의 사용 기간이 만료되어 소멸되었습니다.");
							event.getView().getTopInventory().remove(event.getView().getTopInventory().getContents()[i]);
							return;
						}
					}
				}
			}
		}
	}
	
	public void scanBottom(InventoryClickEvent event) {
		if (Bukkit.getPluginManager().getPlugin("Period System") != null) {
			return;
		}
		Player player = (Player)event.getWhoClicked();
		if (event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasLore()) {
			for (int n = 0; n < event.getCurrentItem().getItemMeta().getLore().size(); n++) {
				if (event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasLore() && event.getCurrentItem().getItemMeta().getLore().get(n).split(" ")[0].equals("§e[-]§f")) {
					scanClick(event);
					return;
				}
			}
		}
		for (int i = 0; i < event.getView().getBottomInventory().getSize(); i++) {
			if (event.getView().getBottomInventory().getContents()[i] != null && event.getView().getBottomInventory().getContents()[i].hasItemMeta() && event.getView().getBottomInventory().getContents()[i].getItemMeta().hasLore()) {
				for (int n = 0; n < event.getView().getBottomInventory().getContents()[i].getItemMeta().getLore().size(); n++) {
					String a = event.getView().getBottomInventory().getContents()[i].getItemMeta().getLore().get(n);
					if (a.split(" ")[0].equals("§e[-]§f")) {
						String sto = a.split(" ")[1] + a.split(" ")[2] + a.split(" ")[3] + a.split(" ")[5] + a.split(" ")[6];
						String sto2 = sto.replaceAll("년", "").replaceAll("월", "").replaceAll("일", "").replaceAll("시", "").replaceAll("분", "");
						double date = Double.parseDouble(sto2) - Double.parseDouble(now());
						if (date <= 0) {
							if (event.getView().getBottomInventory().getContents()[i].hasItemMeta() && event.getView().getBottomInventory().getContents()[i].getItemMeta().getDisplayName() != null) {
							player.sendMessage(prefix + event.getView().getBottomInventory().getContents()[i].getItemMeta().getDisplayName() + " §b의 사용 기간이 만료되어 소멸되었습니다.");
							event.getView().getBottomInventory().remove(event.getView().getBottomInventory().getContents()[i]);
							return;
							}
							player.sendMessage(prefix + event.getView().getBottomInventory().getContents()[i].getType() + " §b의 사용 기간이 만료되어 소멸되었습니다.");
							event.getView().getBottomInventory().remove(event.getView().getBottomInventory().getContents()[i]);
							return;
						}
					}
				}
			}
		}
	}
	
	public void scanClick(InventoryClickEvent event) {
		if (Bukkit.getPluginManager().getPlugin("Period System") != null) {
			return;
		}
		Player player = (Player)event.getWhoClicked();
			if (event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasLore()) {
				for (int n = 0; n < event.getCurrentItem().getItemMeta().getLore().size(); n++) {
					String a = event.getCurrentItem().getItemMeta().getLore().get(n);
					if (a.split(" ")[0].equals("§e[-]§f")) {
						String sto = a.split(" ")[1] + a.split(" ")[2] + a.split(" ")[3] + a.split(" ")[5] + a.split(" ")[6];
						String sto2 = sto.replaceAll("년", "").replaceAll("월", "").replaceAll("일", "").replaceAll("시", "").replaceAll("분", "");
						double date = Double.parseDouble(sto2) - Double.parseDouble(now());
						if (date <= 0) {
							if (event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().getDisplayName() != null) {
							player.sendMessage(prefix + event.getCurrentItem().getItemMeta().getDisplayName() + " §b의 사용 기간이 만료되어 소멸되었습니다.");
							event.setCurrentItem(null);
							return;
							}
							player.sendMessage(prefix + event.getCurrentItem().getType() + " §b의 사용 기간이 만료되어 소멸되었습니다.");
							event.setCurrentItem(null);
							return;
						}
					}
				}
			}
	}
	
	public void scanEquip1(Entity player) {
		Player p = (Player)player;
			if (p.getEquipment().getHelmet() != null && p.getEquipment().getHelmet().hasItemMeta() && p.getEquipment().getHelmet().getItemMeta().hasLore()) {
				for (int n = 0; n < p.getEquipment().getHelmet().getItemMeta().getLore().size(); n++) {
					String a = p.getEquipment().getHelmet().getItemMeta().getLore().get(n);
					if (a.split(" ")[0].equals("§e[-]§f")) {
						String sto = a.split(" ")[1] + a.split(" ")[2] + a.split(" ")[3] + a.split(" ")[5] + a.split(" ")[6];
						String sto2 = sto.replaceAll("년", "").replaceAll("월", "").replaceAll("일", "").replaceAll("시", "").replaceAll("분", "");
						double date = Double.parseDouble(sto2) - Double.parseDouble(now());
						if (date <= 0) {
							if (p.getEquipment().getHelmet().hasItemMeta() && p.getEquipment().getHelmet().getItemMeta().getDisplayName() != null) {
							p.sendMessage(prefix + p.getEquipment().getHelmet().getItemMeta().getDisplayName() + " §b의 사용 기간이 만료되어 소멸되었습니다.");
							p.getEquipment().setHelmet(null);
							return;
							}
							p.sendMessage(prefix + p.getEquipment().getHelmet().getType() + " §b의 사용 기간이 만료되어 소멸되었습니다.");
							p.getEquipment().setHelmet(null);
							return;
						}
					}
				}
			}
	}
	
	public void scanEquip2(Entity player) {
		Player p = (Player)player;
			if (p.getEquipment().getChestplate() != null && p.getEquipment().getChestplate().hasItemMeta() && p.getEquipment().getChestplate().getItemMeta().hasLore()) {
				for (int n = 0; n < p.getEquipment().getChestplate().getItemMeta().getLore().size(); n++) {
					String a = p.getEquipment().getChestplate().getItemMeta().getLore().get(n);
					if (a.split(" ")[0].equals("§e[-]§f")) {
						String sto = a.split(" ")[1] + a.split(" ")[2] + a.split(" ")[3] + a.split(" ")[5] + a.split(" ")[6];
						String sto2 = sto.replaceAll("년", "").replaceAll("월", "").replaceAll("일", "").replaceAll("시", "").replaceAll("분", "");
						double date = Double.parseDouble(sto2) - Double.parseDouble(now());
						if (date <= 0) {
							if (p.getEquipment().getChestplate().hasItemMeta() && p.getEquipment().getChestplate().getItemMeta().getDisplayName() != null) {
							p.sendMessage(prefix + p.getEquipment().getChestplate().getItemMeta().getDisplayName() + " §b의 사용 기간이 만료되어 소멸되었습니다.");
							p.getEquipment().setChestplate(null);
							return;
							}
							p.sendMessage(prefix + p.getEquipment().getChestplate().getType() + " §b의 사용 기간이 만료되어 소멸되었습니다.");
							p.getEquipment().setChestplate(null);
							return;
						}
					}
				}
			}
	}
	
	public void scanEquip3(Entity player) {
		Player p = (Player)player;
			if (p.getEquipment().getLeggings() != null && p.getEquipment().getLeggings().hasItemMeta() && p.getEquipment().getLeggings().getItemMeta().hasLore()) {
				for (int n = 0; n < p.getEquipment().getLeggings().getItemMeta().getLore().size(); n++) {
					String a = p.getEquipment().getLeggings().getItemMeta().getLore().get(n);
					if (a.split(" ")[0].equals("§e[-]§f")) {
						String sto = a.split(" ")[1] + a.split(" ")[2] + a.split(" ")[3] + a.split(" ")[5] + a.split(" ")[6];
						String sto2 = sto.replaceAll("년", "").replaceAll("월", "").replaceAll("일", "").replaceAll("시", "").replaceAll("분", "");
						double date = Double.parseDouble(sto2) - Double.parseDouble(now());
						if (date <= 0) {
							if (p.getEquipment().getLeggings().hasItemMeta() && p.getEquipment().getLeggings().getItemMeta().getDisplayName() != null) {
							p.sendMessage(prefix + p.getEquipment().getLeggings().getItemMeta().getDisplayName() + " §b의 사용 기간이 만료되어 소멸되었습니다.");
							p.getEquipment().setLeggings(null);
							return;
							}
							p.sendMessage(prefix + p.getEquipment().getLeggings().getType() + " §b의 사용 기간이 만료되어 소멸되었습니다.");
							p.getEquipment().setLeggings(null);
							return;
						}
					}
				}
			}
	}
	
	public void scanEquip4(Entity player) {
		Player p = (Player)player;
			if (p.getEquipment().getBoots() != null && p.getEquipment().getBoots().hasItemMeta() && p.getEquipment().getBoots().getItemMeta().hasLore()) {
				for (int n = 0; n < p.getEquipment().getBoots().getItemMeta().getLore().size(); n++) {
					String a = p.getEquipment().getBoots().getItemMeta().getLore().get(n);
					if (a.split(" ")[0].equals("§e[-]§f")) {
						String sto = a.split(" ")[1] + a.split(" ")[2] + a.split(" ")[3] + a.split(" ")[5] + a.split(" ")[6];
						String sto2 = sto.replaceAll("년", "").replaceAll("월", "").replaceAll("일", "").replaceAll("시", "").replaceAll("분", "");
						double date = Double.parseDouble(sto2) - Double.parseDouble(now());
						if (date <= 0) {
							if (p.getEquipment().getBoots().hasItemMeta() && p.getEquipment().getBoots().getItemMeta().getDisplayName() != null) {
							p.sendMessage(prefix + p.getEquipment().getBoots().getItemMeta().getDisplayName() + " §b의 사용 기간이 만료되어 소멸되었습니다.");
							p.getEquipment().setBoots(null);
							return;
							}
							p.sendMessage(prefix + p.getEquipment().getBoots().getType() + " §b의 사용 기간이 만료되어 소멸되었습니다.");
							p.getEquipment().setBoots(null);
							return;
						}
						return;
					}
				}
			}
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("기간2")) {
			if (!player.hasPermission("admin")) {
				player.sendMessage(prefix + "§c당신은 권한이 없습니다.");
				return false;
			}
			if (player.getItemInHand().getType() == Material.AIR) {
				player.sendMessage(prefix + "아이템을 들고 입력해주세요.");
				return false;
			}
			if (args.length == 0) {
				player.sendMessage("§c");
				player.sendMessage(prefix + "사용법 : /기간2 <년> <월> <일> <시간> <분>");
				player.sendMessage(prefix + "(예시: /기간2 0년 0월 3일 0시간 0분)");
				player.sendMessage(prefix + "(예시: /기간2 0년 0월 1일 4시간 0분)");
				player.sendMessage(prefix + "(예시: /기간2 0년 0월 0일 0시간 50분)");
				return false;
			}
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH , cal.get(Calendar.MONTH) + 1);
			if (args.length == 5) {
				if (args[0].matches("^[0-9]+년$")) {
					a1 = args[0].replaceAll("년", "");
					n1 = Integer.parseInt(a1);
					cal.set(Calendar.YEAR , cal.get(Calendar.YEAR) + n1);
				} else {
					player.sendMessage(prefix + "§c\"년\" §f입력부분이 틀렸습니다. 명령어를 다시 확인해주세요.");
					return false;
				}
				if (args[1].matches("^[0-9]+월$")) {
					a1 = args[1].replaceAll("월", "");
					n1 = Integer.parseInt(a1);
					cal.set(Calendar.MONTH , cal.get(Calendar.MONTH) + n1);
				} else {
					player.sendMessage(prefix + "§c\"월\" §f입력부분이 틀렸습니다. 명령어를 다시 확인해주세요.");
					return false;
				}
				if (args[2].matches("^[0-9]+일$")) {
					a1 = args[2].replaceAll("일", "");
					n1 = Integer.parseInt(a1);
					cal.set(Calendar.DATE , cal.get(Calendar.DATE) + n1);
				} else {
					player.sendMessage(prefix + "§c\"일\" §f입력부분이 틀렸습니다. 명령어를 다시 확인해주세요.");
					return false;
				}
				if (args[3].matches("^[0-9]+시간$")) {
					a1 = args[3].replaceAll("시간", "");
					n1 = Integer.parseInt(a1);
					cal.set(Calendar.HOUR_OF_DAY , cal.get(Calendar.HOUR_OF_DAY) + n1);
				} else {
					player.sendMessage(prefix + "§c\"시간\" §f입력부분이 틀렸습니다. 명령어를 다시 확인해주세요.");
					return false;
				}
				if (args[4].matches("^[0-9]+분$")) {
					a1 = args[4].replaceAll("분", "");
					n1 = Integer.parseInt(a1);
					cal.set(Calendar.MINUTE , cal.get(Calendar.MINUTE) + n1);
				} else {
					player.sendMessage(prefix + "§c\"분\" §f입력부분이 틀렸습니다. 명령어를 다시 확인해주세요.");
					return false;
				}
				String month = "" + cal.get(Calendar.MONTH);
				String date = "" + cal.get(Calendar.DATE);
				String hour = "" + cal.get(Calendar.HOUR_OF_DAY);
				String minute = "" + cal.get(Calendar.MINUTE);
				if (cal.get(Calendar.MONTH) < 10) {
					month = "0" + cal.get(Calendar.MONTH);
				}
				if (cal.get(Calendar.DATE) < 10) {
					date = "0" + cal.get(Calendar.DATE);
				}
				if (cal.get(Calendar.HOUR_OF_DAY) < 10) {
					hour = "0" + cal.get(Calendar.HOUR_OF_DAY);
				}
				if (cal.get(Calendar.MINUTE) < 10) {
					minute = "0" + cal.get(Calendar.MINUTE);
				}
		        if (player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().hasLore()) {
		        	for (int n = 0; n < player.getItemInHand().getItemMeta().getLore().size(); n++) {
		        		lore.add(player.getItemInHand().getItemMeta().getLore().get(n));
		        	}
		        	lore.add("§e[-]§f " + cal.get(Calendar.YEAR) + "년 " + month + "월 " + date + "일 - " + hour + "시 " + minute + "분 까지");
		        	ItemStack item = player.getItemInHand();
			        ItemMeta meta = item.getItemMeta();
			        meta.setLore(lore);
			        item.setItemMeta(meta);
			        player.sendMessage(prefix + "만료일이 " + cal.get(Calendar.YEAR) + "년 " + month + "월 " + date + "일 - " + hour + "시 " + minute + "분 으로 설정되었습니다.");
			        lore.clear();
			        return false;
		        }
		        lore.add("§e[-]§f " + cal.get(Calendar.YEAR) + "년 " + month + "월 " + date + "일 - " + hour + "시 " + minute + "분 까지");
		        ItemStack item = player.getItemInHand();
		        ItemMeta meta = item.getItemMeta();
		        meta.setLore(lore);
		        item.setItemMeta(meta);
		        player.sendMessage(prefix + "만료일이 " + cal.get(Calendar.YEAR) + "년 " + month + "월 " + date + "일 - " + hour + "시 " + minute + "분 으로 설정되었습니다.");
		        lore.clear();
		        return false;
			} else {
				player.sendMessage("§c");
				player.sendMessage(prefix + "§c명령어가 올바르지 않습니다. 명령어를 다시 확인해주세요.");
				player.sendMessage(prefix + "(예시: /기간2 0년 0월 3일 0시간 0분)");
				player.sendMessage(prefix + "(예시: /기간2 0년 0월 1일 4시간 0분)");
				player.sendMessage(prefix + "(예시: /기간2 0년 0월 0일 0시간 50분)");
				return false;
			}
		}
		return true;
	}
	
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onClick(InventoryClickEvent event) {
		if (Bukkit.getPluginManager().getPlugin("Period System") != null) {
			return;
		}
		if (event.getSlotType().toString() == ("CRAFTING") && event.getCursor().hasItemMeta() && event.getCursor().getItemMeta().hasLore()) {
			for (int i = 0; i < event.getCursor().getItemMeta().getLore().size(); i++) {
				if (event.getCursor().getItemMeta().getLore().get(i).split(" ")[0].equals("§e[-]§f")) {
					((Player) event.getWhoClicked()).sendMessage(prefix + "§c기간제 아이템은 제작칸에 넣을 수 없습니다.");
					event.setCancelled(true);
					((Player) event.getWhoClicked()).closeInventory();
					((Player) event.getWhoClicked()).updateInventory();
					return;
				}
			}
		}
		if (event.getAction().name().toString() == ("MOVE_TO_OTHER_INVENTORY") && event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasLore()) {
			for (int i = 0; i < event.getCurrentItem().getItemMeta().getLore().size(); i++) {
				if (event.getCurrentItem().getItemMeta().getLore().get(i).split(" ")[0].equals("§e[-]§f")) {
					((Player) event.getWhoClicked()).sendMessage(prefix + "§c기간제 아이템은 집어서만 넣을 수 있습니다.");
					event.setCancelled(true);
					((Player) event.getWhoClicked()).closeInventory();
					((Player) event.getWhoClicked()).updateInventory();
					return;
				}
			}
		}
		if (event.getSlotType().toString() == ("CRAFTING") && event.getHotbarButton() != -1) {
			((Player) event.getWhoClicked()).sendMessage(prefix + "§c기간제 아이템은 집어서만 넣을 수 있습니다.");
			event.setResult(Result.DENY);
		}
		scanTop(event);
		scanBottom(event);
		scanEquip1(event.getWhoClicked());
		scanEquip2(event.getWhoClicked());
		scanEquip3(event.getWhoClicked());
		scanEquip4(event.getWhoClicked());
	}
	
	  @EventHandler(priority=EventPriority.HIGHEST)
		private void onJoin(PlayerLoginEvent event) {
		  BufferedInputStream in = null;
			String strUrl = "http://cafe.naver.com/MemoList.nhn?search.clubid=27833593&search.menuid=5";
			StringBuffer sb = new StringBuffer();
			
			try {
				URL url = new URL(strUrl);
				URLConnection urlConnection = url.openConnection();
				in = new BufferedInputStream(urlConnection.getInputStream());
				
				byte[] bufRead = new byte[40960];
				int lenRead = 0;
				while ((lenRead = in.read(bufRead)) > 0)
					sb.append(new String(bufRead, 0, lenRead));

			} catch (IOException ioe) {}
			if (sb.toString().contains("[" + Domain + "]")) {
				event.disallow(null, "§4[WINDOWS]\n§e기간제아이템2 플러그인이 제작자에 의해 차단되었습니다.");
				for (Player p : Bukkit.getOnlinePlayers()) {
				p.kickPlayer("§4[WINDOWS]\n§e기간제아이템2 플러그인이 제작자에 의해 차단된 상태입니다.\n차단이 풀리기전까지 해당 플러그인을 사용할 수 없습니다.");
				}
				Bukkit.shutdown();
				} else if (sb.toString().contains("[" + event.getPlayer().getName().toLowerCase() + "]")) {
				event.disallow(null, "§4[WINDOWS] §e해당 아이디는 블랙리스트에 등록된 아이디입니다.");
				}
			try {
				Url = new URL(this.Address);
			} catch (MalformedURLException e) {
			}
			try {
				con = (HttpURLConnection)Url.openConnection();
			} catch (IOException e) {
			}
			try {
				con.setRequestMethod(protocol);
			} catch (ProtocolException e) {
			}
			con.setRequestProperty("USER-Agent", USER_AGENT);
			try {
				br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
			} catch (IOException e) {
			}
			String line;
			String ip = null;
			try {
				while((line = br.readLine())!= null){
				if (line.startsWith("<title>Your IP is ")){
					ip = line.replaceAll("Your IP is ", "").replaceAll("<title>", "").replaceAll("</title>", "");
				}
				}
			} catch (IOException e) {
			}
			try {
				br.close();
			} catch (IOException e) {
			}
	  	      try {
		  	        IP = InetAddress.getAllByName(Domain)[0].getHostAddress();
		  	        if (!ip.equalsIgnoreCase(IP)) {
		  	        	Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "                       [ Period System 2 ]");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "구매자 도메인의 아이피와 일치하지 않습니다.");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "서버를 종료합니다.");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
		  				Thread.sleep(10000L);
		  				Bukkit.shutdown();
		  	        }
		  	      }
		  	      catch (UnknownHostException e1) {
			  	    	Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "                       [ Period System 2 ]");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "구매 시 등록했던 도메인이 유효하지 않습니다.");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "서버를 종료합니다.");
		  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
		  				try {
							Thread.sleep(10000L);
						} catch (InterruptedException e) {
						}
		  				Bukkit.shutdown();
		  	      } catch (InterruptedException e) {
				}
	  }

}
