package com.kirik.ttcraft.main.util;

public class PlayerNotFoundException extends TTCraftCommandException {

	private static final long serialVersionUID = 1L;

	public PlayerNotFoundException() {
		this("Player not found");
	}

	public PlayerNotFoundException(String message) {
		super(message);
		setColor('4');
	}

	public PlayerNotFoundException(Throwable cause) {
		this("Player not found", cause);
	}

	public PlayerNotFoundException(String message, Throwable cause) {
		super(message, cause);
		setColor('4');
	}
}
