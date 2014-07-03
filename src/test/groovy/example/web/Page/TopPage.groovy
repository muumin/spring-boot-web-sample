package example.web.Page

import geb.Page

class TopPage extends Page {
    static url = 'http://localhost:8080'
    static at = { waitFor { title == 'Jumbotron Template for bootstrap' } }
    static content = {
        hello { $('h1') }
        nameForm { module NameModule }
    }
}
