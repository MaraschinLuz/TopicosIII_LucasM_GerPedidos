package br.upf.projectapp.converter;

import br.upf.projectapp.entity.ClienteEntity;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Named;

@Named
@FacesConverter(forClass = ClienteEntity.class)
public class ClienteConverter implements Converter<ClienteEntity> {

    @Override
    public ClienteEntity getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Integer id = Integer.valueOf(value);
            // Aqui você precisaria de uma forma de buscar o ClienteEntity pelo ID.
            // Idealmente, você injetaria o ClienteFacade aqui, mas em um converter isso é mais complexo.
            // Para simplificar, vamos retornar um ClienteEntity com o ID, assumindo que o restante dos dados será preenchido.
            // Em um ambiente real, você buscaria o objeto completo do banco de dados.
            ClienteEntity cliente = new ClienteEntity();
            cliente.setId(id);
            return cliente;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, ClienteEntity value) {
        if (value == null) {
            return null;
        }
        if (value.getId() == null) {
            return null;
        }
        return value.getId().toString();
    }
}


