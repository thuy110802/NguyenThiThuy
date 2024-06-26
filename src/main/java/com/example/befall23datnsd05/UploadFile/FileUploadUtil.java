package com.example.befall23datnsd05.UploadFile;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class FileUploadUtil {
    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile){
        Path uploadPath = Paths.get(uploadDir);

        try {
            InputStream inputStream = multipartFile.getInputStream();
            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException ex){
            System.out.println(ex);
//            throw new IOException("Không lưu được file " + fileName, ex);
        }
    }

    public static void cleanDir(String dir){
        Path dirPath = Paths.get(dir);

        try{
           Files.list(dirPath).forEach(file->{
               if(!Files.isDirectory(file)){
                   try{
                       Files.delete(file);
                   }catch (IOException ex){
                       System.out.println("không thể xóa tệp: " + file);
                   }
               }
           });
        }catch (IOException ex){
            System.out.println("không thể liệt kê thư mục: " + dirPath);
        }
    }
}
