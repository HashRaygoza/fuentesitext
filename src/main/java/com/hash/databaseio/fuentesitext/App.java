package com.hash.databaseio.fuentesitext;

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.kernel.colors.ColorConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author draygoza
 */
public class App {

    static final private Logger LOGGER = Logger.getLogger("com.hash.databaseio.fuentesitext.App");

    static public void main(String[] args) {
        try {
            File archivo = new File("fuentes.pdf");

            PdfDocument pdf = new PdfDocument(new PdfWriter(archivo));
            try (Document document = new Document(pdf)) {
                // Creamos el objeto para usar la fuente Times Roman
                PdfFont fuenteTimesRoman = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
                // Creamos el objeto para usar la fuente Times Bold
                PdfFont fuenteTimesBold = PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);

                // Texto al que le aplicaremos las fuentes
                Text textoBold = new Text("Texto usando Times Bold ");
                Text textoRoman = new Text("Texto usando Times Roman ");

                textoBold.setFont(fuenteTimesBold);
                textoRoman.setFont(fuenteTimesRoman);

                // Texto que le cambiaremos el tamaño
                Text textoSize = new Text("Texto de otro tamaño");
                textoSize.setFont(fuenteTimesBold);
                textoSize.setFontSize(25f);

                Paragraph parrafo = new Paragraph();

                // Texto de otro color
                Text textoColor = new Text("Texto en otro color ");
                textoColor.setFontColor(ColorConstants.MAGENTA);

                // Fuente externa
                // Indicamos la ruta al archivo de la fuente
                String rutaFuente = "PressStart2P.ttf";
                FontProgram fontProgram = FontProgramFactory.createFont(rutaFuente);
                PdfFont fuenteExterna = PdfFontFactory.createFont(fontProgram, PdfEncodings.WINANSI, true);

                Text textoFuenteExterna = new Text("Texto con una fuente externa ");
                textoFuenteExterna.setFont(fuenteExterna);
                
                // Ahora juntaremos todo
                Text textoTodo =  new Text("Texto al que le aplicamos todo lo aprendido");
                textoTodo.setFont(fuenteExterna);
                textoTodo.setFontColor( ColorConstants.ORANGE );
                textoTodo.setFontSize(45f);

                // Puede usar varias fuentes en un mismo parrafo aplicando la
                // fuente a uno o varios Text y agregandolos al parrafo
                parrafo.add(textoBold);
                parrafo.add(textoRoman);
                parrafo.add(textoSize);
                parrafo.add(textoColor);
                parrafo.add(textoFuenteExterna);
                parrafo.add(textoTodo);

                document.add(parrafo);
            }

        } catch (FileNotFoundException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
}
