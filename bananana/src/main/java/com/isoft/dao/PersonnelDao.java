package com.isoft.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface PersonnelDao {
    /**
     * 增加员工
     * @param name
     * @param phone
     * @param password
     * @param sex
     * @param age
     * @param address
     * @param phone
     * @param
     * @param
     * @return
     */
    @Insert("INSERT INTO user(NAME,sno,PASSWORD,sex,age,address,phone,role,school,time,power,job) VALUES(#{name},#{sno},#{password},#{sex},#{age},#{address},#{phone},'2',#{school},#{time},#{power},#{job})")
    int addPeople(@Param("name") String name, @Param("sno") String sno, @Param("password") String password, @Param("sex") String sex, @Param("age") String age, @Param("address") String address, @Param("phone") String phone,  @Param("school") String school, @Param("time") String time, @Param("power")String power,@Param("job") String job);

}
