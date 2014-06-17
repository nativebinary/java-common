package models;

import siena.Generator;

public abstract class ModelAutoIncrement extends ModelBase {

    @siena.Id(Generator.AUTO_INCREMENT)
    public long id;

}
