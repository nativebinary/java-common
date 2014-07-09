package common.basic.externals.google;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpMediaType;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpStatusCodes;
import com.google.api.client.http.MultipartContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import common.basic.interfaces.ICallback;
import common.basic.logs.Logger;
import common.basic.utils.CloseableUtil;
import common.basic.utils.MimeType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GoogleHttpClientUtil {
    static HttpRequestFactory httpRequestFactory;

    static {
        httpRequestFactory = new NetHttpTransport().createRequestFactory();
    }

    GoogleHttpClientUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static String get(URL url)
    {
        try {
            final HttpResponse httpResponse = request(url);
            final int statusCode = httpResponse.getStatusCode();
            if (statusCode == HttpStatusCodes.STATUS_CODE_OK)
            {
                return httpResponse.parseAsString();
            }

        } catch (IOException e) {
            Logger.e(e);
        } catch (InterruptedException e) {
            Logger.e(e);
        } catch (ExecutionException e) {
            Logger.e(e);
        }

        return "";
    }

    private static HttpResponse request(URL url) throws IOException, InterruptedException, ExecutionException {GenericUrl genericUrl = new GenericUrl(url);
        final HttpRequest httpRequest = httpRequestFactory.buildGetRequest(genericUrl);
        final Future<HttpResponse> httpResponseFuture = httpRequest.executeAsync();
        return httpResponseFuture.get();
    }


    public static byte[] download(URL url) {
        try {
            final HttpResponse httpResponse = request(url);
            final int statusCode = httpResponse.getStatusCode();
            if (statusCode == HttpStatusCodes.STATUS_CODE_OK)
            {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    httpResponse.download(byteArrayOutputStream);
                    return byteArrayOutputStream.toByteArray();
                }
                finally {
                    CloseableUtil.close(byteArrayOutputStream);
                }
            }

        } catch (IOException e) {
            Logger.e(e);
        } catch (InterruptedException e) {
            Logger.e(e);
        } catch (ExecutionException e) {
            Logger.e(e);
        }

        return null;
    }


    public static void downloadAsStream(URL url, final ICallback<InputStream> callback) {
        try {
            final HttpResponse httpResponse = request(url);
            final int statusCode = httpResponse.getStatusCode();
            if (statusCode == HttpStatusCodes.STATUS_CODE_OK)
            {
                final InputStream inputStream = httpResponse.getContent();
                callback.onSuccess(inputStream);
                CloseableUtil.close(inputStream);
            }

        } catch (IOException e) {
            Logger.e(e);
            callback.onFail(e);
        } catch (InterruptedException e) {
            Logger.e(e);
            callback.onFail(e);
        } catch (ExecutionException e) {
            Logger.e(e);
            callback.onFail(e);
        }
    }


    public static String upload(URL url, String name, File file) {
        try {
            GenericUrl genericUrl = new GenericUrl(url);

            MultipartContent multipartContent = new MultipartContent();
            multipartContent.setMediaType(new HttpMediaType("multipart/form-data"));
            final String boundary = multipartContent.getBoundary();
            multipartContent.setBoundary("__END_OF_PART__");

            final FileContent fileContent = new FileContent(MimeType.detect(file).getValue() , file);

            final HttpHeaders fileHeaders = new HttpHeaders();
            fileHeaders.set("Content-Disposition", "form-data; name=\"file\"; filename=\"" + file.getName() + "\"");
            fileHeaders.setContentType("application/octet-stream");

            multipartContent.addPart(new MultipartContent.Part(fileHeaders, fileContent));

            final HttpHeaders textHeaders = new HttpHeaders();
            textHeaders.set("Content-Disposition", "form-data; name=\"title\"");

            multipartContent.addPart(new MultipartContent.Part(textHeaders, ByteArrayContent.fromString("text/plain", name)));

            final HttpRequest httpRequest = httpRequestFactory.buildPostRequest(genericUrl, multipartContent);

//            java.util.logging.Logger logger = java.util.logging.Logger.getLogger("com.google.api.client");
//            logger.setLevel(Level.ALL);
//            httpRequest.setLoggingEnabled(true);
//            httpRequest.setCurlLoggingEnabled(true);

            final HttpResponse httpResponse = httpRequest.execute();
            final int statusCode = httpResponse.getStatusCode();
            if (statusCode == HttpStatusCodes.STATUS_CODE_OK)
            {
                return httpResponse.parseAsString();
            }

        } catch (IOException e) {
            Logger.e(e);
        }

        return "";
    }

}
