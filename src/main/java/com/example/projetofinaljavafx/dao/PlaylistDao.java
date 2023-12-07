package com.example.projetofinaljavafx.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.example.projetofinaljavafx.modelo.Musica;
import com.example.projetofinaljavafx.modelo.Playlist;
import com.example.projetofinaljavafx.modelo.Usuario;

public class PlaylistDao {
    ArrayList<Playlist> playlists;
    
    public void carregarPlaylists(Usuario usuario) throws IOException {
        String caminhoPasta = "src/main/java/com/example/projetofinaljavafx/arquivos/playlists";
        File pasta = new File(caminhoPasta);

        if (!pasta.exists()){
            pasta.getParentFile().mkdirs();
        }

        for(File f : pasta.listFiles()) {
            int indiceInicio = f.getName().indexOf("_");
            indiceInicio++;
            int indiceFinal = f.getName().length() - 4;

            String nomePlaylist = f.getName().substring(indiceInicio, indiceFinal);

            try(BufferedReader br = new BufferedReader(new FileReader(f))) {
                String nomeUsuario = br.readLine();
                int idUsuario = Integer.parseInt(br.readLine());

                if(nomeUsuario != usuario.getLogin() || idUsuario != usuario.getId()) {
                    continue;
                }

                ArrayList<Musica> musicas = new ArrayList<>();

                String tituloMusica;
                while((tituloMusica = br.readLine()) != null) {
                    String caminhoMusica = br.readLine();

                    Musica m = new Musica(tituloMusica, caminhoMusica);
                    musicas.add(m);
                }

                Playlist playlist = new Playlist(nomePlaylist, musicas, usuario);
                playlists.add(playlist);
            }
            catch(IOException e) {
                throw e;
            }
        }
    }

    public boolean cadastrarPlaylist(String nome, Usuario usuarioDono) throws IOException {
        String caminhoPlaylist = "src/main/java/com/example/projetofinaljavafx/arquivos/playlists/playlist_" + nome + ".txt";
        File arquivoPlaylist = new File(caminhoPlaylist);

        if (!arquivoPlaylist.exists()){
            arquivoPlaylist.getParentFile().mkdirs();
        }
        else {
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoPlaylist, true))) {
            writer.write(usuarioDono.getLogin());
            writer.newLine();
            writer.write(usuarioDono.getId());
            writer.newLine();

        } 
        catch (IOException e) {
            return false;
        }
        
        Playlist p = new Playlist(nome, new ArrayList<>(), usuarioDono);
        playlists.add(p);

        return true;
    }

    public void adicionarMusica(String nomePlaylist, Musica musica, Usuario usuario) throws IOException {
        String caminhoPlaylist = "src/main/java/com/example/projetofinaljavafx/arquivos/playlists/playlist_" + nomePlaylist + ".txt";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoPlaylist))) {
            String login = br.readLine();
            int id = Integer.parseInt(br.readLine());

            if (usuario.getId() != id || usuario.getLogin() != login) {
                return;
            }
        }
        catch (IOException e) {
            throw e;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoPlaylist, true))) {
            writer.write(musica.getNome());
            writer.newLine();
            writer.write(musica.getCaminho());
            writer.newLine();
        } 
        catch (IOException e) {
            throw e;
        }

        Playlist playlistArray = null;
        for(Playlist p : playlists) {
            if(p.getTitulo() == nomePlaylist) {
                playlistArray = p;
            }
        }

        playlistArray.getMusicas().add(musica);
    }
}
