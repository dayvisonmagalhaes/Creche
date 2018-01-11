package br.com.creche.Deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import br.com.creche.modelo.AlunoPresenca;

/**
 * Created by u6390869 on 11/01/2018.
 */

public class AlunoPresencaDesc implements JsonDeserializer{

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement element = json.getAsJsonObject();
        if (json.getAsJsonObject() != null){
            element = json.getAsJsonObject();
        }

        return (new Gson().fromJson(element,AlunoPresenca.class));
    }
}
