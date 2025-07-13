package com.example.comerceTrack.Config;

import com.example.comerceTrack.Venta.Models.Entities.Venta;
import com.example.comerceTrack.Venta.Services.PdfGeneratorService;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.File;

@Configuration
@RequiredArgsConstructor
public class FacturasJobConfig {
    private final EntityManagerFactory entityManagerFactory;
    private final PdfGeneratorService pdfGeneratorService;

    @Bean
    public JpaPagingItemReader<Venta> ventaReader() {
        return new JpaPagingItemReaderBuilder<Venta>()
                .name("ventaReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("SELECT v FROM Venta v")
                .pageSize(10)
                .build();
    }

    @Bean
    public ItemProcessor<Venta, File> ventaPdfProcessor() {
        return pdfGeneratorService::generarPdfFactura;
    }

    @Bean
    public ItemWriter<File> pdfWriter() {
        return files -> {
            for (File f : files) {
                System.out.println("Factura PDF generada: " + f.getAbsolutePath());
            }
        };
    }

    @Bean
    public Step generarFacturasStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("generarFacturasStep", jobRepository)
                .<Venta, File>chunk(5, transactionManager)
                .reader(ventaReader())
                .processor(ventaPdfProcessor())
                .writer(pdfWriter())
                .build();
    }

    @Bean
    public Job generarFacturasJob(JobRepository jobRepository, Step generarFacturasStep) {
        return new JobBuilder("generarFacturasJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(generarFacturasStep)
                .build();
    }
}
