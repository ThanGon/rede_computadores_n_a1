package HospedeiroDestinoTCP;

import HospedeiroComum.Mensagem;

import java.io.*;

import java.net.*;


public class HospedeiroDestinoTCP {

    public static void main(String[] args) throws Exception {

            System.out.println("Insira o caminho para armazenamento de arquivos");
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String path = consoleReader.readLine();

            // Cria um novo servidor na porta 7777

            ServerSocket server = new ServerSocket(7777);

            System.out.println("Servidor iniciado na porta 7777");

            // Aceita uma conexão de um cliente

            Socket socket = server.accept();

            long startTime = System.nanoTime();

            System.out.println("Conexão estabelecida");

            ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

            Mensagem mensagemTrocada;

            try {
                mensagemTrocada = (Mensagem) in.readObject();

                // Cria um objeto File para o arquivo que será escrito

                File outputFile = new File(path  + "\\" + mensagemTrocada.nomeArquivo);

                // Cria um FileOutputStream para escrever os dados no arquivo

                FileOutputStream fos = new FileOutputStream(outputFile);

                // Escreve os dados do array de bytes no arquivo

                fos.write(mensagemTrocada.arquivo);

                // Fecha o FileOutputStream, garantindo que todos os dados sejam escritos no arquivo

                fos.close();

//                System.out.println("ArquivoDeTeste.txt pronto!");

            } catch (IOException | ClassNotFoundException e) {

                System.out.println(e.getMessage());
                throw e;

            }

            long endTime = System.nanoTime();

            // Calcula a duração da transferência em segundos

            double durationInSeconds = (endTime - startTime)
                    / 1e9;

            // Calcula o tamanho do arquivo em bits

            double fileSizeInBits = mensagemTrocada.arquivo.length * 8;

            // Calcula a taxa de transferência em Mbps

            double transferRateMbps = fileSizeInBits / durationInSeconds
                    / 1e6;



            // printa a taxa de transferência

            System.out.println("Taxa de transferência: " +
                    transferRateMbps + " Mbps");





    }

}