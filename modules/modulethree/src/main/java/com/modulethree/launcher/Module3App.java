package com.modulethree.launcher;


import client.Module1Client;
import com.moduleone.service.Module1Service;

public class Module3App {

    public static void main(String[] args) {
        var service = new Module1Service();
        var client = new Module1Client(service);

        var data = client.retrieveData();
        System.out.println(data);
    }
}
