package HospedeiroOrigemTCP;

import HospedeiroComum.Mensagem;

import java.net.*;

import java.io.*;

public class HospedeiroOrigemTCP {
    public static void main(String[] args) throws Exception {
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
        
        FileInputStream fis = new FileInputStream(arquivo);
        BufferedInputStream bis = new BufferedInputStream(fis);
        byte[] arquivoBytes = new byte[(int) arquivo.length()];
        bis.read(arquivoBytes);
        bis.close();

        Socket clienteSocket = new Socket(ipDestino, portaDestino);

        ObjectOutputStream saida = new ObjectOutputStream(clienteSocket.getOutputStream());
        
        saida.writeObject(new Mensagem(arquivo.getName(), arquivoBytes));
        // saida.writeUTF(arquivoNome);
        // saida.write(arquivoBytes);

        saida.flush();
        saida.close();
        clienteSocket.close();
    }
}
