import java.util.*;
import edu.duke.*;
import java.io.File;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFiles {
    HashMap<String, ArrayList<String>> map;
    public WordsInFiles(){
        map= new HashMap<String, ArrayList<String>>();
    }
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f.toString());
        String fileName = f.getName();
        for(String w : fr.words()){
            w = w.toLowerCase();
            if (map.containsKey(w)){
                ArrayList<String> list = map.get(w);
                if(!list.contains(fileName))
                list.add(fileName);
                map.put(w,list);
            }
            else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(fileName);
                map.put(w,list);
            }
    }
    }
    private void buildWordFileMap(){
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    private int maxNumber(){
        int max=0;
        for(ArrayList<String> list: map.values()){
            if(max<list.size()){
                max=list.size();
            }
        }
        return max;
    }
    private ArrayList<String> wordsInNumFiles(int number)
    {
        ArrayList<String> words = new ArrayList<String>();
        for(String w: map.keySet()){
            if(map.get(w).size()==number){
                words.add(w);
            }
        }
        return words;
    }
    private void printFilesIn(String word){
        ArrayList<String> files = map.get(word);
        for(String file: files){
            System.out.println(file);
        }
    }
    public void tester()
    {
        buildWordFileMap();
        System.out.println(maxNumber());
        System.out.println(wordsInNumFiles(5).size());
        printFilesIn("red");
    }
}
