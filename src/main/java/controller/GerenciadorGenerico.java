/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import adapter.LocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
import java.time.LocalDateTime;

/**
 *
 * @author MARCIO JUNIOR
 */
public abstract class GerenciadorGenerico {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();
  
    public <T> List<T> carregarListas(String caminho, Class<T> type){
        try (FileReader reader = new FileReader(caminho)){   
        Type tipoLista = TypeToken.getParameterized(List.class, type).getType(); 
        List<T> objetos = gson.fromJson(reader, tipoLista);
        
        return (objetos!=null)? objetos : new ArrayList<>();
    }   catch (IOException e){
        System.err.println("️ Erro ao carregar lista: " + e.getMessage());  
        return null;
        
    }
    }
    
    public  <T> void salvarLista(String caminho, List<T> lista){
        try(FileWriter writer = new FileWriter(caminho)){
            gson.toJson(lista, writer);
        }catch (IOException e){
            System.err.println("Erro ao salvar: " + e.getMessage());
        }
    }
  
    
}