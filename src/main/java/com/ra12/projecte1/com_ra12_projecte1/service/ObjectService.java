package com.ra12.projecte1.com_ra12_projecte1.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ra12.projecte1.com_ra12_projecte1.logs.ObjecteLogs;
import com.ra12.projecte1.com_ra12_projecte1.model.Objecte;
import com.ra12.projecte1.com_ra12_projecte1.repository.ObjecteRepository;

@Service
public class ObjectService {
    @Autowired
    ObjecteRepository objecteRepository;

    @Autowired
    ObjecteLogs objecteLogs;


    public int updatingObjecte(Long id, Objecte objecte){ 
        if(objecteRepository.findUserById(id) == null){
            objecteLogs.error("No s'ha pogut actualitzar l'usuari", "ObjectService", "updatingObjecte");
            return 0;
        }
        objecteLogs.info("S'ha actualitzat l'objecte correctament.", "ObjectService", "updatingObjecte");
        return objecteRepository.update(id, objecte);
    }

    public int deletingById(Long id){
        if (objecteRepository.findUserById(id) == null) {
            objecteLogs.error("No s'ha pogut esborrar l'objecte.", "ObjectService", "deletingById");
            return 0;
        }
        objecteLogs.info("S'ha pogut esborrar l'usuari correctament", "ObjectService", "deletingById");
        return objecteRepository.delete(id);
    }

    public int deletingAll(){
        List<Objecte> tots = objecteRepository.findAll();

        if (tots == null || tots.isEmpty()) {
            objecteLogs.error("No s'ha trobat cap objecte en la Base de Dades", "ObjectService", "deletingAll");
            return 0;
        }
        objecteLogs.info("S'ha esborrat tots els objectes correctament.", "ObjectService", "deletingAll");
        return objecteRepository.delete();
    }

    public String uploadingImage(Long id, MultipartFile image){
        Objecte objecte = objecteRepository.findUserById(id);
        int numReg = 0;

        if(objecte == null){
            objecteLogs.error("Error al carregar la imatge. No s'ha afegit cap.", "ObjecteService", "uploadingImage");
            return "";
        }

        String pathFinal = savingImage(image);

        if (pathFinal != null) {
            numReg = objecteRepository.uploadImage(id, pathFinal);
        }

        if (numReg == 0) {
            objecteLogs.error("Error a l'afegir la imatge a la Base de Dades", "ObjecteService", "uploadingImage");
            return null;
        }
        objecte.setImage_path(pathFinal);
        return pathFinal;
    }

    public String savingImage(MultipartFile image){

        String path = "/private/imageObjecte";

        try {
            if(image == null || image.isEmpty()){
                objecteLogs.error("Error al carregar la imatge. No s'ha afegit cap.", "ObjecteService", "savingImage");
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
            objecteLogs.info("S'ha guardat correctament la imatge en el path: " + filePath, "ObjecteService", "savingImage");
            return path;

        } catch (Exception e) {
            e.printStackTrace();
            objecteLogs.error("Error al carregar la imatge.", "ObjecteService", "savingImage");
            return null;
        }
    }
}
