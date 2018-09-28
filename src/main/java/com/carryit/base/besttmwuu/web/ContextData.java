package com.carryit.base.besttmwuu.web;

import com.carryit.base.besttmwuu.entity.Prize;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ContextData {

    private static List<Prize> nameList = Collections.synchronizedList(new LinkedList());
    private static Integer allSize = 0;
    private static List<String> shakedUid = Collections.synchronizedList(new LinkedList());

    public static void addShakedUid(String uid){
        shakedUid.add(uid);
    }

    public static boolean isShaked(String uid){
        return shakedUid.contains(uid);
    }

    public static void removeShakedUid(){
        shakedUid.clear();
    }

    public static Integer getAllSize() {
        return allSize;
    }

    public static void setAllSize(Integer allSize) {
        ContextData.allSize = allSize;
    }

    public static synchronized void add(Prize prize) {
        nameList.add(prize);
    }

    public static synchronized int getSize() {
        return nameList.size();
    }

    public static synchronized void removeAll() {
        nameList.clear();
    }

    public static synchronized List<Prize> getAll(){
        return nameList;
    }

    public static synchronized Prize getIdx(int idx) {
        if (nameList.size() > 0) {
            return nameList.get(idx);
        } else {
            return null;
        }
    }

}
