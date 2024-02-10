package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {

        String rutaSQL = "sqlSentence.txt";
        
        BufferTextoSQL buffer = new BufferTextoSQL(rutaSQL);
        
        buffer.mostrarTexto();
        
        buffer.encontrarOcurrencias();
        
        buffer.mostrarOcurrencias();

    }

    public static class BufferTextoSQL {

        private String rutaTexto;
        private String contenidoTexto;
        private List<String> arrayOcurrencias;
        private int numeroOcurrencias;
        private BufferedReader bufferTexto;

        public BufferTextoSQL(String rutaTexto) throws IOException {
            this.rutaTexto = rutaTexto;
            this.contenidoTexto = "";
            this.arrayOcurrencias = new ArrayList<>();
            this.numeroOcurrencias = 0;

            //Ya que al construir el objeto intentamos leer un archivo. Por lo tanto puede que retorne un error de funcion
            try {
                this.bufferTexto = new BufferedReader(new FileReader(rutaTexto, StandardCharsets.UTF_8));
                this.leerTexto();
            } catch (IOException ioE) {

            }

        }

        private void leerTexto() throws IOException {
            //Extraemos linea por linea el contenido del texto
            String linea = bufferTexto.readLine();
            //Mientras haya lineas por leer 
            while (linea != null) {
                //Adjuntar al contenido del texto
                this.contenidoTexto = contenidoTexto.concat(linea).concat("\n");
                linea = bufferTexto.readLine();
            }
            //Cerrar el buffer una vez traido todas las lineas del texto
            bufferTexto.close();
        }

        public void mostrarTexto() {
            System.out.println("Texto de la ruta : " + this.rutaTexto);
            System.out.println(this.contenidoTexto);
            System.out.println();
        }

        public void encontrarOcurrencias() {
            Pattern p = Pattern.compile("\\b[Ss][Ee][Ll][Ee][Cc][Tt]\\b\\s+[Cc][Oo][Uu][Nn][Tt]\\s*\\(\\s*\\*\\s*\\)\\s+\\b[Ff][Rr][Oo][Mm]\\b\\s+[a-zA-Z_][a-zA-Z0-9_]*", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(this.contenidoTexto);

            while (m.find()) {
                String match = m.group();
                this.arrayOcurrencias.add(match);
            }
            this.numeroOcurrencias = arrayOcurrencias.size();
            
        }

        public void mostrarOcurrencias() {
            System.out.println("El numero de coincidencias con SELECT COUNT(*) FROM <TABLA> es : " + this.numeroOcurrencias);
            int index = 0;
            for (String sentencia : arrayOcurrencias) {
                System.out.println("#" + index);
                System.out.println(sentencia);
                index++;
            }
        }

        //Getters y Setters
        public String getRutaTexto() {
            return rutaTexto;
        }

        public void setRutaTexto(String rutaTexto) {
            this.rutaTexto = rutaTexto;
        }

        public String getContenidoTexto() {
            return contenidoTexto;
        }

        public void setContenidoTexto(String contenidoTexto) {
            this.contenidoTexto = contenidoTexto;
        }

        public List<String> getArrayOcurrencias() {
            return arrayOcurrencias;
        }

        public void setArrayOcurrencias(List<String> arrayOcurrencias) {
            this.arrayOcurrencias = arrayOcurrencias;
        }

        public int getNumeroOcurrencias() {
            return numeroOcurrencias;
        }

        public void setNumeroOcurrencias(int numeroOcurrencias) {
            this.numeroOcurrencias = numeroOcurrencias;
        }

        public BufferedReader getBufferTexto() {
            return bufferTexto;
        }

        public void setBufferTexto(BufferedReader bufferTexto) {
            this.bufferTexto = bufferTexto;
        }

    }

}
