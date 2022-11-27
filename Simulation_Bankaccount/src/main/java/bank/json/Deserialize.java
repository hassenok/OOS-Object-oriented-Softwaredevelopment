package bank.json;

import bank.IncomingTransfer;
import bank.OutgoingTransfer;
import bank.Payment;
import bank.Transaction;
import com.google.gson.JsonDeserializer;
import com.google.gson.*;

import java.lang.reflect.Type;
/**
 * @author Hassen Trabelsi
 * @version 1.0
 * class Deserialize
 */
public class Deserialize implements JsonDeserializer<Transaction> {
    /**
     * Method for deserializing a Transaction Object
     * @param jsonElement deserialization Object
     * @param type type of the Object
     * @param jsonDeserializationContext further configuration
     * @return Transaction Object
     * @throws JsonParseException Runtime error
     */
    @Override
    public Transaction deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException
    {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String classname = jsonObject.get("CLASSNAME").getAsString();
        JsonObject data = jsonObject.get("INSTANCE").getAsJsonObject();

        if (classname.equals("IncomingTransfer"))
        {
            return new IncomingTransfer(
                    data.get("sender").getAsString(),
                    data.get("recipient").getAsString(),
                    data.get("date").getAsString(),
                    data.get("amount").getAsDouble(),
                    data.get("description").getAsString()
            );
        }
        else if (classname.equals("OutgoingTransfer"))
        {
            return new OutgoingTransfer(
                    data.get("sender").getAsString(),
                    data.get("recipient").getAsString(),
                    data.get("date").getAsString(),
                    data.get("amount").getAsDouble(),
                    data.get("description").getAsString()

            );
        }
        else
        {
            return new Payment(
                    data.get("incomingInterest").getAsDouble(),
                    data.get("outgoingInterest").getAsDouble(),
                    data.get("date").getAsString(),
                    data.get("amount").getAsDouble(),
                    data.get("description").getAsString()

            );
        }
    }
}
