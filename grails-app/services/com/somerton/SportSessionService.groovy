package com.somerton

import grails.gorm.services.Service

@Service(SportSession)
interface SportSessionService {

    SportSession get(Serializable id)
    
    SportSession save(SportSession sportSession)

}