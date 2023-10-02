package HospedeiroOrigemTCP;

import HospedeiroComum.Mensagem;

import java.net.*;

import java.io.*;

public class HospedeiroOrigemTCP {
    public static void main(String[] args) throws Exception {
        // CONFIGURAÇÃO DE CONEXÃO DO SERVIDOR
        System.out.println("Insira o Ip do hospedeiro de destino:");
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        InetAddress ipDestino = InetAddress.getByName(consoleReader.readLine());

        System.out.println("Insira a Porta do hospedeiro de destino:");
        consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int portaDestino = Integer.parseInt(consoleReader.readLine());

        System.out.println(ipDestino + ":" + portaDestino);

        System.out.println("Insira o caminho do arquivo a ser enviado:");
        String caminhoArquivo = consoleReader.readLine();
        consoleReader.close();

        File arquivo = new File(caminhoArquivo);

        // LENDO ARQUIVO I/O DO DISCO PARA BUFFER DE MEMÓRIA
        FileInputStream fis = new FileInputStream(arquivo);
        BufferedInputStream bis = new BufferedInputStream(fis);
        byte[] arquivoBytes = new byte[(int) arquivo.length()];
        bis.read(arquivoBytes);
        bis.close();

        // TENTA CONECTAR AO DESTINO
        Socket clienteSocket = new Socket(ipDestino, portaDestino);

        float startTime = System.nanoTime();
        ObjectOutputStream saida = new ObjectOutputStream(clienteSocket.getOutputStream());

        // ENVIA STREAM DE ARQUIVO
        saida.writeObject(new Mensagem(arquivo.getName(), arquivoBytes));

        saida.flush();
        saida.close();
        clienteSocket.close();

        double endTime = System.nanoTime();

        // CONVERTE DURAÇÃO DE NANO SEGUNDOS PARA SEGUNDOS
        double duracao = (endTime - startTime) / 1e9;

        double tamanhoArquivoEmBits = arquivo.length() * 8;

        // DIVIDE POR 1e6 PARA CONVERTER PARA MEGABITS
        double taxaDeTransferenciaMbps = tamanhoArquivoEmBits / duracao / 1e6f;

        System.out.println("Taxa de transferência: " + taxaDeTransferenciaMbps + " Mbps");
    }
}
