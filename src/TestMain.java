import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class TestMain {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = BasicAuthInterceptor.getUnsafeOkHttpClient();

        Request request = new Request.Builder()
                .url("https://"+MainFrame.IP_PORT+"/argusapi/v1/parameters/10876?currentValues=")
                .get()
                .addHeader("Authorization", "Basic "+MainFrame.AUTH_TOKEN)
                .build();

        Response response = client.newCall(request).execute();

        System.out.println("Response:\n\n"+response.body().string());
    }

}
