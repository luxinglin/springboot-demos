package cn.com.gary.springboot.data.mongo.common.pojo;

/**
 * @author luxinglin
 * @version 1.0
 * @Description: <p>
 * $eq Matches values that are equal to a specified value.
 * </p>
 * <p>
 * $gt Matches values that are greater than a specified value.
 * </p>
 * <p>
 * $gte Matches values that are greater than or equal to a specified
 * value.
 * </p>
 * <p>
 * $in Matches any of the values specified in an array.
 * </p>
 * <p>
 * $lt Matches values that are less than a specified value.
 * </p>
 * <p>
 * $lte Matches values that are less than or equal to a specified value.
 * </p>
 * <p>
 * $ne Matches all values that are not equal to a specified value.
 * </p>
 * <p>
 * $nin Matches none of the values specified in an array.
 * </p>
 * @create 2019-04-23 13:33
 **/
public enum OperatorEnum {
    EQ, GT, GTE, IN, LT, LTE, NE, NIN;
}
