package cn.com.gary.springboot.data.mongo.common.pojo;

/**
 *
 */
public class QueryParam {
    /**
     * collection field name
     */
    private String fieldName;
    /**
     * Operator name
     */
    private String operator;
    /**
     * value
     */
    private Object value;

    public QueryParam() {

    }

    public QueryParam(String fieldName, String operator, Object value) {
        this.fieldName = fieldName;
        this.operator = operator;
        this.value = value;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}