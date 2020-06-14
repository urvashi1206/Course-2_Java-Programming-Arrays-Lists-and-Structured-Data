
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr= new FileResource(filename);
         for(String lines : fr.lines()){
             records.add(WebLogParser.parseEntry(lines));
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
            }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIps = new ArrayList<String>();
         for(LogEntry le : records){
             String ipAddr = le.getIpAddress();
             if(!uniqueIps.contains(ipAddr)){
                 uniqueIps.add(ipAddr);
             }
         }
         return uniqueIps.size();
     }
     
     public void printAllHigherThanNum(int num){
         for(LogEntry le : records){
             int statCode = le.getStatusCode();
             if(statCode>num){
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> IPVisits = new ArrayList<String>();
         for(LogEntry le : records){
             String date = le.getAccessTime().toString();
             
             if(date.contains(someday))
             {
                if(!IPVisits.contains(le.getIpAddress()))
                {
                    IPVisits.add(le.getIpAddress());
                }
             }  
         }
         return IPVisits;
     }
     public int countUniqueIPsInRange(int low, int high){
         ArrayList<String> stats = new ArrayList<String>();
         for(LogEntry le : records){
             int stat= le.getStatusCode();
             if(stat>=low && stat<=high)
             {
                 if(!stats.contains(le.getIpAddress()))
                 {
                     stats.add(le.getIpAddress());
                 }
             }
         }
         return stats.size();
     }
     public HashMap<String,Integer> countVisitsPerIP(){
         HashMap<String,Integer> count = new HashMap<String,Integer>();
         for(LogEntry le : records){
             String ip = le.getIpAddress();
             if(!count.containsKey(ip)){
                 count.put(ip,1);
             }
             else 
             {
                 count.put(ip,count.get(ip)+1);
             }
         }
         return count;
     }
     public int mostNumberVisitsByIP(HashMap<String,Integer> count){
         int max=0;
         for(String w: count.keySet()){
             if(max<count.get(w)){
                 max=count.get(w);
             }
         }
         return max;
     }
     public ArrayList iPsMostVisits(HashMap<String, Integer> count)
     {
         ArrayList<String> ips= new ArrayList<String>();
         int max = mostNumberVisitsByIP(count);
         for(String w: count.keySet()){
             if(max==count.get(w)){
                 ips.add(w);
             }
         }
         return ips;
     }
     public HashMap<String, ArrayList<String>> iPsForDays(){
         HashMap<String, ArrayList<String>> dates = new HashMap<String, ArrayList<String>>();
         for(LogEntry le : records){
             String date = le.getAccessTime().toString().substring(4,10);
             String ip = le.getIpAddress();
             if(!dates.containsKey(date))
             {
                 ArrayList<String> ips = new ArrayList<String>();
                 ips.add(ip);
                 dates.put(date,ips);
             }
             else{
                 ArrayList<String> ips= dates.get(date);
                 ips.add(ip);
                 dates.put(date,ips);
             }
         }
         return dates;
     }
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dates)
     {
         dates = iPsForDays();
         int max=0;
         String date = "";
         for(String w: dates.keySet()){
             if(max<dates.get(w).size()){
                 max=dates.get(w).size();
                 date = w;
             }
         }
         return date;
     }
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dates, String date)
     {
         ArrayList<String> ips = new ArrayList<String>();
         ArrayList<String> iplist = new ArrayList<String>();
         for(String w: dates.keySet()){
             if(w.equals(date)){
                 ips=dates.get(w);
             }
         }
         int count=0;
         for(String s: ips){
            if(!iplist.contains(s)){
                count++;
                if(count>1){
                    iplist.add(s);
                    count=0;
                }
            }
         }
         return iplist;
     }
}
