# PSDS
Professional skills development system Java School 

**personal_account (модуль Личный кабинет)**\
**knowledge_base (модуль База знаний)**

Подключение к БД находится в application.properties. Можно изменить конфигурацию (если нужно, чтобы hibernate только обновлял БД, то изменить create -> update)

Если в БД не создаются таблицы автоматически: добавить аннотацию @Table(name="<table_name>"")