package visao.menu;



import controle.UserLog;
import visao.principal.TelaPrincipal;
//import visao.telas.TelaAgenciaConsulta;

public class MenuPrincipal extends javax.swing.JPanel {
	private static final long serialVersionUID = 23;
	
	private javax.swing.JMenuItem agenciaAdicionar;
    private javax.swing.JMenuItem agenciaConsulta;
    private javax.swing.JMenuItem arquivoConsulta;
    private javax.swing.JMenuItem arquivoEnviar;
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JMenu menuAgencias;
    private javax.swing.JMenu menuArquivos;
    private javax.swing.JMenuItem sair;
    private javax.swing.JMenu sairMenu;
    private javax.swing.JMenuItem usuarioCadastro;
    private javax.swing.JMenuItem usuarioConsulta;
    private javax.swing.JMenuItem usuarioPerfil;
    private javax.swing.JMenu usuariosMenu;
    UserLog user = new UserLog();
    
    private static TelaPrincipal refTelaPrincipal;
    
    public void root() {
    
    	boolean user = UserLog.userRoot;

		usuarioCadastro.setEnabled(user);
		agenciaAdicionar.setEnabled(user);
		usuarioConsulta.setEnabled(user);
    		
    }
    
    public MenuPrincipal(TelaPrincipal telaPrincipal) {
    	construirMenu(); 
    	root();
    	refTelaPrincipal = telaPrincipal;
    	java.awt.EventQueue.invokeLater(new Runnable(){
    		public void run(){
    			refTelaPrincipal.repaint();
    			refTelaPrincipal.revalidate();
    		}
    	});
    }
    public javax.swing.JMenuBar getJMenuBar() {	
    	return this.barraMenu;
    }
	
    public static void arquivoEnviar(){
    	refTelaPrincipal.construirTelaArquivoEnviar();
    	refTelaPrincipal.exibirTelaArquivoEnviar();
    }
    public static void arquivoConsulta() {
      	refTelaPrincipal.construirTelaArquivoConsulta();
    	refTelaPrincipal.exibirTelaArquivoConsulta();
    }
    
    public static void agenciaCadastrar() {
    	refTelaPrincipal.construirTelaAgenciaCadastrar();
        refTelaPrincipal.exibirTelaAgenciaCadastrar();
    }
    
    public static void agenciaConsulta() {
    	refTelaPrincipal.construirTelaAgenciaConsulta();
        refTelaPrincipal.exibirTelaAgenciaConsulta();
    }
    
    public static void usuarioPerfil() {
    	refTelaPrincipal.construirTelaUsuarioPerfil();
        refTelaPrincipal.exibirTelaUsuarioPerfil();
    }
    
    public static void usuarioPerfil(String var) {
    	refTelaPrincipal.construirTelaUsuarioPerfil(var);
        refTelaPrincipal.exibirTelaUsuarioPerfil(var);
    }
    
    public static void usuarioConsulta() {
    	refTelaPrincipal.construirTelaUsuarioConsulta();
        refTelaPrincipal.exibirTelaUsuarioConsulta();
    }
    
    public static void usuarioCadastar() {
    	refTelaPrincipal.construirTelaUsuarioCadastrar();
        refTelaPrincipal.exibirTelaUsuarioCadastrar();
    }
    
	public void construirMenu() {
		barraMenu = new javax.swing.JMenuBar();
        menuArquivos = new javax.swing.JMenu();
        arquivoEnviar = new javax.swing.JMenuItem();
        arquivoConsulta = new javax.swing.JMenuItem();
        menuAgencias = new javax.swing.JMenu();
        agenciaAdicionar = new javax.swing.JMenuItem();
        agenciaConsulta = new javax.swing.JMenuItem();
        usuariosMenu = new javax.swing.JMenu();
        usuarioPerfil = new javax.swing.JMenuItem();
        usuarioCadastro = new javax.swing.JMenuItem();
        usuarioConsulta = new javax.swing.JMenuItem();
        sairMenu = new javax.swing.JMenu();
        sair = new javax.swing.JMenuItem();
         
        arquivoEnviar.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		arquivoEnviar();
            }
        });
        
        arquivoConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	arquivoConsulta();
            }
        });
        
        agenciaAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	agenciaCadastrar();
            }
        });
        
        
        agenciaConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	agenciaConsulta();
            }
        });
        
        usuarioPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	usuarioPerfil();
            }
        });
        
        usuarioConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	usuarioConsulta();
            }
        });
        
        usuarioCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	usuarioCadastar();
            }
        });
        
        sair.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            System.exit(0);
         }
     	});
        
        agenciaConsulta.setText("Consultar");
        
        menuArquivos.setText("Arquivos");

        arquivoEnviar.setText("Enviar"); 

        menuArquivos.add(arquivoEnviar);

        arquivoConsulta.setText("Consultar");
        
        menuArquivos.add(arquivoConsulta);

        barraMenu.add(menuArquivos);

        menuAgencias.setText("Agencias e Regionais");

        agenciaAdicionar.setText("Adicionar");    
        
        menuAgencias.add(agenciaConsulta);

        menuAgencias.add(agenciaAdicionar);
        
        

        barraMenu.add(menuAgencias);

        usuariosMenu.setText("Usuarios");

        usuarioPerfil.setText("Meu Perfil");

        usuariosMenu.add(usuarioPerfil);

        usuarioCadastro.setText("Cadastrar");
        usuariosMenu.add(usuarioCadastro);

        usuarioConsulta.setText("Consultar");

        usuariosMenu.add(usuarioConsulta);

        barraMenu.add(usuariosMenu);

        sairMenu.setText("Sair");

        sair.setText("Sair");
        
        sairMenu.add(sair);
        
        barraMenu.add(sairMenu);
        
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }
	
	
}
