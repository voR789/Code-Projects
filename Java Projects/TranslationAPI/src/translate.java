import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.*;
import java.util.*;


public class translate {
    public static void main(String[] args) throws UnsupportedEncodingException {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter beginning translation language: ");
        String startLan = input.nextLine();
        System.out.print("Enter ending translation language: ");
        String endLan = input.nextLine();
        System.out.print("Enter phrase to translate: ");
        String word = input.nextLine();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://text-translator2.p.rapidapi.com/translate"))
                .header("content-type", "application/x-www-form-urlencoded")
                .header("X-RapidAPI-Key", "d86bf41259msh0f8631b36b84e3dp1ab581jsn84761ee055df")
                .header("X-RapidAPI-Host", "text-translator2.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString("source_language="+startLan+"&target_language="+endLan+"&text="+word))
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String translationWithShell = unescape(response.body());
        String translationWithoutShell = translationWithShell.substring(translationWithShell.indexOf("translatedText")+18,translationWithShell.indexOf("\"\n" + "    }"));
        System.out.println(translationWithoutShell);
    }

    public static String unescape(String s) {
        int i=0, len=s.length();
        char c;
        StringBuffer sb = new StringBuffer(len);
        while (i < len) {
            c = s.charAt(i++);
            if (c == '\\') {
                if (i < len) {
                    c = s.charAt(i++);
                    if (c == 'u') {
                        // TODO: check that 4 more chars exist and are all hex digits
                        c = (char) Integer.parseInt(s.substring(i, i+4), 16);
                        i += 4;
                    } // add other cases here as desired...
                }
            } // fall through: \ escapes itself, quotes any character but u
            sb.append(c);
        }
        return sb.toString();
    }
    // Credits to stackOverflow for code for encoding unicode from API to UTF-8 format so Java can read it.
}
