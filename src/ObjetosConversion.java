public record ObjetosConversion(
        String result, // Campo crucial para verificar si la API fue exitosa ("success" o "error")
        String base_code, // Moneda de origen
        String target_code, // Moneda de destino
        double conversion_rate, // Tasa de conversión
        double conversion_result // El resultado de la conversión
) {
}
