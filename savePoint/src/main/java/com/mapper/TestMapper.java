package com.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface TestMapper {


    @Update("update test set username='1' ")
    void update();

    @Update("update test set password='2' ")
    void update1();
}
