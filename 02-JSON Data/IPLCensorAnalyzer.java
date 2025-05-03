import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

public class IPLCensorAnalyzer {

    public static void main(String[] args) throws Exception {
        String jsonInputFile = "ipl.json";
        String csvInputFile = "ipl.csv";
        String jsonOutputFile = "output.json";
        String csvOutputFile = "output.csv";

        List<Map<String, Object>> jsonData = readJsonData(jsonInputFile);
        jsonData = applyCensorshipToJson(jsonData);
        writeJsonData(jsonOutputFile, jsonData);

        List<String[]> csvData = readCsvData(csvInputFile);
        csvData = applyCensorshipToCsv(csvData);
        writeCsvData(csvOutputFile, csvData);
    }

    public static List<Map<String, Object>> readJsonData(String inputFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(inputFile), List.class);
    }

    public static List<Map<String, Object>> applyCensorshipToJson(List<Map<String, Object>> data) {
        for (Map<String, Object> match : data) {
            String team1 = (String) match.get("team1");
            String team2 = (String) match.get("team2");
            match.put("team1", maskTeamName(team1));
            match.put("team2", maskTeamName(team2));
            match.put("player_of_match", "REDACTED");
        }
        return data;
    }

    public static String maskTeamName(String teamName) {
        String[] parts = teamName.split(" ", 2);
        return parts.length == 2 ? parts[0] + " ***" : "***";
    }


    public static void writeJsonData(String outputFile, List<Map<String, Object>> data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFile), data);
    }

    public static List<String[]> readCsvData(String inputFile) throws IOException {
        List<String[]> rows = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(inputFile))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                rows.add(nextLine);
            }
        } catch (com.opencsv.exceptions.CsvValidationException e) {
            e.printStackTrace();
        }
        return rows;
    }


    public static List<String[]> applyCensorshipToCsv(List<String[]> data) {
        List<String[]> sanitizedData = new ArrayList<>();
        for (String[] row : data) {
            if (Arrays.asList(row).contains("match_id")) {
                sanitizedData.add(row);
            } else {
                row[1] = maskTeamName(row[1]);
                row[2] = maskTeamName(row[2]);
                row[6] = "REDACTED";
                sanitizedData.add(row);
            }
        }
        return sanitizedData;
    }

    public static void writeCsvData(String outputFile, List<String[]> data) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(outputFile))) {
            writer.writeAll(data);
        }
    }
}
