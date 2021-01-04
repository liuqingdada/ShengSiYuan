package com.shengsiyuan.kotlin2;

public class GenericTest {

    interface Animal<T> {
        T getName();
    }

    public void printAnimal(Animal<String> animal) {
//        Animal<Object> ao = animal;
        Animal<?> a = animal;
    }
}
