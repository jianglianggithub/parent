package com.抽象工厂设计模式;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Product1 extends AbstractFactory {
    @Override
    Shucai shucai() {

        return new Shucai() {
            @Override
            public void show() {
                System.out.println("蔬菜1");
            }
        };
    }

    @Override
    Dongwu dongwu() {

        return  new Dongwu() {
            @Override
            public void show() {
                System.out.println("动物1");
            }
        };
    }

}


