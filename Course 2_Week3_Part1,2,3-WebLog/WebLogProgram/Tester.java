
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la= new LogAnalyzer();
        la.readFile("weblog2_log");
        la.printAll();
        // complete method
    }
    public void testUniqueIP(){
        LogAnalyzer la= new LogAnalyzer();
        la.readFile("weblog2_log");
        int count = la.countUniqueIPs();
        System.out.println("There are "+count+" IPs");
    }
    public void testStatusCode(){
        LogAnalyzer la= new LogAnalyzer();
        la.readFile("weblog2_log");
        la.printAllHigherThanNum(400);
    }
    public void testUniqueIPVisits(){
        LogAnalyzer la= new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList<String> UniqueIPVisits = la.uniqueIPVisitsOnDay("Sep 27");
        System.out.println(UniqueIPVisits.size());
        for(String s: UniqueIPVisits){
            System.out.println(s);
        }
    }
    public void countUniqueIPInRange(){
        LogAnalyzer la= new LogAnalyzer();
        la.readFile("weblog2_log");
        int stat= la.countUniqueIPsInRange(200,299);
        System.out.println(stat);
    }
    
    public void countVisitsIP(){
        LogAnalyzer la= new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> count = la.countVisitsPerIP();
        System.out.println(count);
    }
    
    public void mostNumVisitsByIP(){
        LogAnalyzer la= new LogAnalyzer();
        la.readFile("weblog2_log");
        int count = la.mostNumberVisitsByIP(la.countVisitsPerIP());
        System.out.println(count);
    }
    
    public void iPMostVisits(){
        LogAnalyzer la= new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList<String> ips = la.iPsMostVisits(la.countVisitsPerIP());
        System.out.println(ips);
    }
    
    public void iPForDays(){
        LogAnalyzer la= new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> ips = la.iPsForDays();
        System.out.println(ips);
    }
    
    public void dayWithMosIPVisit(){
        LogAnalyzer la= new LogAnalyzer();
        la.readFile("weblog2_log");
        String date = la.dayWithMostIPVisits(la.iPsForDays());
        System.out.println(date);
    }
    public void iPWithMosVisitOnDay(){
        LogAnalyzer la= new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList ips = la.iPsWithMostVisitsOnDay(la.iPsForDays(), "Sep 29");
        System.out.println(ips);
    }
}
