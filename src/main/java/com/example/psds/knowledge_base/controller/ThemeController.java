package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.dto.ThemeDTO;
import com.example.psds.knowledge_base.model.Theme;
import com.example.psds.knowledge_base.service.ThemeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/themes")
public class ThemeController {
    private final ThemeService themeService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<ThemeDTO> getThemesList(){
        return themeService.getThemeList();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void changeTheme(@RequestBody ThemeDTO themeDTO){
        Theme theme = themeService.saveTheme(themeDTO);
        for (int j=0; j<theme.getLessons().size(); j++){
            theme.getLessons().get(j).setTheme(theme);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{themeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTheme(@PathVariable Long themeId){
        themeService.deleteTheme(themeId);
    }
}
