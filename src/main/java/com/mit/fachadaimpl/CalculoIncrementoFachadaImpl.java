package com.mit.fachadaimpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mit.dao.IAuditoriaDao;
import com.mit.dao.ICalculoIncrementoDao;
import com.mit.dao.IExclusionesDao;
import com.mit.dao.IMovilRangosIncrementoDao;
import com.mit.dao.IParametrosDao;
import com.mit.dao.IParametrosIncrementosFija;
import com.mit.dao.IUsuarioDao;
import com.mit.dao.IUvtsDao;
import com.mit.entitys.Auditoria;
import com.mit.entitys.CalculoIncremento;
import com.mit.entitys.Exclusiones;
import com.mit.entitys.MovilRangosIncremento;
import com.mit.entitys.ParametrosCalculoFija;
import com.mit.entitys.ParametrosCalculoMovil;
import com.mit.entitys.ParametrosIncrementoFija;
import com.mit.entitys.Uvts;
import com.mit.fachada.ICalculoIncremento;
import com.mit.utils.CantidadesEstados;

import job_imt_extraccion_clientes.job_archivo_entrada_a_eoc_2_0.Job_Archivo_Entrada_a_EOC;
import job_imt_extraccion_clientes.job_archivo_reinyeccion_entrada_a_eoc_2_0.Job_Archivo_Reinyeccion_Entrada_a_EOC;
import local_project.actualizar_imt_tbl_movil_producto_subtipo_oferta_0_1.Actualizar_IMT_TBL_MOVIL_PRODUCTO_SUBTIPO_OFERTA;
import local_project.job_archivo_salida_e_0_1.Job_Archivo_Salida_E;

@Service
public class CalculoIncrementoFachadaImpl implements ICalculoIncremento {
	
	@Autowired
	private IExclusionesDao exclusionesDao;
	
	@Autowired
	private IMovilRangosIncrementoDao movilRangosIncrementosDao;
	
	@Autowired
	private IAuditoriaDao auditDao;
	
	@Autowired
	private ICalculoIncrementoDao calculoIncrementoDao;
	
	@Autowired
	private IParametrosIncrementosFija paramIncFijaDao;
	
	@Autowired
	private IUvtsDao uvtsDao;
	
	@Autowired
	private IParametrosDao paramDao;
	
	@Autowired
	private IUsuarioDao usuDao;
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public ResponseEntity<List<Exclusiones>> guardarExclusiones(MultipartFile file, String user) throws IOException {
		exclusionesDao.deleteAll();
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		Path path = Paths.get(convFile.getPath());
		List<Exclusiones> list = new ArrayList<Exclusiones>();
		Stream<String> streamFile = Files.lines(path);
				list = streamFile.map(linea -> linea.split(",")).filter(p-> !p[0].equalsIgnoreCase("nombre")).map(arreglo->{
					if(!arreglo[0].equalsIgnoreCase("nombre") ) {
						Exclusiones exc = new Exclusiones(arreglo[0].toString(), Integer.parseInt(arreglo[1].toString()), Long.parseLong(arreglo[2].toString()), new Date(), "mcampos");
						return exc;
					}			
					return null;
				}).collect(Collectors.toList());
				for (Exclusiones exclusiones : list) {
					exclusionesDao.save(exclusiones);
				}
		return new ResponseEntity<List<Exclusiones>>(list,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<MovilRangosIncremento>> obtenerMovilRangosIncrementos() throws Exception {		
		List<MovilRangosIncremento> list = (List<MovilRangosIncremento>) movilRangosIncrementosDao.findAll(); 
		return new ResponseEntity<List<MovilRangosIncremento>>(list, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> guardarMRI(MovilRangosIncremento mri, String usuario) throws Exception {
		MovilRangosIncremento movil = movilRangosIncrementosDao.save(mri);
		Auditoria aud = new Auditoria("INSERT IMT_TBL_MOVIL_RANGOS_INCREMENTO ", "IMT_TBL_MOVIL_RANGOS_INCREMENTO", usuario, new Date(), "1");
		Auditoria audit =  auditDao.save(aud);
		if(movil!=null && audit != null) {			
			return new ResponseEntity<String>("MOVIL RANGO INCREMENTO GUARDADO CORRECTAMENTE",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("ERROR GUARDANDO OBJETO",HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> borrarMRI(MovilRangosIncremento mri) throws Exception {
		movilRangosIncrementosDao.delete(mri);
		return new ResponseEntity<String>("Registro Borrado Correctamente", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ParametrosIncrementoFija>> obtenerParamentrosIncrementoFija() throws Exception {
		List<ParametrosIncrementoFija> list = (List<ParametrosIncrementoFija>) paramIncFijaDao.findAll();
		
		return new ResponseEntity<List<ParametrosIncrementoFija>>(list, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> borrarParametrosIncrementoFija(ParametrosIncrementoFija pif) throws Exception {
		paramIncFijaDao.delete(pif);
		return new ResponseEntity<String>("Registro Borrado Correctamente",HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> guardarPIF(ParametrosIncrementoFija pif, String usuario) throws Exception {
		ParametrosIncrementoFija pi = paramIncFijaDao.save(pif);
		Auditoria aud = new Auditoria("Insert imt_tbl_parametros_incremento", "imt_tbl_parametros_incremento", usuario, new Date(), "1");
		Auditoria audit = auditDao.save(aud);
		if(pi!=null && audit!=null) {
			return new ResponseEntity<String>("Registro Guardado Correctamente",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Error realizando la operacion", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@Override
	public ResponseEntity<List<Uvts>> obtenerValoresUvts() throws Exception {
		List<Uvts> listUvts = (List<Uvts>) uvtsDao.findAll();
		return new ResponseEntity<List<Uvts>>(listUvts,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> guardarUvt(Uvts uvt, String usuario) throws Exception {
		StoredProcedureQuery spq = em.createStoredProcedureQuery("Imt_Sp_Agregar_Uvt")
				.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter(2, int.class, ParameterMode.IN)
				.registerStoredProcedureParameter(3, Date.class, ParameterMode.IN)
				.registerStoredProcedureParameter(4, Date.class, ParameterMode.IN)
				.registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(6, String.class, ParameterMode.OUT)
				.setParameter(1, uvt.getVLR_UVT_ACT())
				.setParameter(2, uvt.getANIO_VIGENCIA())
				.setParameter(3, uvt.getFEC_INI_VIGENCIA())
				.setParameter(4, uvt.getFEC_FIN_VIGENCIA())
				.setParameter(5, uvt.getUSER_CREA());
		boolean result = spq.execute();
		em.close();
		return new ResponseEntity<String>(spq.getOutputParameterValue(6).toString(),HttpStatus.OK);			
		
		
	}

	@Override
	public ResponseEntity<String> borrarValorUvt(Uvts uvt) throws Exception {
		uvtsDao.delete(uvt);
		return new ResponseEntity<String>("Registro Borrado Correctamente",HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> obtenerUltimaActualizacionPSO() throws Exception {
		String ultima = auditDao.obtenerUltimaActualizacionPSO();
		return new ResponseEntity<String>(ultima,HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<String> actualizarPso(String usuario) throws Exception {
		Actualizar_IMT_TBL_MOVIL_PRODUCTO_SUBTIPO_OFERTA job = new Actualizar_IMT_TBL_MOVIL_PRODUCTO_SUBTIPO_OFERTA();
		int exitCode = job.runJobInTOS(new String[] {});
		if(exitCode == 0) {
			auditDao.actualizarUsuario(usuario);
			return new ResponseEntity<String>("ACTUALIZACION TABLA PRODUCTO SUBTIPO OFERTA REALIZADA CORRECTAMENTE",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Error realizando la operacion", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	@Override
	public ResponseEntity<String> ejecutarCIM(ParametrosCalculoMovil calculoMovil) throws Exception {
		int prueba = 0;
		ParametrosCalculoMovil pcm = calculoMovil;
		StoredProcedureQuery spq = em.createStoredProcedureQuery("Imt_Incremento_Por_Porcentaje_Para_Movil_X_Contrato_Sp")
				.registerStoredProcedureParameter(1, double.class, ParameterMode.IN)
				.registerStoredProcedureParameter(2, double.class, ParameterMode.IN)
				.registerStoredProcedureParameter(3, double.class, ParameterMode.IN)
				.registerStoredProcedureParameter(4, double.class, ParameterMode.IN)
				.registerStoredProcedureParameter(5, int.class, ParameterMode.IN)
				.registerStoredProcedureParameter(6, int.class, ParameterMode.IN)
				.registerStoredProcedureParameter(7, double.class, ParameterMode.IN)
				.registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(9, int.class, ParameterMode.IN)
				.registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
				.setParameter(1, calculoMovil.getIva())
				.setParameter(2, calculoMovil.getImpoconsumo())
				.setParameter(3, calculoMovil.getPorcion_datos())
				.setParameter(4, calculoMovil.getPorcion_voz())
				.setParameter(5, calculoMovil.getUnidad_redondeo())
				.setParameter(6, calculoMovil.getVigencia_uvt())
				.setParameter(7, calculoMovil.getIncremento_usos_por_defecto())
				.setParameter(8, calculoMovil.getTipo_redondeo_usos())
				.setParameter(9, calculoMovil.getFrecuencia_commit())
				.setParameter(10, calculoMovil.getEstado_a_calcular());
		spq.execute();
		em.close();
		return new ResponseEntity<String>("Ejecucion Calculo Incremento Movil Realizada Correctamente!!",HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> ejecutarCIF(ParametrosCalculoFija calculoFija) throws Exception {
		StoredProcedureQuery spq = em.createStoredProcedureQuery("Imt_Incremento_Para_Fija_X_No_Referencia_Multiplay_Sp")
				.registerStoredProcedureParameter(1, double.class, ParameterMode.IN)
				.registerStoredProcedureParameter(2, double.class, ParameterMode.IN)
				.registerStoredProcedureParameter(3, int.class, ParameterMode.IN)
				.registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
				.setParameter(1, calculoFija.getIva_oficial())
				.setParameter(2, calculoFija.getIncremento_maximo())
				.setParameter(3, calculoFija.getFrecuencia_commit())
				.setParameter(4, calculoFija.getEstado_a_calcular());
		spq.execute();
		em.close();
		return new ResponseEntity<String>("Ejecucion Calculo Incremento Fija Realizado Correctamente",HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> generarArchivoPLM() throws Exception {
		String ruta = System.getProperty("user.home")+File.separator+"Downloads"+File.separator;
		paramDao.guardarRuta(ruta);
		Job_Archivo_Entrada_a_EOC job = new Job_Archivo_Entrada_a_EOC();
		int exitcode = job.runJobInTOS(new String[] {});
		if(exitcode == 0) {
			return new ResponseEntity<String>("Descarga Relizada correctamente ",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Se ha producido un error en la operacion",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@Override
	public List<String> prueba2() throws Exception {
		List<String> list = new ArrayList<String>();
		list = usuDao.queryPersonalizada();
		
		return list;
	}

	@Override
	public ResponseEntity<String> generarArchivoPLMCorregido() throws Exception {
		Job_Archivo_Reinyeccion_Entrada_a_EOC job = new Job_Archivo_Reinyeccion_Entrada_a_EOC();
		int exitcode = job.runJobInTOS(new String[] {});
		if(exitcode == 0) {
			return new ResponseEntity<String>("Descarga relizada correctamente ",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Se ha producido un error en la operación",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<List<CantidadesEstados>> calculoEstados() throws Exception {
		List<CantidadesEstados> list = new ArrayList<>();
		list = calculoIncrementoDao.calculoEstados();
		return new ResponseEntity<List<CantidadesEstados>>(list, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<CalculoIncremento>> calculoPorEstados(String estado) throws Exception {
		List<CalculoIncremento> list = new ArrayList<>();
		if(estado != null) {
			list = calculoIncrementoDao.calculoPorEstados(estado);
		}else {
			list = calculoIncrementoDao.calculoPorEstados();
		}
		return new ResponseEntity<List<CalculoIncremento>>(list, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<BigDecimal>> estratosCFM() throws Exception {
		List<BigDecimal> list = new ArrayList<>();
		list = calculoIncrementoDao.estratosCFM();
		return new ResponseEntity<List<BigDecimal>>(list, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<CalculoIncremento>> cfmPorEstrato(Integer estrato) throws Exception {
		List<CalculoIncremento> list = new ArrayList<>();
		list = calculoIncrementoDao.cfmPorEstrato(estrato);
		return new ResponseEntity<List<CalculoIncremento>>(list, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<CantidadesEstados>> cuentasIncNoInc() throws Exception {
		List<CantidadesEstados> list = new ArrayList<>();
		list = calculoIncrementoDao.cuentasIncNoInc();
		return new ResponseEntity<List<CantidadesEstados>>(list, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<CalculoIncremento>> cuentasIncNoInc(String tipo) throws Exception {
		List<CalculoIncremento> list = new ArrayList<>();
		if(tipo.equals("INC")) {
			list = calculoIncrementoDao.cuentasIncrementadas();
		}else {
			list = calculoIncrementoDao.cuentasNoIncrementadas();
		}
		return new ResponseEntity<List<CalculoIncremento>>(list, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<String>> contratosDANE() throws Exception {
		List<String> list = new ArrayList<>();
		list = calculoIncrementoDao.contratosDANE();
		return new ResponseEntity<List<String>>(list, HttpStatus.OK);
	}



}
