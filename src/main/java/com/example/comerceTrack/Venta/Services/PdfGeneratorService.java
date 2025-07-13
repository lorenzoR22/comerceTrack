package com.example.comerceTrack.Venta.Services;

import com.example.comerceTrack.Venta.Models.Entities.DetalleVenta;
import com.example.comerceTrack.Venta.Models.Entities.Venta;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class PdfGeneratorService {

    private final JobLauncher jobLauncher;
    private final Job generarFacturasJob;

    public PdfGeneratorService(JobLauncher jobLauncher,@Qualifier("generarFacturasJob") Job generarFacturasJob) {
        this.jobLauncher = jobLauncher;
        this.generarFacturasJob = generarFacturasJob;
    }

    public File generarPdfFactura(Venta venta) throws Exception {
        String nombreArchivo = "factura-" + venta.getId() + ".pdf";
        File file = new File("facturas/" + nombreArchivo);
        file.getParentFile().mkdirs(); // Crea carpeta si no existe

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream content = new PDPageContentStream(document, page);
            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 14);
            content.setLeading(16f);
            content.newLineAtOffset(50, 700);

            content.showText("Factura #" + venta.getId());
            content.newLine();
            content.showText("Fecha: " + venta.getFecha());
            content.newLine();
            content.showText("Total: $" + venta.getTotal());
            content.newLine();
            content.showText("Vendedor: " + venta.getUsuario().getNombre());
            content.newLine();
            content.newLine();

            content.setFont(PDType1Font.HELVETICA, 12);
            for (DetalleVenta d : venta.getDetalles()) {
                content.showText("- " + d.getProducto().getNombre() +
                        " x" + d.getCantidad() + " - $" + d.getPrecioUnitario());
                content.newLine();
            }

            content.endText();
            content.close();

            document.save(file);
        }
        return file;
    }

    @Scheduled(fixedRate = 5 * 60 * 1000) //se ejecuta cada cada 5 minutos
    public void lanzarJobConArchivo() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(generarFacturasJob, params);
    }
}
