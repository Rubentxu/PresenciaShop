package presencia.digital.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import presencia.digital.domain.enumeration.RedirectType;

import presencia.digital.domain.enumeration.Condition;

import presencia.digital.domain.enumeration.Visibility;

/**
 * A Product.
 */
@Document(collection = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    @Field("idsupplier")
    private Long idsupplier;

    @Field("idmanufacturer")
    private Long idmanufacturer;

    @NotNull
    @Field("idcategorydefault")
    private Long idcategorydefault;

    @NotNull
    @Field("idshopdefault")
    private Long idshopdefault;

    @NotNull
    @Field("idtaxtrulesgroup")
    private Long idtaxtrulesgroup;

    @NotNull
    @Field("onsale")
    private Boolean onsale;

    @NotNull
    @Field("onlineonly")
    private Boolean onlineonly;

    @Field("ean_13")
    private String ean13;

    @Field("isbn")
    private String isbn;

    @Field("upc")
    private String upc;

    @NotNull
    @Field("ecotax")
    private Float ecotax;

    @NotNull
    @Field("quantity")
    private Integer quantity;

    @NotNull
    @Field("minimalquantity")
    private Integer minimalquantity;

    @NotNull
    @Field("price")
    private Float price;

    @NotNull
    @Field("wholesaleprice")
    private Float wholesaleprice;

    @Field("unity")
    private String unity;

    @NotNull
    @Field("unitpriceratio")
    private Float unitpriceratio;

    @NotNull
    @Field("additionalshippingcost")
    private Float additionalshippingcost;

    @Field("reference")
    private String reference;

    @Field("supplierreference")
    private String supplierreference;

    @Field("location")
    private String location;

    @NotNull
    @Field("width")
    private Float width;

    @NotNull
    @Field("height")
    private Float height;

    @NotNull
    @Field("depth")
    private Float depth;

    @NotNull
    @Field("weight")
    private Float weight;

    @NotNull
    @Field("outofstock")
    private Integer outofstock;

    @Field("quantitydiscount")
    private Boolean quantitydiscount;

    @NotNull
    @Field("customizable")
    private Integer customizable;

    @NotNull
    @Field("uploadablefiles")
    private Integer uploadablefiles;

    @NotNull
    @Field("textfields")
    private Integer textfields;

    @NotNull
    @Field("active")
    private Integer active;

    @NotNull
    @Field("redirecttype")
    private RedirectType redirecttype;

    @NotNull
    @Field("idtyperedirected")
    private Integer idtyperedirected;

    @NotNull
    @Field("availablefororder")
    private Boolean availablefororder;

    @Field("availabledate")
    private LocalDate availabledate;

    @NotNull
    @Field("showcondition")
    private Boolean showcondition;

    @NotNull
    @Field("condition")
    private Condition condition;

    @NotNull
    @Field("showprice")
    private Boolean showprice;

    @NotNull
    @Field("indexed")
    private Boolean indexed;

    @NotNull
    @Field("visibility")
    private Visibility visibility;

    @NotNull
    @Field("cacheispack")
    private Boolean cacheispack;

    @NotNull
    @Field("cachehasatachments")
    private Boolean cachehasatachments;

    @NotNull
    @Field("isvirtual")
    private Boolean isvirtual;

    @Field("cachedefaultattribute")
    private Integer cachedefaultattribute;

    @NotNull
    @Field("dateadd")
    private LocalDate dateadd;

    @NotNull
    @Field("dateupd")
    private LocalDate dateupd;

    @NotNull
    @Field("advancedstockmanagement")
    private Boolean advancedstockmanagement;

    @NotNull
    @Field("packstocktype")
    private Boolean packstocktype;

    @NotNull
    @Field("state")
    private Boolean state;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIdsupplier() {
        return idsupplier;
    }

    public Product idsupplier(Long idsupplier) {
        this.idsupplier = idsupplier;
        return this;
    }

    public void setIdsupplier(Long idsupplier) {
        this.idsupplier = idsupplier;
    }

    public Long getIdmanufacturer() {
        return idmanufacturer;
    }

    public Product idmanufacturer(Long idmanufacturer) {
        this.idmanufacturer = idmanufacturer;
        return this;
    }

    public void setIdmanufacturer(Long idmanufacturer) {
        this.idmanufacturer = idmanufacturer;
    }

    public Long getIdcategorydefault() {
        return idcategorydefault;
    }

    public Product idcategorydefault(Long idcategorydefault) {
        this.idcategorydefault = idcategorydefault;
        return this;
    }

    public void setIdcategorydefault(Long idcategorydefault) {
        this.idcategorydefault = idcategorydefault;
    }

    public Long getIdshopdefault() {
        return idshopdefault;
    }

    public Product idshopdefault(Long idshopdefault) {
        this.idshopdefault = idshopdefault;
        return this;
    }

    public void setIdshopdefault(Long idshopdefault) {
        this.idshopdefault = idshopdefault;
    }

    public Long getIdtaxtrulesgroup() {
        return idtaxtrulesgroup;
    }

    public Product idtaxtrulesgroup(Long idtaxtrulesgroup) {
        this.idtaxtrulesgroup = idtaxtrulesgroup;
        return this;
    }

    public void setIdtaxtrulesgroup(Long idtaxtrulesgroup) {
        this.idtaxtrulesgroup = idtaxtrulesgroup;
    }

    public Boolean isOnsale() {
        return onsale;
    }

    public Product onsale(Boolean onsale) {
        this.onsale = onsale;
        return this;
    }

    public void setOnsale(Boolean onsale) {
        this.onsale = onsale;
    }

    public Boolean isOnlineonly() {
        return onlineonly;
    }

    public Product onlineonly(Boolean onlineonly) {
        this.onlineonly = onlineonly;
        return this;
    }

    public void setOnlineonly(Boolean onlineonly) {
        this.onlineonly = onlineonly;
    }

    public String getEan13() {
        return ean13;
    }

    public Product ean13(String ean13) {
        this.ean13 = ean13;
        return this;
    }

    public void setEan13(String ean13) {
        this.ean13 = ean13;
    }

    public String getIsbn() {
        return isbn;
    }

    public Product isbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getUpc() {
        return upc;
    }

    public Product upc(String upc) {
        this.upc = upc;
        return this;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public Float getEcotax() {
        return ecotax;
    }

    public Product ecotax(Float ecotax) {
        this.ecotax = ecotax;
        return this;
    }

    public void setEcotax(Float ecotax) {
        this.ecotax = ecotax;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Product quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getMinimalquantity() {
        return minimalquantity;
    }

    public Product minimalquantity(Integer minimalquantity) {
        this.minimalquantity = minimalquantity;
        return this;
    }

    public void setMinimalquantity(Integer minimalquantity) {
        this.minimalquantity = minimalquantity;
    }

    public Float getPrice() {
        return price;
    }

    public Product price(Float price) {
        this.price = price;
        return this;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getWholesaleprice() {
        return wholesaleprice;
    }

    public Product wholesaleprice(Float wholesaleprice) {
        this.wholesaleprice = wholesaleprice;
        return this;
    }

    public void setWholesaleprice(Float wholesaleprice) {
        this.wholesaleprice = wholesaleprice;
    }

    public String getUnity() {
        return unity;
    }

    public Product unity(String unity) {
        this.unity = unity;
        return this;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public Float getUnitpriceratio() {
        return unitpriceratio;
    }

    public Product unitpriceratio(Float unitpriceratio) {
        this.unitpriceratio = unitpriceratio;
        return this;
    }

    public void setUnitpriceratio(Float unitpriceratio) {
        this.unitpriceratio = unitpriceratio;
    }

    public Float getAdditionalshippingcost() {
        return additionalshippingcost;
    }

    public Product additionalshippingcost(Float additionalshippingcost) {
        this.additionalshippingcost = additionalshippingcost;
        return this;
    }

    public void setAdditionalshippingcost(Float additionalshippingcost) {
        this.additionalshippingcost = additionalshippingcost;
    }

    public String getReference() {
        return reference;
    }

    public Product reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getSupplierreference() {
        return supplierreference;
    }

    public Product supplierreference(String supplierreference) {
        this.supplierreference = supplierreference;
        return this;
    }

    public void setSupplierreference(String supplierreference) {
        this.supplierreference = supplierreference;
    }

    public String getLocation() {
        return location;
    }

    public Product location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Float getWidth() {
        return width;
    }

    public Product width(Float width) {
        this.width = width;
        return this;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getHeight() {
        return height;
    }

    public Product height(Float height) {
        this.height = height;
        return this;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getDepth() {
        return depth;
    }

    public Product depth(Float depth) {
        this.depth = depth;
        return this;
    }

    public void setDepth(Float depth) {
        this.depth = depth;
    }

    public Float getWeight() {
        return weight;
    }

    public Product weight(Float weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Integer getOutofstock() {
        return outofstock;
    }

    public Product outofstock(Integer outofstock) {
        this.outofstock = outofstock;
        return this;
    }

    public void setOutofstock(Integer outofstock) {
        this.outofstock = outofstock;
    }

    public Boolean isQuantitydiscount() {
        return quantitydiscount;
    }

    public Product quantitydiscount(Boolean quantitydiscount) {
        this.quantitydiscount = quantitydiscount;
        return this;
    }

    public void setQuantitydiscount(Boolean quantitydiscount) {
        this.quantitydiscount = quantitydiscount;
    }

    public Integer getCustomizable() {
        return customizable;
    }

    public Product customizable(Integer customizable) {
        this.customizable = customizable;
        return this;
    }

    public void setCustomizable(Integer customizable) {
        this.customizable = customizable;
    }

    public Integer getUploadablefiles() {
        return uploadablefiles;
    }

    public Product uploadablefiles(Integer uploadablefiles) {
        this.uploadablefiles = uploadablefiles;
        return this;
    }

    public void setUploadablefiles(Integer uploadablefiles) {
        this.uploadablefiles = uploadablefiles;
    }

    public Integer getTextfields() {
        return textfields;
    }

    public Product textfields(Integer textfields) {
        this.textfields = textfields;
        return this;
    }

    public void setTextfields(Integer textfields) {
        this.textfields = textfields;
    }

    public Integer getActive() {
        return active;
    }

    public Product active(Integer active) {
        this.active = active;
        return this;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public RedirectType getRedirecttype() {
        return redirecttype;
    }

    public Product redirecttype(RedirectType redirecttype) {
        this.redirecttype = redirecttype;
        return this;
    }

    public void setRedirecttype(RedirectType redirecttype) {
        this.redirecttype = redirecttype;
    }

    public Integer getIdtyperedirected() {
        return idtyperedirected;
    }

    public Product idtyperedirected(Integer idtyperedirected) {
        this.idtyperedirected = idtyperedirected;
        return this;
    }

    public void setIdtyperedirected(Integer idtyperedirected) {
        this.idtyperedirected = idtyperedirected;
    }

    public Boolean isAvailablefororder() {
        return availablefororder;
    }

    public Product availablefororder(Boolean availablefororder) {
        this.availablefororder = availablefororder;
        return this;
    }

    public void setAvailablefororder(Boolean availablefororder) {
        this.availablefororder = availablefororder;
    }

    public LocalDate getAvailabledate() {
        return availabledate;
    }

    public Product availabledate(LocalDate availabledate) {
        this.availabledate = availabledate;
        return this;
    }

    public void setAvailabledate(LocalDate availabledate) {
        this.availabledate = availabledate;
    }

    public Boolean isShowcondition() {
        return showcondition;
    }

    public Product showcondition(Boolean showcondition) {
        this.showcondition = showcondition;
        return this;
    }

    public void setShowcondition(Boolean showcondition) {
        this.showcondition = showcondition;
    }

    public Condition getCondition() {
        return condition;
    }

    public Product condition(Condition condition) {
        this.condition = condition;
        return this;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Boolean isShowprice() {
        return showprice;
    }

    public Product showprice(Boolean showprice) {
        this.showprice = showprice;
        return this;
    }

    public void setShowprice(Boolean showprice) {
        this.showprice = showprice;
    }

    public Boolean isIndexed() {
        return indexed;
    }

    public Product indexed(Boolean indexed) {
        this.indexed = indexed;
        return this;
    }

    public void setIndexed(Boolean indexed) {
        this.indexed = indexed;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public Product visibility(Visibility visibility) {
        this.visibility = visibility;
        return this;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public Boolean isCacheispack() {
        return cacheispack;
    }

    public Product cacheispack(Boolean cacheispack) {
        this.cacheispack = cacheispack;
        return this;
    }

    public void setCacheispack(Boolean cacheispack) {
        this.cacheispack = cacheispack;
    }

    public Boolean isCachehasatachments() {
        return cachehasatachments;
    }

    public Product cachehasatachments(Boolean cachehasatachments) {
        this.cachehasatachments = cachehasatachments;
        return this;
    }

    public void setCachehasatachments(Boolean cachehasatachments) {
        this.cachehasatachments = cachehasatachments;
    }

    public Boolean isIsvirtual() {
        return isvirtual;
    }

    public Product isvirtual(Boolean isvirtual) {
        this.isvirtual = isvirtual;
        return this;
    }

    public void setIsvirtual(Boolean isvirtual) {
        this.isvirtual = isvirtual;
    }

    public Integer getCachedefaultattribute() {
        return cachedefaultattribute;
    }

    public Product cachedefaultattribute(Integer cachedefaultattribute) {
        this.cachedefaultattribute = cachedefaultattribute;
        return this;
    }

    public void setCachedefaultattribute(Integer cachedefaultattribute) {
        this.cachedefaultattribute = cachedefaultattribute;
    }

    public LocalDate getDateadd() {
        return dateadd;
    }

    public Product dateadd(LocalDate dateadd) {
        this.dateadd = dateadd;
        return this;
    }

    public void setDateadd(LocalDate dateadd) {
        this.dateadd = dateadd;
    }

    public LocalDate getDateupd() {
        return dateupd;
    }

    public Product dateupd(LocalDate dateupd) {
        this.dateupd = dateupd;
        return this;
    }

    public void setDateupd(LocalDate dateupd) {
        this.dateupd = dateupd;
    }

    public Boolean isAdvancedstockmanagement() {
        return advancedstockmanagement;
    }

    public Product advancedstockmanagement(Boolean advancedstockmanagement) {
        this.advancedstockmanagement = advancedstockmanagement;
        return this;
    }

    public void setAdvancedstockmanagement(Boolean advancedstockmanagement) {
        this.advancedstockmanagement = advancedstockmanagement;
    }

    public Boolean isPackstocktype() {
        return packstocktype;
    }

    public Product packstocktype(Boolean packstocktype) {
        this.packstocktype = packstocktype;
        return this;
    }

    public void setPackstocktype(Boolean packstocktype) {
        this.packstocktype = packstocktype;
    }

    public Boolean isState() {
        return state;
    }

    public Product state(Boolean state) {
        this.state = state;
        return this;
    }

    public void setState(Boolean state) {
        this.state = state;
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
        Product product = (Product) o;
        if (product.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", idsupplier='" + getIdsupplier() + "'" +
            ", idmanufacturer='" + getIdmanufacturer() + "'" +
            ", idcategorydefault='" + getIdcategorydefault() + "'" +
            ", idshopdefault='" + getIdshopdefault() + "'" +
            ", idtaxtrulesgroup='" + getIdtaxtrulesgroup() + "'" +
            ", onsale='" + isOnsale() + "'" +
            ", onlineonly='" + isOnlineonly() + "'" +
            ", ean13='" + getEan13() + "'" +
            ", isbn='" + getIsbn() + "'" +
            ", upc='" + getUpc() + "'" +
            ", ecotax='" + getEcotax() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", minimalquantity='" + getMinimalquantity() + "'" +
            ", price='" + getPrice() + "'" +
            ", wholesaleprice='" + getWholesaleprice() + "'" +
            ", unity='" + getUnity() + "'" +
            ", unitpriceratio='" + getUnitpriceratio() + "'" +
            ", additionalshippingcost='" + getAdditionalshippingcost() + "'" +
            ", reference='" + getReference() + "'" +
            ", supplierreference='" + getSupplierreference() + "'" +
            ", location='" + getLocation() + "'" +
            ", width='" + getWidth() + "'" +
            ", height='" + getHeight() + "'" +
            ", depth='" + getDepth() + "'" +
            ", weight='" + getWeight() + "'" +
            ", outofstock='" + getOutofstock() + "'" +
            ", quantitydiscount='" + isQuantitydiscount() + "'" +
            ", customizable='" + getCustomizable() + "'" +
            ", uploadablefiles='" + getUploadablefiles() + "'" +
            ", textfields='" + getTextfields() + "'" +
            ", active='" + getActive() + "'" +
            ", redirecttype='" + getRedirecttype() + "'" +
            ", idtyperedirected='" + getIdtyperedirected() + "'" +
            ", availablefororder='" + isAvailablefororder() + "'" +
            ", availabledate='" + getAvailabledate() + "'" +
            ", showcondition='" + isShowcondition() + "'" +
            ", condition='" + getCondition() + "'" +
            ", showprice='" + isShowprice() + "'" +
            ", indexed='" + isIndexed() + "'" +
            ", visibility='" + getVisibility() + "'" +
            ", cacheispack='" + isCacheispack() + "'" +
            ", cachehasatachments='" + isCachehasatachments() + "'" +
            ", isvirtual='" + isIsvirtual() + "'" +
            ", cachedefaultattribute='" + getCachedefaultattribute() + "'" +
            ", dateadd='" + getDateadd() + "'" +
            ", dateupd='" + getDateupd() + "'" +
            ", advancedstockmanagement='" + isAdvancedstockmanagement() + "'" +
            ", packstocktype='" + isPackstocktype() + "'" +
            ", state='" + isState() + "'" +
            "}";
    }
}
