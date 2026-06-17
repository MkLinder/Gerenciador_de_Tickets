package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatadorDataHora {

    private static final DateTimeFormatter FORMATADOR =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static String formatar(LocalDateTime dataHora) {

        if (dataHora == null) {
            return "-";
        }

        return dataHora.format(FORMATADOR);
    }
}