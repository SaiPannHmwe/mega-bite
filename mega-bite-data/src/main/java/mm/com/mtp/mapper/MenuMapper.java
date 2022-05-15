package mm.com.mtp.mapper;

import mm.com.mtp.datatable.MenuDataTable;
import mm.com.mtp.form.MenuForm;
import mm.com.mtp.model.Menu;
import mm.com.mtp.payload.MenuResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Created by Sai Pann Hmwe on 4/3/2022.
 */
@Mapper(componentModel = "spring")
public interface MenuMapper {

    MenuResponse toMenuResponse(Menu menu);

    MenuDataTable toMenuDataTable(Menu menu);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "lastModifiedDate", ignore = true)
    })
    Menu toMenu(MenuForm menuForm);

    @Mappings({
            @Mapping(target = "id", source = "menu.id"),
            @Mapping(target = "name", source = "menuForm.name"),
            @Mapping(target = "nameChinese", source = "menuForm.nameChinese")
    })
    Menu toMenu(Menu menu, MenuForm menuForm);

    MenuForm toMenuForm(Menu menu);

}
