package spring.example.SpringDI;

public class Address {

    private String name;

    private String country;

    private String postcode;

    @Override
    public String toString() {
        return "{name=" + name
                + ", country=" + country
                + ", postcode=" + postcode + "}";
    }

    //===== getter and setter =====//
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

}
