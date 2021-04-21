package com.example.homeworkhelperbeta;

public class MyProperties {
    private static MyProperties mInstance= null;

    public int checkBox;
    public int hour;
    public int minute;

    protected MyProperties(){}

    public static synchronized MyProperties getInstance() {
        if(null == mInstance){
            mInstance = new MyProperties();
        }
        return mInstance;
    }
}
