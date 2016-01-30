package com.netty.log.dto;


import java.io.Serializable;

/**
 * Created by raja.
 */
public class AttributeSegment implements Serializable {

    private String propertyName;
    private String dataType;
    private String value;
    private long valueFrom;
    private long valueTo;
    private AttributeOperatorsEnum operator;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getValueFrom() {
        return valueFrom;
    }

    public void setValueFrom(long valueFrom) {
        this.valueFrom = valueFrom;
    }

    public long getValueTo() {
        return valueTo;
    }

    public void setValueTo(long valueTo) {
        this.valueTo = valueTo;
    }

    public AttributeOperatorsEnum getOperator() {
        return operator;
    }

    public void setOperator(AttributeOperatorsEnum operator) {
        this.operator = operator;
    }
}
