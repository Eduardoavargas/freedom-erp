package org.freedom.persist.entity;

// Generated 18/02/2014 15:53:28 by Hibernate Tools 4.0.0

/**
 * VditromaneioId generated by hbm2java
 */
public class VditromaneioId implements java.io.Serializable {

	private int coditroma;
	private int codroma;
	private short codfilial;
	private int codemp;

	public VditromaneioId() {
	}

	public VditromaneioId(int coditroma, int codroma, short codfilial,
			int codemp) {
		this.coditroma = coditroma;
		this.codroma = codroma;
		this.codfilial = codfilial;
		this.codemp = codemp;
	}

	public int getCoditroma() {
		return this.coditroma;
	}

	public void setCoditroma(int coditroma) {
		this.coditroma = coditroma;
	}

	public int getCodroma() {
		return this.codroma;
	}

	public void setCodroma(int codroma) {
		this.codroma = codroma;
	}

	public short getCodfilial() {
		return this.codfilial;
	}

	public void setCodfilial(short codfilial) {
		this.codfilial = codfilial;
	}

	public int getCodemp() {
		return this.codemp;
	}

	public void setCodemp(int codemp) {
		this.codemp = codemp;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VditromaneioId))
			return false;
		VditromaneioId castOther = (VditromaneioId) other;

		return (this.getCoditroma() == castOther.getCoditroma())
				&& (this.getCodroma() == castOther.getCodroma())
				&& (this.getCodfilial() == castOther.getCodfilial())
				&& (this.getCodemp() == castOther.getCodemp());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCoditroma();
		result = 37 * result + this.getCodroma();
		result = 37 * result + this.getCodfilial();
		result = 37 * result + this.getCodemp();
		return result;
	}

}
