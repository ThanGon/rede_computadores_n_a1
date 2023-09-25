package HospedeiroComum;

import java.io.Serializable;

public class Mensagem implements Serializable {
    public String nomeArquivo;
    public byte[] arquivo;

    public Mensagem(String nomeArquivo, byte[] arquivo) {
        this.nomeArquivo = nomeArquivo;
        this.arquivo = arquivo;
    }
}
