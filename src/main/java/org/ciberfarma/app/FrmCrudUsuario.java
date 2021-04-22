package org.ciberfarma.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.ciberfarma.modelo.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class FrmCrudUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCodigo;
	private JTextArea txtS;
	private JTextField txtUsuario;
	private JTextField txtClave;
	private JTextField txtfna;
	private JTextField txtTipo;
	private JTextField txtEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudUsuario frame = new FrmCrudUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmCrudUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrar();
			}
		});
		btnRegistrar.setBounds(203, 7, 89, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}

			
		});
		btnBuscar.setBounds(306, 11, 89, 23);
		contentPane.add(btnBuscar);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(85, 44, 86, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("codigo");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(10, 47, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(85, 79, 86, 20);
		contentPane.add(txtApellido);
		
		
		JLabel lblNewLabel_2 = new JLabel("Apellido");
		lblNewLabel_2.setBounds(10, 82, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(85, 8, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 219, 385, 123);
		contentPane.add(scrollPane);
		
		txtS = new JTextArea();
		scrollPane.setViewportView(txtS);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}

			 
		});
		btnListado.setBounds(203, 43, 89, 23);
		contentPane.add(btnListado);
		
		JLabel lblNewLabel_3 = new JLabel("Usuario");
		lblNewLabel_3.setBounds(10, 118, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(85, 115, 86, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Clave");
		lblNewLabel_4.setBounds(10, 155, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		txtClave = new JTextField();
		txtClave.setBounds(85, 146, 86, 20);
		contentPane.add(txtClave);
		txtClave.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("fnacim");
		lblNewLabel_5.setBounds(10, 180, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Tipo");
		lblNewLabel_6.setBounds(227, 155, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Estado");
		lblNewLabel_7.setBounds(227, 180, 46, 14);
		contentPane.add(lblNewLabel_7);
		
		txtfna = new JTextField();
		txtfna.setBounds(85, 177, 86, 20);
		contentPane.add(txtfna);
		txtfna.setColumns(10);
		
		txtTipo = new JTextField();
		txtTipo.setBounds(283, 152, 86, 20);
		contentPane.add(txtTipo);
		txtTipo.setColumns(10);
		
		txtEstado = new JTextField();
		txtEstado.setBounds(283, 177, 86, 20);
		contentPane.add(txtEstado);
		txtEstado.setColumns(10);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Actualizar();
				
			}
		});
		btnActualizar.setBounds(306, 45, 89, 23);
		contentPane.add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Eliminar();
			}
		});
		btnEliminar.setBounds(203, 78, 89, 23);
		contentPane.add(btnEliminar);
		
		
		
	}
	  void Eliminar() {
		  int codigo = leerCodigo();
			
		  
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa_sesion01");
			EntityManager em = factory.createEntityManager();
			
			try {
			Usuario p1 = em.find(Usuario.class, codigo);
			em.getTransaction().begin();
			
			em.remove(p1);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error al eliminar" + e.getMessage());
		}
		
	}

	void Actualizar() {
		  Usuario u = new Usuario();
			//u.setCodigo(10); auto_increment .. ya lo tienes en tu tabla
			u.setNombre(leerNombre());
			u.setApellido(leerApellido());
			u.setUsuario(leerUsuario());
			u.setClave(leerClave());
			u.setFnacim(leerFnacim());
			u.setTipo(leerTipo());
			u.setEstado(leerEstado());
			
			// grabar el objeto
			// 1. fabricar el acceso a los datos
			EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
			// 2. crear el manejador de entidades
			EntityManager em = fabrica.createEntityManager();
			
			// 3. empezar mi transaccion
			em.getTransaction().begin();
			// proceso a realizar (persistencia)
			//em.persist(u);
			em.merge(u);
			// 4. confirmar la transacci�n
			em.getTransaction().commit();
			System.out.println("Registro agregado");
			em.close();
		
	}

	void listado() {
		// obtener un listado del contenido
			
		 // pasar el listado a txt,..
		 EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		 EntityManager em = fabrica.createEntityManager();
		 
		 //TypedQuery<Usuario> consulta = em.createNamedQuery("Usuario.findAll", Usuario.class);
		 //List<Usuario> lstUsuarios = consulta.getResultList();
		TypedQuery<Usuario> consulta = em.createNamedQuery("Usuario.findAll", Usuario.class);
		//consulta.setParameter("xtipo", 1); 
		List<Usuario> lstUsuarios = consulta.getResultList();
		 em.close();
		 for (Usuario u : lstUsuarios) {
			 txtS.append(u.getCodigo()+ "\t" +u.getNombre() + "\t" + u.getApellido() + "\n");
			
		}
		
	}

	 void registrar() {
		 
		 Usuario u = new Usuario();
			//u.setCodigo(10); auto_increment .. ya lo tienes en tu tabla
			u.setNombre(leerNombre());
			u.setApellido(leerApellido());
			u.setUsuario(leerUsuario());
			u.setClave(leerClave());
			u.setFnacim(leerFnacim());
			u.setTipo(leerTipo());
			u.setEstado(leerEstado());
			
			// grabar el objeto
			// 1. fabricar el acceso a los datos
			EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
			// 2. crear el manejador de entidades
			EntityManager em = fabrica.createEntityManager();
			
			// 3. empezar mi transaccion
			em.getTransaction().begin();
			// proceso a realizar (persistencia)
			em.persist(u);
			//em.merge(u);
			// 4. confirmar la transacci�n
			em.getTransaction().commit();
			System.out.println("Registro agregado");
			em.close();
		
		
	}

	
	

	private int leerEstado() {
		
		return Integer.parseInt(txtEstado.getText());
	}

	private int leerTipo() {
		// TODO Auto-generated method stub
		return Integer.parseInt(txtTipo.getText());
	}

	private String leerFnacim() {
		// TODO Auto-generated method stub
		return txtfna.getText();
	}

	private String leerClave() {
		// TODO Auto-generated method stub
		return txtClave.getText();
	}

	private String leerUsuario() {
		// TODO Auto-generated method stub
		return txtUsuario.getText();
	}

	void buscar() {
		 // leer el codigo
		 int codigo = leerCodigo(); 
		 // buscar en la tabla, para obtener un usuario
		 EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		 EntityManager em = fabrica.createEntityManager();
		
		 Usuario u = em.find(Usuario.class, codigo);
		 em.close();
		 // si existe lo muestra en los campos, sino avisa
		 if( u == null) {
			 aviso("Usuario " + codigo + " No existe!!!");
			 
		 } else {
			 txtNombre.setText(u.getNombre());
			 txtApellido.setText(u.getApellido());
			 
		 }
		 
		}

	private void aviso(String string) {
		JOptionPane.showMessageDialog(this, string,"Aviso del sistema", JOptionPane.WARNING_MESSAGE);
		
		
	}
	private String leerNombre() {
		
		return txtNombre.getText();
	}


	private int leerCodigo() {
		
		return Integer.parseInt(txtCodigo.getText());
	}
	private String leerApellido() {
		
		return txtApellido.getText();
	}
}
