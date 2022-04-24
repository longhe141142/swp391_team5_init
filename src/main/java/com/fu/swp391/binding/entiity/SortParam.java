package com.fu.swp391.binding.entiity;

import com.fu.swp391.common.enumConstants.SortEnum;
import java.util.ArrayList;
import java.util.List;

public class SortParam {

  String orderType=null;
  String orderBy=null;
  List<String> orderListType = new ArrayList<>();
  List<String> orderFields= new ArrayList<>();

  public SortParam(String orderType, String orderBy) {
    super();
    this.orderType = orderType;
    this.orderBy = orderBy;
  }

  public SortParam(){
    orderListType.add(SortEnum.ASCENDING);
    orderListType.add(SortEnum.DESCENDING);
  }

  public List<String> getOrderListType() {
    return orderListType;
  }

  public void setOrderListType(List<String> orderListType) {
    this.orderListType = orderListType;
  }

  public List<String> getOrderFields() {
    return orderFields;
  }

  public void setOrderFields(List<String> orderFields) {
    this.orderFields = orderFields;
  }

  public String getOrderType() {
    return orderType;
  }

  public void setOrderType(String orderType) {
    this.orderType = orderType;
  }

  public String getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }
}
