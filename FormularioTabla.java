
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class FormularioTabla extends JFrame implements ActionListener {

	JFrame ventana;
	private JFrame f;
	private DefaultTableModel model;
	private JTable jt;
	private JLabel mensaje2,mensaje3,mensaje4;
	private JTextField resultadoA,resultadoB,resultadoC;
	private JButton editar,eliminar,agregar;
	
	
	public FormularioTabla() {
		ventana = new JFrame();
		String data[][] = { {"1","Tacos","$12.00"}, {"2","Tamales","$10.00"}, {"3","Tortas","$35.00"}, {"4","Cochito","$45.00"}   };
		String label[] = {"ID", "NOMBRE", "PRECIO"};
		//JTable jt = new JTable(data,label);
		
		model =   new DefaultTableModel(data,label);
		jt = new JTable(model);
		
		//model.addRow(new Object[] {"5", "Pozol", "$18.00"});
		
		mensaje2 = new JLabel("Agregar Nombre:");
        mensaje2.setBounds(10, 490, 100, 60);
        mensaje2.setForeground(Color.white);
        ventana.add(mensaje2);
        
        resultadoA = new JTextField();
        resultadoA.setBounds(120, 500, 200, 40);
        //resultadoA.setEditable(false);
        ventana.add(resultadoA);
        
        mensaje3 = new JLabel("Agregar Precio:");
        mensaje3.setBounds(10, 540, 100, 60);
        mensaje3.setForeground(Color.white);
        ventana.add(mensaje3);
        
        mensaje4 = new JLabel("Valor seleccionado a editar:");
        mensaje4.setBounds(120, 450, 200, 60);
        mensaje4.setForeground(Color.white);
        ventana.add(mensaje4);
        
        
        resultadoB = new JTextField();
        resultadoB.setBounds(120, 550, 100, 40);
        //resultadoA.setEditable(false);
        ventana.add(resultadoB);
        
        resultadoC = new JTextField();
        resultadoC.setBounds(460, 500, 200, 40);
        //resultadoA.setEditable(false);
        ventana.add(resultadoC);
        
        editar = new JButton();
        editar.setText("editar");
        editar.setBounds(350, 500, 100, 40);
        editar.addActionListener(this);
        ventana.add(editar);
        
        eliminar = new JButton();
        eliminar.setText("eliminar");
        eliminar.setBounds(350, 550, 100, 40);
        eliminar.addActionListener(this);
        ventana.add(eliminar);
        
        agregar = new JButton();
        agregar.setText("agregar");
        agregar.setBounds(120, 600, 120, 25);
        agregar.addActionListener(this);
        ventana.add(agregar);
		
		jt.setCellSelectionEnabled(true);
		ListSelectionModel select = jt.getSelectionModel();
		select.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {
			
				
			}
	
		} );
		eliminar.setEnabled(false);
		JScrollPane sp = new JScrollPane(jt); 
		sp.setBounds(10, 20, 500, 400);
		ventana.add(sp);
		ventana.setSize(700,720);
		ventana.setLocationRelativeTo(null);
        ventana.setResizable(true);
        ventana.setLayout(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.getContentPane().setBackground(new java.awt.Color(141,18,47));
		ventana.setVisible(true);
		
		
	}
	
	@Override
 	   public void actionPerformed(ActionEvent e) { 
		String Data = null;
		int valor = 0;
		int celda = 0;
		 
		int []row = jt.getSelectedRows(); // selecciona la posicion de la fila
		int[] columns = jt.getSelectedColumns();
		for(int i = 0; i< row.length; i++) {
			for(int j = 0; j < columns.length;j++) {
				Data = (String) jt.getValueAt(row[i], columns[j]);
				celda = columns[j];
			}
			valor = row[i];
			
		}
		eliminar.setEnabled(true);
			//System.out.println("FILA:" + valor);
			//System.out.println("COLUMNA:" + celda);
 		switch(e.getActionCommand()) {
 		   case "eliminar":
 			  if(model.getRowCount() > 0) {  //Verifica que tiene elementos y elimina
 					model.removeRow(valor);
 				}
 			  
 	     break;
 	     
 		   case "editar":
 			  //if (!jt.isEditing() && jt.editCellAt(jt.getSelectedRow(), 
                      //jt.getSelectedColumn())) {
 			   
 				  	  mensaje4.setText(Data);
 				  	  if(resultadoC.getText().equals(null)) {
 				  		  resultadoC.setText(Data);
 				  	  }else {
 				  	  jt.setValueAt(resultadoC.getText(),valor, celda);
 				  	  }
                  //jt.getEditorComponent().requestFocusInWindow();  // obligamos que la celda reciba el foco
             // } 
 			   break;
 			   
 		   case "agregar":
 			  eliminar.setEnabled(false);
 			  int id = model.getRowCount()+1;
 			  String valorId = String.valueOf(id);
 			  String resultado = resultadoA.getText();
 		      String resultado2 = "$"+resultadoB.getText()+".00";
 		      addRow(valorId,resultado,resultado2);
 			  break;

 		   }
 	   };
 	   
 	
	public void addRow(String str1, String str2, String str3) {
		model.addRow(new Object[] {str1,str2,str3}); //Añade nuevos elementos

		//model.insertRow(4,  new Object[] {"1", "Tacos", "$15.00"});  //Insertar nuevo dato
	}
	
	
	
	public static void main(String[] args){
		FormularioTabla i = new FormularioTabla();
        i.addRow("5", "Gordita", "$16.00");
        
        
    }
}