import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Demoapi {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://irctc1.p.rapidapi.com/api/v1/searchStation?query="))
				.header("X-RapidAPI-Key", "faff75f070msh73f7ba897fa7245p191030jsn0ad7a34a9e0f")
				.header("X-RapidAPI-Host", "irctc1.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
	}

}
