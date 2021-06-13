/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vikto
 */
@Entity
@Table(name = "skins")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Skins.findAll", query = "SELECT s FROM Skins s")
    , @NamedQuery(name = "Skins.findBySkinId", query = "SELECT s FROM Skins s WHERE s.skinId = :skinId")
    , @NamedQuery(name = "Skins.findBySkinName", query = "SELECT s FROM Skins s WHERE s.skinName = :skinName")
    , @NamedQuery(name = "Skins.findBySkinPrice", query = "SELECT s FROM Skins s WHERE s.skinPrice = :skinPrice")
    , @NamedQuery(name = "Skins.findBySkinImagePath", query = "SELECT s FROM Skins s WHERE s.skinImagePath = :skinImagePath")})
public class Skins implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "skin_id")
    private Integer skinId;
    @Column(name = "skin_name")
    private String skinName;
    @Column(name = "skin_price")
    private Integer skinPrice;
    @Column(name = "skin_image_path")
    private String skinImagePath;
    @ManyToMany(mappedBy = "skinsList")
    private List<User> userList;

    public Skins() {
    }

    public Skins(String skinName, Integer skinPrice, String skinImagePath) {
        this.skinName = skinName;
        this.skinPrice = skinPrice;
        this.skinImagePath = skinImagePath;
    }

    public Skins(Integer skinId) {
        this.skinId = skinId;
    }

    public Integer getSkinId() {
        return skinId;
    }

    public void setSkinId(Integer skinId) {
        this.skinId = skinId;
    }

    public String getSkinName() {
        return skinName;
    }

    public void setSkinName(String skinName) {
        this.skinName = skinName;
    }

    public Integer getSkinPrice() {
        return skinPrice;
    }

    public void setSkinPrice(Integer skinPrice) {
        this.skinPrice = skinPrice;
    }

    public String getSkinImagePath() {
        return skinImagePath;
    }

    public void setSkinImagePath(String skinImagePath) {
        this.skinImagePath = skinImagePath;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (skinId != null ? skinId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Skins)) {
            return false;
        }
        Skins other = (Skins) object;
        if ((this.skinId == null && other.skinId != null) || (this.skinId != null && !this.skinId.equals(other.skinId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Skins[ skinId=" + skinId + " ]";
    }

}
