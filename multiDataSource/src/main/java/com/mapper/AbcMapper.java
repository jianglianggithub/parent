package com.mapper;

import com.pojo.Pyg;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AbcMapper {


    @Select(" select * from abc ")
    List<Map> select();
    @Select(" select * from tb_item   ")
    List<Map> select2();

    @Insert("insert into tb_item(id,title,sell_point,num,user_id) values(#{i},#{aa},#{bb},#{cc},#{userid})")//now()是获取得mysql 时区得时间 如果
            // 链接得时候 用utc 但是 使用 now() 得时候 他默认 会变成mysql 对应得时区
        //假设  链接 为 utc 也就是 +00：00
    //mysql = 东1 时区 使用now 设置进去 其实是 世界时间+1小时
    // 获取出来 原本应该 东8时区-东1时区=7小时
    // 但是 由于 是utc 默认mysql 时区=+00 获取出来 对你设置进去得 东1时区 加了一个小时
    //就变成 真正获取出来得时间是东9时区了。而不是东8 这点要注意
    void inss(int i, String aa, String bb, String cc, int userid);


    int insert(Map map);
    List<Pyg> ssss();

    @Insert("insert into abc(aa) values( #{aa})")
    void insertAbc(String aa);
}
