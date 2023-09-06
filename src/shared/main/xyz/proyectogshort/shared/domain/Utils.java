package xyz.proyectogshort.shared.domain;

import org.apache.commons.validator.routines.UrlValidator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Utils {
    public static String dateToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static boolean validateUrl(String url) {
        // Try to prevent shell injection
        var excludedChars = List.of("<", ">", "\\", ";", "|", "$");

        var urlContainsExcluded = excludedChars.stream().anyMatch(url::contains);
        if (urlContainsExcluded) {
            return false;
        }

        return new UrlValidator().isValid(url);
    }
}
