import com.google.gson.Gson; // Asegúrate de tener la librería Gson
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaAPI {
    private static final String API_KEY = "9e9f2bba1989ad3828dd9dcf";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

//     Metodo que recibe moneda de origen y moneda de destino
    public ObjetosConversion consultarMonto(String monedaOrigen, String monedaDestino, double monto)
            throws IOException, InterruptedException {

        // Construir la URL para el endpoint /pair/{base}/{target}/{amount}
        String url = BASE_URL + API_KEY + "/pair/" + monedaOrigen + "/" + monedaDestino + "/" + monto;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Usar Gson para convertir el JSON de la respuesta a un objeto ObjetosConversion
        return new Gson().fromJson(response.body(), ObjetosConversion.class);
    }
}

