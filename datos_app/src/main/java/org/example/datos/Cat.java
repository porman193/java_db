package org.example.datos;

public class Cat {
    private String id;
    private String url;
    private String apiKey = "live_eZQnIVbihpuy7DB5NzbJMeUfUppIGuMNgJSzBMZ4j43Ul9zvl1GL3I2FMERNeg6p";
    private String image;
    private Long idFavorite = null;
    public Cat() {
    }

    public Cat(String id, String url, String apiKey, String image, Long idFavorite) {
        this.id = id;
        this.url = url;
        this.apiKey = apiKey;
        this.image = image;
        this.idFavorite = idFavorite;
    }

    public Long getIdFavorite() {
        return idFavorite;
    }

    public void setIdFavorite(Long idFavorite) {
        this.idFavorite = idFavorite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
