/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.controller;

import spring.model.PersonObject;

/**
 * Testing stuff in Main
 *
 * @author Stefan
 */
public class Main {
    
    String folder = "D:\\Users\\Stefan\\Google Drive\\School\\Projects\\Proj\\src\\main\\resources\\json\\";
    String file = "recording003_long.json";

    public static void main(String[] args) {

        JsonController JC;
        PersonController PC = null;
        StatController SC = null;
        // Maakt een JsonController om JSON files te verwerken
        try {
            JC = new JsonController("D:\\Users\\Stefan\\Google Drive\\School\\Projects\\Proj\\src\\main\\resources\\json\\recording003_long.json");
            PC = new PersonController();
            SC = new StatController();
            PC.convertJsonToPerson(JC.getList());
            SC.extractStatistics(PC.getList());
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }

        printPC(PC);
        printSC(SC);

    }
    
    private static void printPC(PersonController PC) {
        System.out.println(PC.toString());
        for (PersonObject p : PC.getList()) {
            System.out.println(p.getJsonList().toString());
        }
        System.out.println("Start time: \t" + PC.getStartTime().toString());
        System.out.println("End time: \t" + PC.getEndTime().toString());
    }
    
    private static void printSC(StatController SC) {
        System.out.println(SC.list.get(0).dataArray());
        System.out.println(SC.list.get(0).getStatId());
    }

    public String getPath() {
        return folder + file;
    }
}
