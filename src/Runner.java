import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuryakov on 29-Oct-16.
 */
public class Runner {

    private static String directoryPath;
    private static String directoryName;
    private static String fullDirectoryPath;
    private static final String FILENAME = "root.txt";
    private static final String POEM_PATH = "poem.txt";
    private static final String DIR_PATH = "files.txt";
    private static List<String> poemStrings;
    private static List<String> dirStrings;

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(FILENAME)));
            directoryPath = br.readLine();
            directoryName = br.readLine();
            fullDirectoryPath = directoryPath + File.separator + directoryName;
            poemStrings = readListOfStrings(POEM_PATH);
            dirStrings = readListOfStrings(DIR_PATH);

            File directory = new File(fullDirectoryPath);
            if (!directory.exists()) {
                directory.mkdir();
            }

            for (String path : dirStrings) {
                String[] arr = path.split("/");
                String folderToCreateFilePath = directory.getPath();
                for (int i = 0; i < arr.length - 1; i++) {
                    folderToCreateFilePath += File.separator + arr[i];
                    File tempFolder = new File(folderToCreateFilePath);
                    if (!tempFolder.exists()) {
                        tempFolder.mkdir();
                    }
                }
                File file = new File(folderToCreateFilePath, arr[arr.length - 1]);
                if (file.getPath().contains(".")) {
                    file.createNewFile();
                    writeTask(file, arr.length - 1);
                } else {
                    file.mkdir();
                }
            }
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

    private static void writeTask(File file, int index) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(file.getAbsolutePath() + System.lineSeparator());
        fw.write(index + System.lineSeparator());
        fw.write(poemStrings.get(index - 1));
        fw.close();
    }
}
