package GUI;

import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import Exceptions.*;
import Programa.Logica;

import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;

/**
 * Clase que modela una interfaz grafica.
 * @author Arroyo Tomas (126078) - Alconcher Julian (126094)
 *
 */
public class GUI {

	JFrame ProyectoEDD2022;
	
	private JTextField textCrearArbol;
	private JTextField textAgregarNodoParent,textEliminarNodo,textObtenerCamino,textGradoK,textAgregarNodoTag;
	
	private JTextArea textArea,textAreaNotifications;
	
	private Logica main;
	
	private JButton btnAgregarNodo,btnCrearArbol,btnEliminarNodo,btnObtenerGrados,btnRecorridoPostOrden;
	private JButton btnRecorridoPreOrden,btnEliminarNodosK,btnGradoArbol,btnObtenerCamino,btnRecorridoPorNiveles;
	
	private JLabel lblIngresePadre,lblIngreseTag;
	
	private JCheckBox checkBoxMantenerPadre;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	
	
	/**
	 * Creo aplicacion
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Inicializamos los contenidos
	 */
	private void initialize() {
		ProyectoEDD2022 = new JFrame();
		ProyectoEDD2022.setResizable(false);
		ProyectoEDD2022.setBounds(100, 100, 702, 414);
		ProyectoEDD2022.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ProyectoEDD2022.setTitle("Tree Simulator");
		ProyectoEDD2022.setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("logo.png")));
		
		//BOTONES
		
		//Boton crear arbol
		btnCrearArbol = new JButton("Crear Arbol");
		btnCrearArbol.setBounds(10, 152, 318, 27);
		btnCrearArbol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tag = textCrearArbol.getText();
				textCrearArbol.setText("");
				if(tag.length()==0)
					textAreaNotifications.setText("Debe ingresar el rotulo de la raiz");
				else if(tag.length()>1)
					textAreaNotifications.setText("Debe ingresar un solo caracter");
				else {				
					Character tagRoot = tag.charAt(0);
					main = new Logica(tagRoot);
					textAreaNotifications.setText("Arbol creado, con raiz: " + tagRoot);
					inicializateButtons(true);
					textArea.setText(main.printTree());
					textCrearArbol.setText("");
				}
				
			}
		});
		/**
		 * Boton que al presionar, crea un nuevo arbol con raiz
		 * Si las opciones ingresadas son invalidas o inexistentes se mostraran los carteles en rojo.
		 */
		btnAgregarNodo = new JButton("Agregar Nodo");
		btnAgregarNodo.setBounds(10, 8, 152, 27);
		btnAgregarNodo.setVisible(false);
		btnAgregarNodo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String parent = textAgregarNodoParent.getText();
			String tag = textAgregarNodoTag.getText();
			boolean checked = checkBoxMantenerPadre.isSelected();
			if(parent.length()==0) {
				textAreaNotifications.setText("Debe ingresar el padre del nodo a ingresar");
				lblIngresePadre.setForeground(new Color(255, 0, 0));
			}
			else if(tag.length()==0){
				textAreaNotifications.setText("Debe ingresar el rotulo del nodo a agregar");
				lblIngresePadre.setForeground(new Color(0,0,0));
				lblIngreseTag.setForeground(new Color(255,0,0));
			}
			else {	
				Character parentChar = parent.charAt(0);
				Character tagChar = tag.charAt(0);
				try {
					if(main.addNode(parentChar, tagChar)) {
						textAreaNotifications.setText("Nodo agregado con exito" );
						lblIngresePadre.setForeground(new Color(0,0,0));
						lblIngreseTag.setForeground(new Color(0,0,0));
						textArea.setText(main.printTree());
						if(!checked)
							textAgregarNodoParent.setText("");
						textAgregarNodoTag.setText("");
					}
					else {
						textAreaNotifications.setText("No se encontro el padre");
						lblIngresePadre.setForeground(new Color(255,0,0));
					}
				} catch (InvalidOperationException e1) {textAreaNotifications.setText(e1.getMessage());
				}
			}
			
		}});
		//Boton eliminar nodo
		btnEliminarNodo = new JButton("Eliminar Nodo");
		btnEliminarNodo.setBounds(10, 47, 318, 27);
		btnEliminarNodo.setVisible(false);
		btnEliminarNodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tag = textEliminarNodo.getText();
				if(tag.length()==0)
					textAreaNotifications.setText("Debe ingresar el rotulo del nodo a eliminar");
				else {	
					Character tagChar = tag.charAt(0);
					try {
						if(main.removeNode(tagChar)) {
							textAreaNotifications.setText("Nodo eliminado con exito");
							textArea.setText(main.printTree());
							textEliminarNodo.setText("");
						}
					}catch (Exceptions.InvalidPositionException e1) {textAreaNotifications.setText(e1.getMessage());}
				}
				if(main.size() == 0) {
					inicializateButtons(false);
					textAreaNotifications.setText("Al eliminar la raiz, el arbol se elimino. Ingrese nuevamente un nodo raiz");
				}
				
			}});
		
		//Boton obtener grados
		btnObtenerGrados = new JButton("Obtener Grados");
		btnObtenerGrados.setBounds(10, 269, 355, 27); 
		btnObtenerGrados.setVisible(false);
		btnObtenerGrados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					textAreaNotifications.setText(main.printGrades());
					
			} 
			
		});
		
		//Boton grado del arbol
		btnGradoArbol = new JButton("Obtener Grado de arbol");
		btnGradoArbol.setBounds(10, 343, 355, 27);
		btnGradoArbol.setVisible(false);
		btnGradoArbol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaNotifications.setText("El grado del arbol es: " + main.treeGrade());
			}
		});
		
		//Boton obtener camino
		btnObtenerCamino = new JButton("Obtener camino");
		btnObtenerCamino.setVisible(false);
		btnObtenerCamino.setBounds(10, 82, 318, 27);
		btnObtenerCamino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tag = textObtenerCamino.getText();
				if(tag.length()==0)
					textAreaNotifications.setText("Debe ingresar el rotulo del nodo");
				
				else {
					Character tagChar = tag.charAt(0);
					try {
							textAreaNotifications.setText(main.getPath(tagChar));
							textObtenerCamino.setText("");
					} catch (InvalidOperationException e1) {textAreaNotifications.setText(e1.getMessage());}
					textArea.setText(main.printTree());
				}
			}
		});
		//Boton obtener recorrido preorden
		btnRecorridoPreOrden = new JButton("Recorrido pre-orden");
		btnRecorridoPreOrden.setBounds(10, 195, 355, 27);
		btnRecorridoPreOrden.setVisible(false);
		btnRecorridoPreOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaNotifications.setText("");
				textAreaNotifications.setText(main.stringizer(main.preOrden()));
			}
			
		});
		
		//Boton Recorrido post orden
		btnRecorridoPostOrden = new JButton("Recorrido post-orden");
		btnRecorridoPostOrden.setBounds(10, 306, 355, 27);
		btnRecorridoPostOrden.setVisible(false);
		btnRecorridoPostOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaNotifications.setText("");
				textAreaNotifications.setText(main.stringizer(main.postOrden()));
			}
		});
		//Boton recorrido por niveles
		btnRecorridoPorNiveles = new JButton("Recorrido por niveles");
		btnRecorridoPorNiveles.setBounds(10, 232, 355, 27);
		btnRecorridoPorNiveles.setVisible(false);
		btnRecorridoPorNiveles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaNotifications.setText("");
				textAreaNotifications.setText(main.stringizer(main.recorridoPorNiveles()));
				
			}
		});
		//Boton eliminar nodos grado k
		btnEliminarNodosK = new JButton("Eliminar Nodos K");
		btnEliminarNodosK.setBounds(10, 117, 318, 27);
		btnEliminarNodosK.setVisible(false);
		btnEliminarNodosK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String tag = textGradoK.getText();
				if(tag.length()==0)
					textAreaNotifications.setText("Debe ingresar el grado 'K' que desea eliminar");
				else {
					try {
							textAreaNotifications.setText("Se han eliminado los siguientes nodos: " + main.removeOrdenK(Integer.parseInt(tag)));
							textArea.setText(main.printTree());
							textGradoK.setText("");
					}catch (Exceptions.InvalidPositionException e1) {textAreaNotifications.setText(e1.getMessage());
					}catch (InvalidKeyException e1) {textAreaNotifications.setText(e1.getMessage());
					}catch(NumberFormatException e13) {textAreaNotifications.setText("Debe ingresar un numero entero");
					}catch (InvalidOperationException e1) {textAreaNotifications.setText(e1.getMessage() + tag);}
				}
				if(main.size() == 0) {
					inicializateButtons(false);
					textAreaNotifications.setText("Al eliminar la raiz, el arbol se elimino. Ingrese nuevamente un nodo raiz");
				}
				
			}});
		
		
		//TEXT FIELDS 
		
		//Entrada de texto de crear arbol
		textCrearArbol = new JTextField();
		textCrearArbol.setToolTipText("Ingrese el rotulo del nodo raiz");
		//textCrearArbol.setText("Ingrese el rotulo de la raiz");
		textCrearArbol.setBounds(338, 152, 27, 27);
		ProyectoEDD2022.getContentPane().add(textCrearArbol);
		textCrearArbol.setColumns(10);
		//Entrada de texto de agregar nodo
		textAgregarNodoParent = new JTextField();
		textAgregarNodoParent.setForeground(Color.BLACK);
		textAgregarNodoParent.setToolTipText("Ingrese el rotulo del padre del nodo a insertar");
		textAgregarNodoParent.setVisible(false);
		textAgregarNodoParent.setBounds(226, 8, 27, 27);
		ProyectoEDD2022.getContentPane().add(textAgregarNodoParent);
		textAgregarNodoParent.setColumns(10);
		
		textAgregarNodoTag = new JTextField();
		textAgregarNodoTag.setToolTipText("Ingrese el rotulo del nodo a insertar");
		textAgregarNodoTag.setVisible(false);
		textAgregarNodoTag.setBounds(338, 8, 27, 27);
		ProyectoEDD2022.getContentPane().add(textAgregarNodoTag);
		textAgregarNodoTag.setColumns(10);
		//Rotulo del nodo a eliminar
		textEliminarNodo = new JTextField();
		textEliminarNodo.setToolTipText("Ingrese el nodo a eliminar\r\n");
		textEliminarNodo.setVisible(false);
		textEliminarNodo.setColumns(10);
		textEliminarNodo.setBounds(338, 47, 27, 27);
		ProyectoEDD2022.getContentPane().add(textEliminarNodo);
		//Rotulo del cual se va a obtener el camino
		textObtenerCamino = new JTextField();
		textObtenerCamino.setToolTipText("Ingrese el rotulo del nodo para obtener el camino");
		textObtenerCamino.setVisible(false);
		textObtenerCamino.setBounds(338, 82, 27, 27);
		ProyectoEDD2022.getContentPane().add(textObtenerCamino);
		textObtenerCamino.setColumns(10);
		//Grado K a eliminar
		textGradoK = new JTextField();
		textGradoK.setToolTipText("Ingrese el grado que desea eliminar");
		textGradoK.setVisible(false);
		textGradoK.setBounds(338, 117, 27, 27);
		ProyectoEDD2022.getContentPane().add(textGradoK);
		textGradoK.setColumns(10);
		//Impresion de nodos
		//Label padre
		lblIngresePadre = new JLabel("Padre:");
		lblIngresePadre.setVisible(false);
		lblIngresePadre.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblIngresePadre.setBounds(188, 15, 42, 13);
		//Label tag
		lblIngreseTag = new JLabel("Rotulo:");
		lblIngreseTag.setVisible(false);
		lblIngreseTag.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblIngreseTag.setBounds(286, 15, 42, 13);
		

		
		
		//AGREGO A EL PANEL
		ProyectoEDD2022.getContentPane().setLayout(null);
		ProyectoEDD2022.getContentPane().add(btnCrearArbol);
		ProyectoEDD2022.getContentPane().add(btnAgregarNodo);
		ProyectoEDD2022.getContentPane().add(btnEliminarNodo);
		ProyectoEDD2022.getContentPane().add(btnObtenerGrados);
		ProyectoEDD2022.getContentPane().add(btnGradoArbol);
		ProyectoEDD2022.getContentPane().add(btnObtenerCamino);
		ProyectoEDD2022.getContentPane().add(btnRecorridoPostOrden);
		ProyectoEDD2022.getContentPane().add(btnRecorridoPorNiveles);
		ProyectoEDD2022.getContentPane().add(btnRecorridoPreOrden);
		ProyectoEDD2022.getContentPane().add(btnEliminarNodosK);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(375, 84, 303, 286);
		ProyectoEDD2022.getContentPane().add(scrollPane);
		
		//SALIDA 
		//Area de salida
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Arial", Font.BOLD, 12));
		textArea.setToolTipText("Muestra del arbol en tiempo real");
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(375, 8, 303, 64);
		ProyectoEDD2022.getContentPane().add(scrollPane_1);
		
		//Salida de advertencias
		textAreaNotifications = new JTextArea();
		scrollPane_1.setViewportView(textAreaNotifications);
		textAreaNotifications.setToolTipText("Notificaciones");
		textAreaNotifications.setText("¡Bienvenido!");
		textAreaNotifications.setRows(1);
		textAreaNotifications.setLineWrap(true);
		textAreaNotifications.setFont(new Font("Arial", Font.PLAIN, 14));
		textAreaNotifications.setEditable(false);
		textAreaNotifications.setWrapStyleWord(true);
		ProyectoEDD2022.getContentPane().add(lblIngresePadre);
		ProyectoEDD2022.getContentPane().add(lblIngreseTag);
		
		checkBoxMantenerPadre = new JCheckBox("");
		checkBoxMantenerPadre.setToolTipText("Mantener padre");
		checkBoxMantenerPadre.setVisible(false);
		checkBoxMantenerPadre.setBounds(253, 11, 21, 21);
		ProyectoEDD2022.getContentPane().add(checkBoxMantenerPadre);

	}

	/**
	 * Inicializa los botones, una vez realizada la operacion de crear arbol, este boton se oculta, y los demas se hacen visibles.
	 * @param state
	 */
	private void inicializateButtons(boolean state) {
		btnCrearArbol.setVisible(!state);
		textCrearArbol.setVisible(!state);
		btnEliminarNodosK.setVisible(state);
		btnRecorridoPorNiveles.setVisible(state);
		btnAgregarNodo.setVisible(state);
		btnEliminarNodo.setVisible(state);
		btnObtenerGrados.setVisible(state);
		btnGradoArbol.setVisible(state);
		btnObtenerCamino.setVisible(state);
		btnRecorridoPreOrden.setVisible(state);
		btnRecorridoPostOrden.setVisible(state);
		btnRecorridoPorNiveles.setVisible(state);
		btnEliminarNodosK.setVisible(state);
		textAgregarNodoParent.setVisible(state);
		textAgregarNodoTag.setVisible(state);
		textEliminarNodo.setVisible(state);
		textObtenerCamino.setVisible(state);
		textGradoK.setVisible(state);
		lblIngresePadre.setVisible(state);
		lblIngreseTag.setVisible(state);
		checkBoxMantenerPadre.setVisible(state);
	}
}
