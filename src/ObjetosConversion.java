public record ObjetosConversion(
        String result, // Campo crucial para verificar si la API fue exitosa ("success" o "error")
        String documentation, // Opcional, si quieres incluirlo
        String terms_of_use, // Opcional, si quieres incluirlo
        long time_last_update_unix, // Opcional
        String time_last_update_utc, // Opcional
        String base_code, // Moneda de origen
        String target_code, // Moneda de destino
        double conversion_rate, // Tasa de conversión
        double conversion_result // El resultado de la conversión
) {
}
