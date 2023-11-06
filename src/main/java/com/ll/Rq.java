package com.ll;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    @Getter
    private int id;
    @Getter
    private String cmd;
    private Map<String,String> paramMap;
    Rq(String cmd){
        String[] cmdBits = cmd.split("\\?",2);
        this.cmd = cmdBits[0];

        if(cmdBits.length == 1){
            return;
        }
        paramMap = new HashMap<>();
        String[] param = cmdBits[1].split("&");
        for(int i=0; i< param.length;i++){
            String[] paramValue = param[i].split("=");
            String key = paramValue[0];
            String value = paramValue[1];
            paramMap.put(key,value);
        }
    }
}
