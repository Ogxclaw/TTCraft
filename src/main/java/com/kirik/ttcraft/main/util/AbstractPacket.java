/*
 * package com.kirik.ttcraft.main.util;
 * 
 * import com.comphenix.protocol.PacketType;
 * import com.comphenix.protocol.ProtocolLibrary;
 * import com.comphenix.protocol.events.PacketContainer;
 * import org.bukkit.entity.Player;
 * 
 * import java.lang.reflect.InvocationTargetException;
 * 
 * public abstract class AbstractPacket {
 * 
 * // The packet being modified
 * protected PacketContainer handle;
 * 
 */
/**
 * Constructs a new strongly typed wrapper for the given packet.
 * 
 * @param handle - handle to the raw packet data.
 * @param type   - packet type
 *//*
	 * 
	 * protected AbstractPacket(PacketContainer handle, PacketType type) {
	 * // Make sure we're given a valid packet
	 * if(handle == null)
	 * throw new IllegalArgumentException("Packet handle cannot be null");
	 * if(handle.getType() != type)
	 * throw new IllegalArgumentException(handle.getHandle() +
	 * " is not a packet of type " + type);
	 * 
	 * this.handle = handle;
	 * }
	 * 
	 */
/**
 * Retrieve a handle to the raw packet data.
 * 
 * @return Raw packet data.
 *//*
	 * 
	 * public PacketContainer getHandle() {
	 * return handle;
	 * }
	 * 
	 */
/**
 * Send the current packet to the given receiver
 * 
 * @param receiver - the receiver
 * @throws RuntimeException if the packet cannot be sent
 *//*
	 * 
	 * public void sendPacket(Player receiver) {
	 * try {
	 * ProtocolLibrary.getProtocolManager().sendServerPacket(receiver, getHandle());
	 * }catch(InvocationTargetException e) {
	 * throw new RuntimeException("Cannot send packet.", e);
	 * }
	 * }
	 * 
	 */
/**
 * Simulate receiving the current packet from the given sender
 * 
 * @param sender - the sender
 * @throws RuntimeException if the packet cannot be received
 *//*
	 * 
	 * public void receivePacket(Player sender) {
	 * try {
	 * ProtocolLibrary.getProtocolManager().recieveClientPacket(sender,
	 * getHandle());
	 * }catch(Exception e) {
	 * throw new RuntimeException("Cannot receive packet.", e);
	 * }
	 * }
	 * 
	 * 
	 * }
	 */
