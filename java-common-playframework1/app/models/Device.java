package models;

public class Device extends ModelAutoIncrement {
    public static Device findByUdid(String udid) {
        return all().filter("udid", udid).get();
    }

    @siena.NotNull
    @siena.Index("Index_Device_udid")
    @siena.Unique("Unique_Device_udid")
    public String udid;

    @siena.NotNull
    @siena.Index("Index_Device_os")
    public OS os;

    @siena.NotNull
    @siena.Index("Index_Device_version")
    public int version;

    public Device() {
    }

    public Device(String udid, OS os, int version) {
        this.udid = udid;
        this.os = os;
        this.version = version;
    }
}
