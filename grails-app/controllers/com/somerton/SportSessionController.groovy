package com.somerton

import com.somerton.utils.FileUtils
import grails.validation.ValidationException

import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

class SportSessionController {

    def springSecurityService

    SportSessionService sportSessionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_COACH', 'ROLE_CLIENT'])
    def index() {
        List<SportSession> results
        if(params.location && params.sport)
            results = SportSession.findAllByLocationIlikeAndSportType("%${params.location}%",SportType.findById(params.sport))
        else if (params.location)
            results = SportSession.findAllByLocationIlike("%${params.location}%")
        else if(params.sport)
            results = SportSession.findAllBySportType(SportType.findById(params.sport))
        else
            results = SportSession.findAll()
        respond results
    }

    @Secured('ROLE_COACH')
    def create() {
        respond new SportSession()
    }

    @Secured('ROLE_COACH')
    def save(SportSession sportSession) {
        if (sportSession == null) {
            flash.message = "Error: Sport Session is not initialized"
            redirect action: "index"
            return
        }

        try {
            def sportSessionPicPart = request.getPart("sportSessionPicFile")

            if (sportSessionPicPart==null || sportSessionPicPart.size==0){
                flash.message = "No picture chosen for the sport session."
                redirect action: "index"
                return
            }

            sportSession.picture = FileUtils.generateFileName(sportSessionPicPart)

            FileUtils.saveFile(sportSessionPicPart,sportSession.picture)

            sportSession.coach = User.get(springSecurityService.principal.id)
            sportSession.sportType = SportType.findByName('Boxing & Yoga')

            sportSessionService.save(sportSession)
        } catch (ValidationException e) {
            respond sportSession.errors, view:'create'
            return
        }

        redirect uri:'/'
    }

}
