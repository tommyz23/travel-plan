package com.example.utils.FileUtils;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHelper {
    private static String FILEPATH = "F:\\homework_photo\\";
    public static String upload(MultipartFile file, String name, String planId, String uploaderId){
        File folderFile = new File(FILEPATH + planId + "\\" + uploaderId);
        if (!folderFile.exists())
            folderFile.mkdirs();
        Path path = Paths.get(FILEPATH + planId + "\\" + uploaderId + "\\" + name);
        try {
            Files.write(path, file.getBytes());
            return path.toString();
        }catch (IOException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static byte[] read(String pathValue){
        Path path = Paths.get(pathValue);
        try {
            byte[] file = Files.readAllBytes(path);
            return file;
        }catch (IOException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static boolean del(String pathValue){
        Path path = Paths.get(pathValue);
        try {
            Files.deleteIfExists(path);
            return true;
        }catch (IOException e){
            return false;
        }
    }

    public static boolean delAll(String pathValue){
        File directory = new File(pathValue);
        try {
            FileUtils.deleteDirectory(directory);
            return true;
        }catch (IOException e){
            return false;
        }
    }

    public static String getPath(){
        return FILEPATH;
    }
}
