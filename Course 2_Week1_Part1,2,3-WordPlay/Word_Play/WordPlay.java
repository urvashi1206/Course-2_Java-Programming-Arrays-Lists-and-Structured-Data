import edu.duke.*;
import java.lang.Character;
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch)
    {
        if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u' || ch=='A' || ch=='E' || ch=='I' || ch=='O' || ch=='U')
        {
            return true;
        }
        return false;
    }
    public void testVowel()
    {
        boolean vowel = isVowel('F');
        System.out.println(vowel);
    }
    public String replaceVowels(String phrase, char ch)
    {
        StringBuilder sr = new StringBuilder(phrase);
        for(int i=0; i<sr.length();i++)
        {
            if(phrase.charAt(i)=='a'||phrase.charAt(i)=='e'||phrase.charAt(i)=='i'||phrase.charAt(i)=='o'||phrase.charAt(i)=='u'||phrase.charAt(i)=='A'||phrase.charAt(i)=='E'||phrase.charAt(i)=='I'||phrase.charAt(i)=='O'||phrase.charAt(i)=='U')
            {
                int index=i;
                sr.setCharAt(i,ch);
            }
        }
        return sr.toString();
    }
    public void testReplaceVowels()
    {
        String str= replaceVowels("Hello World", '*');
        System.out.println(str);
    }
     public String emphasis(String phrase, char ch) {
        
        StringBuilder sb = new StringBuilder();
        char x = Character.toLowerCase(ch);
        for(int i =0; i < phrase.length(); i++ ) {
           char b = phrase.charAt(i) ;
           char c = Character.toLowerCase(b);
           if(i%2==0 && x == c) {
               sb.insert(i, '*');
           } else if(i%2 == 1 && x == c) {
               sb.insert(i, '+');
           } else {
               sb.insert(i, b);
           }   
        }
        System.out.println(sb);
        return sb.toString();
        
    }

    public void check() {
        // TODO code application logic he
        emphasis("Mary Bella Abracadabra", 'a');
    }

}
