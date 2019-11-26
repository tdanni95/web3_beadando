package webprog.model;

import org.springframework.format.annotation.DateTimeFormat;
import webprog.exceptions.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hallgato {
    private String neptunKod;
    private String nev;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate szulDatum;
    private Collection<Orak> orak;
    private Allapot allapot;
    private Tipus tipus;


    @Override
    public String toString(){
        return "Hallgato{" +
                "neptunkod="+neptunKod+
                "nev="+nev+
                "Szul datum/" + szulDatum +
                "Orak=" + orak +
                "Allapot=" + allapot + "}";
    }

    public String getNeptunKod() {
        return neptunKod;
    }
    public void setNeptunKod(String neptunKod) throws InvalidNeptunKod {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        Matcher matcher = pattern.matcher(neptunKod);
        if (neptunKod.length() == 6 && matcher.matches()){
            this.neptunKod = neptunKod;
        }
        else{
            throw new InvalidNeptunKod(String.valueOf(neptunKod));
        }
    }

    public String getNev(){
        return nev;
    }
    public String setNev(String nev) throws InvalidNev {
        if (nev.matches("^[a-zA-Z]$")) {
            this.nev = nev;
        }
        else{
            throw new InvalidNev(nev);
        }
    }

    public LocalDate getSzulDatum(){return  szulDatum;}
    public void setSzulDatum(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate szulDatum){
        this.szulDatum = szulDatum;
    }

    public Collection<Orak> getOrak(){return orak;}
    public void setOrak(Collection<Orak> orak){this.orak = orak;}

    public Allapot getAllapot(){return allapot;}
    public void setAllapot(Allapot allapot){
        this.allapot = allapot;
    }

    public Tipus getTipus(){return tipus;}

    public void setTipus(Tipus tipus) {this.tipus = tipus;}

    public Hallgato(String neptunKod, String nev, LocalDate szulDatum, Collection<Orak> orak, Allapot allapot, Tipus tipus){
        this.neptunKod = neptunKod;
        this.nev = nev;
        this.szulDatum = szulDatum;
        this.orak = orak;
        this.allapot = allapot;
        this.tipus = tipus;
    }
}
