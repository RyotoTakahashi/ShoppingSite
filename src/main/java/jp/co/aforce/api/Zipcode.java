package jp.co.aforce.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet("/zipcode")
public class Zipcode extends HttpServlet {

	private final HttpClient client = HttpClient.newHttpClient();
	private final Gson gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String zipcode = req.getParameter("zipcode");
		if (zipcode == null || zipcode.isBlank()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "郵便番号が必要です");
			return;
		}

		zipcode = zipcode.replace("-", "");

		String apiUrl = "https://zipcloud.ibsnet.co.jp/api/search?zipcode=" + zipcode;

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(apiUrl))
				.GET()
				.build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() == 200) {
				JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

				if (!json.get("results").isJsonNull()) {
					JsonArray results = json.getAsJsonArray("results");
					JsonObject address = results.get(0).getAsJsonObject();

					String prefecture = address.get("address1").getAsString();
					String city = address.get("address2").getAsString();

					JsonObject result = new JsonObject();
					result.addProperty("prefecture", prefecture);
					result.addProperty("city", city);

					resp.setContentType("application/json; charset=UTF-8");
					resp.getWriter().write(gson.toJson(result));
				} else {
					resp.sendError(HttpServletResponse.SC_NOT_FOUND, "住所が見つかりません");
				}

			} else {
				resp.sendError(HttpServletResponse.SC_BAD_GATEWAY, "API接続に失敗しました");
			}

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "API呼び出し中断");
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "API呼び出しエラー");
		}
	}

}
