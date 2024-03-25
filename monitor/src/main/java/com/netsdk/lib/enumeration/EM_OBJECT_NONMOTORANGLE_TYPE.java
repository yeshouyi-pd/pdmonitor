package com.netsdk.lib.enumeration;


/** 
* @author 291189
* @description  非机动车的角度 
* @origin autoTool
* @date 2023/02/14 20:11:39
*/
public enum EM_OBJECT_NONMOTORANGLE_TYPE {
/**
未知
*/
EM_OBJECT_NONMOTORANGLE_TYPE_UNKNOWN(0,"未知"),
/**
正面
*/
EM_OBJECT_NONMOTORANGLE_TYPE_FRONT(1,"正面"),
/**
侧面
*/
EM_OBJECT_NONMOTORANGLE_TYPE_SIDE(2,"侧面"),
/**
后面
*/
EM_OBJECT_NONMOTORANGLE_TYPE_BEHIND(3,"后面");

private int value;

private String note;

public String getNote() {
        return note;
    }

public int getValue() {
        return value;
    }

EM_OBJECT_NONMOTORANGLE_TYPE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

public static String getNoteByValue(int givenValue) {
        for (EM_OBJECT_NONMOTORANGLE_TYPE enumType : EM_OBJECT_NONMOTORANGLE_TYPE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

public static int getValueByNote(String givenNote) {
        for (EM_OBJECT_NONMOTORANGLE_TYPE enumType : EM_OBJECT_NONMOTORANGLE_TYPE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }

public static EM_OBJECT_NONMOTORANGLE_TYPE getEnum(int value) {
        for (EM_OBJECT_NONMOTORANGLE_TYPE e : EM_OBJECT_NONMOTORANGLE_TYPE.values()) {
            if (e.getValue() == value)
                return e;
        }
        return EM_OBJECT_NONMOTORANGLE_TYPE.EM_OBJECT_NONMOTORANGLE_TYPE_UNKNOWN;
    }

}