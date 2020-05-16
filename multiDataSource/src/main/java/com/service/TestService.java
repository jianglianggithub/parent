package com.service;


import com.mapper.AbcMapper;
import com.pojo.Pyg;
import com.source.TargetDataSource;
import org.assertj.core.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class TestService {


    @Autowired
    TestService service;


    @Autowired
    AbcMapper mapper;







    public void  a1() throws InterruptedException {
         mapper.inss(2,"bb","bb","1",1);

    }

    @TargetDataSource("slave")
    @Transactional
    public void  a2(){
        List<Pyg> select = mapper.ssss();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:Ss");

        System.out.println(format.format(select.get(0).getAaaa()));
//       service.a3();
//        int i=1/0;


    }



    //对于 嵌套事务来说。里面报错 事务可以回滚 但是 外面 报错 只能回滚外面那个  里面回滚不了。
    @TargetDataSource("slave")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void  a3(){
        mapper.insertAbc("aa");
        int i=1/0;

    }


}
