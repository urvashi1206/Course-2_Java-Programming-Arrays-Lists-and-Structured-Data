import edu.duke.*;
import java.util.*;
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {
    public int[] countWordLengths(FileResource resource, int[] counts)
    {
        int flag=0;
        for(String words: resource.words())
        {
            StringBuilder sb = new StringBuilder(words);
            int len= 0;
            for(int i=0;i<sb.length();i++)
            {
                if (i==0 && !Character.isLetter(sb.charAt(i))){
                    sb.deleteCharAt(i);					
		}
		else if (i==sb.length()-1 && !Character.isLetter(sb.charAt(i))){
		    sb.deleteCharAt(i);
		}
                else
                {
                    len++;
                }
            }
            if(len<=counts.length && len!=0)
            {
                counts[len-1]+=1;
            }
        }
        return counts;
    }
    public int indexOfMax(int[] words)
    {
        int max=0;
        for(int i=0;i<words.length;i++)
        {
            if(max<words[i])
            {
                max=words[i];
            }
        }
        return max;
    }
    public void testCountWordLengths()
    {
        FileResource fr = new FileResource();
        int [] counts= new int[31];
        int [] words = countWordLengths(fr,counts);
        for(int k=0;k<words.length;k++)
        {
            System.out.print(words[k]+" words of length "+(k+1));
            System.out.println();
        }
        int max=indexOfMax(words);
        System.out.println(max);
    }
    
}
