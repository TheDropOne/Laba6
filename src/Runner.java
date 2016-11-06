import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuryakov on 29-Oct-16.
 */
public class Runner {

    private static String directoryPath;
    private static String directoryName;
    private static final String FILENAME = "root.txt";
    private static final String POEM_PATH = "poem.txt";
    private static final String DIR_PATH = "files.txt";

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(FILENAME)));
            List<String> poemStrings = readListOfStrings(POEM_PATH);
            List<String> dirStrings = readListOfStrings(DIR_PATH);
            directoryPath = br.readLine();
            directoryName = br.readLine();

            makeFile(directoryPath, "111.txt");
            System.out.println((new File(directoryPath)).lastModified());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> readListOfStrings(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        List<String> listOfStrings = new ArrayList<>();
        String tempString = br.readLine();
        while (tempString != null) {
            listOfStrings.add(tempString);
            tempString = br.readLine();
        }
        br.close();
        return listOfStrings;
    }

    private static void makeDir(String fullpath) {
        File file = new File(fullpath);
        file.mkdir();
    }

    private static void makeFile(String dirpath, String filename) throws IOException {
        File file = new File(dirpath, filename);
    }
}
