package models;

import common.basic.jsons.GsonUtil;
import play.modules.siena.EnhancedModel;
import siena.core.lifecycle.PreInsert;
import siena.core.lifecycle.PreSave;
import siena.core.lifecycle.PreUpdate;

import java.util.Date;
import javax.persistence.PrePersist;

public abstract class ModelBase extends EnhancedModel {

    @siena.NotNull
    @siena.DateTime
    public Date dateCreated = new Date();

    @siena.NotNull
    @siena.DateTime
    public Date dateModified = new Date();

    public void touch(){
        dateModified = new Date();
    }

    @PrePersist
    @PreInsert
    @PreUpdate
    @PreSave
    public void prePersist() {
        touch();
    }

    @Override public void insert() { touch(); super.insert(); }
    @Override public void update() { touch(); super.update(); }
    @Override public void save()   { touch(); super.save(); }
    @Override public void _save()  { touch(); super._save(); }

    @Override
    public String toString() { return GsonUtil.toJson(this); }
}

