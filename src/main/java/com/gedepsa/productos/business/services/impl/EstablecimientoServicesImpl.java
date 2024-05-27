package com.gedepsa.productos.business.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gedepsa.productos.business.services.EstablecimientoServices;
import com.gedepsa.productos.business.services.model.DatosContacto;
import com.gedepsa.productos.business.services.model.Direccion;
import com.gedepsa.productos.business.services.model.Establecimiento;

@Service
public class EstablecimientoServicesImpl implements EstablecimientoServices {

	private final Map<Long, Establecimiento> ESTABLECIMIENTOS = new HashMap<>();
	
	public EstablecimientoServicesImpl() {
		init();
	}
	
	@Override
	public Long create(Establecimiento establecimiento) {
		
		if(establecimiento.getCodigo() != null) {
			throw new IllegalStateException("Para crear un establecimiento su código debe ser null");
		}
		
		Long codigo = System.currentTimeMillis();
		establecimiento.setCodigo(codigo);
		
		ESTABLECIMIENTOS.put(establecimiento.getCodigo(), establecimiento);
		
		return codigo;
	}
	
	@Override
	public Optional<Establecimiento> read(Long id) {
		return Optional.ofNullable(ESTABLECIMIENTOS.get(id));
	}

	@Override
	public List<Establecimiento> getAll() {
		return new ArrayList<>(ESTABLECIMIENTOS.values());
	}

	// ******************************************************************
	//
	// Private Methods
	//
	// ******************************************************************
		
	private void init() {
		
		Establecimiento establecimiento1 = new Establecimiento();
		Establecimiento establecimiento2 = new Establecimiento();
		
		Direccion direccion1 = new Direccion();
		DatosContacto datosContacto1 = new DatosContacto();
		
		Direccion direccion2 = new Direccion();
		DatosContacto datosContacto2 = new DatosContacto();
		
		direccion1.setDireccion("Avinguda de la Granvia de l'Hospitalet, 75");
		direccion1.setPoblacion("L'Hospitalet de Llobregat");
		direccion1.setCodigoPostal("08908");
		direccion1.setProvincia("Barcelona");
		direccion1.setPais("España");
		
		datosContacto1.setTelefono("932591572");
		datosContacto1.setEmail("granvia2@pollosamigos.com");

		direccion2.setDireccion("Avda. Monforte de Lemos, 36");
		direccion2.setPoblacion("Fuencarral - El Pardo");
		direccion2.setCodigoPostal("28029");
		direccion2.setProvincia("Madrid");
		direccion2.setPais("España");
		
		datosContacto2.setTelefono("91 7301000");
		datosContacto2.setEmail("lavaguada@pollosamigos.com");
		
		establecimiento1.setCodigo(100L);
		establecimiento1.setNombreComercial("Pollos Amigos - BCN");
		establecimiento1.setDireccion(direccion1);
		establecimiento1.setDatosContacto(datosContacto1);
		
		establecimiento2.setCodigo(101L);
		establecimiento2.setNombreComercial("Pollos Amigos - Vaguada");
		establecimiento2.setDireccion(direccion2);
		establecimiento2.setDatosContacto(datosContacto2);
		
		ESTABLECIMIENTOS.put(establecimiento1.getCodigo(), establecimiento1);
		ESTABLECIMIENTOS.put(establecimiento2.getCodigo(), establecimiento2);
	}
	
}
