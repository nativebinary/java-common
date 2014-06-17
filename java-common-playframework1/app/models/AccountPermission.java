package models;

import java.util.ArrayList;
import java.util.List;

public class AccountPermission extends ModelUuid {

    @siena.Index("index_AccountPermission_account")
    public Account account;

    @siena.Index("index_AccountPermission_permission")
    public Permission permission;


    public static List<Permission> getListPermission(Account account) {
        List<AccountPermission> listUserPermission = all().filter("account", account).fetch();
        List<Permission> listPermission = new ArrayList<Permission>();
        listPermission.add(Permission.Login);

        for (AccountPermission userPermission : listUserPermission)
            listPermission.add(userPermission.permission);

        return listPermission;
    }


}
