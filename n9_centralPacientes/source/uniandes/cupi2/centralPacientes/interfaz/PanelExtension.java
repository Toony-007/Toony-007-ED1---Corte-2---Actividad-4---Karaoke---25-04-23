/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelExtension.java,v 1.4 2006/08/17 15:51:54 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_centralPacientes
 * Autor: Daniel Francisco Romero - 11-jul-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.centralPacientes.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel de manejo de extensiones
 */
public class PanelExtension extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando Opci�n 1
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando Opci�n 2
     */
    private static final String OPCION_2 = "OPCION_2";

    /**
     * Comando Opci�n 3
     */
    private static final String OPCION_3 = "OPCION_3";

    /**
     * Comando Opci�n 4
     */
    private static final String OPCION_4 = "OPCION_4";

    /**
     * Comando Opci�n 5
     */
    private static final String OPCION_5 = "OPCION_5";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazCentralPacientes principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Bot�n Opci�n 1
     */
    private JButton btnOpcion1;

    /**
     * Bot�n Opci�n 2
     */
    private JButton btnOpcion2;

    /**
     * Bot�n Opci�n 3
     */
    private JButton btnOpcion3;

    /**
     * Bot�n Opci�n 4
     */
    private JButton btnOpcion4;

    /**
     * Bot�n Opci�n 5
     */
    private JButton btnOpcion5;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param ventana Ventana principal
     */
    public PanelExtension( InterfazCentralPacientes ventana )
    {
        principal = ventana;

        setBorder( new TitledBorder( "Opciones" ) );
        setLayout( new GridBagLayout( ) );

        // Bot�n opci�n 1
        btnOpcion1 = new JButton( "Cantidad Pacientes." );
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets( 0, 0, 0, 5 );
        add( btnOpcion1, gbc );

        // Bot�n opci�n 2
        btnOpcion2 = new JButton( "Existen pacientes con gripa." );
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        gbc = new GridBagConstraints( );
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets( 0, 0, 0, 5 );
        add( btnOpcion2, gbc );

        // Bot�n opci�n 3
        btnOpcion3 = new JButton( "Pacientes mujeres con Covid." );
        btnOpcion3.setActionCommand( OPCION_3 );
        btnOpcion3.addActionListener( this );
        gbc = new GridBagConstraints( );
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets( 0, 0, 0, 5 );
        add( btnOpcion3, gbc );

        // Bot�n opci�n 4
        btnOpcion4 = new JButton( "Existen pacientes en la clinica." );
        btnOpcion4.setActionCommand( OPCION_4 );
        btnOpcion4.addActionListener( this );
        gbc = new GridBagConstraints( );
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.insets = new Insets( 0, 0, 0, 5 );
        add( btnOpcion4, gbc );

        // Bot�n opci�n 5
        btnOpcion5 = new JButton( "Opci�n 5" );
        btnOpcion5.setActionCommand( OPCION_5 );
        btnOpcion5.addActionListener( this );
        gbc = new GridBagConstraints( );
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.insets = new Insets( 0, 0, 0, 5 );
        add( btnOpcion5, gbc );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param e Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if( OPCION_1.equals( e.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion1( );
        }
        else if( OPCION_2.equals( e.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion2( );
        }
        else if( OPCION_3.equals( e.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion3( );
        }
        else if( OPCION_4.equals( e.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion4( );
        }
        else if( OPCION_5.equals( e.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion5( );
        }

    }

}
