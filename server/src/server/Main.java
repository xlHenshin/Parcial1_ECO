package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;

import processing.core.PApplet;

public class Main extends PApplet {

	private String recordatorio, colorGlobal;
	private ArrayList <Recordatorio> recordatorioLista;
	private boolean viewConfirmar;
	private Recordatorio visualizacion;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main(Main.class.getName());
	}
	
	public void settings() {
		size(700, 500);
	}
	
	public void setup() {
		
		recordatorioLista = new ArrayList<Recordatorio>();
		
		new Thread(() -> {
			try {
				ServerSocket server = new ServerSocket(5000);
				System.out.println("Esperando usuario...");
				Socket socket = server.accept();
				System.out.println("El usuario esta conectado");

				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();

				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader breader = new BufferedReader(isr);

				while (true) {

					System.out.println("Esperando recordatorio...");
					String mensajeRecibido = breader.readLine();
					

					Gson gson = new Gson();

					recordatorio = gson.fromJson(mensajeRecibido, String.class);
					
					organizarDatos();

				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
		
	}
	
	public void draw() {
		
		background(255,255,255);
		
		rectMode(CENTER);
		textAlign(CENTER);
		textSize(10);
		
		
		
		for (int i = 0; i < recordatorioLista.size(); i++) {
			
			
			recordatorioLista.get(i).pintarDatos();
		}
		
		if (viewConfirmar) {
			
			visualizacion.pintarDatos();
		}
		
	}
	
	public void organizarDatos() {
		
		String [] datosLista = recordatorio.split(",");
		
		String color = datosLista[0];
		System.out.println("El color es: "+color);
		colorGlobal = datosLista[0];
		int posX = parseInt(datosLista[1]);
		int posY = parseInt(datosLista[2]);
		String texto = datosLista[3];
		String confirmar= datosLista[4];
		
		if (confirmar.equals("si")) {
			recordatorioLista.add(new Recordatorio(color, posX, posY, texto, this));
			System.out.println("Guardado");
		}else {
			visualizacion = (new Recordatorio(color, posX, posY, texto, this));
			viewConfirmar=true;
			System.out.println("Previzualizando");
		}
		
		
	}
	
}
