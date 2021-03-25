package com.example.parcial1_dca;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private Button vistaBtn, confirmarBtn, verde, amarillo, rojo;
    private EditText posX, posY, recordatorio;
    private BufferedWriter bwriter;
    private String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vistaBtn=findViewById(R.id.vista);
        confirmarBtn=findViewById(R.id.confirmar);
        verde=findViewById(R.id.verde);
        amarillo=findViewById(R.id.amarillo);
        rojo=findViewById(R.id.rojo);
        posX=findViewById(R.id.posX);
        posY=findViewById(R.id.posY);
        recordatorio=findViewById(R.id.recordatorio);

        new Thread(

                ()->{

                    try {
                        Socket socket = new Socket("192.168.1.5", 5000);

                        OutputStream os = socket.getOutputStream();
                        OutputStreamWriter osw = new OutputStreamWriter(os);
                        bwriter = new BufferedWriter(osw);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

        ).start();

        verdeButton();
        amarilloButton();
        rojoButton();
        vistaButton();
        continuarButton();
    }

    private void verdeButton(){
        verde.setOnClickListener(
                v->{
                    color="verde";
                }
        );
    }

    private void amarilloButton(){
        amarillo.setOnClickListener(
                v->{
                    color="amarillo";
                }
        );
    }

    private void rojoButton(){
        rojo.setOnClickListener(
                v->{
                    color="amarillo";
                }
        );
    }

    private void vistaButton(){
        vistaBtn.setOnClickListener(
                v->{
                    new Thread(

                            ()->{

                                String posx, posy, recor, data;
                                posx= posX.getText().toString();
                                posy= posY.getText().toString();
                                recor= recordatorio.getText().toString();
                                data= color+"," + posx +"," + posy + "," + recor;

                                Log.e("Recordatorio= ", data);
                                Gson gson = new Gson();

                                String reJson = gson.toJson(data);

                                try {
                                    bwriter.write(reJson+"\n");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    bwriter.flush();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                posX.setText("");
                                posY.setText("");
                                recordatorio.setText("");
                                color=null;
                            }
                    ).start();
                }
        );
    }

    private void continuarButton(){

        confirmarBtn.setOnClickListener(
                v->{
                    new Thread(

                            ()->{

                                String posx, posy, recor, data;
                                posx= posX.getText().toString();
                                posy= posY.getText().toString();
                                recor= recordatorio.getText().toString();
                                data= color+"," + posx +"," + posy + "," + recor;

                                Log.e("Recordatorio= ", data);
                                Gson gson = new Gson();

                                String reJson = gson.toJson(data);

                                try {
                                    bwriter.write(reJson+"\n");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    bwriter.flush();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                posX.setText("");
                                posY.setText("");
                                recordatorio.setText("");
                                color=null;
                            }
                    ).start();
                }
        );

    }

}