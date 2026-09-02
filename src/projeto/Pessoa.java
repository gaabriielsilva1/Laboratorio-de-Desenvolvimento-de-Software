package projeto;

public class Pessoa {
    public String nome;
    public int idade;
    public char sexo;
    public String idioma;
    public String interesse; // Novo atributo

    public Pessoa(String nome, int idade, char sexo, String idioma, String interesse) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.idioma = idioma;
        this.interesse = interesse;
    }

    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public char getSexo() { return sexo; }
    public String getIdioma() { return idioma; }
    public String getInteresse() { return interesse; }

    // Array preparado com todos os atributos incluindo o interesse
    public Object[] obterDados() {
        return new Object[] { nome, idade, String.valueOf(sexo), idioma, interesse };
    }

    public String toCsvRow() {
        String nomeFormatado = nome.replace("\"", "\"\"");
        String idiomaFormatado = idioma.replace("\"", "\"\"");
        String interesseFormatado = interesse.replace("\"", "\"\""); 
        
        return String.format("\"%s\",%d,\"%c\",\"%s\",\"%s\"", nomeFormatado, idade, sexo, idiomaFormatado, interesseFormatado);
    }
}