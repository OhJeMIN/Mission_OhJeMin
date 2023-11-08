package com.ll.base;

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
        if(cmdBits[1].isBlank()){
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
    public int getParamAsInt(String key, int defaultValue){
        try{
            int id = Integer.parseInt(paramMap.get(key));
            return id;
        }catch (NullPointerException e){
            return defaultValue;
        }
    }
}
