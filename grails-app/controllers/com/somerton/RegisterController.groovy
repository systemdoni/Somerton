package com.somerton

import com.somerton.utils.FileUtils
import grails.validation.ValidationException
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional
@Secured('permitAll')
class RegisterController {

    static allowedMethods = [register: "POST"]

    def index() { }

    def register() {
        if(!params.password.equals(params.repassword)) {
            flash.message = "Passwords do not match"
            redirect action: "index"
        } else {
            try {
                if (User.findByUsername(params.username))
                {
                    flash.message = "Username already in use"
                    redirect action: "index"
                    return
                }

                def user = new User(username: params.username,
                                    password: params.password,
                                    firstName: params.firstName,
                                    lastName: params.lastName,
                                    profilePic: params.profilePic
                )
                def profilePicPart = request.getPart("profilePicFile")
                if (profilePicPart==null || profilePicPart.size==0){
                    flash.message = "No profile picture chosen"
                    redirect action: "index"
                    return
                }
                user.profilePic = FileUtils.generateFileName(profilePicPart)

                FileUtils.saveFile(profilePicPart, user.profilePic)

                user.save()

                def role = Role.get(params.role.id)

                if(role) {
                    UserRole.create user, role

                    UserRole.withSession {
                      it.flush()
                      it.clear()
                    }

                    flash.message = "Your registration was successful. Please login."
                    redirect controller: "login", action: "auth"
                } else {
                    flash.message = "Registration failed"
                    render view: "index"
                }
            } catch (ValidationException e) {
                flash.message = "Registration failed"
                redirect action: "index"
            }
        }
    }
}
