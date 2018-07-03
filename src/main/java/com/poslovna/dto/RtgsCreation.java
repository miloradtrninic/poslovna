package com.poslovna.dto;

import java.util.Date;

public class RtgsCreation {
	private Long id;
	private String svrhaPlacanja;
	private Date datumNaloga;
	private Date datumValute;
	private int modelZaduzenja;
	private String pozivNaBrojZaduzenja;
	private int modelOdobrenja;
	private String sifraValute;
	private String pozivNaBrojOdobrenja;
	private double iznos;
	private String obracunskiRacunDuznika;
	private String obracunskiRacunPoverioca;
	private String duznik;
	private String primalac;
	private String swiftDuznika;
	private String swiftPoverioca;
	private String racunDuznika;
	private String racunPoverioca;
	
	public RtgsCreation() {
		
	}

	public RtgsCreation(Long id, String svrhaPlacanja, Date datumNaloga, Date datumValute, int modelZaduzenja,
			String pozivNaBrojZaduzenja, int modelOdobrenja, String sifraValute, String pozivNaBrojOdobrenja,
			double iznos, String obracunskiRacunDuznika, String obracunskiRacunPoverioca, String duznik,
			String primalac, String swiftDuznika, String swiftPoverioca, String racunDuznika, String racunPoverioca) {
		super();
		this.id = id;
		this.svrhaPlacanja = svrhaPlacanja;
		this.datumNaloga = datumNaloga;
		this.datumValute = datumValute;
		this.modelZaduzenja = modelZaduzenja;
		this.pozivNaBrojZaduzenja = pozivNaBrojZaduzenja;
		this.modelOdobrenja = modelOdobrenja;
		this.sifraValute = sifraValute;
		this.pozivNaBrojOdobrenja = pozivNaBrojOdobrenja;
		this.iznos = iznos;
		this.obracunskiRacunDuznika = obracunskiRacunDuznika;
		this.obracunskiRacunPoverioca = obracunskiRacunPoverioca;
		this.duznik = duznik;
		this.primalac = primalac;
		this.swiftDuznika = swiftDuznika;
		this.swiftPoverioca = swiftPoverioca;
		this.racunDuznika = racunDuznika;
		this.racunPoverioca = racunPoverioca;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSvrhaPlacanja() {
		return svrhaPlacanja;
	}

	public void setSvrhaPlacanja(String svrhaPlacanja) {
		this.svrhaPlacanja = svrhaPlacanja;
	}

	public Date getDatumNaloga() {
		return datumNaloga;
	}

	public void setDatumNaloga(Date datumNaloga) {
		this.datumNaloga = datumNaloga;
	}

	public Date getDatumValute() {
		return datumValute;
	}

	public void setDatumValute(Date datumValute) {
		this.datumValute = datumValute;
	}

	public int getModelZaduzenja() {
		return modelZaduzenja;
	}

	public void setModelZaduzenja(int modelZaduzenja) {
		this.modelZaduzenja = modelZaduzenja;
	}

	public String getPozivNaBrojZaduzenja() {
		return pozivNaBrojZaduzenja;
	}

	public void setPozivNaBrojZaduzenja(String pozivNaBrojZaduzenja) {
		this.pozivNaBrojZaduzenja = pozivNaBrojZaduzenja;
	}

	public int getModelOdobrenja() {
		return modelOdobrenja;
	}

	public void setModelOdobrenja(int modelOdobrenja) {
		this.modelOdobrenja = modelOdobrenja;
	}

	public String getSifraValute() {
		return sifraValute;
	}

	public void setSifraValute(String sifraValute) {
		this.sifraValute = sifraValute;
	}

	public String getPozivNaBrojOdobrenja() {
		return pozivNaBrojOdobrenja;
	}

	public void setPozivNaBrojOdobrenja(String pozivNaBrojOdobrenja) {
		this.pozivNaBrojOdobrenja = pozivNaBrojOdobrenja;
	}

	public double getIznos() {
		return iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}

	public String getObracunskiRacunDuznika() {
		return obracunskiRacunDuznika;
	}

	public void setObracunskiRacunDuznika(String obracunskiRacunDuznika) {
		this.obracunskiRacunDuznika = obracunskiRacunDuznika;
	}

	public String getObracunskiRacunPoverioca() {
		return obracunskiRacunPoverioca;
	}

	public void setObracunskiRacunPoverioca(String obracunskiRacunPoverioca) {
		this.obracunskiRacunPoverioca = obracunskiRacunPoverioca;
	}

	public String getDuznik() {
		return duznik;
	}

	public void setDuznik(String duznik) {
		this.duznik = duznik;
	}

	public String getPrimalac() {
		return primalac;
	}

	public void setPrimalac(String primalac) {
		this.primalac = primalac;
	}

	public String getSwiftDuznika() {
		return swiftDuznika;
	}

	public void setSwiftDuznika(String swiftDuznika) {
		this.swiftDuznika = swiftDuznika;
	}

	public String getSwiftPoverioca() {
		return swiftPoverioca;
	}

	public void setSwiftPoverioca(String swiftPoverioca) {
		this.swiftPoverioca = swiftPoverioca;
	}

	public String getRacunDuznika() {
		return racunDuznika;
	}

	public void setRacunDuznika(String racunDuznika) {
		this.racunDuznika = racunDuznika;
	}

	public String getRacunPoverioca() {
		return racunPoverioca;
	}

	public void setRacunPoverioca(String racunPoverioca) {
		this.racunPoverioca = racunPoverioca;
	}
	
	
}
