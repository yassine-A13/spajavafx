package spa;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class RendezVousDAO {
	public static Path path = Paths.get("C:\\Users\\samsu\\OneDrive\\Bureau\\spaManagement-javFX(2)\\spaManagement-javFX\\src\\spa\\RendezVous.txt");

    public static void create(RendezVous r) throws Exception {
        Files.write(
            path, 
            (r.toLine() + "\n").getBytes(),
            StandardOpenOption.CREATE,
            StandardOpenOption.APPEND
        );
    }
    
    public static List<RendezVous> getAll() throws Exception {
    	if(!Files.exists(path)) return new ArrayList<>();
    	
    	List<String> lines = Files.readAllLines(path);
    	List<RendezVous> r = new ArrayList<>();
    	
    	for(String line : lines) {
            r.add(RendezVous.fromLine(line));
            
            
    	}
    	return r;
    }
    

    
    public static void updatebyid(int id, RendezVous updateRendezVous)throws Exception{
    	if(!Files.exists(path)) return;
    	
    	List<String> lines =Files.readAllLines(path);
    	
    	for(int i=0 ; i<lines.size();i++) {
    		RendezVous r = RendezVous.fromLine(lines.get(i));
    		if(r.id==id) {
    			lines.set(i, updateRendezVous.toLine());
    		}
    	}
    	Files.write(path,lines);	
    }
    


    
    public static void deletebyid(int id)throws Exception{
    	if(!Files.exists(path)) return;
    	
    	List<String> lines = Files.readAllLines(path);
    	
    	lines.removeIf(line -> {
    		RendezVous r = RendezVous.fromLine(line);
            return r.id==id;
        });
    	
    	Files.write(path, lines);
    	
    }
}