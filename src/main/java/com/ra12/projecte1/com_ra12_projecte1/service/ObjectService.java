package com.ra12.projecte1.com_ra12_projecte1.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ra12.projecte1.com_ra12_projecte1.dto.ObjectRequest;
import com.ra12.projecte1.com_ra12_projecte1.dto.ObjectResponse;
import com.ra12.projecte1.com_ra12_projecte1.logs.ObjecteLogs;
import com.ra12.projecte1.com_ra12_projecte1.repository.ObjecteRepository;

@Service
public class ObjectService {
    @Autowired
    ObjecteRepository objecteRepository;

    @Autowired
    ObjecteLogs objecteLogs;

    public List<ObjectResponse> getAllObjectes() {
        objecteLogs.info("S'han consultat tots els Objectes", "ObjectService", "getAllObjectes");
        return objecteRepository.findAll();
    }

    public ObjectResponse getObjecteByID(Long id) {
        if (objecteRepository.findObjecteById(id) == null) {
            objecteLogs.error("No s'ha pogut trobar l'objecte", "ObjectService", "getObjectByID");
            return null;
        }
        objecteLogs.info("S'ha consultat l'objecte correctament.", "ObjectService", "getObjectByID");
        return objecteRepository.findObjecteById(id);
    }

    public int createObjecte(ObjectRequest objecte) {
        int isSaved = objecteRepository.save(objecte);
        if (isSaved == 0) {
            objecteLogs.error("No s'ha guardat l'objecte" + objecte.getTitulo(), "ObjectService", "createObjecte");
        } else {
            objecteLogs.info("S'ha guardat l'objecte " + objecte.getTitulo() + " correctament.", "ObjectService",
                    "createObjecte");
        }
        return isSaved;
    }

    public int createObjectesBatch(MultipartFile csvFile) {
        objecteLogs.info("Inserint Objectes en bloc", "ObjectService", "createObjectesBatch");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(csvFile.getInputStream()))) {
            String linia = br.readLine();
            int numObjectesCreats = 0;

            while ((linia = br.readLine()) != null) {

                // Separar per comes (CSV simple)
                String[] camps = linia.split(",");

                ObjectRequest new_objecte = new ObjectRequest(camps[0], camps[1], camps[2], camps[3], camps[4],
                        Boolean.parseBoolean(camps[5]));
                if (createObjecte(new_objecte) == 0) {
                    continue;
                }
                numObjectesCreats++;

            }

            objecteLogs.info("S'han guardat " + numObjectesCreats + " objectes", "ObjectService",
                    "createObjectesBatch");

            Path pathDirectori = Paths.get("com_ra12_projecte1/private/csv_processed");
            Path pathFitxer = Paths.get("com_ra12_projecte1/private/csv_processed/"
                    + System.currentTimeMillis() + csvFile.getOriginalFilename());

            Files.createDirectories(pathDirectori);
            Files.copy(csvFile.getInputStream(), pathFitxer);

            objecteLogs.info("S'ha guardat el csv a " + pathFitxer.toString(), "ObjectService",
                    "createObjectesBatch");

            return numObjectesCreats;
        } catch (Exception e) {
            return 0;
        }
    }

    public int updatingObjecte(Long id, ObjectRequest objecte) {
        if (objecteRepository.findObjecteById(id) == null) {
            objecteLogs.error("No s'ha pogut actualitzar l'usuari", "ObjectService", "updatingObjecte");
            return 0;
        }
        objecteLogs.info("S'ha actualitzat l'objecte correctament.", "ObjectService", "updatingObjecte");
        return objecteRepository.update(id, objecte);
    }

    public int deletingById(Long id) {
        if (objecteRepository.findObjecteById(id) == null) {
            objecteLogs.error("No s'ha pogut esborrar l'objecte.", "ObjectService", "deletingById");
            return 0;
        }
        objecteLogs.info("S'ha pogut esborrar l'usuari correctament", "ObjectService", "deletingById");
        return objecteRepository.delete(id);
    }

    public int deletingAll() {
        List<ObjectResponse> tots = objecteRepository.findAll();

        if (tots == null || tots.isEmpty()) {
            objecteLogs.error("No s'ha trobat cap objecte en la Base de Dades", "ObjectService", "deletingAll");
            return 0;
        }
        objecteLogs.info("S'ha esborrat tots els objectes correctament.", "ObjectService", "deletingAll");
        return objecteRepository.delete();
    }

    public String uploadingImage(Long id, MultipartFile image) {
        ObjectResponse objecte = objecteRepository.findObjecteById(id);
        int numReg = 0;

        if (objecte == null) {
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

    public String savingImage(MultipartFile image) {

        String path = "/private/imageObjecte/";

        try {
            if (image == null || image.isEmpty()) {
                objecteLogs.error("Error al carregar la imatge. No s'ha afegit cap.", "ObjecteService", "savingImage");
                return null;
            }

            String originalFile = image.getOriginalFilename();

            Path fileDir = Paths.get(path);

            if (!Files.exists(fileDir)) {
                Files.createDirectories(fileDir);
            }

            String baseNom = originalFile.substring(0, originalFile.lastIndexOf("."));
            String extensio = originalFile.substring(originalFile.lastIndexOf("."));

            String newFile = "image_" + baseNom + System.currentTimeMillis() + extensio;

            Path filePath = fileDir.resolve(newFile);
            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            objecteLogs.info("S'ha guardat correctament la imatge en el path: " + filePath, "ObjecteService",
                    "savingImage");
            return path;

        } catch (Exception e) {
            e.printStackTrace();
            objecteLogs.error("Error al carregar la imatge.", "ObjecteService", "savingImage");
            return null;
        }
    }
}
