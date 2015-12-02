import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class JavaExample {

    public static void main(String[] args) throws Exception {
        String apikey = "YOUR_API_KEY_HERE";
        String data = "doc[document_url]=http://104.155.79.247/html/index_1.html";
        data += "&doc[name]=java_sample_2.pdf";
        data += "&doc[document_type]=pdf";
        data += "&doc[test]=true";
        data += "&doc[javascript]=true";
        data += "&doc[prince_options][javascript]=true";

        byte[] encodedData = data.getBytes("UTF8");

        String url = "https://docraptor.com/docs?user_credentials=" + apikey;
        String agent = "Mozilla/4.0";
        String type = "application/x-www-form-urlencoded";

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setDoOutput(true); // send as a POST
        conn.setRequestProperty("User-Agent", agent);
        conn.setRequestProperty("Content-Type", type);
        conn.setRequestProperty("Content-Length", Integer.toString(encodedData.length));

        OutputStream os = conn.getOutputStream();
        os.write(encodedData);
        os.flush();

        InputStream responseStream = conn.getInputStream();
        OutputStream outputStream = new FileOutputStream(new File("java_sample_3.pdf"));
        byte[] buffer = new byte[1024];
        int len;
        while ((len = responseStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, len);
        }
        responseStream.close();
        outputStream.close();
    }
}
