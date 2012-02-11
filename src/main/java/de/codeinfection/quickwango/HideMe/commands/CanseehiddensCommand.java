package de.codeinfection.quickwango.HideMe.commands;

import de.codeinfection.quickwango.HideMe.HideMe;
import de.codeinfection.quickwango.HideMe.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permissible;
/**
 *
 * @author CodeInfection
 */
public class CanseehiddensCommand implements CommandExecutor
{
    protected final HideMe plugin;
    protected final Server server;

    
    public CanseehiddensCommand(HideMe plugin)
    {
        this.plugin = plugin;
        this.server = plugin.getServer();
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player target;
        if (args.length > 0)
        {
            args[0] = args[0].trim().toLowerCase();
            if (sender instanceof Permissible && !Permissions.HIDDEN_OTHERS.isAuthorized((Permissible)sender))
            {
                sender.sendMessage(ChatColor.RED + "You are not allowed to hide others.");
                return true;
            }
            target = this.server.getPlayerExact(args[0]);
            if (target == null)
            {
                sender.sendMessage(ChatColor.RED + "Couldn't find that player.");
                return true;
            }
        }
        else
        {
            if (sender instanceof Player)
            {
                target = (Player)sender;
            }
            else
            {
                sender.sendMessage(ChatColor.RED + "Only players can hide!");
                return true;
            }
        }
        
        if (sender == target && !Permissions.CANSEEHIDDENS.isAuthorized(target))
        {
            sender.sendMessage(ChatColor.RED + "You are not allowed to check your state!");
            return true;
        }
        else if (!Permissions.CANSEEHIDDENS_OTHERS.isAuthorized(sender))
        {
            sender.sendMessage(ChatColor.RED + "You are not allowed to check the state of others!");
            return true;
        }
        if (this.plugin.canSeeHiddens.contains(target))
        {
            if (target == sender)
            {
                target.sendMessage(ChatColor.GREEN + "You CAN see hidden players");
            }
            else
            {
                sender.sendMessage(ChatColor.GREEN + "He CAN see hidden players!");
            }
        }
        else
        {
            if (target == sender)
            {
                target.sendMessage(ChatColor.RED + "You can NOT see hidden players!");
            }
            if (target != sender)
            {
                sender.sendMessage(ChatColor.RED + "He can NOT see hidden players!");
            }
        }
        return true;
    }
}