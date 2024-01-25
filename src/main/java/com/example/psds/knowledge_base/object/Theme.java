package com.example.psds.knowledge_base.object;



import java.util.ArrayList;
import java.util.List;

public class Theme {
    private long id;
    private String title;
    private String description;
    private List<ThemeAndProfile> tapTheme = new ArrayList<>();
    private List<Lesson> lessons = new ArrayList<>();
}
