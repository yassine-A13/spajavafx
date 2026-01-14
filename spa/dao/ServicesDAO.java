package spa;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ServicesDAO {

    private static final Path path = Paths.get("C:\\Users\\samsu\\OneDrive\\Bureau\\spaManagement-javFX(2)\\spaManagement-javFX\\src\\spa\\services.txt");

    // CREATE
    public static void ajouterService(Services s) throws Exception {
        Files.write(
                path,
                (s.toLine() + System.lineSeparator()).getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );
    }

    // READ ALL
    public static List<Services> getAll() throws Exception {
        if (!Files.exists(path)) {
            return new ArrayList<>();
        }

        List<String> lines = Files.readAllLines(path);
        List<Services> services = new ArrayList<>();

        for (String line : lines) {
            services.add(Services.fromLine(line));
        }
        return services;
    }

    // DELETE
    public static void supprimerService(String nom) throws Exception {
        if (!Files.exists(path)) return;

        List<String> lines = Files.readAllLines(path);
        lines.removeIf(line ->
                spa.Services.fromLine(line).getNom().equalsIgnoreCase(nom)
        );
        Files.write(path, lines);
    }
}
