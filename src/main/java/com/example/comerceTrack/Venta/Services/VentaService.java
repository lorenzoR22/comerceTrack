package com.example.comerceTrack.Venta.Services;

import com.example.comerceTrack.Producto.Models.Entities.Producto;
import com.example.comerceTrack.Producto.Repositories.ProductoRepository;
import com.example.comerceTrack.Producto.Services.ProductoService;
import com.example.comerceTrack.Usuario.Models.Entities.Usuario;
import com.example.comerceTrack.Usuario.Repositories.UsuarioRepository;
import com.example.comerceTrack.Usuario.Services.UsuarioService;
import com.example.comerceTrack.Venta.Mappers.VentaMapper;
import com.example.comerceTrack.Venta.Models.Dtos.DetalleVentaRequest;
import com.example.comerceTrack.Venta.Models.Dtos.VentaRequest;
import com.example.comerceTrack.Venta.Models.Dtos.VentaResponse;
import com.example.comerceTrack.Venta.Models.Entities.DetalleVenta;
import com.example.comerceTrack.Venta.Models.Entities.Venta;
import com.example.comerceTrack.Venta.Repositories.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository ventaRepository;
    private final VentaMapper ventaMapper;
    private final UsuarioRepository UsuarioRepository;
    private final ProductoRepository productoRepository;

    public VentaResponse getVenta(Long id){
        Venta venta=ventaRepository.findById(id)
                .orElseThrow(()->new RuntimeException("no se encontro la venta"));
        return ventaMapper.toDTO(venta);
    }

    public VentaResponse saveVenta(VentaRequest ventaRequest){
        Usuario usuario=UsuarioRepository.findById(ventaRequest.usuario_id())
                .orElseThrow(()->new RuntimeException("No se encontro el user"));

        Venta venta=new Venta();
        venta.setUsuario(usuario);

        for(DetalleVentaRequest item:ventaRequest.detalles()){
            Producto producto=productoRepository.findById(item.producto_id())
                    .orElseThrow(()->new RuntimeException("no se encontro el producto"));

            DetalleVenta newItem=productoToDetalleVenta(producto,venta,item.cantidad());

            venta.getDetalles().add(newItem);
            venta.sumarAlTotal(producto.getPrecio().multiply(BigDecimal.valueOf(item.cantidad())));
        }

        Venta saved=ventaRepository.save(venta);
        return ventaMapper.toDTO(saved);
    }

    private DetalleVenta productoToDetalleVenta(Producto producto,Venta venta,Integer cantidad){
         DetalleVenta nuevo=new DetalleVenta();
         nuevo.setProducto(producto);
         nuevo.setVenta(venta);
         nuevo.setCantidad(cantidad);
         nuevo.setPrecioUnitario(producto.getPrecio());
         return nuevo;
    }

}
