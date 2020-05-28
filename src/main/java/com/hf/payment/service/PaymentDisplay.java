package com.hf.payment.service;

import net.sf.json.JSONObject;
import java.util.*;
import java.util.concurrent.*;

public class PaymentDisplay implements Callable<String> {
    // 1.定义一个map接收输入值并累加
    Map<String, Double> paymentMap = new ConcurrentHashMap<String, Double>();
    // 2.实现Callable的call方法，console输入，返回future对象
    public String call() throws Exception {
        // 3.Scanner类new一个对象接收输入值
        Scanner sc = new Scanner(System.in);
        if(sc.hasNextLine()){
            String str = sc.nextLine();
            // 4.空格检验
            if(str.indexOf(" ") == -1){
                System.out.println("The currency need to be separeted from amounts with a blank space！");
            }else{
                String[] strs = str.split("\\s+");
                if(paymentMap.get(strs[0]) != null ){
                    Double d1 = paymentMap.get(strs[0]);
                    Double d2 = Double.valueOf(strs[1]);
                    paymentMap.put(strs[0],(d1+d2));
                }else if(str.equals("quit")){  // 5.quit退出
                    System.out.println("quit successful!");
                    return null;
                }else {
                    // 6.KEY值未存在，则新增k-v
                    paymentMap.put(strs[0],Double.valueOf(strs[1]));
                }
            }
        }
        try{
            // 7.抓空指针异常
            String futureBackStr =  JSONObject.fromObject(paymentMap).toString();
            return futureBackStr;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}