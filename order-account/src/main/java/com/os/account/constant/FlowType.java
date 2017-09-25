package com.os.account.constant;

import org.apache.commons.lang3.EnumUtils;

import java.util.Enumeration;

public enum FlowType {
    IN("income"),OUT("pay");

    private String value;

    FlowType(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    public FlowType getFlowType(String value){
        for(FlowType type:FlowType.values()){
            if(value.equals(type.getValue())){
                return type;
            }
        }
        throw new IllegalArgumentException("No correct Flow Type for value ["+value+"]");
    }
}
