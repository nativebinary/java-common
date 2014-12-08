package models;

import common.basic.facades.jsons.gson.GsonUtil;
import common.basic.utils.StringUtil;
import common.basic.utils.UuidUtil;
import common.play1.utils.CryptoUtil;

import java.util.Date;
import java.util.List;

public class Account extends ModelAutoIncrement {

    public static Account findByEmail(String email) {
        return all().filter("email", email).get();
    }

    public static Account findByNickname(String nickname) {
        return all().filter("nickname", nickname).get();
    }

    @play.data.validation.Email
    @siena.Index("Index_Account_email")
    @siena.Unique("Unique_Account_email")
    @common.play1.annotations.ExcludeGson
    public String email;

    @common.play1.annotations.ExcludeGson
    @siena.NotNull
    public String salt;

    @common.play1.annotations.ExcludeGson
    @siena.NotNull
    public String password;

    @siena.NotNull
    @siena.Index("Index_Account_nickname")
    @siena.Unique("Unique_Account_nickname")
    public String nickname;

    @common.play1.annotations.ExcludeGson
    public Date dateEmailVerified;

    @common.play1.annotations.ExcludeGson
    public String emailVerificationCode;


    @common.play1.annotations.ExcludeGson
    public Date dateLeaved;

    @common.play1.annotations.ExcludeGson
    public String leavedReason;

    public Date dateLastLoggedIn;

    public AttachmentAccountProfile attachmentAccountProfile;


    public void setPasswordToEncrypt(String password) {
        if(StringUtil.isNullOrEmpty(salt))
            salt = CryptoUtil.generateSalt();

        this.password = CryptoUtil.encrypt(salt, password);
    }

    public boolean isPasswordCorrect(String password)
    {
        return CryptoUtil.isValid(salt, password, this.password);
    }

    public List<Permission> getListPermission() {
        return AccountPermission.getListPermission(this);
    }

    public String getJsonArrayPermission() {
        return GsonUtil.toJsonString(getListPermission());
    }

    public void load() {
        if(attachmentAccountProfile != null)
            attachmentAccountProfile.get();
    }

    public void generateEmailVerificationCode() {

        this.emailVerificationCode = UuidUtil.generateWithoutDash();
    }

    public void sendMailConfirmEmail() {


    }
}
