package de.codeinfection.quickwango.HideMe;

import org.bukkit.permissions.Permissible;

/**
 *
 * @author CodeInfection
 */
public enum Permissions
{
    HIDE("hide"),
    HIDE_AUTO("hide.auto"),
    HIDE_OTHERS("hide.others"),
    SEEHIDDENS("seehiddens"),
    SEEHIDDENS_AUTO("seehiddens.auto"),
    LISTHIDDENS("listhiddens"),
    LISTSEEHIDDENS("listseehiddens"),
    DROP("drop");

    private static final String PERMISSION_BASE = "HideMe.";
    private String permission;


    Permissions(String permission)
    {
        this.permission = PERMISSION_BASE + permission;
    }

    public boolean isAuthorized(final Permissible permissible)
    {
        return permissible.hasPermission(this.permission);
    }
}
