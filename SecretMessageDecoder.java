import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SecretMessageDecoder {
    //fetches data from Docs URL, parses coordinate table, and print secret message

    public static void printSecretMessage(String url) {
        try {
            // fetch and parse HTML doscument from url
            Document doc = Jsoup.connect(url).get();

            //locate element
            Element table = doc.select("table").first();
            if (table == null) {
                System.out.println("Error");
                return;
            }

            //map to store characters at coordiantes
            Map<String, Character> gridMap = new HashMap<>();
            int maxX = 0;
            int maxY = 0;

            //iterate through table rows
            Elements rows = table.select("tr");
            for (int i = 1; i < rows.size(); i++) {
                Elements cols = rows.get(i).select("td");

                if(cols.size() >= 3) {
                    try {
                        //extract values
                        int x = Integer.parseInt(cols.get(0).text().trim());
                        char character = cols.get(1).text().trim().charAt(0);
                        int y = Integer.parseInt(cols.get(2).text().trim());

                        //update grid boundaries
                        maxX = Math.max(maxX, x);
                        maxY = Math.max(maxY, y);

                        //store character
                        gridMap.put(x + "," + y, character);
                    } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                        continue;
                    }
                }
            }
            //print top to bottom, so loop from maxY down to 0
            for (int y = maxY; y >= 0; y--) {
                StringBuilder rowString = new StringBuilder();
                for(int x = 0; x <= maxX; x++) {
                    String key = x + "," + y;

                    //append character or space
                    rowString.append(gridMap.getOrDefault(key, ' '));
                }
                System.out.println(rowString.toString());
            }
        } catch (IOException e) {
            System.err.println("Error" + e.getMessage());
        }
    }
    public static void main(String[] args) {
        String url = "https://docs.google.com/document/d/e/2PACX-1vSZ9d7OCd4QMsjJi2VFQmPYLebG2sGqI879_bSPugwOo_fgRcZLAFyfajPWU91UDiLg-RxRD41lVYRA/pub";
        printSecretMessage(url);
    }
}