import ec.edu.utpl.clasica.computacion.pa.s7.pe.URIData;

public class SiteData {
    private String url;
    private URIData uridata;

    public SiteData(String url, URIData uridata) {
        this.url = url;
        this.uridata = uridata;
    }

    public String getUrl() {
        return url;
    }

    public URIData getURIData() {
        return uridata;
    }
}
