package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.dto.ThemeDTO;
import com.example.psds.knowledge_base.service.ThemeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theme")
public class ThemeController {
    private final ThemeService themeService;

    public ThemeController(final ThemeService themeService) {
        this.themeService = themeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<ThemeDTO> getThemesList(){
        return themeService.getThemeList();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void changeTheme(@RequestBody ThemeDTO themeDTO){
        themeService.changeTheme(themeDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{themeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTheme(@PathVariable Long themeId){
        themeService.deleteTheme(themeId);
    }
}
