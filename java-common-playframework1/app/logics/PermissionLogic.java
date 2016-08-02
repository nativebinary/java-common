package logics;

import models.Account;
import models.Permission;

import java.util.List;

public class PermissionLogic {
    public static Permission checkPermission(Account account, Permission... arrayPermissionRequired) {
        List<Permission> listPermissionGranted = account.getListPermission();

        for(Permission permission : arrayPermissionRequired) {
            if (!listPermissionGranted.contains(permission))
                return permission;
        }

        return null;
    }

    public static boolean isAdmin(Account account) {
        return null != checkPermission(account, Permission.Admin);
    }
}
