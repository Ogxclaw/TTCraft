package com.kirik.ttcraft.events.managers;

import java.util.HashMap;
import java.util.Stack;

import org.bukkit.entity.Player;

import com.kirik.ttcraft.events.tasks.RequestTask;
import com.kirik.ttcraft.main.TTCraft;

public class RequestManager implements RequestTask {

	protected static final HashMap<Player, Stack<RequestManager>> requests = new HashMap<Player, Stack<RequestManager>>();

	public static RequestManager getRequest(Player forPlayer) {
		return getRequest(forPlayer, null);
	}

	public static RequestManager getRequest(Player forPlayer, Player byPlayer) {
		if (requests.containsKey(forPlayer)) {
			Stack<RequestManager> reqs = requests.get(forPlayer);
			if (byPlayer == null) {
				return reqs.isEmpty() ? null : reqs.firstElement();
			} else {
				for (RequestManager req : reqs) {
					if (req.byPlayer == byPlayer) {
						return req;
					}
				}
			}
		}
		return null;
	}

	public static void timeoutCheck(Player forPlayer) {
		@SuppressWarnings("unchecked")
		Stack<RequestManager> reqs = (Stack<RequestManager>) requests.get(forPlayer).clone();
		for (RequestManager req : reqs) {
			if (!req.isInTime()) {
				req.remove();
			}
		}
	}

	public static void timeoutCheck() {
		for (Player ply : requests.keySet()) {
			timeoutCheck(ply);
		}
	}

	public static void removeAllRequests(Player forPlayer) {
		requests.remove(forPlayer);
	}

	private final Player forPlayer;
	private final Player byPlayer;
	private final RequestTask execute;
	private long timeout;

	private TTCraft plugin = TTCraft.instance;

	public RequestManager(Player forPlayer, Player byPlayer, RequestTask requestTask) {
		this.byPlayer = byPlayer;
		this.forPlayer = forPlayer;
		this.execute = requestTask;
		this.timeout = 0;
	}

	@Override
	public void accept() {
		try {
			execute.accept();
		} catch (Exception e) {
		}
		remove();
	}

	@Override
	public void decline() {
		try {
			execute.decline();
		} catch (Exception e) {
		}
		remove();
	}

	private void remove() {
		requests.get(forPlayer).remove(this);
	}

	public void add(String msg) {
		RequestManager req = getRequest(forPlayer, byPlayer);
		if (req != null) {
			req.remove();
		}
		this.timeout = System.currentTimeMillis() + 30000;
		if (!requests.containsKey(forPlayer)) {
			requests.put(forPlayer, new Stack<RequestManager>());
		}
		requests.get(forPlayer).add(this);
		plugin.playerManager.sendMessage(forPlayer,
				String.format(msg, forPlayer.getDisplayName(), byPlayer.getDisplayName()));
		plugin.playerManager.sendMessage(forPlayer, "Use /tpaccept to accept or /tpdeny to decline");
	}

	private boolean isInTime() {
		return System.currentTimeMillis() <= timeout;
	}
}
