package com.example.projetofinaljavafx.dao;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Classe que realiza a persistência dos diretórios de músicas.
 */
public class DiretorioDao {
    /**
     * Lista de diretórios de músicas.
     */
    private ArrayList<String> diretorios = new ArrayList<>();

    /**
     * Cadastra um diretório de músicas, salvando-o no arquivo e na lista
     * de diretórios.
     * @param caminho Caminho para o diretório.
     * @throws IOException caso haja erro ao ler o arquivo.
     */
    public void cadastrarDiretorio(String caminho) throws IOException {
        for (String dir : diretorios) {
            if(dir.equals(caminho)) {
                return;
            }
        }
        String caminhoArquivo = "src/main/java/com/example/projetofinaljavafx/arquivos/diretorios.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            writer.write(caminho);
            writer.newLine();
            diretorios.add(caminho);
            escreverMusicas(caminho);
        } 
        catch (IOException e) {
            throw e;
        }
    }

    /**
     * Carrega os diretórios de músicas salvos no arquivo para a lista de
     * diretórios.
     * @throws IOException caso haja erro ao ler o arquivo.
     */
    public void carregarDiretorios() throws IOException {
        String caminhoArquivo = "src/main/java/com/example/projetofinaljavafx/arquivos/diretorios.txt";
        File arquivo = new File(caminhoArquivo);

        new PrintWriter("src/main/java/com/example/projetofinaljavafx/arquivos/musicas.txt").close();

        if (!arquivo.exists()) {
            arquivo.getParentFile().mkdirs();
        }

        try(BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while((linha = br.readLine()) != null) {
                diretorios.add(linha);
                escreverMusicas(linha);
            }
        }
        catch(IOException e) {
            throw e;
        }
    }

    /**
     * Escaneia o diretório de músicas e escreve o caminho de cada arquivo
     * .mp3 encontrado no arquivo de músicas.
     * @param caminhoDiretorio Caminho para o diretório.
     * @throws IOException caso haja erro ao ler o arquivo.
     */
    public void escreverMusicas(String caminhoDiretorio) throws IOException {
        String caminhoMusicas = "src/main/java/com/example/projetofinaljavafx/arquivos/musicas.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoMusicas, true))) {
            File f = new File(caminhoDiretorio);
            File[] arquivos = f.listFiles();

            for (File arquivo : arquivos) {
                String caminhoArquivo = arquivo.getAbsolutePath();
                int indicePonto = caminhoArquivo.lastIndexOf(".");

                if (indicePonto >= 0 && indicePonto < caminhoArquivo.length()) {
                    if (caminhoArquivo.substring(indicePonto).equals(".mp3")) {
                        writer.write(caminhoArquivo);
                        writer.newLine();
                    }
                }
            }
        } 
        catch (IOException e) {
            throw e;
        }
    }

}
