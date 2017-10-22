package presencia.digital.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Category.
 */
@Document(collection = "category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    @Field("idparent")
    private Instant idparent;

    @Field("idshopdefault")
    private Instant idshopdefault;

    @Field("leveldepth")
    private Integer leveldepth;

    @Field("nleft")
    private Integer nleft;

    @Field("nright")
    private Integer nright;

    @Field("active")
    private Boolean active;

    @Field("dateadd")
    private LocalDate dateadd;

    @Field("dateupd")
    private LocalDate dateupd;

    @Field("position")
    private Integer position;

    @Field("isrootcategory")
    private Boolean isrootcategory;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getIdparent() {
        return idparent;
    }

    public Category idparent(Instant idparent) {
        this.idparent = idparent;
        return this;
    }

    public void setIdparent(Instant idparent) {
        this.idparent = idparent;
    }

    public Instant getIdshopdefault() {
        return idshopdefault;
    }

    public Category idshopdefault(Instant idshopdefault) {
        this.idshopdefault = idshopdefault;
        return this;
    }

    public void setIdshopdefault(Instant idshopdefault) {
        this.idshopdefault = idshopdefault;
    }

    public Integer getLeveldepth() {
        return leveldepth;
    }

    public Category leveldepth(Integer leveldepth) {
        this.leveldepth = leveldepth;
        return this;
    }

    public void setLeveldepth(Integer leveldepth) {
        this.leveldepth = leveldepth;
    }

    public Integer getNleft() {
        return nleft;
    }

    public Category nleft(Integer nleft) {
        this.nleft = nleft;
        return this;
    }

    public void setNleft(Integer nleft) {
        this.nleft = nleft;
    }

    public Integer getNright() {
        return nright;
    }

    public Category nright(Integer nright) {
        this.nright = nright;
        return this;
    }

    public void setNright(Integer nright) {
        this.nright = nright;
    }

    public Boolean isActive() {
        return active;
    }

    public Category active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDate getDateadd() {
        return dateadd;
    }

    public Category dateadd(LocalDate dateadd) {
        this.dateadd = dateadd;
        return this;
    }

    public void setDateadd(LocalDate dateadd) {
        this.dateadd = dateadd;
    }

    public LocalDate getDateupd() {
        return dateupd;
    }

    public Category dateupd(LocalDate dateupd) {
        this.dateupd = dateupd;
        return this;
    }

    public void setDateupd(LocalDate dateupd) {
        this.dateupd = dateupd;
    }

    public Integer getPosition() {
        return position;
    }

    public Category position(Integer position) {
        this.position = position;
        return this;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Boolean isIsrootcategory() {
        return isrootcategory;
    }

    public Category isrootcategory(Boolean isrootcategory) {
        this.isrootcategory = isrootcategory;
        return this;
    }

    public void setIsrootcategory(Boolean isrootcategory) {
        this.isrootcategory = isrootcategory;
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
        Category category = (Category) o;
        if (category.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), category.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Category{" +
            "id=" + getId() +
            ", idparent='" + getIdparent() + "'" +
            ", idshopdefault='" + getIdshopdefault() + "'" +
            ", leveldepth='" + getLeveldepth() + "'" +
            ", nleft='" + getNleft() + "'" +
            ", nright='" + getNright() + "'" +
            ", active='" + isActive() + "'" +
            ", dateadd='" + getDateadd() + "'" +
            ", dateupd='" + getDateupd() + "'" +
            ", position='" + getPosition() + "'" +
            ", isrootcategory='" + isIsrootcategory() + "'" +
            "}";
    }
}
