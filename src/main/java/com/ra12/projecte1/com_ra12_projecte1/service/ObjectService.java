package com.ra12.projecte1.com_ra12_projecte1.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ra12.projecte1.com_ra12_projecte1.model.Objecte;
import com.ra12.projecte1.com_ra12_projecte1.repository.ObjecteRepository;

@Service
public class ObjectService {
    @Autowired
    ObjecteRepository objecteRepository;




    public int updatingObjecte(Long id, Objecte objecte){ 
        if(objecteRepository.findUserById(id) == null){
            return 0;
        }
        return objecteRepository.update(id, objecte);
    }

    public int deletingById(Long id){
        if (objecteRepository.findUserById(id) == null) {
            return 0;
        }
        return objecteRepository.delete(id);
    }

    public int deletingAll(){
        return objecteRepository.delete();
    }

    public String uploadingImage(Long id, MultipartFile image){
        Objecte objecte = objecteRepository.findUserById(id);
        int numReg = 0;

        if(objecte == null){
            return "";
        }

        String pathFinal = savingImage(image);

        if (pathFinal != null) {
            numReg = objecteRepository.uploadImage(id, pathFinal);
        }

        if (numReg == 0) {
            return null;
        }
        objecte.setImage_path(pathFinal);
        return pathFinal;
    }

    public String savingImage(MultipartFile image){

        String path = "/private/imageObjecte";

        try {
            if(image == null || image.isEmpty()){
                return null;
            }

            String originalFile = image.getOriginalFilename();

            Path fileDir = Paths.get(path);

            if(!Files.exists(fileDir)){
                Files.createDirectories(fileDir);
            }

            String baseNom = originalFile.substring(0, originalFile.lastIndexOf("."));
            String extensio = originalFile.substring(originalFile.lastIndexOf("."));

            String newFile = "image_" + baseNom + System.currentTimeMillis() + extensio;

            Path filePath = fileDir.resolve(newFile);
            Files.copy(image.getInputStream(), filePath,StandardCopyOption.REPLACE_EXISTING);

            return path;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
