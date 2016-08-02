package controllers;


import logics.Check;
import models.Permission;

@Check(Permission.Admin)
public class Tests extends ControllerBase {
    public static void index(){
        render();
    }

    public static void rest(){
        render();
    }

}