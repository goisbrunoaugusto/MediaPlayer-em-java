package com.example.projetofinaljavafx.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.example.projetofinaljavafx.modelo.Musica;

public class MusicaDao {
    ArrayList<Musica> musicas = new ArrayList<>();

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public void carregarMusicas() throws IOException {
        String caminhoArquivo = "src/main/java/com/example/projetofinaljavafx/arquivos/musicas.txt";
        File arquivo = new File(caminhoArquivo);

        if (!arquivo.exists()){
            arquivo.getParentFile().mkdirs();
        }

        try(BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            musicas.clear();
            while((linha = br.readLine()) != null) {
                int indicePonto = linha.lastIndexOf(".");
                String nomeMusica = linha.substring(0, indicePonto);
                
                Musica m = new Musica(nomeMusica, linha);
                musicas.add(m);
            }
        }
        catch(IOException e) {
            throw e;
        }
    }
}