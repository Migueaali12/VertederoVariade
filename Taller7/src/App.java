import ec.edu.utpl.clasica.computacion.pa.s7.pe.URIData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class App {

    public static void main(String[] args) {

        var urls = List.of(
                "https://dlcdn.apache.org/zeppelin/zeppelin-0.10.1/zeppelin-0.10.1-bin-netinst.tgz",
                "https://freeling-user-manual.readthedocs.io/en/v4.2/processing-classes/",
                "https://freeling-user-manual.readthedocs.io/en/v4.2/modules/tagger/",
                "https://freeling-tutorial.readthedocs.io/en/latest/example01/",
                "https://gephi.org/",
                "https://www.amazon.com/Debt-Collection-Answers-Protect-Rights-ebook/dp/B01AR8H2U0",
                "https://d.tube/!#!/v/billionairepal/l044yenn",
                "https://j4loxa.com",
                "https://github.com/gephi/gephi/releases/download/v0.10.1/gephi-0.10.1-macos-x64.dmg",
                "https://medium.com/swlh/think-like-an-economist-9dda35681e51?aduc=knxn5Qx1561843084525",
                "https://www.dta.fau.de/",
                "https://www.apache.org/dyn/closer.cgi/zeppelin/zeppelin-0.10.1/zeppelin-0.10.1-bin-all.tgz"
        );

        int numThreads = Runtime.getRuntime().availableProcessors();
        System.out.printf("Numero de nucleos del procesador: %d\n", numThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        List<Callable<SiteData>> tasks = new ArrayList<>();

        for (String url : urls) {
            tasks.add(new SiteCallable(url));
        }

        List<String> filteredUrl = new ArrayList<>();

        try {
            List<Future<SiteData>> results = executorService.invokeAll(tasks);

            for (Future<SiteData> result : results) {

                try {
                    SiteData siteData = result.get();
                    if (siteData.getURIData().statusCode() == 200 || siteData.getURIData().statusCode() == 302) {
                        filteredUrl.add(siteData.getUrl());
                    }
                    System.out.println("URL: " + siteData.getUrl());
                    System.out.println("Status Code: " + siteData.getURIData().statusCode());
                    System.out.println("Content Type: " + siteData.getURIData().contentType());
                    System.out.println("Content Length: " + siteData.getURIData().contentLength());
                    System.out.println("-----------------------------");
                } catch (ExecutionException e) {
                    Throwable cause = e.getCause();
                    if (cause instanceof IOException) {
                        System.out.println(cause.getMessage());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Urls con StatusCode igual a 200 0 302:");
        for (String url : filteredUrl) {
            System.out.println(url);
        }

        executorService.shutdown();
    }
}





