package de.cubeisland.HideMe.commands;

import de.cubeisland.HideMe.HideMe;
import de.cubeisland.HideMe.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
/**
 *
 * @author CodeInfection
 */
public class HiddenCommand implements CommandExecutor
{
    protected final HideMe plugin;
    protected final Server server;

    
    public HiddenCommand(HideMe plugin)
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
        
        if (sender == target && !Permissions.HIDDEN.isAuthorized(target))
        {
            // actually: no permissions
            sender.sendMessage("Unknown command. Type \"help\" for help.");
            return true;
        }
        else if (sender != target && !Permissions.HIDDEN_OTHERS.isAuthorized(sender))
        {
            // actually: no permissions
            sender.sendMessage("Unknown command. Type \"help\" for help.");
            return true;
        }
        
        if (this.plugin.hiddenPlayers.contains(target))
        {
            if (target == sender)
            {
                target.sendMessage(ChatColor.GREEN + "You ARE hidden");
            }
            else
            {
                sender.sendMessage(ChatColor.GREEN + "He IS hidden!");
            }
        }
        else
        {
            if (target == sender)
            {
                target.sendMessage(ChatColor.RED + "You are NOT hidden!");
            }
            if (target != sender)
            {
                sender.sendMessage(ChatColor.RED + "He is NOT hidden!");
            }
        }
        return true;
    }
}
