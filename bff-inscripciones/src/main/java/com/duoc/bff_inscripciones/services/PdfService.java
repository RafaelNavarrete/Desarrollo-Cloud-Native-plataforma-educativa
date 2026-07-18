package com.duoc.bff_inscripciones.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.duoc.bff_inscripciones.model.ResumenInscripcion;

/**
 * Servicio que genera el archivo fisico (PDF) del resumen de inscripcion
 * y lo guarda temporalmente en el sistema de archivos local del contenedor,
 * antes de que sea subido a S3 por S3Service.
 *
 * @author Rafael Navarrete
 */
@Service
public class PdfService {

    @Value("${resumenes.temp.path}")
    private String tempPath;

    // Genera el PDF del resumen y retorna la ruta local del archivo generado
    public String generarResumenPdf(ResumenInscripcion resumen) {
        try {
            Path carpeta = Paths.get(tempPath);
            if (!Files.exists(carpeta)) {
                Files.createDirectories(carpeta);
            }

            String nombreArchivo = "resumen_" + resumen.getNumeroResumen() + ".pdf";
            String rutaCompleta = tempPath + File.separator + nombreArchivo;

            Document documento = new Document();
            PdfWriter.getInstance(documento, new FileOutputStream(rutaCompleta));
            documento.open();
            documento.add(new Paragraph("Resumen de Inscripcion"));
            documento.add(new Paragraph("N° Resumen: " + resumen.getNumeroResumen()));
            documento.add(new Paragraph("Estudiante: " + resumen.getEstudiante()));
            documento.add(new Paragraph("Curso: " + resumen.getCurso()));
            documento.add(new Paragraph("Fecha: " + resumen.getFecha()));
            documento.close();

            return rutaCompleta;

        } catch (Exception e) {
            throw new RuntimeException("Error al generar el PDF del resumen: " + e.getMessage(), e);
        }
    }

    // Elimina el archivo temporal local despues de subirlo a S3
    public void eliminarArchivoTemporal(String rutaArchivo) {
        try {
            Files.deleteIfExists(Paths.get(rutaArchivo));
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el archivo temporal: " + e.getMessage(), e);
        }
    }
}
