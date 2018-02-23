package pe.com.eb.model;

import java.io.Serializable;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class ObjectBean implements Serializable{
	
	

	private static final long serialVersionUID = 1L;


	
	@Column(name="com_crea")
	private String comCrea;
	
	@Column(name="fec_crea")
	private Date fecCrea;
	
	@Column(name="usu_crea")
	private String usuCrea;

	@Column(name="com_modi")
	private String comModi;

	@Column(name="fec_modi")
	private Date fecModi;

	@Column(name="usu_modi")
	private String usuModi;


	public String getComCrea() {
		return comCrea;
	}

	public void setComCrea(String comCrea) {
		this.comCrea = comCrea;
	}

	public Date getFecCrea() {
		return fecCrea;
	}

	public void setFecCrea(Date fecCrea) {
		this.fecCrea = fecCrea;
	}

	public String getUsuCrea() {
		return usuCrea;
	}

	public void setUsuCrea(String usuCrea) {
		this.usuCrea = usuCrea;
	}

	public String getComModi() {
		return comModi;
	}

	public void setComModi(String comModi) {
		this.comModi = comModi;
	}

	public Date getFecModi() {
		return fecModi;
	}

	public void setFecModi(Date fecModi) {
		this.fecModi = fecModi;
	}

	public String getUsuModi() {
		return usuModi;
	}

	public void setUsuModi(String usuModi) {
		this.usuModi = usuModi;
	}
	
	
	

}
