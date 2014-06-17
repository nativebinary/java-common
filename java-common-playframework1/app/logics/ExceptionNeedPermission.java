package logics;

import models.Permission;

public class ExceptionNeedPermission extends Exception {
    public final Permission permission;
    public ExceptionNeedPermission(Permission permission) {
        this.permission = permission;
    }
}
