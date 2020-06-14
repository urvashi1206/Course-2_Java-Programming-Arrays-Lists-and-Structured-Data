import java.util.*;
import edu.duke.*;
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
    private ArrayList<String> words;
    private ArrayList<Integer> freqs;
    
    public CharactersInPlay() {
        words = new ArrayList<String>();
        freqs = new ArrayList<Integer>();
    }
    private void update(String person)
    {
        person=person.toLowerCase();
        int index = words.indexOf(person);
        if(index==-1)
        {
            words.add(person);
            freqs.add(1);
        }
        else {
            int freq = freqs.get(index);
            freqs.set(index,freq+1);
        }
    }
    private void  findAllCharacters()
    {
        FileResource resource = new FileResource();
        for(String s : resource.lines()){
            for(int k=0;k<s.length();k++)
            {
                if(s.charAt(k)=='.')
                {
                    String name = s.substring(0,k);
                    update(name);
                }
            }
        }
        int maxIndex = findMax();
        System.out.println("max word/freq: "+words.get(maxIndex)+" "+freqs.get(maxIndex));
        
    }
    private int findMax(){
        int max = freqs.get(0);
        int maxIndex = 0;
        for(int k=0; k < freqs.size(); k++){
            if (freqs.get(k) > max){
                max = freqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }
    public void tester()
    {
        findAllCharacters();
        charactersWithNumParts(10, 15);
    }
    private void charactersWithNumParts(int num1, int num2){
        for (int i = 0; i < words.size(); i++) {
            if (freqs.get(i) >= num1 && freqs.get(i) <= num2) {
                System.out.println(words.get(i) + "\t" + freqs.get(i));
            }
        }
    }
}
