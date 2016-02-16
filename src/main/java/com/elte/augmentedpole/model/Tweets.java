
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
@Table(name = "tweets")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tweets.findAll", query = "SELECT t FROM Tweets t"),
    @NamedQuery(name = "Tweets.findById", query = "SELECT t FROM Tweets t WHERE t.id = :id"),
    @NamedQuery(name = "Tweets.findByText", query = "SELECT t FROM Tweets t WHERE t.text = :text"),
    @NamedQuery(name = "Tweets.findBySnowflake", query = "SELECT t FROM Tweets t WHERE t.snowflake = :snowflake"),
    @NamedQuery(name = "Tweets.findByTranslatedText", query = "SELECT t FROM Tweets t WHERE t.translatedText = :translatedText"),
    @NamedQuery(name = "Tweets.findByTimestamp", query = "SELECT t FROM Tweets t WHERE t.timestamp = :timestamp")})
public class Tweets implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "text")
    private String text;
    @Basic(optional = false)
    @Column(name = "snowflake")
    private long snowflake;
    @Basic(optional = false)
    @Column(name = "translated_text")
    private String translatedText;
    @Basic(optional = false)
    @Column(name = "timestamp")
    private long timestamp;

    public Tweets() {
    }

    public Tweets(Integer id) {
        this.id = id;
    }

    public Tweets(Integer id, String text, long snowflake, String translatedText, long timestamp) {
        this.id = id;
        this.text = text;
        this.snowflake = snowflake;
        this.translatedText = translatedText;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getSnowflake() {
        return snowflake;
    }

    public void setSnowflake(long snowflake) {
        this.snowflake = snowflake;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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
        if (!(object instanceof Tweets)) {
            return false;
        }
        Tweets other = (Tweets) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.elte.augmentedpole.model.Tweets[ id=" + id + " ]";
    }
    
}
