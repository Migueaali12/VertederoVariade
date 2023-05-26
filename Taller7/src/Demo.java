import ec.edu.utpl.clasica.computacion.pa.s7.pe.WebClient;
import java.io.IOException;

public class Demo {

    public static void main(String[] args) throws IOException, InterruptedException {

        String uri1 = "https://utpl.edu.ec";
        //String uri2 = "http://j4loxa.com";
        String uri3 = "https://gephi.org";

        var resUri1 = WebClient.getStatus(uri1);
        //var resUri2 = WebClient.getStatus(uri2);
        var resUri3 = WebClient.getStatus(uri3);

        System.out.println(resUri1);
        //System.out.println(resUri2);
        System.out.println(resUri3);

    }

}
