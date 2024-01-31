package Source;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
    public String filePath;
    private String message;

    public ReadFile(String filePath){
        this.filePath = filePath;
        this.message = "";
    }

    public String read(){
        // File reader 
        try{
            File input = new File(this.filePath);
            Scanner reader = new Scanner(input);
            while(reader.hasNextLine()){
                String data = reader.nextLine();
                this.message += data+"\n";
            }
            reader.close();
        } catch(FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }
        return message;
    }
}
