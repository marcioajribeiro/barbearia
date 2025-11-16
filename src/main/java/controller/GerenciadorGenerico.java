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
 * Classe abstrata responsável por fornecer operações genéricas de
 * carregamento e salvamento de listas utilizando arquivos JSON.
 *
 * @author Márcio Antônio
 * @author Rafael Martins
 */
public abstract class GerenciadorGenerico {

    /**
     * Instância do Gson configurada com:
     */
    private static final Gson gson =
            new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .setPrettyPrinting()
                    .create();

    /**
     * Carrega uma lista genérica de objetos a partir de um arquivo JSON.
     *
     *
     * @param <T>    tipo dos objetos contidos na lista
     * @param caminho caminho do arquivo JSON
     * @param type     classe do tipo T para reconstrução dos objetos
     * @return lista carregada a partir do arquivo, ou uma lista vazia caso o arquivo
     *         exista mas esteja vazio; retorna {@code null} caso ocorra uma falha de leitura
     */
    public <T> List<T> carregarListas(String caminho, Class<T> type) {
        try (FileReader reader = new FileReader(caminho)) {

            Type tipoLista = TypeToken.getParameterized(List.class, type).getType();
            List<T> objetos = gson.fromJson(reader, tipoLista);

            return (objetos != null) ? objetos : new ArrayList<>();

        } catch (IOException e) {
            System.err.println("️ Erro ao carregar lista: " + e.getMessage());
            return null;
        }
    }

    /**
     * Salva uma lista genérica de objetos em formato JSON.
     *
     *
     * @param <T>   tipo dos objetos contidos na lista
     * @param caminho caminho do arquivo onde a lista será salva
     * @param lista   lista de objetos a serem serializados
     */
    public <T> void salvarLista(String caminho, List<T> lista) {
        try (FileWriter writer = new FileWriter(caminho)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar: " + e.getMessage());
        }
    }
}