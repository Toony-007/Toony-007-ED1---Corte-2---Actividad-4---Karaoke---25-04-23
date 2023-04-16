/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: DialogoOpcionesInsertar.java,v 1.7 2009/03/04 13:48:48 ju-cort1 Exp $ 
 * Universidad de los Andes (Bogotá - Colombia) 
 * Departamento de Ingeniería de Sistemas y Computación 
 * Todos los derechos reservados 2005 
 * 
 * Proyecto Cupi2 
 * Ejercicio: n9_centralPacientes
 * Autor: Daniel Romero- Julio 7/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.centralPacientes.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Diálogo con las opciones para realizar la inserción de un paciente
 */
public class DialogoOpcionesInsertar extends JDialog
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Referencia a la ventana principal de la aplicación
     */
    private InterfazCentralPacientes principal;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con las opciones
     */
    private PanelOpcionesInsertar panelOpciones;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del diálogo
     * @param ventana Referencia a la ventana principal de la aplicación - ventana!=null
     */
    public DialogoOpcionesInsertar( InterfazCentralPacientes ventana )
    {
        super( ventana, true );
        principal = ventana;
        setLayout( new BorderLayout( ) );
        setResizable( false );
        setTitle( "Central de Pacientes" );
        setPreferredSize( new Dimension( 339, 197 ) );

        // Panel con las opciones
        panelOpciones = new PanelOpcionesInsertar( this );
        add( panelOpciones, BorderLayout.NORTH );

        pack( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Cancela la adición del paciente cerrando el diálogo
     */
    public void cancelar( )
    {
        dispose( );
    }

    /**
     * Muestra el diálogo para proseguir con la adición del paciente
     */
    public void continuarAdicionPaciente( )
    {
        try
        {
            int forma = panelOpciones.darFormaInsercion( );
            int codigo = panelOpciones.darCodigoPaciente( );
            dispose( );
            principal.mostrarDialogoInsertarPaciente( forma, codigo );
        }
        catch( NumberFormatException e )
        {
            JOptionPane.showMessageDialog( this, "El código del paciente con relación al cual se va a realizar la adición debe ser un valor numérico", "Adición Pacientes", JOptionPane.ERROR_MESSAGE );
        }
    }

}
