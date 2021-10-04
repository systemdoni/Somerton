package com.somerton

class SportSession {

    String name
    String location
    String picture
    User coach
    SportType sportType

    static constraints = {
        name nullable: false, blank: false
        location nullable: false, blank: false
        picture nullable: false, blank: false, display: false
        coach nullable: false, blank: false, display: false
        sportType nullable: false, blank: false, display: false
    }

    String toString() {
        name
    }


}
