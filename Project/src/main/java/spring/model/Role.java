/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Role implements Serializable {

    private long id;
    
    @Size(min = 1, message = "Name is required.")
    private String name;

    // constructors 
    public Role() {
        this.setId(System.nanoTime());
    }

    public Role(long id, String name) {

        this.setId(id);
        this.setName(name);

    }

    // getters and setters  
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
