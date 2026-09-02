package projeto;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Arquivo {
    
    public static void exportarParaCsv(List<Pessoa> lista, String caminhoArquivo) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivo))) {
            // Cabeçalho atualizado com a nova coluna
            writer.println("Nome,Idade,Sexo,Idioma,Interesse"); 

            for (Pessoa pessoa : lista) {
                writer.println(pessoa.toCsvRow());
            }
        }
    }
}