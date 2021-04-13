package com.isoft.dao;

import com.isoft.entity.order;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface orderDao {
    @Select("select consignee,address,print_info,mobile,postscript,order_price from hiolabs_order where pay_status=2")
    ArrayList<order> getorder();
}
