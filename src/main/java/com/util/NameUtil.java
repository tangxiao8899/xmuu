package com.util;

import java.util.UUID;

public class NameUtil {
    public static String getName(){
        int machineId = 1;
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }
       return machineId +String.format("%011d", hashCodeV);
    }

}
