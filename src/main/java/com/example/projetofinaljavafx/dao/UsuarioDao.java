package com.example.projetofinaljavafx.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;

import com.example.projetofinaljavafx.modelo.Usuario;
import com.example.projetofinaljavafx.modelo.UsuarioVIP;

public class UsuarioDao {
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private int ultimoId = 0;

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void carregarUsuarios() throws IOException {
        String caminhoArquivo = "src/main/java/com/example/projetofinaljavafx/arquivos/usuarios.txt";
        File arquivo = new File(caminhoArquivo);

        if (!arquivo.exists()){
            arquivo.getParentFile().mkdirs();
        }

        try(BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String login;

            while((login = br.readLine()) != null) {
                String senha = br.readLine();
                String tipo = br.readLine();
                int id = Integer.parseInt(br.readLine());

                if(id > ultimoId) {
                    ultimoId = id;
                }

                Usuario usuario;

                if(tipo.equals("VIP")) {
                    usuario = new UsuarioVIP();
                }
                else {
                    usuario = new Usuario();
                }
                
                usuario.setLogin(login); 
                usuario.setSenha(senha);
                usuario.setId(ultimoId);
                usuarios.add(usuario);
            }
        }
        catch(IOException e) {
            throw e;
        }
    }

    public boolean cadastrarUsuario(String nome, String senha, String tipo) {
        if(existeUsuario(nome)){
            return false;
        };
        Usuario usuario;
        if(tipo.equals("VIP") ) {
            usuario = new UsuarioVIP(); 
        } 
        else {
            usuario = new Usuario();
        }
    
        usuario.setLogin(nome);
        usuario.setSenha(senha);
        ultimoId++;
        usuario.setId(ultimoId);

        String caminhoUsuarios = "src/main/java/com/example/projetofinaljavafx/arquivos/usuarios.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoUsuarios, true))) {
            writer.write(usuario.getLogin());
            writer.newLine();
            writer.write(usuario.getSenha());
            writer.newLine();
            writer.write(tipo);
            writer.newLine();
            writer.write(Integer.toString(usuario.getId()));
            writer.newLine();
        } 
        catch (IOException e) {
            return false;
        }
            
        usuarios.add(usuario);
        return true;
    }

    public boolean existeUsuario(String nome) {
        for(Usuario u : usuarios) {
            if(u.getLogin().equals(nome)) {
                return true;
            }
        }
        return false;
    }

}