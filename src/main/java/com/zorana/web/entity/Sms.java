/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zorana.web.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author Zorana
 */
@Entity
@Table(name = "sms")
public class Sms implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "smsid")
    private Integer smsid;
    @Column(name = "sentdate")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date sentdate;
    @Size(max = 50)
    @Column(name = "tonumber")
    private String tonumber;
    @Size(max = 250)
    @Column(name = "message")
    private String message;
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    @ManyToOne
    private User userid;

    public Sms() {
    }

    public Sms(Integer smsid) {
        this.smsid = smsid;
    }

    public Integer getSmsid() {
        return smsid;
    }

    public void setSmsid(Integer smsid) {
        this.smsid = smsid;
    }

    public Date getSentdate() {
        return sentdate;
    }

    public void setSentdate(Date sentdate) {
        this.sentdate = sentdate;
    }

    public String getTonumber() {
        return tonumber;
    }

    public void setTonumber(String tonumber) {
        this.tonumber = tonumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (smsid != null ? smsid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sms)) {
            return false;
        }
        Sms other = (Sms) object;
        if ((this.smsid == null && other.smsid != null) || (this.smsid != null && !this.smsid.equals(other.smsid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zorana.web.entity.Sms[ smsid=" + smsid + " ]";
    }
    
}
