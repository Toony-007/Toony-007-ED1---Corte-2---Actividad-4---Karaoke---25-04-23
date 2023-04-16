/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CentralPacientesTest.java,v 1.10 2006/09/05 16:12:53 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_centralPacientes
 * Autor: Daniel Francisco Romero - 11-jul-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.centralPacientes.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import uniandes.cupi2.centralPacientes.mundo.CentralPacientes;
import uniandes.cupi2.centralPacientes.mundo.NoExisteException;
import uniandes.cupi2.centralPacientes.mundo.Paciente;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Hospital estén correctamente implementados
 */
public class CentralPacientesTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private CentralPacientes central;

    /**
     * El número de pacientes a manejar en cada prueba
     */
    private int numeroPacientes;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una nuevo Hospital vacío
     * 
     */
    private void setupEscenario1( )
    {
        central = new CentralPacientes( );
        numeroPacientes = 0;

    }

    /**
     * Construye una nuevo Hospital con 10 pacientes
     * 
     */
    private void setupEscenario2( )
    {
        central = new CentralPacientes( );
        numeroPacientes = 10;
        for( int cont = 0; cont < numeroPacientes; cont++ )
        {
            central.agregarPacienteAlComienzo( new Paciente( cont, "nombre" + cont, "clinica santa clara" + cont, "la historia" + cont, Paciente.HOMBRE ) );
        }
    }

    /**
     * Construye una nuevo Hospital con 10 pacientes
     * 
     */
    private void setupEscenario3( )
    {
        central = new CentralPacientes( );
        numeroPacientes = 10;
        for( int cont = 0; cont < numeroPacientes; cont++ )
        {
            central.agregarPacienteAlFinal( new Paciente( cont, "nombre" + cont, "clínica la concordia" + cont, "la historia" + cont, Paciente.HOMBRE ) );
        }
    }

    /**
     * Prueba la adición de pacientes. <br>
     * <b> Métodos a probar: </b> <br>
     * darPacientes, agregarPacienteAlComienzo. <br>
     * <b> Objetivo: </b> probar que el método agregarPacienteAlComienzo() agrega correctamente pacientes a la lista. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al agregar un paciente en un hospital sin pacientes este debe ser insertado sin errores. <br>
     * 2. Al agregar un paciente 'A' al comienzo de la lista este debe quedar de primero y no se debe perder ninguno de los pacientes previamente agregados. <br>
     */
    public void testAgregarPacienteAlComienzo( )
    {
        setupEscenario1( );
        numeroPacientes = 11;
        ArrayList pacientes = new ArrayList( );
        Paciente paciente;
        Paciente aux;
        for( int cont = 0; cont < numeroPacientes; cont++ )
        {
            paciente = new Paciente( cont, "nombre" + cont, "Clínica Palermo" + cont, "la información médica del paciente  " + cont, Paciente.MUJER );
            pacientes.add( paciente );
        }
        // Agrega los pacientes y verifica que se hayan agregado correctamente
        for( int cont = 0; cont < numeroPacientes; cont++ )
        {
            paciente = ( Paciente )pacientes.get( cont );
            central.agregarPacienteAlComienzo( new Paciente( paciente.darCodigo( ), paciente.darNombre( ), paciente.darClinica( ), paciente.darInformacionMedica( ), paciente.darSexo( ) ) );
            ArrayList losPacientes = central.darPacientes( );
            aux = ( Paciente )losPacientes.get( 0 );

            // Verifica que la información sea correcta
            assertEquals( "La adición no se realizó correctamente", paciente.darCodigo( ), aux.darCodigo( ) );
            assertEquals( "La adición no se realizó correctamente", paciente.darClinica( ), aux.darClinica( ) );
            assertEquals( "La adición no se realizó correctamente", paciente.darInformacionMedica( ), aux.darInformacionMedica( ) );
            assertEquals( "La adición no se realizó correctamente", paciente.darNombre( ), aux.darNombre( ) );
            assertEquals( "La adición no se realizó correctamente", paciente.darSexo( ), aux.darSexo( ) );

            // Verifica que el número de pacientes en la lista sea el correcto
            assertEquals( "El número de pacientes no es el correcto", cont + 1, losPacientes.size( ) );
        }
    }

    /**
     * Prueba la adición de pacientes. <br>
     * <b> Métodos a probar: </b> <br>
     * darPacientes, agregarPacienteAlFinal. <br>
     * <b> Objetivo: </b> probar que el método agregarPacienteAlFinal() agrega correctamente pacientes a la lista. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al agregar un paciente en un hospital sin pacientes este debe ser insertado sin errores. <br>
     * 2. Al agregar un paciente 'A' al final de la lista este debe quedar de último y no se debe perder ninguno de los pacientes previamente agregados. <br>
     */
    public void testAgregarPacienteAlFinal( )
    {
        setupEscenario1( );
        numeroPacientes = 11;
        ArrayList pacientes = new ArrayList( );
        Paciente paciente;
        Paciente aux;
        for( int cont = 0; cont < numeroPacientes; cont++ )
        {
            paciente = new Paciente( cont, "nombre paciente" + cont, "Clínica El Bosque" + cont, "la información médica del paciente  " + cont, Paciente.HOMBRE );
            pacientes.add( paciente );
        }
        // Agrega los pacientes y verifica que se hayan agregado correctamente

        for( int cont = 0; cont < numeroPacientes; cont++ )
        {
            paciente = ( Paciente )pacientes.get( cont );
            central.agregarPacienteAlFinal( new Paciente( paciente.darCodigo( ), paciente.darNombre( ), paciente.darClinica( ), paciente.darInformacionMedica( ), paciente.darSexo( ) ) );
            ArrayList losPacientes = central.darPacientes( );
            aux = ( Paciente )losPacientes.get( cont );

            // Verifica que la información sea correcta
            assertEquals( "La adición no se realizó correctamente", paciente.darCodigo( ), aux.darCodigo( ) );
            assertEquals( "La adición no se realizó correctamente", paciente.darClinica( ), aux.darClinica( ) );
            assertEquals( "La adición no se realizó correctamente", paciente.darInformacionMedica( ), aux.darInformacionMedica( ) );
            assertEquals( "La adición no se realizó correctamente", paciente.darNombre( ), aux.darNombre( ) );
            assertEquals( "La adición no se realizó correctamente", paciente.darSexo( ), aux.darSexo( ) );

            // Verifica que el número de pacientes en la lista sea el correcto
            assertEquals( "El número de pacientes no es el correcto", cont + 1, losPacientes.size( ) );
        }

    }

    /**
     * Prueba la adición de pacientes. <br>
     * <b> Métodos a probar: </b> <br>
     * agregarPacienteAntesDe, localizar, agregarPacienteAlComienzo. <br>
     * <b> Objetivo: </b> probar que el método agregarPacienteAntesDe() agrega correctamente pacientes a la lista. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al agregar un paciente en un hospital sin pacientes este debe ser insertado sin errores. <br>
     * 2. Al agregar un paciente 'A' antes de un paciente 'B' previamente adicionado, el paciente 'A' debe quedar antes del paciente 'B' en la lista. <br>
     * 3. Al agregar un paciente este debe quedar registrado en el hospital
     */
    public void testAgregarPacienteAntesDe( )
    {
        setupEscenario1( );
        numeroPacientes = 11;
        ArrayList pacientes = new ArrayList( );
        Paciente paciente;
        Paciente aux;
        for( int cont = 0; cont < numeroPacientes; cont++ )
        {
            paciente = new Paciente( cont, "nombre" + cont, "eps" + cont, "la historia" + cont, Paciente.HOMBRE );
            pacientes.add( paciente );
        }

        // Se agrega el primer paciente a la central para tener un elementos antes del cual agregar
        paciente = ( Paciente )pacientes.get( 0 );
        central.agregarPacienteAlComienzo( new Paciente( paciente.darCodigo( ), paciente.darNombre( ), paciente.darClinica( ), paciente.darInformacionMedica( ), paciente.darSexo( ) ) );
        // Agrega los pacientes y verifica que se hayan agregado correctamente buscándolos
        int ced = 0;

        try
        {
            for( int cont = 1; cont < numeroPacientes - 1; cont++ )
            {
                paciente = ( Paciente )pacientes.get( cont );
                central.agregarPacienteAntesDe( ced, new Paciente( paciente.darCodigo( ), paciente.darNombre( ), paciente.darClinica( ), paciente.darInformacionMedica( ), paciente.darSexo( ) ) );
                aux = central.localizar( paciente.darCodigo( ) );

                // Verifica que la información sea correcta
                assertEquals( "La adición no se realizó correctamente", paciente.darCodigo( ), aux.darCodigo( ) );
                assertEquals( "La adición no se realizó correctamente", paciente.darClinica( ), aux.darClinica( ) );
                assertEquals( "La adición no se realizó correctamente", paciente.darInformacionMedica( ), aux.darInformacionMedica( ) );
                assertEquals( "La adición no se realizó correctamente", paciente.darNombre( ), aux.darNombre( ) );
                assertEquals( "La adición no se realizó correctamente", paciente.darSexo( ), aux.darSexo( ) );
                ced = paciente.darCodigo( );
            }

            // Se verifica que los pacientes hayan sido ingresados en el orden correcto
            ArrayList pacientesHospital = central.darPacientes( );

            for( int cont = 0; cont < numeroPacientes - 1; cont++ )
            {
                paciente = ( Paciente )pacientesHospital.get( cont );
                assertEquals( "La adición no se realizó correctamente", numeroPacientes - 2 - cont, paciente.darCodigo( ) );
            }

            // Adiciona un paciente en la mitad y verifica que haya sido agregado correctamente
            paciente = ( Paciente )pacientes.get( numeroPacientes - 1 );
            ced = 5;
            central.agregarPacienteAntesDe( ced, new Paciente( paciente.darCodigo( ), paciente.darNombre( ), paciente.darClinica( ), paciente.darInformacionMedica( ), paciente.darSexo( ) ) );

            aux = central.localizar( ced + 1 );
            assertEquals( "La adición no se realizó correctamente", paciente.darCodigo( ), aux.darSiguiente( ).darCodigo( ) );
            assertEquals( "La adición no se realizó correctamente", ced, aux.darSiguiente( ).darSiguiente( ).darCodigo( ) );
            aux = aux.darSiguiente( );
            assertEquals( "La adición no se realizó correctamente", paciente.darCodigo( ), aux.darCodigo( ) );
            assertEquals( "La adición no se realizó correctamente", paciente.darClinica( ), aux.darClinica( ) );
            assertEquals( "La adición no se realizó correctamente", paciente.darInformacionMedica( ), aux.darInformacionMedica( ) );
            assertEquals( "La adición no se realizó correctamente", paciente.darNombre( ), aux.darNombre( ) );
            assertEquals( "La adición no se realizó correctamente", paciente.darSexo( ), aux.darSexo( ) );
        }
        catch( NoExisteException e )
        {
            fail( "No se debió arrojar esta excepción" );
        }
    }

    /**
     * Prueba que la adición de pacientes arroje error cuando la cédula del paciente siguiente no exista. <br>
     * <b> Métodos a probar: </b> <br>
     * agregarPacienteAntesDe. <br>
     * <b> Objetivo: </b> Probar que el método agregarPacienteAntesDe() arroje excepción cuando se trate de agregar un paciente antes de otro que no exista. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al agregar un paciente con una cédula del paciente siguiente que no exista se debe arrojar una excepción.
     */
    public void testAgregarPacienteAntesDeError( )
    {
        setupEscenario2( );

        // Tratar de agregar un paciente con la cédula del paciente siguiente inexistente
        try
        {
            central.agregarPacienteAntesDe( 1000, new Paciente( numeroPacientes, "carlos perez", "coopsalud", "El paciente sufre de rinitis crónica", Paciente.HOMBRE ) );
            fail( "No se debió agregar el paciente" );
        }
        catch( NoExisteException e1 )
        {
            // Se verifica que la lista de pacientes no haya crecido
            assertEquals( "No se debió agregar el paciente", numeroPacientes, central.darPacientes( ).size( ) );
        }
    }

    /**
     * Prueba la adición de pacientes. <br>
     * <b> Métodos a probar: </b> <br>
     * agregarPacienteDespuesDe, localizar. <br>
     * <b> Objetivo: </b> Probar que el método agregarPacienteDespuesDe() agregue pacientes correctamente a la lista. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al agregar un paciente en un hospital sin pacientes este debe ser insertado sin errores. <br>
     * 2. Al agregar un paciente 'A' después de un paciente 'B' previamente adicionado, el paciente 'A' debe quedar déspues del paciente 'B' en la lista. <br>
     * 3. Al agregar un paciente este debe quedar registrado en el hospital
     */
    public void testAgregarPacienteDespuesDe( )
    {
        setupEscenario1( );
        numeroPacientes = 11;
        ArrayList pacientes = new ArrayList( );
        Paciente paciente;
        Paciente aux;
        for( int cont = 0; cont < numeroPacientes; cont++ )
        {
            paciente = new Paciente( cont, "nombre" + cont, "eps" + cont, "la historia" + cont, Paciente.HOMBRE );
            pacientes.add( paciente );
        }

        // Agrega el primer paciente para tener un elemento después del cual insertar pacientes
        paciente = ( Paciente )pacientes.get( 0 );
        central.agregarPacienteAlComienzo( new Paciente( paciente.darCodigo( ), paciente.darNombre( ), paciente.darClinica( ), paciente.darInformacionMedica( ), paciente.darSexo( ) ) );

        // Agrega los pacientes y verifica que se hayan agregado correctamente buscándolos
        int ced = 0;
        try
        {
            for( int cont = 1; cont < numeroPacientes - 1; cont++ )
            {
                paciente = ( Paciente )pacientes.get( cont );
                central.agregarPacienteDespuesDe( ced, new Paciente( paciente.darCodigo( ), paciente.darNombre( ), paciente.darClinica( ), paciente.darInformacionMedica( ), paciente.darSexo( ) ) );
                aux = central.localizar( paciente.darCodigo( ) );

                // Verifica que la información sea correcta
                assertEquals( "La adición no se realizó correctamente", paciente.darCodigo( ), aux.darCodigo( ) );
                assertEquals( "La adición no se realizó correctamente", paciente.darClinica( ), aux.darClinica( ) );
                assertEquals( "La adición no se realizó correctamente", paciente.darInformacionMedica( ), aux.darInformacionMedica( ) );
                assertEquals( "La adición no se realizó correctamente", paciente.darNombre( ), aux.darNombre( ) );
                assertEquals( "La adición no se realizó correctamente", paciente.darSexo( ), aux.darSexo( ) );
                ced = paciente.darCodigo( );
            }

            // Se verifica que los pacientes hayan sido ingresados en el orden correcto
            ArrayList pacientesHospital = central.darPacientes( );

            for( int cont = 0; cont < numeroPacientes - 1; cont++ )
            {
                paciente = ( Paciente )pacientesHospital.get( cont );
                assertEquals( "La adición no se realizó correctamente", cont, paciente.darCodigo( ) );
            }

            // Adiciona un paciente en la mitad y verifica que haya sido agregado correctamente
            paciente = ( Paciente )pacientes.get( numeroPacientes - 1 );
            ced = 3;
            central.agregarPacienteDespuesDe( ced, new Paciente( paciente.darCodigo( ), paciente.darNombre( ), paciente.darClinica( ), paciente.darInformacionMedica( ), paciente.darSexo( ) ) );

            aux = central.localizar( ced );
            assertEquals( "La adición no se realizó correctamente", paciente.darCodigo( ), aux.darSiguiente( ).darCodigo( ) );
            aux = aux.darSiguiente( );
            assertEquals( "La adición no se realizó correctamente", paciente.darCodigo( ), aux.darCodigo( ) );
            assertEquals( "La adición no se realizó correctamente", paciente.darClinica( ), aux.darClinica( ) );
            assertEquals( "La adición no se realizó correctamente", paciente.darInformacionMedica( ), aux.darInformacionMedica( ) );
            assertEquals( "La adición no se realizó correctamente", paciente.darNombre( ), aux.darNombre( ) );
            assertEquals( "La adición no se realizó correctamente", paciente.darSexo( ), aux.darSexo( ) );
        }
        catch( NoExisteException e )
        {

            fail( "No se debió arrojar esta excepción" );
        }
    }

    /**
     * Prueba que la adición de pacientes arroje error cuando la cédula del paciente anterior no exista. <br>
     * <b> Métodos a probar: </b> <br>
     * agregarPacienteDespuesDe. <br>
     * <b> Objetivo: </b> Probar que el método agregarPacientedespuesDe() arroje excepción cuando se trate de agregar un paciente despues de otro que no exista. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al agregar un paciente con una cédula del paciente anterior que no exista se debe arrojar una excepción.
     */
    public void testAgregarPacienteDespuesDeError( )
    {
        setupEscenario2( );

        // Tratar de agregar un paciente con la cédula del paciente anterior inexistente
        try
        {
            central.agregarPacienteDespuesDe( 1000, new Paciente( numeroPacientes, "ana lopez", "sanitas", "El paciente sufre varicela", Paciente.MUJER ) );
            assertTrue( "No se debió agregar el paciente", false );
        }
        catch( NoExisteException e1 )
        {
            // Se verifica que la lista de pacientes no haya crecido
            assertEquals( "No se debió agregar el paciente", numeroPacientes, central.darPacientes( ).size( ) );
        }
    }

    /**
     * Prueba la eliminación de pacientes. <br>
     * <b> Métodos a probar: </b> <br>
     * eliminarPaciente, localizar. <br>
     * <b> Objetivo: </b> Probar que el método eliminarPaciente() elimine correctamente los pacientes de la lista. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al eliminar un paciente existente en un hospital al buscarlos no se debe encontrar. <br>
     * 2. Al eliminar un paciente existente en un hospital la lista debe quedar encadenada correctamente. <br>
     * 3. Al agregar un paciente este debe quedar registrado en el hospital
     */
    public void testEliminarPaciente( )
    {
        setupEscenario3( );

        // Eliminar la cabeza
        try
        {
            central.eliminarPaciente( 0 );
            Paciente paciente = central.localizar( 0 );

            assertNull( "El paciente no debería encontrarse", paciente );
            assertEquals( "El número de pacientes no es el correcto", numeroPacientes - 1, central.darPacientes( ).size( ) );

            // Eliminar un elemento del medio
            central.eliminarPaciente( 5 );
            paciente = central.localizar( 5 );
            assertNull( "El paciente no debería encontrarse", paciente );
            assertEquals( "El número de pacientes no es el correcto", numeroPacientes - 2, central.darPacientes( ).size( ) );

            // Eliminar la cola
            central.eliminarPaciente( numeroPacientes - 1 );
            paciente = central.localizar( numeroPacientes - 1 );
            assertNull( "El paciente no debería encontrarse", paciente );
            assertEquals( "El número de pacientes no es el correcto", numeroPacientes - 3, central.darPacientes( ).size( ) );

            // Verificar que la lista haya quedado encadenada correctamente después de las eliminaciones
            ArrayList pacientes = central.darPacientes( );
            for( int cont = 0; cont < numeroPacientes - 3; cont++ )
            {
                paciente = ( Paciente )pacientes.get( cont );
                if( cont < 4 )
                {

                    assertEquals( "La eliminación no se realizó de forma correcta", cont + 1, paciente.darCodigo( ) );
                }
                else if( cont > 4 )
                {
                    assertEquals( "La eliminación no se realizó de forma correcta", cont + 2, paciente.darCodigo( ) );
                }
            }

            // Eliminar el resto de pacientes y verificar que la eliminación se haga de forma correcta
            numeroPacientes -= 3;
            for( int cont = 0; cont < pacientes.size( ) - 1; cont++ )
            {
                if( cont != 5 )
                {
                    paciente = ( Paciente )pacientes.get( cont + 1 );
                    central.eliminarPaciente( paciente.darCodigo( ) );
                    numeroPacientes--;
                    assertEquals( "El número de pacientes no es correcto", numeroPacientes, central.darPacientes( ).size( ) );
                }

            }

        }
        catch( NoExisteException e )
        {
            fail( "No se debió arrojar esta excepción" );
        }
    }

    /**
     * Prueba que al tratar de eliminar un paciente que no exista se arroje excepción. <br>
     * <b> Métodos a probar: </b> <br>
     * eliminarPaciente. <br>
     * <b> Objetivo: </b> Probar que el método eliminarPaciente() arroje excepción cuando se trate de eliminar un paciente que no exista. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al eliminar un paciente que no existe se debe lanzar excepción.
     */
    public void testEliminarPacienteError( )
    {
        setupEscenario1( );

        try
        {
            central.eliminarPaciente( 1000 );
            assertTrue( "No se debió eliminar ningún paciente", false );
        }
        catch( NoExisteException e )
        {

            // Verificar que no se haya eliminado ningún paciente
            assertEquals( "No se debió eliminar ningún paciente", numeroPacientes, central.darPacientes( ).size( ) );

            setupEscenario2( );

            try
            {
                central.eliminarPaciente( 1000 );
                assertTrue( "No se debió eliminar ningún paciente", false );
            }
            catch( NoExisteException e1 )
            {
                // Verificar que no se haya eliminado ningún paciente
                assertEquals( "No se debió eliminar nimgún paciente", numeroPacientes, central.darPacientes( ).size( ) );
            }

        }
    }

    /**
     * Prueba que la búsqueda de pacientes se realice correctamente. <br>
     * <b> Métodos a probar: </b> <br>
     * localizar. <br>
     * <b> Objetivo: </b> Probar que el método localizar() sea capaz de encontrar los pacientes que se encuentran en la lista. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar un paciente existente se debe encontrar. <br>
     * 2. Al buscar un paciente que no exista se debe retornar null.
     */
    public void testLocalizar( )
    {
        setupEscenario2( );

        // Busca un paciente del medio
        Paciente paciente = central.localizar( numeroPacientes - 6 );
        assertNotNull( "El paciente debería haberse encontrado", paciente );
        assertEquals( "El paciente no se busco de forma correcta", numeroPacientes - 6, paciente.darCodigo( ) );

        // Busca el paciente de la cola
        paciente = central.localizar( numeroPacientes - 1 );
        assertNotNull( "El paciente debería haberse encontrado", paciente );
        assertEquals( "El paciente no se busco de forma correcta", numeroPacientes - 1, paciente.darCodigo( ) );

        paciente = central.localizar( 1000 );
        assertNull( "El paciente no debería haberse encontrado", paciente );
    }

    /**
     * Prueba que la búsqueda del paciente anterior a otro se realice correctamente. <br>
     * <b> Métodos a probar: </b> <br>
     * localizarAnterior. <br>
     * <b> Objetivo: </b> Probar que el método localizarAnterior() sea capaz de encontrar el paciente anterior de un paciente dado. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar un paciente anterior en una lista vacía se debe retornar null. <br>
     * 2. Al buscar el paciente anterior al primero se debe retornar null. <br>
     * 3. Al buscar el paciente anterior a uno intermedio este debe ser encontrado. <br>
     * 4. Al buscar el paciente anterior al último este debe ser encontrado. <br>
     * 5. Al buscar el paciente anterior a uno que no exista se debe retornar null.
     */
    public void testLocalizarAnterior( )
    {

        setupEscenario1( );

        // Verifica que se retorne null cuando la lista de pacientes esté vacía
        Paciente paciente = central.localizarAnterior( 0 );
        assertNull( "El paciente anterior no debería existir", paciente );

        setupEscenario2( );

        // Verifica que se retorne null cuando se pida el paciente anterior al primero
        paciente = central.localizarAnterior( numeroPacientes - 1 );
        assertNull( "El paciente anterior no debería existir", paciente );

        // Verifica que se busque correctamente el paciente anterior a un paciente del medio
        paciente = central.localizarAnterior( numeroPacientes / 2 );
        assertEquals( "El paciente anterior no es el correcto", ( numeroPacientes / 2 ) + 1, paciente.darCodigo( ) );

        // Verifica que se busque correctamente el paciente anterior al paciente del final
        paciente = central.localizarAnterior( 0 );
        assertEquals( "El paciente anterior no es el correcto", 1, paciente.darCodigo( ) );

        // Verifica que se retorne null cuando se busque un paciente que no exista
        paciente = central.localizarAnterior( 1000 );
        assertNull( "El paciente anterior no debería existir", paciente );

    }

    /**
     * Prueba que la búsqueda del paciente anterior a otro se realice correctamente. <br>
     * <b> Métodos a probar: </b> <br>
     * localizarUltimo. <br>
     * <b> Objetivo: </b> Probar que el método localizarUltimo() sea capaz de encontrar el último paciente de la lista de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar el último paciente en una lista vacía se debe retornar null. <br>
     * 2. Al buscar el último paciente en una lista no vacía este debe ser encontrado.
     */
    public void testLocalizarUltimo( )
    {

        setupEscenario1( );

        // Verifica que se retorne null cuando la lista de pacientes esté vacía
        Paciente paciente = central.localizarUltimo( );
        assertNull( "El último paciente no debería existir", paciente );

        setupEscenario2( );

        // Verifica que se retorne correctamente el último paciente de una lista no vacía
        paciente = central.localizarUltimo( );
        assertEquals( "El paciente anterior no es el correcto", 0, paciente.darCodigo( ) );
    }

}
