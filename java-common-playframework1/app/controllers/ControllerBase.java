package controllers;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import common.basic.logs.Logger;
import common.basic.utils.HashMapStringObject;
import common.basic.utils.MimeType;
import common.basic.utils.StringUtil;
import common.play1.extensions.ExclusionStrategyWithExcludeGson;
import common.play1.utils.ValidationUtil;
import logics.Check;
import logics.ExceptionNeedAuth;
import logics.ExceptionNeedPermission;
import logics.Json;
import logics.PermissionLogic;
import logics.SessionLogic;
import models.Account;
import models.Permission;
import play.i18n.Messages;
import play.mvc.Before;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControllerBase extends Controller {


    private static boolean hasJson() {
        final boolean hasJsonController = null != getControllerAnnotation(Json.class);
        final boolean hasJsonAction = null != getActionAnnotation(Json.class);
        return hasJsonController || hasJsonAction;

    }

    private static List<Permission> getListPermissionRequired() {
        List<Permission> listPermissionRequired = new ArrayList<Permission>();

        Check checkController = getControllerAnnotation(Check.class);
        if(null != checkController)
            listPermissionRequired.addAll(Arrays.asList(checkController.value()));

        Check checkAction = getActionAnnotation(Check.class);
        if(null != checkAction)
            listPermissionRequired.addAll(Arrays.asList(checkAction.value()));

        return listPermissionRequired;
    }


    @Before
    protected static void checkPermission(){
        try
        {
            if(hasJson())
                return;

            checkPermissionThrows(getListPermissionRequired());
        }
        catch (ExceptionNeedPermission e)
        {
            flash.error("Permission denied.");
            flash.put("permission", e.permission.toString());
            Home.index();
        }
        catch (ExceptionNeedAuth e)
        {
            flash.error("Permission denied.");
            Home.index();
        }
    }

    @Before
    protected static void checkPermissionJson(){
        final List<Permission> listPermissionRequired = getListPermissionRequired();

        checkPermissionJson(listPermissionRequired);
    }

    protected static void checkPermissionJson(List<Permission> listPermissionRequired) {
        try
        {
            if(!hasJson())
                return;

            checkPermissionThrows(listPermissionRequired);
        }
        catch (ExceptionNeedPermission e)
        {
            renderJsonFalse(new HashMapStringObject().and("message", "Permission denied.").and("permission", e.permission));
        }
        catch (ExceptionNeedAuth e)
        {
            renderJsonFalse("Permission denied.");
        }
    }


    protected static void checkPermissionThrows(List<Permission> listPermissionRequired) throws ExceptionNeedAuth, ExceptionNeedPermission {
        if(0 == listPermissionRequired.size())
            return;

        long accountId = getAccountId();
        if(0 == accountId)
            throw new ExceptionNeedAuth();

        Account account = Account.findById(accountId);
        if(null == account)
            throw new ExceptionNeedAuth();

        final Permission permission = PermissionLogic.checkPermission(account, listPermissionRequired.toArray(new Permission[listPermissionRequired.size()]));
        if(null != permission)
            throw new ExceptionNeedPermission(permission);
    }

    public static boolean checkPermission(Permission permission) {
        final Account accountLogin = getAccountLogin();

        //noinspection SimplifiableIfStatement
        if(accountLogin == null)
            return false;

        return null == PermissionLogic.checkPermission(accountLogin, permission);
    }

    protected static boolean isAdmin() {
        return checkPermission(Permission.Admin);
    }




    @Before
    protected static void addRenderArgAccountLogin() {
        Account account = getAccountLogin();
        if(null != account)
            renderArgs.put("accountLogin", account);
    }

    protected static Account getAccountLogin() {
        long accountId = getAccountId();
        return Account.findById(accountId);
    }

    protected static long getAccountId() {
        return SessionLogic.getAccountId(session);
    }

    protected static boolean isLoggedIn() {
        final Account accountLogin = getAccountLogin();
        return null != accountLogin;
    }

    @Deprecated // use renderJsonTrue or renderJsonFalse
    protected static void renderJSON(Object o) {
        Controller.renderJSON(toJsonString(o));
    }

    private static String toJsonString(Object o) {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setExclusionStrategies(new ExclusionStrategyWithExcludeGson())
                .create();

        return gson.toJson(o);
    }


    protected static void renderJsonTrue() {
        //noinspection deprecation
        renderJSON(new HashMapStringObject());
    }

    protected static void renderJsonTrue(Object result) {
        //noinspection deprecation
        renderJSON(result);
    }

    protected static void renderJsonFalse(String errorMessage) {
        final String keyUnderline = errorMessage.replaceAll(" ", "_").toLowerCase();
        String value = Messages.get(keyUnderline);

        if (StringUtil.equals(value, keyUnderline))
            value = errorMessage;

        renderJsonFalse(new HashMapStringObject().and("message", value));
    }

    protected static void renderJsonFalse(Object result) {
        response.status = 590;
        response.contentType = MimeType.Json.getValue();
        Logger.i(request.url, result);

        //noinspection deprecation
        renderJSON(result);
    }


    @Before
    private static void validate() {
        String errorMessage = ValidationUtil.checkJoined(params);
        if(!StringUtil.isNullOrEmpty(errorMessage))
        {
            if(hasJson())
            {
                renderJsonFalse(errorMessage);
                return;
            }
            else
            {
                flash.error(errorMessage);
                redirect("../index");
            }
            //noinspection UnnecessaryReturnStatement
            return;
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public static void redirectToSlash(){
        redirect(request.url + "/", true);
    }
}

