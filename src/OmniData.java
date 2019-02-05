import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;


public class OmniData {

    @SerializedName("DataList")
    @Expose List<DataList> dataList;

    public class DataList{

        @SerializedName("Parameter")
        @Expose int parameter;
        @SerializedName("Parameter Label")
        @Expose String parameterLabel;
        @SerializedName("DataSet")
        @Expose List<DataSet> dataSet;

        public class DataSet{

            @SerializedName("Data")
            @Expose String data;
            @SerializedName("Time")
            @Expose String time;

        }

    }

    public OmniData getData(){

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        String response;

        response = readUrl("https://\"+MainFrame.IP_PORT+\"/argusapi/v1/parameters/10876?currentValues="); //Grabs x amount of JSON Formatted RAW text from the website
        System.out.println("Response: "+response);
        return gson.fromJson(response, OmniData.class); //Parses the JSON Formatted RAW text into a JSON Object and then creates a Student.java object with said data.
    }


    private String readUrl(String url){

        OkHttpClient client = BasicAuthInterceptor.getUnsafeOkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Authorization", "Basic "+MainFrame.AUTH_TOKEN)
                .build();


        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


}