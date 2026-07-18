package com.duoc.bff_inscripciones.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.nio.file.Paths;

/**
 * Servicio que gestiona todas las operaciones contra AWS S3 para los
 * resumenes de inscripcion. Cada resumen se guarda en una carpeta cuyo
 * nombre corresponde a su numero de resumen, ej: /12345/resumen_12345.pdf
 *
 * @author Rafael Navarrete
 */
@Service
public class S3Service {

    @Value("${aws.region}")
    private String region;

    @Value("${aws.s3.bucket}")
    private String bucket;

    private S3Client getClient() {
        return S3Client.builder().region(Region.of(region)).build();
    }

    // Sube el PDF local a S3, dentro de una carpeta con el numero de resumen
    public String subirArchivo(String rutaLocal, String numeroResumen, String nombreArchivo) {
        String key = numeroResumen + "/" + nombreArchivo;
        try (S3Client s3 = getClient()) {
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build();
            s3.putObject(request, RequestBody.fromFile(Paths.get(rutaLocal)));
        }
        return key;
    }

    // Descarga el contenido del resumen desde S3
    public byte[] descargarArchivo(String numeroResumen, String nombreArchivo) {
        String key = numeroResumen + "/" + nombreArchivo;
        try (S3Client s3 = getClient()) {
            GetObjectRequest request = GetObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build();
            return s3.getObjectAsBytes(request).asByteArray();
        }
    }

    // Reemplaza el archivo del resumen en S3 (mismo key)
    public String actualizarArchivo(String rutaLocal, String numeroResumen, String nombreArchivo) {
        return subirArchivo(rutaLocal, numeroResumen, nombreArchivo);
    }

    // Elimina el archivo del resumen desde S3
    public void eliminarArchivo(String numeroResumen, String nombreArchivo) {
        String key = numeroResumen + "/" + nombreArchivo;
        try (S3Client s3 = getClient()) {
            DeleteObjectRequest request = DeleteObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build();
            s3.deleteObject(request);
        }
    }
}
