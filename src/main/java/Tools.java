import com.opencsv.CSVReader;
import model.Grant;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class Tools {
    public static ArrayList<Grant> getGrantsFromCsv(String path) {
        ArrayList<Grant> result = new ArrayList<>();
        try (FileReader fileReader = new FileReader(path)) {
            CSVReader reader = new CSVReader(fileReader);
            String[] records;
            reader.readNext();

            while ((records = reader.readNext()) != null) {
                String companyName = records[0];
                if (companyName.isEmpty()) {
                    continue;
                }

                String streetName = records[1];

                String grantStr = records[2];
                grantStr = grantStr.substring(1).replace(",","");
                BigDecimal grantSize = BigDecimal.valueOf(Double.parseDouble(grantStr));

                Year fiscalYear = Year.parse(records[3]);
                String businessType = records[4];
                int workPlaces = 0;
                if (!records[5].isEmpty()) {
                    workPlaces = Integer.parseInt(records[5]);
                }
                Grant grant = new Grant(companyName, streetName, grantSize,
                        fiscalYear, businessType, workPlaces);
                result.add(grant);
            }
        }

        catch (IOException e) {
            throw new RuntimeException();
        }

        return result;
    }
}
