package com.netty.log.dto;

/**
 * Created by raja .
 */
public enum AttributeOperatorsEnum {

    IS("is"), BETWEEN("between"), AT_LEAST("atLeast"), AT_MOST("atMost");

    private String operator;

    private AttributeOperatorsEnum(String operator) {
        this.operator = operator;
    }
}
