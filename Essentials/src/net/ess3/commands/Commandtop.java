package net.ess3.commands;

import static net.ess3.I18n._;
import net.ess3.economy.Trade;
import net.ess3.api.IUser;
import net.ess3.api.server.Location;
//TODO: remove bukkit
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;


public class Commandtop extends EssentialsCommand
{
	@Override
	public void run(final IUser user, final String commandLabel, final String[] args) throws Exception
	{
		final int topX = user.getLocation().getBlockX();
		final int topZ = user.getLocation().getBlockZ();
		final int topY = user.getWorld().getHighestBlockYAt(topX, topZ);
		user.getTeleport().teleport(new Location(user.getWorld(), user.getLocation().getX(), topY + 1, user.getLocation().getZ(), user.getLocation().getYaw(), user.getLocation().getPitch()), new Trade(commandName, ess), TeleportCause.COMMAND);
		user.sendMessage(_("teleportTop"));
	}
}
