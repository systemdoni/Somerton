package com.somerton

class BootStrap {

    def init = { servletContext ->
        new Role(authority: 'ROLE_COACH').save()
        new Role(authority: 'ROLE_CLIENT').save()
        new SportType(name: 'Boxing & Yoga').save()
        //new SportType(name: 'Football').save()
    }
    def destroy = {
    }
}
