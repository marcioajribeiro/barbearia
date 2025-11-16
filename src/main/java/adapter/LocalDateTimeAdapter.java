package adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Adaptador personalizado para serializar e desserializar objetos {@link LocalDateTime}
 * utilizando a biblioteca Gson.
 */
public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {

    /** Formatador utilizado para converter LocalDateTime para string e vice-versa. */
    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    /**
     * Serializa um objeto LocalDateTime para JSON.
     *  @param out   escritor JSON onde o valor será gravado
     *  @param value objeto LocalDateTime a ser convertido; pode ser {@code null}
     *  @throws IOException caso ocorra erro na escrita do JSO
     */
    @Override
    public void write(JsonWriter out, LocalDateTime value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        out.value(formatter.format(value));
    }

    /**
     * Desserializa um valor JSON para um objeto LocalDateTime.
     * @param in leitor JSON contendo a string da data
     * @return objeto LocalDateTime correspondente, ou code null caso a string seja vazia
     * @throws IOException caso ocorra erro na leitura do JSON
     */
    @Override
    public LocalDateTime read(JsonReader in) throws IOException {
        String str = in.nextString();
        if (str == null || str.isEmpty()) {
            return null;
        }
        return LocalDateTime.parse(str, formatter);
    }
}
