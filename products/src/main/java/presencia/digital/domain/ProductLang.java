package presencia.digital.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serializable;
import java.util.Objects;

/**
 * A ProductLang.
 */
@Document(collection = "product_lang")
public class ProductLang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("description")
    private String description;

    @Field("descriptionshort")
    private String descriptionshort;

    @Field("linkrewrite")
    private String linkrewrite;

    @Field("metadescription")
    private String metadescription;

    @Field("metakeywords")
    private String metakeywords;

    @Field("metatitle")
    private String metatitle;

    @Field("availablenow")
    private String availablenow;

    @Field("availablelater")
    private String availablelater;

    @Field("langcode")
    private String langcode;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ProductLang name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public ProductLang description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionshort() {
        return descriptionshort;
    }

    public ProductLang descriptionshort(String descriptionshort) {
        this.descriptionshort = descriptionshort;
        return this;
    }

    public void setDescriptionshort(String descriptionshort) {
        this.descriptionshort = descriptionshort;
    }

    public String getLinkrewrite() {
        return linkrewrite;
    }

    public ProductLang linkrewrite(String linkrewrite) {
        this.linkrewrite = linkrewrite;
        return this;
    }

    public void setLinkrewrite(String linkrewrite) {
        this.linkrewrite = linkrewrite;
    }

    public String getMetadescription() {
        return metadescription;
    }

    public ProductLang metadescription(String metadescription) {
        this.metadescription = metadescription;
        return this;
    }

    public void setMetadescription(String metadescription) {
        this.metadescription = metadescription;
    }

    public String getMetakeywords() {
        return metakeywords;
    }

    public ProductLang metakeywords(String metakeywords) {
        this.metakeywords = metakeywords;
        return this;
    }

    public void setMetakeywords(String metakeywords) {
        this.metakeywords = metakeywords;
    }

    public String getMetatitle() {
        return metatitle;
    }

    public ProductLang metatitle(String metatitle) {
        this.metatitle = metatitle;
        return this;
    }

    public void setMetatitle(String metatitle) {
        this.metatitle = metatitle;
    }

    public String getAvailablenow() {
        return availablenow;
    }

    public ProductLang availablenow(String availablenow) {
        this.availablenow = availablenow;
        return this;
    }

    public void setAvailablenow(String availablenow) {
        this.availablenow = availablenow;
    }

    public String getAvailablelater() {
        return availablelater;
    }

    public ProductLang availablelater(String availablelater) {
        this.availablelater = availablelater;
        return this;
    }

    public void setAvailablelater(String availablelater) {
        this.availablelater = availablelater;
    }

    public String getLangcode() {
        return langcode;
    }

    public ProductLang langcode(String langcode) {
        this.langcode = langcode;
        return this;
    }

    public void setLangcode(String langcode) {
        this.langcode = langcode;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductLang productLang = (ProductLang) o;
        if (productLang.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productLang.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductLang{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", descriptionshort='" + getDescriptionshort() + "'" +
            ", linkrewrite='" + getLinkrewrite() + "'" +
            ", metadescription='" + getMetadescription() + "'" +
            ", metakeywords='" + getMetakeywords() + "'" +
            ", metatitle='" + getMetatitle() + "'" +
            ", availablenow='" + getAvailablenow() + "'" +
            ", availablelater='" + getAvailablelater() + "'" +
            ", langcode='" + getLangcode() + "'" +
            "}";
    }
}
