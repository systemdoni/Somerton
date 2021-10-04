package com.somerton.utils

import javax.servlet.http.Part
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

class FileUtils{

    static String generateFileName(Part filePart){
        return '/uploads/'+
                System.currentTimeMillis()+'-'+
                Paths.get( filePart.getSubmittedFileName() ).getFileName().toString()
    }

    static File saveFile(Part filePart, String filename){
        File file =
                new File(System.getProperty("user.home")+File.separator+filename)

        if(!file.getParentFile().exists())
            file.mkdirs()

        Files.copy(filePart.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING)
        filePart.getInputStream().close()
        return file
    }
}
