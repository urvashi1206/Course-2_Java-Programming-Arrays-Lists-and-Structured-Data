import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    private int[] validKey;
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder str= new StringBuilder();
        for(int i=whichSlice;i<message.length();i+=totalSlices)
        {
            str.append(message.charAt(i));
        }
        return str.toString();
    }
    public void testString(){
        String str = sliceString("abcdefghijklm", 0, 3);
        System.out.println(str);
        str = sliceString("abcdefghijklm", 1, 3);
        System.out.println(str);
        str = sliceString("abcdefghijklm", 2, 3);
        System.out.println(str);
        str = sliceString("abcdefghijklm", 0, 4);
        System.out.println(str);
        str = sliceString("abcdefghijklm", 1, 4);
        System.out.println(str);
        str = sliceString("abcdefghijklm", 2, 4);
        System.out.println(str);
        str = sliceString("abcdefghijklm", 3, 4);
        System.out.println(str);
        str = sliceString("abcdefghijklm", 0, 5);
        System.out.println(str);
        str = sliceString("abcdefghijklm", 1, 5);
        System.out.println(str);
        str = sliceString("abcdefghijklm", 2, 5);
        System.out.println(str);
        str = sliceString("abcdefghijklm", 3, 5);
        System.out.println(str);
        str = sliceString("abcdefghijklm", 4, 5);
        System.out.println(str);
    }
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker();
        for(int i=0;i<klength;i++)
        {
            String slice = sliceString(encrypted, i, klength);
            int newKey = cc.getKey(slice);
            key[i] = newKey;
        }
        return key;
    }
    public void testKeyLength(){
        FileResource fr = new FileResource("messages/secretmessage2.txt");
        String encrypted = fr.asString();
        int [] key = tryKeyLength(encrypted, 38, 'e');
        for(int i =0;i<key.length;i++)
        {
            System.out.print(key[i]+"\t");
        }
    }
    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource fr = new FileResource();
        FileResource dict = new FileResource("dictionaries/German");
        String str = fr.asString();
        HashSet<String> dictionary = readDictionary(dict); 
        String decrypted = breakForLanguage(str, dictionary);
        for(int i=0;i<validKey.length;i++){
            System.out.print(validKey[i]+" ");
        }
        System.out.println("\nLength: "+validKey.length);
        System.out.println(decrypted);
        mostCommonCharIn(dictionary);
    }
    public HashSet<String> readDictionary(FileResource fr)
    {
        HashSet<String> dictionary = new HashSet<String>();
        for(String word : fr.words()){
            word=word.toLowerCase();
            if(!dictionary.contains(word)){
                dictionary.add(word);
            }
        }
        return dictionary;
    }
    public int countWords(String message, HashSet<String> dictionary){
        String [] splitWords = message.split("\\W+");
        int count=0;
        for(int i=0;i<splitWords.length;i++)
        {
            String word= splitWords[i].toLowerCase();
            if(dictionary.contains(word)){
                count++;
            }
        }
        return count;
    }
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int maxCount=0;
        String decrypted="";
        for(int i=1;i<=100;i++)
        {
            int[] keys=tryKeyLength(encrypted, i, mostCommonCharIn(dictionary));
            VigenereCipher vc= new VigenereCipher(keys);
            String decrypt = vc.decrypt(encrypted);
            int count = countWords(decrypt, dictionary);
            if(maxCount<count){
                maxCount=count;
                decrypted= decrypt;
                validKey=keys;
            }
        }
        System.out.println("MaxCount: "+maxCount);
        return decrypted;
    }
    public char mostCommonCharIn(HashSet<String> dictionary){
        int max=0;
        HashMap<Character, Integer> characters = new HashMap<Character, Integer>();
        char[] chars = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o',
                        'p','q','r','s','t','u','v','w','x','y','z'};
        for( int i=0; i<chars.length; i++){
            characters.put(chars[i],0);
        }
        for (String word : dictionary){
           for( char s : characters.keySet()){
               if (word.contains(Character.toString(s))){
                   characters.put(s, characters.get(s)+1);
                }
            }
        }
        for(char s : characters.keySet()){
            if(max<characters.get(s)){
                max=characters.get(s);
            }
        }
        for(char s : characters.keySet()){
            if (characters.get(s) == max){
                return s;
            }
        }
        return 'a';
    }
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>>languages){
        for(String s: languages.keySet()){
            HashSet language = languages.get(s);
            String decrypted= breakForLanguage(encrypted, language);
            int wordCount = countWords(decrypted, language);
            System.out.println("LANGUAGE CHOSEN = " + s);
            System.out.println("Decrypted message  =\n"+ decrypted);
            System.out.println("Words counted = "+ wordCount);
        }
    }
    public void breakVigenere2() {
        //WRITE YOUR CODE HERE
        FileResource fr = new FileResource();
        String str = fr.asString();
        HashMap<String,HashSet<String>> languages = new HashMap<String,HashSet<String>>();
        
        FileResource English = new FileResource("dictionaries/English");
        HashSet<String> dictionary = readDictionary(English);
        languages.put("English",dictionary);
        
        FileResource Danish = new FileResource("dictionaries/Danish");
        dictionary = readDictionary(Danish);
        languages.put("Danish",dictionary);
        
        FileResource Dutch = new FileResource("dictionaries/Dutch");
        dictionary = readDictionary(Dutch);
        languages.put("Dutch",dictionary);
        
        FileResource French = new FileResource("dictionaries/French");
        dictionary = readDictionary(French);
        languages.put("French",dictionary);
        
        FileResource German = new FileResource("dictionaries/German");
        dictionary = readDictionary(German);
        languages.put("German",dictionary);
        
        FileResource Italian = new FileResource("dictionaries/Italian");
        dictionary = readDictionary(Italian);
        languages.put("Italian",dictionary);
        
        FileResource Portugese = new FileResource("dictionaries/Portuguese");
        dictionary = readDictionary(Portugese);
        languages.put("Portuguese",dictionary);
        
        FileResource Spanish = new FileResource("dictionaries/Spanish");
        dictionary = readDictionary(Spanish);
        languages.put("Spanish",dictionary);
        
        breakForAllLangs(str, languages);
    }
}
