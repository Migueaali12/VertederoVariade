import ec.edu.utpl.clasica.computacion.pa.s7.pe.URIData;
import ec.edu.utpl.clasica.computacion.pa.s7.pe.WebClient;

import java.io.IOException;
import java.util.concurrent.Callable;

public class SiteCallable implements Callable<SiteData> {

    private String url;

    public SiteCallable(String url) {
        this.url = url;
    }

    @Override
    public SiteData call() throws IOException, InterruptedException {
        URIData uridata;
        try {
            uridata = WebClient.getStatus(url);
        } catch (IOException e) {
            throw new IOException("No se pudieron obtener datos de la siguiente URL: " + url);
        }
        return new SiteData(url, uridata);
    }
}
