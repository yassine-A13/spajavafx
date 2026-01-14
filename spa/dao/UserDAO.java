package spa;

import java.nio.file.*;
import java.util.*;



public class UserDAO {
	public static Path path = Paths.get("C:\\Users\\samsu\\OneDrive\\Bureau\\spaManagement-javFX(2)\\spaManagement-javFX\\src\\spa\\user.txt");

    public static void create(spa.user u) throws Exception {
        Files.write(
            path, 
            (u.toLine() + "\n").getBytes(),
            StandardOpenOption.CREATE,
            StandardOpenOption.APPEND
        );
    }
    
    public static List<spa.user> getAll() throws Exception {
    	if(!Files.exists(path)) return new ArrayList<>();
    	
    	List<
    	String> lines = Files.readAllLines(path);
    	List<user> users = new ArrayList<>();
    	
    	for(String line : lines) {
            users.add(user.fromLine(line));
            
    	}
    	return users;
    }
    
    public static user getbycin(String cin)throws Exception {
    	if(!Files.isExecutable(path)) return null;
    	
    	List<String> lines = Files.readAllLines(path);
    	
    	for(String line : lines) {
    		user u = user.fromLine(line);
    		if(u.cin.equals(cin)) {
    			System.out.print(u.toLine());
    			return u;
    		}
    	}
    	System.out.print("user not found");
    	return null;
    }
    
    public static void updatebycin(String cin, user updateUser)throws Exception{
    	if(!Files.exists(path)) return;
    	
    	List<String> lines =Files.readAllLines(path);
    	
    	for(int i=0 ; i<lines.size();i++) {
    		user u = user.fromLine(lines.get(i));
    		if(u.cin.equals(cin)) {
    			lines.set(i, updateUser.toLine());
    		}
    	}
    	Files.write(path,lines);	
    }
    
    public static void deletebycin(String cin)throws Exception{
    	if(!Files.exists(path)) return;
    	
    	List<String> lines = Files.readAllLines(path);
    	
    	lines.removeIf(line -> {
            user u = user.fromLine(line);
            return u.cin.equals(cin);
        });
    	
    	Files.write(path, lines);
    	
    }
}
