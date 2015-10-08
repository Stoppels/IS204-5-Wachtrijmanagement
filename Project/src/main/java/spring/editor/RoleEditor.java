/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.editor;

import spring.model.Role;
import spring.service.RoleService;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleEditor extends PropertyEditorSupport {

    @Autowired
    private RoleService roleService;

    // Converts a String role id to a Roll (when submitting form)
    @Override
    public void setAsText(String text) {
       Role r = this.roleService.roleGetById(Long.valueOf(text));

        this.setValue(r);
    }

}

