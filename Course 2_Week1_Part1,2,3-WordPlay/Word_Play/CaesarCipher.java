import edu.duke.*;
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (    `your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher{
    public int[] countLetters(String s)
    {
        int [] counts = new int[26];
        String alphabet="abcdefghijklmnopqrstuvwxyz";
        s=s.toLowerCase();
        for(int i=0;i<s.length();i++)
        {
            int position=alphabet.indexOf(s.charAt(i));
            if(position!=-1)
            {
                counts[position]+=1;
            }
        }
        return counts;
    }
    public int maxIndex(int [] counts)
    {
        int max=0;
        int maxpos=0;
        for(int i=0;i<counts.length;i++)
        {
            if(max<counts[i])
            {
                max=counts[i];
                maxpos=i;
            }
        }
        System.out.println("max count"+max);
        System.out.println("max pos"+maxpos);
        return maxpos;
    }
    public String encrypt(String input, int key)
    {
        StringBuilder sr= new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftAlpha=alphabet.substring(key)+alphabet.substring(0,key);
        System.out.println(shiftAlpha);
        for(int i=0;i<sr.length();i++)
        {
            char a= sr.charAt(i);
            if(Character.isLowerCase(a))
            {
                char b = Character.toUpperCase(a);
                int idx=alphabet.indexOf(b);
                if(idx!=-1) {
                    char x=shiftAlpha.charAt(idx);
                    char c=Character.toLowerCase(x);
                    sr.setCharAt(i,c);
                }
            }
            int idx=alphabet.indexOf(a);
            if(idx!=-1)
            {
                char x=shiftAlpha.charAt(idx);            
                sr.setCharAt(i,x);
            }
        }
        return sr.toString();
    }
    public void testCaesar()
    {
        String encrypted = encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", 15);
        System.out.println("key is " + "\n" + encrypted);
        String decrypted1 = encrypt("Pi cddc qt xc iwt rdcutgtcrt gddb lxiw ndjg wpi dc udg p hjgegxht epgin. NTAA ADJS!",11);
        System.out.println("key is " + "\n" + decrypted1);
        String decrypted2 = decrypt("kgtwXbrao oexkxzxgxk",1);
        System.out.println("key is " + "\n" + decrypted2);
    }
    public String encryptTwoKeys(String input, int key1, int key2)
    {
        StringBuilder sr= new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftAlpha1 = alphabet.substring(key1)+alphabet.substring(0,key1);
        String shiftAlpha2 = alphabet.substring(key2)+alphabet.substring(0,key2);
        System.out.println(shiftAlpha1);
        System.out.println(shiftAlpha2);
        for(int i=0;i<sr.length();i++)
        {   
            if(i%2==0) {
                char a= sr.charAt(i);
                if(Character.isLowerCase(a))
                {
                    char b = Character.toUpperCase(a);
                    int idx=alphabet.indexOf(b);
                    if(idx!=-1) {
                        char x=shiftAlpha1.charAt(idx);
                        char c=Character.toLowerCase(x);
                        sr.setCharAt(i,c);
                    }
                }
                int idx=alphabet.indexOf(a);
                if(idx!=-1)
                {
                    char x=shiftAlpha1.charAt(idx);            
                    sr.setCharAt(i,x);
                }
            }
            else {
                char a= sr.charAt(i);
                if(Character.isLowerCase(a))
                {
                    char b = Character.toUpperCase(a);
                    int idx=alphabet.indexOf(b);
                    if(idx!=-1) {
                        char x=shiftAlpha2.charAt(idx);
                        char c=Character.toLowerCase(x);
                        sr.setCharAt(i,c);
                    }
                }
                int idx=alphabet.indexOf(a);
                if(idx!=-1)
                {
                    char x=shiftAlpha2.charAt(idx);            
                    sr.setCharAt(i,x);
                }
            }
        }
        return sr.toString();
    }
    public void testTwoKeys()
    {
        String encrypted = encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?",12, 2);
        System.out.println("key is " + "\n" + encrypted);
    }
    public String decrypt(String message,int key)
    {
        String decryption=encrypt(message,26-key);
        return decryption;
    }
    public void testdecrypt() {
        String message = encrypt("First Legion", 15);
        System.out.println(message);
        String message1 = decrypt(message,15);
        System.out.println(message1);
    }
    public String halfOfString(String message, int start)
    {
        String result = new String();
        for (int i = start; i < message.length(); i = i+2) {
            result = result + message.charAt(i);
        }
        return result;
    }
    public void testHalfOfString()
    {
        String half = halfOfString("Akag tjw Xibhr awoa aoee xakex znxag xwko",0);
        System.out.println("Half String for 0: "+half);
    }
    public int getKey(String s)
    {
        int [] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex-4;
        System.out.println("dkey:"+dkey);
        if (maxDex < 4) 
        {
            dkey=26-(4-maxDex);
        }
        System.out.println("dkey:"+dkey);
        return dkey;
    }
    public String decryptTwoKeys(String encrypted)
    {
        String firstHalf = halfOfString(encrypted,0);
        System.out.println(firstHalf);
        String secondHalf = halfOfString(encrypted,1);
        System.out.println(secondHalf);
        int dkey1=getKey(firstHalf);
        int dkey2=getKey(secondHalf);
        System.out.println("The keys are: "+dkey1+","+dkey2);
        
        String enc= encryptTwoKeys(encrypted, 26-dkey1, 26-dkey2);
        return enc;
    }
    public void testdecryptTwoKeys() {
        String encrypted = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        System.out.println(decryptTwoKeys(encrypted));
    }
}
