package com.cezar;

import java.io.File;
import static java.nio.file.StandardCopyOption.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String oldExtension = "JPG";
        String newExtension = "dat";
        String toReplace = "IMG_";
        String replacement = "";
        String current = "Not available";
        String newFileName = "Temporary unavailable";
        String oldFileName = "Temporary unavailable";
        boolean revert = false;
        boolean replaceExtension = true;

        try{
            current = new java.io.File( "." ).getCanonicalPath();
            System.out.println("Current directory: " + current + "\n");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }


        File path = new File(current);

        File [] files = path.listFiles();
        if(revert){
            for (int i = 0; i < files.length; i++){
                //System.out.println(files[i].toString().toUpperCase());
                if (files[i].isFile() && files[i].toString().toLowerCase().endsWith(newExtension)){ //this line weeds out other directories/folders
                    File tempFile = new File(current + files[i].toString());
                    oldFileName = tempFile.getName();
                    newFileName = oldFileName.replaceAll(newExtension ,oldExtension);
                    files[i].renameTo(new File(current + "\\" +  newFileName));
                    System.out.println(newFileName);
                }
            }
        }

       else for (int i = 0; i < files.length; i++){
            if (files[i].isFile() && files[i].toString().toUpperCase().endsWith(oldExtension)){ //this line weeds out other directories/folders
                File tempFile = new File(current + files[i].toString());
                oldFileName = tempFile.getName();
                newFileName = oldFileName.replaceAll(toReplace, replacement);
                if(replaceExtension){
                    newFileName = newFileName.replaceAll(oldExtension, newExtension);
                    files[i].renameTo(new File(current + "\\" +  newFileName));
                }
                System.out.println(newFileName);
            }
        }





    }
}
