
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snoopy.game.Pages.gamepage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;
import java.io.IOException;
/**
 *
 * @author COMPUTER
 */

public class FileReaderProject {
    
    private File file;
    private FileReader reader;
    private char[] chars;
    Vector v;
    
    public FileReaderProject(String file_name){   
        
        v = new Vector();
        file = new File(file_name); 
        
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    

    
    
    public String [] getinput(){
        
        try{
            
            reader = new FileReader(file);

            chars = new char[(int) file.length()+1];
            reader.read(chars);
            chars[chars.length-1] = ' ';

        }catch(Exception e){         
            
            e.printStackTrace();
        
        }
        
        findRows();
         
        String [] array = new String [v.size()];
        
        for(int i = 0; i<v.size(); i++) {
            array[i] = ((String)v.elementAt(i)).trim();
        }
        
        return array;  
      
    }
  
    private void findRows(){
        
        char [] char_array = new char[150]; 
        int j = 0;
        
        for(int i = 0; i<chars.length; i++) {
        
            if(chars[i]  == '\n' || chars[i]  == '\u001a') {
                
                if(j > 0) {
                    
                    int length = 0;
                    
                    if (chars[i] == ' '|| chars[i]  == '\t') {
                        length = j;
                    }
                    else {
                        length = j-1;
                    }
                    
                    char [] temp = new char[length];
                    
                    for(int h=0; h<length; h++) {
                        temp[h] = char_array[h];
                    }
                    
                    String str = new String(temp);
                    
                    if (!str.isEmpty()) {
                        v.add(new String (temp));
                    }
                }
                
                char_array = new char[150];
                j=0;

            }
            else {

                char_array[j] = chars[i];
                j++;

            }
      
        }
        
    }
   
    
    public void setInput(String myFile,String text) {
        
        try {

            try (FileWriter fw = new FileWriter(myFile+ ".txt",true)) {
                file.delete();
                fw.write(text);
                fw.write(System.getProperty("line.separator"));
            }
        }
        catch(IOException ioe) {
            System.err.println(ioe);
        }
        
    }
    
}