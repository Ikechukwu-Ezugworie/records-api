package com.mkobo.assessment.recordsapi.util;

import com.mkobo.assessment.recordsapi.pojo.PatientPojo;
import com.opencsv.CSVWriter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Utility {

    private static final ModelMapper modelMapper = new ModelMapper();
    private static final Logger log = LoggerFactory.getLogger(Utility.class);
    public static final String CSV = ".csv";
    public static final String PROFILE_PREFIX = "Profile-";

    /**
     * <p>Maps a List from one type to another</p>
     *
     * @param sourceList the list to be mapped
     * @param targetType the type to be mapped to
     * @param <S>
     * @param <T>        return type
     * @return
     */
    public static <S, T> List<T> mapList(List<S> sourceList, Class<T> targetType) {
        return sourceList.stream()
                .map(source -> modelMapper.map(source, targetType))
                .collect(Collectors.toList());
    }

    /**
     * <p>Generate a patients CSV</p>
     * @param patient
     * @param filePath
     * @throws IOException
     */
    public static void generatePatientProfileCSV(PatientPojo patient, String filePath) throws IOException {
        log.info("Generating Patient's profile as a CSV");
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {

            String[] header = {"ID", "Name", "Age", "LastVisitDate"};
            writer.writeNext(header);

            String[] data = {String.valueOf(patient.getId()), patient.getName(), String.valueOf(patient.getAge()), patient.getLastVisitDate().toString()};
            writer.writeNext(data);

        } catch (IOException e) {
            log.debug("Error generating patient's profile as a CSV file: {}", e.getMessage());
            throw new IOException("Error generating patient's profile as a CSV file: ", e);
        }
    }
}
