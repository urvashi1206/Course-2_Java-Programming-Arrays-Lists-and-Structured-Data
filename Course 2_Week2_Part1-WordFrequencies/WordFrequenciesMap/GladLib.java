import edu.duke.*;
import java.util.*;

public class GladLib {
    
    private HashMap<String,ArrayList<String>> map;
    private ArrayList<String> words;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";
    
    public GladLib(){
        map= new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        words = new ArrayList<String>();
    }
    
    public GladLib(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"country","noun","animal","adjective","name","color","timeframe","fruit","verb"};
        
        for(String s: labels){
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            map.put(s,list);
        }
     
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if(map.containsKey(label)) {
            
            return randomFrom(map.get(label));
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int sum=0;
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        if(!words.contains(w.substring(first+1,last))){
            if(!w.substring(first+1,last).equals("number"))
            words.add(w.substring(first+1,last));
        }
        
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    private int totalWordsInMap(){
        int num=0;
        for(String w: map.keySet()){
            num+=map.get(w).size();
            
        }
        return num;
    }
    private int totalWordsConsidered(){
        int total=0;
            
        for(String w: words){
            total += map.get(w).size();
        }
        return total;
    }
    public void makeStory(){
        System.out.println("\n");
        
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\nTOTAL WORDS IN MAP: "+totalWordsInMap());
        System.out.println("TOTAL WORDS IN considered : "+totalWordsConsidered());
       
    }
    

}
