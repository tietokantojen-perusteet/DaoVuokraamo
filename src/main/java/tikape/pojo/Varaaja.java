package tikape.pojo;

public class Varaaja {

    private Integer asiakasnumero;
    private String etunimi;
    private String sukunimi;

    public Varaaja(Integer asiakasnumero, String etunimi, String sukunimi) {
        this.asiakasnumero = asiakasnumero;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
    }

    public Integer getAsiakasnumero() {
        return asiakasnumero;
    }

    public void setAsiakasnumero(Integer asiakasnumero) {
        this.asiakasnumero = asiakasnumero;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }

}
