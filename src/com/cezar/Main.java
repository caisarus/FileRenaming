package com.cezar;

import java.io.File;
import java.util.Scanner;

public class Main {
    static String oldExtension = "Not defined";
    static String newExtension = "Not defined";
    static String oldString = "Not defined";
    static String newString = "Not defined";
    static String currentDirectory = "Not defined";
    static String newFileName = "Not defined";
    static String oldFileName = "Not defined";
    static boolean replaceExtension = false;
    static boolean replaceString = false;

    public static void main(String[] args) {
        setVariables();
        setDirectory();
        if(replaceString || replaceExtension)
            replacing();
    }

    private static void replacing(){
        File path = new File(currentDirectory);
        File [] files = path.listFiles();

        for (int i = 0; i < files.length; i++){
            if (files[i].isFile()){ //this line weeds out other directories/folders
                File tempFile = new File(currentDirectory + files[i].toString());
                oldFileName = tempFile.getName();
                newFileName = oldFileName;
                if(replaceString) {
                    System.out.println("Replacing string: " + oldString + " with " + newString + " on file: " + oldFileName);
                    newFileName = oldFileName.replaceAll(oldString, newString);
                }
                if(replaceExtension && files[i].toString().endsWith(oldExtension)){
                    System.out.println("Replacing extension: " + oldExtension + " with " + newExtension + " on file: " + oldFileName);
                    newFileName = newFileName.replaceAll(oldExtension, newExtension);
                }
                files[i].renameTo(new File(currentDirectory + "\\" +  newFileName));
            }
        }
    }

    private static void setVariables(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want to replace string in name?");
        replaceString = scanner.nextBoolean();
        scanner.nextLine();
        if(replaceString) {
            System.out.println("What is the string that you want to replace?");
            oldString = scanner.nextLine();

            System.out.println("Replace " + oldString + " with: ");
            newString = scanner.nextLine();
        }

        System.out.println("Do you want to replace extension of files?");
        replaceExtension = scanner.nextBoolean();
        scanner.nextLine();
        if(replaceExtension) {
            System.out.println("What is the extension that you want replaced?");
            oldExtension = scanner.nextLine();

            System.out.println("Replace " + oldExtension + " with: ");
            newExtension = scanner.nextLine();
        }
    }

    private static void setDirectory(){
        try{
            currentDirectory = new java.io.File( "." ).getCanonicalPath();
            System.out.println("Working in directory: " + currentDirectory + "\n");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
