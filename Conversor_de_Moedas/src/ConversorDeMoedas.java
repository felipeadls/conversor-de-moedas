import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConversorDeMoedas {

    // Método para mapear nomes de moedas aos códigos ISO
    public static Map<String, String> criarMapaDeMoedas() {
        Map<String, String> mapaMoedas = new HashMap<>();
        mapaMoedas.put("dolar", "USD");
        mapaMoedas.put("euro", "EUR");
        mapaMoedas.put("real", "BRL");
        mapaMoedas.put("iene", "JPY");
        mapaMoedas.put("libra", "GBP");
        mapaMoedas.put("peso", "ARS");
        // Adicione mais moedas conforme necessário
        return mapaMoedas;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> mapaMoedas = criarMapaDeMoedas();

        System.out.println("Digite a moeda de origem: ");
        String moedaOrigemInput = scanner.nextLine().toLowerCase();
        System.out.println("Digite a moeda destino: ");
        String moedaDestinoInput = scanner.nextLine().toLowerCase();
        System.out.println("Digite o valor para conversão: ");
        double valor = scanner.nextDouble();

        // Converte o nome da moeda para o código, se disponível no mapa
        String moedaOrigem = mapaMoedas.getOrDefault(moedaOrigemInput, moedaOrigemInput.toUpperCase());
        String moedaDestino = mapaMoedas.getOrDefault(moedaDestinoInput, moedaDestinoInput.toUpperCase());

        String link = "https://v6.exchangerate-api.com/v6/922827fc88f0f243e1612d39/latest/" + moedaOrigem;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(link))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        if (jsonObject.has("conversion_rates")) {
            JsonObject taxasDeCambio = jsonObject.getAsJsonObject("conversion_rates");

            if (taxasDeCambio.has(moedaDestino)) {
                double taxaDeCambio = taxasDeCambio.get(moedaDestino).getAsDouble();

                // Calcula o valor convertido
                double valorConvertido = valor * taxaDeCambio;
                System.out.printf("O valor convertido é: %.2f %s%n", valorConvertido, moedaDestino);
            } else {
                System.out.println("Moeda de destino não encontrada.");
            }
        } else {
            System.out.println("Erro: campo 'conversion_rates' não encontrado.");
        }
    }
}
