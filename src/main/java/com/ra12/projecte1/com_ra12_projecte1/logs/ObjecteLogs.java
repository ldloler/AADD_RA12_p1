package com.ra12.projecte1.com_ra12_projecte1.logs;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class ObjecteLogs {
    private final String logDirectory = "/private/logs";

    public ObjecteLogs(){
        Path fileDir = Paths.get(logDirectory);

        if(!Files.exists(fileDir)){
            try {
                Files.createDirectories(fileDir);
            } catch (Exception e) {
                System.err.println("Error al carregar el fitxer logs: " + e);
            }   
        }
    }

    private static String obtenirFitxerAvui(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fecha = LocalDateTime.now().format(formato);
        return fecha.concat(".log");
    }

    public void error(String frase, String classe, String modulo){
        String fitxer = obtenirFitxerAvui();
        Path currentFile = Paths.get(logDirectory + fitxer);

        try (BufferedWriter writer = Files.newBufferedWriter(
                currentFile,
                StandardCharsets.UTF_8, 
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)){
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd: HH:mm:ss");
            String fechaHora = '[' + LocalDateTime.now().format(formato) + ']';

            String linea = fechaHora + " ERROR - " + classe + " - " + modulo + " - " + frase + "\n";
            writer.write(linea);

        } catch (Exception e) {
            System.err.println("Error al log ".concat(fitxer).concat(" és corrupte."));
        }
    }

    public void info(String frase, String classe, String modulo){
        String fitxer = obtenirFitxerAvui();
        Path currentFile = Paths.get(logDirectory + fitxer);

        try (BufferedWriter writer = Files.newBufferedWriter(
                currentFile,
                StandardCharsets.UTF_8, 
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)){
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd: HH:mm:ss");
            String fechaHora = '[' + LocalDateTime.now().format(formato) + ']';

            String linea = fechaHora + " INFO - " + classe + " - " + modulo + " - " + frase + "\n";
            writer.write(linea);

        } catch (Exception e) {
            System.err.println("Error al log ".concat(fitxer).concat(" és corrupte."));
        }
    }
}
