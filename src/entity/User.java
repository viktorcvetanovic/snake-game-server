/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author vikto
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId")
    , @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username")
    , @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")
    , @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
    , @NamedQuery(name = "User.findByGameName", query = "SELECT u FROM User u WHERE u.gameName = :gameName")
    , @NamedQuery(name = "User.findByMoney", query = "SELECT u FROM User u WHERE u.money = :money")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "game_name")
    private String gameName;
    @Column(name = "money")
    private Integer money;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "userskin", inverseJoinColumns = {
        @JoinColumn(name = "skin_id", referencedColumnName = "skin_id")}, joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")})
    private List<Skins> skinsList;
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Bestscores> bestscoresList;

    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public User(Integer userId, String username, String password, String email, String gameName, Integer money, List<Skins> skinsList, List<Bestscores> bestscoresList) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.gameName = gameName;
        this.money = money;
        this.skinsList = skinsList;
        this.bestscoresList = bestscoresList;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @XmlTransient
    public List<Skins> getSkinsList() {
        return skinsList;
    }

    public void setSkinsList(List<Skins> skinsList) {
        this.skinsList = skinsList;
    }

    @XmlTransient
    public List<Bestscores> getBestscoresList() {
        return bestscoresList;
    }

    public void setBestscoresList(List<Bestscores> bestscoresList) {
        this.bestscoresList = bestscoresList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.User[ userId=" + userId + " ]";
    }

    public void addSkinsToList(Skins s) {
        if (!skinsList.contains(s)) {
            skinsList.add(s);
        }
    }

    public void addScoreToList(Bestscores s) {
        bestscoresList.add(s);
    }

}
