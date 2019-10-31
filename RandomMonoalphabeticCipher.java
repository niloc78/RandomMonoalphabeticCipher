/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package part1;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Colin
 */
public class RandomMonoalphabeticCipher {
    public static char[] cipherGenerator(char[] plaintext) {
    Random rand = ThreadLocalRandom.current();
        for (int i = plaintext.length - 1; i >= 0; i--) {
            // shuffle method: swaps two indexes starting from the last index
            int randomindex = rand.nextInt(i + 1); // starts from index 94 , randoms 0 - 94 (included). When loop resets it becomes 0-94-1 and so on. This way it doesnt include the swapped char
            char x = plaintext[randomindex]; // store the char at randomindex so it can be used later
            plaintext[randomindex] = plaintext[i]; // replace the char at randomindex with char at last index i
            plaintext[i] = x; // replace char at last index i with the stored char at random index from before
        }   
       return plaintext;
    }
   
    public static void main(String[] args) {
        int k = 0;
        while (k == 0) {
        System.out.println("*** Monoalphabetic Cipher ***");
        char[] randomkey = new char[95];
        char[] plaintext = new char[95];
        for (int j = 32; j < 127; j++) {
            plaintext[j - 32] = (char) j;
        }
        //System.out.println(plaintext);
        randomkey = RandomMonoalphabeticCipher.cipherGenerator(plaintext);
        
        int s = 0;
        while (s == 0) {
        System.out.print("Monoalphabetic Cipher Key: "); // put while statement here so randomkey stays the same
        System.out.println(randomkey);
        System.out.println("1) Generate a New Key");
        System.out.println("2) Enter text to Encrypt");
        System.out.println("3) Enter text to Decrypt");
        System.out.println("q) Quit");
        Scanner input1 = new Scanner(System.in);
        System.out.print("Enter: ");
        String selection = input1.nextLine();       
        if (selection.equals("1")) { // eresets loop back to k so new cipher is generated
            k = 0;
            s = 1;
        } else if (selection.equals("2")) {
          //String randomkeystring = Arrays.toString(randomkey);
          //System.out.println(randomkeystring);
          Scanner input2 = new Scanner(System.in);
          System.out.print("Text to Encrypt: ");
          String texttoencrypt = input2.nextLine();
          char[] encryptedtext = new char[texttoencrypt.length()];
           
          for (int e = 0; e < texttoencrypt.length(); e++) {
              int a = texttoencrypt.charAt(e) - 32;
               //texttoencrypt = texttoencrypt.replace(texttoencrypt.charAt(e), randomkey[a]);// duplicates sometimes for some reason need to split into substring
               encryptedtext[e] = randomkey[a];

          }
          System.out.print("ciphertext: ");
          System.out.println(encryptedtext);
          
        } else if (selection.equals("3")) {
            Scanner input3 = new Scanner(System.in);
            String randomkeystring = new String(randomkey);
            System.out.print("Text to Decrypt: ");
            String texttodecrypt = input3.nextLine(); // how to reverse? : go back to randomkey, find each char of texttodecrypt in randomkey, index of the matched char in randomkey + 32= plaintext char
            char[] decryptedtext = new char[texttodecrypt.length()];
            for (int d = 0; d < texttodecrypt.length(); d++) {
               int b = randomkeystring.indexOf(texttodecrypt.charAt(d));
                decryptedtext[d] = (char) (b + 32);

               //texttodecrypt = texttodecrypt.substring(d, d).replace(texttodecrypt.charAt(d), (char) (b + 32));
               
            }
            System.out.print("plaintext: ");
            //System.out.print(randomkeystring);
            System.out.println(decryptedtext);
        } else if (selection.equalsIgnoreCase("q")) {
            k = 1;
            s = 1;
            System.out.println("GOODBYE");
        } else {
            System.out.println("ERROR: INVALID SELECTION");
        }
        
        } 
        
        }
    }
}
