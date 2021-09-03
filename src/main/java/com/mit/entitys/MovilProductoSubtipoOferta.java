package com.mit.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "IMT_TBL_MOVIL_PRODUCTO_SUBTIPO_OFERTA")
public class MovilProductoSubtipoOferta {
	
	@Id
	@Column(name = "ID_PRODUCTO", nullable = false)
	private String id_producto;
	
	@Column(name = "SUBTIPO_OFERTA",nullable = false)
	private String subtipo_oferta;

}
