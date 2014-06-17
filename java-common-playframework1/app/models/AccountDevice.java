package models;

import com.sun.istack.NotNull;
import siena.Index;

import java.util.List;

public class AccountDevice extends ModelAutoIncrement {

    public static AccountDevice findByAccountDevice(Account account, Device device) {
        return all().filter("account", account).filter("device", device).get();
    }

    public static List<AccountDevice> findByAccount(Account account) {
        return all().filter("account", account).fetch();
    }

    public static List<AccountDevice> findByDevice(Device device) {
        return all().filter("device", device).fetch();
    }

    public static int deleteByDevice(Device device) {
        return all().filter("device", device).delete();
    }


    @NotNull
    @Index("index_AccountDevice_account")
    public Account account;

    @NotNull
    @Index("index_AccountDevice_device")
    public Device device;

    public AccountDevice() {
    }

    public AccountDevice(Account account, Device device) {
        this.account = account;
        this.device = device;
    }

}
