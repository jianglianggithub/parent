package com.责任链.dubbo;

import org.apache.commons.compress.utils.Lists;
import org.elasticsearch.common.collect.HppcMaps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Start {


    public static void main(String[] args) {
        Filter filter1=new Filter() {
            @Override
            public void doFilter(Invoker invoker, Map pram) {
                pram.put("1","1");
                System.out.println("filter1拿到参数做调整后"+pram);
                invoker.invoke(pram);
            }
        };
        Filter filter2=new Filter() {
            @Override
            public void doFilter(Invoker invoker, Map pram) {
                pram.put("2","2");
                System.out.println("filter2拿到参数做调整后"+pram);
                invoker.invoke(pram);
            }
        };
        List<Filter> list= Lists.newArrayList();
        list.add(filter1);
        list.add(filter2);

        Invoker invoker=(param)->{
            System.out.println(param);
        };

        Map map= new HashMap();
        ss(list,invoker).invoke(map);
    }


    public static Invoker ss(List<Filter> filters,Invoker invoker) {
        Invoker result=invoker;
        for (int i = filters.size()-1; i >=0; i--) {
            Invoker temp=result;
            Filter filter = filters.get(i);
            result=new Invoker() {
                @Override
                public void invoke(Map param) {
                    filter.doFilter(temp,param);
                }
            };
        }

        return result;

    }

}
