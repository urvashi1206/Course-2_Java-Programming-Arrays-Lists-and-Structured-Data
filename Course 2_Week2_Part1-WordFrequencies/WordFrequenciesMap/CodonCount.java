import edu.duke.*;
import java.util.*;
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CodonCount {
    HashMap<String,Integer> map;
    public CodonCount(){
        map = new HashMap<String,Integer>();
    }
    private void buildCodonMap(int start, String dna){
        System.out.println("************ For"+start+" ************");
        System.out.println(dna);
        dna = dna.substring(start);
        int length = dna.length();
        int leftout = length%3;
        dna = dna.substring(0,length-leftout);
        for(int k=0;k<dna.length();k+=3)
        {
            String w = dna.substring(k,k+3);
            System.out.println(w);
            w = w.toLowerCase();
            if (!map.containsKey(w)){
                map.put(w,1);
            }
            else {
                map.put(w,map.get(w)+1);
            }
        }
    }
    private String getMostCommonCodon(){
        int max=0;
        String maxKey="";
        for(String w: map.keySet()){
            if(max<map.get(w)){
                max=map.get(w);
                maxKey=w;
            }
            System.out.println("\n"+map.get(w)+"\t"+w);
        }
        return maxKey;
    }
    private void printCodonCounts(int start, int end){
        for(String w: map.keySet()){
            if(map.get(w)>=start && map.get(w)<=end)
            {
                System.out.println("\n"+map.get(w)+"\t"+w);
            }
        }
    }
    public void test(){
        FileResource fr = new FileResource();
        buildCodonMap(2,fr.asString());
        String maxKey=getMostCommonCodon();
        System.out.println("\nMaximum occured word: "+maxKey);
        //printCodonCounts(1, 5);
        map.clear();
        // buildCodonMap(0,"CGTTCAAGTTCAA");
        // maxKey=getMostCommonCodon();
        // System.out.println("\nMaximum occured word: "+maxKey);
        // map.clear();
        // buildCodonMap(1,fr.asString());
        // maxKey=getMostCommonCodon();
        // System.out.println("\nMaximum occured word: "+maxKey);
        // map.clear();
        // buildCodonMap(2,fr.asString());
        // maxKey=getMostCommonCodon();
        // System.out.println("\nMaximum occured word: "+maxKey);
        // map.clear();
    }
}


























