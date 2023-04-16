/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CentralPacientes.java,v 1.12 2006/09/05 16:07:12 da-romer Exp $.
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_centralPacientes
 * Autor: Daniel Francisco Romero - 11-jul-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.centralPacientes.mundo;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Esta clase representa una central en la que se maneja una lista de pacientes <br>
 * <b>inv:</b> <br>
 * numPacientes == longitud de la lista de pacientes <br>
 * los c�digos de los pacientes son �nicos en la central <br>
 * listaClinicas != null
 */
public class CentralPacientes
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Primer paciente de la lista
     */
    private Paciente primero;

    /**
     * N�mero de pacientes en la central
     */
    private int numPacientes;

    /**
     * Vector de cl�nicas manejadas por la central
     */
    private ArrayList listaClinicas;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea una nueva central sin pacientes y con una lista predefinida de cl�nicas
     */
    public CentralPacientes( )
    {
        primero = null;
        numPacientes = 0;

        listaClinicas = new ArrayList( );
        listaClinicas.add( "Clínica del Country" );
        listaClinicas.add( "Clínica Palermo" );
        listaClinicas.add( "Clínica Reina Sof�a" );
        listaClinicas.add( "Clínica El Bosque" );
        listaClinicas.add( "Clínica San Ignacio" );
        listaClinicas.add( "Otra" );

        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el n�mero de pacientes de la cl�nica
     * @return El n�mero de pacientes de la cl�nica
     */
    public int darNumeroPacientes( )
    {
        return numPacientes;
    }

    /**
     * Adiciona un paciente al principio de la lista
     * @param pac El paciente a ser agregado al comienzo de la lista. <br>
     *        pac!=null y no existe un paciente con c�digo igual a pac.codigo
     */
    public void agregarPacienteAlComienzo( Paciente pac )
    {
        if( primero == null ) // Crea la cabeza si no existe
        {
            primero = pac;
        }
        else
            // Realiza la adici�n antes del paciente que est� al inicio de la lista
        {
            pac.cambiarSiguiente( primero );
            primero = pac;
        }
        numPacientes++;
        verificarInvariante( );
    }

    /**
     * Adiciona un paciente al final de la lista. Si la lista est� vac�a el paciente queda de primero
     * @param pac El paciente a ser agregado al final la lista. <br>
     *        pac!=null y no existe un paciente con c�digo igual a pac.codigo
     */
    public void agregarPacienteAlFinal( Paciente pac )
    {
        if( primero == null ) // Si la cabeza no existe adiciona de primero el paciente
        {
            primero = pac;
        }
        else
        {
            // Busca el �ltimo paciente de la lista
            Paciente p = localizarUltimo( );

            // Adiciona el paciente despu�s del �ltimo paciente de la lista
            p.insertarDespues( pac );
        }
        numPacientes++;
        verificarInvariante( );
    }

    /**
     * Adiciona un paciente a la lista de pacientes antes del paciente con el c�digo especificado. <br>
     * <b> pre: </b> primero!= null. <br>
     * @param cod El c�digo del paciente antes del que se va insertar el nuevo paciente.
     * @param pac El paciente que se va a adicionar. <br>
     *        pac!=null y no existe un paciente con c�digo igual a pac.codigo
     * @throws NoExisteException Si el paciente antes del que se va a realizar la adici�n no se encuentra registrado.
     */
    public void agregarPacienteAntesDe( int cod, Paciente pac ) throws NoExisteException
    {
        if( primero == null )
            throw new NoExisteException( cod );
        else if( cod == primero.darCodigo( ) )
        {
            pac.cambiarSiguiente( primero );
            primero = pac;
        }
        else
        {
            Paciente anterior = localizarAnterior( cod );
            if( anterior == null )
                throw new NoExisteException( cod );
            anterior.insertarDespues( pac );
        }
        numPacientes++;
        verificarInvariante( );
    }

    /**
     * Adiciona un paciente a la lista de pacientes despu�s del paciente con el c�digo especificado.
     * @param cod El c�digo del paciente despu�s del que se va insertar el nuevo paciente.
     * @param pac El paciente que se va a adicionar. <br>
     *        pac!=null y no existe un paciente con c�digo igual a pac.codigo
     * @throws NoExisteException Si el paciente despu�s del que se va a realizar la adici�n no se encuentra registrado.
     */
    public void agregarPacienteDespuesDe( int cod, Paciente pac ) throws NoExisteException
    {
        Paciente anterior = localizar( cod );

        if( anterior == null ) // Si no existe el paciente despu�s del que se desea realizar la adici�n se arroja la excepci�n
        {
            throw new NoExisteException( cod );
        }
        else
            // Se adiciona el paciente
        {
            anterior.insertarDespues( pac );
        }
        numPacientes++;
        verificarInvariante( );
    }

    /**
     * Busca el paciente con el c�digo dado en la lista de pacientes.
     * @param codigo El c�digo del paciente que se va a buscar
     * @return El paciente con el c�digo especificado. Si el paciente no existe se retorna null
     */
    public Paciente localizar( int codigo )
    {
        Paciente actual = primero;
        while( actual != null && actual.darCodigo( ) != codigo )
        {
            actual = actual.darSiguiente( );
        }
        return actual;
    }

    /**
     * Busca el paciente anterior al paciente con el c�digo especificado
     * @param cod C�digo del paciente del que se desea el paciente anterior
     * @return El paciente anterior al paciente con el c�digo dado. Se retorna null si el paciente con el c�digo dado no existe o si es el primero de la lista
     */
    public Paciente localizarAnterior( int cod )
    {
        Paciente anterior = null;
        Paciente actual = primero;

        while( actual != null && actual.darCodigo( ) != cod )
        {
            anterior = actual;
            actual = actual.darSiguiente( );
        }

        return actual != null ? anterior : null;
    }

    /**
     * Retorna el �ltimo paciente de la lista
     * @return El �ltimo paciente de la lista. Si la lista est� vac�a se retorna null
     */
    public Paciente localizarUltimo( )
    {
        Paciente actual = primero;

        if( actual != null )
        {
            while( actual.darSiguiente( ) != null )
            {
                actual = actual.darSiguiente( );
            }
        }
        return actual;
    }

    /**
     * Elimina el paciente con el c�digo especificado.
     * @param cod El c�digo del paciente a ser eliminado. cod >= 0
     * @throws NoExisteException Si el paciente con el c�digo especificado no existe y por tanto no pudo ser eliminado de la lista
     */
    public void eliminarPaciente( int cod ) throws NoExisteException
    {
        if( primero == null )
            throw new NoExisteException( cod );
        else if( cod == primero.darCodigo( ) )
        {
            // El paciente es el primero de la lista
            primero = primero.darSiguiente( );
        }
        else
        {
            // El paciente es un elemento intermedio de la lista
            Paciente anterior = localizarAnterior( cod );
            if( anterior == null )
                throw new NoExisteException( cod );
            anterior.desconectarSiguiente( );
        }
        numPacientes--;
        verificarInvariante( );
    }

    /**
     * Devuelve una lista con los pacientes de la central
     * @return Lista de Pacientes
     */
    public ArrayList darPacientes( )
    {
        ArrayList pacientes = new ArrayList( );
        Paciente actual = primero;
        while( actual != null )
        {
            pacientes.add( actual );
            actual = actual.darSiguiente( );
        }
        return pacientes;
    }

    /**
     * Retorna la lista de cl�nicas manejadas por la central
     * @return La lista de cl�nicas manejadas por la central
     */
    public ArrayList darListaClinicas( )
    {
        return listaClinicas;
    }





    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica que el invariante de la clase se cumpla. Si algo falla, lanza un AssertError. <br>
     * <b>inv: </b> <br>
     * numPacientes == longitud de la lista de pacientes <br>
     * los c�digos de los pacientes son �nicos en la central <br>
     * listaClinicas != null
     */
    private void verificarInvariante( )
    {
        assert numPacientes == darLongitud( ) : "N�mero de pacientes inconsistente";
        assert !hayCodigosRepetidos( ) : "Los c�digos no son �nicos";
        assert listaClinicas != null : "La lista de cl�nicas no puede ser nula";
    }

    /**
     * Retorna la longitud de la lista
     * @return Longitud de la lista de pacientes
     */
    private int darLongitud( )
    {
        Paciente actual = primero;
        int longitud = 0;

        while( actual != null )
        {
            longitud++;
            actual = actual.darSiguiente( );
        }
        return longitud;
    }

    /**
     * Indica si en la lista de pacientes hay al menos un c�digo repetido
     * @return True si hay c�digo repetidos o false en caso contrario
     */
    private boolean hayCodigosRepetidos( )
    {
        boolean repetidos = false;

        Paciente actual = primero;

        while( actual != null && !repetidos )
        {
            Paciente elPaciente = actual.darSiguiente( );

            while( elPaciente != null && !repetidos )
            {
                if( actual.darCodigo( ) == elPaciente.darCodigo( ) )
                {
                    repetidos = true;
                }
                elPaciente = elPaciente.darSiguiente( );
            }

            actual = actual.darSiguiente( );
        }

        return repetidos;
    }

    //------------------------------------------------------------------
    // Trabajo de los estudiantes
    //------------------------------------------------------------------

    // -----------------------------------------------------------------
    // Trabajo del estudiante - N9L1 - 11/04/23
    // Antony Salcedo
    // -----------------------------------------------------------------

    /**
     * Este método devuelve la cantidad de pacientes que se encuentran en la central.
     * @return la cantidad de pacientes en la central.
     * @throws NoHayPacientesException si la lista de pacientes se encuentra vacía.
     * @throws Exception si ocurre un error durante la ejecución del método.
     */
    public int darCantidadPacientes() throws NoHayPacientesException, Exception
    {
        // Verificar si la lista está vacía.
        if (primero == null) {
            throw new NoHayPacientesException("No hay pacientes en la central.");
        }
        // Inicializar la variable de cantidad a cero.
        int cantidad = 0;
        // Empezar con el primer paciente en la lista.
        Paciente actual = primero;
        // Recorrer la lista hasta que se llegue al final.
        while(actual != null)
        {
            // Incrementar la cantidad de pacientes.
            cantidad++;
            // Pasar al siguiente paciente en la lista.
            actual = actual.darSiguiente( );
        }
        // Devolver la cantidad de pacientes en la lista.
        return cantidad;
    }

    // -----------------------------------------------------------------
    // Trabajo del estudiante - N9L1 - 11/04/23
    // Antony Salcedo
    // -----------------------------------------------------------------

    /**
     * Verifica si existe al menos un paciente con síntomas de gripa en la central de pacientes.
     * @return true si existe al menos un paciente con síntomas de gripa, false de lo contrario.
     * @throws NoHayPacientesException si la central de pacientes no tiene ningún paciente registrado.
     * @throws Exception si ocurre un error durante la ejecución del método.
     */
    public boolean existenPacientesConGripa() throws NoHayPacientesException, Exception
    {
        // Verificar si la central de pacientes está vacía.
        if (primero == null) {
            throw new NoHayPacientesException("No hay pacientes en la central.");
        }
        // Inicializamos la variable de validades de pacientes con gripa.
        boolean existe = false;

        // Recorrer la lista de pacientes buscando síntomas de gripa.
        Paciente actual = primero;
        while (actual != null && !existe) {
            if (actual.darInformacionMedica().equalsIgnoreCase("gripa")) {
                existe = true;
            }
            actual = actual.darSiguiente();
        }
        // Devolver true si se encontró al menos un paciente con síntomas de gripa, false de lo contrario.
        return existe;
    }


    // -----------------------------------------------------------------
    // Trabajo del estudiante - N9L2 - 12/04/23
    // Antony Salcedo
    // -----------------------------------------------------------------

    /**
     * Este método devuelve la cantidad de pacientes mujeres con COVID que se encuentran en la central de pacientes.
     * @return la cantidad de pacientes mujeres con COVID que se encuentran en la central de pacientes.
     * @throws NoHayPacientesException si la central de pacientes no tiene ningún paciente registrado.
     * @throws Exception si ocurre un error durante la ejecución del método.
     */
    public int CantidadPacientesMuejresCovid() throws NoHayPacientesException, Exception
    {
        // Verificamos si la central de pacientes está vacía.
        if (primero == null) {
            throw new NoHayPacientesException("No hay pacientes en la central.");
        }
        // Inicializamos la cantidad de pacientes mujeres con COVID en 0.
        int cantidad = 0;
        // Recorremos la lista de pacientes mientras no lleguemos al final.
        Paciente actual = primero;
        while(actual != null)
        {
            // Si el paciente actual es mujer y tiene COVID, aumentamos la cantidad en 1.
            if(actual.darSexo() == 2 && actual.darInformacionMedica().equalsIgnoreCase("COVID"))
            {
                cantidad++;
            }
            // Avanzamos al siguiente paciente.
            actual = actual.darSiguiente();
        }
        // Devolvemos la cantidad de pacientes mujeres con COVID.
        return cantidad;
    }

    // -----------------------------------------------------------------
    // Trabajo del estudiante - N9L2 - 12/04/23
    // Antony Salcedo
    // -----------------------------------------------------------------

    /**
     * Verifica si existe al menos un paciente asociado a una clínica determinada.
     * @param nClinicaUsuario Nombre de la clínica que se desea buscar.
     * @return Retorna true si existe al menos un paciente asociado a la clínica buscada, de lo contrario, retorna false.
     * @throws NoHayPacientesException si la central de pacientes no tiene ningún paciente registrado.
     * @throws IllegalArgumentException si el usuario no inrgreso un nombre de clinica valido.
     * @throws Exception si ocurre un error durante la ejecución del método.
     */
    public boolean existenPacientesDadaLaClinica(String nClinicaUsuario) throws NoHayPacientesException, IllegalArgumentException, Exception
    {
        // Se verifica que el usuario haya ingresado un nombre de clínica.
        if(nClinicaUsuario == null || nClinicaUsuario.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar un nombre de clínica válido.");
        }
        // Verificamos si la lista de pacientes está vacía.
        if (primero == null) {
            throw new NoHayPacientesException("No hay pacientes en la central.");
        }
        // Inicializamos una variable para indicar si existe al menos un paciente asociado a la clínica buscada.
        boolean existePaciente = false;
        // Recorremos la lista de pacientes mientras no se encuentre un paciente asociado a la clínica buscada.
        Paciente actual = primero;
        while(actual != null && !existePaciente) {
            // Verificamos si el paciente actual está asociado a la clínica buscada.
            if(actual.darClinica().equalsIgnoreCase(nClinicaUsuario)) {
                existePaciente = true;
            }
            // Avanzamos al siguiente paciente.
            actual = actual.darSiguiente();
        }
        // Retornamos el resultado de la búsqueda.
        return existePaciente;
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Trabajo del estudiante - N9L2 - 11/04/23
    // Antony Salcedo
    // -----------------------------------------------------------------


    /**
     * Este método es una extensión que permite al usuario obtener la cantidad de pacientes registrados en la central médica.
     * En caso de que el usuario confirme la opción de obtener la cantidad de pacientes, se invoca el método darCantidadPacientes() para obtener el número de pacientes y se devuelve como texto junto con un mensaje.
     * Si el usuario elige no obtener la cantidad de pacientes, se devuelve un mensaje de despedida.
     * En caso de que la lista de pacientes esté vacía, se captura la excepción NoHayPacientesException y se devuelve un mensaje de error con el mensaje correspondiente.
     * En caso de que ocurra alguna otra excepción durante la ejecución del método, se captura la excepción y se devuelve un mensaje de error con el mensaje correspondiente.
     * @return la respuesta correspondiente según la opción seleccionada por el usuario.
     */
    public String metodo1() {
        try {
            // Solicitar al usuario si desea conocer la cantidad de pacientes.
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea conocer la cantidad de pacientes en la central?", "Cantidad de pacientes", JOptionPane.YES_NO_OPTION);
            // Verificar la opción seleccionada por el usuario.
            if (opcion == JOptionPane.YES_OPTION) {
                // Si el usuario desea conocer la cantidad de pacientes, se invoca el método darCantidadPacientes() para obtener la cantidad.
                return "La cantidad de pacientes es: " + darCantidadPacientes();
            } else {
                // Si el usuario no desea conocer la cantidad de pacientes, se devuelve un mensaje de despedida.
                return "Vuelva pronto.";
            }
        } catch (NoHayPacientesException e) {
            // Capturar la excepción NoHayPacientesException en caso de que la lista de pacientes se encuentre vacía.
            return "Error: No hay pacientes en la central.";
        } catch (Exception e) {
            // Capturar cualquier otra excepción que ocurra durante la ejecución del método.
            return "Ocurrió un error durante la ejecución del método" + "\n" + "Error: " + "(" + e.getMessage() + ")";
        }
    }


    // -----------------------------------------------------------------
    // Trabajo del estudiante - N9L2 - 11/04/23
    // Antony Salcedo
    // -----------------------------------------------------------------

    /**
     * Este metodo es una extencion que permite al usuario saber si existen pacientes con gripa en la central de pacientes.
     * En caso de que el usuario confirme la opcion de saber si exiten pacientes con gripa, se invoca el método existenPacientesConGripa() para verificar si existen pacientes con síntomas de gripa.
     * Si existen pacientes con gripa, se retorna un mensaje indicando que sí existen pacientes con gripa; de lo contrario, se retorna un mensaje indicando que no existen pacientes con gripa.
     * Si el usuario elige no saber si existen pacientes con gripa, se devuelve un mensaje de despedida.
     * En caso de que la lista de pacientes esté vacía, se captura la excepción NoHayPacientesException y se devuelve un mensaje de error con el mensaje correspondiente.
     * En caso de que ocurra alguna otra excepción durante la ejecución del método, se captura la excepción y se devuelve un mensaje de error con el mensaje correspondiente.
     * @return la respuesta correspondiente según la opción seleccionada por el usuario.
     */
    public String metodo2( )
    {
        try {
            // Preguntar al usuario si desea saber si existen pacientes con gripa.
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea saber si existen pacientes con gripa?", "¿Existen pacientes con gripa?", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {
                // Si el usuario responde afirmativamente, llamar al método existenPacientesConGripa() para verificar si existen pacientes con síntomas de gripa.
                if (existenPacientesConGripa()) {
                    // Si existen pacientes con gripa, retornar un mensaje indicando que sí existen pacientes con gripa.
                    return "Existen pacientes con gripa.";
                } else {
                    // Si no existen pacientes con gripa, retornar un mensaje indicando que no existen pacientes con gripa.
                    return "No existen pacientes con gripa.";
                }
            } else {
                // Si el usuario responde negativamente, retornar un mensaje indicando que vuelva pronto.
                return "Vuelva pronto.";
            }
        } catch (NoHayPacientesException e) {
            // Capturar la excepción NoHayPacientesException en caso de que la lista de pacientes se encuentre vacía.
            return "Error: " + "\n" + "(" + e.getMessage() + ")";
        } catch (Exception e) {
            // Capturar cualquier otra excepción que ocurra durante la ejecución del método.
            return "Ocurrió un error durante la ejecución del método" + "\n" + "Error: " + "(" + e.getMessage() + ")";
        }
    }

    // -----------------------------------------------------------------
    // Trabajo del estudiante - N9L2 - 12/04/23
    // Antony Salcedo
    // -----------------------------------------------------------------

    /**
     * Este método es una extensión que permite al usuario obtener la cantidad de pacientes mujeres con COVID en la central de pacientes.
     * En caso de que el usuario confirme la opción de obtener la cantidad de pacientes mujeres con COVID, se invoca el método CantidadPacientesMujeresCovid() para obtener el número de pacientes mujeres con COVID y se devuelve como texto junto con un mensaje.
     * Si el usuario elige no obtener la cantidad de pacientes mujeres con COVID, se devuelve un mensaje de despedida.
     * En caso de que la lista de pacientes esté vacía, se captura la excepción NoHayPacientesException y se devuelve un mensaje de error con el mensaje correspondiente.
     * En caso de que ocurra alguna otra excepción durante la ejecución del método, se captura la excepción y se devuelve un mensaje de error con el mensaje correspondiente.
     * @return la respuesta correspondiente según la opción seleccionada por el usuario.
     */
    public String metodo3( )
    {
        try {
            // Solicitar al usuario si desea conocer la cantidad de pacientes mujeres con COVID.
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea saber la cantidad de pacientes mujeres con COVID?", "Cantidad de pacientes", JOptionPane.YES_NO_OPTION);
            // Verificar la opción seleccionada por el usuario.
            if (opcion == JOptionPane.YES_OPTION) {
                // Si el usuario desea conocer la cantidad de pacientes mujeres con COVID, se invoca el método CantidadPacientesMujeresCovid() para obtener la cantidad.
                return "La cantidad de pacientes es: " +  CantidadPacientesMuejresCovid() + ".";
            } else {
                // Si el usuario no desea conocer la cantidad de pacientes mujeres con COVID, se devuelve un mensaje de despedida.
                return "Vuelva pronto.";
            }
        } catch (NoHayPacientesException e) {
            // Capturar la excepción NoHayPacientesException en caso de que la lista de pacientes se encuentre vacía.
            return "Error:" + "\n" + "(" + e.getMessage() + ")";
        } catch (Exception e) {
            // Capturar cualquier otra excepción que ocurra durante la ejecución del método.
            return "Ocurrió un error durante la ejecución del método." + "\n" + "Error: " + "(" + e.getMessage() + ")";
        }
    }

    // -----------------------------------------------------------------
    // Trabajo del estudiante - N9L2 - 12/04/23
    // Antony Salcedo
    // -----------------------------------------------------------------

    /**
     * Este método es una extensión que permite al usuario saber si existen pacientes afiliandos a una clinica registrada en la central de pacientes.
     * Este método solicita al usuario el nombre de la clínica a la que se encuentra afiliado un paciente,
     * y verifica si existe al menos un paciente asociado a dicha clínica en la central de pacientes.
     * En caso de que el usuario confrime la opcion de saber si existen pacientes afiliados a una cilina en la central de pacientes, 
     * se invoca al metodo existenPacientesDadaLaClinica(String nClinicaUsuario), para realizar la verificación.
     * Si existen pacientes con registrados en la clinica dada por el usiario, se retorna un mensaje indicando que sí existen pacientes con esa afiliciación; 
     * de lo contrario, se retorna un mensaje indicando que no existen pacientes con esa afiliación.
     * Si el usuario elige no saber si existen pacientes dada la clinica, se devuelve un mensaje de despedida.
     * En caso de que la lista de pacientes esté vacía, se captura la excepción NoHayPacientesException y se devuelve un mensaje de error con el mensaje correspondiente.
     * En caso de que el usuario no ingrese un nombre de clinica valido, se captura la excepción IllegalArgumentException y se devuelve un mensaje de error con el mensaje correspondiente.
     * En caso de que ocurra alguna otra excepción durante la ejecución del método, se captura la excepción y se devuelve un mensaje de error con el mensaje correspondiente.
     * @return la respuesta correspondiente según la opción seleccionada por el usuario.
     */
    public String metodo4( )
    {
        try {
            // Se solicita al usuario que ingrese el nombre de la clinica a la cual se encuentra afiliado un paciente.
            String datoUsuario = JOptionPane.showInputDialog(null, "Ingrese la clínica a la que se encuentra afiliado un paciente.");

            // Se evalúa si el usuario seleccionó "Aceptar" o "Cancelar" en la ventana de diálogo.
            if(datoUsuario != null)
            {
                // Se llama al método existenPacientesDadaLaClinica(String nClinicaUsuario) para verificar si existen pacientes asociados a la clínica ingresada por el usuario.
                boolean hayPacientes = existenPacientesDadaLaClinica(datoUsuario);
                if(hayPacientes){
                    // Si existen pacientes asociados a la clínica ingresada por el usuario, se retorna un mensaje indicando esta información.
                    return "Existen pacientes en la clínica: " + datoUsuario + ".";
                }else{
                    // Si no existen pacientes asociados a la clínica ingresada por el usuario, se retorna un mensaje indicando esta información.
                    return ("La clínica " + datoUsuario + " no esta registrada en la central.");
                }
            }else {
                // Si el usuario responde negativamente, se devulve un mensaje de despedida.
                return "Vuelva pronto.";
            }
        } catch (NoHayPacientesException e) {
            // Capturar la excepción NoHayPacientesException en caso de que la lista de pacientes se encuentre vacía.
            return "Error:" + "\n" + "(" + e.getMessage() + ")";
        } catch (IllegalArgumentException e) { 
            // Capturar la excepción IllegalArgumentException en caso de que el usuario no ingresó un nombre de clínica válido.
            return "Error:" + "\n" + "(" + e.getMessage() + ")";
        } catch (Exception e) {
            // Capturar cualquier otra excepción que ocurra durante la ejecución del método.
            return "Ocurrió un error durante la ejecución del método" + "\n" + "Error: " + "(" + e.getMessage() + ")";
        }
    }


    /**
     * M�todo para la extensi�n2
     * @return respuesta5
     */
    public String metodo5( )
    {
        return "Respuesta 5";
    }
}
