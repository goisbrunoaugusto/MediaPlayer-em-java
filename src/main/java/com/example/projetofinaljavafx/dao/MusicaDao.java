package com.example.projetofinaljavafx.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.example.projetofinaljavafx.modelo.Musica;

/**
 * Classe que realiza a persistência das músicas.
 */
public class MusicaDao {
    /**
     * Lista de músicas.
     */
    ArrayList<Musica> musicas = new ArrayList<>();

    /**
     * Retorna a lista de músicas.
     * @return Lista de músicas.
     */
    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    /**
     * Carrega as músicas salvas no arquivo para a lista de músicas.
     * @throws IOException caso haja erro ao ler o arquivo.
     */
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
                File arquivoMusica = new File(linha);
                String nomeMusica = arquivoMusica.getName();
                nomeMusica = nomeMusica.substring(0, nomeMusica.lastIndexOf('.'));
                
                Musica m = new Musica(nomeMusica, linha);
                musicas.add(m);
            }
        }
        catch(IOException e) {
            throw e;
        }
    }
}
