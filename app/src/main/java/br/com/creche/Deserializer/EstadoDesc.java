package br.com.creche.Deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import br.com.creche.modelo.Estado;

/**
 * Created by u6390869 on 10/09/2017.
 */

public class EstadoDesc implements JsonDeserializer  {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement element = json.getAsJsonObject();
        if (json.getAsJsonObject() != null){
            element = json.getAsJsonObject();
        }

        return (new Gson().fromJson(element,Estado.class));
    }
}
