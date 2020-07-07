package com.helpit.model;

public enum Types {

    family("TYPE_FAMILY"),
    peace ("TYPE_PEACE"),
    environment("TYPE_ENVIRONMENT"),
    disease ("TYPE_DISEASE"),
    education ("TYPE_EDUCATION"),
    culture ("TYPE_CULTURE"),
    animals("TYPE_ANIMALS"),
    addiction   ("TYPE_ADDICTION")  ;

    private String typeName;

    Types(String typeName){
        this.typeName=typeName;
    }

    public String getTypeName () {
        return typeName;
    }


}
