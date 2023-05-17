package ra.model.entity;

public class Certificate {
    private int id;
    private String certificateName;
    private String description;
    private String img;
    private int type;

    public Certificate() {
    }

    public Certificate(int id, String certificateName, String description, String img, int type) {
        this.id = id;
        this.certificateName = certificateName;
        this.description = description;
        this.img = img;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "id=" + id +
                ", certificateName='" + certificateName + '\'' +
                ", description='" + description + '\'' +
                ", img='" + img + '\'' +
                ", type=" + type +
                '}';
    }
}
