package com.example.comerceTrack.Config;

import com.example.comerceTrack.Processor.ProductoItemProcessor;
import com.example.comerceTrack.Producto.Models.Dtos.ProductoRequest;
import com.example.comerceTrack.Producto.Models.Entities.Producto;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class ProductosJobConfig {

    @Bean
    @StepScope
    public FlatFileItemReader<ProductoRequest>reader(@Value("#{jobParameters['filePath']}") String filePath){
        return new FlatFileItemReaderBuilder<ProductoRequest>()
                .name("productoItemReader")
                .resource(new FileSystemResource(filePath))
                .delimited()
                .names("nombre","precio","stock","descripcion","imagenUrl")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>(){{
                    setTargetType(ProductoRequest.class);
                }})
                .linesToSkip(1)
                .build();
    }

    @Bean
    public ItemProcessor<ProductoRequest, Producto>processor(){
        return new ProductoItemProcessor();
    }

    @Bean
    public ItemWriter<Producto>writer(EntityManagerFactory entityManagerFactory){
        return new JpaItemWriterBuilder<Producto>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager,
                      FlatFileItemReader<ProductoRequest>reader,
                      ItemProcessor<ProductoRequest,Producto>processor,
                      ItemWriter<Producto>writer){
        return new StepBuilder("step1",jobRepository)
                .<ProductoRequest,Producto>chunk(5,transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
    @Bean
    public Job importProductoJob(JobRepository jobRepository, Step step1) {
        return new JobBuilder("importProductoJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .build();
    }

}
