import bank.*;
import bank.exceptions.AccountAlreadyExistsException;
import bank.exceptions.AccountDoesNotExistException;
import bank.exceptions.TransactionAlreadyExistException;
import bank.exceptions.TransactionDoesNotExistException;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Mainview");
        VBox parent= new VBox();
        Label label=new Label("testing");
        //parent.getChildren().add(label);
        Scene sc=new Scene(parent);

        stage.setScene(sc);
        stage.show();
    }

    public static void main(String[] args) throws IOException, AccountAlreadyExistsException, TransactionAlreadyExistException, AccountDoesNotExistException, TransactionDoesNotExistException {



        /*PrivateBank pBank1 = new PrivateBank("private_bank_1", 0.1,0.1, "Aachener_Bank");
        PrivateBank pBank2 = new PrivateBank("private_bank_2", 0.5, 0.5, "BBBank");


            pBank2.createAccount("Hassen");
            pBank2.createAccount("Dali");
            //pBank1.createAccount("Enno");
            IncomingTransfer it1 = new IncomingTransfer("Hassen", "Dali", "04.12.21", 100, "description_1");
            OutgoingTransfer ot1 = new OutgoingTransfer("Dali", "Hassen", "05.12.21", 100, "description_2");
            Payment p1 = new Payment(0.1, 0.1, "03.12.21", -100,"payment_1");
            Payment p2 = new Payment(0.1, 0.1, "02.12.21", 100, "payment_2");
            pBank2.addTransaction("Hassen", it1);
            pBank2.addTransaction("Hassen", ot1);
            pBank2.addTransaction("Hassen", p1);
            pBank2.addTransaction("Hassen", p2);
            pBank2.removeTransaction("Hassen", p1);
            pBank2.addTransaction("Dali", p1);
            pBank2.addTransaction("Dali", ot1);
            System.out.println(pBank2.getTransactions("Hassen"));




        System.out.println(pBank1.getTransactions("Hassen"));

        System.out.println(pBank2.getAllAccounts());*/
        launch();






    }


}


