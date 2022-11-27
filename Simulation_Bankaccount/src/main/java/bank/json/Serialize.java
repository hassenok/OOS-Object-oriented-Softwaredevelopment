package bank.json;

import bank.IncomingTransfer;
import bank.OutgoingTransfer;
import bank.Payment;
import bank.Transaction;
import com.google.gson.*;

import java.lang.reflect.Type;
/**
 * @author Hassen Trabelsi
 * @version 1.0
 * class Serialize
 */
public class Serialize implements JsonSerializer<Transaction> {
    /**
     * Method for serializing a Transaction object.
     * @param transaction serialization Object
     * @param type type of the Object
     * @param jsonSerializationContext further Configuration
     * @return Json Object
     */
    @Override
    public JsonElement serialize(Transaction transaction, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        JsonObject jsonInstance = new JsonObject();

        if (transaction instanceof IncomingTransfer)
        {
            IncomingTransfer incomingTransfer = (IncomingTransfer) transaction;
            jsonObject.addProperty("CLASSNAME", "IncomingTransfer");
            jsonInstance.addProperty("date", incomingTransfer.getDate());
            jsonInstance.addProperty("amount", incomingTransfer.getAmount());
            jsonInstance.addProperty("description", incomingTransfer.getDescription());
            jsonInstance.addProperty("sender", incomingTransfer.getSender());
            jsonInstance.addProperty("recipient", incomingTransfer.getRecipient());
            jsonObject.add("INSTANCE", jsonInstance);
        }
        else if (transaction instanceof OutgoingTransfer)
        {
            OutgoingTransfer outgoingTransfer = (OutgoingTransfer) transaction;
            jsonObject.addProperty("CLASSNAME", "OutgoingTransfer");
            jsonInstance.addProperty("date", outgoingTransfer.getDate());
            jsonInstance.addProperty("amount", outgoingTransfer.getAmount());
            jsonInstance.addProperty("description", outgoingTransfer.getDescription());
            jsonInstance.addProperty("sender", outgoingTransfer.getSender());
            jsonInstance.addProperty("recipient", outgoingTransfer.getRecipient());
            jsonObject.add("INSTANCE", jsonInstance);
        }
        else if (transaction instanceof Payment)
        {
            Payment payment = (Payment) transaction;
            jsonObject.addProperty("CLASSNAME", "Payment");
            jsonInstance.addProperty("date", payment.getDate());
            jsonInstance.addProperty("amount", payment.getAmount());
            jsonInstance.addProperty("description", payment.getDescription());
            jsonInstance.addProperty("incomingInterest", payment.getIncomingInterest());
            jsonInstance.addProperty("outgoingInterest", payment.getOutgoingInterest());
            jsonObject.add("INSTANCE", jsonInstance);
        }
        return jsonObject;
    }



}
