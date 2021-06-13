/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vikto
 */
@Entity
@Table(name = "bestscores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bestscores.findAll", query = "SELECT b FROM Bestscores b")
    , @NamedQuery(name = "Bestscores.findByScoreId", query = "SELECT b FROM Bestscores b WHERE b.scoreId = :scoreId")
    , @NamedQuery(name = "Bestscores.findByScore", query = "SELECT b FROM Bestscores b WHERE b.score = :score")})
public class Bestscores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_id")
    private Integer scoreId;
    @Column(name = "score")
    private Integer score;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    public Bestscores() {
    }

    public Bestscores(Integer score, User user_id) {
        this.score = score;
        this.userId = user_id;
    }

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scoreId != null ? scoreId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bestscores)) {
            return false;
        }
        Bestscores other = (Bestscores) object;
        if ((this.scoreId == null && other.scoreId != null) || (this.scoreId != null && !this.scoreId.equals(other.scoreId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Bestscores[ scoreId=" + scoreId + " ]";
    }

}
