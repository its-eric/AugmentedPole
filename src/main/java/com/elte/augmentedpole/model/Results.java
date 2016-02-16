
package com.elte.augmentedpole.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eric
 */
@Entity
@Table(name = "results")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Results.findAll", query = "SELECT r FROM Results r"),
    @NamedQuery(name = "Results.findById", query = "SELECT r FROM Results r WHERE r.id = :id"),
    @NamedQuery(name = "Results.findByName", query = "SELECT r FROM Results r WHERE r.name = :name"),
    @NamedQuery(name = "Results.findByImageURL", query = "SELECT r FROM Results r WHERE r.imageURL = :imageURL")})
public class Results implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "imageURL")
    private String imageURL;

    public Results() {
    }

    public Results(Integer id) {
        this.id = id;
    }

    public Results(Integer id, String name, String imageURL) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Results)) {
            return false;
        }
        Results other = (Results) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.elte.augmentedpole.model.Results[ id=" + id + " ]";
    }
    
}
