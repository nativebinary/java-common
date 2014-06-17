package controllers.api.v1;

import controllers.ControllerBase;
import logics.Json;
import models.Account;
import models.AccountDevice;
import models.Device;
import models.OS;

@Json
public class AccountDevices extends ControllerBase {
    @SuppressWarnings("UnusedDeclaration")
    public static void register(String udid, OS os, int version){ // void 장비를 등록합니다.
        try {
            Device device = InsertOrUpdateDevice(udid, os, version);

            final Account account = getAccountLogin();
            if (account == null)
                return;

            AccountDevice accountDevice = AccountDevice.findByAccountDevice(account, device);
            if(null != accountDevice)
                return;

            AccountDevice.deleteByDevice(device);
            accountDevice = new AccountDevice(account, device);
            accountDevice.insert();
        }
        finally {
            renderJsonTrue();
        }
    }

    private static Device InsertOrUpdateDevice(String udid, OS os, int version) {
        Device device = Device.findByUdid(udid);
        if(device == null)
        {
            device = new Device(udid, os, version);
            device.insert();
            return device;
        }

        if(device.version != version)
        {
            device.version = version;
            device.update();
        }

        return device;
    }

    @SuppressWarnings("UnusedDeclaration")
    public static void deregister(String udid) { // void 장비 등록을 해제합니다.

        try {
            final Device device = Device.findByUdid(udid);
            if (device == null)
                return;

            AccountDevice.all().filter("device", device).delete();
        }
        finally {
            renderJsonTrue();
        }
    }
}

