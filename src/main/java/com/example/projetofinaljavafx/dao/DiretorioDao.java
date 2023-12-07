package com.example.projetofinaljavafx.dao;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DiretorioDao {
    private ArrayList<String> diretorios = new ArrayList<>();

    public void cadastrarDiretorio(String caminho) throws IOException {
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

    public void carregarDiretorios() throws IOException {
        String caminhoArquivo = "src/main/java/com/example/projetofinaljavafx/arquivos/diretorios.txt";
        File arquivo = new File(caminhoArquivo);

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

    public void escreverMusicas(String caminhoDiretorio) throws IOException {
        String caminhoMusicas = "src/main/java/com/example/projetofinaljavafx/arquivos/musicas.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoMusicas, true))) {
            File f = new File(caminhoDiretorio);
            File[] arquivos = f.listFiles();

            for (File arquivo : arquivos) {
                String caminhoArquivo = arquivo.getAbsolutePath();
                int indicePonto = caminhoArquivo.lastIndexOf(".");

                if (indicePonto >= 0 && indicePonto < caminhoArquivo.length()) {
                    if (caminhoArquivo.substring(indicePonto).equals("mp3")) {
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
