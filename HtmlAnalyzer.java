import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class HtmlAnalyzer {
    public static void main(String[] args) {
        // Verifica se o argumento contém uma URL ou um caminho de arquivo válido
        if (args.length != 1 || !(args[0].startsWith("http") || new File(args[0]).exists())) {
            System.out.println("Uso: java HtmlAnalyzer <url ou caminho de arquivo>");
            return;
        }

        String htmlContent = "";
        try {
            if (args[0].startsWith("http")) {
                // Faz a leitura do conteúdo HTML a partir da URL
                URL url = new URL(args[0]);
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    htmlContent += inputLine.trim() + "\n";
                }
                in.close();
            } else {
                // Faz a leitura do conteúdo HTML a partir do arquivo
                BufferedReader in = new BufferedReader(new FileReader(args[0]));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    htmlContent += inputLine.trim() + "\n";
                }
                in.close();
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo ou conectar a URL");
            return;
        }

        String deepestText = findDeepestText(htmlContent);
        if (deepestText == null) {
            System.out.println("Não foi possível encontrar texto na estrutura HTML");
        } else if (deepestText.equals("malformed HTML")) {
            System.out.println("Estrutura HTML mal formada");
        } else {
            System.out.println(deepestText);
        }
    }

    private static String findDeepestText(String htmlContent) {
        String[] lines = htmlContent.split("\n");

        String deepestText = null;
        int deepestLevel = 0;

        int currentLevel = 0;
        for (String line : lines) {
            String trimmedLine = line.trim();

            // Ignora linhas em branco
            if (trimmedLine.isEmpty()) {
                continue;
            }

            // Verifica se a linha contém uma tag de abertura ou fechamento
            if (trimmedLine.startsWith("<") && trimmedLine.endsWith(">")) {
                // Ignora tags de auto-fechamento
                if (trimmedLine.endsWith("/>")) {
                    continue;
                }

                if (trimmedLine.startsWith("</")) {
                    // Tag de fechamento
                    currentLevel--;
                } else {
                    // Tag de abertura
                    currentLevel++;
                }
            } else {
                // Trecho de texto
                if (currentLevel > deepestLevel) {
                    deepestLevel = currentLevel;
                    deepestText = trimmedLine;
                }
            }

            // Verifica se a estrutura HTML é mal-formada
            if (currentLevel < 0) {
                return "Estrutura HTML mal formada";
            }
        }

        if (currentLevel != 0) {
            return "Estrutura HTML mal formada";
        }

        return deepestText;
    }
}
