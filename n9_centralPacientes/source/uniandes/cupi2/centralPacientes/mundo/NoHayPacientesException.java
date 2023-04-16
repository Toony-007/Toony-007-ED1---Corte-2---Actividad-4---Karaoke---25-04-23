package uniandes.cupi2.centralPacientes.mundo;

public class NoHayPacientesException extends Exception
{

   public NoHayPacientesException(String causa)
   {
       super (causa);
   }
}
