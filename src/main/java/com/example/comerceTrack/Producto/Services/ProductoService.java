package com.example.comerceTrack.Producto.Services;

import com.example.comerceTrack.Producto.Mappers.ProductoMapper;
import com.example.comerceTrack.Producto.Models.Dtos.ProductoReponse;
import com.example.comerceTrack.Producto.Models.Dtos.ProductoRequest;
import com.example.comerceTrack.Producto.Models.Entities.Producto;
import com.example.comerceTrack.Producto.Repositories.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ProductoService{

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final JobLauncher jobLauncher;
    private final Job importProductoJob;

    public ProductoService(ProductoRepository productoRepository, ProductoMapper productoMapper, JobLauncher jobLauncher,@Qualifier("importProductoJob") Job importProductoJob) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
        this.jobLauncher = jobLauncher;
        this.importProductoJob = importProductoJob;
    }

    public ProductoReponse getProducto(Long id){
        Producto producto=productoRepository.findById(id)
                .orElseThrow(()->new RuntimeException("no se encontro el producto"));
        return productoMapper.toDTO(producto);
    }
    public ProductoReponse saveProducto(ProductoRequest productoRequest){
        Producto producto=productoMapper.toEntity(productoRequest);
        Producto saved=productoRepository.save(producto);
        return productoMapper.toDTO(saved);
    }

    public void lanzarJobConArchivo(MultipartFile file) throws Exception {

        Path tempFile= Files.createTempFile("productos-",".csv");
        Files.write(tempFile,file.getBytes());

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("filePath", tempFile.toAbsolutePath().toString())
                .addLong("startAt", System.currentTimeMillis()) // evitar conflicto si lo ejecutás varias veces
                .toJobParameters();

        jobLauncher.run(importProductoJob, jobParameters);
    }

}
