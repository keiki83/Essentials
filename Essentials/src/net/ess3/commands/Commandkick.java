package net.ess3.commands;

import net.ess3.Console;
import static net.ess3.I18n._;
import net.ess3.api.IUser;
import net.ess3.api.server.CommandSender;
import net.ess3.api.server.Player;
import net.ess3.permissions.Permissions;


public class Commandkick extends EssentialsCommand
{
	@Override
	protected void run(final CommandSender sender, final String commandLabel, final String[] args) throws Exception
	{
		if (args.length < 1)
		{
			throw new NotEnoughArgumentsException();
		}

		final IUser user = ess.getUserMap().matchUser(args[0], false, false);
		if (Permissions.KICK_EXEMPT.isAuthorized(user))
		{
			throw new Exception(_("kickExempt"));
		}
		final String kickReason = args.length > 1 ? getFinalArg(args, 1) : _("kickDefault");
		user.kickPlayer(kickReason);
		final String senderName = sender instanceof Player ? ((Player)sender).getDisplayName() : Console.NAME;

		for (Player onlinePlayer : server.getOnlinePlayers())
		{
			final IUser player = onlinePlayer.getUser();
			if (Permissions.KICK_NOTIFY.isAuthorized(player))
			{
				onlinePlayer.sendMessage(_("playerKicked", senderName, user.getName(), kickReason));
			}
		}
	}
}
