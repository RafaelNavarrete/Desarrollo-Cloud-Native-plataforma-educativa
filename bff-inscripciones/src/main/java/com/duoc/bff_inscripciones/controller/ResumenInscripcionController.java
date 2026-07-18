package com.duoc.bff_inscripciones.controller;

import com.duoc.bff_inscripciones.model.ResumenInscripcion;
import com.duoc.bff_inscripciones.services.PdfService;
import com.duoc.bff_inscripciones.services.ResumenProductorService;
import com.duoc.bff_inscripciones.services.S3Service;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST que expone los endpoints del BFF de inscripciones.
 * Orquesta la generacion del PDF, el almacenamiento en S3 y la
 * publicacion del mensaje en la cola de RabbitMQ hacia cursos-service.
 *
 * @author Rafael Navarrete
 */
@RestController
@RequestMapping("/resumenes")
public class ResumenInscripcionController {

    private final PdfService pdfService;
    private final S3Service s3Service;
    private final ResumenProductorService productorService;

    public ResumenInscripcionController(PdfService pdfService, S3Service s3Service, ResumenProductorService productorService) {
        this.pdfService = pdfService;
        this.s3Service = s3Service;
        this.productorService = productorService;
    }

    // POST /resumenes - Genera el PDF, lo sube a S3 y publica el mensaje en la cola
    @PostMapping
    public ResponseEntity<String> crearResumen(@RequestBody ResumenInscripcion resumen) {
        try {
            String rutaLocal = pdfService.generarResumenPdf(resumen);
            String nombreArchivo = "resumen_" + resumen.getNumeroResumen() + ".pdf";

            String key = s3Service.subirArchivo(rutaLocal, resumen.getNumeroResumen(), nombreArchivo);
            pdfService.eliminarArchivoTemporal(rutaLocal);

            productorService.enviarResumen(resumen);

            return ResponseEntity.ok("Resumen creado, almacenado en S3 con clave: " + key + " y enviado a la cola");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    // GET /resumenes/descargar - Descarga el resumen desde S3
    @GetMapping("/descargar")
    public ResponseEntity<byte[]> descargarResumen(
            @RequestParam String numeroResumen,
            @RequestParam String nombreArchivo) {
        try {
            byte[] contenido = s3Service.descargarArchivo(numeroResumen, nombreArchivo);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + nombreArchivo)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(contenido);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // PUT /resumenes - Regenera el PDF con datos actualizados y reemplaza el archivo en S3
    @PutMapping
    public ResponseEntity<String> modificarResumen(@RequestBody ResumenInscripcion resumen) {
        try {
            String rutaLocal = pdfService.generarResumenPdf(resumen);
            String nombreArchivo = "resumen_" + resumen.getNumeroResumen() + ".pdf";

            String key = s3Service.actualizarArchivo(rutaLocal, resumen.getNumeroResumen(), nombreArchivo);
            pdfService.eliminarArchivoTemporal(rutaLocal);

            return ResponseEntity.ok("Resumen actualizado en S3: " + key);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    // DELETE /resumenes - Elimina el archivo del resumen desde S3
    @DeleteMapping
    public ResponseEntity<String> eliminarResumen(
            @RequestParam String numeroResumen,
            @RequestParam String nombreArchivo) {
        try {
            s3Service.eliminarArchivo(numeroResumen, nombreArchivo);
            return ResponseEntity.ok("Resumen eliminado: " + nombreArchivo);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
