package de.codeinfection.quickwango.HideMe.commands;

import de.codeinfection.quickwango.HideMe.HideMe;
import de.codeinfection.quickwango.HideMe.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permissible;

/**
 *
 * @author CodeInfection
 */
public class ListhiddensCommand implements CommandExecutor
{
    protected final HideMe plugin;

    
    public ListhiddensCommand(HideMe plugin)
    {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Permissible && !Permissions.LISTHIDDENS.isAuthorized((Permissible)sender))
        {
            sender.sendMessage(ChatColor.RED + "You are not allowed to list the hidden players!");
            return true;
        }

        if (this.plugin.hiddenPlayers.isEmpty())
        {
            sender.sendMessage(ChatColor.RED + "There are no hidden players!");
        }
        else
        {
            sender.sendMessage("Hidden players:");
            for (Player player : this.plugin.hiddenPlayers)
            {
                sender.sendMessage(" - " + ChatColor.YELLOW + player.getName());
            }
            HideMe.log("'" + sender.getName() + "' listed hidden players!");
        }
        
        return true;
    }
}
