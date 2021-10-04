package com.somerton

class SportType {

    String name

    static constraints = {
        name nullable: false, blank: false
    }

    String toString(){
        name
    }

}
