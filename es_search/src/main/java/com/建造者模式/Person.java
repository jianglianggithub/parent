package com.建造者模式;

public class Person {
    private String name;
    private String age;
    private String yaowei;

    public Person(Builder builder){
        this.name=builder.getName();
        this.age=builder.getAge();
        this.yaowei=builder.getYaowei();
    }

    public static void main(String[] args) {
//        Person build = new Person.Builder().setAge().setName().setYaowei().build();

    }


   static class Builder {
        private String name;
        private String age;
        private String yaowei;
        public Builder(){}

        public String getName() {
            return name;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public String getAge() {
            return age;
        }

        public Builder setAge(String age) {
            this.age = age;
            return this;
        }

        public String getYaowei() {
            return yaowei;
        }

        public Builder setYaowei(String yaowei) {
            this.yaowei = yaowei;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}



