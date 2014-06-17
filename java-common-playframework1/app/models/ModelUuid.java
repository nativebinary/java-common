package models;

import siena.Generator;

public abstract class ModelUuid extends ModelBase {

    @siena.Id(Generator.UUID)
    public String id;

}

