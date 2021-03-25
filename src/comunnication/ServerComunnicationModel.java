/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunnication;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author vikto
 * @param <T>
 */
public class ServerComunnicationModel<T> implements Serializable {

    private String reason;
    private List<T> data;

    public ServerComunnicationModel() {
    }

    public ServerComunnicationModel(String reason, List<T> data) {
        this.reason = reason;
        this.data = data;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ServerComunnicationModel{" + "reason=" + reason + ", data=" + data + '}';
    }

}
