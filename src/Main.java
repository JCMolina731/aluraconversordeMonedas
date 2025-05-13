import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner opcion = new Scanner(System.in);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();
        while (true){

            System.out.println("*******************************************************");
            System.out.println("Sea Bienvenido/a al Conversor de Moneda =]");
            System.out.println("\n1) Dólar =>> Peso Argentino");
            System.out.println("2) Peso argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real Brasileño");
            System.out.println("4) Real Brasileño =>> Dòlar");
            System.out.println("5) Dólar =>> Peso Colombiano");
            System.out.println("6) Peso Colombiano =>> Dólar");
            System.out.println("7) Salir");
            System.out.println("Elija una opción válida:");
            System.out.println("*******************************************************");

            int input = opcion.nextInt();

            if(input != 7){
                Scanner valor = new Scanner(System.in);
                System.out.println("Ingrese el valor que deseas convertir");
                double dates = valor.nextDouble();

                String moneda1 = "";
                String moneda2 = "";
                switch (input){

                    case 1:
                        moneda1 = "USD";
                        moneda2 = "ARS";
                        break;
                    case 2:
                        moneda1 = "ARS";
                        moneda2 = "USD";
                        break;
                    case 3:
                        moneda1 = "USD";
                        moneda2 = "BRL";
                        break;
                    case 4:
                        moneda1 = "BRL";
                        moneda2 = "USD";
                        break;
                    case 5:
                        moneda1 = "USD";
                        moneda2 = "COP";
                        break;
                    case 6:
                        moneda1 = "COP";
                        moneda2 = "USD";
                        break;
                }
                String api_key = "b70caa5df945049b7b05b5a3";
                String direccion = "https://v6.exchangerate-api.com/v6/";

                String urlString = direccion + api_key + "/pair/" + moneda1 + "/" + moneda2 + "/" + dates;

                try{
                    HttpClient client =HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(urlString))
                            .build();
                    HttpResponse<String> response =client
                            .send(request, HttpResponse.BodyHandlers.ofString());
                    String json =response.body();
                    MonedaOmdb mimoneda = gson.fromJson(json, MonedaOmdb.class);
                    System.out.println("El valor "+dates+" ["+moneda1+"] corresponde al valor final de =>>> "+mimoneda.conversion_result() +" ["+moneda2+"]");

                }catch (Exception e){
                    System.out.println("Ocurrio un error al consultar la API:");
                    e.printStackTrace();
                }

            }else{
                System.out.println("Sistema de Conversor de Monedas terminado");
                break;
            }

        }
    }
}